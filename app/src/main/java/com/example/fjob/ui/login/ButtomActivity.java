package com.example.fjob.ui.login;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.SearchView;

import com.example.fjob.Entity.job.JobMessage;
import com.example.fjob.JobViewModel;
import com.example.fjob.MyAdapter;
import com.example.fjob.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ButtomActivity extends AppCompatActivity {
    private static final String TAG = "ButtomActivity";
    private JobViewModel jobViewModel;
    private MyAdapter myAdapter;
    private RecyclerView recyclerView;
    private List<JobMessage>allJobs;
    private Button buttonRefresh;
    private LiveData<List<JobMessage>> fileJobs;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buttom);
        BottomNavigationView navView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_home, R.id.navigation_dashboard, R.id.navigation_notifications)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(navView, navController);
     //  jobViewModel=ViewModelProviders.of(this).get(JobViewModel.class);
          jobViewModel=ViewModelProviders.of(this).get(JobViewModel.class);
        recyclerView=findViewById(R.id.recycler_view_job_show2);


    }




//
//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//      //  return super.onCreateOptionsMenu(menu);
//
//        MenuInflater inflater=getMenuInflater();
//        inflater.inflate(R.menu.search_menu,menu);
//     return true;
//    }

//    @Override
//    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
//
//        switch (item.getItemId()){
//
//            case R.id.job:
//                Intent intent =new Intent(this, AddJobActivity.class);
//             startActivity(intent);
//             return true;
//
//             default:
//
//        }
//        return super.onOptionsItemSelected(item);
//    }


}
