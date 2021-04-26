package com.nikita.kut.android.simbirsoft_workshop.util

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import com.nikita.kut.android.simbirsoft_workshop.R

fun ViewGroup.inflate(@LayoutRes layoutRes: Int, attachToRoot: Boolean = false): View {
    return LayoutInflater.from(context).inflate(layoutRes, this, attachToRoot)
}

fun Fragment.openFragment(activity: FragmentActivity) {
    activity.supportFragmentManager.beginTransaction()
        .replace(R.id.fragment_main_container, this).commit()
}

fun Fragment.openFragmentWithAddBackStack(activity: FragmentActivity) {
    activity.supportFragmentManager.beginTransaction()
        .replace(R.id.fragment_main_container, this)
        .addToBackStack(null)
        .commit()
}