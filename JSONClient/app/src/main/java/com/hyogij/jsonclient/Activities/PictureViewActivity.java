package com.hyogij.jsonclient.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.ImageView;

import com.hyogij.jsonclient.Constants;
import com.hyogij.jsonclient.loader.ImageLoader;
import com.hyogij.jsonclient.R;
import com.hyogij.jsonclient.helper.StringUtils;

/**
 * Created by hyogij on 2015. 12. 13..
 */
public class PictureViewActivity extends Activity {
    private static final String CLASS_NAME = PictureViewActivity.class
            .getCanonicalName();

    public ImageLoader imageLoader = null;
    private String url = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.picture_view_activity);

        Intent intent = getIntent();
        url = intent.getStringExtra(Constants.TAG_URL);

        String id = intent.getStringExtra(Constants.TAG_ID);
        // Change an activity name
        setTitle(StringUtils.getActvityTitle(getString(R.string.picture_view_activity), Constants
                .TAG_ID, id));

        imageLoader = new ImageLoader(getApplicationContext());
        new ProcessFacebookTask().execute(null, null, null);
    }

    // Change an activity name
    private void setActvityTitle(String id) {
        StringBuilder title = new StringBuilder(getString(R.string.picture_view_activity));
        title.append(" : ");
        title.append(Constants.TAG_ID);
        title.append("(");
        title.append(id);
        title.append(")");
        setTitle(title.toString());
    }

    private class ProcessFacebookTask extends AsyncTask<Void, Void, Void> {
        @Override
        protected Void doInBackground(Void... params) {
            try {
                final ImageView image = (ImageView) findViewById(R.id.image);
                runOnUiThread(new Runnable() {
                    public void run() {
                        imageLoader.DisplayImage(url, image);
                    }
                });
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }
    }
}
