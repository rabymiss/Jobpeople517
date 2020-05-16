package com.example.fjob;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.fjob.Dao.LoginDao;
import com.example.fjob.UserRepository;
import com.example.fjob.data.model.LoginUser;

import java.util.List;

/**
 * Class exposing authenticated user details to the UI.
 */
public class UserViewModel  extends AndroidViewModel {

    private LoginDao loginDao;
 private UserRepository userRepository;

    public UserViewModel(@NonNull Application application) {
        super(application);
        userRepository=new UserRepository(application);

    }

    public LiveData<List<LoginUser>> findAllUserLive(String pattern) { return userRepository.findAllUserLive(pattern);}
    LiveData<List<LoginUser>>getAllUsersLive(){ return userRepository.getAllUsersLive();};

    public void insertUsers(LoginUser...loginUsers) { userRepository.insertUsers(loginUsers); }
   public void deleteUsers(LoginUser... loginUsers) {
       userRepository.deleteUsers(loginUsers);
    }
    public void UpdateUsers(LoginUser... loginUsers) {
       userRepository.UpdateUsers(loginUsers);
    }
    public     void deleteAllUsers(Void... voids) {
      userRepository.deleteAllUsers();
    }


}
