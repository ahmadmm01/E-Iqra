package com.pam.e_iqra;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.pam.e_iqra.ViewHolder.StudentsViewHolder;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.pam.e_iqra.model.User;
import com.firebase.ui.database.FirebaseRecyclerOptions;


public class TeacherStudentsMenuActivity extends AppCompatActivity
{
    private FloatingActionButton TeacherStudentsAdd_button;
    private DatabaseReference StudentsRef;
    private RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher_students_menu);

        recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        StudentsRef = FirebaseDatabase.getInstance().getReference().child("User").child("Student");
        TeacherStudentsAdd_button = findViewById(R.id.button_TeacherStudentsAdd);

        TeacherStudentsAdd_button.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Intent intent = new Intent (getApplicationContext(), StudentsManageActivity.class);
                startActivity(intent);
            }
        });

    }

    @Override
    protected void onStart()
    {
        super.onStart();

        FirebaseRecyclerOptions<User> options = new FirebaseRecyclerOptions.Builder<User>().setQuery(StudentsRef, User.class).build();
        FirebaseRecyclerAdapter<User, StudentsViewHolder> adapter = new FirebaseRecyclerAdapter<User, StudentsViewHolder>(options)
        {
            @Override
            protected void onBindViewHolder(@NonNull StudentsViewHolder holder, int position, @NonNull final User model)
            {
                holder.txtStudentsId.setText(model.getSid());
                holder.txtStudentsName.setText(model.getSname());

                holder.itemView.setOnClickListener(new View.OnClickListener()
                {
                    @Override
                    public void onClick(View v)
                    {
                        Intent intent = new Intent(getApplicationContext(), StudentsManageActivity.class);
                        intent.putExtra("sid", model.getSid());
                        startActivity(intent);
                    }
                });
            }

            @NonNull
            @Override
            public StudentsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewtype)
            {
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_students, parent,false);
                StudentsViewHolder holder = new StudentsViewHolder(view);
                return holder;
            }
        };
        recyclerView.setAdapter(adapter);
        adapter.startListening();
    }
}