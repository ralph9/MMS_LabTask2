package com.example.deathgrips;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GestureDetectorCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;
import android.view.GestureDetector;
import android.view.MotionEvent;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class MainActivity extends AppCompatActivity implements RecyclerViewAdapter.ItemClickListener{

    static RecyclerViewAdapter adapter;
    static ArrayList<Task> tasksList = new ArrayList<>();
    // This is the gesture detector compat instance.

    @SuppressLint("ClickableViewAccessibility")
    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FloatingActionButton fab = findViewById(R.id.addTaskButton);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent taskDetailsActivity = new Intent(getApplicationContext(),CreateTask.class);
                startActivity(taskDetailsActivity);
            }
        });



        // data to populate the RecyclerView with
        Task task1 = new Task("Task1","something1", Calendar.getInstance().getTime(),true, TaskType.MEETING);
        tasksList.add(task1);

        Task task2 = new Task("Task2","something1", Calendar.getInstance().getTime(),true, TaskType.EMAIL);
        tasksList.add(task2);

        Task task3 = new Task("Task3","something1", Calendar.getInstance().getTime(),true, TaskType.MEETING);
        tasksList.add(task3);

        Task task4 = new Task("Task4","something1", Calendar.getInstance().getTime(),true, TaskType.PHONE);
        tasksList.add(task4);

        Task task5 = new Task("Task5","something1", Calendar.getInstance().getTime(),true, TaskType.TODO);
        tasksList.add(task5);

        Task task6 = new Task("Task6","something1", Calendar.getInstance().getTime(),true, TaskType.TODO);
        tasksList.add(task6);

        Task task7 = new Task("Task7","something1", Calendar.getInstance().getTime(),true, TaskType.TODO);
        tasksList.add(task7);

        Task task8 = new Task("Task8","something1", Calendar.getInstance().getTime(),true, TaskType.TODO);
        tasksList.add(task8);

        Task task9 = new Task("Task9","something1", Calendar.getInstance().getTime(),true, TaskType.TODO);
        tasksList.add(task9);

        Task task10 = new Task("Task10","something1", Calendar.getInstance().getTime(),true, TaskType.TODO);
        tasksList.add(task10);

        Task task11 = new Task("Task11","something1", Calendar.getInstance().getTime(),false, TaskType.TODO);
        tasksList.add(task11);

        //Set up the RecyclerView
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.rvTasks);

        LinearLayoutManager layoutManager = new LinearLayoutManager(recyclerView.getContext());
        recyclerView.setLayoutManager(layoutManager);

        //Adapter specification
        adapter = new RecyclerViewAdapter(this, tasksList);
        adapter.setClickListener(this);
        recyclerView.setAdapter(adapter);

    }

    //Method to add a task to the list and call to update the changes on the recyclerViewAdapter
    static void addTaskToList(Task taskToAdd){
        tasksList.add(taskToAdd);
        adapter.notifyDataSetChanged();
    }

    //Method to send the details to the Intent about the task selected, so they can be displayed
    //in the activity
    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onItemClick(View view, int position) {
        if (position < tasksList.size()) {
            Intent taskDetailsActivity = new Intent(this, TaskDetails.class);
            taskDetailsActivity.putExtra("title", adapter.getItem(position).title);
            taskDetailsActivity.putExtra("description", adapter.getItem(position).description);
            taskDetailsActivity.putExtra("status", adapter.getItem(position).isDone);
            Date dateFromTask = (adapter.getItem(position).dueDate);
            taskDetailsActivity.putExtra("dueDate", dateFromTask);
            taskDetailsActivity.putExtra("typeOfTask", adapter.getItem(position).typeOfTask);
            startActivity(taskDetailsActivity);
        }
    }

}
