package com.nikita.kut.android.simbirsoft_workshop.screens

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
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.nikita.kut.android.simbirsoft_workshop.R
import com.nikita.kut.android.simbirsoft_workshop.adapters.FriendAdapter
import com.nikita.kut.android.simbirsoft_workshop.databinding.FragmentProfileBinding
import com.nikita.kut.android.simbirsoft_workshop.util.MaxCountLayoutManager
import com.nikita.kut.android.simbirsoft_workshop.util.openFragment
import com.nikita.kut.android.simbirsoft_workshop.viewmodel.ProfileViewModel

class ProfileFragment : Fragment(), ChangePhotoFragment.ChangePhotoClickListener {

    private lateinit var binding: FragmentProfileBinding

    private val friendAdapter: FriendAdapter
        get() = binding.rvListFriends.adapter as FriendAdapter

    private val profileViewModel: ProfileViewModel by activityViewModels()

    private val isCameraPermissionGranted: Boolean
        get() {
            return ContextCompat.checkSelfPermission(
                requireContext(),
                android.Manifest.permission.CAMERA
            ) == PackageManager.PERMISSION_GRANTED
        }

    private val isGalleryPermissionGranted: Boolean
        get() {
            return ContextCompat.checkSelfPermission(
                requireContext(),
                android.Manifest.permission.READ_EXTERNAL_STORAGE
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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.navigationView.selectedItemId = R.id.item_profile
        initFriendList()
        binding.ivMan.setOnClickListener { changePhotoShowDialog() }
        setBottomNavViewListener()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (resultCode == Activity.RESULT_OK && data != null && TAKE_OR_PICK == 1) {
            when (requestCode) {
                TAKE_PHOTO_REQUEST_CODE -> {
                    val photo = data.getParcelableExtra("data") as? Bitmap
                    binding.ivMan.setImageBitmap(photo)
                }
                PICK_PHOTO_REQUEST_CODE -> {
                    binding.ivMan.setImageURI(data.data)
                }
            }
        } else {
            super.onActivityResult(requestCode, resultCode, data)
        }
    }

    private fun initFriendList() {
        with(binding.rvListFriends) {
            adapter = FriendAdapter()
            layoutManager = LinearLayoutManager(requireContext())
            // установка максимального количества элементов на одном экране, после которого начинается прокрутка списка
            layoutManager = MaxCountLayoutManager(
                requireContext()
            ).apply { setMaxCount(3) }
        }
        friendAdapter.updateListFriends(profileViewModel.friendList)
    }

    private fun setBottomNavViewListener() {
        val onNavigateItemSelectListener =
            BottomNavigationView.OnNavigationItemSelectedListener { item ->
                return@OnNavigationItemSelectedListener when (item.itemId) {
                    R.id.item_news -> {
                        NewsFragment().openFragment(requireActivity())
                        true
                    }
                    R.id.item_search -> {
                        SearchFragment().openFragment(requireActivity())
                        true
                    }
                    R.id.item_help -> {
                        HelpFragment().openFragment(requireActivity())
                        true
                    }
                    R.id.item_history -> false
                    R.id.item_profile -> false
                    else -> false
                }
            }
        binding.navigationView.setOnNavigationItemSelectedListener(onNavigateItemSelectListener)
    }

    private fun changePhotoShowDialog() {
        ChangePhotoFragment().show(childFragmentManager, CHANGE_PHOTO_TAG)
    }

    override fun pickPictureFromGallery() {
        if (!isGalleryPermissionGranted) {
            ActivityCompat.requestPermissions(
                requireActivity(),
                arrayOf(android.Manifest.permission.READ_EXTERNAL_STORAGE), PICK_PHOTO_REQUEST_CODE
            )
        } else {
            val pickGalleryPicture = Intent(Intent.ACTION_PICK)
            pickGalleryPicture.type = "image/*"
            startActivityForResult(pickGalleryPicture, PICK_PHOTO_REQUEST_CODE)
        }
    }

    override fun takePhoto() {
        if (!isCameraPermissionGranted) {
            ActivityCompat.requestPermissions(
                requireActivity(),
                arrayOf(android.Manifest.permission.CAMERA),
                TAKE_PHOTO_REQUEST_CODE
            )
        } else {
            val takePhotoIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
            takePhotoIntent.resolveActivity(requireActivity().packageManager).also {
                startActivityForResult(takePhotoIntent, TAKE_PHOTO_REQUEST_CODE)
            }
        }
    }

    override fun deletePhoto() {
        binding.ivMan.setImageResource(R.drawable.ic_no_photo)
    }

    companion object {
        const val CHANGE_PHOTO_TAG = "change_photo_tag"
        const val TAKE_PHOTO_REQUEST_CODE = 123
        const val PICK_PHOTO_REQUEST_CODE = 321
        const val TAKE_OR_PICK = 1
    }
}
