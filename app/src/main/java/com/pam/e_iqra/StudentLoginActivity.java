package com.pam.e_iqra;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.pam.e_iqra.model.User;

public class StudentLoginActivity extends AppCompatActivity
{
    public static String Student_ID;
    public String Student_Password;
    private EditText StudentID_editText, StudentPassword_editText;
    private Button StudentLogin_button;
    private String ParentDbName = "User";
    private ProgressDialog loadBar;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_login);

        StudentID_editText = findViewById(R.id.editText_StudentID);
        StudentPassword_editText = findViewById(R.id.editText_StudentPassword);
        StudentLogin_button = findViewById(R.id.button_StudentLogin);

        loadBar = new ProgressDialog(this);

        StudentLogin_button.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {

                loginUser();
            }
        });
    }

    public void loginUser()
    {
        Student_ID = StudentID_editText.getText().toString();
        Student_Password = StudentPassword_editText.getText().toString();

        if(TextUtils.isEmpty(Student_ID))
        {
            Toast.makeText(this, "Please write your Student ID", Toast.LENGTH_SHORT).show();
        }
        else if(TextUtils.isEmpty(Student_Password))
        {
            Toast.makeText(this, "Please write your password", Toast.LENGTH_SHORT).show();
        }
        else
        {
            loadBar.setTitle("Login account");
            loadBar.setMessage("please wait, while we are check credentials..");
            loadBar.setCanceledOnTouchOutside(false);
            loadBar.show();

            AllowAccessAccount(Student_ID, Student_Password);
        }
    }

    private void AllowAccessAccount(final String sid, final String spass)
    {
        final DatabaseReference RootRef;
        RootRef = FirebaseDatabase.getInstance().getReference();
        RootRef.addListenerForSingleValueEvent(new ValueEventListener()
        {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot)
            {
                if (dataSnapshot.child(ParentDbName).child("Student").child(sid).exists())
                {
                    User UserData = dataSnapshot.child(ParentDbName).child("Student").child(sid).getValue(User.class);
                    if (UserData.getSid().equals(sid))
                    {
                        if (UserData.getSpass().equals(spass))
                        {
                            loadBar.dismiss();

                            Intent intent = new Intent(getApplicationContext(), StudentMenuActivity.class);

                            startActivity(intent);
                        }
                        else
                        {
                            loadBar.dismiss();
                            Toast.makeText(getApplicationContext(), "Password is incorrect!", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
                else
                {
                    loadBar.dismiss();
                    Toast.makeText(getApplicationContext(), "Student with ID " + sid + " does not exist", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError)
            {

            }
        });
    }
}