package com.example.jeffyang.badmintoncornercaller;

import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import java.util.HashMap;

public class Main2Activity extends AppCompatActivity {

    CornerCaller cornerCaller;
    TextView currentCorner, numCornerLeft;
    AsyncTaskRunner asyncTaskRunner = new AsyncTaskRunner(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        currentCorner = (TextView) findViewById(R.id.currentCorner);
        numCornerLeft = (TextView) findViewById(R.id.numCornerLeft);

        // retrieving and setting up the CornerCaller object passed from previous activity
        Intent in = getIntent();
        cornerCaller = in.getParcelableExtra("Caller_object");

        // placeholder text
        currentCorner.setTextSize(50);
        currentCorner.setText("Ready");

        // this does the bulk of the work
        asyncTaskRunner.execute();

    }

    @Override
    public void onStop() {
        super.onStop();
        asyncTaskRunner.cancel(true);
    }



    private class AsyncTaskRunner extends AsyncTask<Void, Integer, String> {

        Context context;
        SoundPool soundPool = new SoundPool(5, AudioManager.STREAM_MUSIC, 0);
        HashMap<Integer, Integer> soundPoolMap = new HashMap<>();


        public AsyncTaskRunner(Context context){
            this.context = context;
        }

        @Override
        protected void onPreExecute(){
            // loading all the audio files to be ready to be played
            soundPoolMap.put(1, soundPool.load(context, R.raw.one, 0));
            soundPoolMap.put(2, soundPool.load(context, R.raw.two, 0));
            soundPoolMap.put(3, soundPool.load(context, R.raw.three, 0));
            soundPoolMap.put(4, soundPool.load(context, R.raw.four, 0));
            soundPoolMap.put(5, soundPool.load(context, R.raw.five, 0));
            soundPoolMap.put(6, soundPool.load(context, R.raw.six, 0));
        }

        @Override
        protected String doInBackground(Void... values) {

            // 3 second delay for user to get ready
            try {
                Thread.sleep(3 * 1000);
            } catch (Exception e){
                e.printStackTrace();
            }

            int corner;

            while (cornerCaller.getNumberOfCalls() != 0){

                if (isCancelled()){
                    break;
                }

                corner = cornerCaller.callCorner();

                // plays the correct audio file based on which corner
                soundPool.play(soundPoolMap.get(corner), 1f, 1f, 100, 0, 1f);

                // displays corner number on screen
                publishProgress(corner);

                cornerCaller.decrementNumberOfCalls();

                // the interval between the corners
                try {
                    Thread.sleep( (long) cornerCaller.getCallInterval() * 1000);
                } catch (Exception e){
                    e.printStackTrace();
                }
            }

            return null;
        }


        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);

            int i = values[0];

            currentCorner.setTextSize(300);
            currentCorner.setText(Integer.toString(i));
            numCornerLeft.setText(Integer.toString(cornerCaller.getNumberOfCalls()));
        }

        @Override
        protected void onPostExecute(String result){

        }
    }


}
