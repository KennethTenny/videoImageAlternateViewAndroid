package com.example.kennymac.videoplay;

import android.media.MediaPlayer;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.MediaController;
import android.widget.ProgressBar;
import android.widget.VideoView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

public class MainActivity extends AppCompatActivity
{

    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;
    private StorageReference mStorageRef;

    @Override
    public void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(mAuthListener);
    }

    @Override
    public void onStop() {
        super.onStop();
        if (mAuthListener != null) {
            mAuth.removeAuthStateListener(mAuthListener);
        }
    }



    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mAuth = FirebaseAuth.getInstance();

        mStorageRef = FirebaseStorage.getInstance().getReference();

        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null) {
                    // User is signed in
                    Log.d("TAG", "onAuthStateChanged:signed_in:" + user.getUid());
                } else {
                    // User is signed out
                    Log.d("TAG", "onAuthStateChanged:signed_out");
                }

            }
        };
        

        String str="https://firebasestorage.googleapis.com/v0/b/videoplay-e370a.appspot.com/o/Nikon%20D7500-%20Time-lapse%20sample%20video.mp4?alt=media&token=58cdbd51-a96f-481c-80b1-b755928ace58";
        Uri uri = Uri.parse(str);



        ImageView mImageView= (ImageView)findViewById(R.id.ImageView);
        VideoView mVideoView= (VideoView)findViewById(R.id.VideoView);
        final ProgressBar progressBarLandScape=(ProgressBar)findViewById(R.id.progressbar);

        mVideoView.setVideoURI(uri);
        progressBarLandScape.setVisibility(View.VISIBLE);
        mVideoView.requestFocus();
        mVideoView.start();

        mVideoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                mp.setLooping(true);
            }
        });

        mVideoView.setOnInfoListener(new MediaPlayer.OnInfoListener() {
            @Override
            public boolean onInfo(MediaPlayer mp, int what, int extra) {
                if (what == MediaPlayer.MEDIA_INFO_BUFFERING_END) {
                    return true;
                }
                else if(what == MediaPlayer.MEDIA_INFO_BUFFERING_START){
                    return true;
                }
                return false;
            }
        });



        // check your conditions like show video view r image view here


        MediaController mediaController = new MediaController(this);
        mediaController.setAnchorView(mVideoView);
        mVideoView.setMediaController(mediaController);
        //mVideoView.setVideoURI(Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.sample));
        mVideoView.setVideoURI(uri);

        mVideoView.start();

        if(true)
        {
            mVideoView.setVisibility(View.VISIBLE);
            mImageView.setVisibility(View.GONE);
            progressBarLandScape.setVisibility(View.GONE);
        }
        else if(false)
        {
            mImageView.setVisibility(View.VISIBLE);
            mVideoView.setVisibility(View.GONE);
        }

    }

}
