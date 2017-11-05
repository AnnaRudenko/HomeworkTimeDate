package com.rudenko.anna.timeandcalendar;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.Window;
import android.widget.DatePicker;

import java.util.Calendar;

public class DatePickerDialog extends Dialog{
    private DatePickerDialogListener listener;
    private DatePicker datePicker;




    public DatePickerDialog(Context context) {
        super(context);
        initDialog();
    }

    private void initDialog() {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.custom_dialog_date_picker);
        datePicker = findViewById(R.id.datePicker);
        Calendar today = Calendar.getInstance();
        datePicker.init(today.get(Calendar.YEAR), today.get(Calendar.MONTH),
                today.get(Calendar.DAY_OF_MONTH), new DatePicker.OnDateChangedListener() {
                    @Override
                    public void onDateChanged(DatePicker datePicker, int year,
                                              int monthOfYear, int dayOfMonth) {
                        sendDateToListener(year, monthOfYear, dayOfMonth);
                        dismiss();
                    }
                });

    }

    private void sendDateToListener(int year, int monthOfYear, int dayOfMonth) {
        if (listener != null){
            listener.onDateReceive("Год: " + year + " Месяц: "
                    + (monthOfYear + 1) + " День: " + dayOfMonth);
        }
    }

    public DatePickerDialog( Context context, int themeResId) {
        super(context, themeResId);
    }

    interface DatePickerDialogListener {
        void onDateReceive(String date);
    }

    void setDatePickerDialogListener(DatePickerDialogListener listener){
        this.listener = listener;
    }

    protected DatePickerDialog(Context context, boolean cancelable, DialogInterface.OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
    }
}
