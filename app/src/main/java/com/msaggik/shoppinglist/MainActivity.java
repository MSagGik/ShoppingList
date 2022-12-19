package com.msaggik.shoppinglist;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    public static final String REQUEST_RESULT = "RESULT";

    private ArrayList<String> productList = new ArrayList<>(); // контейнер списка продуктов
    private ArrayAdapter<String> arrayAdapter; // переходник между данными и экраном

    // жизненный цикл основного экрана
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // создание адаптера простого списка
        arrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, productList);
        // привязка ListView
        ListView listView = findViewById(R.id.listProduct);
        listView.setAdapter(arrayAdapter);
    }

    // добавление меню
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.add_menu, menu);
        return true;
    }
    // обработка нажатия на меню
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_add:
                launchActivity(); // исполняемый метод
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
    // исполняемый метод в меню (обмена сообщениями)
    private void launchActivity() {
        Intent intent = new Intent(this, AddProductActivity.class);
        startActivityForResult(intent, 1); // запуск второй активности
    }
    // обработка результата из второй активности
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode,resultCode,data);
        if (resultCode == RESULT_OK) {
            productList.add(data.getStringExtra(REQUEST_RESULT));
            arrayAdapter.notifyDataSetChanged();
            Toast.makeText(getApplicationContext(), data.getStringExtra(REQUEST_RESULT), Toast.LENGTH_LONG).show();
        }
    }
}