package com.alandvg.mcontigotest.viewmodel

import android.util.Log
import androidx.databinding.Observable
import androidx.databinding.ObservableBoolean
import androidx.databinding.ObservableField
import androidx.lifecycle.ViewModel;
import com.alandvg.mcontigotest.adapter.MusicVideoAdapter
import com.alandvg.mcontigotest.api.ItunesApi
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class SearchViewModel : ViewModel() {


    val compositeDisposableReques = CompositeDisposable()
    val adapterSearch = ObservableField<MusicVideoAdapter>()
    val textSearch = ObservableField<String>("")
    val searching = ObservableBoolean(false)
    val enabledSearch = ObservableBoolean(false)

    init {

        textSearch.addOnPropertyChangedCallback(object : Observable.OnPropertyChangedCallback() {
            override fun onPropertyChanged(sender: Observable?, propertyId: Int) {

                compositeDisposableReques.add(
                    ItunesApi.itunesService()
                        .searchMusicVideo(textSearch.get() ?: "")
                        .filter { textSearch.get()?.length ?: 0 > 0 }
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .doOnSubscribe {
                            searching.set(true)
                        }
                        .doOnError {

                        }
                        .subscribe {
                            Log.d("Teste", "${it.resultCount} ${it.results}")

                            adapterSearch.set(MusicVideoAdapter(it.results ?: listOf()))

                            compositeDisposableReques.clear()

                            searching.set(false)
                        }
                )

            }

        })

    }


    fun clearTextSearch(){
        textSearch.set("")
    }


    fun initSearch(search: Boolean = true) {
        enabledSearch.set(search)
    }


}