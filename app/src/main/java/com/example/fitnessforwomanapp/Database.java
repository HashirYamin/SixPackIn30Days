package com.example.fitnessforwomanapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class Database extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "workout_db";
    private static final int DATABASE_VERSION = 1;


    public Database(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String createExercisesTable  = "create table ExercisesTable(String dayId, name text, duration text, instructions text, mistakes text, breathingtips text, muscle text)";
        sqLiteDatabase.execSQL(createExercisesTable );
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
    // Insert data into Exercises table
    public void addExercise(String name, String duration, String instructions, String mistakes, String breathingtips, String muscle) {
        ContentValues values = new ContentValues();
        values.put("name", name);
        values.put("duration", duration);
        values.put("instructions", instructions);
        values.put("mistakes", mistakes);
        values.put("breathingtips", breathingtips);
        values.put("muscle", muscle);
        SQLiteDatabase db = this.getWritableDatabase();
        long result = db.insert("Exercises", null, values);
        db.close();
    }
//
//    // Retrieve exercises for a specific day
//    public int checkExercisesForDay(int exerciseday, String exerciseName, String duration, String instructions, String mistakes, String breathingtips, String muscle) {
//        int result = 0;
//        String[] str = new String[2];
//        str[0] = String.valueOf(exerciseday);
//        str[1] = exerciseName;
//        String[] str2 = new String[6];
//        str2[2] = duration;
//        str2[3] = instructions;
//        str2[4] = mistakes;
//        str2[5] = breathingtips;
//
//        SQLiteDatabase db = getReadableDatabase();
//        Cursor c = db.rawQuery("Select * from Exercises where exerciseday = ? and name = ?", str);
//
//        if (c.moveToFirst()) {
//            result = 1; // Exercise found
//        }
//
//        c.close();
//        db.close();
//        return result;
//    }


}
