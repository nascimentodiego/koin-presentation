package br.com.diegonascimento.koinpresentation

import android.app.Application
import br.com.diegonascimento.koinpresentation.di.*
import br.com.diegonascimento.koinpresentation.persistence.repository.FilmsRepositoryContract
import org.junit.After
import org.junit.Test

import org.junit.Before
import org.koin.android.ext.koin.with
import org.koin.standalone.StandAloneContext.startKoin
import org.koin.standalone.StandAloneContext.stopKoin
import org.koin.standalone.inject
import org.koin.test.KoinTest
import org.mockito.Mockito
import org.mockito.Mockito.mock


class ExampleUnitTest : KoinTest {

    private val repository: FilmsRepositoryContract by inject()

    @Before
    fun before() {
        startKoin(
            listOf(
                androidModule,
                networkModule,
                databaseTestModule,
                repositoryModule
            )
        ) with mock(Application::class.java)
    }

    @After
    fun after() {
        stopKoin()
    }

    @Test
    fun shouldGetFilms() {
        Mockito.`when`(repository.getFilms()).thenCallRealMethod()
    }
}
