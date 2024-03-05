package com.example.myapplication;
import android.app.DatePickerDialog;
import android.content.SharedPreferences;
import android.os.Environment;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class MainActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener {

    LinearLayout parentLayout;
    List<View> addedPhones = new ArrayList<>();
    private TextView dateText;

    private int countClicks = 0;
    private String Name;
    private String Surname;
    private String City;
    private String Birth;
    private int Department;
    private int[] Phones;

    private TextView counterText;
    private SharedPreferences prefs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final EditText name = findViewById(R.id.name_input);
        final EditText surname = findViewById(R.id.surname_input);
        final EditText city = findViewById(R.id.city_input);
        final EditText birth = findViewById(R.id.date_text);
        final Spinner departments = findViewById(R.id.departments_input);

        parentLayout = findViewById(R.id.layout);

        // Recover the instance state.
        prefs = getPreferences(MODE_PRIVATE);

        Name = prefs.getString("name", null);
        Surname = prefs.getString("surname", null);
        City = prefs.getString("city", null);
        Birth = prefs.getString("birth", null);
        Department = prefs.getInt("department", 0);

        name.setText(Name);
        surname.setText(Surname);
        city.setText(City);
        birth.setText(Birth);
        departments.setSelection(Department);

        final Button validate = findViewById(R.id.validate);
        validate.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Snackbar.make(findViewById(R.id.c_layout), "Name: " + name.getText(), Snackbar.LENGTH_LONG).show();

                SharedPreferences.Editor editor = prefs.edit();
                editor.putString("name", name.getText().toString());
                editor.putString("surname", surname.getText().toString());
                editor.putString("city", city.getText().toString());
                editor.putString("birth", birth.getText().toString());
                editor.putInt("department", departments.getSelectedItemPosition());

                editor.apply();
            }
        });

        final Button addPhone = findViewById(R.id.add_phone);
        addPhone.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                addPhoneMethod();
            }
        });

        dateText = findViewById(R.id.date_text);
        findViewById(R.id.show_dialog).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDatePickerDialog();
            }
        });
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return super.onTouchEvent(event);
    }

    public void showDatePickerDialog(){
        DatePickerDialog datePickerDialog = new DatePickerDialog(
                this,
                this,
                Calendar.getInstance().get(Calendar.YEAR),
                Calendar.getInstance().get(Calendar.MONTH),
                Calendar.getInstance().get(Calendar.DAY_OF_MONTH));
        datePickerDialog.show();
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        String date = month + "/" + dayOfMonth + "/" + year;
        dateText.setText(date);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    public void resetAction(MenuItem item){
        final EditText name = findViewById(R.id.name_input);
        final EditText surname = findViewById(R.id.surname_input);
        final EditText city = findViewById(R.id.city_input);
        final EditText birth = findViewById(R.id.date_text);
        final Spinner departments = findViewById(R.id.departments_input);

        name.setText("");
        surname.setText("");
        city.setText("");
        birth.setText("");
        departments.setSelection(0);

        // Removing all the layouts from the phone list
        for (View view : addedPhones) {
            ViewGroup parent = (ViewGroup) view.getParent();
            if (parent != null) {
                parent.removeView(view);
            }
        }

        createSnackResponseForNumber();
    }

    public void addPhoneMethod(){

        addPhoneInputLayout();

        Button addPhoneButton = findViewById(R.id.add_phone);
        addPhoneButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addPhoneInputLayout();
            }
        });

    }

    private void addPhoneInputLayout() {

        LayoutInflater inflater = LayoutInflater.from(this);
        View phoneInputLayout = inflater.inflate(R.layout.phone_input_layout, parentLayout, false);

        Button deleteButton = phoneInputLayout.findViewById(R.id.delete_button);
        EditText editText = phoneInputLayout.findViewById(R.id.phone_number_edit_text);

        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                parentLayout.removeView((View) v.getParent());
                createSnackResponseForNumber();
            }
        });

        parentLayout.addView(phoneInputLayout);
        addedPhones.add(phoneInputLayout);
        createSnackResponseForNumber();
    }

    private void createSnackResponseForNumber(){
        String snackResponse = addedPhones.size() > 1 ?
                addedPhones.size() + "s" :
                addedPhones.size() + " ";

        Snackbar.make(findViewById(R.id.c_layout), snackResponse, Snackbar.LENGTH_LONG).show();
    }




}