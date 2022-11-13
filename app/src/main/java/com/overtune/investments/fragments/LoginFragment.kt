package com.overtune.investments.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.overtune.investments.MainActivity
import com.overtune.investments.R
import com.overtune.investments.databinding.FragmentLoginBinding
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.URL

class LoginFragment : Fragment() {

    private lateinit var binding: FragmentLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.signInButton.setOnClickListener {
            launchNext()
        }
    }

    private fun launchNext() {
        val fragment = ChatFragment()
        parentFragmentManager
            .beginTransaction()
            .addToBackStack(null) // добавление фрагмента в стек
            .replace(R.id.fragment_container_view, fragment) // скрыть текущий экран и показать следующий. .add накладывает экраны друг на друга
            .commit() // подтверждение изменений
    }
}