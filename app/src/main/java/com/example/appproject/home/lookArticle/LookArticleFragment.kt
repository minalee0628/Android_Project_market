package com.example.appproject.home.lookArticle

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.appproject.R
import com.example.appproject.data.DBKey
import com.example.appproject.databinding.FragmentHomeBinding
import com.example.appproject.home.addArticle.AddArticleActivity
import com.example.appproject.home.addArticle.ItemAddAdapter
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class LookArticleFragment: Fragment(R.layout.fragment_look_article) {
    private var binding: FragmentHomeBinding? = null
    private val auth: FirebaseAuth by lazy { Firebase.auth }

    private lateinit var articleDB: DatabaseReference
    private lateinit var userDB: DatabaseReference
    private lateinit var articleAdapter: LookArticleAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val fragmentHomeBinding = FragmentHomeBinding.bind(view)
        binding = fragmentHomeBinding

        articleDB = Firebase.database.reference.child(DBKey.DB_ARTICLES)
        userDB = Firebase.database.reference.child(DBKey.DB_USER)


        //fragmentHomeBinding.itemRecyclerView.layoutManager = LinearLayoutManager(context)
        //fragmentHomeBinding.itemRecyclerView.adapter = articleAdapter


    }
}