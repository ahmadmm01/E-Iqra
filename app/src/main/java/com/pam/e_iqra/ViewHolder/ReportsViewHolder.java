package com.pam.e_iqra.ViewHolder;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.pam.e_iqra.R;
import com.pam.e_iqra.Interface.ItemClickListener;

public class ReportsViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener
{
    public TextView txtReportsId, txtStudentsId, txtIqraId, txtIqraValue;
    public ItemClickListener listener;

    public ReportsViewHolder(@NonNull View itemView)
    {
        super(itemView);
        txtReportsId = (TextView) itemView.findViewById(R.id.row_ReportsReportsId);
        txtStudentsId = (TextView) itemView.findViewById(R.id.row_ReportsStudentsId);
        txtIqraId = (TextView) itemView.findViewById(R.id.row_ReportsIqraId);
        txtIqraValue = (TextView) itemView.findViewById(R.id.row_ReportsIqraValue);
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
