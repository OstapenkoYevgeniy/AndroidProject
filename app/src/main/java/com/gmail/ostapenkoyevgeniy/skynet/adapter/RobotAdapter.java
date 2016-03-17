package com.gmail.ostapenkoyevgeniy.skynet.adapter;

import android.app.DialogFragment;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import com.gmail.ostapenkoyevgeniy.skynet.AllRobotActivity;
import com.gmail.ostapenkoyevgeniy.skynet.R;
import com.gmail.ostapenkoyevgeniy.skynet.asynctask.DeleteRobotAsyncTask;
import com.gmail.ostapenkoyevgeniy.skynet.dialog.EditRobotDialog;
import com.gmail.ostapenkoyevgeniy.skynet.entity.Robot;

import java.util.List;

public class RobotAdapter extends RecyclerView.Adapter<RobotAdapter.RobotViewHolder> {
    public RobotViewHolder robotViewHolder;
    private RecyclerView recyclerView;
    private Context context;
    private List<Robot> robots;

    public RobotAdapter(Context context) {
        this.context = context;
    }

    public RobotAdapter(Context context, RecyclerView recyclerView, List<Robot> robots) {
        this.recyclerView = recyclerView;
        this.robots = robots;
        this.context = context;
    }

    @Override
    public RobotViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recycler_item, parent, false);
        robotViewHolder = new RobotViewHolder(context, recyclerView, v);
        RobotAdapter.RobotViewHolder vh = robotViewHolder;
        return vh;
    }

    @Override
    public void onBindViewHolder(RobotViewHolder holder, int position) {
        holder.robotId.setText(String.valueOf(robots.get(position).getId()));
        holder.robotName.setText(robots.get(position).getName());
        holder.robotType.setText(robots.get(position).getType());
        holder.robotYear.setText(String.valueOf(robots.get(position).getYear()));
    }

    @Override
    public long getItemId(int position) {
        return super.getItemId(position);
    }

    @Override
    public int getItemCount() {
        return robots.size();
    }

    public class RobotViewHolder extends RecyclerView.ViewHolder {
        TextView robotId;
        TextView robotName;
        TextView robotType;
        TextView robotYear;
        ImageButton ibtnEdit;
        ImageButton ibntDelete;

        public RobotViewHolder(Context context, RecyclerView recyclerView, View itemView) {
            super(itemView);
            robotId = (TextView) itemView.findViewById(R.id.tv_recycler_item_id);
            robotName = (TextView) itemView.findViewById(R.id.tv_recycler_item_name);
            robotType = (TextView) itemView.findViewById(R.id.tv_recycler_item_type);
            robotYear = (TextView) itemView.findViewById(R.id.tv_recycler_item_year);
            ibtnEdit = (ImageButton) itemView.findViewById(R.id.btn_robot_edit);
            ibntDelete = (ImageButton) itemView.findViewById(R.id.btn_robot_delete);

            MyOnClickListener onClickListener = new MyOnClickListener(context, recyclerView, this);
            ibtnEdit.setOnClickListener(onClickListener);
            ibntDelete.setOnClickListener(onClickListener);
        }

        public void removeAt(int position) {
            robots.remove(position);
            notifyItemRemoved(position);
            notifyItemRangeChanged(position, robots.size());
        }

        public void update(int position, Robot robot) {
            robots.remove(position);
            robots.add(position, robot);
            notifyItemChanged(position);
        }
    }

    private class MyOnClickListener implements View.OnClickListener {
        private RobotViewHolder robotViewHolder;
        private RecyclerView recyclerView;
        private AllRobotActivity context;

        public MyOnClickListener(Context context, RecyclerView recyclerView, RobotViewHolder robotViewHolder) {
            this.recyclerView = recyclerView;
            this.robotViewHolder = robotViewHolder;
            this.context = (AllRobotActivity) context;
        }

        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.btn_robot_delete:
                    DeleteRobotAsyncTask delete = new DeleteRobotAsyncTask(recyclerView.getContext(), robotViewHolder);
                    delete.execute(Integer.valueOf(robotViewHolder.robotId.getText().toString()));
                    break;
                case R.id.btn_robot_edit:
                    DialogFragment editRobotDialog = new EditRobotDialog();

                    Bundle bundle = new Bundle();
                    bundle.putInt("id", Integer.valueOf(robotViewHolder.robotId.getText().toString()));
                    bundle.putCharArray("name", robotViewHolder.robotName.getText().toString().toCharArray());
                    bundle.putCharArray("type", robotViewHolder.robotType.getText().toString().toCharArray());
                    bundle.putInt("year", Integer.valueOf(robotViewHolder.robotYear.getText().toString()));

                    editRobotDialog.setArguments(bundle);
                    editRobotDialog.show(context.getFragmentManager(), "dlgAddRobot");
                    break;
            }
        }
    }
}

