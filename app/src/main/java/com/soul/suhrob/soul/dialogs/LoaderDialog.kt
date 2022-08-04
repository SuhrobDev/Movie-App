package com.soul.suhrob.soul.dialogs

import android.content.Context
import android.view.LayoutInflater
import androidx.appcompat.app.AlertDialog
import com.soul.suhrob.soul.databinding.DialogLoaderBinding

class LoaderDialog(context: Context) : AlertDialog(context) {
    val binding = DialogLoaderBinding.inflate(LayoutInflater.from(context))

    init {
        window?.setBackgroundDrawableResource(android.R.color.transparent)
        setCancelable(false)
        setView(binding.root)
    }
}