package com.example.alex.personaltrainer;

import android.app.Activity;
import android.content.Intent;
//import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import static android.view.View.OnClickListener;


public class MainActivity extends Activity implements OnClickListener {
    int numberType = 0;
    int numberLevel;
    int numberDay;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button buttonGo = (Button) findViewById(R.id.buttonGo);
        buttonGo.setOnClickListener(this);
        Button buttonGoTest = (Button) findViewById(R.id.buttonGoTest);
        buttonGoTest.setOnClickListener(this);

    }

    public void onClick(View btn) {
        Intent intent;
        switch (btn.getId()) {
            case R.id.buttonGoTest:
                intent = new Intent(this, Test.class);
                intent.putExtra("numberType", numberType);
                startActivity(intent);
                break;
            case R.id.buttonGo:
                loadSetting();
                intent = new Intent(this, MainApp.class);
                intent.putExtra("numberType", numberType);
                intent.putExtra("numberLevel", numberLevel);
                intent.putExtra("numberDay", numberDay);
                startActivity(intent);
            default:
                break;
        }
    }
    void loadSetting() {
        //SharedPreferences loadsetting;
        //loadsetting = getPreferences(MODE_PRIVATE);
        //String loadText = loadsetting.getString("type" + Integer.toString(numberType), "");
        String loadText = "2;1";
        //numberDay = 1;
        //numberLevel = 1;
        getSettingString(loadText);
    }
    void getSettingString(String stroka){
        int find;
        find = stroka.indexOf(";");
        numberLevel = Integer.parseInt(stroka.substring(0,find));
        numberDay = Integer.parseInt(stroka.substring(find+1));
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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