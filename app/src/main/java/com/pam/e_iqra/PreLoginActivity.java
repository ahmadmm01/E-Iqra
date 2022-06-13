package com.pam.e_iqra;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class PreLoginActivity extends AppCompatActivity
{
    Button Teacher_button, Student_button;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pre_login);

        Teacher_button = findViewById(R.id.button_Teacher);
        Student_button = findViewById(R.id.button_Student);

        Teacher_button.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Intent Teacher = new Intent(getApplicationContext(), TeacherLoginActivity.class);
                startActivity(Teacher);
            }
        });

        Student_button.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Intent Student = new Intent(getApplicationContext(), StudentLoginActivity.class);
                startActivity(Student);
            }
        });
    }
}