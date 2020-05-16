package com.example.fjob;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.fjob.Dao.JobDao;
import com.example.fjob.Entity.job.JobMessage;

import java.util.List;

public class JobViewModel extends AndroidViewModel {

    private JobRepository jobRepository;
    public JobViewModel(@NonNull Application application) {
        super(application);
        jobRepository =new JobRepository(application);
    }
    LiveData<List<JobMessage>> getAllJobsLive() {
        return jobRepository.getAllJobsLive();
    }
    LiveData<List<JobMessage>> findAllJobsLive(String patten) {
        return  jobRepository.findAllJobsLive(patten);}

  public   void insertJobs(JobMessage... jobMessages) {
        jobRepository.insertJobs(jobMessages);
    }
    public void deleteJobs(JobMessage... jobMessages) { jobRepository.deleteJobs(jobMessages);
    }

    public void updateJobs(JobMessage... jobMessages) {
        jobRepository.updateJobs(jobMessages);
    }
    public  void deleteAllJobs() {
        jobRepository.deleteAllJobs();
    }

}
