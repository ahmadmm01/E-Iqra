package com.pam.e_iqra;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class TeacherMenuActivity extends AppCompatActivity
{
    ImageView LogoutTeacher_imageView, StudentsIconTeacher_imageView, ReportsIconTeacher_imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher_menu);

        LogoutTeacher_imageView = findViewById(R.id.imageView_LogoutTeacher);
        StudentsIconTeacher_imageView = findViewById(R.id.imageView_StudentsIconTeacher);
        ReportsIconTeacher_imageView = findViewById(R.id.imageView_ReportsIconTeacher);

        LogoutTeacher_imageView.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Intent LogoutTeacher = new Intent(getApplicationContext(), TeacherLoginActivity.class);
                startActivity(LogoutTeacher);
            }
        });

        StudentsIconTeacher_imageView.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Intent StudentsList = new Intent(getApplicationContext(), TeacherStudentsMenuActivity.class);
                startActivity(StudentsList);
            }
        });

        ReportsIconTeacher_imageView.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Intent ReportsList = new Intent(getApplicationContext(), TeacherReportsMenuActivity.class);
                startActivity(ReportsList);
            }
        });
    }
}