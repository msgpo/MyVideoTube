package com.liskovsoft.leanbackassistant.recommendations;

import android.app.Notification;
import android.app.PendingIntent;
import android.content.Context;
import android.graphics.Bitmap;
import androidx.annotation.RequiresApi;
import androidx.core.app.NotificationCompat;
import androidx.core.content.ContextCompat;
import com.liskovsoft.leanbackassistant.R;

@RequiresApi(21)
public class RecommendationBuilderOld {
    private String mTitle;
    private String mDescription;
    private Context mContext;
    private int mSmallIcon;
    private PendingIntent mIntent;
    private Bitmap mImage;

    public RecommendationBuilderOld setTitle(String title) {
        mTitle = title;
        return this;
    }

    public RecommendationBuilderOld setDescription(String description) {
        mDescription = description;
        return this;
    }

    public RecommendationBuilderOld setImage(Bitmap image) {
        mImage = image;
        return this;
    }

    public RecommendationBuilderOld setContext(Context context) {
        mContext = context;
        return this;
    }

    public RecommendationBuilderOld setSmallIcon(int smallIcon) {
        mSmallIcon = smallIcon;
        return this;
    }

    public RecommendationBuilderOld setIntent(PendingIntent intent) {
        mIntent = intent;
        return this;
    }

    public Notification build() {
        Notification notification = new NotificationCompat.BigPictureStyle(
                new NotificationCompat.Builder(mContext, NotificationCompat.CATEGORY_RECOMMENDATION)
                        .setContentTitle(mTitle)
                        .setContentText(mDescription)
                        .setPriority(NotificationCompat.PRIORITY_MAX)
                        .setLocalOnly(true)
                        .setOngoing(true)
                        .setColor(ContextCompat.getColor(mContext, R.color.fastlane_background))
                        .setCategory(Notification.CATEGORY_RECOMMENDATION)
                        .setLargeIcon(mImage)
                        .setSmallIcon(mSmallIcon)
                        .setContentIntent(mIntent)
                        //.setExtras(mExtras)
                        ).build();

        return notification;
    }
}
