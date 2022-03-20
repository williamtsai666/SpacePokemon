package com.wt.spacepokemon.di

import com.wt.spacepokemon.api.SpacePokemonApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 *  author : William Tsai
 *  date : 2022/3/19
 *  description : di provide api service
 */
@InstallIn(SingletonComponent::class)
@Module
class NetworkModule {

    @Singleton
    @Provides
    fun providePokemonApiService(): SpacePokemonApiService {
        return SpacePokemonApiService()
    }
}