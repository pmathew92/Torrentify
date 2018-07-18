package prince.com.torrentify.ui

import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import okhttp3.ResponseBody
import org.json.JSONObject
import prince.com.torrentify.model.Movie
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
        compositeDisposable?.dispose()
    }

    override fun getTorrentList() {
        view.showLoader(true)
        view.showError(false,null)
        compositeDisposable?.add(fetchMovieList())
    }


    private fun fetchMovieList(): Disposable {
        return NetworkClient.networkService()
                .getMovies(pageCount)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ response ->
                    view.showLoader(false)
                    (this::handleSuccess)(response)
                },
                        { error ->
                            view.showLoader(false)
                            view.showError(true,error.localizedMessage)
                            error.printStackTrace()
                        })

    }


    private fun handleSuccess(responseBody: ResponseBody) {
        ++pageCount
        val movieList: MutableList<Movie> = ArrayList()
        val jsonObject = JSONObject(responseBody.string())
        val data = jsonObject.getJSONObject("data")
        val movies = data.getJSONArray("movies")
        val length = movies.length()
        for (i in 0 until (length - 1)) {
            val movie = movies.getJSONObject(i)
            movieList.add(Movie(movie.getString("title"), movie.getString("large_cover_image"),
                    movie.getString("year"), movie.getString("summary")))
        }
        view.addMovieData(movieList)
    }

}