import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import org.dizitart.kno2.nitrite
import org.dizitart.no2.exceptions.UniqueConstraintException
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query
import java.io.File
import java.util.*

val db = nitrite {
    file = File("data.db")
}


private val httpClient: OkHttpClient by lazy {
    val client = OkHttpClient.Builder()
    client.build()
}


private interface QuizListInterface {
    @GET("/android_app.php")
    fun get(@Query("method") str: String?): Call<ArrayList<QuizCategory?>?>?
}

var retrofit: Retrofit = Retrofit.Builder().client(httpClient).addConverterFactory(
    GsonConverterFactory.create(GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create())
).baseUrl("https://www.geeksforgeeks.org/").build()

fun readAllCatagories() {
    val repo = db.getRepository(QuizCategory::class.java)
    val response = retrofit.create(QuizListInterface::class.java).get("fetch_quiz_list")!!.execute()
    val body = response.body()
    body?.forEach {
        try {
//            println(>>readAllCataGories   ")
            repo.insert(it)
            println(">>readAllCataGories  Added category $it ")
        } catch (e: UniqueConstraintException) {
        }
    }
}


fun main() {
    readAllCatagories()
    httpClient.connectionPool().evictAll()
}

fun timed(func: () -> Unit): Long {
    val st = System.currentTimeMillis()
    func()
    println(">>timed  ${System.currentTimeMillis() - st} ")
    return System.currentTimeMillis() - st

}