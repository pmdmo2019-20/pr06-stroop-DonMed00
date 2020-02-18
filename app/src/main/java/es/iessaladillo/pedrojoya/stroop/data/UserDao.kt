package es.iessaladillo.pedrojoya.stroop.data

import androidx.lifecycle.LiveData
import androidx.room.*
import es.iessaladillo.pedrojoya.stroop.data.entity.User

@Dao
interface UserDao {

    @Query("SELECT * FROM users")
    fun queryAllUsers(): LiveData<List<User>>


    @Query("SELECT * FROM users WHERE userId = :userId")
    fun queryUser(userId: Long): LiveData<User>





    @Insert
    fun insertUser(user: User): Long

    @Update
    fun updateUser(user: User): Int

    @Delete
    fun deleteUser(user: User): Int


    ​
}