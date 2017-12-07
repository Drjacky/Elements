package ir.hosseinabbasi.elements.utils.rx.impl;

import javax.inject.Inject;

import io.reactivex.ObservableTransformer;
import io.reactivex.Scheduler;
import io.reactivex.SingleTransformer;
import ir.hosseinabbasi.elements.utils.rx.ThreadTransformer;
import ir.hosseinabbasi.elements.utils.rx.qualifiers.IOThreadPref;

/**
 * Created by Dr.jacky on 2017/11/18.
 */

public final class WorkThreadTransformer implements ThreadTransformer {

    private final Scheduler subscribeOnScheduler;
    private final Scheduler observeOnScheduler;

    @Inject
    public WorkThreadTransformer(@IOThreadPref Scheduler subscribeOnScheduler,
                                 @IOThreadPref Scheduler observeOnScheduler) {
        this.subscribeOnScheduler = subscribeOnScheduler;
        this.observeOnScheduler = observeOnScheduler;
    }

    @Override
    public <T> ObservableTransformer<T, T> applySchedulers() {
        return observable -> observable.subscribeOn(subscribeOnScheduler).observeOn(observeOnScheduler);
    }

/*    @Override
    public <T> SingleTransformer<T, T> applySchedulers() {
        return observable -> observable.subscribeOn(subscribeOnScheduler).observeOn(observeOnScheduler);
    }*/
}
