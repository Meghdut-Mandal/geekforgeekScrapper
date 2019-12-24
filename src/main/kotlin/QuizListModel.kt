import com.google.gson.annotations.SerializedName
import java.util.ArrayList

data class QuizListModel(
    @SerializedName("ID") val quizId: Int, @SerializedName("name") val quizName: String, @SerializedName("question_count") val questionCount: Int
)
data class QuizCategory(
    @SerializedName("term_id") val quizTermId: String,
    @SerializedName("name") val quizCategoryName: String,
    val quizzes: ArrayList<QuizListModel>
)