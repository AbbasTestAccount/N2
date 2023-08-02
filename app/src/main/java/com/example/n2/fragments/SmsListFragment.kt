package com.example.n2.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.n2.R
import com.example.n2.http_service.SmsService2
import com.example.n2.adapter.SmsAdapter
import com.example.n2.adapter.SmsItemInterface
import com.example.n2.databinding.FragmentSmsListBinding
import com.example.n2.http_service.oldSendSmsFun
import com.example.n2.room.MyDatabase
import com.example.n2.room.SmsClass
import org.koin.android.ext.android.inject

class SmsListFragment : Fragment(), SmsItemInterface {

    lateinit var binding: FragmentSmsListBinding
    val smsService by inject<SmsService2>()


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSmsListBinding.inflate(inflater, container, false)

        val database = MyDatabase.getDatabase(requireContext()).smsDao



        database.getAllSms().observeForever {
            var adapter = SmsAdapter(it as ArrayList<SmsClass>, this)
            binding.recyclerView.adapter = adapter
        }


        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        binding.recyclerView.layoutManager = LinearLayoutManager(context,LinearLayoutManager.VERTICAL, false)

    }

    override fun onDeleteClicked(menuItem: MenuItem, smsClass: SmsClass) {
        smsService.removeSms(smsClass)

    }

    override fun onArchivedClicked(menuItem: MenuItem, smsClass: SmsClass) {
        TODO("Not yet implemented")
    }

    override fun onResendClicked(menuItem: MenuItem, smsClass: SmsClass) {
        oldSendSmsFun(smsClass)
    }

    override fun onSmsItemClicked(smsClass: SmsClass) {
        openSmsItem(smsClass)
    }

    private fun openSmsItem(smsClass: SmsClass) {
        val transaction = parentFragmentManager.beginTransaction()
        transaction.replace(R.id.mainFrame, SmsFragment(smsClass))
        transaction.addToBackStack(null)
        transaction.commit()
    }
}