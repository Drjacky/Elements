package ir.hosseinabbasi.elements.ui.main;

import javax.inject.Inject;

import ir.hosseinabbasi.elements.data.DataManager;
import ir.hosseinabbasi.elements.ui.base.BasePresenter;
import ir.hosseinabbasi.elements.utils.rx.RxDisposableFactory;
import ir.hosseinabbasi.elements.utils.rx.RxDisposables;
import ir.hosseinabbasi.elements.utils.rx.ThreadTransformer;

/**
 * Created by Dr.jacky on 2017/11/18.
 */

public final class MainActivityPresenter<V extends IMainActivityView> extends BasePresenter<V> implements IMainActivityPresenter<V> {

    private final ThreadTransformer threadTransformer;
    private final RxDisposables disposables;

    @Inject
    public MainActivityPresenter(DataManager dataManager,
                                 ThreadTransformer threadTransformer,
                                 RxDisposableFactory rxDisposableFactory) {
        super(dataManager, threadTransformer, rxDisposableFactory);
        this.threadTransformer = getThreadTransformer();
        this.disposables = getRxDisposables();
    }

}
