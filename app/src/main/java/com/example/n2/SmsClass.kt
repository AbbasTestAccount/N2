package com.example.n2

data class SmsClass(
    val text : String,
    val sourceSim : Int,
    val recipients : List<String>
)
