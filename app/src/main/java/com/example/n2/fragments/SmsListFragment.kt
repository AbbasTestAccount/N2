package com.example.n2.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.n2.R
import com.example.n2.http_service.SmsService2
import com.example.n2.adapter.SmsAdapter
import com.example.n2.adapter.SmsItemInterface
import com.example.n2.databinding.FragmentSmsListBinding
import com.example.n2.repository.SmsRepositoryImp
import com.example.n2.repository.room.MyDatabase
import com.example.n2.repository.room.SmsClass
import com.example.n2.utils.MainViewModelFactory
import com.example.n2.utils.sendSms
import org.koin.android.ext.android.inject

class SmsListFragment : Fragment(), SmsItemInterface {

    lateinit var binding: FragmentSmsListBinding
    private val smsService by inject<SmsService2>()
    private lateinit var smsListFragmentViewModel: SmsListFragmentViewModel


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSmsListBinding.inflate(inflater, container, false)

        smsListFragmentViewModel = ViewModelProvider(
            this,
            MainViewModelFactory(
                SmsRepositoryImp(
                    MyDatabase.getDatabase(requireContext()).smsDao
                )
            )
        )[SmsListFragmentViewModel::class.java]


        smsListFragmentViewModel.getAllSms().observe(requireActivity()) {
            var adapter = SmsAdapter(it as ArrayList<SmsClass>, this)
            binding.recyclerView.adapter = adapter
        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.recyclerView.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)

    }

    override fun onDeleteClicked(menuItem: MenuItem, smsClass: SmsClass) {
        smsService.removeSms(smsClass)

    }

    override fun onArchivedClicked(menuItem: MenuItem, smsClass: SmsClass) {
        TODO("Not yet implemented")
    }

    override fun onResendClicked(menuItem: MenuItem, smsClass: SmsClass) {
        sendSms(smsClass)
    }

    override fun onSmsItemClicked(smsClass: SmsClass) {
        val transaction = parentFragmentManager.beginTransaction()
        transaction.replace(R.id.mainFrame, SmsFragment(smsClass))
        transaction.addToBackStack(null)
        transaction.commit()
    }

}