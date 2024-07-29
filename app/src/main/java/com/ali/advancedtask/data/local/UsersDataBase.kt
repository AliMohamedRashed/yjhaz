package com.ali.advancedtask.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(
    entities = [LocalUser::class],
    version = 1,
    exportSchema = false
)
abstract class UsersDataBase: RoomDatabase() {
    abstract val dao: UsersDAO

    companion object{
        @Volatile
        private var daoInstance: UsersDAO? = null

        private fun buildDatabase(context: Context): UsersDataBase =
            Room.databaseBuilder(
                context,
                UsersDataBase::class.java,
                "users_database"
            ).fallbackToDestructiveMigration().build()

        fun getDAOInstance(context: Context): UsersDAO {
            synchronized(this){
                if(daoInstance == null){
                    daoInstance = buildDatabase(context).dao
                }
                return daoInstance as UsersDAO
            }
        }
    }
}