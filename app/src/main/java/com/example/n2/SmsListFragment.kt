package com.example.n2

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.n2.databinding.FragmentSmsListBinding
import ir.shabrangkala.simplelibrary.Adapter.SmsAdapter

class SmsListFragment : Fragment() {

    lateinit var binding: FragmentSmsListBinding
    lateinit var data : ArrayList<SmsClass>

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentSmsListBinding.inflate(inflater, container,false)
        data = arrayListOf()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        binding.recyclerView.adapter = SmsAdapter(data)

    }
}