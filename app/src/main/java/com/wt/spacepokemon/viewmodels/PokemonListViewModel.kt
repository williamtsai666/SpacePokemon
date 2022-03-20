package com.wt.spacepokemon.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.wt.spacepokemon.data.Craft
import com.wt.spacepokemon.data.PokemonInSpace
import com.wt.spacepokemon.data.SpaceRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class PokemonListViewModel @Inject constructor(
    private val spaceRepository: SpaceRepository
) : ViewModel() {

    val pokemonList = MutableLiveData<MutableList<PokemonInSpace>>()

    fun getPokemonListByCraftName(craft: String) {
        viewModelScope.launch {
            val list = spaceRepository.getPokemonListByCraftName(craft)
            list?.let {
                withContext(Dispatchers.IO) {
                    for (p in it) {
                        //query db to get pokemon img
                        p.imgUrl = spaceRepository.findPokemonImgUrlById(p.id)
                    }
                    pokemonList.postValue(it)
                }
            }
        }
    }
}