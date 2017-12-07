package ir.hosseinabbasi.elements.ui.detail;

import android.content.Context;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import ir.hosseinabbasi.elements.R;
import ir.hosseinabbasi.elements.data.db.model.Data;
import ir.hosseinabbasi.elements.data.network.ApiEndPoint;
import ir.hosseinabbasi.elements.di.ActivityContext;
import ir.hosseinabbasi.elements.ui.base.BaseFragment;

/**
 * Created by Dr.jacky on 2017/11/18.
 */


public class DetailView extends BaseFragment implements IDetailView {

    public static final String TAG = "DetailView";

    @Inject
    @ActivityContext
    Context mContext;

    @Inject
    Resources mResources;

    @Inject
    DetailPresenter<DetailView> mPresenter;

    @BindView(R.id.fragment_detail_txtTitle)
    TextView txtPhotoTitle;

    @BindView(R.id.fragment_detail_txtDescription)
    TextView txtDescription;

    @BindView(R.id.fragment_detail_imgOriginal)
    ImageView imgPhotoOriginal;

    public static DetailView getInstance() {
        return new DetailView();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_detail, container, false);
        getActivityComponent().inject(this);
        setUnBinder(ButterKnife.bind(this, view));
        mPresenter.onAttach(this);
        initViews();
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onDestroyView() {
        mPresenter.onDetach();
        super.onDestroyView();
    }

    private void initViews() {
        Bundle bundle = this.getArguments();
        Data data = bundle.getParcelable("data");
        if(data != null) {
            txtPhotoTitle.setText(data.getTitle());
            txtDescription.setText(data.getDescription());
            String tempUrl = data.getImage();

            if(tempUrl != null && !tempUrl.isEmpty()) {
                Picasso.with(mContext)
                        .load(tempUrl)
                        .placeholder(R.mipmap.ic_launcher)
                        .into(imgPhotoOriginal);
            }
            else{
                Picasso.with(mContext)
                        .load(R.mipmap.ic_launcher)
                        .placeholder(R.mipmap.ic_launcher)
                        .into(imgPhotoOriginal);
            }

        }
    }

    @Override
    public void onError(int resId) {

    }

    @Override
    public void onError(String message) {

    }
}
