package com.pam.e_iqra;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class TeacherLoginActivity extends AppCompatActivity
{
    String Teacher_ID, Teacher_Password;

    EditText TeacherID_editText, TeacherPassword_editText;

    Button  TeacherLogin_button;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher_login);

        TeacherID_editText = findViewById(R.id.editText_TeacherID);
        TeacherPassword_editText = findViewById(R.id.editText_TeacherPassword);

        TeacherLogin_button = findViewById(R.id.button_TeacherLogin);

        TeacherLogin_button.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Intent TeacherMenu = new Intent(getApplicationContext(), TeacherMenuActivity.class);
                startActivity(TeacherMenu);
            }
        });
    }
}