package com.example.eskikoprojectuas.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import com.example.eskikoprojectuas.databinding.FragmentUkurBinding
import com.example.eskikoprojectuas.model.Anak
import com.example.eskikoprojectuas.viewmodel.ListViewModel
import com.google.android.material.snackbar.Snackbar


class UkurFragment : Fragment(), UkurListener {
    private lateinit var viewModel: ListViewModel
    private lateinit var binding: FragmentUkurBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        binding = FragmentUkurBinding.inflate(inflater,container,false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel =
            ViewModelProvider(this).get(ListViewModel::class.java)

        binding.anak = Anak("", "", "")

        binding.listener = this



    }

    override fun onClick(v: View) {
        val berat = binding.txtBerat.text.toString()
        val tinggi = binding.txtTinggi.text.toString()
        val usia = binding.txtUsia.text.toString()
//
        val anak = Anak(berat, tinggi, usia)
        viewModel.addAnak(anak)
        Snackbar.make(v, "Ukur created", Snackbar.LENGTH_LONG).show()
        v.findNavController().popBackStack()
    }

}