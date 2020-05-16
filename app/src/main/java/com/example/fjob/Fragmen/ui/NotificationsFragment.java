package com.example.fjob.Fragmen.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.example.fjob.NotificationsViewModel;
import com.example.fjob.R;
import com.example.fjob.ui.login.LoginActivity;
import com.example.fjob.ui.login.myui.AddMyMessageActivity;
import com.example.fjob.ui.login.myui.RusumeActivity;


public class NotificationsFragment extends Fragment {
private Button buttonQuite;
private ConstraintLayout constraintLayout;
    private NotificationsViewModel notificationsViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        notificationsViewModel = ViewModelProviders.of(this).get(NotificationsViewModel.class);
        return inflater.inflate(R.layout.fragment_notifications, container, false);
}



    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        //我的简历
        constraintLayout=requireActivity().findViewById(R.id.constrain_my_message);
        constraintLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(requireActivity(), RusumeActivity.class);
                startActivity(intent);
            }
        });



        buttonQuite=requireActivity().findViewById(R.id.btn_quit_my);
        buttonQuite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent=new Intent(requireActivity(), LoginActivity.class);
                startActivity(intent);
            }
        });


    }
}