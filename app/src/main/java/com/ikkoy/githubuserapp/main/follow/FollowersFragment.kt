package com.ikkoy.githubuserapp.main.follow

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.ikkoy.githubuserapp.R
import com.ikkoy.githubuserapp.UserAdapter
import com.ikkoy.githubuserapp.databinding.FragmentFollowBinding

class FollowersFragment : Fragment(R.layout.fragment_follow) {

    private var _binding: FragmentFollowBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: FollowersViewModel
    private lateinit var adapter: UserAdapter
    private lateinit var username: String

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentFollowBinding.bind(view)
    }
}