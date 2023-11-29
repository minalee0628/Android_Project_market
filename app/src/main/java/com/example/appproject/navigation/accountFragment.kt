package com.example.appproject.navigation

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.EditText
import androidx.fragment.app.Fragment
import com.example.appproject.sign.LoginActivity
import com.example.appproject.R
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase


class accountFragment : Fragment(R.layout.fragment_account){
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    /*
        //로그아웃 하기
        view.findViewById<EditText>(R.id.fragment_account_sign_out)?.setOnClickListener {
            Firebase.auth.signOut()
            startActivity(
                Intent(activity, LoginActivity::class.java)
            )
        }


     */
         
    }
}