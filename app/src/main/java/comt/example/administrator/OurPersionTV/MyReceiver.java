package comt.example.administrator.OurPersionTV;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.media.AudioAttributes;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;


import org.greenrobot.eventbus.EventBus;
import org.json.JSONException;
import org.json.JSONObject;

import cn.jpush.android.api.JPushInterface;

/**
 * Created by Administrator on 2017/6/21 0021.
 */

public class MyReceiver extends BroadcastReceiver {
    private static final String TAG = "MyReceiver";
    String message;
    String checkidold;


    @Override
    public void onReceive(final Context context, Intent intent) {

        Bundle bundle = intent.getExtras();
        if (JPushInterface.ACTION_REGISTRATION_ID.equals(intent.getAction())) {

        }
        // 自定义消息不会展示在通知栏，完全要开发者写代码去处理
        else if (JPushInterface.ACTION_MESSAGE_RECEIVED.equals(intent.getAction())) {
            message = bundle.getString(JPushInterface.EXTRA_MESSAGE);

        }
        //--通知
        else if (JPushInterface.ACTION_NOTIFICATION_RECEIVED.equals(intent.getAction())) {

            message = bundle.getString(JPushInterface.EXTRA_ALERT);
            /**
             * 附加字段一般都是键值对的形式，所以都是json数据格式
             */
            checkidold = bundle.getString(JPushInterface.EXTRA_EXTRA);
            Log.e("--checkidold-", checkidold);
            if (message != null && checkidold != null) {
                try {
                    JSONObject jsonObject = new JSONObject(checkidold);
                    String oldid = jsonObject.getString("option1");
                    //发布事件
                    EventBus.getDefault().post(new EventBean(oldid));

                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
            // 在这里可以自己写代码去定义用户点击后的行为
        } else if (JPushInterface.ACTION_NOTIFICATION_OPENED.equals(intent.getAction())) {
            //--跳转后去掉提醒
            //--用户点开通知栏的消息后暂时进入老人入住列表界面
            checkidold = bundle.getString(JPushInterface.EXTRA_EXTRA);
//            Intent i = new Intent(context, OldManAdminActivity.class);
//            if (!TextUtils.isEmpty(checkidold) && checkidold != null) {
//                Bundle bundle1 = new Bundle();
//                bundle1.putSerializable("checkidold", checkidold);
//                i.putExtras(bundle1);
//            }
//            i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//            context.startActivity(i);

        } else {
            Log.e(TAG, "Unhandled intent - " + intent.getAction());
        }
    }

}