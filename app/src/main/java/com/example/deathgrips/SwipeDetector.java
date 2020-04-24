package com.example.deathgrips;

import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.List;

public class SwipeDetector implements View.OnTouchListener {
    TextView taskToAlter = null;
    List<Task> listOfTasks = null;
    RecyclerViewAdapter recyclerView = null;

    //Constructor that takes the list of tasks and a RecyclerViewAdapter for updating
    public SwipeDetector(TextView myTextView, List<Task> mData, RecyclerViewAdapter recyclerAdapter) {
        this.taskToAlter = myTextView;
        this.listOfTasks = mData;
        this.recyclerView = recyclerAdapter;
    }

  //Enum with the two swipes we're interested in
    public static enum Action {
        LR, // Left to Right
        RL, // Right to Left
        None // when no action was detected
    }

    private static final String logTag = "Swipe";
    private static final int MIN_DISTANCE = 100;
    private float downX, downY, upX, upY;
    private Action mSwipeDetected = Action.None;

    public boolean swipeDetected() {
        return mSwipeDetected != Action.None;
    }

    public Action getAction() {
        return mSwipeDetected;
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                downX = event.getX();
                downY = event.getY();
                mSwipeDetected = Action.None;
                return false;

            case MotionEvent.ACTION_MOVE:
                upX = event.getX();
                upY = event.getY();

                float deltaX = downX - upX;
                float deltaY = downY - upY;


                //When we detect a swipe to the right we delete the task where it happened from
                //the list and we refresh the recyclerView so it registers the changes
                if (deltaY>0 && deltaY<10 && deltaX<0 || deltaY==0 && deltaX>-15 && deltaX<0){
                    System.out.println("a swipe to the right has happened");
                    TextView taskView = (TextView) v.findViewById(R.id.taskItem);
                    String taskViewText = (String) taskView.getText();
                    for(int i = 0; i < listOfTasks.size(); i++){
                        if(listOfTasks.get(i).title.equals(taskViewText)){
                            listOfTasks.remove(i);
                            recyclerView.notifyDataSetChanged();
                        }
                    }
                    return true;
                }

                //When we detect a swipe to the left we access the required task and we mark it as
                //done
                if (deltaY>=0 && deltaY<10 && deltaX>0 || deltaY<0 && deltaX>15 && deltaX<40){
                    System.out.println("a swipe to the left has happened");
                TextView taskView = (TextView) v.findViewById(R.id.taskItem);
                String taskViewText = (String) taskView.getText();
                    for(int i = 0; i < listOfTasks.size(); i++){
                        if(listOfTasks.get(i).title.equals(taskViewText)){
                            listOfTasks.get(i).isDone = true;
                        }
                    }
                    return true;
            }

                if (Math.abs(deltaX) > MIN_DISTANCE) {
                    // left or right
                    if (deltaX < 0) {
                        mSwipeDetected = Action.LR;
                        return false;
                    }
                    if (deltaX > 0) {
                        mSwipeDetected = Action.RL;
                        return false;
                    }
                }
                return true;
        }
        return false;
    }
}