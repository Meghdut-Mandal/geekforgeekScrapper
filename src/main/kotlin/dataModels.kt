import com.google.gson.annotations.SerializedName
import org.dizitart.no2.objects.Id
import java.util.*
/*

 */

data class QuizList(
    @Id
    @SerializedName("ID") val quizId: Int,
    @SerializedName("name") val quizName: String,
    @SerializedName("question_count") val questionCount: Int
)

data class QuizCategory(
    @Id
    @SerializedName("term_id") val quizTermId: String,
    @SerializedName("name") val quizCategoryName: String,
    val quizzes: ArrayList<QuizList>
)

data class Quiz(
    @Id
    @SerializedName("ID") val quizId: Int,
    @SerializedName("question") val question: String,
    @SerializedName("explanation") val explanation: String,
    @SerializedName("answer") val answer: List<String>,
    @SerializedName("correct") val correct: List<String>
)
