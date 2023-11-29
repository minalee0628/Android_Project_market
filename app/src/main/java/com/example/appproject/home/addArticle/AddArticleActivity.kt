package com.example.appproject.home.addArticle

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.example.appproject.R
import com.example.appproject.data.DBKey.Companion.DB_ARTICLES
import com.example.appproject.data.ItemModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class AddArticleActivity : AppCompatActivity(){
    private val auth: FirebaseAuth by lazy { Firebase.auth }

    //private val storage: FirebaseStorage by lazy { Firebase.storage }

    private val articleDB: DatabaseReference by lazy {
        Firebase.database.reference.child(DB_ARTICLES)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_article)

        val toolbar = findViewById(R.id.toolbar) as Toolbar
        setSupportActionBar(toolbar)
        val ab = supportActionBar!!
        ab.setDisplayShowTitleEnabled(false)
        ab.setDisplayHomeAsUpEnabled(true)


        //등록하기 버튼 클릭시
        findViewById<Button>(R.id.uploadButton).setOnClickListener {
            val title = findViewById<EditText>(R.id.textTitle).text.toString()
            val content = findViewById<EditText>(R.id.textContent).text.toString()
            val price = findViewById<EditText>(R.id.textPrice).text.toString()
            val sellOrNot = findViewById<EditText>(R.id.textSellOrNot).text.toString()
            val sellerId = auth.currentUser?.uid.orEmpty()

            if (title.isEmpty()) {
                Toast.makeText(this, "제목을 입력해주세요.", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            if (price.isEmpty()) {
                Toast.makeText(this, "내용을 입력해주세요.", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            if (content.isEmpty()) {
                Toast.makeText(this, "가격을 입력해주세요.", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            if (sellOrNot.isEmpty()) {
                Toast.makeText(this, "'판매중'이라고 입력해주세요.", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            uploadArticle(sellerId, title, content, price, sellOrNot)
        }


    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id = item.itemId
        when (id) {
            android.R.id.home -> {
                finish()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun uploadArticle(sellerId: String, title: String, content: String, price: String, sellOrNot : String) {
        val model = ItemModel(sellerId, title, System.currentTimeMillis(), content, price, sellOrNot)
        articleDB.push().setValue(model)
        Toast.makeText(this, "판매글이 등록되었습니다.", Toast.LENGTH_SHORT).show()
        finish()
    }
}