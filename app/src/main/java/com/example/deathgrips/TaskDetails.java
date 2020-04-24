package com.example.deathgrips;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.media.Image;
import android.os.Build;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import java.time.LocalDate;
import java.util.Date;

public class TaskDetails extends AppCompatActivity {



    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_details);
        Bundle extrasDetailsTask = getIntent().getExtras();

        //We retrieve the extras passed from MainActivity in order to fill the inputs with the
        //details for the selected task
        if (extrasDetailsTask != null) {
            String titleText = extrasDetailsTask.getString("title");
            String description = extrasDetailsTask.getString("description");
            boolean status = extrasDetailsTask.getBoolean("status");
            TaskType taskType = (TaskType) getIntent().getSerializableExtra("typeOfTask");
            Date dateTrueFormat = (Date) extrasDetailsTask.getSerializable("dueDate");
            System.out.println("Details for this task:");
            System.out.println(titleText + " " + description + " " + String.valueOf(status) + " " + String.valueOf(dateTrueFormat) + " " + String.valueOf(taskType));
            TextView titleToFill = (TextView) findViewById(R.id.textTitle);
            titleToFill.setText(titleText);

            TextView descriptionToFill = (TextView) findViewById(R.id.textDescription);
            descriptionToFill.setText(description);

            //We fill the data for the status of the task
            TextView statusToFill = (TextView) findViewById(R.id.textStatus);
            if(status){
                statusToFill.setText("This task has been completed");
            }else{
                statusToFill.setText("This task has not yet been completed");
            }

            //We fill the data for the date of the selected task
            TextView dateToFill = (TextView) findViewById(R.id.textDate);
            dateToFill.setText(String.valueOf(dateTrueFormat));

            //We set the icon for the task at hand based on its type of task
            ImageView iconOfTask = findViewById(R.id.iconDetails);
            switch(taskType){
                case TODO: {
                    //set icon to assigment
                    iconOfTask.setImageResource(R.drawable.ic_task_todo_foreground);

                    break;
                }
                case EMAIL:{
                    iconOfTask.setImageResource(R.drawable.ic_mail_task_foreground);
                    break;
                }
                case PHONE:{
                    //set icon to phone
                    iconOfTask.setImageResource(R.drawable.ic_task_phone_foreground);
                    break;
                }
                case MEETING:{
                    //set icon to meeting
                    iconOfTask.setImageResource(R.drawable.ic_task_meeting_foreground);
                    break;
                }
            }

        }
    }
}
