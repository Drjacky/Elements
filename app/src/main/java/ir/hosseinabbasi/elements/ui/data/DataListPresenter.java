package ir.hosseinabbasi.elements.ui.data;

import android.os.Environment;
import android.util.Log;

import com.opencsv.CSVReader;
import com.opencsv.bean.CsvToBeanBuilder;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import io.reactivex.Single;
import io.reactivex.SingleObserver;
import io.reactivex.SingleSource;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.BiConsumer;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import ir.hosseinabbasi.elements.R;
import ir.hosseinabbasi.elements.data.DataManager;
import ir.hosseinabbasi.elements.data.db.model.Data;
import ir.hosseinabbasi.elements.ui.base.BasePresenter;
import ir.hosseinabbasi.elements.utils.rx.RxDisposableFactory;
import ir.hosseinabbasi.elements.utils.rx.RxDisposables;
import ir.hosseinabbasi.elements.utils.rx.ThreadTransformer;
import okhttp3.ResponseBody;
import okio.BufferedSink;
import okio.Okio;
import retrofit2.Response;

/**
 * Created by Dr.jacky on 2017/11/19.
 */


public final class DataListPresenter<V extends DataListView> extends BasePresenter<V> implements IDataListPresenter<V> {

    private final ThreadTransformer threadTransformer;
    private final RxDisposables disposables;
    private String fileName;

    @Inject
    public DataListPresenter(DataManager dataManager,
                             ThreadTransformer threadTransformer,
                             RxDisposableFactory rxDisposableFactory) {
        super(dataManager, threadTransformer, rxDisposableFactory);
        this.threadTransformer = getThreadTransformer();
        this.disposables = getRxDisposables();
    }

    @Override
    public void getDataList(Map<String, String> options) {
        getMvpView().showLoading();
        disposables.add(getDataManager().downloadFile(options)
                .compose(threadTransformer.applySchedulers())
                .flatMap(processResponse())
                .flatMap(file -> {
                    List<Data> dataList = new ArrayList<>();
                    FileReader fileReader = new FileReader(file.getAbsoluteFile());
                    /*CsvToBeanBuilder<Data> csvToBeanBuilder = new CsvToBeanBuilder<>(fileReader);
                    List<Data> beans = csvToBeanBuilder
                            .withType(Data.class).build().parse();*/
                    CSVReader reader = new CSVReader(fileReader);
                    for(String[] nextLine : reader)
                        dataList.add(new Data(nextLine[0], nextLine[1], nextLine[2]));

                    return Observable.just(dataList);
                }).subscribe(dataList -> {
                    getMvpView().hideLoading();
                    getMvpView().loadDataList(dataList);
                }, throwable -> {
                    getMvpView().hideLoading();
                    getMvpView().onError(R.string.error_save_to_disk);
                }));
                // There is just one item. No BackPressure strategy.
    }

    private Function<Response<ResponseBody>, Observable<File>> processResponse() {
        return this::saveToDisk;
    }

    private Observable<File> saveToDisk(final Response<ResponseBody> responseBodyResponse) {
        return Observable.create(sub -> {

                File file = null;
                try {
                    String header = responseBodyResponse.headers().get("Content-Disposition");
                    fileName = header.replace("attachment; filename=", "");
                    if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED))
                        file = new File(Environment.getExternalStorageDirectory().getAbsoluteFile(), fileName);
                    // Add other possibilities for some devices.

                    BufferedSink sink = Okio.buffer(Okio.sink(file));
                    sink.writeAll(responseBodyResponse.body().source());
                    sink.close();
                    sub.onNext(file);
                } catch (IOException e) {
                    getMvpView().onError(R.string.error_save_to_disk);
                    e.printStackTrace();
                    sub.onError(e);
                }
        });
    }

}