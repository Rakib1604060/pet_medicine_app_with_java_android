package com.elanco.elancoapps;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.widget.DatePicker;
import android.widget.Toast;

import java.util.Calendar;

import androidx.fragment.app.DialogFragment;

public class SelcectDateFragment extends DialogFragment implements DatePickerDialog.OnDateSetListener{

    public static String date=null;


    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        final Calendar calendar = Calendar.getInstance();
        int yy = calendar.get(Calendar.YEAR);
        int mm = calendar.get(Calendar.MONTH);
        int dd = calendar.get(Calendar.DAY_OF_MONTH);
        return new DatePickerDialog(getActivity(), this, yy, mm, dd);
    }

    @Override
    public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
        populateSetDate(i, i1+1, i2);
         date=i2+"/"+i1+"/"+i;

    }

    void populateSetDate(int year, int month, int day) {
        Toast.makeText(getActivity(), "year:"+year+" day:" +day, Toast.LENGTH_SHORT).show();

    }



}
