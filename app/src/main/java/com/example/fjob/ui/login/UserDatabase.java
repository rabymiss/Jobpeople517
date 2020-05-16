package com.example.fjob.ui.login;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.fjob.Dao.LoginDao;
import com.example.fjob.data.model.LoginUser;

@Database(entities = {LoginUser.class},version = 1,exportSchema = false)
public abstract class UserDatabase extends RoomDatabase {

    private static UserDatabase INSTANCE;
    static synchronized UserDatabase getDatabase(Context context){
        if (INSTANCE==null){
            INSTANCE= Room.databaseBuilder(context.getApplicationContext(),UserDatabase.class,"user_database").build();
        }
        return INSTANCE;
    }
    public abstract LoginDao getLoginDao();
}
