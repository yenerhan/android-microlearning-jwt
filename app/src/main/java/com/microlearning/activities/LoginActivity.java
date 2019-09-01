package com.microlearning.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Patterns;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.microlearning.api.RetrofitClient;
import com.microlearning.api.UserService;
import com.microlearning.model.user.LoginUserRequestDTO;
import com.microlearning.storage.SharedPrefManager;
import com.sinavtime.microlearning.R;

import okhttp3.Headers;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText editTextEmail;
    private EditText editTextPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        editTextEmail = findViewById(R.id.editTextEmail);
        editTextPassword = findViewById(R.id.editTextPassword);

        findViewById(R.id.buttonLogin).setOnClickListener(this);
        findViewById(R.id.textViewRegister).setOnClickListener(this);
    }

    @Override
    protected void onStart() {
        super.onStart();

        if (SharedPrefManager.getInstance(this).isLoggedIn()) {
            Intent intent = new Intent(this, MainActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
        }
    }

    private void userLogin() {

        String email = editTextEmail.getText().toString().trim();
        String password = editTextPassword.getText().toString().trim();

        if (email.isEmpty()) {
            editTextEmail.setError("Email is required");
            editTextEmail.requestFocus();
            return;
        }

        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            editTextEmail.setError("Enter a valid email");
            editTextEmail.requestFocus();
            return;
        }

        if (password.isEmpty()) {
            editTextPassword.setError("Password required");
            editTextPassword.requestFocus();
            return;
        }

        if (password.length() < 6) {
            editTextPassword.setError("Password should be atleast 6 character long");
            editTextPassword.requestFocus();
            return;
        }
        final LoginUserRequestDTO loginUserRequestDTO = new LoginUserRequestDTO();
        loginUserRequestDTO.setEmail(editTextEmail.getText().toString());
        loginUserRequestDTO.setPassword(editTextPassword.getText().toString());
        UserService userService = RetrofitClient.getRetrofitInstance(this).create(UserService.class);
        Call<Void> call = userService.userLogin(loginUserRequestDTO);

        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {

                if (response.code() == 200) {
                    Headers headers = response.headers();
                    String token = response.headers().get("authorization");
                    SharedPrefManager.getInstance(LoginActivity.this).saveToken(token,loginUserRequestDTO.getEmail());

                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(intent);
                    Toast.makeText(LoginActivity.this, "Kullanıcı bulundu.", Toast.LENGTH_LONG).show();

                } else {
                    Toast.makeText(LoginActivity.this, "Kullanıcı bulunamadı.", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {

            }
        });

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.buttonLogin:
                userLogin();
                break;
            case R.id.textViewRegister:
                startActivity(new Intent(this, RegisterActivity.class));
                break;
        }
    }
}
