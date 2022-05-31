package com.example.trucksharingmobileapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class OrderDetailsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_details);

        Button estimateButton = findViewById(R.id.estimateButton);
        Integer[] profile1 = {R.drawable.profile1, R.drawable.profile2, R.drawable.profile3};
        Integer[] profile2 = {R.drawable.profile4, R.drawable.profile5, R.drawable.profile6};

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
        String pickupLocation = intent.getStringExtra("pickupLocation");
        double pickupLatitude = intent.getDoubleExtra("pickupLatitude",0);
        double pickupLongitude = intent.getDoubleExtra("pickupLongitude",0);
        String dropoffLocation = intent.getStringExtra("dropoffLocation");
        double dropoffLatitude = intent.getDoubleExtra("dropoffLatitude",0);
        double dropoffLongitude = intent.getDoubleExtra("dropoffLongitude",0);
        int position = intent.getIntExtra("position",0);
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
        ImageView orderDetailImageView2 = findViewById(R.id.orderDetailImageView2);

        senderTextView.setText("From sender "+username);
        pickupTimeTextView.setText("Pick up time: "+pickuptime);
        receiverTextView.setText("To receiver "+receivername);
        dropoffTimeTextView.setText("Est. delivery date: "+dropofftime);
        weightTextView.setText("Weight: "+weight);
        widthTextView.setText("Width: "+width);
        lengthTextView.setText("Length: "+length);
        heightTextView.setText("Height: "+height);
        typeTextView.setText("Type: "+type);
        quantityTextView.setText("Quantity: "+quantity);
        orderDetailImageView.setImageResource(profile1[position]);
        orderDetailImageView2.setImageResource(profile2[position]);
        estimateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1 = new Intent(getApplicationContext(),MapsActivity.class);

                intent1.putExtra("pickupLocation",pickupLocation);
                intent1.putExtra("pickupLatitude",pickupLatitude);
                intent1.putExtra("pickupLongitude",pickupLongitude);
                intent1.putExtra("dropoffLocation",dropoffLocation);
                intent1.putExtra("dropoffLatitude",dropoffLatitude);
                intent1.putExtra("dropoffLongitude",dropoffLongitude);


                startActivity(intent1);
            }
        });
    }
}