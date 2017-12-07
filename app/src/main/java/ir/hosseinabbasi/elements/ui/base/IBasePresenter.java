package ir.hosseinabbasi.elements.ui.base;

/**
 * Created by Dr.jacky on 2017/11/18.
 */

public interface IBasePresenter<V extends BaseView> {

    void onAttach(V baseView);

    void onDetach();
}
