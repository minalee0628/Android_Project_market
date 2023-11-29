package com.example.appproject.home.addArticle

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.appproject.data.ItemModel
import com.example.appproject.databinding.RecyclerItemBinding
import java.text.DecimalFormat
import java.text.SimpleDateFormat
import java.util.Date


class ItemAddAdapter (val onItemClicked: (ItemModel) -> Unit): ListAdapter<ItemModel, ItemAddAdapter.ViewHolder>(
    diffUtil
) {
    inner class ViewHolder(private val binding: RecyclerItemBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(articleModel: ItemModel) {
            val format = SimpleDateFormat("MM월 dd일")
            val date = Date(articleModel.date)
            val priceFormat = DecimalFormat("#,###")

            binding.titleTextView.text = articleModel.title
            binding.dateTextView.text = format.format(date).toString()
            binding.priceTextView.text = "${priceFormat.format(articleModel.price.toInt())}원"
            binding.sellOrNotView.text = articleModel.sellOrNot

            binding.root.setOnClickListener {
                onItemClicked(articleModel)
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(RecyclerItemBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(currentList[position])

    }


    companion object {
        val diffUtil = object : DiffUtil.ItemCallback<ItemModel>() {
            override fun areItemsTheSame(oldItem: ItemModel, newItem: ItemModel): Boolean {
                return oldItem.date == newItem.date
            }

            override fun areContentsTheSame(oldItem: ItemModel, newItem: ItemModel): Boolean {
                return oldItem.content == newItem.content
            }

        }
    }


}