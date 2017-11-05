package com.rudenko.anna.timeandcalendar;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import org.w3c.dom.Text;

public class MainActivity extends Activity implements View.OnClickListener {

    private TextView editTime;
    private TextView editDay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editDay = findViewById(R.id.tv_edit_day);
        editTime = findViewById(R.id.tv_edit_time);

        editDay.setOnClickListener(this);
        editTime.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.tv_edit_day:
                showDataPicker();
                break;
            case R.id.tv_edit_time:
                showTimePicker();
                break;
        }
    }

    private void showTimePicker() {
        TimePickerDialog tDialog = new TimePickerDialog(this);
        tDialog.setTimePickerDialogResultListener(new TimePickerDialog.TimePickerDialogResultListener() {
            @Override
            public void onTimeReceive(String time) {
                editTime.setText(time);
            }
        });
        tDialog.show();

    }

    private void showDataPicker() {
        DatePickerDialog dialog = new DatePickerDialog(this);
        dialog.setDatePickerDialogListener(new DatePickerDialog.DatePickerDialogListener() {
            @Override
            public void onDateReceive(String date) {
                editDay.setText(date);
            }
        });
        dialog.show();
    }
}
