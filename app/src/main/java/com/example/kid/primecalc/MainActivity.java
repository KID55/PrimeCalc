package com.example.kid.primecalc;

import android.content.Context;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;
import java.io.IOException;
import java.util.ArrayList;

public class MainActivity extends ActionBarActivity {

    private ListView myListView;
    ArrayList<String> myArrayList = new ArrayList<String>();
    PrimeCalculator calc = new PrimeCalculator();
    int duration = Toast.LENGTH_LONG;
    EditText aEdit;
    EditText bEdit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        aEdit = (EditText)findViewById(R.id.aField);
        bEdit = (EditText)findViewById(R.id.bField);
        myListView = (ListView)findViewById(R.id.listView);

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



    public void onClick(View view) throws IOException {
        myArrayList.clear();
        if (Integer.parseInt(aEdit.getText().toString()) < 1) {
            Toast toast = Toast.makeText(this,R.string.toast_higher_than_0, duration);
            toast.show();
            aEdit.setText(null);
            aEdit.requestFocus();
        } else if (Integer.parseInt(bEdit.getText().toString()) < 1) {
            Toast toast = Toast.makeText(this,R.string.toast_higher_than_0, duration);
            toast.show();
            bEdit.setText(null);
            bEdit.requestFocus();
        } else if (Integer.parseInt(bEdit.getText().toString()) < Integer.parseInt(aEdit.getText().toString())) {
            Toast toast = Toast.makeText(this,R.string.toast_higher_than_A, Toast.LENGTH_LONG);
            toast.show();
            bEdit.setText(null);
            bEdit.requestFocus();
        } else {
            myArrayList = calc.calc(calc.createList(Integer.parseInt(bEdit.getText().toString())), Integer.parseInt(aEdit.getText().toString()));
        }
        if (myArrayList.size() == 0) {
            Toast toast = Toast.makeText(this,R.string.no_primes, duration);
            toast.show();
            aEdit.setText(null);
            bEdit.setText(null);
            aEdit.requestFocus();
        }
        ArrayAdapter aa = new ArrayAdapter(this,android.R.layout.simple_list_item_1,myArrayList);
        myListView.setAdapter(aa);
        aa.notifyDataSetChanged();

    }


}
