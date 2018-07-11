package prince.com.torrentify.ui

import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import prince.com.torrentify.network.NetworkClient


class TorrentListPresenterImpl(val view: TorrentListContract.TorrentListView)
    : TorrentListContract.TorrentListPresenter {

    private var compositeDisposable: CompositeDisposable? = null
    var pageCount = 1

    init {
        view.presenter = this
        compositeDisposable = CompositeDisposable()
    }

    override fun subscribe() {
        getTorrentList()
    }

    override fun unSubscribe() {
        compositeDisposable?.clear()
    }

    override fun getTorrentList() {
        var subscriber = getSingle()
    }


    fun getSingle(): Disposable {
        return NetworkClient.networkService()
                .getMovies()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ response ->
                },
                        { error -> error.printStackTrace() });

    }


}