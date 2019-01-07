package com.alandvg.mcontigotest.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel;
import com.alandvg.mcontigotest.api.ItunesApi
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class SearchViewModel : ViewModel() {


    val compositeDisposableReques = CompositeDisposable()

    init {
        compositeDisposableReques.add(
            ItunesApi.itunesService()
                .searchMusicVideo("beyonce")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnError {

                }
                .subscribe {
                    Log.d("Teste", "${it.resultCount} ${it.results}")
                    compositeDisposableReques.clear()
                }
        )

    }


}
