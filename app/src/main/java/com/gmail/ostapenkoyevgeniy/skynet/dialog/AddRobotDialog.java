package com.gmail.ostapenkoyevgeniy.skynet.dialog;

import android.app.DialogFragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.gmail.ostapenkoyevgeniy.skynet.R;
import com.gmail.ostapenkoyevgeniy.skynet.asynctask.AddRobotAsyncTask;
import com.gmail.ostapenkoyevgeniy.skynet.entity.Robot;

public class AddRobotDialog extends DialogFragment implements OnClickListener {
    private EditText robotName;
    private EditText robotYear;
    private Spinner robotType;

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        getDialog().setTitle(R.string.add_robot);
        getDialog().getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        View v = inflater.inflate(R.layout.add_robot_dialog, null);
        v.findViewById(R.id.add_robot_btn_yes).setOnClickListener(this);
        v.findViewById(R.id.add_robot_btn_no).setOnClickListener(this);
        robotName = (EditText) v.findViewById(R.id.add_robot_name);
        robotYear = (EditText) v.findViewById(R.id.add_robot_year);
        robotType = (Spinner) v.findViewById(R.id.add_robot_type);

        return v;
    }

    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.add_robot_btn_yes:
                try {
                    String name = robotName.getText().toString();
                    String type = robotType.getSelectedItem().toString();
                    int year = Integer.valueOf(robotYear.getText().toString());

                    Robot robot = new Robot();
                    robot.setName(name);
                    robot.setYear(year);
                    robot.setType(type);

                    AddRobotAsyncTask addRobot = new AddRobotAsyncTask(v.getContext());
                    addRobot.execute(robot);

                    dismiss();
                } catch (NumberFormatException e) {
                    Toast.makeText(v.getContext(), "Некорректный год!", Toast.LENGTH_LONG).show();
                } catch (Exception e) {
                    Toast.makeText(v.getContext(), "Неизвестная ошибка! Im sorry :(", Toast.LENGTH_LONG).show();
                }
                break;
            case R.id.add_robot_btn_no:
                dismiss();
                break;
        }
    }
}

