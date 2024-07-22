package com.example.financialportfolioapp.di

import com.example.financialportfolioapp.data.AssetRepositoryImpl
import com.example.financialportfolioapp.domain.repository.AssetRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface RepositoryModule {
    @Binds
    fun bindAssetRepository(assetRepositoryImpl: AssetRepositoryImpl): AssetRepository
}
