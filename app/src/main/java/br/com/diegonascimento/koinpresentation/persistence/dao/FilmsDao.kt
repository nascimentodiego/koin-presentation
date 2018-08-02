package br.com.diegonascimento.koinpresentation.persistence.dao

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Query
import br.com.diegonascimento.koinpresentation.model.Film

@Dao
interface FilmsDao {
    /**
     * Select all films from the films table.
     *
     * @return all films.
     */
    @Query("SELECT * FROM films")
    fun getFilms(): List<Film>

    /**
     * Insert film.
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertFilm(film: Film)

    /**
     * Delete all films.
     */
    @Query("DELETE FROM films")
    fun deleteFilms()
}