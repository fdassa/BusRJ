package br.com.fdassa.busrj.network

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import okhttp3.logging.HttpLoggingInterceptor.Level
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit

object NetworkConfig {
    private const val CONNECT_TIME_OUT = 120L
    private const val READ_TIME_OUT = 30L
    private const val WRITE_TIME_OUT = 30L
    // Substituir pelo IP do computador que está rodando o backend
    private const val baseUrl = "http://192.168.2.5:1026"

    fun <T> provideApi(clazz: Class<T>): T {
        val retrofit = Retrofit.Builder()
            .addConverterFactory(MoshiConverterFactory.create())
            .client(provideOkHttpClient())
            .baseUrl(baseUrl)
            .build()
        return retrofit.create(clazz)
    }

    private fun provideOkHttpClient(): OkHttpClient {
        val interceptor = HttpLoggingInterceptor().apply {
            level = Level.BODY
        }
        return OkHttpClient.Builder()
            .addInterceptor(interceptor)
            .connectTimeout(CONNECT_TIME_OUT, TimeUnit.SECONDS)
            .readTimeout(READ_TIME_OUT, TimeUnit.SECONDS)
            .writeTimeout(WRITE_TIME_OUT, TimeUnit.SECONDS)
            .build()
    }
}