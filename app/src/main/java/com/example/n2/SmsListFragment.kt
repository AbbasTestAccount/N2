package com.example.n2

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.work.PeriodicWorkRequestBuilder
import androidx.work.WorkInfo
import androidx.work.WorkManager
import androidx.work.workDataOf
import com.example.n2.databinding.FragmentSmsListBinding
import java.util.concurrent.TimeUnit

class SmsListFragment : Fragment() {

    lateinit var binding: FragmentSmsListBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSmsListBinding.inflate(inflater, container, false)

        val workManager = WorkManager.getInstance(container!!.context)


        var smsClass = SmsClass("salam", 2, arrayOf("981236783", "987637773"))

        val smsWorker = PeriodicWorkRequestBuilder<SmsWorker>(10, TimeUnit.SECONDS)
            .setInputData(workDataOf("text" to "salam",
                                        "sourceSim" to 2,
                                       "recipients" to  arrayOf("981236783", "987637773")))
            .addTag("sms send").build()


        Toast.makeText(context, "test", Toast.LENGTH_SHORT).show()
        workManager.enqueue( smsWorker )


        var adapter = SmsAdapter(arrayListOf())

        workManager
            .getWorkInfosByTagLiveData("sms send")
            .observe(viewLifecycleOwner) {

                val smsWorker = it[0]!!
                if(smsWorker.state == WorkInfo.State.SUCCEEDED  ) {
//                    Toast.makeText(requireContext(), "${smsWorker.outputData.getString("isSend")}", Toast.LENGTH_SHORT).show()
                    adapter.addItem(smsClass)
                    binding.recyclerView.adapter = adapter


                }else{
//                    Toast.makeText(requireContext(), "${smsWorker.outputData.getString("isSend")}", Toast.LENGTH_SHORT).show()
                }


            }



        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        binding.recyclerView.layoutManager = LinearLayoutManager(context,LinearLayoutManager.VERTICAL, false)

    }
}