package com.zukron.batiknya.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.zukron.batiknya.model.BatikResponse
import com.zukron.batiknya.network.RestApi
import com.zukron.batiknya.tools.NetworkState
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers
import java.util.concurrent.Executors

/**
 * Project name is BatikNya
 * Created by Zukron Alviandy R on 9/24/2020
 * Contact me if any issues on zukronalviandy@gmail.com
 */
object Repository {
    private val apiService = RestApi.apiService
    private const val postPerPage = 10
    private val executor = Executors.newFixedThreadPool(5)
    private val pageListConfig = PagedList.Config.Builder()
        .setPageSize(postPerPage)
        .setEnablePlaceholders(false)
        .build()

    val networkState: MutableLiveData<NetworkState> = MutableLiveData()

    fun getPopular(
        compositeDisposable: CompositeDisposable
    ): LiveData<List<BatikResponse.Batik>> {
        networkState.postValue(NetworkState.LOADING)

        return object : LiveData<List<BatikResponse.Batik>>() {
            override fun onActive() {
                super.onActive()

                compositeDisposable.add(
                    apiService.getPopular()
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe {
                            value = it.data
                            networkState.postValue(NetworkState.LOADED)
                        }
                )
            }
        }
    }

    fun getAll(
        compositeDisposable: CompositeDisposable
    ): LiveData<List<BatikResponse.Batik>> {
        networkState.postValue(NetworkState.LOADING)

        return object : LiveData<List<BatikResponse.Batik>>() {
            override fun onActive() {
                super.onActive()

                compositeDisposable.add(
                    apiService.getAll()
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe {
                            value = it.data
                            networkState.postValue(NetworkState.LOADED)
                        }
                )
            }
        }
    }

    fun getAllPaged(
        compositeDisposable: CompositeDisposable
    ): LiveData<PagedList<BatikResponse.Batik>> {
        val dataFactory = BatikDataFactory(compositeDisposable, apiService)

        return LivePagedListBuilder(dataFactory, pageListConfig)
            .setFetchExecutor(executor)
            .build()
    }
}