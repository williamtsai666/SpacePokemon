package com.wt.spacepokemon.di

import com.wt.spacepokemon.api.SpacePokemonApiService
import com.wt.spacepokemon.data.AppDatabase
import com.wt.spacepokemon.data.SpaceRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 *  author : William Tsai
 *  date : 2022/3/19
 *  description : di provide repository
 */
@Module
@InstallIn(SingletonComponent::class)
class RepositoryModule {

    @Singleton
    @Provides
    fun provideSpaceRepository(
        appDatabase: AppDatabase,
        apiService: SpacePokemonApiService
    ): SpaceRepository {
        return SpaceRepository(appDatabase, apiService)
    }
}