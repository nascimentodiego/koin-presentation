package br.com.diegonascimento.koinpresentation.di

import br.com.diegonascimento.koinpresentation.BuildConfig
import br.com.diegonascimento.koinpresentation.network.starwars.StarWarsApi
import com.google.gson.Gson
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

val networkModule = module {
    single {
        val httpLoggingInterceptor = HttpLoggingInterceptor()

        if (BuildConfig.DEBUG) {
            httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        } else {
            httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.NONE
        }

        OkHttpClient.Builder()
                .connectTimeout(Constants.REQUEST_TIMEOUT, TimeUnit.SECONDS)
                .readTimeout(Constants.REQUEST_TIMEOUT, TimeUnit.SECONDS)
                .addInterceptor(httpLoggingInterceptor).build()
    }

    single {
        Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(Gson()))
                .baseUrl(BuildConfig.API_ENDPOINT)
                .client(get())
                .build()

    }

    single {
        val retrofit: Retrofit = get()
        retrofit.create<StarWarsApi>(StarWarsApi::class.java)
    }
}