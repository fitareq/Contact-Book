package com.fitareq.contactbook.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.fitareq.contactbook.model.LoginBody;
import com.fitareq.contactbook.model.ResponseObj;
import com.fitareq.contactbook.repository.LoginRepository;

public class LoginViewModel extends AndroidViewModel {

    private LoginRepository repository;
    public LoginViewModel(@NonNull Application application) {
        super(application);
        repository = new LoginRepository(application);
    }

    public void setUserData(ResponseObj responseObj)
    {
        repository.setUserData(responseObj);
    }
    public LiveData<ResponseObj> getUserData(){
        return repository.getUserData();
    }

    public void loginUser(LoginBody loginBody, LoginRepository.LoginCallBack loginCallBack)
    {
        repository.loginUser(loginBody, loginCallBack);
    }

}
