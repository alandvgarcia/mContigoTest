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
    val searching = ObservableBoolean()
    val enabledSearch = ObservableBoolean(false)

    val successSearch = ObservableBoolean(false)
    val firstSearch = ObservableBoolean(false)

    val linkSelected = ObservableField<String>("")
    val previewSelected = ObservableField<String>("")

    init {

        adapterSearch.set(MusicVideoAdapter(mutableListOf(), this@SearchViewModel))

        textSearch.addOnPropertyChangedCallback(object : Observable.OnPropertyChangedCallback() {
            override fun onPropertyChanged(sender: Observable?, propertyId: Int) {

                compositeDisposableRequest.clear()

                compositeDisposableRequest.add(
                    ItunesApi.itunesService()
                        .searchMusicVideo(textSearch.get() ?: "")
                        .filter { textSearch.get()?.length ?: 0 > 0 }
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .doOnSubscribe {
                            searching.set(true)
                            if (adapterSearch.get()?.listSearch?.size ?: 0 > 0) {
                                adapterSearch.get()?.listSearch?.clear()
                                adapterSearch.get()?.notifyDataSetChanged()
                            }
                        }
                        .doOnTerminate {
                            searching.set(false)
                            firstSearch.set(true)
                        }
                        .subscribe({
                            Log.d("Teste", "${it.resultCount} ${it.results}")

                            it.results?.let { results ->
                                adapterSearch.get()?.listSearch?.addAll(results)
                                adapterSearch.get()?.notifyDataSetChanged()
                                successSearch.set(true)
                            }

                            searching.set(false)
                            compositeDisposableRequest.clear()
                        },
                            { error ->
                                error.printStackTrace()
                                Log.e("Teste", "Erro Internet")
                                successSearch.set(false)
                                compositeDisposableRequest.clear()
                            })
                )

            }

        })

    }

    override fun onSelectDetalhesArtista(link: String) {
        linkSelected.set(link)
    }

    override fun onPlayVideoPreview(linkVideo: String) {
        previewSelected.set(linkVideo)
    }

    fun clearTextSearch() {
        textSearch.set("")
    }


    fun initSearch(search: Boolean = true) {
        enabledSearch.set(search)
    }


}