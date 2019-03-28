package br.com.diegonascimento.koinpresentation.di

import org.koin.dsl.module
import java.util.concurrent.Executor
import java.util.concurrent.Executors

val androidModule = module {
    single{ Executors.newSingleThreadExecutor() as Executor }
}