package br.com.diegonascimento.koinpresentation.persistence.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
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