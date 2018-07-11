package prince.com.torrentify.network

import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory

object NetworkClient {
    private const val BASE_PATH = "https://yts.am/api/v2/"

    private val retrofit = Retrofit.Builder()
            .baseUrl(BASE_PATH)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()

    fun networkService(): NetworkInterface {
        return retrofit.create(NetworkInterface::class.java)
    }
}