package prince.com.torrentify.ui

import prince.com.torrentify.BasePresenter
import prince.com.torrentify.BaseView

interface TorrentListContract {

    interface TorrentListView:BaseView<TorrentListPresenter> {
        fun showLoader(status:Boolean)
    }

    interface TorrentListPresenter:BasePresenter{
        fun getTorrentList()
    }
}