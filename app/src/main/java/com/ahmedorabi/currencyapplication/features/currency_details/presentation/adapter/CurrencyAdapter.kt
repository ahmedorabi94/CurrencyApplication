package com.ahmedorabi.currencyapplication.features.currency_details.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.ahmedorabi.currencyapplication.core.domain.model.CurrencyDbModel
import com.ahmedorabi.currencyapplication.databinding.CurrencyItemBinding

class CurrencyAdapter :
    ListAdapter<CurrencyDbModel, CurrencyAdapter.MyViewHolder>(DiffCallback) {


    companion object DiffCallback : DiffUtil.ItemCallback<CurrencyDbModel>() {
        override fun areItemsTheSame(oldItem: CurrencyDbModel, newItem: CurrencyDbModel): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(
            oldItem: CurrencyDbModel,
            newItem: CurrencyDbModel
        ): Boolean {
            return oldItem.fromName == newItem.fromName
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding =
            CurrencyItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)

    }


    class MyViewHolder(private var binding: CurrencyItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: CurrencyDbModel) {
            binding.currencyFromName.text = item.fromName
            binding.currencyToName.text = item.ToName
        }

    }


}