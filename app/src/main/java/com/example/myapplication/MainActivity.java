package com.example.myapplication;
import android.os.PersistableBundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity implements IShow {

    private InsertInfoFragment insertInfo;
    private ShowInfoFragment showInfo;
    private DatePickerFragment birthDate;
    private User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (savedInstanceState == null) {
            insertInfo = new InsertInfoFragment();
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.c_layout, insertInfo)
                    .commit();
        } else {
            insertInfo = (InsertInfoFragment) getSupportFragmentManager().findFragmentByTag("insertInfo");
            showInfo = (ShowInfoFragment) getSupportFragmentManager().findFragmentByTag("showInfo");
            birthDate = (DatePickerFragment) getSupportFragmentManager().findFragmentByTag("birthDate");
        }
    }

    @Override
    public void onValidate(User user) {
        showInfo = ShowInfoFragment.newInstance(user);
        FragmentTransaction tx = getSupportFragmentManager().beginTransaction();
        tx.replace(R.id.c_layout, showInfo, "showInfo");
        tx.addToBackStack(null);
        tx.commit();
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState, @NonNull PersistableBundle outPersistentState) {
        super.onSaveInstanceState(outState, outPersistentState);
        getSupportFragmentManager().putFragment(outState, "insertInfo", insertInfo);
        getSupportFragmentManager().putFragment(outState, "showInfo", showInfo);
        getSupportFragmentManager().putFragment(outState, "birthDate", birthDate);

    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        insertInfo = (InsertInfoFragment) getSupportFragmentManager().getFragment(savedInstanceState, "insertInfo");
        showInfo = (ShowInfoFragment) getSupportFragmentManager().getFragment(savedInstanceState, "showInfo");
        birthDate = (DatePickerFragment) getSupportFragmentManager().getFragment(savedInstanceState, "birthDate");
    }
}