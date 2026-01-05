package com.example.eskikoprojectuas.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.eskikoprojectuas.databinding.FragmentDataListBinding
import com.example.eskikoprojectuas.model.Anak
import com.example.eskikoprojectuas.viewmodel.DataListViewModel

class DataListFragment : Fragment() {
    private lateinit var viewModel: DataListViewModel
    private val dataListAdapter = DataListAdapter(arrayListOf())
    private lateinit var binding: FragmentDataListBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDataListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // 1) init ViewModel dulu (WAJIB sebelum dipakai)
        viewModel = ViewModelProvider(this)[DataListViewModel::class.java]

        // 2) setup recycler view
        binding.recViewData.layoutManager = LinearLayoutManager(context)
        binding.recViewData.adapter = dataListAdapter

        // 3) observe dulu biar data yang datang langsung ke-render
        observeViewModel()

        // 4) baru trigger load data (ini yang akan "menyentuh" Room DB)
        viewModel.refresh()

        // 5) swipe refresh
        binding.refreshLayout.setOnRefreshListener {
            viewModel.refresh()
            binding.refreshLayout.isRefreshing = false
        }
    }

    private fun observeViewModel() {
        viewModel.anakLD.observe(viewLifecycleOwner, Observer {
            dataListAdapter.updateDataList(it)
        })

        viewModel.loadingLD.observe(viewLifecycleOwner, Observer { isLoading ->
            if (isLoading == true) {
                binding.progressLoad.visibility = View.VISIBLE
                binding.recViewData.visibility = View.INVISIBLE
            } else {
                binding.progressLoad.visibility = View.INVISIBLE
                binding.recViewData.visibility = View.VISIBLE
            }
        })

        viewModel.errorLD.observe(viewLifecycleOwner, Observer { isError ->
            if (isError == true) {
                binding.txtError.text = "Something wrong when load data"
                binding.txtError.visibility = View.VISIBLE
            } else {
                binding.txtError.visibility = View.INVISIBLE
            }
        })
    }
}
