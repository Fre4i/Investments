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
import com.overtune.investments.databinding.ItemMessageBinding
import java.text.SimpleDateFormat
import java.util.*

class ChatFragment : Fragment() {

    private lateinit var binding: FragmentChatBinding

    val list = mutableListOf<MessageItem>()

    var userName = ""
    var userNameBool = false

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
        val adapter1 = ChatAdapter(testList(), context)
        binding.chatList.adapter = adapter1

        binding.sendMessage.setOnClickListener {
            val textMes = binding.editTextMessage.text.toString()
            if (textMes != "") {
                val sdf = SimpleDateFormat("hh:mm")
                val currentDate = sdf.format(Date())

                list.add(MessageItem(textMes, currentDate.toString(), "0"))
                if (userName == "" && !userNameBool) {
                    list.add(
                        MessageItem(
                            "Здравствуйте, как к вам обращаться?",
                            currentDate,
                            "1"
                        )
                    )
                    userNameBool = true
                } else if (userNameBool && userName == ""){
                    userName = textMes
                    list.add(MessageItem("Хорошо, $userName", currentDate, "1"))
                    userNameBool = false
                } else {
                    when(textMes) {
                        "Хочу подписать договор" -> {
                            list.add(MessageItem("$userName, прикрепите документ", currentDate, "1"))
                        }
                    }
                }
                binding.chatList.adapter = ChatAdapter(list, context)
                binding.chatList.getAdapter()!!.notifyItemInserted(list.size)
                binding.editTextMessage.setText("")
            }
        }

        binding.pinFile.setOnClickListener {
            val sdf = SimpleDateFormat("hh:mm")
            val currentDate = sdf.format(Date())
            list.add(MessageItem("Договор.pdf", currentDate.toString(), "0", true))

            list.add(MessageItem("Выберите опцию ниже:                 ", currentDate, "1", true))

            binding.chatList.adapter = ChatAdapter(list, context)
            binding.chatList.getAdapter()!!.notifyItemInserted(list.size)
            binding.editTextMessage.setText("")
        }

    }

    private fun testList(): MutableList<MessageItem> {

//        list.add(MessageItem("Как я могу к Вам обращаться?", "4:19", "1"))
//        list.add(MessageItem("Можете звать меня Мария", "4:19", "0"))
        return list
    }
}