package com.example.trucksharingmobileapp;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.TimePicker;

import com.google.android.libraries.places.api.Places;
import com.google.android.libraries.places.api.model.Place;
import com.google.android.libraries.places.widget.Autocomplete;
import com.google.android.libraries.places.widget.model.AutocompleteActivityMode;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class AddNewOrderActivity extends AppCompatActivity {
    String pickupTime;
    String dropoffTime;
    String pickupDate ;
    String dropoffDate ;
    String hour;
    String minute;
    EditText pickUpLocationEditText,dropOffLocationEditText;
    double pickUpLatitude,pickUpLongitude,dropOffLatitude,dropOffLongitude;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_order);


        Intent intent = getIntent();
        String username = intent.getStringExtra("username");
        Button nextButton = findViewById(R.id.nextButton);

        EditText receiverNameInput = findViewById(R.id.receiverNameInput);
        TimePicker timePicker = findViewById(R.id.datePicker1);
        timePicker.setIs24HourView(true);
        pickUpLocationEditText = findViewById(R.id.pickUpLocationEditText);
        dropOffLocationEditText = findViewById(R.id.dropOffLocationEditText);

        EditText dropOffLocationEditText = findViewById(R.id.dropOffLocationEditText);


        DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        CalendarView calendarView = findViewById(R.id.calendarView);
        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView calendarView, int i, int i1, int i2) {
                Calendar c = Calendar.getInstance();
                c.set(i,i1,i2);
                pickupDate = formatter.format(c.getTime());
                c.add(Calendar.DAY_OF_YEAR,1);
                dropoffDate = formatter.format(c.getTime());
            }
        });


        String API_KEY = "AIzaSyBVmLqoajJlHhh2HkVDMOq6pOhiKCgfrak";
        Places.initialize(getApplicationContext(), API_KEY);

        pickUpLocationEditText.setFocusable(false);
        pickUpLocationEditText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                List<Place.Field> fieldList = Arrays.asList(Place.Field.ID, Place.Field.NAME, Place.Field.ADDRESS, Place.Field.LAT_LNG);
                Intent intent = new Autocomplete.IntentBuilder(AutocompleteActivityMode.OVERLAY, fieldList).build(AddNewOrderActivity.this);
                startActivityForResult(intent, 100);
            }
        });
        dropOffLocationEditText.setFocusable(false);

        dropOffLocationEditText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pickUpLocationEditText.setFocusable(false);
                List<Place.Field> fieldList = Arrays.asList(Place.Field.ID, Place.Field.NAME, Place.Field.ADDRESS, Place.Field.LAT_LNG);
                Intent intent = new Autocomplete.IntentBuilder(AutocompleteActivityMode.OVERLAY, fieldList).build(AddNewOrderActivity.this);
                startActivityForResult(intent, 101);
            }
        });
        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String receiverName = receiverNameInput.getText().toString();
                String pickUpLocation = pickUpLocationEditText.getText().toString();
                String dropOffLocation = dropOffLocationEditText.getText().toString();

                if (Build.VERSION.SDK_INT >= 23 ){
                    hour = String.valueOf(timePicker.getHour());
                    minute = String.valueOf(timePicker.getMinute());

                }
                else {
                    hour = String.valueOf(timePicker.getCurrentHour());
                    minute = String.valueOf(timePicker.getCurrentMinute());
                }
                SimpleDateFormat df = new SimpleDateFormat("HH:mm");
                String time = hour+":"+minute;
                Calendar c = Calendar.getInstance();
                Date d = null;
                try {
                    d = df.parse(time);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                c.setTime(d);
                c.add(Calendar.HOUR, 3);
                time = df.format(d.getTime());
                String newTime = df.format(c.getTime());
                pickupTime =  time + " " + pickupDate;

                dropoffTime = newTime+ " " + dropoffDate;

                Intent intent3 = new Intent(getApplicationContext(),AddNewOrderActivity2.class);
                intent3.putExtra("username",username);
                intent3.putExtra("receivername",receiverName);
                intent3.putExtra("pickupTime",pickupTime);
                intent3.putExtra("dropoffTime",dropoffTime);
                intent3.putExtra("pickUpLocation",pickUpLocation);
                intent3.putExtra("pickUpLatitude",pickUpLatitude);
                intent3.putExtra("pickUpLongitude",pickUpLongitude);
                intent3.putExtra("dropOffLocation",dropOffLocation);
                intent3.putExtra("dropOffLatitude",dropOffLatitude);
                intent3.putExtra("dropOffLongitude",dropOffLongitude);

                startActivity(intent3);
                finish();
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==100){
            Place place = Autocomplete.getPlaceFromIntent(data);
            pickUpLocationEditText.setText(place.getName());
            pickUpLatitude = place.getLatLng().latitude;
            pickUpLongitude = place.getLatLng().longitude;
        }
        else if (requestCode==101){
            Place place = Autocomplete.getPlaceFromIntent(data);
            dropOffLocationEditText.setText(place.getName());
            dropOffLatitude = place.getLatLng().latitude;
            dropOffLongitude= place.getLatLng().longitude;
        }
    }
}