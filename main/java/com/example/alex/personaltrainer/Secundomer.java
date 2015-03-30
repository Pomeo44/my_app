package com.example.alex.personaltrainer;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.content.Intent;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;


public class Secundomer extends Activity implements View.OnClickListener{
    int mCurrentPeriod;
    TextView digital_clock;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.secundomer);

        Button buttonPls30 = (Button) findViewById(R.id.buttonPls30);
        buttonPls30.setOnClickListener(this);
        Button buttonNextРhase = (Button) findViewById(R.id.buttonNextРhase);
        buttonNextРhase.setOnClickListener(this);

        Intent intent = getIntent();
        mCurrentPeriod = intent.getIntExtra("amountSecond", 0);

        digital_clock = (TextView) findViewById(R.id.tv_digital_clock);

        Timer myTimer = new Timer();
        TimerTask myTimerTask = new TimerTask() {
            @Override
            public void run() {
                TimerMethod();
            }
        };
        myTimer.schedule(myTimerTask, 0, 1000);
    }
    private void TimerMethod() {
        this.runOnUiThread(Timer_Tick);
    }

    private Runnable Timer_Tick = new Runnable() {
        public void run() {
            mCurrentPeriod--;
            String temp = (new SimpleDateFormat("mm:ss")).format(new Date(
                    mCurrentPeriod * 1000));
            digital_clock.setText(temp);

        }
    };
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.buttonPls30:
                mCurrentPeriod = mCurrentPeriod + 30;
                break;
            case R.id.buttonNextРhase:
                Intent intent = new Intent();
                setResult(RESULT_OK, intent);
                finish();
                break;
            default:
                break;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_secundomer, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}