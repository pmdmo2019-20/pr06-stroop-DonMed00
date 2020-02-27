package es.iessaladillo.pedrojoya.stroop.data

import androidx.lifecycle.LiveData
import androidx.room.*
import es.iessaladillo.pedrojoya.stroop.data.entity.User

@Dao
interface UserDao {

    @Query("SELECT * FROM User")
    fun queryAllUsers(): LiveData<List<User>>


    @Query("SELECT * FROM User WHERE userId = :userId")
    fun queryUser(userId: Long): User


    @Insert
    fun insertUser(user: User)

    @Update
    fun updateUser(user: User)

    @Delete
    fun deleteUser(user: User)
}

