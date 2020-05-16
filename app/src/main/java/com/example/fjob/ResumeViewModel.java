package com.example.fjob;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.fjob.Dao.ReceiveResumeDao;
import com.example.fjob.Entity.job.JobMessage;
import com.example.fjob.Entity.job.ResumeEntity;
import com.example.fjob.ResumeReposiry;

import java.util.List;

public class ResumeViewModel extends AndroidViewModel {

    private ReceiveResumeDao receiveResumeDao;
    private ResumeReposiry resumeReposiry;

    public ResumeViewModel(@NonNull Application application) {
        super(application);
        resumeReposiry =new ResumeReposiry(application);
    }

    public LiveData<List<ResumeEntity>> findMsgsWithPattern(String patten) {
        return resumeReposiry.findResumeWithPattern(patten);
    }
    public   void insertRess(ResumeEntity... resumeEntities) {
        resumeReposiry.insertRess(resumeEntities);
    }




}
