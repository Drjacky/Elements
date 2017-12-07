package ir.hosseinabbasi.elements.ui.detail;

import javax.inject.Inject;

import ir.hosseinabbasi.elements.data.DataManager;
import ir.hosseinabbasi.elements.ui.base.BasePresenter;
import ir.hosseinabbasi.elements.utils.rx.RxDisposableFactory;
import ir.hosseinabbasi.elements.utils.rx.RxDisposables;
import ir.hosseinabbasi.elements.utils.rx.ThreadTransformer;

/**
 * Created by Dr.jacky on 2017/11/18.
 */


public final class DetailPresenter<V extends DetailView> extends BasePresenter<V> implements IDetailPresenter<V> {

    private final ThreadTransformer threadTransformer;
    private final RxDisposables disposables;

    @Inject
    public DetailPresenter(DataManager dataManager,
                           ThreadTransformer threadTransformer,
                           RxDisposableFactory rxDisposableFactory) {
        super(dataManager, threadTransformer, rxDisposableFactory);
        this.threadTransformer = getThreadTransformer();
        this.disposables = getRxDisposables();
    }

}