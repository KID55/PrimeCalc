package com.example.kid.primecalc;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Gravity;
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

    private ListView myListView; // Список найденных простых чисел
    private ListView mySuperListView; // Список найденных массивов
    ArrayList<String> myArrayList = new ArrayList<>(); // Коллекция найденных простых чисел
    ArrayList<String> mySuperArrayList = new ArrayList<>(); // Коллекция найденных массивов
    PrimeCalculator calc = new PrimeCalculator(); // Объект PrimeCalculator
    public static int duration = Toast.LENGTH_LONG; // Время отображения тост-уведомления
    EditText aEdit; // Объект, который будет брать значение А из поля ввода
    EditText bEdit; // Объект, который будет брать значение В из поля ввода
    EditText pEdit; // Объект, который будет брать значение Р из поля ввода

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        aEdit = (EditText)findViewById(R.id.aField); // Связываем
        bEdit = (EditText)findViewById(R.id.bField); // объекты
        pEdit = (EditText)findViewById(R.id.pField); // и поля ввода
        myListView = (ListView)findViewById(R.id.listView); // а также списки
        mySuperListView = (ListView)findViewById(R.id.listView2); //с соответствующими элементами на активности
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


    /**
     * Обработка клика на кнопку GO!
     */
    public void onClick(View view) throws IOException {
        myArrayList.clear(); // Очищаем лист найденных простых чисел, нужно для многократного использования без засирания списка
        if (aEdit.getText().toString().equals("") || bEdit.getText().toString().equals("")){ // Проверка на пустые поля
            Toast toast = Toast.makeText(this,R.string.toast_higher_than_0, duration);
            toast.setGravity(Gravity.CENTER, 0, 0);
            toast.show();
        } else if (Integer.parseInt(aEdit.getText().toString()) < 1) { // Проверка на значение меньше 1
            Toast toast = Toast.makeText(this,R.string.toast_higher_than_0, duration);
            toast.setGravity(Gravity.CENTER,0,0);
            toast.show();
            aEdit.setText(null);
            aEdit.requestFocus();
        } else if (Integer.parseInt(bEdit.getText().toString()) < 1) { // Проверка на значение меньше 1
            Toast toast = Toast.makeText(this,R.string.toast_higher_than_0, duration);
            toast.setGravity(Gravity.CENTER,0,0);
            toast.show();
            bEdit.setText(null);
            bEdit.requestFocus();
        } else if (Integer.parseInt(bEdit.getText().toString()) < Integer.parseInt(aEdit.getText().toString())) { // Проверка на А > В
            Toast toast = Toast.makeText(this,R.string.toast_higher_than_A, Toast.LENGTH_LONG);
            toast.setGravity(Gravity.CENTER,0,0);
            toast.show();
            bEdit.setText(null);
            bEdit.requestFocus();
        } else {
            // Находим простые числа, (да ужасно смотрится, но это работает. Надеюсь в будущем я научусь не писать такой гавнокод)
            myArrayList = calc.calc(calc.createList(Integer.parseInt(bEdit.getText().toString())), Integer.parseInt(aEdit.getText().toString()));
        }
        if (myArrayList.size() == 0) { // Проверка на отсутствие простых чисел в диапазоне
            Toast toast = Toast.makeText(this,R.string.no_primes, duration);
            toast.setGravity(Gravity.CENTER,0,0);
            toast.show();
        }
        ArrayAdapter<String> aa = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,myArrayList); // Создаем адаптер
        myListView.setAdapter(aa); // Подключаем адаптер
        aa.notifyDataSetChanged(); // Напоминаем адаптеру, чтоб не забыл почистить ListView перед следующим использованием

    }

    /**
     * Метод для нахождения массива случайных чисел с максимально близким к заданному средним-арифметическим модулей разностей
     * всех элементов массива.
     */
    public void onSuperClick(View view) throws IOException {
        mySuperArrayList.clear(); // Очищаем лист найденных простых чисел, нужно для многократного использования без засирания списка
        if (pEdit.getText().toString().equals("")){ // Проверка на пустые поля
            Toast toast = Toast.makeText(this,R.string.toast_higher_than_0, duration);
            toast.setGravity(Gravity.CENTER, 0, 0);
            toast.show();
        } else {
            for (int i = 0; i < Integer.parseInt(pEdit.getText().toString()); i++){ // Находим число массивов равное Р (иногда правда находит)
                // Находим и добавляем массив в коллекцию (да, опять километровые строчки)
                mySuperArrayList.add(calc.calcRandomArray(calc.createList(Integer.parseInt(bEdit.getText().toString())), Integer.parseInt(aEdit.getText().toString()), Integer.parseInt(pEdit.getText().toString())));
            }
        ArrayAdapter<String> aa1 = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,mySuperArrayList); // Создаем адаптер
        mySuperListView.setAdapter(aa1); // Подключаем его
        aa1.notifyDataSetChanged(); // Напоминалка для очистки ListView
        }

    }
}
