package comt.example.administrator.OurPersionTV;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.squareup.picasso.Picasso;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.io.IOException;
import java.util.Calendar;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;


/**
 * Created by Administrator on 2017/10/10 0010.
 */

public class MainTwoActivity extends Activity {
    String str[] = {"正", "二", "三", "四", "五", "六", "七", "八", "九", "十", "冬", "腊"};
    //--当前时间
    TextView tvtwo_time;
    //-当前日期
    TextView tvtwo_yearmonthday;
    //--日期转换农历
    TextView tvtwo_nongli;
    boolean zixianc = true;
    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if ((int) msg.obj == 1) {
                long curtime = System.currentTimeMillis();
                tvtwo_time.setText(DateFormat.format("HH:mm:ss", curtime));
                //--时间为00：00：00时更新日期
                if (DateFormat.format("HH:mm:ss", curtime).equals("00:00:00")) {
                    //--农历日期
                    Setnongli();
                    //--2017-xx-xx
                    Calendar calendar = Calendar.getInstance();
                    int year = calendar.get(Calendar.YEAR);
                    int month = calendar.get(Calendar.MONTH) + 1;
                    int day = calendar.get(Calendar.DAY_OF_MONTH);
                    tvtwo_yearmonthday.setText(year + "年" + month + "月" + day + "日");
                }

            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maintwo);
        initview();
        if (zixianc) {
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    Intent intent = new Intent(MainTwoActivity.this, MainThreeActivity.class);
                    MainTwoActivity.this.finish();
                    startActivity(intent);
                    overridePendingTransition(R.animator.animation_in, R.animator.animation_out);
                }
            }, 30000);
        }
        getcurrenttime();

    }

    public void getcurrenttime() {
        new Thread(new Runnable() {
            @Override
            public void run() {

                do {
                    if (zixianc) {
                        try {
                            Thread.sleep(1000);
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

    public void initview() {
        tvtwo_time = (TextView) findViewById(R.id.tvtwo_time);
        tvtwo_yearmonthday = (TextView) findViewById(R.id.tvtwo_yearmonthday);
        tvtwo_nongli = (TextView) findViewById(R.id.tvtwo_nongli);
        long curtime = System.currentTimeMillis();
        tvtwo_time.setText(DateFormat.format("HH:mm:ss", curtime));
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH) + 1;
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        tvtwo_yearmonthday.setText(year + "年" + month + "月" + day + "日");
        Setnongli();
    } //--设置农历日期

    public void Setnongli() {
        Zhuan zhuan = new Zhuan(Calendar.getInstance());
        for (int i = 1; i <= 12; i++) {
            if (zhuan.month == i) {
                String mh = zhuan.month + "";
                mh = str[i - 1];
                tvtwo_nongli.setText("农历 : " + mh + "月" + zhuan.getChinaDayString(zhuan.day));
            }

        }
    }




    @Override
    protected void onDestroy() {
        super.onDestroy();

        zixianc = false;
    }
}
