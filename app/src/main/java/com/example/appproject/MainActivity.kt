package com.example.appproject

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.appproject.navigation.accountFragment
import com.example.appproject.navigation.chatFragment
import com.example.appproject.navigation.homeFragment
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //하단 네비게이션 Fragment
        val homeFragment = homeFragment()
        val chatFragment = chatFragment()
        val accountFragment = accountFragment()

        //첫화면은 homeFragment로 설정
        setFragment(homeFragment)

        //하단 네비게이션을 클릭시
        val bottomNavigationView  = findViewById<BottomNavigationView>(R.id.bottom_navigation)
        bottomNavigationView .setOnItemSelectedListener {
            when (it.itemId) {
                R.id.action_home -> setFragment(homeFragment)
                R.id.action_chat -> setFragment(chatFragment)
                R.id.action_account -> setFragment(accountFragment)
            }
            return@setOnItemSelectedListener true
        }
    }

    //선택한 Fragment을 FrameLayout에 적용
    private fun setFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.main_content, fragment).commit()
        }
    }
    
}