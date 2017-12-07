package ir.hosseinabbasi.elements.utils.rx;

import javax.inject.Inject;

import ir.hosseinabbasi.elements.utils.rx.impl.CompositeDisposablesImpl;

/**
 * Created by Dr.jacky on 2017/11/18.
 */

public class RxDisposableFactory {

    @Inject
    public RxDisposableFactory() {
    }

    public RxDisposables get() {
        return new CompositeDisposablesImpl();
    }
}
