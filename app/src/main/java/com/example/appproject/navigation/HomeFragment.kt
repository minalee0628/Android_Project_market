package com.example.appproject.navigation

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.appproject.home.addArticle.AddArticleActivity
import com.example.appproject.data.DBKey.Companion.DB_ARTICLES
import com.example.appproject.data.DBKey.Companion.DB_USER
import com.example.appproject.home.addArticle.ItemAddAdapter
import com.example.appproject.data.ItemModel
import com.example.appproject.R
import com.example.appproject.databinding.FragmentHomeBinding
import com.example.appproject.home.lookArticle.ArticlePageModel
import com.example.appproject.home.lookArticle.LookArticleFragment
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.ChildEventListener
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class homeFragment : Fragment(R.layout.fragment_home){
    private var binding: FragmentHomeBinding? = null
    private val auth: FirebaseAuth by lazy { Firebase.auth }

    private lateinit var articleDB: DatabaseReference
    private lateinit var userDB: DatabaseReference
    private lateinit var articleAdapter: ItemAddAdapter

    private val articleList = mutableListOf<ItemModel>()
    private val listener = object: ChildEventListener {
        // 데이터 추가
        override fun onChildAdded(snapshot: DataSnapshot, previousChildName: String?) {

            val articleModel = snapshot.getValue(ItemModel::class.java)
            articleModel ?: return

            articleList.add(articleModel)
            articleAdapter.submitList(articleList)
        }
        // 데이터 변화
        override fun onChildChanged(snapshot: DataSnapshot, previousChildName: String?) {}
        // 데이터 삭제
        override fun onChildRemoved(snapshot: DataSnapshot) {}
        // 데이터가 이동
        override fun onChildMoved(snapshot: DataSnapshot, previousChildName: String?) {}
        // 데이터 제어중 오류 발생
        override fun onCancelled(error: DatabaseError) {}

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val fragmentHomeBinding = FragmentHomeBinding.bind(view)
        binding = fragmentHomeBinding

        articleDB = Firebase.database.reference.child(DB_ARTICLES)
        userDB = Firebase.database.reference.child(DB_USER)

        //구매 목록에서 물품 선택시
        articleAdapter = ItemAddAdapter(onItemClicked = { articleModel ->
            if (auth.currentUser != null) {
                if (auth.currentUser?.uid != articleModel.sellerId) {
                    //다른 사용자가 쓴 판매 글을 선택할시
                    //상세 페이지로 이동
                    /*
                    val articlePage = ArticlePageModel(
                        buyerId = auth.currentUser!!.uid,
                        sellerId = articleModel.sellerId,
                        itemTitle = articleModel.title,
                        itemPrice = articleModel.price,
                        itemContent = articleModel.content,
                        sellOrNot = articleModel.sellOrNot,
                        key = System.currentTimeMillis()
                    )
                    context?.let {
                        val intent = Intent(it, LookArticleFragment::class.java)
                        startActivity(intent)
                    }

                     */
                    Snackbar.make(view, "다른 사용자가 올린 아이템 입니다.", Snackbar.LENGTH_LONG).show()

                } else {        
                    //자신이 쓴 판매 글을 선택할시
                    //판매 글 수정
                    Snackbar.make(view, "내가 올린 아이템 입니다.", Snackbar.LENGTH_LONG).show()
                }
            }

        })
        articleList.clear()



        fragmentHomeBinding.itemRecyclerView.layoutManager = LinearLayoutManager(context)
        fragmentHomeBinding.itemRecyclerView.adapter = articleAdapter


        fragmentHomeBinding.addButton.setOnClickListener {
            context?.let {
                val intent = Intent(it, AddArticleActivity::class.java)
                startActivity(intent)
            }
        }
        articleDB.addChildEventListener(listener)

    }

    override fun onResume() {
        super.onResume()
        articleAdapter.notifyDataSetChanged()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        articleDB.removeEventListener(listener)
    }
}