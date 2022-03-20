package com.wt.spacepokemon

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import com.wt.spacepokemon.adapters.SpaceCraftAdapter
import com.wt.spacepokemon.databinding.SpaceCraftFragmentBinding
import com.wt.spacepokemon.viewmodels.SpaceCraftViewModel
import dagger.hilt.android.AndroidEntryPoint

/**
 *  author : William Tsai
 *  date : 2022/3/17
 *  description : show craft list
 */
@AndroidEntryPoint
class SpaceCraftFragment : Fragment() {

    companion object {
        fun newInstance() = SpaceCraftFragment()
    }

    private val viewModel: SpaceCraftViewModel by viewModels()
    private val spaceCraftAdapter by lazy {
        SpaceCraftAdapter()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = SpaceCraftFragmentBinding.inflate(inflater, container, false)
        binding.run {
            rvCraftList.layoutManager = GridLayoutManager(requireContext(), 2)
            rvCraftList.adapter = spaceCraftAdapter
        }

        viewModel.run {
            craftList.observe(viewLifecycleOwner, Observer {
                //update craft list
                spaceCraftAdapter.setData(it)
            })
        }
        //fetch pokemon to db
        viewModel.fetPokemonListToDB()
        viewModel.fetchSpaceData()
        return binding.root
    }
}