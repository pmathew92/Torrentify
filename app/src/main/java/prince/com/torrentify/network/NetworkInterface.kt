package prince.com.torrentify.network

import io.reactivex.Single
import okhttp3.ResponseBody
import retrofit2.http.GET
import retrofit2.http.Query


interface NetworkInterface {
    @GET("list_movies.json")
    fun getMovies(@Query("page") page:Int): Single<ResponseBody>
}