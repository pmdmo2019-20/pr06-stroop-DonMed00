package es.iessaladillo.pedrojoya.stroop.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(
)
data class Game(
    @PrimaryKey(autoGenerate = true) val gameId: Long,
    val gameMode: String,
    val totalTime: Int,
    val totalWords: Int,
    val corrects: Int,
    val points: Int
)