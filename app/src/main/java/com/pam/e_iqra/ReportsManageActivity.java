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

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;

public class ReportsManageActivity extends AppCompatActivity
{
    private EditText ReportsReportsId_editText, ReportsStudentsId_editText, ReportsIqraId_editText, ReportsIqraValue_editText;
    private Button SaveReports_button;
    private ProgressDialog loadingBar;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reports_manage);

        ReportsReportsId_editText = findViewById(R.id.editText_ReportsReportsId);
        ReportsStudentsId_editText = findViewById(R.id.editText_ReportsStudentsId);
        ReportsIqraId_editText = findViewById(R.id.editText_ReportsIqraId);
        ReportsIqraValue_editText = findViewById(R.id.editText_ReportsIqraValue);
        SaveReports_button = findViewById(R.id.button_SaveReports);

        loadingBar = new ProgressDialog(this);

        SaveReports_button.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                saveData();
            }
        });
    }

    private void saveData()
    {
        String rid = ReportsReportsId_editText.getText().toString();
        String sid = ReportsStudentsId_editText.getText().toString();
        String iid = ReportsIqraId_editText.getText().toString();
        String ivalue = ReportsIqraId_editText.getText().toString();

        if(TextUtils.isEmpty(rid))
        {
            Toast.makeText(this, "Please Write Report ID", Toast.LENGTH_SHORT).show();
        }
        else if(TextUtils.isEmpty(sid))
        {
            Toast.makeText(this, "Please Write Student ID", Toast.LENGTH_SHORT).show();
        }
        else if(TextUtils.isEmpty(iid))
        {
            Toast.makeText(this, "Please Write Iqra ID", Toast.LENGTH_SHORT).show();
        }
        else if(TextUtils.isEmpty(ivalue))
        {
            Toast.makeText(this, "Please Write Value", Toast.LENGTH_SHORT).show();
        }
        else
        {
            loadingBar.setTitle("Create Data");
            loadingBar.setMessage("Please Wait, While We are Check Database..");
            loadingBar.setCanceledOnTouchOutside(false);
            loadingBar.show();

            validate(rid, sid, iid, ivalue);
        }
    }

    private void validate(final String rid, final String sid, final String iid, final String ivalue)
    {
        final DatabaseReference db = FirebaseDatabase.getInstance().getReference();
        db.addListenerForSingleValueEvent(new ValueEventListener()
        {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot)
            {
                if(!(dataSnapshot.child("Report").child(rid).exists()))
                {
                    HashMap<String, Object> UserDataMap = new HashMap<>();

                    UserDataMap.put("rid", rid);
                    UserDataMap.put("sid", sid);
                    UserDataMap.put("iid", iid);
                    UserDataMap.put("ivalue", ivalue);

                    db.child("Report").child(rid).updateChildren(UserDataMap).addOnCompleteListener(new OnCompleteListener<Void>()
                    {
                        @Override
                        public void onComplete(@NonNull Task<Void> task)
                        {
                            if(task.isSuccessful())
                            {
                                Toast.makeText(getApplicationContext(), "Congratulation data has been created.", Toast.LENGTH_SHORT).show();
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
                    Toast.makeText(getApplicationContext(), "this "+ rid + " already exist", Toast.LENGTH_SHORT).show();
                    loadingBar.dismiss();
                    Toast.makeText(getApplicationContext(), "please try again using another id", Toast.LENGTH_SHORT).show();

                    Intent intent = new Intent(getApplicationContext(), TeacherReportsMenuActivity.class);
                    startActivity(intent);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError)
            {

            }
        });
    }
}