package com.example.eskikoprojectuas.view

import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.example.eskikoprojectuas.databinding.FragmentProfileBinding
import com.example.eskikoprojectuas.model.Anak
import com.example.eskikoprojectuas.util.buildDb
import com.example.eskikoprojectuas.viewmodel.ProfileViewModel
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.util.Calendar

class ProfileFragment : Fragment(), ProfileListener {
    private lateinit var viewModel: ProfileViewModel
    private lateinit var binding: FragmentProfileBinding

    // Inisialisasi SharedPreferences
//    private val PREF_FILE = "child_profile"
//    private val NAME_KEY = "profile_name"
//    private val BOD_KEY = "profile_bod"
//    private val GENDER_KEY = "profile_gender"

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentProfileBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(this)[ProfileViewModel::class.java]

        binding.lifecycleOwner = viewLifecycleOwner
        binding.vm = viewModel
        binding.listener = this

        viewModel.loadProfile()
    }

    @SuppressLint("DefaultLocale")
    private fun showDatePicker(onPicked: (String) -> Unit) {
        val cal = Calendar.getInstance()
        DatePickerDialog(
            requireContext(),
            { _, y, m, d ->
                onPicked(String.format("%02d/%02d/%04d", d, m + 1, y))
            },
            cal.get(Calendar.YEAR),
            cal.get(Calendar.MONTH),
            cal.get(Calendar.DAY_OF_MONTH)
        ).show()
    }

    private fun onGenderSelect() {
        val isMale = binding.btnRadioMale.isChecked
        val isFemale = binding.btnRadioFemale.isChecked
        viewModel.genderLD.value = when {
            isMale -> true
            isFemale -> false
            else -> null
        }
    }

    override fun onSave(v: View) {
        Snackbar.make(
            binding.root,
            "DEBUG name=${viewModel.nameLD.value} dob=${viewModel.doBLD.value} gender=${viewModel.genderLD.value}",
            Snackbar.LENGTH_LONG
        ).show()

        val ok = viewModel.saveProfile()
        if (ok) Snackbar.make(binding.root, "Profil tersimpan", Snackbar.LENGTH_LONG).show()
        else Snackbar.make(binding.root, "Lengkapi nama, tanggal lahir, dan pilih jenis kelamin", Snackbar.LENGTH_LONG).show()
    }

    override fun onPickDate(v: View) {
        Snackbar.make(binding.root, "Pick date clicked", Snackbar.LENGTH_LONG).show()
        showDatePicker {
            pickedDate -> viewModel.doBLD.value = pickedDate

//            binding.txtInputEditBoD.setText(pickedDate)
        }
    }

    override fun onGenderSelect(v: View) {
        onGenderSelect()
    }

//    private fun loadProfile() {
//        val prefs = requireContext().getSharedPreferences(PREF_FILE, Context.MODE_PRIVATE)
//        val name = prefs.getString(NAME_KEY, "") ?: ""
//        val bod = prefs.getString(BOD_KEY, "") ?: ""
//        val gender = prefs.getString(GENDER_KEY, "") ?: ""
//
//        binding.txtInputEditName.setText(name)
//        binding.txtInputEditBoD.setText(bod)
//
//        when (gender) {
//            "Laki-Laki" -> binding.btnRadioMale.isChecked = true
//            "Perempuan" -> binding.btnRadioFemale.isChecked = true
//            else -> binding.btnRadioGenders.clearCheck()
//        }
//    }

//    @SuppressLint("UseKtx")
//    private fun saveProfile() {
//        val name = binding.txtInputEditName.text.toString()
//        val bod = binding.txtInputEditBoD.text.toString()
//        val gender = when (binding.btnRadioGenders.checkedRadioButtonId) {
//            binding.btnRadioMale.id -> "Laki-Laki"
//            binding.btnRadioFemale.id -> "Perempuan"
//            else -> ""
//        }
//
//        // Validasi
//        if (name.isEmpty()) {
//            binding.txtInputEditName.error = "Nama tidak boleh kosong"
//            return
//        }
//
//        if (bod.isEmpty()) {
//            binding.txtInputEditBoD.error = "Tanggal Lahir tidak boleh kosong"
//            return
//        }
//
//        if (gender.isEmpty()) {
//            Toast.makeText(requireContext(), "Pilih jenis kelamin", Toast.LENGTH_SHORT).show()
//            return
//        }
//
//        val prefs = requireContext().getSharedPreferences(PREF_FILE, Context.MODE_PRIVATE)
//        prefs.edit()
//            .putString(NAME_KEY, name)
//            .putString(BOD_KEY, bod)
//            .putString(GENDER_KEY, gender)
//            .apply()
//
//        Toast.makeText(requireContext(), "Profil tersimpan", Toast.LENGTH_SHORT).show()
//    }
}