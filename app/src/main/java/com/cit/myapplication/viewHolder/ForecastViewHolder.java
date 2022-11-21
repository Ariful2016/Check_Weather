package com.cit.myapplication.viewHolder;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.cit.myapplication.R;
import com.cit.myapplication.databinding.ItemForecastBinding;

public class ForecastViewHolder extends RecyclerView.ViewHolder {

    public ImageView icon;
    public TextView date,temp,feels,humidity,time;
     public ForecastViewHolder(@NonNull View view) {
        super(view);

        icon = view.findViewById(R.id.icon);
        date = view.findViewById(R.id.date);
        temp = view.findViewById(R.id.temp);
        feels = view.findViewById(R.id.feels);
        humidity = view.findViewById(R.id.humidity);
        time = view.findViewById(R.id.time);

    }
}
