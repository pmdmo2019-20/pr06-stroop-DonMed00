package es.iessaladillo.pedrojoya.stroop.data.pojo

data class UserAndGame(
    val imageId: Int,
    val userName: String,
    val gameMode: String,
    val totalTime: Int,
    val totalWords: Int,
    val corrects: Int,
    val points: Int
)