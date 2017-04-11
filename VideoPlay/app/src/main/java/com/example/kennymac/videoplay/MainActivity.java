package com.example.kennymac.videoplay;

import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.MediaController;
import android.widget.VideoView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        String mUrl = "http://www.servername.com/projects/projectname/videos/1361439400.mp4";
//
//        Uri uri = Uri.parse("https://drive.google.com/open?id=0BwpSkSc58LtoLVN1d0RObXU5YW8"); //Declare your url here.
//
//        VideoView mVideoView = (VideoView) findViewById(R.id.videoview);
//        mVideoView.setMediaController(new MediaController(this));
//        mVideoView.setVideoURI(uri);
//        mVideoView.requestFocus();
//        mVideoView.start();

        ImageView mImageView= (ImageView)findViewById(R.id.ImageView);
        VideoView mVideoView= (VideoView)findViewById(R.id.VideoView);

        // check your conditions like show video view r image view here


        MediaController mediaController = new MediaController(this);
        mediaController.setAnchorView(mVideoView);
        mVideoView.setMediaController(mediaController);
        mVideoView.setVideoURI(Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.sample));
        mVideoView.start();

        if(false)
        {
            mVideoView.setVisibility(View.VISIBLE);
            mImageView.setVisibility(View.GONE);
        }
        else if(true)
        {
            mImageView.setVisibility(View.VISIBLE);
            mVideoView.setVisibility(View.GONE);
        }

    }

}
