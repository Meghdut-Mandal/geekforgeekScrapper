import org.dizitart.kno2.nitrite
import org.dizitart.no2.exceptions.UniqueConstraintException
import java.io.File

val db = nitrite {
    file = File("data.db")
}
val quizRepository=QuizRepository()


fun readAllCatagories() {
    val repo = db.getRepository(QuizCategory::class.java)
    val body = quizRepository.getCategories()
    body?.forEach {
        try {
            repo.insert(it)
            println(">>readAllCataGories  Added category $it ")
        } catch (e: UniqueConstraintException) {
            println(">>readAllCatagories  Catagory Alredy Exists in the dataBase !! ${it.quizCategoryName} ")
        }
    }
}

fun menu() {
    println(
        """ Welcome to GeekForGeeks Scrapper enter 
1. read All Categories
2. read All Quizzes    """.trimIndent()
    )
}

fun main() {
    readAllCatagories()
}

fun timed(func: () -> Unit): Long {
    val st = System.currentTimeMillis()
    func()
    println(">>timed  ${System.currentTimeMillis() - st} ")
    return System.currentTimeMillis() - st

}