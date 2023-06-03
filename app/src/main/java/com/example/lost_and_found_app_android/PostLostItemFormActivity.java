package com.example.lost_and_found_app_android;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.Toast;
public class PostLostItemFormActivity extends AppCompatActivity {
    RadioGroup lostOrFoundRadioGroup;
    RadioButton lostItemRadioBtn;
    RadioButton foundItemRadioBtn;
    EditText userName;
    EditText userPhoneNumber;
    EditText itemDescription;
    EditText dateOfPost;
    EditText locationOfLostFoundItem;

    String lostOrFoundPost;
    float itemLat;
    float itemLng;
    Button getCurrentLocationButton;
    Button savePostButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_lost_item_form);

        // Widgets
        lostOrFoundRadioGroup = findViewById(R.id.idRadioGroupLostOrFound);
        userName = findViewById(R.id.idNameInput);
        userPhoneNumber = findViewById(R.id.editTextPhone);
        itemDescription = findViewById(R.id.idItemDescription);
        dateOfPost = findViewById(R.id.idEditTextDate);
        locationOfLostFoundItem = findViewById(R.id.idLocationLostFoundItem);
        savePostButton = findViewById(R.id.idSavePostBtn);
        getCurrentLocationButton = findViewById(R.id.idGetCurrentLocationBtn);
        lostItemRadioBtn = findViewById(R.id.idLostRadioBtn);
        foundItemRadioBtn = findViewById(R.id.idFoundRadioBtn);
        lostItemRadioBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                lostOrFoundPost = "Lost";
            }
        });

        foundItemRadioBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                lostOrFoundPost = "Found";
            }
        });

        getCurrentLocationButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /**
                 * Todo @Jason: Grab users current location via Google Maps Android API
                 * and store this location in the `locationOfLostOrFoundItem` property. Note: Will
                 * probably need to replace the `Location` field in the Db with Lat/Lng fields.
                 */


            }
        });

        savePostButton.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("NewApi")
            @Override
            public void onClick(View view) {
                try {
                    if (lostOrFoundPost != null) {
                        LostAndFoundModel lostAndFoundPost = new LostAndFoundModel(-1, userName.getText().toString(), Integer.parseInt(userPhoneNumber.getText().toString()), itemDescription.getText().toString(), dateOfPost.getText().toString(), false, itemLat, itemLng, lostOrFoundPost);
                        DbHelper dataBaseHelper = new DbHelper(PostLostItemFormActivity.this);
                        boolean success = dataBaseHelper.createLostOrFoundItemRecord(lostAndFoundPost);

                        // Todo @Jason: Create Maps marker using Lat/Lng of location entered on Post activity form.
                        if (success) {
                            Toast.makeText(PostLostItemFormActivity.this, lostOrFoundPost + " item has been posted successfully!", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(PostLostItemFormActivity.this, "An error has occurred! The item has not been posted.", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(PostLostItemFormActivity.this, "You must select a category for this post.", Toast.LENGTH_SHORT).show();
                    }
                } catch(Exception e) {
                    Toast.makeText(PostLostItemFormActivity.this, "You must fill out all required fields.", Toast.LENGTH_SHORT).show();
                }
            }
        });
    };
}
