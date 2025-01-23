package com.example.weatherapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class HourlyAdapter extends RecyclerView.Adapter<HourlyAdapter.ViewHolder> {
    private List<WeatherHour> items;

    public HourlyAdapter(List<WeatherHour> items) {
        this.items = items;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_hourly_weather, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        WeatherHour item = items.get(position);
        holder.tvTime.setText(item.getTime());
        holder.tvTemp.setText(String.format("%.1f°C", item.getTemp()));
        holder.tvDesc.setText(item.getWeatherDesc());
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public void updateData(List<WeatherHour> newItems) {
        this.items = newItems;
        notifyDataSetChanged();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvTime;
        TextView tvTemp;
        TextView tvDesc;

        ViewHolder(View view) {
            super(view);
            tvTime = view.findViewById(R.id.tvTime);
            tvTemp = view.findViewById(R.id.tvTemp);
            tvDesc = view.findViewById(R.id.tvDesc);
        }
    }
}