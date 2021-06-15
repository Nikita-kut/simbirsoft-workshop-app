package com.nikita.kut.android.simbirsoft_workshop.feature.profile.presentation

import androidx.lifecycle.ViewModel
import com.nikita.kut.android.simbirsoft_workshop.R
import com.nikita.kut.android.simbirsoft_workshop.feature.profile.model.Friend
import kotlin.random.Random

class ProfileViewModel : ViewModel() {

    val friendList = arrayListOf(
        Friend(Random.nextLong(), R.drawable.avatar_1, R.string.name_1),
        Friend(Random.nextLong(), R.drawable.avatar_2, R.string.name_2),
        Friend(Random.nextLong(), R.drawable.avatar_3, R.string.name_3),
    )


}