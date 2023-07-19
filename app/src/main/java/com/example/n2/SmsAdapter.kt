package com.example.n2

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.n2.databinding.SmsItemBinding


class SmsAdapter(private val data : ArrayList<SmsClass>):RecyclerView.Adapter<SmsAdapter.SMSViewHolder>() {
    lateinit var binding: SmsItemBinding

    inner class SMSViewHolder(itemView : View, private val context: Context): RecyclerView.ViewHolder(itemView){


        fun bindData(position: Int){


            binding.sourceSim.text = data[position].sourceSim.toString()

            val temp = ""
            for (i in 0 until data[position].recipients.size){
                Toast.makeText(context, data[position].recipients[i], Toast.LENGTH_SHORT).show()
                temp.plus(", ").plus(data[position].recipients[i])
            }
            binding.recipients.text = temp
            binding.TextMassage.text = data[position].text


        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SMSViewHolder {
        binding = SmsItemBinding.inflate(LayoutInflater.from(parent.context),parent, false)
        return SMSViewHolder(binding.root, parent.context)

    }

    override fun onBindViewHolder(holder: SMSViewHolder, position: Int) {
        holder.bindData(position)
    }



    override fun getItemCount(): Int {
        return data.size
    }

    fun addItem(smsClass: SmsClass){
        data.add(smsClass)
        notifyItemInserted(0)
    }
}