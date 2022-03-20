package com.wt.spacepokemon

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import com.bumptech.glide.Glide
import com.wt.spacepokemon.adapters.TagListAdapter
import com.wt.spacepokemon.databinding.PokemonDetailFragmentBinding
import com.wt.spacepokemon.viewmodels.PokemonDetailViewModel
import dagger.hilt.android.AndroidEntryPoint


/**
 *  author : William Tsai
 *  date : 2022/3/17
 *  description : show pokemon detail information
 */
@AndroidEntryPoint
class PokemonDetailFragment : Fragment() {

    companion object {
        fun newInstance() = PokemonDetailFragment()
    }

    private val viewModel: PokemonDetailViewModel by viewModels()
    private lateinit var binding: PokemonDetailFragmentBinding
    private val typeAdapter by lazy {
        TagListAdapter()
    }
    private val weaknessAdapter by lazy {
        TagListAdapter()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = PokemonDetailFragmentBinding.inflate(inflater, container, false)
        binding.apply {
            rvTypeList.adapter = typeAdapter
            rvWeaknessList.adapter = weaknessAdapter
            myToolbar.setNavigationOnClickListener { view ->
                view.findNavController().navigateUp()
            }
        }
        val pokemonId = arguments?.getInt("id")
        pokemonId?.let { viewModel.fetchPokemonDetailInfo(it) }
        viewModel.pokemon.observe(viewLifecycleOwner) {
            //update pokemon
            binding.apply {
                tvName.text = it.name
                tvNum.text = it.num
                tvHeight.text = it.height
                tvWeight.text = it.weight
                tvCandy.text = it.candy
                tvCandyCount.text = it.candy_count.toString()
                typeAdapter.setData(it.type)
                weaknessAdapter.setData(it.weaknesses)
                Glide.with(requireContext()).load(it.img).into(ivAvatar)
                myToolbar.title = it.name

            }
        }
        return binding.root
    }
}