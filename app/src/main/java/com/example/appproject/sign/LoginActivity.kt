package com.example.appproject.sign

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.appproject.MainActivity
import com.example.appproject.R
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase


class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_in)

        //회원가입 버튼(btn_signup)을 누르면 SignUpActivity로 이동
        val btnSignUp = findViewById<Button>(R.id.btn_sign_up)
        btnSignUp.setOnClickListener {
            startActivity(
                Intent(this, SignUpActivity::class.java))
            finish()
        }

        //로그인 버튼(btn_sign_in)을 누르면 user email과 password를 입력 받고 doLogin() 함수 호출
        findViewById<Button>(R.id.btn_sign_in)?.setOnClickListener {
            val userEmail = findViewById<EditText>(R.id.editText_Email)?.text.toString()
            val password = findViewById<EditText>(R.id.editText_Password)?.text.toString()

            if (userEmail.isEmpty() || password.isEmpty()) {
                Toast.makeText(this, "이메일과 비밀번호를 입력해주세요.", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            else{
                doLogin(userEmail, password)
            }
        }
    }

    //로그인 함수
    private fun doLogin(userEmail: String, password: String) {
        //("android@hansung.ac.kr", "hansungandroid") 은 저장되어있음
        Firebase.auth.signInWithEmailAndPassword(userEmail, password)
            .addOnCompleteListener(this) { // it: Task<AuthResult!>
                if (it.isSuccessful) {
                    Toast.makeText(this, "환영합니다.", Toast.LENGTH_SHORT).show()
                    startActivity(
                        Intent(this, MainActivity::class.java))
                    finish()
                } else {
                    Log.w("LoginActivity", "signInWithEmail", it.exception)
                    Toast.makeText(this, "이메일 또는 비밀번호를 잘못 입력했습니다. 다시 입력해주세요.", Toast.LENGTH_SHORT).show()
                }
            }
    }

}