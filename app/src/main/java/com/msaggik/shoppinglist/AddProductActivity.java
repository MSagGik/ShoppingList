package com.msaggik.shoppinglist;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

public class AddProductActivity extends AppCompatActivity {

    // ввод текста
    private EditText editText; // наименование
    private EditText editTextAmount; // количество
    private Spinner spinner; // единица измерения
    //единицы измерения
    String[] si = {"кг.", "шт.", "уп."};
    String siYes = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_product);

        // ???
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        // присваивание переменным элементов из представления
        editText = findViewById(R.id.editProduct);
        editTextAmount = findViewById(R.id.editProductAmount);
        spinner = findViewById(R.id.spinner);

        // создание адаптера для спиннера (передача массива си на адаптер)
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, si);
        // определение разметки для использования при выборе элемента
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // применение адаптера к элементу спиннера
        spinner.setAdapter(adapter);

        // определение слушателя
        AdapterView.OnItemSelectedListener itemSelectedListener = new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) { // adapterView - объект спиннер, в котором произошло событие выбора
                // view - объект представления внутри спиннера, который представляет выбранный элемент
                // i - индекс выбранного элемента в адаптере
                // l - идентификатор строки того элемента, который был выбран

                // получение выбранного объекта
                siYes = (String) adapterView.getItemAtPosition(i);
                //String item = (String) adapterView.getItemAtPosition(i);
                //textView.setText(item); // запись в текствью
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) { // ???

            }
        };
        // запись выбранной единицы измерения
        spinner.setOnItemSelectedListener(itemSelectedListener);
    }

    // обработка нажатия на меню
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                back(); // исполняемый метод возврата на основную активити
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
    // метод возврата на основную активити
    public void back() {
        Intent i = new Intent();
        i.putExtra(MainActivity.REQUEST_RESULT, editText.getText().toString() + " " + editTextAmount.getText().toString() + " " + siYes); // ("ключ", "значение")
        setResult(RESULT_OK, i);
        finish();
    }
}
