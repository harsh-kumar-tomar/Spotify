package com.example.javaappversion19.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.media.AudioAttributes;
import android.media.Image;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.widget.Toolbar;

import com.bumptech.glide.Glide;
import com.example.javaappversion19.R;

public class Music_Playing_Activity extends AppCompatActivity {

    private MediaPlayer mediaPlayer;
    private ImageView backButton , songImage;

    TextView timeRemaining , totalTime , songName , singer;
    private SeekBar seekBar;
    final Handler handler = new Handler();

    Button pause , startAgain , repeat ,donotDisturb;
    int flag = 0 , repeatInt = 0 , donotDisturbInt = 0 ;
    String url = "" , imgurl = "" , songname="";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_music_playing);

        pause = findViewById(R.id.pause);
        backButton = findViewById(R.id.backButton);
        seekBar = findViewById(R.id.seekBar);
        timeRemaining = findViewById(R.id.timeRemaining);
        totalTime = findViewById(R.id.totalTime);
        startAgain = findViewById(R.id.startAgain);
        repeat = findViewById(R.id.repeat);
        donotDisturb = findViewById(R.id.donotDisturb);
        songImage = findViewById(R.id.songImage);
        songName = findViewById(R.id.songName);
        singer = findViewById(R.id.singer);

        getWindow().setStatusBarColor(ContextCompat.getColor(this, R.color.status_bar_color));


        songName.setText(getIntent().getStringExtra("songname"));
        singer.setText(getIntent().getStringExtra("singername"));


        // Load the image using Glide in a separate thread
        new Thread(new Runnable() {
            @Override
            public void run() {
                // Load the image using Glide
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        imgurl = getIntent().getStringExtra("imgurl");

                        Glide.with(Music_Playing_Activity.this)
                                .load(imgurl)
                                .into(songImage);
                    }
                });
            }
        }).start();



// Create and prepare the MediaPlayer in a separate thread
        mediaPlayer = new MediaPlayer();

        new Thread(new Runnable() {
            @Override
            public void run() {


                try {
                    // Set the audio attributes for the media player (optional, but recommended)
                    url = getIntent().getStringExtra("url");

                    AudioAttributes audioAttributes = new AudioAttributes.Builder()
                            .setContentType(AudioAttributes.CONTENT_TYPE_MUSIC)
                            .setUsage(AudioAttributes.USAGE_MEDIA)
                            .build();
                    mediaPlayer.setAudioAttributes(audioAttributes);
                    // Set the data source to your song URL
                    mediaPlayer.setDataSource(url);
                    // Prepare the media player
                    mediaPlayer.prepare();
                    // Start playing the audio
                    mediaPlayer.start();


                } catch (Exception e) {
                    Log.e("TAG", "Error playing audio: " + e.getMessage());
//                    Toast.makeText(Music_Playing_Activity.this, "Not able to fetch music", Toast.LENGTH_SHORT).show();
                    e.printStackTrace();
                }
            }
        }).start();


        //pause play button set

        pause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(flag==0)
                {
                    mediaPlayer.pause();
                    pause.setBackgroundResource(R.drawable.baseline_play_circle_24);
                    flag=1;
                }else {
                    mediaPlayer.start();
                    pause.setBackgroundResource(R.drawable.baseline_pause_circle_24);
                    flag=0;
                }

            }
        });

        // back button

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.slide_out_right);

            }
        });



        //seek bar
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if (fromUser) {
                    int newPosition = (int) ((float) progress / 100 * mediaPlayer.getDuration());
                    mediaPlayer.seekTo(newPosition);
                    int currentPosition = mediaPlayer.getCurrentPosition();
                    timeRemaining.setText(millisecondsToTime(currentPosition));

                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });


        startAgain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                mediaPlayer.reset();
            }
        });


        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (mediaPlayer != null && mediaPlayer.isPlaying()) {
                    int currentPosition = mediaPlayer.getCurrentPosition();
                    int totalDuration = mediaPlayer.getDuration();
                    int progress = (int) (((float) currentPosition / totalDuration) * 100);


//                    timeRemaining.setText(millisecondsToTime(currentPosition));


                    seekBar.setProgress(progress);
                }
                handler.postDelayed(this, 1000); // Update every 1 second
            }
        }, 1000);

        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (mediaPlayer != null && mediaPlayer.isPlaying()) {
                    int currentPosition = mediaPlayer.getCurrentPosition();
                    timeRemaining.setText(millisecondsToTime(currentPosition));
                    totalTime.setText(millisecondsToTime(mediaPlayer.getDuration()));


                }
                handler.postDelayed(this, 1000); // Update every 1 second
            }
        }, 1000);




        //repeat set
        repeat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (repeatInt == 0)
                {
                    repeat.setBackgroundResource(R.drawable.baseline_repeat_on_24);
                    Toast.makeText(Music_Playing_Activity.this, "Repetition on ", Toast.LENGTH_SHORT).show();
                    repeatInt = 1;
                }else {
                    repeat.setBackgroundResource(R.drawable.baseline_repeat_24);
                    Toast.makeText(Music_Playing_Activity.this, "Repetition off ", Toast.LENGTH_SHORT).show();
                    repeatInt = 0;
                }
            }
        });

        //donot disturb set
        donotDisturb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (donotDisturbInt == 0)
                {
                    donotDisturb.setBackgroundResource(R.drawable.baseline_do_not_disturb_on_total_silence_24);
                    Toast.makeText(Music_Playing_Activity.this, "dnd on ", Toast.LENGTH_SHORT).show();

                    donotDisturbInt = 1;
                }else {
                    donotDisturb.setBackgroundResource(R.drawable.baseline_do_not_disturb_off_24);
                    Toast.makeText(Music_Playing_Activity.this, "dnd off ", Toast.LENGTH_SHORT).show();

                    donotDisturbInt = 0;
                }
            }
        });

        Toolbar toolbar = findViewById(R.id.toolbar);



        songImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toolbar.collapseActionView();
            }
        });




    }

    protected void onPause() {

        super.onPause();
        overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.slide_out_right);

    }

    private String millisecondsToTime(int milliseconds) {
        int seconds = (milliseconds / 1000) % 60;
        int minutes = (milliseconds / (1000 * 60)) % 60;
        int hours = (milliseconds / (1000 * 60 * 60)) % 24;

        if (hours > 0) {
            return String.format("%2d:%2d:%02d", hours, minutes, seconds);
        } else {
            return String.format("%2d:%02d", minutes, seconds);
        }
    }



//    private void callDatabase() {
//        DatabaseReference reference;
//        reference = FirebaseDatabase.getInstance().getReference("Mann Mera").child("path");
//
//
//    }

    protected void onDestroy() {
        super.onDestroy();
        // Release the media player when you're done with it
        if (mediaPlayer != null) {
            mediaPlayer.release();
            mediaPlayer = null;
        }
    }
}