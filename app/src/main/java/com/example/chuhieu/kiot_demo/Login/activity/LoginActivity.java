package com.example.chuhieu.kiot_demo.Login.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


import com.example.chuhieu.kiot_demo.Service.ApiService;
import com.example.chuhieu.kiot_demo.Service.RetrofitClient;
import com.example.chuhieu.kiot_demo.Login.model.Login;
import com.example.chuhieu.kiot_demo.Login.model.User;
import com.example.chuhieu.kiot_demo.MainActivity;
import com.example.chuhieu.kiot_demo.R;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;


public class LoginActivity extends AppCompatActivity {
    EditText ed_taikhoan, ed_matkhau, ed_code;
    private Button btnLogin;
    public static String token;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        ed_taikhoan = findViewById(R.id.ed_user);
        ed_matkhau = findViewById(R.id.ed_password);
        ed_code = findViewById(R.id.ed_code);
        btnLogin = findViewById(R.id.btnLogin);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Login login = new Login();

                login.setUserName(ed_taikhoan.getText().toString());
                login.setPassword(ed_matkhau.getText().toString());
                login.setTenantCode(ed_code.getText().toString());
                login.setTenantId("0");
                login.setEmail("");
                login.setLanguage("");

                setLogin(login);
            }
        });

    }

//    public void btnlogin(View view) {
//        Login login = new Login();
//
//        login.setUserName(ed_taikhoan.getText().toString());
//        login.setPassword(ed_matkhau.getText().toString());
//        login.setTenantCode(ed_code.getText().toString());
//        login.setTenantId("0");
//        login.setEmail("");
//        login.setLanguage("");
//
//        setLogin(login);
//    }

    public void setLogin(Login login) {
        Retrofit retrofit = RetrofitClient.getRetrofitClient();
        ApiService apiService = retrofit.create(ApiService.class);
        Call<User> call = apiService.token(login);
        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {

                if (response.isSuccessful()) {
                        if (!response.body().getToken().isEmpty()) {
                            token = response.body().getToken();
                            startActivity(new Intent(LoginActivity.this, MainActivity.class));
                            Toast.makeText(LoginActivity.this, "Đăng nhập thành công", Toast.LENGTH_SHORT).show();

                            finish();
                        }
                        else {
                            Toast.makeText(LoginActivity.this, response.body().getError(), Toast.LENGTH_SHORT).show();
                        }
                }

            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                Toast.makeText(LoginActivity.this, "Có lỗi xảy ra", Toast.LENGTH_SHORT).show();
            }
        });
    }

}
