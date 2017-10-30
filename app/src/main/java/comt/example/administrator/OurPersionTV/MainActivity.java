package comt.example.administrator.OurPersionTV;


import android.content.DialogInterface;
import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;
import android.widget.Chronometer;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.squareup.picasso.Picasso;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.TimeZone;
import java.util.concurrent.TimeUnit;

import cn.jpush.android.api.JPushInterface;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;


public class MainActivity extends AppCompatActivity {
    MediaPlayer mediaPlayer;
    RecyclerView plan_rv;
    List<String> titles = new ArrayList<>();
    OldManExaminationData oldManExaminationData;
    List<OldManExaminationData> examinationDataList = new ArrayList<>();
    BJDialog dialog;
    String oldid;
    BJOldMan bjOldMan;
    int index = 0;
    TextView dangqianloucruzhurenshu;
    String str[] = {"正", "二", "三", "四", "五", "六", "七", "八", "九", "十", "冬", "腊"};
    ViewPager benlouoldman_viewpager;
    List<View> viewpagerlist = new ArrayList<>();
    //--这个viewpageradapter暂时是公用的
    ViewpagerAdapter viewpagerAdapter;
    int currentview;
    //--viewpager中的rv
    RecyclerView oldman_viewpager_rv;
    ViewPagerRvAdapter rvAdapter;
    OldMan oldMan;
    //--当前时间
    TextView tv_time;
    //-当前日期
    TextView tv_yearmonthday;
    //--日期转换农历
    TextView tv_nongli;
    //-第二个viewpagerBig
    ViewPager viewpagerbig;
    ViewPagerBigRvAdapter bigRvAdapter;
    RecyclerView viewpagerbig_rv;
    List<View> viewpagerbiglist = new ArrayList<>();
    int bigcurrentview;
    //--切换计划
    int tag = 0;
    boolean zixianc = true;
    Handler handler = new Handler() {

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            //--轮播本楼老人
            if ((int) msg.obj == 1) {
                benlouoldman_viewpager.setCurrentItem(currentview);

            }
            //--实时获取当前时间
            else if ((int) msg.obj == 2) {
                long curtime = System.currentTimeMillis();
                tv_time.setText(DateFormat.format("HH:mm:ss", curtime));
                //--时间为00：00：00时更新日期
                if (DateFormat.format("HH:mm:ss", curtime).equals("00:00:00")) {
                    //--农历日期
                    Setnongli();
                    //--2017-xx-xx
                    Calendar calendar = Calendar.getInstance();
                    int year = calendar.get(Calendar.YEAR);
                    int month = calendar.get(Calendar.MONTH) + 1;
                    int day = calendar.get(Calendar.DAY_OF_MONTH);
                    tv_yearmonthday.setText(year + "年" + month + "月" + day + "日");
                }
            }  //--轮播老人
            else if ((int) msg.obj == 3) {
                viewpagerbig.setCurrentItem(bigcurrentview);
            } else if ((int) msg.obj == 4) {
                Toast.makeText(MainActivity.this, "获取数据失败", Toast.LENGTH_SHORT);

            } else if ((int) msg.obj == 5) {
                dangqianloucruzhurenshu.setText(oldMan.getOldMan().size() + "");
                SmallViewPager();

                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        if (zixianc) {
                            for (int i = 0; i < oldMan.getOldMan().size(); i++) {
                                index = index + 1;
                                gethttp(oldMan.getOldMan().get(i).getId());

                            }
                        }
                    }
                }).start();


            } else if ((int) msg.obj == 6) {
                examinationDataList.add(oldManExaminationData);
                if (examinationDataList.size() == oldMan.getOldMan().size() &&
                        examinationDataList.size() == index) {

                    BigViewPager();

                }

            } else if ((int) msg.obj == 7) {
                dialog = new BJDialog(MainActivity.this, R.layout.bjdialog, R.style.MyDialog);
                dialog.show();
                Chronometer chronometer = (Chronometer) dialog.findViewById(R.id.bjdialog_time);
                chronometer.setBase(SystemClock.elapsedRealtime());//计时器清零
                chronometer.start();
                CircleImageView bj_image = (CircleImageView) dialog.findViewById(R.id.bj_image);
                Picasso.with(MainActivity.this).load(OkHttpURL.ImageURL + bjOldMan.getBJOldMan().getOld().getImgPath()).placeholder(R.mipmap.default_image).into(bj_image);
                TextView age = (TextView) dialog.findViewById(R.id.age);
                if (bjOldMan.getBJOldMan().getOld().getBirthday() != null) {

                    String[] str = bjOldMan.getBJOldMan().getOld().getBirthday().toString().split("-");
                    Calendar calendar = Calendar.getInstance();
                    int year = calendar.get(Calendar.YEAR);
                    int month = calendar.get(Calendar.MONTH) + 1;
                    if (month > Integer.parseInt(str[1])) {
                        age.setText(year - Integer.parseInt(str[0]) + 1 + "");

                    } else {
                        age.setText(year - Integer.parseInt(str[0]) + "");
                    }
                } else {
                    age.setText("56");
                }
                TextView name = (TextView) dialog.findViewById(R.id.name);
                name.setText(bjOldMan.getBJOldMan().getOld().getCustomerName());
                TextView sex = (TextView) dialog.findViewById(R.id.sex);
                if (bjOldMan.getBJOldMan().getOld().getSex().equals("0"))
                    sex.setText("男");
                else if (bjOldMan.getBJOldMan().getOld().getSex().equals("1")) {
                    sex.setText("女");
                }
                TextView bed = (TextView) dialog.findViewById(R.id.bed);
                bed.setText(bjOldMan.getBJOldMan().getPositionName().substring(bjOldMan.getBJOldMan().getPositionName().length() - 5, bjOldMan.getBJOldMan().getPositionName().length()));
                final ImageView dialog_bg = (ImageView) dialog.findViewById(R.id.dialog_bg);
                /** 设置缩放动画 */
                final ScaleAnimation animation = new ScaleAnimation(0.7f, 1.0f, 0.7f, 1.0f,
                        Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
                animation.setDuration(2000);//设置动画持续时间
                /** 常用方法 */
                animation.setRepeatCount(20);//设置重复次数
                animation.setFillAfter(false);//动画执行完后是否停留在执行完的状态
                dialog_bg.setAnimation(animation);
                animation.startNow();

                if (dialog.isShowing()) {
                    mediaPlayer = MediaPlayer.create(MainActivity.this, R.raw.air);
                    mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
                    mediaPlayer.start();


                }
                dialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
                    @Override
                    public void onDismiss(DialogInterface dialog) {
                        mediaPlayer.stop();
                    }
                });
            } else if ((int) msg.obj == 8) {
                if (tag % 2 == 0) {

                    PlanAdapter adapter = new PlanAdapter(MainActivity.this, initplan());
                    plan_rv.setAdapter(adapter);
                } else {
                    PlanAdapter adapter = new PlanAdapter(MainActivity.this, initplantwo());
                    plan_rv.setAdapter(adapter);
                }
            }

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //设置无标题
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        //设置全屏
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);
        JPushInterface.setDebugMode(true);
        JPushInterface.init(this);
        getTitles();
        initview();
        //--沉浸式状态栏
        //当系统版本为4.4或者4.4以上时可以使用沉浸式状态栏
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            //透明状态栏
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            //透明导航栏
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
        }
        if (zixianc) {
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {

                    Intent intent = new Intent(MainActivity.this, MainTwoActivity.class);
                    startActivity(intent);
                    MainActivity.this.finish();
                    overridePendingTransition(R.animator.animation_in, R.animator.animation_out);

                }
            }, 300000);
        }
        //--订阅事件
        EventBus.getDefault().register(this);
    }

    public void initview() {
        plan_rv = (RecyclerView) findViewById(R.id.plan_rv);
        PlanAdapter adapter = new PlanAdapter(MainActivity.this, initplan());
        LinearLayoutManager layoutManager = new LinearLayoutManager(MainActivity.this);
        plan_rv.setLayoutManager(layoutManager);
        plan_rv.setHasFixedSize(true);
        plan_rv.setAdapter(adapter);
        new Thread() {
            @Override
            public void run() {
                super.run();

                do {
                    if (zixianc) {
                        try {
                            tag = tag + 1;
                            Thread.sleep(15000);
                            Message message = new Message();
                            message.obj = 8;
                            handler.sendMessage(message);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    } else {
                        Thread.interrupted();
                    }
                } while (true);

            }
        }.start();
        dangqianloucruzhurenshu = (TextView) findViewById(R.id.dangqianloucruzhurenshu);
        tv_time = (TextView) findViewById(R.id.tv_time);
        long curtime = System.currentTimeMillis();
        tv_time.setText(DateFormat.format("HH:mm:ss", curtime));
        GetCurrentTime();
        tv_yearmonthday = (TextView) findViewById(R.id.tv_yearmonthday);
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH) + 1;
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        tv_yearmonthday.setText(year + "年" + month + "月" + day + "日");
        tv_nongli = (TextView) findViewById(R.id.tv_nongli);
        Setnongli();
        benlouoldman_viewpager = (ViewPager) findViewById(R.id.benlouoldman_viewpager);
        viewpagerbig = (ViewPager) findViewById(R.id.viewpagerbig);
        //获取老人信息
        GetOldManFormHttp();
    }

    public void LunBoViewPager() {
        if (viewpagerlist.size() > 1) {
            new Thread(new Runnable() {
                @Override
                public void run() {

                    do {
                        if (zixianc) {
                            try {
                                Thread.sleep(5000);
                                if (currentview == viewpagerlist.size() - 1) {
                                    currentview = 0;

                                } else {
                                    currentview = currentview + 1;
                                }
                                Message message = new Message();
                                message.obj = 1;
                                handler.sendMessage(message);


                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                    } while (true);


                }
            }).start();
        }

    }

    public void LunBoBigViewPager() {
        if (viewpagerbiglist.size() > 1) {
            new Thread(new Runnable() {
                @Override
                public void run() {

                    do {
                        if (zixianc) {
                            try {
                                Thread.sleep(15000);
                                if (bigcurrentview == viewpagerbiglist.size() - 1) {
                                    bigcurrentview = 0;

                                } else {
                                    bigcurrentview = bigcurrentview + 1;
                                }
                                Message message = new Message();
                                message.obj = 3;
                                handler.sendMessage(message);


                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                    while (true);

                }
            }).start();
        }

    }


    public void GetCurrentTime() {
        new Thread(new Runnable() {
            @Override
            public void run() {

                do {
                    if (zixianc) {
                        try {
                            Thread.sleep(1000);
                            Message message = new Message();
                            message.obj = 2;
                            handler.sendMessage(message);

                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                } while (true);


            }
        }).start();

    }

    //--设置农历日期
    public void Setnongli() {
        Zhuan zhuan = new Zhuan(Calendar.getInstance());
        for (int i = 1; i <= 12; i++) {
            if (zhuan.month == i) {
                String mh = zhuan.month + "";
                mh = str[i - 1];
                tv_nongli.setText("农历 : " + mh + "月" + zhuan.getChinaDayString(zhuan.day));
            }

        }
    }

    public void GetOldManFormHttp() {
        OkHttpClient okHttpClient = new OkHttpClient.Builder().connectTimeout(15, TimeUnit.SECONDS).readTimeout(15, TimeUnit.SECONDS).build();
        Request request = new Request.Builder().get().url("http://119.23.10.131:7006/api/Customer/GetCustomerListByIP?IpAddress=192.168.0.1").build();
        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String result = response.body().string();
                if (result.contains("Message")) {
                    Message message = new Message();
                    message.obj = 4;
                    handler.sendMessage(message);

                } else {
                    String s = "{\"OldMan\":";
                    StringBuffer sb = new StringBuffer(result);
                    sb.insert(0, s);
                    String data = sb.toString() + "}";
                    Gson gson = new Gson();
                    oldMan = gson.fromJson(data, OldMan.class);
                    Message message = new Message();
                    message.obj = 5;
                    handler.sendMessage(message);

                }


            }
        });

    }

    public void SmallViewPager() {

        /**本楼老人首先判断老人总数是否能够被4整除,如果能够整除就第一个
         * 否则最后一页就要判断余数的数量来判断展示几个老人
         *
         */
        if (oldMan.getOldMan().size() % 4 == 0) {
            for (int i = 0; i < oldMan.getOldMan().size() / 4; i++) {
                View view = LayoutInflater.from(MainActivity.this).inflate(R.layout.viewpagerrv_layout, null);
                oldman_viewpager_rv = (RecyclerView) view.findViewById(R.id.oldman_viewpager_rv);
                List<OldMan.OldManBean> list = new ArrayList<>();
                for (int j = 0; j < 4; j++) {
                    list.add(oldMan.getOldMan().get(4 * i + j));
                }

                rvAdapter = new ViewPagerRvAdapter(MainActivity.this, list);
                oldman_viewpager_rv.setHasFixedSize(true);
                LinearLayoutManager manager = new LinearLayoutManager(MainActivity.this, LinearLayoutManager.HORIZONTAL, false) {
                    @Override
                    public boolean canScrollHorizontally() {
                        return false;

                    }
                };
                oldman_viewpager_rv.setLayoutManager(manager);
                oldman_viewpager_rv.setAdapter(rvAdapter);
                viewpagerlist.add(view);
            }
        } else {
            for (int i = 0; i < oldMan.getOldMan().size() / 4 + 1; i++) {
                View view = LayoutInflater.from(MainActivity.this).inflate(R.layout.viewpagerrv_layout, null);
                oldman_viewpager_rv = (RecyclerView) view.findViewById(R.id.oldman_viewpager_rv);
                List<OldMan.OldManBean> list = new ArrayList<>();
                if (i != oldMan.getOldMan().size() / 4) {
                    for (int j = 0; j < 4; j++) {
                        list.add(oldMan.getOldMan().get(4 * i + j));
                    }
                } else {
                    for (int j = 0; j < oldMan.getOldMan().size() % 4; j++) {
                        list.add(oldMan.getOldMan().get(4 * i + j));
                    }

                }
                rvAdapter = new ViewPagerRvAdapter(MainActivity.this, list);
                oldman_viewpager_rv.setHasFixedSize(true);
                LinearLayoutManager manager = new LinearLayoutManager(MainActivity.this, LinearLayoutManager.HORIZONTAL, false) {
                    @Override
                    public boolean canScrollHorizontally() {
                        return false;

                    }
                };
                oldman_viewpager_rv.setLayoutManager(manager);
                oldman_viewpager_rv.setAdapter(rvAdapter);
                viewpagerlist.add(view);
            }

        }
        viewpagerAdapter = new ViewpagerAdapter(viewpagerlist, MainActivity.this);
        benlouoldman_viewpager.setAdapter(viewpagerAdapter);
        //--轮播本楼老人的viewpager
        LunBoViewPager();
    }

    public void BigViewPager() {
        /**本楼详情老人首先判断老人总数是否能够被4整除,如果能够整除就第一个
         * 否则最后一页就要判断余数的数量来判断展示几个老人
         *
         */
        if (oldMan.getOldMan().size() % 6 == 0) {
            for (int i = 0; i < oldMan.getOldMan().size() / 6; i++) {
                View view = LayoutInflater.from(MainActivity.this).inflate(R.layout.viewpagerbig_layout, null);
                viewpagerbig_rv = (RecyclerView) view.findViewById(R.id.viewpagerbig_rv);
                List<OldMan.OldManBean> list = new ArrayList<>();
                for (int j = 0; j < 6; j++) {
                    list.add(oldMan.getOldMan().get(6 * i + j));
                }
                List<OldManExaminationData> examinationDataListTT = new ArrayList<>();
                for (int j = 0; j < 6; j++) {
                    examinationDataListTT.add(examinationDataList.get(6 * i + j));
                }
                bigRvAdapter = new ViewPagerBigRvAdapter(MainActivity.this, list, examinationDataListTT);
                viewpagerbig_rv.setHasFixedSize(true);
                GridLayoutManager manager = new GridLayoutManager(MainActivity.this, 3);
                viewpagerbig_rv.setLayoutManager(manager);
                viewpagerbig_rv.setAdapter(bigRvAdapter);

                viewpagerbiglist.add(view);
            }
            viewpagerAdapter = new ViewpagerAdapter(viewpagerbiglist, MainActivity.this);
            viewpagerbig.setAdapter(viewpagerAdapter);

        } else

        {
            for (int i = 0; i < oldMan.getOldMan().size() / 6 + 1; i++) {
                View view = LayoutInflater.from(MainActivity.this).inflate(R.layout.viewpagerbig_layout, null);
                viewpagerbig_rv = (RecyclerView) view.findViewById(R.id.viewpagerbig_rv);
                List<OldMan.OldManBean> list = new ArrayList<>();
                if (i != oldMan.getOldMan().size() / 6) {
                    for (int j = 0; j < 6; j++) {
                        list.add(oldMan.getOldMan().get(6 * i + j));
                    }
                } else {
                    for (int j = 0; j < oldMan.getOldMan().size() % 6; j++) {
                        list.add(oldMan.getOldMan().get(6 * i + j));
                    }

                }
                List<OldManExaminationData> examinationDataListTT = new ArrayList<>();
                if (i != oldMan.getOldMan().size() / 6) {
                    for (int j = 0; j < 6; j++) {
                        examinationDataListTT.add(examinationDataList.get(6 * i + j));
                    }
                } else {
                    for (int j = 0; j < oldMan.getOldMan().size() % 6; j++) {
                        examinationDataListTT.add(examinationDataList.get(6 * i + j));
                    }

                }
                bigRvAdapter = new ViewPagerBigRvAdapter(MainActivity.this, list, examinationDataListTT);
                viewpagerbig_rv.setHasFixedSize(true);
                GridLayoutManager manager = new GridLayoutManager(MainActivity.this, 3);
                viewpagerbig_rv.setLayoutManager(manager);
                viewpagerbig_rv.setAdapter(bigRvAdapter);

                viewpagerbiglist.add(view);
            }
            viewpagerAdapter = new ViewpagerAdapter(viewpagerbiglist, MainActivity.this);
            viewpagerbig.setAdapter(viewpagerAdapter);

        }
        LunBoBigViewPager();
    }

    public void gethttp(String id) {
        OkHttpClient okHttpClient = new OkHttpClient.Builder().connectTimeout(15, TimeUnit.SECONDS).readTimeout(15, TimeUnit.SECONDS).build();
        final Request request = new Request.Builder().get().url("http://119.23.10.131:7006/api/Customer/GetOldPersonSignRecordByOldPersonId?oldPersonId=" + id + "&beginTime=" + titles.get(titles.size() - 1) + "&endTime=" + titles.get(0) + "").build();
        Call call = okHttpClient.newCall(request);
        try {
            Response response = call.execute();
            if (response.code() == 200) {
                String result = response.body().string();
                String s = "{\"OldManExaminationData\":";
                StringBuffer sb = new StringBuffer(result);
                sb.insert(0, s);
                String data = sb.toString() + "}";
                Gson gson = new Gson();
                oldManExaminationData = gson.fromJson(data, OldManExaminationData.class);
                Message message = new Message();
                message.obj = 6;
                handler.sendMessage(message);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<String> getTitles() {
        String mYear; // 当前年
        String mMonth; // 月
        String mDay;
        int current_day;
        int current_month;
        int current_year;

        final Calendar c = Calendar.getInstance();
        c.setTimeZone(TimeZone.getTimeZone("GMT+8:00"));
        current_day = c.get(Calendar.DAY_OF_MONTH);
        current_month = c.get(Calendar.MONTH);
        current_year = c.get(Calendar.YEAR);
        for (int i = 0; i < 7; i++) {
            c.clear();//记住一定要clear一次
            c.set(Calendar.MONTH, current_month);
            c.set(Calendar.DAY_OF_MONTH, current_day);
            c.set(Calendar.YEAR, current_year);
            c.add(Calendar.DATE, -i);//j记住是DATE
            mMonth = String.valueOf(c.get(Calendar.MONTH) + 1);// 获取当前月份
            mDay = String.valueOf(c.get(Calendar.DAY_OF_MONTH));// 获取当前日份的日期号码
            mYear = String.valueOf(c.get(Calendar.YEAR));// 获取当前年份
            String date = mYear + "-" + mMonth + "-" + mDay;
            titles.add(date);
        }
        return titles;
    }

    //--接收到的消息
    @Subscribe
    public void onEventMainThread(EventBean event) {
        if (!MainActivity.this.isDestroyed()) {
            if (!event.getMessage().equals("")) {
                oldid = event.getMessage();
                gethttp();
            }
        } else {
            return;

        }


    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        zixianc = false;
        if (dialog != null&&dialog.isShowing()) {
            dialog.dismiss();
        }
        Thread.interrupted();
        ////取消订阅
        EventBus.getDefault().unregister(this);
    }

    public void gethttp() {
        OkHttpClient okHttpClient = new OkHttpClient.Builder().connectTimeout(15, TimeUnit.SECONDS).readTimeout(15, TimeUnit.SECONDS).build();
        Request request = new Request.Builder().get().url("http://119.23.10.131:7006/api/Customer/GetCheckinByOldId?OldId=" + oldid).build();
        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String result = response.body().string();
                String s = "{\"BJOldMan\":";
                StringBuffer sb = new StringBuffer(result);
                sb.insert(0, s);
                String data = sb.toString() + "}";
                Gson gson = new Gson();
                bjOldMan = gson.fromJson(data, BJOldMan.class);
                Message message = new Message();
                message.obj = 7;
                handler.sendMessage(message);


            }
        });

    }

    public List<OldManPlan> initplan() {
        List<OldManPlan> list = new ArrayList<>();

        OldManPlan oldManPlan = new OldManPlan();
        oldManPlan.setName("洗澡，按摩");
        oldManPlan.setTime("14:00-16:00");
        OldManPlan oldManPlan2 = new OldManPlan();
        oldManPlan2.setName("入厕");
        oldManPlan2.setTime("17:00-17:30");
        OldManPlan oldManPlan3 = new OldManPlan();
        oldManPlan3.setName("晚饭");
        oldManPlan3.setTime("18:00-18:40");
        OldManPlan oldManPlan4 = new OldManPlan();
        oldManPlan4.setName("洗漱");
        oldManPlan4.setTime("20:00-20:40");
        list.add(oldManPlan);
        list.add(oldManPlan2);
        list.add(oldManPlan3);
        list.add(oldManPlan4);

        return list;
    }

    public List<OldManPlan> initplantwo() {
        List<OldManPlan> list = new ArrayList<>();

        OldManPlan oldManPlan = new OldManPlan();
        oldManPlan.setName("起床，早餐");
        oldManPlan.setTime("08:00-08:40");
        OldManPlan oldManPlan2 = new OldManPlan();
        oldManPlan2.setName("入厕");
        oldManPlan2.setTime("09:00-09:30");
        OldManPlan oldManPlan3 = new OldManPlan();
        oldManPlan3.setName("补水");
        oldManPlan3.setTime("10:00-10:30");
        OldManPlan oldManPlan4 = new OldManPlan();
        oldManPlan4.setName("午餐");
        oldManPlan4.setTime("12:00-13:00");
        list.add(oldManPlan);
        list.add(oldManPlan2);
        list.add(oldManPlan3);
        list.add(oldManPlan4);

        return list;
    }
}
