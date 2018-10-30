/*
 * Copyright (C) 2018 Diego Figueredo do Nascimento.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package br.com.diegonascimento.koinpresentation.di

import android.arch.persistence.room.Room
import br.com.diegonascimento.koinpresentation.persistence.database.StarWarsDatabase
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module.module


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