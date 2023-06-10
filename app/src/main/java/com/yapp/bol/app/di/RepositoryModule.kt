package com.yapp.bol.app.di

import com.yapp.bol.data.repository.MockRepositoryImpl
import com.yapp.bol.data.repository.auth.AuthRepositoryImpl
import com.yapp.bol.domain.repository.AuthRepository
import com.yapp.bol.domain.repository.MockRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun bindMockRepository(mockRepositoryImpl: MockRepositoryImpl): MockRepository

    @Binds
    abstract fun bindAuthRepository(repository: AuthRepositoryImpl): AuthRepository
}
