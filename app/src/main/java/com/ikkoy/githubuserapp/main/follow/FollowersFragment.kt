package com.ikkoy.githubuserapp.main.follow

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.ikkoy.githubuserapp.R
import com.ikkoy.githubuserapp.UserAdapter
import com.ikkoy.githubuserapp.databinding.FragmentFollowBinding
import com.ikkoy.githubuserapp.main.detail.DetailUsersActivity

class FollowersFragment : Fragment(R.layout.fragment_follow) {

    private var _binding: FragmentFollowBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: FollowersViewModel
    private lateinit var adapter: UserAdapter
    private lateinit var username: String

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentFollowBinding.bind(view)
        setData()
    }
    private fun setData() {
        val args = arguments
        username = args?.getString(DetailUsersActivity.EXTRA_USERNAME).toString()

        adapter = UserAdapter()
        adapter.notifyDataSetChanged()

        binding.apply {
            fragmentRv.setHasFixedSize(true)
            fragmentRv.layoutManager = LinearLayoutManager(activity)
            fragmentRv.adapter = adapter
        }

        viewModel = ViewModelProvider(
            this,
            ViewModelProvider.NewInstanceFactory()
        )[FollowersViewModel::class.java]
        viewModel.getFollowers(username)
        viewModel.getUserFollowers().observe(viewLifecycleOwner) {
            if (it != null) {
                adapter.setList(it)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}