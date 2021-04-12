package com.nikita.kut.android.simbirsoft_workshop

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import android.view.View
import android.widget.LinearLayout
import androidx.fragment.app.DialogFragment

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
                dismiss()
            }
        dialogView.findViewById<LinearLayout>(R.id.take_photo)
            .setOnClickListener {
                listener.takePhoto()
            }
        dialogView.findViewById<LinearLayout>(R.id.delete)
            .setOnClickListener {
                listener.deletePhoto()
            }
        return dialog.create()
    }

    interface ChangePhotoClickListener {
        fun takePhoto()
        fun deletePhoto()
    }
}