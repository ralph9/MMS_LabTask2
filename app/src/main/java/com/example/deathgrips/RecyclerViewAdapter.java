package com.example.deathgrips;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>{


    private List<Task> mData;
    private LayoutInflater mInflater;
    private ItemClickListener mClickListener;
    private RecyclerViewAdapter recyclerAdapter = this;

    // data is passed into the constructor
    RecyclerViewAdapter(Context context, List<Task> data) {
        this.mInflater = LayoutInflater.from(context);
        this.mData = data;
    }


    // inflates the row layout from xml when needed
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.recyclerview_row, parent, false);
        return new ViewHolder(view);
    }


    // binds the data to the TextView in each row
    @SuppressLint("ClickableViewAccessibility")
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        String taskDataTitle = mData.get(position).getTitle();
        TaskType taskType = mData.get(position).getTypeOfTask();
        holder.myTextView.setText(taskDataTitle);
        Drawable img = null;
        switch(taskType){
            case TODO: {
                //set icon to assigment
                img = holder.myTextView.getContext().getResources().getDrawable(R.drawable.ic_task_todo_foreground);

                break;
            }
            case EMAIL:{
                img = holder.myTextView.getContext().getResources().getDrawable(R.drawable.ic_mail_task_foreground);
                break;
            }
            case PHONE:{
                //set icon to phone
                img = holder.myTextView.getContext().getResources().getDrawable(R.drawable.ic_task_phone_foreground);
                break;
            }
            case MEETING:{
                //set icon to meeting
                img = holder.myTextView.getContext().getResources().getDrawable(R.drawable.ic_task_meeting_foreground);
                break;
            }
        }
        img.setBounds(0, 0, img.getMinimumWidth(), img.getMinimumHeight());
        holder.myTextView.setCompoundDrawables(img, null, null, null);
    }


    // total number of rows
    @Override
    public int getItemCount() {
        return mData.size();
    }


    // stores and recycles views as they are scrolled off screen
    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView myTextView;

        @SuppressLint("ClickableViewAccessibility")
        ViewHolder(View itemView) {
            super(itemView);
            myTextView = itemView.findViewById(R.id.taskItem);
            SwipeDetector swipeDetector = new SwipeDetector(myTextView,mData,recyclerAdapter);
            itemView.setOnTouchListener(swipeDetector);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if (mClickListener != null) mClickListener.onItemClick(view, getAdapterPosition());
        }
    }

    // convenience method for getting data at click position
    Task getItem(int id) {
        return mData.get(id);
    }

    // allows clicks events to be caught
    void setClickListener(ItemClickListener itemClickListener) {
        this.mClickListener = itemClickListener;
    }

    // parent activity will implement this method to respond to click events
    public interface ItemClickListener {
        void onItemClick(View view, int position);
    }


}
