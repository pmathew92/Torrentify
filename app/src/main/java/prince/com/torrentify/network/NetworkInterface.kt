package prince.com.torrentify.network

import io.reactivex.Single
import okhttp3.ResponseBody
import retrofit2.http.GET


interface NetworkInterface {
    @GET("list_movies.json")
    fun getMovies(): Single<ResponseBody>
}