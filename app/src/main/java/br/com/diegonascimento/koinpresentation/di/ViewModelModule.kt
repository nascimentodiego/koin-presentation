package br.com.diegonascimento.koinpresentation.di

import br.com.diegonascimento.koinpresentation.feature.films.ListFilmsViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { ListFilmsViewModel(get()) }
}