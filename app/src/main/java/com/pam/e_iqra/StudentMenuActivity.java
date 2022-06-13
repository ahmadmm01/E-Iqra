package com.pam.e_iqra;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class StudentMenuActivity extends AppCompatActivity
{
    ImageView LogoutStudent_imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_menu);

        LogoutStudent_imageView = findViewById(R.id.imageView_LogoutStudent);

        LogoutStudent_imageView.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Intent LogoutStudent = new Intent(getApplicationContext(), StudentLoginActivity.class);
                startActivity(LogoutStudent);
            }
        });
    }
}