package com.example.appproject.sign

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.appproject.R
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class SignUpActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)

        //사용자 확인
        if (Firebase.auth.currentUser == null) {
            startActivity(
                Intent(this, LoginActivity::class.java))
            finish()
        }

        //뒤로가기 버튼(btn_signup_goback)을 누르면 LoginActivity로 이동
        findViewById<Button>(R.id.btn_signup_goback)?.setOnClickListener {
            startActivity(
                Intent(this, LoginActivity::class.java))
        }

        //계정 생성하기(btn_signup_ok)을 누르면 이메일, 비밀번호, 이름, 생년월일을 입력 받고 doSingUp() 함수 호출
        findViewById<Button>(R.id.btn_signup_ok)?.setOnClickListener {
            val newUserEmail = findViewById<EditText>(R.id.editText_email_signup)?.text.toString()
            val newPassword = findViewById<EditText>(R.id.editText_password_signup)?.text.toString()
            val newUserName = findViewById<EditText>(R.id.editText_name_signup)?.text.toString()
            val newUserBirth = findViewById<EditText>(R.id.editText_birth_signup)?.text.toString()

            if (newUserEmail.isEmpty() || newPassword.isEmpty()|| newUserName.isEmpty() || newUserBirth.isEmpty()) {
                Toast.makeText(this, "빈칸을 제대로 입력해주세요.", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            else{
                doSingUp(newUserEmail, newPassword)
            }
        }
    }

    //회원가입 함수
    private fun doSingUp(newUserEmail: String, newPassword: String) {
        Firebase.auth.createUserWithEmailAndPassword(newUserEmail, newPassword)
            .addOnCompleteListener(this) { // it: Task<AuthResult!>
                if (it.isSuccessful) {
                    Toast.makeText(this, "회원가입이 성공적으로 완료되었습니다.", Toast.LENGTH_SHORT)
                        .show()
                    startActivity(
                        Intent(this, LoginActivity::class.java)
                    )
                    finish()
                } else {
                    //Log.w("SingUpActivity", "signUnWithEmail", it.exception)
                    Toast.makeText(this, "회원가입이 실패했습니다. 다시 입력해주세요.", Toast.LENGTH_SHORT)
                        .show()
                }
            }
    }
}