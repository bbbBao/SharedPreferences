package com.example.sharedpreferences;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    EditText editText;
    int saveImageIndex = 0;
    private SharedPreferences pref;
    int[] images = {R.drawable.dog0, R.drawable.dog1, R.drawable.dog2, R.drawable.dog3,
            R.drawable.dog4};



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        pref = this.getSharedPreferences("MyPref", MODE_PRIVATE);

        setContentView(R.layout.activity_main);

        Button button = findViewById(R.id.button);
        ImageView imageView = findViewById(R.id.imageView);
        EditText editText = findViewById(R.id.input);

        int retrieveImage = pref.getInt("imageKey", -1);
        if (retrieveImage != -1){
            imageView.setImageResource(images[retrieveImage]);
            saveImageIndex = retrieveImage;
        }
        editText.setText(pref.getString("inputKey", ""));

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Random ran = new Random();
                int index = ran.nextInt(4 + 1);
                saveImageIndex = index;
                imageView.setImageResource(images[index]);
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        editText = findViewById(R.id.input);
        SharedPreferences.Editor editor = pref.edit();
        editor.putInt("imageKey", saveImageIndex);
        editor.putString("inputKey", editText.getText().toString());
        editor.commit();
    }


}