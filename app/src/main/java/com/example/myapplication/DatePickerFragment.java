package com.example.myapplication;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import java.util.Calendar;
import java.util.Locale;

public class DatePickerFragment extends DialogFragment implements DatePickerDialog.OnDateSetListener {

    private IDate mListener;

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        // Use the current date as the default date in the picker
        final Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);

        // Create a new instance of DatePickerDialog and return it
        return new DatePickerDialog(requireActivity(), this, year, month, day);
    }

    @Override
    public void onDateSet(android.widget.DatePicker view, int year, int month, int dayOfMonth) {
        String selectedDate = formatDate(year, month, dayOfMonth);
        if (mListener != null) {
            mListener.onDateSelected(selectedDate);
        }
    }

    private String formatDate(int year, int month, int dayOfMonth) {
        return String.format(Locale.getDefault(), "%d-%02d-%02d", year, month + 1, dayOfMonth);
    }

    public void setOnDateSelectedListener(InsertInfoFragment listener) {
        mListener = (IDate) listener;
    }
}
