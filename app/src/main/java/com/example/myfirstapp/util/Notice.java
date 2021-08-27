package com.example.myfirstapp.util;

import android.annotation.TargetApi;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.provider.Settings;
import android.view.View;
import android.widget.Toast;

import com.example.myfirstapp.R;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

import static android.content.Context.NOTIFICATION_SERVICE;

/**
 * 通知消息管理
 */
public class Notice extends AppCompatActivity {
    /**
     * 初始化通知
     */
    private void showNotification() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            String channelId = "chat";
            String channelName = "聊天消息";
            int importance = NotificationManager.IMPORTANCE_HIGH;
            createNotificationChannel(channelId, channelName, importance);

            channelId = "subscribe";
            channelName = "促销消息";
            importance = NotificationManager.IMPORTANCE_DEFAULT;
            createNotificationChannel(channelId, channelName, importance);
        }
    }

    /**
     * 创建通知通道
     *
     * @param channelId
     * @param channelName
     * @param importance
     */
    @TargetApi(Build.VERSION_CODES.O)
    private void createNotificationChannel(String channelId, String channelName, int importance) {
        NotificationChannel channel = new NotificationChannel(channelId, channelName, importance);
        channel.setShowBadge(true);
        NotificationManager notificationManager = (NotificationManager) getSystemService(
                NOTIFICATION_SERVICE);
        notificationManager.createNotificationChannel(channel);
    }

    /**
     * 发送聊天消息
     * @param view
     */
    public void sendChatMsg(View view) {
        NotificationManager manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);

        //此处为防止用户误操作把聊天通道的通知关闭了，从而导致无法接收到消息通知，所以跳到设置界面打开聊天通道通知
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel = manager.getNotificationChannel("chat");
            if (channel.getImportance() == NotificationManager.IMPORTANCE_NONE) {
                Intent intent = new Intent(Settings.ACTION_CHANNEL_NOTIFICATION_SETTINGS);
                intent.putExtra(Settings.EXTRA_APP_PACKAGE, getPackageName());
                intent.putExtra(Settings.EXTRA_CHANNEL_ID, channel.getId());
                startActivity(intent);
//                makeText(this, "请手动将通知打开", Toast.LENGTH_SHORT).show();
            }
        }
        Notification notification = new NotificationCompat.Builder(this, "chat")
                .setContentTitle("收到一条聊天消息")
                .setContentText("今天中午吃什么？")
                .setWhen(System.currentTimeMillis())
                .setSmallIcon(R.mipmap.ic_launcher)
                .setLargeIcon(BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher))
                .setAutoCancel(true)
                .build();
        manager.notify(1, notification);
    }

    /**
     * 发送促销消息
     * @param view
     */
    public void sendSubscribeMsg(View view) {
        NotificationManager manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        Notification notification = new NotificationCompat.Builder(this, "subscribe")
                .setContentTitle("收到一条促销消息")
                .setContentText("温暖冬季，百家超市送温暖，快来点击查看吧！")
                .setWhen(System.currentTimeMillis())
                .setNumber(2)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setLargeIcon(BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher))
                .setAutoCancel(true)
                .build();
        manager.notify(2, notification);
    }


}
