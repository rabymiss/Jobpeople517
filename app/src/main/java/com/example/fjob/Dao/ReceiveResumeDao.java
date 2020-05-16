package com.example.fjob.Dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.fjob.Entity.job.JobMessage;
import com.example.fjob.Entity.job.ResumeEntity;

import java.util.List;

@Dao
public interface ReceiveResumeDao {

    @Insert
    void insertResume(ResumeEntity...resumeEntities);
    @Query("SELECT * FROM ResumeEntity WHERE workming  LIKE :pattern ORDER BY ID DESC")
    LiveData<List<ResumeEntity>> findResumeWithPattern(String pattern);
}
