package prince.com.torrentify.ui

import prince.com.torrentify.BasePresenter
import prince.com.torrentify.BaseView
import prince.com.torrentify.model.Movie

interface TorrentListContract {

    interface TorrentListView:BaseView<TorrentListPresenter> {
        fun showLoader(status:Boolean)
        fun showError(status: Boolean,error:String?)
        fun addMovieData(movieList:MutableList<Movie>)
    }

    interface TorrentListPresenter:BasePresenter{
        fun getTorrentList()
    }
}