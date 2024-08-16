package com.example.financialportfolioapp.di

import android.content.Context
import androidx.room.Room
import com.example.financialportfolioapp.data.local.AppDatabase
import com.example.financialportfolioapp.data.local.AssetDao
import com.example.financialportfolioapp.data.local.PortfolioItemDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {
    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext appContext: Context): AppDatabase {
        return Room.databaseBuilder(
            appContext,
            AppDatabase::class.java,
            "assets_database.db"
        )
            .createFromAsset("assets_database.db")
            .fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    fun provideAssetDao(db: AppDatabase): AssetDao {
        return db.assetDao()
    }

    @Provides
    fun providePortfolioItemDao(db: AppDatabase): PortfolioItemDao {
        return db.portfolioItemDao()
    }
}
