package com.example.n2

class SmsRepositoryImp : SmsRepository {

    private val smsList = ArrayList<SmsClass>()

    override fun addItem(smsItem: SmsClass) {
        smsList.add(smsItem)
    }

    override fun removeItem(smsItem: SmsClass) {
        smsList.remove(smsItem)
    }

    override fun getItems(): ArrayList<SmsClass> {
        return smsList
    }

}