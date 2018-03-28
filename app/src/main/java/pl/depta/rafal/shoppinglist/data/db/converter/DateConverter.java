package pl.depta.rafal.shoppinglist.data.db.converter;


import android.arch.persistence.room.TypeConverter;

import java.util.Date;


public class DateConverter {

    @TypeConverter
    public static Date toDate(Long timestamp) {
        return timestamp == null ? new Date() : new Date(timestamp);
    }

    @TypeConverter
    public static Long toTimestamp(Date date) {
        return date == null ? null : date.getTime();
    }

}
