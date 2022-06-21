package com.pam.e_iqra;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class StudentMenuActivity extends AppCompatActivity
{
    private ImageView LogoutStudent_imageView, ReportsIconStudent_imageView;
//    private Bundle bundle = getIntent().getExtras();
//    public String sid = "none";
//    private static String Student_ID;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_menu);

        LogoutStudent_imageView = findViewById(R.id.imageView_LogoutStudent);
        ReportsIconStudent_imageView = findViewById(R.id.imageView_ReportsIconStudent);

//        Bundle bundle = getIntent().getExtras();

//        String sid = bundle.getString("sid");
//        if(sid == "none")
//        {
//            sid = StudentMenuActivity.Student_ID;
//        }
//        else
//        {
//            sid = bundle.getString("sid");
//        }


        LogoutStudent_imageView.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Intent LogoutStudent = new Intent(getApplicationContext(), StudentLoginActivity.class);
                startActivity(LogoutStudent);
            }
        });

        ReportsIconStudent_imageView.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
//                Bundle b = new Bundle();
//                b.putString("sid", sid.trim());

                Intent ReportsStudent = new Intent(getApplicationContext(), StudentReportsMenuActivity.class);

//                ReportsStudent.putExtras(b);

                startActivity(ReportsStudent);
            }
        });
    }
}