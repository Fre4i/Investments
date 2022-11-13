package com.overtune.investments.fragments

import android.os.Build
import android.os.Bundle
import android.text.format.Time
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.overtune.investments.adapters.ChatAdapter
import com.overtune.investments.adapters.MessageItem
import com.overtune.investments.databinding.FragmentChatBinding
import java.text.SimpleDateFormat
import java.util.*

class ChatFragment : Fragment() {

    private lateinit var binding: FragmentChatBinding

    val list = mutableListOf<MessageItem>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentChatBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val layoutManager = LinearLayoutManager(context)
        binding.chatList.layoutManager = layoutManager
        val adapter = ChatAdapter(testList(), context)
        binding.chatList.adapter = adapter

        binding.sendMessage.setOnClickListener {
            val textMes = binding.editTextMessage.text.toString()
            if (textMes != "") {
                val sdf = SimpleDateFormat("hh:mm")
                val currentDate = sdf.format(Date())

                list.add(MessageItem(textMes, currentDate.toString(), "0"))

                binding.chatList.adapter = ChatAdapter(list, context)
                binding.chatList.getAdapter()!!.notifyItemInserted(list.size - 1)
                binding.editTextMessage.setText("")
            }
        }

    }
    private fun testList() :List<MessageItem>{

//        list.add(MessageItem("Как я могу к Вам обращаться?", "4:19", "1"))
//        list.add(MessageItem("Можете звать меня Мария", "4:19", "0"))
        return list
    }
}