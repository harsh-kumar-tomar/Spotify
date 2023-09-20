package com.example.javaappversion19.Activities;

import androidx.appcompat.app.AppCompatActivity;

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

import com.example.javaappversion19.R;

public class Music_Playing_Activity extends AppCompatActivity {

    private MediaPlayer mediaPlayer;
    private ImageView backButton;

    TextView timeRemaining , totalTime;
    private SeekBar seekBar;
    final Handler handler = new Handler();

    Button pause , startAgain , repeat;
    int flag = 0 , repeatInt = 0;


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



        mediaPlayer = new MediaPlayer();

        try {
            // Set the audio attributes for the media player (optional, but recommended)
            AudioAttributes audioAttributes = new AudioAttributes.Builder()
                    .setContentType(AudioAttributes.CONTENT_TYPE_MUSIC)
                    .setUsage(AudioAttributes.USAGE_MEDIA)
                    .build();
            mediaPlayer.setAudioAttributes(audioAttributes);

            // Set the data source to your song URL
//            callDatabase();
            String songUrl = "https://firebasestorage.googleapis.com/v0/b/music-bf3c4.appspot.com/o/Sun%20Saathiya.mp3?alt=media&token=639ee81b-10d2-4dd9-b73b-eceae75b8634";
            mediaPlayer.setDataSource(songUrl);

            // Prepare the media player
            mediaPlayer.prepare();

            // Start playing the audio
            mediaPlayer.start();
        } catch (Exception e) {
            Log.e("TAG", "Error playing audio: " + e.getMessage());
            e.printStackTrace();
        }


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

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        totalTime.setText(millisecondsToTime(mediaPlayer.getDuration()));
        timeRemaining.setText("");

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

//                    int remainingTimeMillis = mediaPlayer.getDuration() - mediaPlayer.getCurrentPosition();
//                    timeRemaining.setText(millisecondsToTime(remainingTimeMillis));
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

                }
                handler.postDelayed(this, 1000); // Update every 1 second
            }
        }, 1000);


        repeat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (repeatInt == 0)
                {
                    repeat.setBackgroundResource(R.drawable.baseline_repeat_on_24);
                    repeatInt = 1;
                }else {
                    repeat.setBackgroundResource(R.drawable.baseline_repeat_24);
                    repeatInt = 0;
                }
            }
        });


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