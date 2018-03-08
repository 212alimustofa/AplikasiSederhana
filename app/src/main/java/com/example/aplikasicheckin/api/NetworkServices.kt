package com.example.aplikasicheckin.api

import com.example.aplikasicheckin.model.ApiResponse
import com.example.aplikasicheckin.model.Data
import io.reactivex.Observable
import okhttp3.ResponseBody
import retrofit2.http.*

/**
 * Created by alimustofa on 19/02/18.
 */
interface NetworkServices {
    @GET("lokasi.php")
    fun getAllLokasi(): Observable<ApiResponse>

    @FormUrlEncoded
    @POST("lokasi.php")
    fun addDataLokasi(@Field("aksi") aksi: String,
                       @Field("nama") namaLokasi: String,
                       @Field("keterangan") keterangan: String,
                       @Field("lat") lat: String?,
                       @Field("lon") lon: String?,
                       @Field("kontributor") kontributor: String): Observable<ResponseBody>
}