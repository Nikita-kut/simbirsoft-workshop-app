package com.nikita.kut.android.simbirsoft_workshop

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.nikita.kut.android.simbirsoft_workshop.data.Friend
import com.nikita.kut.android.simbirsoft_workshop.databinding.FragmentProfileBinding
import com.nikita.kut.android.simbirsoft_workshop.util.MaxCountLayoutManager
import kotlin.random.Random

class ProfileFragment : Fragment() {

    private lateinit var binding: FragmentProfileBinding
    private val friends = arrayListOf(
        Friend(Random.nextLong(), R.drawable.avatar_1, R.string.name_1),
        Friend(Random.nextLong(), R.drawable.avatar_2, R.string.name_2),
        Friend(Random.nextLong(), R.drawable.avatar_3, R.string.name_3),
    )
    private lateinit var friendAdapter: FriendAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentProfileBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        binding.navigationView.selectedItemId = R.id.item_profile
        initFriendList()
    }

    private fun initFriendList() {
        friendAdapter = FriendAdapter()
        with(binding.rvListFriends) {
            adapter = friendAdapter
            layoutManager = LinearLayoutManager(requireContext())
            // установка максимального количества элементов на одном экране, после которого начинается прокрутка списка
            layoutManager = MaxCountLayoutManager(
                requireContext()
            ).apply { setMaxCount(3) }
        }
        friendAdapter.updateListFriends(friends)
    }
}
