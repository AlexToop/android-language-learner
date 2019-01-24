/*
 * Copyright (c) 2018. Created by Alexander Toop. Assignment for Aberystwyth University.
 */

package alt28.assignment.university.aberystwyth.languagelearner.model.util;

import android.arch.persistence.room.TypeConverter;
import android.util.Log;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

/**
 * Full credit for this class goes to Chris Loftus for his FAA workshops.
 * Please contact cwl@aber.ac.uk for further enquiries
 *
 * With time this class would ideally be used for sorting
 * ---Placeholder use---
 */
public class DateTimeConverter {

    @TypeConverter
    public static Date toDate(Long timestamp) {
        Date result = timestamp == null ? null : new Date(timestamp);
        return result;
    }

    @TypeConverter
    public static Long toTimestamp(Date date) {
        Long result = date == null ? null : date.getTime();
        return result;
    }
}
