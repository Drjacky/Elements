package ir.hosseinabbasi.elements.ui.base;

import android.support.annotation.StringRes;

/**
 * Created by Dr.jacky on 2017/11/18.
 */

public interface BaseView {

    void showLoading();

    void hideLoading();

    void onError(@StringRes int resId);

    void onError(String message);

    boolean isNetworkConnected();

}
