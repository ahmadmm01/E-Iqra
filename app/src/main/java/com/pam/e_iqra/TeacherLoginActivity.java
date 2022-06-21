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

public class TeacherLoginActivity extends AppCompatActivity
{
    private String Teacher_ID, Teacher_Password;
    private EditText TeacherID_editText, TeacherPassword_editText;
    private Button  TeacherLogin_button;
    private String ParentDbName = "User";
    private ProgressDialog loadBar;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher_login);

        TeacherID_editText = findViewById(R.id.editText_TeacherID);
        TeacherPassword_editText = findViewById(R.id.editText_TeacherPassword);
        TeacherLogin_button = findViewById(R.id.button_TeacherLogin);

        loadBar = new ProgressDialog(this);

        TeacherLogin_button.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                loginUser();
            }
        });
    }

    private void loginUser()
    {
        Teacher_ID = TeacherID_editText.getText().toString();
        Teacher_Password = TeacherPassword_editText.getText().toString();

        if(TextUtils.isEmpty(Teacher_ID))
        {
            Toast.makeText(this, "Please write your Teacher ID", Toast.LENGTH_SHORT).show();
        }
        else if(TextUtils.isEmpty(Teacher_Password))
        {
            Toast.makeText(this, "Please write your password", Toast.LENGTH_SHORT).show();
        }
        else
        {
            loadBar.setTitle("Login account");
            loadBar.setMessage("please wait, while we are check credentials..");
            loadBar.setCanceledOnTouchOutside(false);
            loadBar.show();

            AllowAccessAccount(Teacher_ID, Teacher_Password);
        }
    }

    private void AllowAccessAccount(final String tid, final String tpass)
    {
        final DatabaseReference RootRef;
        RootRef = FirebaseDatabase.getInstance().getReference();
        RootRef.addListenerForSingleValueEvent(new ValueEventListener()
        {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot)
            {
                if (dataSnapshot.child(ParentDbName).child("Teacher").child(tid).exists())
                {
                    User UserData = dataSnapshot.child(ParentDbName).child("Teacher").child(tid).getValue(User.class);
                    if (UserData.getTid().equals(tid))
                    {
                        if (UserData.getTpass().equals(tpass))
                        {
                            loadBar.dismiss();
                            Toast.makeText(getApplicationContext(), "Login succesfully!", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(TeacherLoginActivity.this, TeacherMenuActivity.class);
                            startActivity(intent);
                        }
                        else
                        {
                            loadBar.dismiss();
                            Toast.makeText(TeacherLoginActivity.this, "Password is incorrect!", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
                else
                {
                    loadBar.dismiss();
                    Toast.makeText(TeacherLoginActivity.this, "Teacher with ID " + tid + " does not exist", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError)
            {

            }
        });
    }
}