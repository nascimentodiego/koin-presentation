/*
 * Copyright (C) 2018 Diego Figueredo do Nascimento.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package br.com.diegonascimento.koinpresentation.network

import Constants
import br.com.diegonascimento.koinpresentation.BuildConfig
import com.google.gson.Gson
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

/**
 * ServiceClient.
 *
 * This class has a base implementation to delivery a simple communication using, okHttp
 * Retrofit.
 *
 * @param T the type of a interface that represent API should be consumed
 * @property mOkHttpclient the instance of okhttp that can be set by client or use a default
 * configuration.
 * @property mRetrofit the instance of okhttp that can be set by client or use a default
 * configuration.
 */
class ServiceClient<T> constructor(var mBaseUrl: String) {
    private var mOkHttpclient: OkHttpClient? = null
    private var mRetrofit: Retrofit? = null

    constructor(baseUrl: String, okHttpClient: OkHttpClient, retrofit: Retrofit) : this(baseUrl) {
        mOkHttpclient = okHttpClient
        mRetrofit = retrofit
    }

    private fun buildOkHttp() {
        val httpLoggingInterceptor = HttpLoggingInterceptor()

        if (BuildConfig.DEBUG) {
            httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        } else {
            httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.NONE
        }

        mOkHttpclient = OkHttpClient.Builder()
                .connectTimeout(Constants.REQUEST_TIMEOUT, TimeUnit.SECONDS)
                .readTimeout(Constants.REQUEST_TIMEOUT, TimeUnit.SECONDS)
                .addInterceptor(httpLoggingInterceptor).build()
    }

    private fun buildRetrofit() {
        mRetrofit = Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(Gson()))
                .baseUrl(mBaseUrl)
                .client(mOkHttpclient!!)
                .build()
    }


    /**
     * Method that grant access to API.
     *
     * */
    fun getServiceApi(api: Class<T>): T {

        if (mOkHttpclient == null) {
            buildOkHttp()
        }

        if (mRetrofit == null) {
            buildRetrofit()
        }

        return mRetrofit!!.create<T>(api)
    }
}