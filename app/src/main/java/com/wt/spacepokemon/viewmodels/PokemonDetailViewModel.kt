package com.wt.spacepokemon.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.wt.spacepokemon.data.Pokemon
import com.wt.spacepokemon.data.PokemonInSpace
import com.wt.spacepokemon.data.SpaceRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class PokemonDetailViewModel @Inject constructor(
    private val spaceRepository: SpaceRepository
) : ViewModel() {
    val pokemon = MutableLiveData<Pokemon>()


    fun fetchPokemonDetailInfo(pokemonId: Int) {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                val findPokemon = spaceRepository.findPokemonById(pokemonId)
                pokemon.postValue(findPokemon)
            }
        }
    }
}