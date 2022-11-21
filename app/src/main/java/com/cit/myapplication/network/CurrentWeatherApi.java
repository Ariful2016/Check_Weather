package com.cit.myapplication.network;

import static com.cit.myapplication.utils.Constants.WEATHER_API_KEY;

import com.cit.myapplication.model.forcastWeatherModel.ForecastWeatherModel;
import com.cit.myapplication.model.weatherModel.CurrentWeatherModel;

import retrofit2.Call;
import retrofit2.http.GET;

public interface CurrentWeatherApi {

    @GET("weather?q=Dhaka&units=metric&appid="+WEATHER_API_KEY)
    Call<CurrentWeatherModel> getCurrentWeather();

    @GET("forecast?q=Dhaka&units=metric&appid="+WEATHER_API_KEY)
    Call<ForecastWeatherModel> getForecastWeather();
}
