package com.example.reposearch.di

import com.example.reposearch.remote.AccountService
import com.example.reposearch.remote.AccountServiceImpl
import com.example.reposearch.remote.RepoSearchService
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RemoteDependencies {

    @Binds
    abstract fun provideAccountService(impl: AccountServiceImpl): AccountService

    companion object {
        @Provides
        @Singleton
        fun provideRetrofit(): Retrofit {
            return Retrofit.Builder().baseUrl("https://api.github.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .client(OkHttpClient.Builder()
                    .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
                    .build())
                .build()
        }

        @Provides
        @Singleton
        fun provideRepoSearchService(retrofit: Retrofit): RepoSearchService {
            return retrofit.create(RepoSearchService::class.java)
        }
    }
}