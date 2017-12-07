package ir.hosseinabbasi.elements.utils;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import ir.hosseinabbasi.elements.utils.rx.RxDisposables;
import ir.hosseinabbasi.elements.utils.rx.ThreadTransformer;
import ir.hosseinabbasi.elements.utils.rx.impl.CompositeDisposablesImpl;
import ir.hosseinabbasi.elements.utils.rx.impl.ViewThreadTransformer;
import ir.hosseinabbasi.elements.utils.rx.impl.WorkThreadTransformer;
import ir.hosseinabbasi.elements.utils.rx.qualifiers.IOThreadPref;
import ir.hosseinabbasi.elements.utils.rx.qualifiers.UIThreadPref;
import ir.hosseinabbasi.elements.utils.rx.qualifiers.WorkThreadTransformerPref;

/**
 * Created by Dr.jacky on 2017/11/18.
 */

@Module
public class UtilsModule {

    @Provides
    @Singleton
    ThreadTransformer providesViewThreadTransformer(
            ViewThreadTransformer transformer) {
        return transformer;
    }

    @Provides
    @Singleton
    @WorkThreadTransformerPref
    ThreadTransformer providesWorkThreadTransformer(
            WorkThreadTransformer transformer) {
        return transformer;
    }

    @Provides
    @Singleton
    @IOThreadPref
    Scheduler provideIoScheduler() {
        return Schedulers.io();
    }

    @Provides
    @Singleton
    @UIThreadPref
    Scheduler provideMainScheduler() {
        return AndroidSchedulers.mainThread();
    }

    @Provides
    @Singleton
    public RxDisposables provideCompositeDisposables(
            CompositeDisposablesImpl disposables) {
        return disposables;
    }

}
