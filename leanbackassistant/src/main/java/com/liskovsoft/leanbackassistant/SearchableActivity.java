package com.liskovsoft.leanbackassistant;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import com.liskovsoft.leanbackassistant.utils.AppUtil;
import com.liskovsoft.myvideotubeapi.Video;
import com.liskovsoft.sharedutils.configparser.AssetPropertyParser2;
import com.liskovsoft.sharedutils.configparser.ConfigParser;
import com.liskovsoft.sharedutils.mylogger.Log;

import static android.support.v4.content.IntentCompat.EXTRA_START_PLAYBACK;

public class SearchableActivity extends Activity {
    private static final String TAG = "SearchableActivity";
    private ConfigParser mParser;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "Search data " + getIntent().getData());

        if (getIntent() != null && getIntent().getData() != null) {
            Uri uri = getIntent().getData();

            if (uri.getLastPathSegment() != null) {
                int id = Integer.valueOf(uri.getLastPathSegment());

                boolean startPlayback = getIntent().getBooleanExtra(EXTRA_START_PLAYBACK, false);
                Log.d(TAG, "Should start playback? " + (startPlayback ? "yes" : "no"));

                String url = obtainVideoUrl(id);

                if (url != null) {
                    Intent intent = new Intent();
                    intent.setData(Uri.parse(url));
                    AppUtil appUtil = new AppUtil(this);
                    intent.setClassName(appUtil.getAppPackageName(), appUtil.getBootstrapClassName());
                    startActivity(intent);
                }
            }
        }

        finish();
    }

    private String obtainVideoUrl(int id) {
        Video video = VideoContentProvider.findVideoWithId(id);

        if (video != null) {
            return video.getVideoUrl();
        }

        return null;
    }
}
