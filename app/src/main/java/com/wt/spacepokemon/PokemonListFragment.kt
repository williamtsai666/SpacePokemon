package com.wt.spacepokemon

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.wt.spacepokemon.adapters.PokemonListAdapter
import com.wt.spacepokemon.databinding.PokemonDetailFragmentBinding
import com.wt.spacepokemon.databinding.PokemonListFragmentBinding
import com.wt.spacepokemon.viewmodels.PokemonListViewModel
import dagger.hilt.android.AndroidEntryPoint

/**
 *  author : William Tsai
 *  date : 2022/3/17
 *  description : show pokemon list in craft
 */
@AndroidEntryPoint
class PokemonListFragment : Fragment() {

    companion object {
        fun newInstance() = PokemonListFragment()
    }

    private val viewModel: PokemonListViewModel by viewModels()
    private val pokemonListAdapter by lazy {
        PokemonListAdapter()
    }
    private lateinit var binding: PokemonListFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = PokemonListFragmentBinding.inflate(inflater, container, false)
        binding.apply {
            rvPokemonList.layoutManager = GridLayoutManager(requireContext(), 2)
            rvPokemonList.adapter = pokemonListAdapter
            myToolbar.setNavigationOnClickListener { view ->
                view.findNavController().navigateUp()
            }
        }

        val craft = arguments?.getString("craftName")
        craft?.let {
            viewModel.getPokemonListByCraftName(it)
            binding.myToolbar.title = getString(R.string.title_pokemon_list) + craft
        }

        viewModel.pokemonList.observe(viewLifecycleOwner) {
            //update pokemon list
            pokemonListAdapter.setData(it)
        }

        return binding.root
    }


}