package ir.hosseinabbasi.elements.ui.data;

import java.util.List;

import ir.hosseinabbasi.elements.data.db.model.Data;
import ir.hosseinabbasi.elements.ui.base.BaseView;

/**
 * Created by Dr.jacky on 2017/11/19.
 */


public interface IDataListView extends BaseView {

    void loadDataList(List<Data> dataList);

    void loadDataDetail(Data data); // Go to Description page.

}
