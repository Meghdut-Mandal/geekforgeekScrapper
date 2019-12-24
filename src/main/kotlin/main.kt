import org.dizitart.no2.exceptions.UniqueConstraintException


val quizRepository = GeekForGeekRepository()
val dataBase = DataBase()

fun readAllQuizes() {

}

fun readAllCatagories() {
    val repo = dataBase.quizCategoryRepository
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