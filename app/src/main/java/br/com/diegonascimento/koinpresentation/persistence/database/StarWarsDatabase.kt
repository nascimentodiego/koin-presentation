package br.com.diegonascimento.koinpresentation.persistence.database

import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.content.Context
import br.com.diegonascimento.koinpresentation.model.Film
import br.com.diegonascimento.koinpresentation.persistence.dao.FilmsDao

@Database(entities = arrayOf(Film::class), version = 1, exportSchema = false)
abstract class StarWarsDatabase : RoomDatabase() {

    abstract fun filmsDao(): FilmsDao

    companion object {
        val DB_NAME = "starwars_db"
        var dbInstance: StarWarsDatabase? = null

        fun getDatabase(context: Context): StarWarsDatabase? {
            if (dbInstance == null) {
                dbInstance = Room.databaseBuilder<StarWarsDatabase>(context.applicationContext,
                        StarWarsDatabase::class.java, DB_NAME).build()
            }
            return dbInstance
        }
    }
}