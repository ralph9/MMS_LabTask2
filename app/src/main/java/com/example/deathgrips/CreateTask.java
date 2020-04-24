package com.example.deathgrips;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class CreateTask extends AppCompatActivity {
    EditText dateInputForPicker;
    DatePickerDialog datePickerDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_task);
        Button buttonAddTask = (Button) findViewById(R.id.buttonAddTaskInstant);
        dateInputForPicker = (EditText) findViewById(R.id.inputDate) ;

        //we add a new listener for the date to pop up on click
        dateInputForPicker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // calendar class's instance and get current date , month and year from calendar
                final Calendar c = Calendar.getInstance();
                int mYear = c.get(Calendar.YEAR); // current year
                int mMonth = c.get(Calendar.MONTH); // current month
                int mDay = c.get(Calendar.DAY_OF_MONTH); // current day
                // date picker dialog
                datePickerDialog = new DatePickerDialog(CreateTask.this,
                        new DatePickerDialog.OnDateSetListener() {

                            @SuppressLint("SetTextI18n")
                            @Override
                            public void onDateSet(DatePicker view, int year,
                                                  int monthOfYear, int dayOfMonth) {
                                // set day of month , month and year value in the edit text
                                dateInputForPicker.setText(dayOfMonth + "/"
                                        + (monthOfYear + 1) + "/" + year);

                            }
                        }, mYear, mMonth, mDay);
                datePickerDialog.show();
            }
        });

        buttonAddTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TextView textViewTitle = (TextView) findViewById(R.id.inputTitle);
                String inputTitle = "";
                String inputDescription = "";
                String inputDate = "";
                String typeSelected = "";
                long dateAsLong = 0;

                //Retrieval of the data from the input to conform the task object
                if(textViewTitle.getText() != null){
                    inputTitle = textViewTitle.getText().toString();
                }

                TextView textViewDescription = (TextView) findViewById(R.id.inputDescription);
                if(textViewDescription.getText() != null) {
                   inputDescription = textViewDescription.getText().toString();
                }

                TextView textViewDate = (TextView) findViewById(R.id.inputDate);
                if(textViewDate.getText() != null) {
                    inputDate = textViewDate.getText().toString();
                }

                //Casting the string into the Date format so it can be added to the Task
                try {
                    @SuppressLint("SimpleDateFormat") SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                    Date date = sdf.parse(inputDate);

                    assert date != null;
                    dateAsLong = date.getTime();

                } catch (ParseException e) {
                    e.printStackTrace();
                }

                //We determine the type of task that it is, in order to assign the correct icon
                //and enum type
                RadioGroup radioButtonTaskType = (RadioGroup) findViewById(R.id.groupRBTypeTask);
                int taskTypeSelectedId = radioButtonTaskType.getCheckedRadioButtonId();
                RadioButton radioButtonType = (RadioButton) findViewById(taskTypeSelectedId);
                if(radioButtonType.getText() != null) {
                   typeSelected = (String) radioButtonType.getText();
                }
                TaskType typeSelectedEnum = TaskType.TODO;
                switch(typeSelected.toUpperCase()){
                    case "TODO": {
                        //set icon to assigment
                       typeSelectedEnum = TaskType.TODO;
                        break;
                    }
                    case "EMAIL":{
                        //type of task email
                        typeSelectedEnum = TaskType.EMAIL;
                        break;
                    }
                    case "PHONE":{
                        //set icon to phone
                        typeSelectedEnum = TaskType.PHONE;
                        break;
                    }
                    case "MEETING":{
                        //set icon to meeting
                        typeSelectedEnum = TaskType.MEETING;
                        break;
                    }
                }

                //We check for any empty fields, and if we're missing data we let the user know
                //Otherwise we add to the list a new task with the data provided by the user
                if(!inputTitle.equals("") | !inputDescription.equals("") | !inputDate.equals("")){
                    //Toast.makeText(getApplicationContext(), "You clicked " + inputTitle + " " + inputDescription + " " + inputDate + " " + typeSelected, Toast.LENGTH_SHORT).show();
                    //Add task to model
                    Task taskToBeAdded = new Task(inputTitle,inputDescription, new Date(dateAsLong), typeSelectedEnum);
                    MainActivity.addTaskToList(taskToBeAdded);
                    System.out.println("Task added");

                    //We go back to the main activity after the new task has been added
                    finish();
                }else{
                    Toast.makeText(getApplicationContext(), "An error occurred. Please fill all the data before adding a task", Toast.LENGTH_SHORT).show();
                }
            }
        });




    }
}
