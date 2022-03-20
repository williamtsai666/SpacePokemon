package com.wt.spacepokemon.di

import android.content.Context
import androidx.room.Room
import com.wt.spacepokemon.data.AppDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 *  author : William Tsai
 *  date : 2022/3/19
 *  description : di provide database
 */
@InstallIn(SingletonComponent::class)
@Module
class DatabaseModule {
    @Singleton
    @Provides
    fun provideAppDatabase(@ApplicationContext context: Context): AppDatabase {
        return Room.databaseBuilder(
            context,
            AppDatabase::class.java, AppDatabase.DATABASE_NAME
        ).build()
    }

}