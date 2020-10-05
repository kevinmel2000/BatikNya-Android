package com.zukron.batiknya.network

import com.zukron.batiknya.model.BatikResponse
import io.reactivex.rxjava3.core.Flowable
import retrofit2.http.GET

/**
 * Project name is BatikNya
 * Created by Zukron Alviandy R on 9/24/2020
 * Contact me if any issues on zukronalviandy@gmail.com
 */
interface ApiService {
    @GET("popular")
    fun getPopular(): Flowable<BatikResponse>

    @GET("all")
    fun getAll(): Flowable<BatikResponse>
}