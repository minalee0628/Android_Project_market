package com.example.appproject.home.lookArticle

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.appproject.data.ItemModel
import com.example.appproject.databinding.FragmentLookArticleBinding
import java.text.DecimalFormat
import java.text.SimpleDateFormat
import java.util.Date

class LookArticleAdapter (val onItemClicked: (ArticlePageModel) -> Unit): ListAdapter<ArticlePageModel, LookArticleAdapter.ViewHolder>(
    diffUtil
) {
    inner class ViewHolder(private val binding: FragmentLookArticleBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(articlePageModel: ArticlePageModel) {
            val priceFormat = DecimalFormat("#,###")

            binding.TextArticleTitle.text = articlePageModel.itemTitle
            binding.textSellerID.text = articlePageModel.sellerId
            binding.TextArticlePrice.text = "${priceFormat.format(articlePageModel.itemPrice.toInt())}원"
            binding.TextArticleContent.text = articlePageModel.itemContent
            binding.TextArticleSellOrNot.text = articlePageModel.sellOrNot

            binding.root.setOnClickListener {
                onItemClicked(articlePageModel)
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(FragmentLookArticleBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(currentList[position])

    }



    companion object {
        val diffUtil = object : DiffUtil.ItemCallback<ArticlePageModel>() {
            override fun areItemsTheSame(oldItem: ArticlePageModel, newItem: ArticlePageModel): Boolean {
                return oldItem.itemTitle == newItem.itemTitle
            }

            override fun areContentsTheSame(oldItem: ArticlePageModel, newItem: ArticlePageModel): Boolean {
                return oldItem.itemContent == newItem.itemContent
            }

        }
    }


}