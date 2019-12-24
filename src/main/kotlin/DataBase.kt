import org.dizitart.kno2.filters.eq
import org.dizitart.kno2.nitrite
import java.io.File
import javax.transaction.xa.XAException

@Suppress("unused")
class DataBase {
    private val db = nitrite {
        file = File("data.db")
    }
    val quizCategoryRepository get() = db.getRepository(QuizCategory::class.java)!!

    fun forEachQuizCategory(func: (QuizCategory) -> Unit) = quizCategoryRepository.find().forEach(func)

    fun getQuizRepository(quizID: Int) = db.getRepository(quizID.toString(), Quiz::class.java)

    fun markExplored(quizCategory: QuizCategory) {

        quizCategoryRepository.update(quizCategory.copy(explored = true))
        println(">DataBase>markExplored  ${quizCategory.quizCategoryName} done ")
    }
}