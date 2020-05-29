package com.example.myapplication.app.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.myapplication.R

class WelcomeFragment : Fragment() {

    companion object {
        fun newInstance() = WelcomeFragment()
    }

    private lateinit var viewModel: WelcomeViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val inflate = inflater.inflate(R.layout.welcome_fragment, container, false)
        val btnLogin = inflate.findViewById<Button>(R.id.btn_login)
        btnLogin.setOnClickListener {
            findNavController().navigate(R.id.loginFragment)
        }
        val btnRegister = inflate.findViewById<Button>(R.id.btn_register)
        btnRegister.setOnClickListener {
            findNavController().navigate(R.id.registerFragment)
        }
        return inflate
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel= ViewModelProvider(this)[WelcomeViewModel::class.java]
        // TODO: Use the ViewModel
    }

}
