package ir.hosseinabbasi.elements.data;

import android.content.Context;

import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Observable;
import io.reactivex.Single;
import ir.hosseinabbasi.elements.data.db.model.Data;
import ir.hosseinabbasi.elements.data.network.ApiHelper;
import ir.hosseinabbasi.elements.di.ApplicationContext;
import okhttp3.ResponseBody;
import retrofit2.Response;
import retrofit2.http.QueryMap;

/**
 * Created by Dr.jacky on 2017/11/18.
 */

@Singleton
public class AppDataManager implements DataManager {

    private static final String TAG = "AppDataManager";

    private final Context mContext;
    private final ApiHelper mApiHelper;

    @Inject
    public AppDataManager(@ApplicationContext Context context,
                          ApiHelper apiHelper) {
        mContext = context;
        mApiHelper = apiHelper;
    }

    @Override
    public Observable<Response<ResponseBody>> downloadFile(@QueryMap Map<String, String> options) {
        return mApiHelper.downloadFile(options); // Get the whole data from server
    }

}
