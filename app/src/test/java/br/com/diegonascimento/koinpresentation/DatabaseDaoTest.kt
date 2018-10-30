package br.com.diegonascimento.koinpresentation

import android.app.Application
import br.com.diegonascimento.koinpresentation.di.databaseTestModule
import br.com.diegonascimento.koinpresentation.model.Film
import br.com.diegonascimento.koinpresentation.persistence.dao.FilmsDao
import br.com.diegonascimento.koinpresentation.persistence.database.StarWarsDatabase
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.koin.android.ext.koin.with
import org.koin.standalone.StandAloneContext.startKoin
import org.koin.standalone.inject
import org.koin.test.AutoCloseKoinTest
import org.mockito.Mockito

class DatabaseDaoTest : AutoCloseKoinTest() {

    val starWarsDatabase by inject<StarWarsDatabase>()
    val filmsDao by inject<FilmsDao>()

    @Before
    fun before() {
         startKoin(
             listOf(databaseTestModule)
         ) with Mockito.mock(Application::class.java)
    }

    @Test
    fun shouldSaveFilm() {
        //Given
        val film = Film("Film 1", "desc 1")

        //when
        filmsDao.insertFilm(film)

        //Then
        val filmDb = filmsDao.getFilms()[0]
        Assert.assertEquals(film, filmDb)
    }
}