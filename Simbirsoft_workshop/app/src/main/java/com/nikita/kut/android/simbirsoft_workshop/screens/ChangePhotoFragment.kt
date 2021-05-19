package com.nikita.kut.android.simbirsoft_workshop.screens

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import android.view.View
import android.widget.LinearLayout
import androidx.fragment.app.DialogFragment
import com.nikita.kut.android.simbirsoft_workshop.R

class ChangePhotoFragment : DialogFragment() {

    private val dialogView: View by lazy {
        requireActivity().layoutInflater.inflate(
            R.layout.fragment_change_photo,
            null
        )
    }
    private val listener: ChangePhotoClickListener by lazy { parentFragment as ChangePhotoClickListener }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val dialog = AlertDialog.Builder(context).setView(dialogView)
        dialogView.findViewById<LinearLayout>(R.id.choose_photo)
            .setOnClickListener {
                listener.pickPictureFromGallery()
                dismiss()
            }
        dialogView.findViewById<LinearLayout>(R.id.take_photo)
            .setOnClickListener {
                listener.takePhoto()
                dismiss()
            }
        dialogView.findViewById<LinearLayout>(R.id.delete)
            .setOnClickListener {
                listener.deletePhoto()
                dismiss()
            }
        return dialog.create()
    }

    interface ChangePhotoClickListener {
        fun pickPictureFromGallery()
        fun takePhoto()
        fun deletePhoto()
    }
}