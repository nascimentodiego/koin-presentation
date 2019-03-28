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
package br.com.diegonascimento.koinpresentation.feature.films

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import br.com.diegonascimento.koinpresentation.model.ResultFilms
import br.com.diegonascimento.koinpresentation.network.RequestResultValue
import br.com.diegonascimento.koinpresentation.persistence.repository.FilmsRepositoryContract


class ListFilmsViewModel(private val filmsRepository: FilmsRepositoryContract) : ViewModel() {
    private var mFilms: LiveData<RequestResultValue<ResultFilms>>? = null


    fun getFilms(): LiveData<RequestResultValue<ResultFilms>>? {
        if (mFilms == null) {
            mFilms = filmsRepository.getFilms()
        }

        return this.mFilms
    }

}