package ir.hosseinabbasi.elements.utils.rx;

import io.reactivex.ObservableTransformer;
import io.reactivex.SingleTransformer;

/**
 * Created by Dr.jacky on 2017/11/18.
 */

public interface ThreadTransformer {
  //<T> SingleTransformer<T, T> applySchedulers();
  <T> ObservableTransformer<T, T> applySchedulers();
}
