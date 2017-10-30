package comt.example.administrator.OurPersionTV;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by Administrator on 2017/9/28 0028.
 */

public class ViewPagerRvAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    List<OldMan.OldManBean> list;
    Context context;
    LayoutInflater layoutInflater;

    public ViewPagerRvAdapter(Context context, List<OldMan.OldManBean> list) {
        this.context = context;
        this.list = list;
        layoutInflater = LayoutInflater.from(context);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = layoutInflater.inflate(R.layout.viewpagerrv_item, parent, false);
        MyViewHolder viewHolder = new MyViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        MyViewHolder viewHolder = (MyViewHolder) holder;
        viewHolder.viewpagerrv_item_name.setTag(position);
        OldMan.OldManBean oldManBean = list.get((Integer) viewHolder.viewpagerrv_item_name.getTag());
        viewHolder.viewpagerrv_item_name.setText(oldManBean.getCustomerName());
        Picasso.with(context).load(OkHttpURL.ImageURL + oldManBean.getImgPath()).placeholder(R.mipmap.default_image).into(viewHolder.viewpagerrv_item_image);


    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        CircleImageView viewpagerrv_item_image;
        TextView viewpagerrv_item_name;

        public MyViewHolder(View itemView) {
            super(itemView);
            viewpagerrv_item_image = (CircleImageView) itemView.findViewById(R.id.viewpagerrv_item_image);
            viewpagerrv_item_name = (TextView) itemView.findViewById(R.id.viewpagerrv_item_name);
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}
