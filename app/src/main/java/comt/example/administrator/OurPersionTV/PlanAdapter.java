package comt.example.administrator.OurPersionTV;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Administrator on 2017/10/25 0025.
 */

public class PlanAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    Context context;
    LayoutInflater layoutInflater;
    List<OldManPlan> list;

    public PlanAdapter(Context context, List<OldManPlan> list) {
        this.context = context;
        this.list = list;
        layoutInflater = LayoutInflater.from(context);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = layoutInflater.inflate(R.layout.plan_item, parent, false);
        MyViewHolder viewHolder = new MyViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        MyViewHolder viewHolder = (MyViewHolder) holder;
        viewHolder.plan_item_name.setTag(position);
        OldManPlan oldManPlan = list.get((Integer) viewHolder.plan_item_name.getTag());
        viewHolder.plan_item_name.setText(oldManPlan.getName());
        viewHolder.plan_item_time.setText(oldManPlan.getTime());

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView plan_item_time;
        TextView plan_item_name;

        public MyViewHolder(View itemView) {
            super(itemView);
            plan_item_time = (TextView) itemView.findViewById(R.id.plan_item_time);
            plan_item_name = (TextView) itemView.findViewById(R.id.plan_item_name);
        }
    }
}
