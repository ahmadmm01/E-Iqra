package com.pam.e_iqra;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;

public class StudentsManageActivity extends AppCompatActivity
{
    private EditText StudentsId_editText, StudentsName_editText, StudentsPassword_editText;
    private Button SaveStudents_button;
    private ProgressDialog loadingBar;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_students_manage);

        StudentsId_editText = findViewById(R.id.editText_StudentsId);
        StudentsName_editText = findViewById(R.id.editText_StudentsName);
        StudentsPassword_editText = findViewById(R.id.editText_StudentsPassword);
        SaveStudents_button = findViewById(R.id.button_SaveStudents);


        loadingBar = new ProgressDialog(this);

        SaveStudents_button.setOnClickListener(new View.OnClickListener()
        {
            @SuppressLint("NotifyDataSetChanged")
            @Override
            public void onClick(View view)
            {
                saveData();
            }
        });
    }

    private void saveData()
    {
        String id = StudentsId_editText.getText().toString();
        String name = StudentsName_editText.getText().toString();
        String password = StudentsPassword_editText.getText().toString();

        if(TextUtils.isEmpty(id))
        {
            Toast.makeText(this, "Please Write your id", Toast.LENGTH_SHORT).show();
        }
        else if(TextUtils.isEmpty(name))
        {
            Toast.makeText(this, "Please Write your name", Toast.LENGTH_SHORT).show();
        }
        else if(TextUtils.isEmpty(password))
        {
            Toast.makeText(this, "Please Write your password", Toast.LENGTH_SHORT).show();
        }
        else
        {
            loadingBar.setTitle("Create Account");
            loadingBar.setMessage("Please Wait, While We are Check Credentials..");
            loadingBar.setCanceledOnTouchOutside(false);
            loadingBar.show();

            validate(id, name, password);
        }
    }

    private void validate(final String id, final String name, final String password)
    {
        final DatabaseReference db = FirebaseDatabase.getInstance().getReference();
        db.addListenerForSingleValueEvent(new ValueEventListener()
        {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot)
            {
                if(!(dataSnapshot.child("User").child("Student").child(id).exists()))
                {
                    HashMap<String, Object> UserDataMap = new HashMap<>();

                    UserDataMap.put("sid",id);
                    UserDataMap.put("sname",name);
                    UserDataMap.put("spass",password);

                    db.child("User").child("Student").child(id).updateChildren(UserDataMap).addOnCompleteListener(new OnCompleteListener<Void>()
                    {
                        @Override
                        public void onComplete(@NonNull Task<Void> task)
                        {
                            if(task.isSuccessful())
                            {
                                Toast.makeText(getApplicationContext(), "Congratulation your account has been created.", Toast.LENGTH_SHORT).show();
                                loadingBar.dismiss();
                                Intent intent = new Intent(getApplicationContext(), TeacherStudentsMenuActivity.class);
                                startActivity(intent);
                            }
                            else
                            {
                                loadingBar.dismiss();
                                Toast.makeText(getApplicationContext(), "Network error, please try again after some time..", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }
                else
                {
                    Toast.makeText(getApplicationContext(), "this "+ id + " already exist", Toast.LENGTH_SHORT).show();
                    loadingBar.dismiss();
                    Toast.makeText(getApplicationContext(), "please try again using another id", Toast.LENGTH_SHORT).show();

                    Intent intent = new Intent(getApplicationContext(), TeacherStudentsMenuActivity.class);
                    startActivity(intent);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}