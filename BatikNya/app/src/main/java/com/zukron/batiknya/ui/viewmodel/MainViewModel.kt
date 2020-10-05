package com.zukron.batiknya.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.zukron.batiknya.model.BatikResponse
import com.zukron.batiknya.repository.Repository
import io.reactivex.rxjava3.disposables.CompositeDisposable

/**
 * Project name is BatikNya
 * Created by Zukron Alviandy R on 9/24/2020
 * Contact me if any issues on zukronalviandy@gmail.com
 */
class MainViewModel : ViewModel() {
    private val compositeDisposable = CompositeDisposable()
    val networkState = Repository.networkState

    val getPopularBatik: LiveData<List<BatikResponse.Batik>> =
        Repository.getPopular(compositeDisposable)

    val getAllBatik: LiveData<List<BatikResponse.Batik>> =
        Repository.getAll(compositeDisposable)

    val getAllPagedBatik: LiveData<PagedList<BatikResponse.Batik>> =
        Repository.getAllPaged(compositeDisposable)

    override fun onCleared() {
        super.onCleared()

        compositeDisposable.clear()
    }
}