import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query
import java.util.*


class GeekForGeekRepository {

    private val httpClient: OkHttpClient by lazy {
        val client = OkHttpClient.Builder()
        client.build()
    }

    private val retrofit: Retrofit by lazy {
        Retrofit.Builder().client(httpClient).addConverterFactory(
            GsonConverterFactory.create(GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create())
        ).baseUrl("https://www.geeksforgeeks.org/").build()
    }

    fun getCategories(): ArrayList<QuizCategory>? =
        retrofit.create(QuizListInterface::class.java).get("fetch_quiz_list").execute().body()

    fun getQuiz(quizId: Int): List<Quiz>? =
        retrofit.create(QuizInterface::class.java).get("fetch_quiz", quizId).execute().body()

    fun closeAllConnections() = httpClient.connectionPool().evictAll()
}

private interface QuizListInterface {
    @GET("/android_app.php")
    fun get(@Query("method") str: String?): Call<ArrayList<QuizCategory>>
}

private interface QuizInterface {
    @GET("/android_app.php")
    fun get(@Query("method") str: String, @Query("quiz_id") i: Int): Call<List<Quiz>>
}