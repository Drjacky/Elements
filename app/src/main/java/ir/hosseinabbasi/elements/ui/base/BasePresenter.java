package ir.hosseinabbasi.elements.ui.base;

import javax.inject.Inject;

import ir.hosseinabbasi.elements.data.DataManager;
import ir.hosseinabbasi.elements.utils.rx.RxDisposableFactory;
import ir.hosseinabbasi.elements.utils.rx.RxDisposables;
import ir.hosseinabbasi.elements.utils.rx.ThreadTransformer;

/**
 * Created by Dr.jacky on 2017/11/18.
 */

public class BasePresenter<V extends BaseView> implements IBasePresenter<V> {

    private static final String TAG = "BasePresenter";

    private final DataManager mDataManager;
    private final ThreadTransformer mThreadTransformer;
    private final RxDisposables mDisposables;

    private V mBaseView;

    @Inject
    public BasePresenter(DataManager dataManager,
                         ThreadTransformer threadTransformer,
                         RxDisposableFactory rxDisposableFactory) {
        this.mDataManager = dataManager;
        this.mThreadTransformer = threadTransformer;
        this.mDisposables = rxDisposableFactory.get();
    }

    @Override
    public void onAttach(V mvpView) {
        mBaseView = mvpView;
    }

    @Override
    public void onDetach() {
        mDisposables.clear();
        mBaseView = null;
    }


    public boolean isViewAttached() {
        return mBaseView != null;
    }

    public V getMvpView() {
        return mBaseView;
    }

    public void checkViewAttached() {
        if (!isViewAttached()) throw new MvpViewNotAttachedException();
    }

    public DataManager getDataManager() {
        return mDataManager;
    }

    public ThreadTransformer getThreadTransformer() {
        return mThreadTransformer;
    }

    public RxDisposables getRxDisposables() {
        return mDisposables;
    }

    public static class MvpViewNotAttachedException extends RuntimeException {
        public MvpViewNotAttachedException() {
            super("Please call Presenter.onAttach(BaseView) before" +
                    " requesting data to the Presenter");
        }
    }
}

