package com.rudenko.anna.timeandcalendar;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TimePicker;

import java.util.Calendar;

import static android.icu.util.Calendar.getInstance;

public class TimePickerDialog extends Dialog {

    private TimePickerDialogResultListener listener;
    private TimePicker timePicker;
    private Button btnOk;

    public TimePickerDialog(Context context) {
        super(context);
        initDialog(context);

    }

    private void initDialog(Context context) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.custom_dialog_time_picker);
        timePicker = findViewById(R.id.timePicker);
        btnOk = findViewById(R.id.btn_ok);

        Calendar now = Calendar.getInstance();
        timePicker.setCurrentHour(now.get(Calendar.HOUR_OF_DAY));
        timePicker.setCurrentMinute(now.get(Calendar.MINUTE));

        btnOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendTimeToListener(timePicker.getCurrentHour(), timePicker.getCurrentMinute());
                dismiss();
            }
        });
        timePicker.setOnTimeChangedListener(new TimePicker.OnTimeChangedListener() {

            @Override
            public void onTimeChanged(TimePicker view, int hourOfDay, int minute) {
                sendTimeToListener(hourOfDay, minute);
            }
        });

    }

    private  void sendTimeToListener(int hourOfDay, int minute)
    {
        if (listener != null){
            listener.onTimeReceive("Время: " + hourOfDay + ":" + minute);
        }
    }

    public TimePickerDialog(Context context, int themeResId) {
        super(context, themeResId);
        initDialog(context);
    }

    protected TimePickerDialog(Context context, boolean cancelable, DialogInterface.OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
        initDialog(context);
    }

    interface TimePickerDialogResultListener {
        void onTimeReceive(String time);
    }

    public void setTimePickerDialogResultListener(TimePickerDialogResultListener listener) {
        this.listener = listener;
    }
}
