import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import org.dizitart.kno2.nitrite
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query
import java.io.File
import java.util.*

val db = nitrite {
    file = File("data.db")
}

private fun getNewHttpClient(): OkHttpClient? {
    val client = OkHttpClient.Builder()
    return client.build()
}


private interface QuizListInterface {
    @GET("/android_app.php")
    fun get(@Query("method") str: String?): Call<ArrayList<QuizCategory?>?>?
}

var retrofit = Retrofit.Builder().client(getNewHttpClient()).addConverterFactory(
    GsonConverterFactory.create(GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create())
).baseUrl("https://www.geeksforgeeks.org/").build()

fun main() {
    val body = retrofit.create(QuizListInterface::class.java).get("fetch_quiz_list")!!.execute().body()

    body?.forEach {
        println(">>main  $it ")
    }


}

fun timed(func: () -> Unit): Long {
    val st = System.currentTimeMillis()
    func()
    println(">>timed  ${System.currentTimeMillis() - st} ")
    return System.currentTimeMillis() - st

}