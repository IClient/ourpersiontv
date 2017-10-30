package comt.example.administrator.OurPersionTV;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.os.Message;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;


import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.LimitLine;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.google.gson.Gson;
import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.TimeZone;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by Administrator on 2017/9/28 0028.
 */

public class ViewPagerBigRvAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    MyViewHolder viewHolder;
    List<OldMan.OldManBean> list;
    Context context;
    LayoutInflater layoutInflater;
    List<OldManExaminationData> examinationDataListTT;

    public ViewPagerBigRvAdapter(Context context, final List<OldMan.OldManBean> list, List<OldManExaminationData> examinationDataListTT) {
        this.context = context;
        this.list = list;
        layoutInflater = LayoutInflater.from(context);
        this.examinationDataListTT = examinationDataListTT;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = layoutInflater.inflate(R.layout.viewpagerbigrv_item, parent, false);
        MyViewHolder viewHolder = new MyViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        viewHolder = (MyViewHolder) holder;
//        viewHolder.line.clear();
        viewHolder.bigrv_item_name.setTag(position);
        OldMan.OldManBean oldMan = list.get((Integer) viewHolder.bigrv_item_name.getTag());
        viewHolder.bigrv_item_name.setText(oldMan.getCustomerName());
        viewHolder.bigrv_item_age.setText("110");
        if (oldMan.getSex().equals("0")) {
            viewHolder.bigrv_item_sex.setText("男");
        } else {
            viewHolder.bigrv_item_sex.setText("女");

        }
        Drawable drawable = context.getResources().getDrawable(R.mipmap.nonormal);
        drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
        viewHolder.bigrv_item_p.setCompoundDrawables(null, drawable, null, null);
        //       2017-10-16
        if (oldMan.getBirthday() != null) {
            String str[] = oldMan.getBirthday().toString().split("-");
            Calendar calendar = Calendar.getInstance();
            int year = calendar.get(Calendar.YEAR);
            int month = calendar.get(Calendar.MONTH) + 1;
            if (month > Integer.parseInt(str[1])) {

                viewHolder.bigrv_item_age.setText((year - Integer.parseInt(str[0])) + "");
            } else {
                viewHolder.bigrv_item_age.setText((year - Integer.parseInt(str[0])) - 1 + "");

            }
        } else {
            viewHolder.bigrv_item_age.setText("55");

        }
        viewHolder.bigrv_item_number.setText("A10" + position);
        viewHolder.bigrv_item_wcd.setText("20%");
        viewHolder.bigrv_item_bj.setText((position + 1) + "次");
        /**
         * 体征数据
         */
        viewHolder.line.setVisibility(View.VISIBLE);

        Picasso.with(context).load(OkHttpURL.ImageURL + oldMan.getImgPath()).placeholder(R.mipmap.default_image).into(viewHolder.bigrv_image);
        XAxis xAxis = viewHolder.line.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setDrawGridLines(false);//设置x轴上每个点对应的线
        xAxis.setAvoidFirstLastClipping(true);
        // y轴的数据
        ArrayList<Entry> yValues = new ArrayList<>();
        for (int i = 0; i < examinationDataListTT.get((Integer) viewHolder.bigrv_item_name.getTag()).getOldManExaminationData().size(); i++) {
            yValues.add(new Entry(examinationDataListTT.get((Integer) viewHolder.bigrv_item_name.getTag()).getOldManExaminationData().get(i).getDiastolicPressure(), i));
        }
//        setlimite(viewHolder);

        LineDataSet lineDataSet = new LineDataSet(yValues, "舒张压");
        //--设置曲线上字体大小
        lineDataSet.setValueTextSize(10.f);
        //--设置圆圈大小
        lineDataSet.setCircleSize(2.5f);
        //设置曲线值的圆点是实心还是空心
        lineDataSet.setCircleColor(Color.parseColor("#ec3733"));
        lineDataSet.setDrawCircleHole(false);
        //---曲线颜色
        lineDataSet.setColor(Color.parseColor("#ec3733"));
        //--曲线字体颜色
        lineDataSet.setValueTextColor(Color.parseColor("#fafafa"));
        //--设置曲线上不显示数据
        lineDataSet.setDrawValues(false);
        //模拟一个x轴的数据
        ArrayList<String> xValues = new ArrayList<>();
        for (int i = 0; i < examinationDataListTT.get((Integer) viewHolder.bigrv_item_name.getTag()).getOldManExaminationData().size(); i++) {
//            xValues.add("第" + (i + 1) + "次");
            xValues.add(examinationDataListTT.get((Integer) viewHolder.bigrv_item_name.getTag()).getOldManExaminationData().get(i).getTestTime().substring(0, 10));
        }
        //------------------第二条线---------------
        // y轴的数据
        ArrayList<Entry> yValuestwo = new ArrayList<>();
        for (int i = 0; i < examinationDataListTT.get((Integer) viewHolder.bigrv_item_name.getTag()).getOldManExaminationData().size(); i++) {

            yValuestwo.add(new Entry(examinationDataListTT.get((Integer) viewHolder.bigrv_item_name.getTag()).getOldManExaminationData().get(i).getSystolicPressure(), i));
        }
        LineDataSet lineDataSettwo = new LineDataSet(yValuestwo, "收缩压");
//        http://blog.csdn.net/dt235201314/article/details/52222088
        //--设置曲线上字体大小
        lineDataSettwo.setValueTextSize(10.f);
        //--设置圆圈大小
        lineDataSettwo.setCircleSize(2.5f);
        //--设置圆圈颜色
        lineDataSettwo.setCircleColor(Color.parseColor("#80cdce"));
        //设置曲线值的圆点是实心还是空心
        lineDataSettwo.setDrawCircleHole(false);
        //---曲线颜色
        lineDataSettwo.setColor(Color.parseColor("#80cdce"));
        //--曲线字体颜色
        lineDataSettwo.setValueTextColor(Color.parseColor("#cccccc"));
        lineDataSettwo.setDrawValues(false);
        //模拟一个x轴的数据
        ArrayList<String> xValuestwo = new ArrayList<>();
        for (int i = 0; i < examinationDataListTT.get((Integer) viewHolder.bigrv_item_name.getTag()).getOldManExaminationData().size(); i++) {
            xValuestwo.add(examinationDataListTT.get((Integer) viewHolder.bigrv_item_name.getTag()).getOldManExaminationData().get(i).getTestTime().replaceAll("T", " "));
        }
        //--一个LineDataSet一条线
        ArrayList<LineDataSet> dataSets = new ArrayList<>();
        //将数据加入dataSets
        dataSets.add(lineDataSet);
        dataSets.add(lineDataSettwo);
        LineData lineData = new LineData(xValues, dataSets);
        viewHolder.line.setData(lineData);
//        viewHolder.line.setDescription(5 + "次测试报表(单位：ML)");
        viewHolder.line.setDescriptionColor(Color.parseColor("#cccccc"));
        viewHolder.line.animateX(1500);
        viewHolder.line.animateY(1500);
        viewHolder.line.setTouchEnabled(true); // 设置是否可以触摸
        viewHolder.line.setDragEnabled(true);// 是否可以拖拽
        viewHolder.line.setScaleEnabled(true);// 是否可以缩放
        viewHolder.line.getAxisRight().setEnabled(false); // 隐藏右边 的坐标轴
        viewHolder.line.setDrawBorders(false);
        viewHolder.line.setGridBackgroundColor(Color.parseColor("#243756"));
        viewHolder.line.setDescription("");
//        initline(viewHolder, examinationDataListTT.get((Integer) viewHolder.bigrv_item_name.getTag()));

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        LineChart line;
        TextView bigrv_item_name;
        TextView bigrv_item_sex;
        TextView bigrv_item_age;
        TextView bigrv_item_p;
        TextView bigrv_item_number;
        TextView bigrv_item_wcd;
        TextView bigrv_item_bj;
        CircleImageView bigrv_image;

        public MyViewHolder(View itemView) {
            super(itemView);
            line = (LineChart) itemView.findViewById(R.id.line);
            bigrv_item_name = (TextView) itemView.findViewById(R.id.bigrv_item_name);
            bigrv_item_sex = (TextView) itemView.findViewById(R.id.bigrv_item_sex);
            bigrv_item_age = (TextView) itemView.findViewById(R.id.bigrv_item_age);
            bigrv_item_p = (TextView) itemView.findViewById(R.id.bigrv_item_p);
            bigrv_item_number = (TextView) itemView.findViewById(R.id.bigrv_item_number);
            bigrv_item_wcd = (TextView) itemView.findViewById(R.id.bigrv_item_wcd);
            bigrv_item_bj = (TextView) itemView.findViewById(R.id.bigrv_item_bj);
            bigrv_image = (CircleImageView) itemView.findViewById(R.id.bigrv_image);
        }
    }

//    public void initline(MyViewHolder viewHolder, OldManExaminationData oldManExaminationData) {
//
//        XAxis xAxis = viewHolder.line.getXAxis();
//        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
//        xAxis.setDrawGridLines(false);//设置x轴上每个点对应的线
//        xAxis.setAvoidFirstLastClipping(true);
//        // y轴的数据
//        ArrayList<Entry> yValues = new ArrayList<>();
//        for (int i = 0; i < oldManExaminationData.getOldManExaminationData().size(); i++) {
//            yValues.add(new Entry(oldManExaminationData.getOldManExaminationData().get(i).getDiastolicPressure(), i));
//        }
////        setlimite(viewHolder);
//
//        LineDataSet lineDataSet = new LineDataSet(yValues, "舒张压");
//        //--设置曲线上字体大小
//        lineDataSet.setValueTextSize(10.f);
//        //--设置圆圈大小
//        lineDataSet.setCircleSize(2.5f);
//        //设置曲线值的圆点是实心还是空心
//        lineDataSet.setCircleColor(Color.parseColor("#ec3733"));
//        lineDataSet.setDrawCircleHole(false);
//        //---曲线颜色
//        lineDataSet.setColor(Color.parseColor("#ec3733"));
//        //--曲线字体颜色
//        lineDataSet.setValueTextColor(Color.parseColor("#fafafa"));
//        //--设置曲线上不显示数据
//        lineDataSet.setDrawValues(false);
//        //模拟一个x轴的数据
//        ArrayList<String> xValues = new ArrayList<>();
//        for (int i = 0; i < oldManExaminationData.getOldManExaminationData().size(); i++) {
////            xValues.add("第" + (i + 1) + "次");
//            xValues.add(oldManExaminationData.getOldManExaminationData().get(i).getTestTime().substring(0, 10));
//        }
//        //------------------第二条线---------------
//        // y轴的数据
//        ArrayList<Entry> yValuestwo = new ArrayList<>();
//        for (int i = 0; i < oldManExaminationData.getOldManExaminationData().size(); i++) {
//
//            yValuestwo.add(new Entry(oldManExaminationData.getOldManExaminationData().get(i).getSystolicPressure(), i));
//        }
//        LineDataSet lineDataSettwo = new LineDataSet(yValuestwo, "收缩压");
////        http://blog.csdn.net/dt235201314/article/details/52222088
//        //--设置曲线上字体大小
//        lineDataSettwo.setValueTextSize(10.f);
//        //--设置圆圈大小
//        lineDataSettwo.setCircleSize(2.5f);
//        //--设置圆圈颜色
//        lineDataSettwo.setCircleColor(Color.parseColor("#80cdce"));
//        //设置曲线值的圆点是实心还是空心
//        lineDataSettwo.setDrawCircleHole(false);
//        //---曲线颜色
//        lineDataSettwo.setColor(Color.parseColor("#80cdce"));
//        //--曲线字体颜色
//        lineDataSettwo.setValueTextColor(Color.parseColor("#cccccc"));
//        lineDataSettwo.setDrawValues(false);
//        //模拟一个x轴的数据
//        ArrayList<String> xValuestwo = new ArrayList<>();
//        for (int i = 0; i < oldManExaminationData.getOldManExaminationData().size(); i++) {
//            xValuestwo.add(oldManExaminationData.getOldManExaminationData().get(i).getTestTime().replaceAll("T", " "));
//        }
//        //--一个LineDataSet一条线
//        ArrayList<LineDataSet> dataSets = new ArrayList<>();
//        //将数据加入dataSets
//        dataSets.add(lineDataSet);
//        dataSets.add(lineDataSettwo);
//        LineData lineData = new LineData(xValues, dataSets);
//        viewHolder.line.setData(lineData);
////        viewHolder.line.setDescription(5 + "次测试报表(单位：ML)");
//        viewHolder.line.setDescriptionColor(Color.parseColor("#cccccc"));
//        viewHolder.line.animateX(1500);
//        viewHolder.line.animateY(1500);
//        viewHolder.line.setTouchEnabled(true); // 设置是否可以触摸
//        viewHolder.line.setDragEnabled(true);// 是否可以拖拽
//        viewHolder.line.setScaleEnabled(true);// 是否可以缩放
//        viewHolder.line.getAxisRight().setEnabled(false); // 隐藏右边 的坐标轴
//        viewHolder.line.setDrawBorders(false);
//        viewHolder.line.setGridBackgroundColor(Color.parseColor("#243756"));
//        viewHolder.line.setDescription("");
//
//    }

//    public void setlimite(MyViewHolder viewHolder) {
//        // 设置x轴的LimitLine
//        LimitLine yLimitLine = new LimitLine(50, "正常最大值");
//        yLimitLine.setLineColor(Color.RED);
//        yLimitLine.setTextColor(Color.RED);
//        // 获得左侧侧坐标轴
//        YAxis leftAxis = viewHolder.line.getAxisLeft();
//        leftAxis.addLimitLine(yLimitLine);
//        // 设置x轴的LimitLine
//        LimitLine yLimitLinetwo = new LimitLine(10, "正常最小值");
//        yLimitLinetwo.setLineColor(Color.RED);
//        yLimitLinetwo.setTextColor(Color.RED);
//        // 获得左侧侧坐标轴
//        YAxis leftAxistwo = viewHolder.line.getAxisLeft();
//        leftAxistwo.addLimitLine(yLimitLinetwo);
//
//    }


}
