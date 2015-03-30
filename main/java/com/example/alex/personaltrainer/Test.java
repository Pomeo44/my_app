package com.example.alex.personaltrainer;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;


public class Test extends Activity implements View.OnClickListener {
    final static int[][] etapTabel = new int[][]{
            {6,13,20,28,36,45,55,65,80,99},
            {4,4,3,3,4},
            {6,5,5,4,5},
            {6,6,5,4,6},
            {6,6,5,5,6},
            {7,6,5,5,6}};
    EditText amountEditText;
    int numberType;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.test);

        Intent intent = getIntent();
        numberType = intent.getIntExtra("numberType", 0);

        Button buttonGo = (Button) findViewById(R.id.buttonGoApp);
        buttonGo.setOnClickListener(this);

        amountEditText = (EditText) findViewById(R.id.editText);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_test, menu);
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
        switch (v.getId()) {
            case R.id.buttonGoApp:
                int amount = Integer.parseInt(amountEditText.getText().toString());
                if (amount >= 0){
                    int i;
                    for (i=0;i<=10;i++){
                        if (amount < etapTabel[numberType][i]){
                            break;
                        }
                    }
                    Intent intent;
                    intent = new Intent(this, MainApp.class);
                    intent.putExtra("numberType", numberType);
                    intent.putExtra("numberDay", 0);
                    intent.putExtra("numberLevel", i);
                    startActivity(intent);
                }

                break;
            default:
                break;
        }
    }
}
