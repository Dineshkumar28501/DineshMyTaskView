package com.sgs.mytaskview

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.sgs.mytaskview.database.LoginModel
import com.sgs.mytaskview.databinding.FragmentLoginAdapterBinding

class LoginAdapter : RecyclerView.Adapter<LoginAdapter.SampleViewHolder>(){

    @SuppressLint("SuspiciousIndentation")
    inner class SampleViewHolder(private val binding: FragmentLoginAdapterBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun setViews(materialRequestModel: LoginModel) {
            binding.name.text = materialRequestModel.username
            binding.password.text = materialRequestModel.password
            binding.firstName.text = materialRequestModel.first
            binding.lastName.text = materialRequestModel.last
            binding.mobile.text = materialRequestModel.mobile
        }


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SampleViewHolder {
        return SampleViewHolder(
            FragmentLoginAdapterBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: SampleViewHolder, position: Int) {
        val sample = differ.currentList[position]
        holder.setViews(sample)
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    private val callBack = object : DiffUtil.ItemCallback<LoginModel>() {
        override fun areItemsTheSame(
            oldItem: LoginModel,
            newItem: LoginModel
        ): Boolean {
            return oldItem.password == newItem.password
        }

        override fun areContentsTheSame(
            oldItem: LoginModel,
            newItem: LoginModel
        ): Boolean {
            return oldItem == newItem
        }

    }

    val differ = AsyncListDiffer(this, callBack)
}