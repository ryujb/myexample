/*
* Copyright (C) 2015 Hansoo Lab.
*
* Licensed under the Apache License, Version 2.0 (the "License");
* you may not use this file except in compliance with the License.
* You may obtain a copy of the License at
*
*      http://www.apache.org/licenses/LICENSE-2.0
*
* Unless required by applicable law or agreed to in writing, software
* distributed under the License is distributed on an "AS IS" BASIS,
* WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
* See the License for the specific language governing permissions and
* limitations under the License.
*/

package com.pushplant.android.timesheet.cal;

import com.pushplant.android.timesheet.cal.WeatherInfo.Weather;

import java.util.Calendar;



/**
 * Value object for a day
 * @author brownsoo
 *
 */
public class OneDayData {
    
    Calendar cal;
    Weather weather;
    int worktime;
    private CharSequence msg = "";
    
    /**
     * OneDayData Constructor
     */
    public OneDayData() {
        this.cal = Calendar.getInstance();
        this.weather = Weather.SUNSHINE;
        this.worktime = 0;

    }
    
    /**
     * Set day info with given data
     * @param year 4 digits of year
     * @param month month Calendar.JANUARY ~ Calendar.DECEMBER
     * @param day day of month (1~#)
     */
    public void setDay(int year, int month, int day) {
        cal = Calendar.getInstance();
        cal.set(year, month, day);
    }

    /**
     * Set day info with cloning calendar
     * @param cal calendar to clone
     */
    public void setDay(Calendar cal) {
        this.cal = (Calendar) cal.clone();
    }

    /**
     * Get calendar
     * @return Calendar instance
     */
    public Calendar getDay() {
        return cal;
    }
    
    /**
     * Same function with {@link Calendar#get(int)}<br>
     * <br>
     *
     * Returns the value of the given field after computing the field values by
     * calling {@code complete()} first.
     *
     * @throws IllegalArgumentException
     *                if the fields are not set, the time is not set, and the
     *                time cannot be computed from the current field values.
     * @throws ArrayIndexOutOfBoundsException
     *                if the field is not inside the range of possible fields.
     *                The range is starting at 0 up to {@code FIELD_COUNT}.
     */
    public int get(int field) throws IllegalArgumentException, ArrayIndexOutOfBoundsException {
        return cal.get(field);
    }

    /**
     * Set weather info
     * @param weather Weather instance
     */
    public void setWeather(Weather weather) {
        this.weather = weather;
    }
    
    /**
     * Get weather info
     * @return
     */
    public Weather getWeather() {
        return this.weather;
    }

    /**
     * Get message
     * @return message
     */
    public CharSequence getMessage() {
        return msg;
    }
    
    /**
     * Set message
     * @param msg message to display
     */
    public void setMessage(CharSequence msg) {
        this.msg = msg;
    }


    public int getWorktime() {
        return worktime;
    }

    public void setWorktime(int worktime) {
        this.worktime = worktime;
    }
}
