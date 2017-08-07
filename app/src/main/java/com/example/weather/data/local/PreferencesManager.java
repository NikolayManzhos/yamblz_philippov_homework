package com.example.weather.data.local;

import android.content.SharedPreferences;

public class PreferencesManager {

    private final String FIRST_TIME_USE = "b_first_time_user";

    private final String LAT = "lat";
    private final String LNG = "lng";
    private final String CURRENT_NAME = "current_name";

    private SharedPreferences sharedPreferences;

    public PreferencesManager(SharedPreferences sharedPreferences) {
        this.sharedPreferences = sharedPreferences;
    }

    public String getPressureUnits() {
        String UNITS_PRESSURE = "b_pressure";
        return sharedPreferences.getString(UNITS_PRESSURE, "hpa");
    }

    public String getWindSpeedUnits() {
        String UNITS_WIND_SPEED = "b_wind_speed";
        return sharedPreferences.getString(UNITS_WIND_SPEED, "meters");
    }

    public String getTemperatureUnits() {
        String UNITS_TEMPERATURE = "b_temperature";
        return sharedPreferences.getString(UNITS_TEMPERATURE, "C");
    }

    public String getCurrentUpdateInterval() {
        String UPDATE_INTERVAL = "b_interval";
        return sharedPreferences.getString(UPDATE_INTERVAL, "3600000");
    }

    public boolean getFirstTimeUser() {
        return sharedPreferences.getBoolean(FIRST_TIME_USE, true);
    }

    public void setFirstTimeUser(boolean status) {
        sharedPreferences.edit().putBoolean(FIRST_TIME_USE, status).apply();
    }

    public void setCurrentLatitude(double latitude) {
        sharedPreferences.edit().putFloat(LAT, (float) latitude).apply();
    }

    public float getCurrentLatitude() {
        //Default Moscow
        return sharedPreferences.getFloat(LAT, (float) 55.7558);
    }

    public void setCurrentLongitude(double longitude) {
        sharedPreferences.edit().putFloat(LNG, (float) longitude).apply();
    }

    public float getCurrentLongitude() {
        //Default Moscow
        return sharedPreferences.getFloat(LNG, (float) 37.6173);
    }

    public void setCurrentCityName(String cityName) {
        sharedPreferences.edit().putString(CURRENT_NAME, cityName).apply();
    }

    public String getCurrentCityName() {
        //Default Moscow
        return sharedPreferences.getString(CURRENT_NAME, "Moscow");
    }
}
