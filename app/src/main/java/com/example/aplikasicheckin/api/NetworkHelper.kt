package com.example.aplikasicheckin.api

import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.Retrofit
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory



/**
 * Created by alimustofa on 19/02/18.
 */
class NetworkHelper {
    private var url = "http://wp.garasitekno.com/service/"
    var retrofit: Retrofit? = null
    var services: NetworkServices? = null

    init {
        val logging = HttpLoggingInterceptor()
        logging.level = HttpLoggingInterceptor.Level.BODY
        val httpClient = OkHttpClient.Builder()
                .addInterceptor(logging)
                .build()

        retrofit = Retrofit.Builder()
                .baseUrl(url)
                .client(httpClient)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build()

        services = retrofit?.create(NetworkServices::class.java)
    }

    fun getRestAPI(): NetworkServices {
        return services!!
    }

}