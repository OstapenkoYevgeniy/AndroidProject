package com.gmail.ostapenkoyevgeniy.skynet;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.gmail.ostapenkoyevgeniy.skynet.adapter.RobotAdapter;
import com.gmail.ostapenkoyevgeniy.skynet.asynctask.FindByIdAsyncTask;
import com.gmail.ostapenkoyevgeniy.skynet.asynctask.FindByNameAsyncTask;
import com.gmail.ostapenkoyevgeniy.skynet.asynctask.GetAllRobotsAsyncTask;
import com.gmail.ostapenkoyevgeniy.skynet.entity.Robot;

import java.util.List;

public class AllRobotActivity extends AppCompatActivity {
    private RecyclerView mRecyclerView;
    private RecyclerView.LayoutManager mLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_robot);

        mRecyclerView = (RecyclerView) findViewById(R.id.rvAllRobots);
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        Intent intent = getIntent();
        int findById = intent.getIntExtra("findById", -1);
        String findByName = intent.getStringExtra("findByName");

        if (findById != -1) {
            FindByIdAsyncTask getRobots = new FindByIdAsyncTask(this);
            getRobots.execute(findById);
        } else if (findByName != null) {
            FindByNameAsyncTask getRobots = new FindByNameAsyncTask(this);
            getRobots.execute(findByName);
        } else {
            GetAllRobotsAsyncTask getRobots = new GetAllRobotsAsyncTask(this);
            getRobots.execute();
        }
    }

    public void addRobots(List<Robot> robots) {
        if (robots != null && robots.get(0) != null) {
            mRecyclerView.setAdapter(new RobotAdapter(this, mRecyclerView, robots));
        } else {
            Toast.makeText(this, "Ничего нет :(", Toast.LENGTH_LONG).show();
        }
    }
}
