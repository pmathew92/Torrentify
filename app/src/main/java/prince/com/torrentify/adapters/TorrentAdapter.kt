package prince.com.torrentify.adapters

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.layout_movie_card.view.*
import prince.com.torrentify.GlideApp
import prince.com.torrentify.R
import prince.com.torrentify.model.Movie

class TorrentAdapter(val context: Context) : RecyclerView.Adapter<TorrentAdapter.MovieHolder>() {
    private var movieList: MutableList<Movie> = ArrayList()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieHolder {
        val view: View = LayoutInflater.from(context).inflate(R.layout.layout_movie_card, parent, false)
        return MovieHolder(view)
    }

    override fun getItemCount(): Int = movieList.size

    override fun onBindViewHolder(holder: MovieHolder, position: Int) {
        val model: Movie = movieList[position]
        holder.movieName.text = model.name
        holder.year.text = model.year
        holder.summary.text = model.summary
        GlideApp.with(context)
                .load(model.image)
                .centerCrop()
                .into(holder.movieImage)
    }

    fun addData(movieList: MutableList<Movie>) {
        this.movieList.addAll(movieList)
        notifyDataSetChanged()
    }

    class MovieHolder(v: View) : RecyclerView.ViewHolder(v) {
        val movieName = v.movie_name
        val movieImage = v.movie_image
        val year = v.release_year
        val summary = v.summary
    }
}