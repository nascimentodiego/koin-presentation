package br.com.diegonascimento.koinpresentation.feature

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import br.com.diegonascimento.koinpresentation.di.androidModule
import br.com.diegonascimento.koinpresentation.di.mockRepositoryModule
import br.com.diegonascimento.koinpresentation.di.networkModule
import br.com.diegonascimento.koinpresentation.di.viewModelModule
import br.com.diegonascimento.koinpresentation.feature.films.ListFilmsViewModel
import br.com.diegonascimento.koinpresentation.model.ResultFilms
import br.com.diegonascimento.koinpresentation.network.RequestResultCode
import br.com.diegonascimento.koinpresentation.network.RequestResultValue
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin
import org.koin.test.KoinTest
import org.koin.test.inject

class ListFilmsViewModelTest : KoinTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private val viewModel: ListFilmsViewModel by inject()

    @Before
    @Throws
    fun setUp() {

        startKoin {
            // modules
            modules(
                    androidModule,
                    networkModule,
                    mockRepositoryModule,
                    viewModelModule
            )
        }
    }

    @After
    @Throws
    fun tearDown() {
        stopKoin()
    }

    @Test
    fun shouldLoadListOfJobs_WhenMethodLoadFilms_isSuccess() {
        val expected = RequestResultValue(ResultFilms(), RequestResultCode.Success).requestResultCode
        val actual = viewModel.getFilms()!!.value!!.requestResultCode

        assert(expected == actual)

    }
}