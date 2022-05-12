package com.example.trucksharingmobileapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;

import java.util.ArrayList;
import java.util.List;

public class HomeActivity extends AppCompatActivity implements TruckAdapter.OnRowClickListener {
    DatabaseHelper db;
    Integer[] truckImageList = {R.drawable.truck1, R.drawable.truck2, R.drawable.truck3,R.drawable.truck4,R.drawable.truck5};
    List<Truck> truckList = new ArrayList<>();
    RecyclerView truckRecycleView;
    String username;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);


        Intent intent = getIntent();
        username = intent.getStringExtra("username");

        db = new DatabaseHelper(this);
        List<Truck> tempt = db.fetchAllTrucks();

        for (int i = 0; i < tempt.size(); i++) {

            Truck truck = new Truck(tempt.get(i).getTruckname(), tempt.get(i).getDescription(), truckImageList[i]);
            truckList.add(truck);

        }
        truckRecycleView = findViewById(R.id.truckRecycleView);
        TruckAdapter truckAdapter = new TruckAdapter(HomeActivity.this, truckList, this);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false);
        truckRecycleView.setLayoutManager(layoutManager);
        truckRecycleView.setAdapter(truckAdapter);

        ImageButton addNewOrderButton = findViewById(R.id.addNewOrderButton);
        addNewOrderButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent2 = new Intent(getApplicationContext(), AddNewOrderActivity.class);
                intent2.putExtra("username", username);
                startActivity(intent2);
                finish();
            }
        });


    }

    @Override
    public void onItemClick(int position) {

    }

    @Override
    public void onShareButtonClick(View v, int position) {
        Intent sharedIntent = new Intent(Intent.ACTION_SEND);
        sharedIntent.setType("text/plain");
        String body = "Share your truck";
        sharedIntent.putExtra(Intent.EXTRA_TEXT, body);
        startActivity(Intent.createChooser(sharedIntent, "Share Truck"));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_example, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.home_item:
                Intent intent3 = new Intent(getApplicationContext(),HomeActivity.class);
                intent3.putExtra("username",username);
                startActivity(intent3);
                finish();
                return true;
            case R.id.order_item:
                Intent intent4 = new Intent(getApplicationContext(), MyOrderActivity.class);
                intent4.putExtra("username",username);
                startActivity(intent4);
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}