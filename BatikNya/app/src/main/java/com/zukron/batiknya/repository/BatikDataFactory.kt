package com.zukron.batiknya.repository

import androidx.paging.DataSource
import com.zukron.batiknya.model.BatikResponse
import com.zukron.batiknya.network.ApiService
import io.reactivex.rxjava3.disposables.CompositeDisposable

/**
 * Project name is BatikNya
 * Created by Zukron Alviandy R on 9/25/2020
 * Contact me if any issues on zukronalviandy@gmail.com
 */
class BatikDataFactory(
    private val compositeDisposable: CompositeDisposable,
    private val apiService: ApiService
) : DataSource.Factory<Int, BatikResponse.Batik>() {

    override fun create(): DataSource<Int, BatikResponse.Batik> {
        return BatikDataSource(compositeDisposable, apiService)
    }
}