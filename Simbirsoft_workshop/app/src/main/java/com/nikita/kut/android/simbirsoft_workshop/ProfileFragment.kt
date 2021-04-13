package com.nikita.kut.android.simbirsoft_workshop

import android.R.attr
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.os.Bundle
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.nikita.kut.android.simbirsoft_workshop.data.Friend
import com.nikita.kut.android.simbirsoft_workshop.databinding.FragmentProfileBinding
import com.nikita.kut.android.simbirsoft_workshop.util.MaxCountLayoutManager
import kotlin.random.Random


class ProfileFragment : Fragment(), ChangePhotoFragment.ChangePhotoClickListener {

    private lateinit var binding: FragmentProfileBinding
    private val friends = arrayListOf(
        Friend(Random.nextLong(), R.drawable.avatar_1, R.string.name_1),
        Friend(Random.nextLong(), R.drawable.avatar_2, R.string.name_2),
        Friend(Random.nextLong(), R.drawable.avatar_3, R.string.name_3),
    )
    private lateinit var friendAdapter: FriendAdapter
    private val isCameraPermissionGranted: Boolean
        get() {
            return ContextCompat.checkSelfPermission(
                requireContext(),
                android.Manifest.permission.CAMERA
            ) == PackageManager.PERMISSION_GRANTED
        }

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
        binding.ivMan.setOnClickListener { changePhotoShowDialog() }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == PHOTO_REQUEST_CODE && resultCode == Activity.RESULT_OK && data != null && TAKE_OR_PICK == 1) {
            val photo = data.getParcelableExtra("data") as? Bitmap
            binding.ivMan.setImageBitmap(photo)
        } else {
            super.onActivityResult(requestCode, resultCode, data)
        }
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

    private fun changePhotoShowDialog() {
        ChangePhotoFragment().show(childFragmentManager, CHANGE_PHOTO_TAG)
    }

    override fun takePhoto() {
        if (!isCameraPermissionGranted) {
            ActivityCompat.requestPermissions(
                requireActivity(),
                arrayOf(android.Manifest.permission.CAMERA),
                PHOTO_REQUEST_CODE
            )
        } else {
            val takePhotoIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
            takePhotoIntent.resolveActivity(requireActivity().packageManager).also {
                startActivityForResult(takePhotoIntent, PHOTO_REQUEST_CODE)
            }
        }
    }

    override fun deletePhoto() {
        binding.ivMan.setImageResource(R.drawable.ic_no_photo)
    }

    companion object {
        const val CHANGE_PHOTO_TAG = "change_photo_tag"
        const val PHOTO_REQUEST_CODE = 123
        const val TAKE_OR_PICK = 1
    }
}
