package com.pam.e_iqra;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.pam.e_iqra.adapter.UserAdapter;
import com.pam.e_iqra.model.User;

import java.util.ArrayList;
import java.util.List;

public class TeacherStudentsMenuActivity extends AppCompatActivity
{
    private RecyclerView recyclerView;
    private FloatingActionButton TeacherStudentsAdd_button;
    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private List<User> list = new ArrayList<>();
    private UserAdapter userAdapter;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher_students_menu);

        recyclerView = findViewById(R.id.recycler_view);
        TeacherStudentsAdd_button = findViewById(R.id.button_TeacherStudentsAdd);

        progressDialog = new ProgressDialog(getApplicationContext());
        progressDialog.setTitle("Loading");
        progressDialog.setMessage("Take the Data...");
        userAdapter = new UserAdapter(getApplicationContext(), list);

        userAdapter.setDialog(new UserAdapter.Dialog()
        {
            @Override
            public void onClick(int pos)
            {
                final CharSequence[] dialogItem = {"Edit", "Delete"};
                AlertDialog.Builder dialog = new AlertDialog.Builder(getApplicationContext());
                dialog.setItems(dialogItem, new DialogInterface.OnClickListener()
                {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i)
                    {
                        switch (i)
                        {
                            case 0:
                                Intent intent = new Intent(getApplicationContext(), TeacherMenuActivity.class);
                                intent.putExtra("id", list.get(pos).getSid());
                                startActivity(intent);
                                break;
                            case 1:
                                deleteData(list.get(pos).getSid());
                                break;
                        }
                    }
                });
                dialog.show();
            }
        });
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL,false);
        RecyclerView.ItemDecoration decoration = new DividerItemDecoration(getApplicationContext(), DividerItemDecoration.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.addItemDecoration(decoration);
        recyclerView.setAdapter(userAdapter);
    }

    @Override
    protected void onStart()
    {
        super.onStart();
        getData();
    }

    private void getData()
    {
        progressDialog.show();

        db.collection("users").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>()
        {
            @SuppressLint("NotifyDataSetChanged")
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task)
            {
                list.clear();
                if(task.isSuccessful())
                {
                    for (QueryDocumentSnapshot document : task.getResult())
                    {
                        User user = new User(document.getString("sid"), document.getString("sname"));
                        user.setSid(document.getId());
                        list.add(user);
                    }
                    userAdapter.notifyDataSetChanged();
                }
                else
                {
                    Toast.makeText(getApplicationContext(), "Failed to take Data!", Toast.LENGTH_SHORT).show();
                }
                progressDialog.dismiss();
            }
        });
    }

    private void deleteData(String id)
    {
        progressDialog.show();
        db.collection("users").document(id).delete().addOnCompleteListener(new OnCompleteListener<Void>()
        {
            @Override
            public void onComplete(@NonNull Task<Void> task)
            {
                if(!task.isSuccessful())
                {
                    Toast.makeText(getApplicationContext(), "Failed to Delete Data!", Toast.LENGTH_SHORT).show();
                }
                progressDialog.dismiss();
                getData();
            }
        });
    }
}