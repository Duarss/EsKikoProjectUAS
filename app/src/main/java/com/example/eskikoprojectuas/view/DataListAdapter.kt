package com.example.eskikoprojectuas.view

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.eskikoprojectuas.databinding.DataListItemBinding
import com.example.eskikoprojectuas.model.Anak

class DataListAdapter(
    val dataList: ArrayList<Anak>
) : RecyclerView.Adapter<DataListAdapter.DataViewHolder>() {

    class DataViewHolder(val binding: DataListItemBinding)
        : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataViewHolder {
        val binding = DataListItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return DataViewHolder(binding)
    }

    override fun getItemCount() = dataList.size

    override fun onBindViewHolder(holder: DataViewHolder, position: Int) {
        val anak = dataList[position]

        holder.binding.txtUsia.text = anak.usia
        holder.binding.txtHeight.text = anak.height
        holder.binding.txtWeight.text = anak.weight
    }

    @SuppressLint("NotifyDataSetChanged")
    fun updateDataList(newDataList: List<Anak>) {
        dataList.clear()
        dataList.addAll(newDataList)
        notifyDataSetChanged()
    }
}
