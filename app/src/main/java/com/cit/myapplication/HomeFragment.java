package com.cit.myapplication;

import android.icu.text.DateFormat;
import android.icu.text.SimpleDateFormat;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.cit.myapplication.adapter.Adapter;
import com.cit.myapplication.databinding.FragmentHomeBinding;
import com.cit.myapplication.model.forcastWeatherModel.ForecastWeatherModel;
import com.cit.myapplication.model.forcastWeatherModel.TotalForecast;
import com.cit.myapplication.model.weatherModel.CurrentWeatherModel;
import com.cit.myapplication.network.CurrentWeatherApi;
import com.cit.myapplication.network.WeatherRetrofit;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class HomeFragment extends Fragment {


    FragmentHomeBinding binding;

    List<TotalForecast> totalForecastList;
    List<ForecastWeatherModel> forecastWeatherModelList;

    public HomeFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentHomeBinding.inflate(inflater);

        totalForecastList = new ArrayList<>();
        forecastWeatherModelList = new ArrayList<>();

        CurrentWeatherApi currentWeatherApi = WeatherRetrofit.getRetrofit().create(CurrentWeatherApi.class);

        Call<CurrentWeatherModel> currentWeatherModelCall = currentWeatherApi.getCurrentWeather();

        currentWeatherModelCall.enqueue(new Callback<CurrentWeatherModel>() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onResponse(Call<CurrentWeatherModel> call, Response<CurrentWeatherModel> response) {

                if (response.isSuccessful()){

                    CurrentWeatherModel currentWeatherModel = response.body();

                    long time = System.currentTimeMillis();

                    Date date = new Date(time);

                    DateFormat dateFormat = new SimpleDateFormat("dd MMM yyyy");
                    binding.date.setText(dateFormat.format(date));
                    binding.temp.setText(""+currentWeatherModel.getMain().getTemp()+" \u2103" );
                    binding.city.setText(currentWeatherModel.getName());
                    binding.feels.setText("Feels like: "+currentWeatherModel.getMain().getFeelsLike());
                    binding.humidity.setText("Humidity: "+currentWeatherModel.getMain().getHumidity());

                    Glide.with(requireActivity()).load("http://openweathermap.org/img/wn/"+currentWeatherModel.getWeather().get(0).getIcon()+"@2x.png").into(binding.img);

                    //Log.i("TAG", "onResponse: "+response.body().getId());
                }
            }

            @Override
            public void onFailure(Call<CurrentWeatherModel> call, Throwable t) {

            }
        });

        Call<ForecastWeatherModel> forecastWeatherModelCall = currentWeatherApi.getForecastWeather();

        forecastWeatherModelCall.enqueue(new Callback<ForecastWeatherModel>() {
            @Override
            public void onResponse(@NonNull Call<ForecastWeatherModel> call, @NonNull Response<ForecastWeatherModel> response) {
                if (response.isSuccessful()){
                    assert response.body() != null;
                    Log.i("TAG", "onResponse: "+""+response.body().getList().size());

                    //forecastWeatherModelList = response.body().getList();

                    totalForecastList = response.body().getList();


                    Adapter adapter = new Adapter(requireActivity(),totalForecastList);

                    binding.recycler.setAdapter(adapter);
                }
            }

            @Override
            public void onFailure(Call<ForecastWeatherModel> call, Throwable t) {


                Log.i("TAG", "onFailure: "+t.getLocalizedMessage());
            }
        });

        return binding.getRoot();





    }
}