package com.cit.myapplication.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.cit.myapplication.R;
import com.cit.myapplication.databinding.ItemForecastBinding;
import com.cit.myapplication.model.forcastWeatherModel.ForecastWeatherModel;
import com.cit.myapplication.model.forcastWeatherModel.TotalForecast;
import com.cit.myapplication.viewHolder.ForecastViewHolder;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class Adapter extends RecyclerView.Adapter<ForecastViewHolder> {

    Context context;
    List<ForecastWeatherModel> forecastWeatherModelList;
    List<TotalForecast> totalForecastList;

    public Adapter(Context context, List<TotalForecast> totalForecastList) {
        this.context = context;
        this.totalForecastList = totalForecastList;
    }

    @NonNull
    @Override
    public ForecastViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_forecast,parent,false);
        return new ForecastViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ForecastViewHolder holder, int position) {

        TotalForecast forecast = totalForecastList.get(position);
            holder.temp.setText(""+forecast.getMain().getTemp()+" \u2103");
            holder.humidity.setText("Humidity: "+forecast.getMain().getHumidity());
            holder.feels.setText("Feels like: "+forecast.getMain().getFeelsLike());

            //Date date = new Date(forecast.getDt());
            //DateFormat dateFormat = new SimpleDateFormat("dd MMM yyyy hh:mm a");
            holder.date.setText(forecast.getDtTxt().substring(0,10));
            holder.time.setText(forecast.getDtTxt().substring(11,19));

            Glide.with(context).load("http://openweathermap.org/img/wn/"+forecast.getWeather().get(0).getIcon()+"@2x.png").into(holder.icon);
        }





    @Override
    public int getItemCount() {
        return totalForecastList.size();
    }
}
