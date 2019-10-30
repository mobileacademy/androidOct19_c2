package com.e.navdrawerapp;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class StudentAdapter extends BaseAdapter {

    private List<Student> students = new ArrayList<>();
    private static final String TAG = "StudentAdapter";

    public StudentAdapter(List<Student> dataSource) {
        students.addAll(dataSource);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Log.d(TAG, "getView for position=" + position);

        // get current student
        Student currentStudent = students.get(position);
        ViewHolder viewHolder;
        if (convertView == null) {
            Log.d(TAG, "view is null at position=" + position + " inflate layout for row");
            // inflate each list row layout
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_layout, parent, false);
            viewHolder = new ViewHolder();

            viewHolder.studentImageView = convertView.findViewById(R.id.studentPictureImageView);
            viewHolder.studentNameTextView = convertView.findViewById(R.id.nameTextView);
            viewHolder.studentUnivTextView = convertView.findViewById(R.id.universityTextView);

            convertView.setTag(viewHolder);
        } else {
            Log.d(TAG, "view is NOT null at position=" + position + " re-use views");
            // re - use the viewHolder
            viewHolder = (ViewHolder) convertView.getTag();
        }

        viewHolder.studentNameTextView.setText(currentStudent.getName());
        viewHolder.studentUnivTextView.setText(currentStudent.getUniv());

        return convertView;
    }

    @Override
    public int getCount() {
        return students.size();
    }

    @Override
    public Student getItem(int position) {
        return students.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    class ViewHolder {
        ImageView studentImageView;
        TextView studentNameTextView;
        TextView studentUnivTextView;
    }
}
