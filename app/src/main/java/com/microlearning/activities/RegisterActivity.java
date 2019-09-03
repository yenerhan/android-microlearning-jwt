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
import com.microlearning.model.user.SaveUserRequestDTO;
import com.microlearning.model.user.User;
import com.microlearning.storage.SharedPrefManager;
import com.sinavtime.microlearning.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText editTextEmail, editTextPassword;
    private EditText editTextFirstName, editTextLastName,editTextCode;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        editTextFirstName = findViewById(R.id.editTextFirstName);
        editTextLastName = findViewById(R.id.editTextLastName);
        editTextEmail = findViewById(R.id.editTextEmail);
        editTextPassword = findViewById(R.id.editTextPassword);
        editTextCode = findViewById(R.id.editTextCode);

        findViewById(R.id.buttonSignUp).setOnClickListener(this);
        findViewById(R.id.textViewLogin).setOnClickListener(this);

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

    private void userSignUp() {
        String email = editTextEmail.getText().toString().trim();
        String password = editTextPassword.getText().toString().trim();
        String firstName = editTextFirstName.getText().toString().trim();
        String lastName = editTextLastName.getText().toString().trim();
        String code = editTextCode.getText().toString().trim();

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

        if (firstName.isEmpty()) {
            editTextFirstName.setError("Name required");
            editTextFirstName.requestFocus();
            return;
        }

        if (lastName.isEmpty()) {
            editTextLastName.setError("LastName required");
            editTextLastName.requestFocus();
            return;
        }

        if (code.isEmpty()) {
            editTextCode.setError("Code required");
            editTextCode.requestFocus();
            return;
        }
        SaveUserRequestDTO saveUserRequestDTO = new SaveUserRequestDTO();
        saveUserRequestDTO.setCode(editTextCode.getText().toString());
        saveUserRequestDTO.setEmail(editTextEmail.getText().toString());
        saveUserRequestDTO.setPassword(editTextPassword.getText().toString());
        saveUserRequestDTO.setFirstName(editTextFirstName.getText().toString());
        saveUserRequestDTO.setLastName(editTextLastName.getText().toString());

        UserService userService = RetrofitClient.getRetrofitInstance(this).create(UserService.class);
        Call<User> call = userService.createUser(saveUserRequestDTO);


        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if (response.code() == 200) {
                    Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(intent);
                } else if (response.code() == 201) {
                    User dr = response.body();
                    Toast.makeText(RegisterActivity.this, dr.getFirstName() + "Giriş Başarılı", Toast.LENGTH_LONG).show();

                } else if (response.code() == 422) {
                    Toast.makeText(RegisterActivity.this, "Kullanıcı Zaten Var", Toast.LENGTH_LONG).show();
                }else if (response.code() == 500) {
                    try {
                        JSONObject jObjError = new JSONObject(response.errorBody().string());
                        Toast.makeText(RegisterActivity.this, jObjError.getString("message"), Toast.LENGTH_LONG).show();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                Toast.makeText(RegisterActivity.this, t.getMessage(), Toast.LENGTH_LONG).show();

            }
        });


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.buttonSignUp:
                userSignUp();
                break;
            case R.id.textViewLogin:
                startActivity(new Intent(this, LoginActivity.class));
                break;
        }
    }
}
