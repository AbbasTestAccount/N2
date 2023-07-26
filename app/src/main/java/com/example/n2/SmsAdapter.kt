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

            binding.recipients.text = data[position].recipients[0]
            var more = 0
            if (data[position].recipients.size > 1){
                for (i in 1 until data[position].recipients.size){
                    if (i == 1){
                        binding.recipients.text = binding.recipients.text.toString().plus(", ").plus(data[position].recipients[i])
                    }else{
                        more++
                    }
                }
            }
            if (more != 0){
                binding.recipients.text = binding.recipients.text.toString().plus(" and $more more")
            }
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