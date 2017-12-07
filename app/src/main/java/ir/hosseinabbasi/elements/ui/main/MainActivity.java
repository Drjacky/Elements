package ir.hosseinabbasi.elements.ui.main;

import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.util.Log;
import android.widget.Toast;

import javax.inject.Inject;

import butterknife.ButterKnife;
import ir.hosseinabbasi.elements.R;
import ir.hosseinabbasi.elements.ui.base.BaseActivity;
import ir.hosseinabbasi.elements.ui.data.DataListView;

/**
 * Created by Dr.jacky on 2017/11/18.
 */

public class MainActivity extends BaseActivity implements IMainActivityView {

    @Inject
    MainActivityPresenter<IMainActivityView> mPresenter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getActivityComponent().inject(this);
        setUnBinder(ButterKnife.bind(this));
        initViews();
        mPresenter.onAttach(this);
    }

    @Override
    public void onDestroy() {
        mPresenter.onDetach();
        super.onDestroy();
    }

    private void initViews() {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.v_container, DataListView.getInstance(), DataListView.TAG)
                .commit();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(requestCode == 1 && grantResults[0] == PackageManager.PERMISSION_GRANTED)
            ((DataListView) getSupportFragmentManager().findFragmentByTag(DataListView.TAG)).callPresenterGetDataList();
        else
            onError(R.string.error_save_to_disk);
    }

}
