package com.example.mad_practice_15;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.text.format.DateUtils;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    TextView tvDateTime;
    Button btnDate, btnTime;
    Calendar dateTime = Calendar.getInstance();

    private final DatePickerDialog.OnDateSetListener onDateSetListener =
            (view, year, month, dayOfMonth) -> {
                dateTime.set(Calendar.YEAR, year);
                dateTime.set(Calendar.MONTH, month);
                dateTime.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                setDateAndTime();
            };

    private final TimePickerDialog.OnTimeSetListener onTimeSetListener =
            (view, hourOfDay, minute) -> {
                dateTime.set(Calendar.HOUR_OF_DAY, hourOfDay);
                dateTime.set(Calendar.MINUTE, minute);
                setDateAndTime();
            };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvDateTime = findViewById(R.id.text_view_time);
        btnDate = findViewById(R.id.button_change_date);
        btnTime = findViewById(R.id.button_change_time);

        setDateAndTime();

        btnDate.setOnClickListener(view -> new DatePickerDialog(MainActivity.this,
                onDateSetListener,
                dateTime.get(Calendar.YEAR),
                dateTime.get(Calendar.MONTH),
                dateTime.get(Calendar.DAY_OF_MONTH))
                .show());

        btnTime.setOnClickListener(view -> new TimePickerDialog(MainActivity.this,
                onTimeSetListener,
                dateTime.get(Calendar.HOUR_OF_DAY),
                dateTime.get(Calendar.MINUTE),
                true)
                .show());
    }

    private void setDateAndTime() {
        tvDateTime.setText(DateUtils.formatDateTime(this,
                dateTime.getTimeInMillis(),
                DateUtils.FORMAT_SHOW_YEAR
                        | DateUtils.FORMAT_SHOW_DATE
                        | DateUtils.FORMAT_SHOW_TIME));
    }
}