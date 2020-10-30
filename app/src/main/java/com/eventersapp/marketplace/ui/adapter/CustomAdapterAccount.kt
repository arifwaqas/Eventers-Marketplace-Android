package com.eventersapp.marketplace.ui.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.eventersapp.marketplace.R
import com.eventersapp.marketplace.data.model.Account
import com.eventersapp.marketplace.databinding.ListItemAccountBinding
import com.eventersapp.marketplace.ui.viewmodel.AccountSettingsViewModel

class CustomAdapterAccount(private val viewModel: AccountSettingsViewModel) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val myAccountList = ArrayList<Account>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        val binding: ListItemAccountBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context), R.layout.list_item_account, parent,
            false
        )
        return ItemViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is ItemViewHolder -> {
                myAccountList[position].let { holder.bindItems(it) }
            }
        }
    }

    override fun getItemCount(): Int {
        return myAccountList.size
    }

    fun setData(newMyAccount: ArrayList<Account>) {
        myAccountList.clear()
        myAccountList.addAll(newMyAccount)
        notifyDataSetChanged()
    }

    inner class ItemViewHolder(val binding: ListItemAccountBinding) :
        RecyclerView.ViewHolder(binding.root) {

        @SuppressLint("SetTextI18n")
        fun bindItems(myAccount: Account) {
            binding.apply {
                radioButtonSelectAccount.isClickable = false
                textAccountName.text = myAccount.name
                myAccount.isSelected?.let {
                    radioButtonSelectAccount.isChecked = it
                }
                cardViewAccount.setOnClickListener {
                    if (!radioButtonSelectAccount.isChecked) {
                        viewModel.updateAccountDetail(
                            myAccount.id,
                            myAccount.name,
                            myAccount.accountAddress,
                            myAccount.passphrase,
                            true
                        )
                    }
                }
            }
        }

    }


}