package com.example.fjob;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.fjob.Dao.JobDao;
import com.example.fjob.Dao.JobMessageAllDao;
import com.example.fjob.Dao.LoginDao;
import com.example.fjob.Dao.ReceiveResumeDao;
import com.example.fjob.Entity.job.JobMessage;
import com.example.fjob.Entity.job.JobMessageAll;
import com.example.fjob.Entity.job.ResumeEntity;
import com.example.fjob.data.model.LoginUser;

@Database(entities = {JobMessage.class, LoginUser.class, JobMessageAll.class, ResumeEntity.class},version = 1,exportSchema = false)


public abstract class JobDatabase extends RoomDatabase {

    private static JobDatabase INSTANCE;
  public   static synchronized JobDatabase getDatabase(Context context){

    if (INSTANCE==null){
        INSTANCE= Room.databaseBuilder(context.getApplicationContext(),JobDatabase.class,"job_database").build();

    }
        return INSTANCE;
    }
    public abstract JobDao getJobDao();
     public abstract LoginDao getLoginDao();
   public  abstract JobMessageAllDao getJobMessageAllDao();
public abstract ReceiveResumeDao getReceiveResumeDao();




}
