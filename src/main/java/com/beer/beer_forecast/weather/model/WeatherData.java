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

    @Column(name = "avg_temp_c") // ⭐ 正确映射数据库字段
    private float avgTempC;

    @Column(name = "day_of_week")
    private String dayOfWeek;

    private int month;

    @Column(name = "weather_code")
    private int weatherCode;

    // ✅ Getter & Setter（确保变量名和上面一致）

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDate getRecordDate() {
        return recordDate;
    }

    public void setRecordDate(LocalDate recordDate) {
        this.recordDate = recordDate;
    }

    public float getAvgTempC() {
        return avgTempC;
    }

    public void setAvgTempC(float avgTempC) {
        this.avgTempC = avgTempC;
    }

    public String getDayOfWeek() {
        return dayOfWeek;
    }

    public void setDayOfWeek(String dayOfWeek) {
        this.dayOfWeek = dayOfWeek;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getWeatherCode() {
        return weatherCode;
    }

    public void setWeatherCode(int weatherCode) {
        this.weatherCode = weatherCode;
    }
}
