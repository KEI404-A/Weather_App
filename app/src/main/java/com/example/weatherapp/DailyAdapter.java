package com.example.weatherapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class DailyAdapter extends RecyclerView.Adapter<DailyAdapter.ViewHolder> {
    private List<WeatherDay> items;

    public DailyAdapter(List<WeatherDay> items) {
        this.items = items;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_daily_weather, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        WeatherDay item = items.get(position);
        holder.tvDate.setText(item.getDate());
        holder.tvTemp.setText(String.format("%.1f°C - %.1f°C",
                item.getTempMin(), item.getTempMax()));
        holder.tvDesc.setText(item.getWeatherDesc());
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public void updateData(List<WeatherDay> newItems) {
        this.items = newItems;
        notifyDataSetChanged();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvDate;
        TextView tvTemp;
        TextView tvDesc;

        ViewHolder(View view) {
            super(view);
            tvDate = view.findViewById(R.id.tvDate);
            tvTemp = view.findViewById(R.id.tvTemp);
            tvDesc = view.findViewById(R.id.tvDesc);
        }
    }
}
