package com.dofkee.bookme.data.remote.retrofit

import com.dofkee.bookme.data.remote.MifAPI
import com.dofkee.bookme.data.remote.RemoteDataSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)

object NetworkModule {
    // аналог localhost
    private const val BASE_URL = "http://192.168.0.22:80/"

    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create()) //для JSON
        .build()

    @Provides
    @Singleton
    fun provideMifApi(retrofit: Retrofit): MifAPI = retrofit.create(MifAPI::class.java)

    @Provides
    @Singleton
    fun provideRemoteDataSource(mifApi: MifAPI): RemoteDataSource = RemoteDataSource(mifApi)
}