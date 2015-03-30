package com.example.alex.personaltrainer;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainApp extends Activity implements View.OnClickListener {
    final int[][][] mainTabel = new int[][][]{
            {{3, 3, 2, 2, 3},
                    {4, 4, 3, 3, 4},
                    {6, 5, 5, 4, 5},
                    {6, 6, 5, 4, 6},
                    {6, 6, 5, 5, 6},
                    {7, 6, 5, 5, 6}},
            {{10, 8, 8, 6, 8},
                    {11, 9, 8, 6, 9},
                    {12, 10, 8, 8, 10},
                    {13, 11, 10, 8, 11},
                    {13, 11, 10, 8, 12},
                    {14, 12, 10, 9, 12}},
            {{14, 13, 12, 10, 13},
                    {16, 14, 14, 12, 14},
                    {16, 14, 15, 13, 15},
                    {18, 18, 15, 16, 14},
                    {18, 18, 16, 14, 18},
                    {20, 18, 16, 16, 18}},
            {{20, 18, 16, 14, 18},
                    {22, 20, 18, 16, 20},
                    {24, 22, 20, 18, 22},
                    {26, 24, 20, 22, 22},
                    {26, 24, 20, 22, 22},
                    {26, 24, 24, 22, 24}},
            {{28, 26, 24, 24, 26},
                    {28, 26, 24, 22, 26},
                    {30, 28, 26, 24, 28},
                    {32, 30, 28, 26, 30},
                    {32, 30, 30, 26, 30},
                    {34, 33, 30, 28, 33},
                    {36, 34, 32, 28, 34}},
            {{3, 3, 2, 2, 3},
                    {4, 4, 3, 3, 4},
                    {6, 5, 5, 4, 5},
                    {6, 6, 5, 4, 6},
                    {6, 6, 5, 5, 6},
                    {7, 6, 5, 5, 6}},
            {{3, 3, 2, 2, 3},
                    {4, 4, 3, 3, 4},
                    {6, 5, 5, 4, 5},
                    {6, 6, 5, 4, 6},
                    {6, 6, 5, 5, 6},
                    {7, 6, 5, 5, 6}}
    };
    int numberType;
    int numberLevel;
    int numberDay;
    int numberPodhod;
    TextView[] PodhodTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_app);

        Button buttonRest1 = (Button) findViewById(R.id.buttonRest1);
        buttonRest1.setOnClickListener(this);

        Button buttonRest2 = (Button) findViewById(R.id.buttonRest2);
        buttonRest2.setOnClickListener(this);

        Button buttonRest3 = (Button) findViewById(R.id.buttonRest3);
        buttonRest3.setOnClickListener(this);

        PodhodTextView = new TextView[5];
        PodhodTextView[0] = (TextView) findViewById(R.id.textView2);
        PodhodTextView[1] = (TextView) findViewById(R.id.textView3);
        PodhodTextView[2] = (TextView) findViewById(R.id.textView4);
        PodhodTextView[3] = (TextView) findViewById(R.id.textView5);
        PodhodTextView[4] = (TextView) findViewById(R.id.textView11);

        Intent intent = getIntent();
        numberType = intent.getIntExtra("numberType", 0);
        numberLevel = intent.getIntExtra("numberLevel", 0);
        numberDay = intent.getIntExtra("numberDay", 0);
        numberPodhod = 0;

        TextView numberDayTextView = (TextView) findViewById(R.id.numberDayTextView);
        numberDayTextView.setText("День " + Integer.toString(numberDay + 1));

        TextView numberLevelTextView = (TextView) findViewById(R.id.numberLevelTextView);
        numberLevelTextView.setText("Уровень " + Integer.toString(numberLevel + 1));

        refreshPodhod(numberPodhod);
    }

    private void refreshPodhod(int numberPodhod) {
        int colorText;
        colorText = Color.GREEN;
        for (int i = 0; i <= 4; i++) {
            if (i == numberPodhod) {
                colorText = Color.RED;
            }
            PodhodTextView[i].setText(Integer.toString(mainTabel[numberLevel][numberDay][i] + 1));
            PodhodTextView[i].setTextColor(colorText);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main_app, menu);
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

    @Override
    public void onClick(View v) {
        Intent intent;
        switch (v.getId()) {
            case R.id.buttonRest1:
                intent = new Intent(this, Secundomer.class);
                intent.putExtra("amountSecond", 90);
                startActivityForResult(intent, 1);
                break;
            case R.id.buttonRest2:
                intent = new Intent(this, Secundomer.class);
                intent.putExtra("amountSecond", 120);
                startActivityForResult(intent, 1);
                break;
            case R.id.buttonRest3:
                intent = new Intent(this, Secundomer.class);
                intent.putExtra("amountSecond", 180);
                startActivityForResult(intent, 1);
                break;
            default:
                break;
        }
    }

    @Override

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        numberPodhod++;
        refreshPodhod(numberPodhod);
        if (numberPodhod >= 5) {
            numberDay++;
            save();
        }
    }

    void save() {
        if (numberDay > 5) {
            numberLevel++;
            numberDay = 0;
            Toast.makeText(this, "Поздравляю! Вы перешли на новый уровень", Toast.LENGTH_SHORT).show();
        }
        SharedPreferences saveseting;
        saveseting = getPreferences(MODE_PRIVATE);
        SharedPreferences.Editor data = saveseting.edit();
        data.putString("type" + Integer.toString(numberType), Integer.toString(numberLevel) + ";" + Integer.toString(numberDay));
        data.commit();
    }
}