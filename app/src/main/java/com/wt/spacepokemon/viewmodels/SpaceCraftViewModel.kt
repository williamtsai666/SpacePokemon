package com.wt.spacepokemon.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.wt.spacepokemon.data.Craft
import com.wt.spacepokemon.data.SpaceRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class SpaceCraftViewModel @Inject constructor(
    private val spaceRepository: SpaceRepository
) : ViewModel() {

    val craftList = MutableLiveData<MutableList<Craft>>()

    fun fetchSpaceData() {
        viewModelScope.launch() {
            withContext(Dispatchers.IO) {
                val list = spaceRepository.fetchSpaceData()
                craftList.postValue(list)
            }
        }
    }

    fun fetPokemonListToDB() {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                spaceRepository.fetchPokemonListAndSaveIntoDB()
            }
        }
    }
}