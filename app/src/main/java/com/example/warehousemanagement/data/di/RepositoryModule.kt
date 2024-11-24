package com.example.warehousemanagement.data.di

import com.example.warehousemanagement.data.repository.WareHouseRepositoryImpl
import com.example.warehousemanagement.domain.repository.WareHouseRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface RepositoryModule {
    @Binds
    fun bindWarehouseApiRepository(repository: WareHouseRepositoryImpl): WareHouseRepository

}