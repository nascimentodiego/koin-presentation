package br.com.diegonascimento.koinpresentation.di

import androidx.room.Room
import br.com.diegonascimento.koinpresentation.persistence.database.StarWarsDatabase
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val databaseModule = module {
    single {
        Room.databaseBuilder<StarWarsDatabase>(
                androidContext(),
                StarWarsDatabase::class.java,
                StarWarsDatabase.DB_NAME
        ).build()
    }

    single { (get() as StarWarsDatabase).filmsDao() }
}