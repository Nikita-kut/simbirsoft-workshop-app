package com.nikita.kut.android.simbirsoft_workshop.app.util

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import com.nikita.kut.android.simbirsoft_workshop.R
import java.io.IOException

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

fun getJSONFromAssets(activity: FragmentActivity, fileName: String): String {
    var json: String
    val charset = Charsets.UTF_8
    try {
        val myUserJSONFile = activity.assets.open(fileName)
        val size = myUserJSONFile.available()
        val buffer = ByteArray(size)
        myUserJSONFile.read(buffer)
        myUserJSONFile.close()
        json = String(buffer, charset)
    } catch (ex: IOException) {
        ex.printStackTrace()
        json = String(ByteArray(1), charset)
    }
    return json
}