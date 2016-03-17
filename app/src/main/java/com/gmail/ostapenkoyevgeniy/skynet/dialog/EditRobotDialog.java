package com.gmail.ostapenkoyevgeniy.skynet.dialog;

import android.app.DialogFragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.Toast;

import com.gmail.ostapenkoyevgeniy.skynet.R;
import com.gmail.ostapenkoyevgeniy.skynet.asynctask.EditRobotAsyncTask;
import com.gmail.ostapenkoyevgeniy.skynet.entity.Robot;

import static android.widget.Toast.LENGTH_LONG;

public class EditRobotDialog extends DialogFragment implements View.OnClickListener {
    private EditText robotName;
    private EditText robotYear;
    private Spinner robotType;

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        getDialog().setTitle(R.string.edit_robot);
        getDialog().getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        View v = inflater.inflate(R.layout.edit_robot_dialog, null);
        v.findViewById(R.id.edit_robot_btn_yes).setOnClickListener(this);
        v.findViewById(R.id.edit_robot_btn_no).setOnClickListener(this);
        robotName = (EditText) v.findViewById(R.id.edit_robot_name);
        robotYear = (EditText) v.findViewById(R.id.edit_robot_year);
        robotType = (Spinner) v.findViewById(R.id.edit_robot_type);

        robotName.setText(String.valueOf(getArguments().getCharArray("name")));
        robotYear.setText(String.valueOf(getArguments().getInt("year")));

        String type = String.valueOf(getArguments().getCharArray("type"));
        SpinnerAdapter adapter = robotType.getAdapter();
        for (int i = 0; i < adapter.getCount(); i++) {
            String tmp = (String) adapter.getItem(i);
            if (tmp.equals(type)) {
                robotType.setSelection(i);
            }
        }

        return v;
    }

    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.edit_robot_btn_yes:
                try {
                    String name = robotName.getText().toString();
                    String type = robotType.getSelectedItem().toString();
                    int year = Integer.valueOf(robotYear.getText().toString());

                    Robot robot = new Robot();
                    robot.setId(getArguments().getInt("id"));
                    robot.setName(name);
                    robot.setYear(year);
                    robot.setType(type);

                    EditRobotAsyncTask editRobot = new EditRobotAsyncTask(v.getContext(), getActivity().findViewById(R.id.rvAllRobots));
                    editRobot.execute(robot);

                    dismiss();
                } catch (NumberFormatException e) {
                    Toast.makeText(v.getContext(), "Некорректный год!", LENGTH_LONG).show();
                } catch (Exception e) {
                    Toast.makeText(v.getContext(), "Неизвестная ошибка! Im sorry :(", LENGTH_LONG).show();
                }
                break;
            case R.id.edit_robot_btn_no:
                dismiss();
                break;
        }
    }
}
