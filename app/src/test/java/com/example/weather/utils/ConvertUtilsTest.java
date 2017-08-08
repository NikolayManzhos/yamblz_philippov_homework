package com.example.weather.utils;


import android.content.Context;

import com.example.weather.BuildConfig;
import com.example.weather.R;
import com.example.weather.data.local.PreferencesManager;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.RuntimeEnvironment;
import org.robolectric.annotation.Config;

import static junit.framework.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

//TODO Find way to provide resources for test runner
@Ignore
@RunWith(RobolectricTestRunner.class)
@Config(constants = BuildConfig.class, sdk = 21, manifest = "src/main/AndroidManifest.xml", packageName = "com.example.weather")
public class ConvertUtilsTest {

    private ConvertUtils utils;
    private PreferencesManager preferencesManager;
    private Context context;

    @Before
    public void setup() {
        preferencesManager = mock(PreferencesManager.class);
        context = RuntimeEnvironment.application.getApplicationContext();
        utils = new ConvertUtils(preferencesManager, context.getResources());
    }

    @Test
    public void convertToCelsius() {
        when(preferencesManager.getTemperatureUnits()).thenReturn("C");
        assertEquals(0, utils.convertTemperature(273.15));
    }

    @Test
    public void convertToFahrenheit() {
        when(preferencesManager.getTemperatureUnits()).thenReturn("F");
        assertEquals(80, utils.convertTemperature(300));
    }

    @Test
    public void convertToMetersPerSecond() {
        when(preferencesManager.getWindSpeedUnits()).thenReturn("meters");
        assertEquals(10, utils.convertWindSpeed(10.4));
    }

    @Test
    public void convertToKilometersPerHour() {
        when(preferencesManager.getWindSpeedUnits()).thenReturn("kilometers");
        assertEquals(7, utils.convertWindSpeed(1.94));
    }

    @Test
    public void convertToMilesPerHour() {
        when(preferencesManager.getWindSpeedUnits()).thenReturn("miles");
        assertEquals(31, utils.convertWindSpeed(14));
    }

    @Test
    public void convertToKnots() {
        when(preferencesManager.getWindSpeedUnits()).thenReturn("knots");
        assertEquals(39, utils.convertWindSpeed(20.5));
    }

    @Test
    public void convertWindSpeedUnknown() {
        when(preferencesManager.getWindSpeedUnits()).thenReturn("unknown");
        assertEquals(11, utils.convertWindSpeed(11.4));
    }

    @Test
    public void convertPressure() {
        assertEquals(976, utils.convertPressure(976.45));
    }

    @Test
    public void testCase() {
        assertEquals("Weather", context.getString(R.string.app_name));
    }
}