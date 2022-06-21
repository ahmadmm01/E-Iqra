package com.pam.e_iqra.ViewHolder;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.pam.e_iqra.R;
import com.pam.e_iqra.Interface.ItemClickListener;

public class StudentsViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener
{
    public TextView txtStudentsId, txtStudentsName;
    public ItemClickListener listener;

    public StudentsViewHolder(@NonNull View itemView)
    {
        super(itemView);

        txtStudentsId = (TextView) itemView.findViewById(R.id.row_StudentsStudentsId);
        txtStudentsName = (TextView) itemView.findViewById(R.id.row_StudentsStudentsName);
    }

    public void setItemClickListener(ItemClickListener listener)
    {
        this.listener = listener;
    }

    @Override
    public void onClick(View v)
    {
        listener.onClick(v, getAdapterPosition() ,false);
    }
}
