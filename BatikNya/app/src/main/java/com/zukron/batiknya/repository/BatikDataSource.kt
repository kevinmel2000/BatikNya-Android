package com.zukron.batiknya.repository

import android.util.Log
import androidx.paging.PageKeyedDataSource
import com.zukron.batiknya.model.BatikResponse
import com.zukron.batiknya.network.ApiService
import com.zukron.batiknya.tools.NetworkState
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers

/**
 * Project name is BatikNya
 * Created by Zukron Alviandy R on 9/25/2020
 * Contact me if any issues on zukronalviandy@gmail.com
 */

class BatikDataSource(
    private val compositeDisposable: CompositeDisposable,
    private val apiService: ApiService
) : PageKeyedDataSource<Int, BatikResponse.Batik>() {

    private val networkState = Repository.networkState

    override fun loadInitial(
        params: LoadInitialParams<Int>,
        callback: LoadInitialCallback<Int, BatikResponse.Batik>
    ) {
        networkState.postValue(NetworkState.LOADING)

        compositeDisposable.add(
            apiService.getAll()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe {
                    callback.onResult(it.data, null, null)

                    networkState.postValue(NetworkState.LOADED)
                }
        )
    }

    override fun loadBefore(
        params: LoadParams<Int>,
        callback: LoadCallback<Int, BatikResponse.Batik>
    ) {
        // who care
    }

    override fun loadAfter(
        params: LoadParams<Int>,
        callback: LoadCallback<Int, BatikResponse.Batik>
    ) {
        // who care
    }
}