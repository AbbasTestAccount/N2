package com.example.n2.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.n2.databinding.SmsChipBinding


class SmsItemAdapter(private val recipients : Array<String>) : RecyclerView.Adapter<SmsItemAdapter.SMSItemViewHolder>(){

    lateinit var binding : SmsChipBinding

    inner class SMSItemViewHolder(itemView : View): RecyclerView.ViewHolder(itemView){
        fun bindData(position: Int){
            binding.chip.text = recipients[position]
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SMSItemViewHolder {
        binding = SmsChipBinding.inflate(LayoutInflater.from(parent.context),parent, false)
        return SMSItemViewHolder(binding.root)
    }

    override fun getItemCount(): Int {
        return recipients.size
    }

    override fun onBindViewHolder(holder: SMSItemViewHolder, position: Int) {
        holder.bindData(position)
    }
}