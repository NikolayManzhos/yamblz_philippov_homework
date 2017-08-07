package com.example.weather.presentation.main.home_screen;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.weather.R;
import com.example.weather.domain.models.CurrentWeatherModel;
import com.example.weather.presentation.di.ActivityContext;
import com.example.weather.presentation.di.scope.PerFragment;
import com.example.weather.presentation.main.home_screen.vh.FutureViewHolder;
import com.example.weather.presentation.main.home_screen.vh.TodayViewHolder;
import com.example.weather.presentation.main.home_screen.view_model.WeatherViewModel;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;



@PerFragment
public class HomeAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context context;
    private List<WeatherViewModel> items;

    private final int TODAY =0, FUTURE = 1;
    private boolean useTodayLayout;

    @Inject
    HomeAdapter(@ActivityContext Context context) {
        this.context = context;
        items = new ArrayList<>();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder viewHolder;
        LayoutInflater inflater = LayoutInflater.from(context);

        switch (viewType) {
            case TODAY:
                View vToday = inflater.inflate(R.layout.item_forecast_today, parent, false);
                viewHolder = new TodayViewHolder(vToday);
                break;
            case FUTURE:
                View vFuture = inflater.inflate(R.layout.item_forecast_future, parent, false);
                viewHolder = new FutureViewHolder(vFuture);
                break;
            default:
                View vTodayDefault = inflater.inflate(R.layout.item_forecast_today, parent, false);
                viewHolder = new TodayViewHolder(vTodayDefault);
                break;
        }
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        int aPosition = holder.getAdapterPosition();
        switch (holder.getItemViewType()) {
            case TODAY:
                TodayViewHolder todayViewHolder = (TodayViewHolder) holder;
                configureTodayViewHolder(todayViewHolder, aPosition);
                break;
            case FUTURE:
                FutureViewHolder futureViewHolder = (FutureViewHolder) holder;
                configureFutureViewHolder(futureViewHolder, aPosition);
                break;
        }
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0 && useTodayLayout) {
            return TODAY;
        } else {
            return FUTURE;
        }
    }

    void setUseTodayLayout(boolean useTodayLayout) {
        this.useTodayLayout = useTodayLayout;
    }

    public void setData(List<WeatherViewModel> items) {
        this.items.clear();
        this.items.addAll(items);
        notifyDataSetChanged();
    }

    private void configureTodayViewHolder(TodayViewHolder vh, int aPosition) {
        WeatherViewModel currentWeather = items.get(aPosition);
        vh.icon.setImageResource(currentWeather.getIconId());
        vh.condition.setText(currentWeather.getCondition());
        vh.temperatureDay.setText(String.valueOf(currentWeather.getTemperature()));
        vh.temperatureNight.setText(String.valueOf(currentWeather.getTemperatureNight()));
    }

    private void configureFutureViewHolder(FutureViewHolder vh, int aPosition) {
        WeatherViewModel currentWeather = items.get(aPosition);
        vh.icon.setImageResource(currentWeather.getIconId());
        vh.condition.setText(currentWeather.getCondition());
        vh.temperatureDay.setText(String.valueOf(currentWeather.getTemperature()));
        vh.temperatureNight.setText(String.valueOf(currentWeather.getTemperatureNight()));
    }
}
