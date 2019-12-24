@file:Suppress("unused")

import org.dizitart.no2.exceptions.UniqueConstraintException


val geekForGeekRepository = GeekForGeekRepository()
val dataBase = DataBase()

fun readAllQuizes() {
    dataBase.forEachQuizCategory { category ->
        if (!category.explored) {
            category.forEachList {
                val quizRepository = dataBase.getQuizRepository(it.quizId)
                val quiz = geekForGeekRepository.getQuiz(it.quizId)
                quiz?.forEach {
                    try {
                        quizRepository.insert(it)
                        println(">>readAllQuizes  Added ${it.quizId} ")
                    } catch (e: UniqueConstraintException) {
                        println(">>readAllQuizes  Quiz Alredy exits ! $it ")
                    }
                }
            }
            dataBase.markExplored(category)
        } else {
            println(">>readAllQuizes  Alredy done !! ${category.quizCategoryName} ")
        }
    }
}

fun readAllCatagories() {
    val repo = dataBase.quizCategoryRepository
    val body = geekForGeekRepository.getCategories()
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
2. read All Quizzes 
e. Exit
""".trimIndent()
    )


}

fun main() {
    menu()
//    readAllCatagories()
    readAllQuizes()
    geekForGeekRepository.closeAllConnections()
}

fun timed(func: () -> Unit): Long {
    val st = System.currentTimeMillis()
    func()
    println(">>timed  ${System.currentTimeMillis() - st} ")
    return System.currentTimeMillis() - st

}