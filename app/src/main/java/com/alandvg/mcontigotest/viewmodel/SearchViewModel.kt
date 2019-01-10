package com.alandvg.mcontigotest.viewmodel

import android.util.Log
import androidx.databinding.Observable
import androidx.databinding.ObservableBoolean
import androidx.databinding.ObservableField
import androidx.lifecycle.ViewModel;
import com.alandvg.mcontigotest.adapter.MusicVideoAdapter
import com.alandvg.mcontigotest.adapter.MusicVideoAdapterInterface
import com.alandvg.mcontigotest.api.ItunesApi
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class SearchViewModel : ViewModel(), MusicVideoAdapterInterface {
    val compositeDisposableRequest = CompositeDisposable()
    val adapterSearch = ObservableField<MusicVideoAdapter>()
    val textSearch = ObservableField<String>("")
    val searching = ObservableBoolean(false)
    val enabledSearch = ObservableBoolean(false)

    val linkSelected = ObservableField<String>("")

    init {

        textSearch.addOnPropertyChangedCallback(object : Observable.OnPropertyChangedCallback() {
            override fun onPropertyChanged(sender: Observable?, propertyId: Int) {

                Log.d("Teste", "${textSearch.get()}")

                compositeDisposableRequest.clear()

                compositeDisposableRequest.add(
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

                            adapterSearch.set(MusicVideoAdapter(it.results ?: listOf(), this@SearchViewModel))

                            compositeDisposableRequest.clear()

                            searching.set(false)
                        }
                )

            }

        })

    }

    override fun onSelectDetalhesArtista(link : String) {
        linkSelected.set(link)
    }

    fun clearTextSearch(){
        textSearch.set("")
    }


    fun initSearch(search: Boolean = true) {
        enabledSearch.set(search)
    }


}