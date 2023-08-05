package com.example.n2.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.PopupMenu
import androidx.annotation.MenuRes
import androidx.recyclerview.widget.RecyclerView
import com.example.n2.R
import com.example.n2.databinding.SmsItemBinding
import com.example.n2.repository.room.SmsClass


class SmsAdapter(private val data: ArrayList<SmsClass>, private val smsItemInterface: SmsItemInterface?):RecyclerView.Adapter<SmsAdapter.SMSViewHolder>() {
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

            binding.date.text = data[position].date

            binding.more.setOnClickListener {
                showMenu(it, R.menu.sms_item_menu,context, data[position])
            }

            binding.item.setOnClickListener {
                smsItemInterface!!.onSmsItemClicked(data[position])
            }


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

    private fun showMenu(v: View, @MenuRes menuRes: Int, context: Context, smsClass: SmsClass) {
        val popup = PopupMenu(context, v)
        popup.menuInflater.inflate(menuRes, popup.menu)

        popup.setOnMenuItemClickListener {
            when(it.itemId){
                R.id.delete ->{
                    smsItemInterface!!.onDeleteClicked(it, smsClass)
                }
                R.id.archive ->{
                    smsItemInterface!!.onArchivedClicked(it, smsClass)
                }
                R.id.resend ->{
                    smsItemInterface!!.onResendClicked(it, smsClass)
                }
            }
            true

        }
        popup.setOnDismissListener {
            // Respond to popup being dismissed.
        }
        // Show the popup menu.
        popup.show()
    }
}

interface SmsItemInterface{
    fun onDeleteClicked(menuItem: MenuItem, smsClass: SmsClass)
    fun onArchivedClicked(menuItem: MenuItem, smsClass: SmsClass)
    fun onResendClicked(menuItem: MenuItem, smsClass: SmsClass)

    fun onSmsItemClicked(smsClass: SmsClass)
}