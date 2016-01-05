package com.sample.androidwear.notificationsample;

import android.app.Notification;
import android.app.PendingIntent;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat.Action;
import android.support.v4.app.NotificationCompat.BigPictureStyle;
import android.support.v4.app.NotificationCompat.InboxStyle;
import android.support.v4.app.NotificationCompat.WearableExtender;
import android.support.v4.app.NotificationManagerCompat;
import android.support.v4.app.RemoteInput;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.NotificationCompat;
import android.text.TextUtils;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private static final int INTENT_NOTIFICATION_ID = 100;
    private static final int INTENT_MOBILE_ID = 101;
    private static final int INTENT_WEAR_ID = 102;
    private static final int INTENT_VOICE_ID = 103;
    private static final int NOTIFICATION_ID = 1000;
    private static final int NOTIFICATION_SECOND_ID = 1001;
    private static final int NOTIFICATION_SUMMARY_ID = 1002;
    private static final String EXTRA_MESSAGE = "extra.message";

    private NotificationManagerCompat mNotificationManager;

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        onHandleNotificationIntent(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mNotificationManager = NotificationManagerCompat.from(this);

        bindButtonEvents();
        onHandleNotificationIntent(getIntent());
    }

    protected void onBasicButtonClicked() {
        Intent intent = new Intent(this, MainActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, INTENT_NOTIFICATION_ID,
                intent, PendingIntent.FLAG_UPDATE_CURRENT);

        Notification notification = new NotificationCompat.Builder(this)
                .setContentTitle("Notification Title")
                .setContentText("Hello World")
                .setSmallIcon(R.drawable.ic_notification)
                .setDefaults(Notification.DEFAULT_ALL)
                .setContentIntent(pendingIntent)
                .build();

        mNotificationManager.notify(NOTIFICATION_ID, notification);
    }

    protected void onActionButtonClicked() {
        Intent intent = new Intent(this, MainActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, INTENT_NOTIFICATION_ID, intent,
                PendingIntent.FLAG_UPDATE_CURRENT);

        intent = new Intent(this, MainActivity.class);
        intent.putExtra(EXTRA_MESSAGE, "Notification Message");
        PendingIntent phonePendingIntent = PendingIntent.getActivity(this, INTENT_MOBILE_ID, intent,
                PendingIntent.FLAG_UPDATE_CURRENT);

        intent = new Intent(this, MainActivity.class);
        intent.putExtra(EXTRA_MESSAGE, "Wear Action");
        PendingIntent wearPendingIntent = PendingIntent.getActivity(this, INTENT_WEAR_ID, intent,
                PendingIntent.FLAG_UPDATE_CURRENT);

        WearableExtender wearableExtender = new WearableExtender()
                .addAction(new Action.Builder(R.drawable.ic_action_wear, "Open From Wear", wearPendingIntent)
                        .build());

        Notification notification = new NotificationCompat.Builder(this)
                .setContentTitle("Notification With Action")
                .setContentText("This is a sample notification with action")
                .setSmallIcon(R.drawable.ic_notification)
                .setDefaults(Notification.DEFAULT_ALL)
                .setContentIntent(pendingIntent)
                .addAction(R.drawable.ic_action_phone, "Open Action", phonePendingIntent)
                .extend(wearableExtender)
                .build();

        mNotificationManager.notify(NOTIFICATION_ID, notification);
    }

    protected void onBackgroundButtonClicked() {
        Intent intent = new Intent(this, MainActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, INTENT_NOTIFICATION_ID, intent,
                PendingIntent.FLAG_UPDATE_CURRENT);

        Bitmap background = BitmapFactory.decodeResource(getResources(), R.drawable.background_notification);

        WearableExtender wearableExtender = new WearableExtender()
                .setBackground(background);

        Notification notification = new NotificationCompat.Builder(this)
                .setContentTitle("Notification With Background")
                .setContentText("This is a sample notification with background")
                .setSmallIcon(R.drawable.ic_notification)
                .setDefaults(Notification.DEFAULT_ALL)
                .setContentIntent(pendingIntent)
                .extend(wearableExtender)
                .build();

        mNotificationManager.notify(NOTIFICATION_ID, notification);
    }

    protected void onPictureButtonClicked() {
        Intent intent = new Intent(this, MainActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, INTENT_NOTIFICATION_ID, intent,
                PendingIntent.FLAG_UPDATE_CURRENT);

        Bitmap picture = BitmapFactory.decodeResource(getResources(), R.drawable.background_notification);
        String title = "Notification With Picture";
        String content = "This is a sample notification with picture";

        BigPictureStyle bigPictureStyle = new BigPictureStyle()
                .setBigContentTitle(title)
                .bigPicture(picture);

        Notification notification = new NotificationCompat.Builder(this)
                .setContentTitle(title)
                .setContentText(content)
                .setSmallIcon(R.drawable.ic_notification)
                .setDefaults(Notification.DEFAULT_ALL)
                .setContentIntent(pendingIntent)
                .setStyle(bigPictureStyle)
                .build();

        mNotificationManager.notify(NOTIFICATION_ID, notification);
    }

    protected void onVoiceButtonClicked() {
        Intent intent = new Intent(this, MainActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, INTENT_NOTIFICATION_ID, intent,
                PendingIntent.FLAG_UPDATE_CURRENT);

        intent = new Intent(this, MainActivity.class);
        PendingIntent voicePendingIntent = PendingIntent.getActivity(this, INTENT_VOICE_ID, intent,
                PendingIntent.FLAG_UPDATE_CURRENT);

        RemoteInput remoteInput = new RemoteInput.Builder(EXTRA_MESSAGE)
                .setLabel("Voice Message Label")
                .setChoices(new CharSequence[]{
                        "Hi!", "Hello", "World"
                })
                .build();

        WearableExtender wearableExtender = new WearableExtender()
                .addAction(new Action.Builder(R.drawable.ic_action_voice, "Voice Message", voicePendingIntent)
                        .addRemoteInput(remoteInput).build());

        Notification notification = new NotificationCompat.Builder(this)
                .setContentTitle("Notification With Voice Input")
                .setContentText("This is a sample notification with voice input and predefined message")
                .setSmallIcon(R.drawable.ic_notification)
                .setDefaults(Notification.DEFAULT_ALL)
                .setContentIntent(pendingIntent)
                .extend(wearableExtender)
                .build();

        mNotificationManager.notify(NOTIFICATION_ID, notification);
    }

    protected void onPagesButtonClicked() {
        Intent intent = new Intent(this, MainActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, INTENT_NOTIFICATION_ID, intent,
                PendingIntent.FLAG_UPDATE_CURRENT);

        Notification secondPageNotification = new NotificationCompat.Builder(this)
                .setContentTitle("Second Page")
                .setContentText("This is the second page")
                .build();

        WearableExtender wearableExtender = new WearableExtender()
                .addPage(secondPageNotification);

        Notification notification = new NotificationCompat.Builder(this)
                .setContentTitle("Notification With Pages")
                .setContentText("This is a sample notification with pages")
                .setSmallIcon(R.drawable.ic_notification)
                .setDefaults(Notification.DEFAULT_ALL)
                .setContentIntent(pendingIntent)
                .extend(wearableExtender)
                .build();

        mNotificationManager.notify(NOTIFICATION_ID, notification);
    }

    protected void onGroupedButtonClicked() {
        String groupKey = "notif.group_key";

        Intent intent = new Intent(this, MainActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, INTENT_NOTIFICATION_ID, intent,
                PendingIntent.FLAG_UPDATE_CURRENT);

        Notification notification = new NotificationCompat.Builder(this)
                .setContentTitle("Notification With Group")
                .setContentText("This is first notification")
                .setSmallIcon(R.drawable.ic_notification)
                .setDefaults(Notification.DEFAULT_ALL)
                .setContentIntent(pendingIntent)
                .setGroup(groupKey)
                .build();

        Notification secondNotification = new NotificationCompat.Builder(this)
                .setContentTitle("Notification With Group 2")
                .setContentText("This is second notification")
                .setSmallIcon(R.drawable.ic_notification)
                .setDefaults(Notification.DEFAULT_ALL)
                .setContentIntent(pendingIntent)
                .setGroup(groupKey)
                .build();

        Notification summaryNotification = new NotificationCompat.Builder(this)
                .setContentTitle("Grouped Notification")
                .setContentText("Found 2 notification grouped")
                .setSmallIcon(R.drawable.ic_notification)
                .setDefaults(Notification.DEFAULT_ALL)
                .setStyle(new InboxStyle()
                                .addLine("Notification With Group   This is first notification")
                                .addLine("Notification With Group 2   This is second notification")
                                .setBigContentTitle("2 Notification Grouped")
                                .setSummaryText("Group summary")
                )
                .setContentIntent(pendingIntent)
                .setGroup(groupKey)
                .setGroupSummary(true)
                .build();

        mNotificationManager.notify(NOTIFICATION_ID, notification);
        mNotificationManager.notify(NOTIFICATION_SECOND_ID, secondNotification);
        mNotificationManager.notify(NOTIFICATION_SUMMARY_ID, summaryNotification);
    }

    protected void onHandleNotificationIntent(Intent intent) {
        mNotificationManager.cancel(NOTIFICATION_ID);
        mNotificationManager.cancel(NOTIFICATION_SECOND_ID);
        mNotificationManager.cancel(NOTIFICATION_SUMMARY_ID);

        Bundle voiceBundle = RemoteInput.getResultsFromIntent(intent);
        CharSequence message = voiceBundle == null ? null : voiceBundle.getCharSequence(EXTRA_MESSAGE);
        message = TextUtils.isEmpty(message) ? intent.getCharSequenceExtra(EXTRA_MESSAGE) : message;

        if (!TextUtils.isEmpty(message)) {
            Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
        }
    }

    private void bindButtonEvents() {
        findViewById(R.id.button_basic).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBasicButtonClicked();
            }
        });
        findViewById(R.id.button_action).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onActionButtonClicked();
            }
        });
        findViewById(R.id.button_background).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackgroundButtonClicked();
            }
        });
        findViewById(R.id.button_picture).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onPictureButtonClicked();
            }
        });
        findViewById(R.id.button_voice).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onVoiceButtonClicked();
            }
        });
        findViewById(R.id.button_pages).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onPagesButtonClicked();
            }
        });
        findViewById(R.id.button_grouped).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onGroupedButtonClicked();
            }
        });
    }
}
