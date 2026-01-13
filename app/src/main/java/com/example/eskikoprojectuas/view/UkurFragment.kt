package com.example.eskikoprojectuas.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.findNavController
import com.example.eskikoprojectuas.databinding.FragmentUkurBinding
import com.example.eskikoprojectuas.model.Anak
import com.example.eskikoprojectuas.util.buildDb
import com.example.eskikoprojectuas.viewmodel.UkurViewModel
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import com.example.eskikoprojectuas.R

class UkurFragment : Fragment(), UkurListener {
    private lateinit var viewModel: UkurViewModel
    private lateinit var binding: FragmentUkurBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        binding = FragmentUkurBinding.inflate(inflater,container,false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this)[UkurViewModel::class.java]

        binding.anak = Anak("", "", "")
        binding.vm = viewModel
        binding.listener = this
    }

    override fun onClick(v: View) {
        val berat = binding.txtBerat.text.toString()
        val tinggi = binding.txtTinggi.text.toString()
        val usia = binding.txtUsia.text.toString()

        // Validasi sederhana
        if (berat.isEmpty() || tinggi.isEmpty() || usia.isEmpty()) {
            Snackbar.make(v, "Isi berat, tinggi, dan usia terlebih dahulu.", Snackbar.LENGTH_LONG).show()
            return
        }

        // Add data ukur
        viewModel.addUkur(
            Anak(
                weight = berat,
                height = tinggi,
                usia = usia
            )
        )

        Snackbar.make(v, "Data ukur tersimpan.", Snackbar.LENGTH_LONG).show()
        v.findNavController().popBackStack(R.id.itemData, false)
    }
}