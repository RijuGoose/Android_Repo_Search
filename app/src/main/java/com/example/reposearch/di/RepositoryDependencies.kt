package com.example.reposearch.di

import com.example.reposearch.repository.RepoSearchRepository
import com.example.reposearch.repository.RepoSearchRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryDependencies {

    @Binds
    abstract fun provideRepoSearchRepository(impl: RepoSearchRepositoryImpl): RepoSearchRepository
}