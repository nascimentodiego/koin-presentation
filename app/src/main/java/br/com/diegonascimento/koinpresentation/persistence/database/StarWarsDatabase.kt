package br.com.diegonascimento.koinpresentation.persistence.database

import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import android.content.Context
import br.com.diegonascimento.koinpresentation.model.Film
import br.com.diegonascimento.koinpresentation.persistence.dao.FilmsDao

@Database(entities = arrayOf(Film::class), version = 1, exportSchema = false)
abstract class StarWarsDatabase : RoomDatabase() {

    abstract fun filmsDao(): FilmsDao

    companion object {
        val DB_NAME = "starwars_db"
    }
}