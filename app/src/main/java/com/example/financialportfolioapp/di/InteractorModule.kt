package com.example.financialportfolioapp.di

import com.example.financialportfolioapp.domain.interactor.Interactor
import com.example.financialportfolioapp.domain.repository.AssetRepository
import com.example.financialportfolioapp.domain.repository.PortfolioItemRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton
@Module
@InstallIn(SingletonComponent::class)
object InteractorModule {
    @Provides
    @Singleton
    fun provideInteractor(
        assetRepository: AssetRepository,
        portfolioItemRepository: PortfolioItemRepository
    ): Interactor {
        return Interactor(assetRepository, portfolioItemRepository)
    }
}
