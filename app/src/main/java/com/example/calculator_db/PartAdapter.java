package com.example.calculator_db;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class PartAdapter extends ArrayAdapter<Part> {

    public PartAdapter(Context context, int resource, List<Part> partList) {
        super(context, resource, partList);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Part part = getItem(position);
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.activity_history_adapter, null);
        }
        TextView partView = convertView.findViewById(R.id.partName);
        TextView answerView = convertView.findViewById(R.id.answerName);
        partView.setText(part.getRequest());
        answerView.setText(part.getAnswer());
        return convertView;
    }
}
