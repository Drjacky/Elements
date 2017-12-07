package ir.hosseinabbasi.elements.ui.data;

import java.util.Map;

import ir.hosseinabbasi.elements.ui.base.IBasePresenter;

/**
 * Created by Dr.jacky on 2017/11/19.
 */


public interface IDataListPresenter<V extends IDataListView> extends IBasePresenter<V> {

    void getDataList(Map<String, String> options);

}
