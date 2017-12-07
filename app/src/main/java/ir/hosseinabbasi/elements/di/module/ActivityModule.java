package ir.hosseinabbasi.elements.di.module;

import android.app.Activity;
import android.content.Context;

import dagger.Module;
import dagger.Provides;
import io.reactivex.disposables.CompositeDisposable;
import ir.hosseinabbasi.elements.di.ActivityContext;
import ir.hosseinabbasi.elements.di.PerActivity;
import ir.hosseinabbasi.elements.ui.main.IMainActivityPresenter;
import ir.hosseinabbasi.elements.ui.main.IMainActivityView;
import ir.hosseinabbasi.elements.ui.main.MainActivityPresenter;

/**
 * Created by Dr.jacky on 2017/11/18.
 */

@Module
public class ActivityModule {

    private Activity mActivity;

    public ActivityModule(Activity activity) {
        this.mActivity = activity;
    }

    @Provides
    @ActivityContext
    Context provideContext() {
        return mActivity;
    }

    @Provides
    Activity provideActivity() {
        return mActivity;
    }

    @Provides
    CompositeDisposable provideCompositeDisposable() {
        return new CompositeDisposable();
    }

    @Provides
    @PerActivity
    IMainActivityPresenter<IMainActivityView> provideMainActivityPresenter(MainActivityPresenter<IMainActivityView>
                                                                                   presenter) {
        return presenter;
    }

}
