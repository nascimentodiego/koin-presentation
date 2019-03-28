package br.com.diegonascimento.koinpresentation.di

import br.com.diegonascimento.koinpresentation.persistence.repository.FilmsRepository
import br.com.diegonascimento.koinpresentation.persistence.repository.FilmsRepositoryContract
import org.koin.dsl.module

val repositoryModule = module {
    single { FilmsRepository(get(), get(), get()) as FilmsRepositoryContract }
}