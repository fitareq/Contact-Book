package com.fitareq.contactbook.repository;

import android.app.Application;
import android.util.Log;

import androidx.lifecycle.LiveData;

import com.fitareq.contactbook.database.LocalDatabase;
import com.fitareq.contactbook.model.LoginBody;
import com.fitareq.contactbook.model.LoginResponse;
import com.fitareq.contactbook.model.ResponseObj;
import com.fitareq.contactbook.model.UserDao;
import com.fitareq.contactbook.network.APIClient;
import com.fitareq.contactbook.network.APIInterface;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginRepository {

    private UserDao userDao;
    private LiveData<ResponseObj> userData;

    public LoginRepository(Application application)
    {
        userDao = LocalDatabase.getInstance(application).userDao();
        userData = userDao.getUserData();
    }

    public void setUserData(ResponseObj responseObj)
    {
        LocalDatabase.databaseWriteExecutor.execute(()->userDao.insertUserData(responseObj));
    }

    public LiveData<ResponseObj> getUserData(){
        return this.userData;
    }

    public void loginUser(LoginBody loginBody, LoginCallBack loginCallBack)
    {
        APIInterface apiInterface = APIClient.getClient().create(APIInterface.class);

        Call<LoginResponse> call = apiInterface.userSignIn(loginBody);

        call.enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                if (response.isSuccessful() && response.body() != null)
                {
                    if (response.body().getResponseCode() == 200)
                    {
                        loginCallBack.loginSuccessResponse(response.body().getResponseObj());
                    } else loginCallBack.loginErrorResponse("Invalid Credential!");
                }else loginCallBack.loginErrorResponse("Invalid Credential!");
            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                Log.v("LoginRepository", "error: "+t.getMessage());
                loginCallBack.loginErrorResponse("Login Failed!");
            }
        });
    }


    public interface LoginCallBack
    {
        void loginSuccessResponse(ResponseObj responseObj);
        void loginErrorResponse(String error);
    }
}
