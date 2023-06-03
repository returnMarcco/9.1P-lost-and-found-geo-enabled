package com.example.lost_and_found_app_android;

import androidx.appcompat.app.AppCompatActivity;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.Button;
import android.view.View;
import android.view.View.OnClickListener;
import android.content.Intent;

public class Dashboard extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dashboard);

        Button postLostItemBtn = findViewById(R.id.idNewPostBtn);
        Button showAllLostItemsBtn = findViewById(R.id.idShowAllItemsBtn);

        postLostItemBtn.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Dashboard.this, PostLostItemFormActivity.class);
                startActivity(intent);
            }
        });
        showAllLostItemsBtn.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Dashboard.this, SeeAllLostItemsActivity.class);
                startActivity(intent);
            }
        });
    }
}