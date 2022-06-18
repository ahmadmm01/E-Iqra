package com.pam.e_iqra.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.pam.e_iqra.R;
import com.pam.e_iqra.model.User;

import java.util.List;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.MyViewHolder>
{
    private Context context;
    private List<User> list;
    private Dialog dialog;

    public interface Dialog
    {
        void onClick(int pos);
    }
    public void setDialog(Dialog dialog)
    {
        this.dialog = dialog;
    }
    public UserAdapter(Context context, List<User> list)
    {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_students, parent, false);
        return new MyViewHolder(itemView);
    }
    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position)
    {
        holder.id.setText(list.get(position).getSid());
        holder.name.setText(list.get(position).getSname());
    }
    @Override
    public int getItemCount()
    {
        return list.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder
    {
        TextView id, name;

        public MyViewHolder(@NonNull View itemView)
        {
            super(itemView);
            id = itemView.findViewById(R.id.row_StudentsId);
            name = itemView.findViewById(R.id.row_StudentsName);
            itemView.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View view)
                {
                    if(dialog!=null)
                    {
                        dialog.onClick(getLayoutPosition());
                    }
                }
            });
        }
    }
}
