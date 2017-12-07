package ir.hosseinabbasi.elements.ui.data;

import android.Manifest;
import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import ir.hosseinabbasi.elements.R;
import ir.hosseinabbasi.elements.data.db.model.Data;
import ir.hosseinabbasi.elements.di.ActivityContext;
import ir.hosseinabbasi.elements.ui.base.BaseFragment;
import ir.hosseinabbasi.elements.ui.detail.DetailView;
import ir.hosseinabbasi.elements.ui.main.MainActivity;

/**
 * Created by Dr.jacky on 2017/11/19.
 */


public class DataListView extends BaseFragment implements IDataListView {

    public static final String TAG = "DataListView";

    @Inject
    @ActivityContext
    Context mContext;

    @Inject
    Activity mActivity;

    @Inject
    DataListPresenter<DataListView> mPresenter;

    @BindView(R.id.fragment_data_rcyData)
    RecyclerView rcyData;

    private Map<String, String> data;

    private NetworkReceiver networkReceiver = new NetworkReceiver();

    public static DataListView getInstance() {
        return new DataListView();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_data, container, false);
        getActivityComponent().inject(this);
        setUnBinder(ButterKnife.bind(this, view));
        mPresenter.onAttach(this);
        initViews();
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        data = new HashMap<>();
        data.put("key", "0Aqg9JQbnOwBwdEZFN2JKeldGZGFzUWVrNDBsczZxLUE");
        data.put("single", "true");
        data.put("gid", "0");
        data.put("output", "csv");
        if(isNetworkConnected()) {
            if (((MainActivity) mContext).hasPermission(android.Manifest.permission.WRITE_EXTERNAL_STORAGE))
                mPresenter.getDataList(data);
            else
                ActivityCompat.requestPermissions(mActivity, new String[]{android.Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
        }
        else
            onError(R.string.error_internet);
    }

    @Override
    public void onResume() {
        super.onResume();

        IntentFilter filter = new IntentFilter();
        filter.addAction("android.net.conn.CONNECTIVITY_CHANGE");
        mActivity.registerReceiver(networkReceiver, filter);
    }

    @Override
    public void onPause() {
        super.onPause();
        mActivity.unregisterReceiver(networkReceiver);
    }

    public class NetworkReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            String tempAction = intent.getAction();
            if (tempAction != null && tempAction.equals(ConnectivityManager.CONNECTIVITY_ACTION)) {
                NetworkInfo networkInfo = intent.getParcelableExtra(ConnectivityManager.EXTRA_NETWORK_INFO);
                if (networkInfo != null && networkInfo.getDetailedState() == NetworkInfo.DetailedState.CONNECTED) {
                    if(((MainActivity) mContext).hasPermission(android.Manifest.permission.WRITE_EXTERNAL_STORAGE))
                        mPresenter.getDataList(data);
                    else
                        ActivityCompat.requestPermissions(mActivity, new String[]{android.Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
                }
                else if (networkInfo != null && networkInfo.getDetailedState() == NetworkInfo.DetailedState.DISCONNECTED)
                    onError(R.string.error_internet);
            }
        }
    }

    @Override
    public void onDestroyView() {
        mPresenter.onDetach();
        super.onDestroyView();
    }

    @Override
    public void loadDataList(List<Data> data) {
        DataListAdapter adapter = new DataListAdapter(mContext, data, this);
        GridLayoutManager manager =
                new GridLayoutManager(mContext, 1);
        rcyData.setLayoutManager(manager);
        rcyData.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void loadDataDetail(Data data) {
        Bundle bundle = new Bundle();
        bundle.putParcelable("data", data);
        DetailView detailFragment = DetailView.getInstance();
        detailFragment.setArguments(bundle);

        ((MainActivity)mContext).getSupportFragmentManager()
                .beginTransaction()
                .setCustomAnimations(R.anim.slide_left, R.anim.slide_right)
                .add(R.id.v_container, detailFragment, DetailView.TAG)
                .addToBackStack(DetailView.TAG)
                .commit();
    }

    private void initViews() {
        rcyData.setLayoutManager(new LinearLayoutManager(getActivity()));
        rcyData.setHasFixedSize(true);
    }

    public void callPresenterGetDataList(){
        mPresenter.getDataList(data);
    }

}