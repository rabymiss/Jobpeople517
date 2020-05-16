package com.example.fjob;

import android.content.Context;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.example.fjob.Dao.JobDao;
import com.example.fjob.Dao.JobMessageAllDao;
import com.example.fjob.Dao.ReceiveResumeDao;
import com.example.fjob.Entity.job.JobMessage;
import com.example.fjob.Entity.job.JobMessageAll;
import com.example.fjob.Entity.job.ResumeEntity;
import com.example.fjob.JobDatabase;

import java.util.List;

public class ResumeReposiry {


    private LiveData<List<ResumeEntity>> allResumesLive;
    private ReceiveResumeDao receiveResumeDao;


    ResumeReposiry(Context context) {
        JobDatabase jobDatabase = JobDatabase.getDatabase(context.getApplicationContext());
        receiveResumeDao = jobDatabase.getReceiveResumeDao();
    }

    LiveData<List<ResumeEntity>> findResumeWithPattern(String patten) {
        return receiveResumeDao.findResumeWithPattern(patten);
    }
    void insertRess(ResumeEntity... resumeEntities) {
        new ResumeReposiry.InsertAsyncTask(receiveResumeDao).execute(resumeEntities);
    }
    static class  InsertAsyncTask extends AsyncTask<ResumeEntity, Void, Void>{

        private ReceiveResumeDao receiveResumeDao;

        InsertAsyncTask(ReceiveResumeDao receiveResumeDao) {
            this.receiveResumeDao =receiveResumeDao ;
        }
        @Override
        protected Void doInBackground(ResumeEntity... resumeEntities) {
            receiveResumeDao.insertResume(resumeEntities);
            return null;
        }
    }
}