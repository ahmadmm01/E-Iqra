package com.pam.e_iqra;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class StudentLoginActivity extends AppCompatActivity
{
    String Student_ID, Student_Password;

    EditText StudentID_editText, StudentPassword_editText;

    Button StudentLogin_button;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_login);

        StudentID_editText = findViewById(R.id.editText_StudentID);
        StudentPassword_editText = findViewById(R.id.editText_StudentPassword);

        StudentLogin_button = findViewById(R.id.button_StudentLogin);

        StudentLogin_button.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Intent StudentMenu = new Intent(getApplicationContext(), StudentMenuActivity.class);
                startActivity(StudentMenu);
            }
        });
    }
}