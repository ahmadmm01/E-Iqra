package com.pam.e_iqra;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.pam.e_iqra.ViewHolder.ReportsViewHolder;
import com.pam.e_iqra.model.Report;
import com.pam.e_iqra.model.User;

public class TeacherReportsMenuActivity extends AppCompatActivity
{
    private FloatingActionButton TeacherReportsAdd_button;
    private DatabaseReference ReportsRef;
    private RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher_reports_menu);

        recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        ReportsRef = FirebaseDatabase.getInstance().getReference().child("Report");
        TeacherReportsAdd_button = findViewById(R.id.button_TeacherReportsAdd);

        TeacherReportsAdd_button.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Intent intent = new Intent (getApplicationContext(), ReportsManageActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onStart()
    {
        super.onStart();

        FirebaseRecyclerOptions<Report> options = new FirebaseRecyclerOptions.Builder<Report>().setQuery(ReportsRef, Report.class).build();
        FirebaseRecyclerAdapter<Report, ReportsViewHolder> adapter = new FirebaseRecyclerAdapter<Report, ReportsViewHolder>(options)
        {
            @Override
            protected void onBindViewHolder(@NonNull ReportsViewHolder holder, int position, @NonNull final Report model)
            {
                holder.txtReportsId.setText(model.getRid());
                holder.txtStudentsId.setText(model.getSid());
                holder.txtIqraId.setText(model.getIid());
                holder.txtIqraValue.setText(model.getIvalue());

                holder.itemView.setOnClickListener(new View.OnClickListener()
                {
                    @Override
                    public void onClick(View v)
                    {
                        Intent intent = new Intent(getApplicationContext(), ReportsManageActivity.class);
                        intent.putExtra("rid", model.getRid());
                        startActivity(intent);
                    }
                });
            }

            @NonNull
            @Override
            public ReportsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
            {
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_reports, parent, false);
                ReportsViewHolder holder = new ReportsViewHolder(view);
                return holder;
            }
        };
        recyclerView.setAdapter(adapter);
        adapter.startListening();
    }
}