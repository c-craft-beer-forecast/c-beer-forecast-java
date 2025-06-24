package com.beer.beer_forecast.weather.model;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "daily_weather_data")
public class WeatherData {

    @Id
    private int id;

    @Column(name = "record_date")
    private LocalDate recordDate;

    @Column(name = "avg_temp_c")
    private float avgTempC;

    @Column(name = "weather_code")
    private int weatherCode;

    // Getter & Setter
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public LocalDate getRecordDate() { return recordDate; }
    public void setRecordDate(LocalDate recordDate) { this.recordDate = recordDate; }
    public float getAvgTempC() { return avgTempC; }
    public void setAvgTempC(float avgTempC) { this.avgTempC = avgTempC; }
    public int getWeatherCode() { return weatherCode; }
    public void setWeatherCode(int weatherCode) { this.weatherCode = weatherCode; }

    // 天気名を返すプロパティ（DBには保存されない）
    @Transient
    public String getWeatherName() {
        return switch (weatherCode) {
            case 1 -> "大雨、雷雨";
            case 2 -> "小雨";
            case 3 -> "くもり";
            case 4 -> "晴れ（ややくもり）";
            case 5 -> "快晴";
            default -> "不明";
        };
    }
    }
