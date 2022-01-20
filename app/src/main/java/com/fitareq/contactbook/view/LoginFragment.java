package com.fitareq.contactbook.view;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.fitareq.contactbook.R;
import com.fitareq.contactbook.model.LoginBody;
import com.fitareq.contactbook.model.ResponseObj;
import com.fitareq.contactbook.repository.LoginRepository;
import com.fitareq.contactbook.viewmodel.ContactViewModel;
import com.fitareq.contactbook.viewmodel.LoginViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

public class LoginFragment extends Fragment {

    private TextInputEditText userEmail, userPass;
    private TextInputLayout userEmailLayout, userPassLayout;
    private FloatingActionButton loginFab;
    private NavController navController;
    private LoginViewModel loginViewModel;

    public LoginFragment() {
        // Required empty public constructor
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_login, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        navController = Navigation.findNavController(view);
        userEmail = view.findViewById(R.id.login_email);
        userPass = view.findViewById(R.id.login_pass);
        loginFab = view.findViewById(R.id.login_fab);
        userEmailLayout = view.findViewById(R.id.textInputLayout);
        userPassLayout = view.findViewById(R.id.textInputLayout2);

        loginViewModel = new ViewModelProvider(requireActivity()).get(LoginViewModel.class);
        NavController navController = Navigation.findNavController(view);




        loginFab.setOnClickListener(v -> {
            userEmailLayout.setError(null);
            userPassLayout.setError(null);

            String email = userEmail.getText().toString();
            String pass = userPass.getText().toString();

            if (TextUtils.isEmpty(email))
            {
                userEmailLayout.setError("Please enter email");
            }else if (TextUtils.isEmpty(pass))
            {
                userPassLayout.setError("Please enter password");
            }else {

                loginViewModel.loginUser(new LoginBody(email, pass), new LoginRepository.LoginCallBack() {
                    @Override
                    public void loginSuccessResponse(ResponseObj responseObj) {
                        Toast.makeText(getContext(), "Login Successful", Toast.LENGTH_SHORT).show();
                        loginViewModel.setUserData(responseObj);
                        navController.navigate(R.id.action_loginFragment_to_contactFragment);
                    }

                    @Override
                    public void loginErrorResponse(String error) {
                        Toast.makeText(getContext(), "Login Failed", Toast.LENGTH_SHORT).show();
                    }
                });
            }

        });
    }
}