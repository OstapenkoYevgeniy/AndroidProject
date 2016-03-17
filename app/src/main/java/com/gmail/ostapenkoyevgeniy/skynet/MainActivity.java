package com.gmail.ostapenkoyevgeniy.skynet;

import android.app.DialogFragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.gmail.ostapenkoyevgeniy.skynet.dialog.AddRobotDialog;

public class MainActivity extends AppCompatActivity {
    private EditText etSearch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etSearch = (EditText) findViewById(R.id.search_robot);
    }

    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.add_robot:
                DialogFragment dlgAddRobot;
                dlgAddRobot = new AddRobotDialog();
                dlgAddRobot.show(getFragmentManager(), "dlgAddRobot");
                break;
            case R.id.get_robots:
                Intent allRobots = new Intent(MainActivity.this, AllRobotActivity.class);
                startActivity(allRobots);
                break;
            case R.id.btn_search_robot:
                String value = etSearch.getText().toString();
                if (value.replaceAll(" ", "").equals("")) {
                    Toast.makeText(this, "Введите услвие поиска!", Toast.LENGTH_LONG).show();
                    break;
                }
                Intent showRobots = new Intent(MainActivity.this, AllRobotActivity.class);
                try {
                    int id = Integer.valueOf(value);
                    showRobots.putExtra("findById", id);
                    startActivity(showRobots);
                } catch (Exception e) {
                    showRobots.putExtra("findByName", value);
                    startActivity(showRobots);
                }
                break;
            default:
                break;
        }
    }
}
