package ercanduman.dayofbirth_horoscopes;

import android.util.Log;

import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

/**
 * Created by 7380 on 17.05.2017.
 */

public class AgeCalculator {

    public int startYear, startMonth, startDay;
    public static int endYear, endMonth, endDay;
    private int resultYear, resultMonth, resultDay;

    //Calendars
    private Calendar startCalendar = Calendar.getInstance();
    private Calendar endCalendar = Calendar.getInstance();

    private String currentDay, dayOfBirth;

    public String getCurrentDay() {
        endYear = endCalendar.get(Calendar.YEAR);
        endMonth = endCalendar.get(Calendar.MONTH);

        //month starts from 0
        endMonth++;
        endDay = endCalendar.get(Calendar.DAY_OF_MONTH);

        currentDay = endCalendar.getDisplayName(Calendar.DAY_OF_WEEK, Calendar.LONG, Locale.getDefault());

        return currentDay + ", " + endDay + "." + endMonth + "." + endYear;
    }


    public void getUserInputs(int yy, int mm, int dd) {
        startYear = yy;
        startMonth = mm;
        startDay = dd;

        startCalendar.set(Calendar.YEAR, startYear);
        startCalendar.set(Calendar.MONTH, startMonth);
        startCalendar.set(Calendar.DAY_OF_MONTH, startDay);

        dayOfBirth = startCalendar.getDisplayName(Calendar.DAY_OF_WEEK, Calendar.LONG, Locale.getDefault());
    }

    public String getDayOfBirth() {
        return dayOfBirth;
    }

    public void calculateYear() {
        resultYear = endYear - startYear;
    }

    public void calculateMonth() {
        if (endMonth >= startMonth) {
            resultMonth = endMonth - startMonth;
        } else {
            resultMonth = endMonth - startMonth;
            resultMonth = 12 + resultMonth;
            resultYear--;
        }
    }

    public void calculateDay() {
        if (endDay >= startDay) resultDay = endDay - startDay;
        else {
            resultDay = endDay - startDay;
            resultDay = 30 + resultDay;
            if (resultMonth == 0) {
                resultMonth = 11;
                resultYear--;
            } else resultMonth--;
        }
    }

    public int getResultYear() {
        return resultYear;
    }

    public int calculateRemainingDays(int selectedMonth, int selectedDay) {
//        int years = Calendar.YEAR;

        Date date1 = new Date();
        date1.setDate(selectedDay);
        date1.setMonth(selectedMonth);
//        date1.setYear(years);

//        years = years - 1; // Focus on the day between 0 - 365
        Date date2 = new Date();
        date2.setDate(endDay);
        date2.setMonth(endMonth);
//        date2.setYear(years);

        // find the difference between two dates
        long difference = date1.getTime() - date2.getTime();

        int remainingDays = (int) TimeUnit.DAYS.convert(difference, TimeUnit.MILLISECONDS);
//        remainingDays = Math.abs(remainingDays);
        Log.d("TAG", String.valueOf(remainingDays));
        if (remainingDays >= 365) {
            remainingDays = remainingDays - 365;
        } else if (remainingDays < 0)
            remainingDays = 365 - Math.abs(remainingDays);

        return remainingDays;
    }
}
