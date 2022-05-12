package com.example.trucksharingmobileapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class OrderDetailsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_details);

        Intent intent = getIntent();
        String username = intent.getStringExtra("username");
        String receivername = intent.getStringExtra("receivername");
        String dropofftime = intent.getStringExtra("dropofftime");
        String pickuptime = intent.getStringExtra("pickuptime");
        String weight = intent.getStringExtra("weight");
        String height = intent.getStringExtra("height");
        String width = intent.getStringExtra("width");
        String length = intent.getStringExtra("length");
        String quantity = intent.getStringExtra("quantity");
        String type = intent.getStringExtra("type");
        int image = intent.getIntExtra("image",0);

        TextView senderTextView = findViewById(R.id.senderTextView);
        TextView receiverTextView = findViewById(R.id.receiverTextView);
        TextView pickupTimeTextView = findViewById(R.id.pickupTimeTextView);
        TextView dropoffTimeTextView = findViewById(R.id.dropoffTimeTextView);
        TextView weightTextView = findViewById(R.id.weightTextView);
        TextView lengthTextView = findViewById(R.id.lengthTextView);
        TextView widthTextView = findViewById(R.id.widthTextView);
        TextView heightTextView = findViewById(R.id.heightTextView);
        TextView typeTextView = findViewById(R.id.typeTextView);
        TextView quantityTextView = findViewById(R.id.quantityTextView);
        ImageView orderDetailImageView = findViewById(R.id.orderDetailImageView);

        senderTextView.setText("From sender "+username);
        pickupTimeTextView.setText("Pick up time: "+pickuptime);
        receiverTextView.setText("To receiver "+receivername);
        dropoffTimeTextView.setText("Drop off time: "+dropofftime);
        weightTextView.setText("Weight: "+weight);
        widthTextView.setText("Width: "+width);
        lengthTextView.setText("Length: "+length);
        heightTextView.setText("Height: "+height);
        typeTextView.setText("Type: "+type);
        quantityTextView.setText("Quantity: "+quantity);
        orderDetailImageView.setImageResource(image);
    }
}