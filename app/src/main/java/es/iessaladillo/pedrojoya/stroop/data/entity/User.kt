package es.iessaladillo.pedrojoya.stroop.data.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class User(
    @PrimaryKey(autoGenerate = true) val userId: Long,
    val userName: String,
    val imageId: Int
)