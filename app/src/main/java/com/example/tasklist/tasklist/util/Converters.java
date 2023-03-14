package com.example.tasklist.tasklist.util;

// An helper class for types like date and priority
// Because the compiler dont know what type they are so
// we need a way to tran tham into type and then back to data

import androidx.room.TypeConverter;

import com.example.tasklist.tasklist.model.Priority;

import java.util.Date;

public class Converters {

    // Helper for Date class

    @TypeConverter
    public static Date fromTimeStamp(Long value){
        return value == null ? null : new Date(value);
    }

    @TypeConverter
    public static Long dateToTimestamp(Date date){
        return date == null ? null : date.getTime();
    }

    // Helper for Priority class

    @TypeConverter
    public static String fromPriority(Priority priority){
        return priority == null ? null :priority.name();
    }

    @TypeConverter
    public static Priority toPriority(String priority){
        return priority == null ? null : Priority.valueOf(priority);
    }
}
