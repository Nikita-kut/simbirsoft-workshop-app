package com.nikita.kut.android.simbirsoft_workshop

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.nikita.kut.android.simbirsoft_workshop.data.CategoriesOfHelp
import com.nikita.kut.android.simbirsoft_workshop.data.SharedPreferenceModel
import com.nikita.kut.android.simbirsoft_workshop.databinding.FragmentFilterBinding
import com.nikita.kut.android.simbirsoft_workshop.util.openFragment

class FilterFragment : Fragment() {

    private lateinit var binding: FragmentFilterBinding

    private var listener: ClickListener? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentFilterBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is ClickListener) {
            listener = context
        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        SharedPreferenceModel.with(requireActivity().application)
        binding.bnvFilter.selectedItemId = R.id.item_news
        setBottomNavViewListener()
        setMenu()
        setSwitchChecked()

        binding.switchChildren.setOnCheckedChangeListener { buttonView, isChecked ->
            if (isChecked) {
                SharedPreferenceModel.apply {
                    put(CategoriesOfHelp.CHILDREN, KEY_CHILDREN)
                    put(true, KEY_CHILDREN_BOOLEAN)
                }
            } else {
                SharedPreferenceModel.apply {
                    preferences.edit().remove(KEY_CHILDREN).apply()
                    put(false, KEY_CHILDREN_BOOLEAN)
                }
            }
        }
        binding.switchAdult.setOnCheckedChangeListener { buttonView, isChecked ->
            if (isChecked) {
                SharedPreferenceModel.apply {
                    put(CategoriesOfHelp.ADULT, KEY_ADULT)
                    put(true, KEY_ADULT_BOOLEAN)
                }
            } else {
                SharedPreferenceModel.apply {
                    preferences.edit().remove(KEY_ADULT).apply()
                    put(false, KEY_ADULT_BOOLEAN)
                }
            }
        }
        binding.switchElderly.setOnCheckedChangeListener { buttonView, isChecked ->
            if (isChecked) {
                SharedPreferenceModel.apply {
                    put(CategoriesOfHelp.ELDERLY, KEY_ELDERLY)
                    put(true, KEY_ELDERLY_BOOLEAN)
                }
            } else {
                SharedPreferenceModel.apply {
                    preferences.edit().remove(KEY_ELDERLY).apply()
                    put(false, KEY_ELDERLY_BOOLEAN)
                }
            }
        }
        binding.switchAnimals.setOnCheckedChangeListener { buttonView, isChecked ->
            if (isChecked) {
                SharedPreferenceModel.apply {
                    put(CategoriesOfHelp.ANIMALS, KEY_ANIMALS)
                    put(true, KEY_ANIMALS_BOOLEAN)
                }
            } else {
                SharedPreferenceModel.apply {
                    preferences.edit().remove(KEY_ANIMALS).apply()
                    put(false, KEY_ANIMALS_BOOLEAN)
                }

            }
        }
        binding.switchEvents.setOnCheckedChangeListener { buttonView, isChecked ->
            if (isChecked) {
                SharedPreferenceModel.apply {
                    put(CategoriesOfHelp.EVENTS, KEY_EVENTS)
                    put(true, KEY_EVENTS_BOOLEAN)
                }
            } else {
                SharedPreferenceModel.apply {
                    preferences.edit().remove(KEY_EVENTS).apply()
                    put(false, KEY_EVENTS_BOOLEAN)
                }
            }
        }
    }

    private fun setSwitchChecked() {
        binding.switchChildren.isChecked =
            SharedPreferenceModel.get<Boolean>(KEY_CHILDREN_BOOLEAN) ?: false
        binding.switchAdult.isChecked =
            SharedPreferenceModel.get<Boolean>(KEY_ADULT_BOOLEAN) ?: false
        binding.switchElderly.isChecked =
            SharedPreferenceModel.get<Boolean>(KEY_ELDERLY_BOOLEAN) ?: false
        binding.switchAnimals.isChecked =
            SharedPreferenceModel.get<Boolean>(KEY_ANIMALS_BOOLEAN) ?: false
        binding.switchEvents.isChecked =
            SharedPreferenceModel.get<Boolean>(KEY_EVENTS_BOOLEAN) ?: false
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
                    R.id.item_profile -> {
                        ProfileFragment().openFragment(requireActivity())
                        true
                    }
                    else -> false
                }
            }
        binding.bnvFilter.setOnNavigationItemSelectedListener(onNavigateItemSelectListener)
    }

    private fun setMenu() {
        with(binding.toolbarFilter) {
            setOnMenuItemClickListener {
                when (it.itemId) {
                    R.id.item_accept -> {
                        listener?.onCheckClick()
                        requireFragmentManager().popBackStack()
                        true
                    }
                    else -> false
                }
            }
            setNavigationOnClickListener {
                requireFragmentManager().popBackStack()
            }
        }
    }

    companion object {
        const val KEY_CHILDREN = "KEY_CHILDREN"
        const val KEY_CHILDREN_BOOLEAN = "KEY_CHILDREN_BOOLEAN"
        const val KEY_ADULT = "KEY_ADULT"
        const val KEY_ADULT_BOOLEAN = "KEY_ADULT_BOOLEAN"
        const val KEY_ELDERLY = "KEY_ELDERLY"
        const val KEY_ELDERLY_BOOLEAN = "KEY_ELDERLY_BOOLEAN"
        const val KEY_ANIMALS = "KEY_ANIMALS"
        const val KEY_ANIMALS_BOOLEAN = "KEY_ANIMALS_BOOLEAN"
        const val KEY_EVENTS = "KEY_EVENTS"
        const val KEY_EVENTS_BOOLEAN = "KEY_EVENTS_BOOLEAN"
    }

    interface ClickListener {
        fun onCheckClick()
    }


}

