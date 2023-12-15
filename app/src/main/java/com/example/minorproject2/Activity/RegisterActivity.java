package com.example.minorproject2.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.minorproject2.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class RegisterActivity extends AppCompatActivity {
    EditText inputEmail,inputPassword,inputConfirmPassword,editTextName;
    TextView alreadyHaveAnAccount;
    Button btnRegister;
    String emailPatern="[a-zA-Z0-9._]+@[a-z]+\\.+[a-z]+";
    ProgressDialog progressDialog;
    FirebaseAuth nAuth;
    FirebaseUser nUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        inputEmail=findViewById(R.id.inputEmail);
        inputPassword=findViewById(R.id.inputPassword);
        inputConfirmPassword=findViewById(R.id.confirmInputPassword);
        alreadyHaveAnAccount=findViewById(R.id.alreadyHaveAnAccount);
        btnRegister = findViewById(R.id.btnRegister);
        editTextName=findViewById(R.id.editTextName);
        progressDialog=new ProgressDialog(this);
        nAuth=FirebaseAuth.getInstance();
        nUser=nAuth.getCurrentUser();

        alreadyHaveAnAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(RegisterActivity.this,loginActivity.class);
                startActivity(intent);

            }
        });
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PerForAuth();
            };
        });
    }



    private void PerForAuth() {
        String email=inputEmail.getText().toString();
        String password=inputPassword.getText().toString();
        String confirmPassword=inputConfirmPassword.getText().toString();

        if(!email.matches(emailPatern)){
            inputEmail.setError("Enter Correct Email");
        } else if (password.isEmpty() || password.length()<6) {
            inputPassword.setError("enter correct password");
        } else if (!password.equals(confirmPassword)) {
            inputConfirmPassword.setError("password dont match both fields");
        }else{
            progressDialog.setMessage("please wit while registraton...");
            progressDialog.setTitle("Registration");
            progressDialog.setCanceledOnTouchOutside(false);
            progressDialog.show();

            nAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if(task.isSuccessful()){
                        progressDialog.dismiss();
                        setLoggedIn();
                        sendUserToNextActivity();
                        Toast.makeText(RegisterActivity.this, "Registration successfull", Toast.LENGTH_SHORT).show();
                    }
                    else{
                        progressDialog.dismiss();
                        Toast.makeText(RegisterActivity.this, ""+task.getException(), Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }

    private void setLoggedIn() {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putBoolean("isLoggedIn",true);
        editor.apply();
        Toast.makeText(this, "register true", Toast.LENGTH_SHORT).show();
    }

    private void sendUserToNextActivity() {
        String userName = editTextName.getText().toString().trim();
        String userEmail = inputEmail.getText().toString().trim();
        Intent intent=new Intent(this,MainActivity.class);
        intent.putExtra("userName", userName);
        intent.putExtra("userEmail", userEmail);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
        finish();
    }
}