import org.dizitart.kno2.nitrite
import java.io.File

@Suppress("unused")
class DataBase {
    private val db = nitrite {
        file = File("data.db")
    }
    val quizCategoryRepository get() = db.getRepository(QuizCategory::class.java)!!

    fun forEachQuizList(func: (QuizList) -> Unit) = quizCategoryRepository.find().flatMap { it.quizzes }.forEach {
        func(it)
    }
    val quizRepository get() = db.getRepository(Quiz::class.java)!!
}