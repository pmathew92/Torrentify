package prince.com.torrentify.ui

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.LinearLayoutManager
import android.view.View.GONE
import android.view.View.VISIBLE
import kotlinx.android.synthetic.main.activity_torrent_list.*
import prince.com.torrentify.R
import prince.com.torrentify.adapters.TorrentAdapter
import prince.com.torrentify.model.Movie

class TorrentListActivity : AppCompatActivity(), TorrentListContract.TorrentListView {
    override lateinit var presenter: TorrentListContract.TorrentListPresenter

    private var mAdapter: TorrentAdapter? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_torrent_list)

        TorrentListPresenterImpl(this)

        mAdapter = TorrentAdapter(this)

        rv_movies.setHasFixedSize(true)
        rv_movies.itemAnimator = DefaultItemAnimator()
        rv_movies.layoutManager = LinearLayoutManager(this)
        rv_movies.adapter = mAdapter

        presenter.subscribe()

    }


    override fun onDestroy() {
        presenter.unSubscribe()
        super.onDestroy()
    }

    override fun showLoader(status: Boolean) {
        if (status) {
            showLoader()
        } else {
            hideLoader()
        }
    }


    override fun addMovieData(movieList: MutableList<Movie>) {
        mAdapter?.addData(movieList)
    }

    private fun showLoader() {
        layout_loading.visibility = VISIBLE
    }

    private fun hideLoader() {
        layout_loading.visibility = GONE
    }


    private fun showError() {
        layout_error.visibility = VISIBLE
    }

    private fun hideError() {
        layout_error.visibility = GONE
    }
}
