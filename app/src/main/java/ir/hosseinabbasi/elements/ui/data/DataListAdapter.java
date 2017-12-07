package ir.hosseinabbasi.elements.ui.data;

import android.content.Context;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import ir.hosseinabbasi.elements.R;
import ir.hosseinabbasi.elements.data.db.model.Data;

/**
 * Created by Dr.jacky on 2017/11/19.
 */

public class DataListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private IDataListView mListener;
    private Context mContext;
    private List<Data> allData = new ArrayList<>();

    public DataListAdapter(Context context, List<Data> dataList, IDataListView listener) {
        mContext = context;
        this.mListener = listener;
        this.allData.clear();
        dataList.remove(0); // Remove Title bar
        this.allData.addAll(dataList);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new DataHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.data_list_item, parent, false));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        Data dataModel = allData.get(position);
        String tempUrl = dataModel.getImage();
        ((DataHolder) holder).render(dataModel);

        if(tempUrl != null && !tempUrl.isEmpty()) {
            Picasso.with(mContext)
                    .load(tempUrl) // Thumbnail URL
                    .placeholder(R.mipmap.ic_launcher)
                    .resize(128, 128) // ToDo Fix this!
                    .into(((DataHolder) holder).thumbnail);
        }
        else{
            Picasso.with(mContext)
                    .load(R.mipmap.ic_launcher)
                    .placeholder(R.mipmap.ic_launcher)
                    .resize(128, 128) // ToDo Fix this!
                    .into(((DataHolder) holder).thumbnail);
        }
    }

    @Override
    public int getItemCount() {
        return allData.size();
    }

    class DataHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        @BindView(R.id.data_item_cnsMain)
        ConstraintLayout mainRow;

        @BindView(R.id.data_list_item_txtTitle)
        TextView title;

        @BindView(R.id.data_list_item_txtDescription)
        TextView description;

        @BindView(R.id.data_list_item_imgThumbnail)
        ImageView thumbnail;

        DataHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }

        void render(Data dataModel) {
            title.setText(dataModel.getTitle());
            description.setText(dataModel.getDescription());
            mainRow.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            Data data = allData.get(this.getLayoutPosition());
            mListener.loadDataDetail(data);
        }
    }

}
