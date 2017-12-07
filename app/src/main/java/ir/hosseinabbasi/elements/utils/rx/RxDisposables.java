package ir.hosseinabbasi.elements.utils.rx;

import io.reactivex.disposables.Disposable;

/**
 * Created by Dr.jacky on 2017/11/18.
 */

public interface RxDisposables {

    void add(Disposable disposable);

    void clear();
}
