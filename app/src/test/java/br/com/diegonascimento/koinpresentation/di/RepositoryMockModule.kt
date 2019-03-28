package br.com.diegonascimento.koinpresentation.di

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import br.com.diegonascimento.koinpresentation.model.ResultFilms
import br.com.diegonascimento.koinpresentation.network.RequestResultCode
import br.com.diegonascimento.koinpresentation.network.RequestResultValue
import br.com.diegonascimento.koinpresentation.persistence.repository.FilmsRepository
import br.com.diegonascimento.koinpresentation.persistence.repository.FilmsRepositoryContract
import org.koin.dsl.module

val mockRepositoryModule = module {
    single { FilmsRepositoryMock() as FilmsRepositoryContract }
}

class FilmsRepositoryMock : FilmsRepositoryContract {
    override fun getFilms(): LiveData<RequestResultValue<ResultFilms>> {
        val data = MutableLiveData<RequestResultValue<ResultFilms>>()
        data.value = RequestResultValue(ResultFilms(), RequestResultCode.Success)

        return data
    }
}