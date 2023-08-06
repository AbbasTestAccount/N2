package com.example.n2.utils

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.n2.fragments.SmsListFragmentViewModel
import com.example.n2.repository.SmsRepositoryImp

class MainViewModelFactory(private val smsRepositoryImp: SmsRepositoryImp) :ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return SmsListFragmentViewModel(smsRepositoryImp) as T
    }

}