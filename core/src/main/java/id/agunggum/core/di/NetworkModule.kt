package id.agunggum.core.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import id.agunggum.core.data.source.remote.network.ApiService
import id.agunggum.core.data.source.remote.network.BasicInterceptor
import okhttp3.CertificatePinner
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {

    @Provides
    fun provideOkHttpClient(): OkHttpClient {
        val accessKey = "699fed796c0447b3b27811e71613522e"
        val hostname = "api.rawg.io"
        val certificatePinner = CertificatePinner.Builder()
            .add(hostname, "sha256/6i+nf58l8neEnNarZOvxiYfVbt2S2xurswGQQBBMa0U=")
            .add(hostname, "sha256/FEzVOUp4dF3gI0ZVPRJhFbSJVXR+uQmMH65xhs1glH4=")
            .add(hostname, "sha256/Y9mvm0exBk1JoQ57f9Vm28jKo5lFm/woKcVxrYxu80o=")
            .build()
        return OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
            .addInterceptor(BasicInterceptor(accessKey))
            .connectTimeout(120, TimeUnit.SECONDS)
            .readTimeout(120, TimeUnit.SECONDS)
            .certificatePinner(certificatePinner)
            .build()
    }

    @Provides
    fun provideApiService(client: OkHttpClient): ApiService {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://api.rawg.io/api/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
        return retrofit.create(ApiService::class.java)
    }
}