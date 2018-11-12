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

package br.com.diegonascimento.koinpresentation.persistence.repository

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import br.com.diegonascimento.koinpresentation.diKodein.kodeinAndroidModule
import br.com.diegonascimento.koinpresentation.model.ResultFilms
import br.com.diegonascimento.koinpresentation.network.RequestResultCode
import br.com.diegonascimento.koinpresentation.network.RequestResultValue
import br.com.diegonascimento.koinpresentation.network.starwars.StarWarsApi
import br.com.diegonascimento.koinpresentation.persistence.dao.FilmsDao
import java.util.concurrent.Executor


class FilmsRepository(private var starWarApi: StarWarsApi,
                      private var filmsDao: FilmsDao,
                      private var executor: Executor) : BaseRepository(), FilmsRepositoryContract {



    override fun getFilms(): LiveData<RequestResultValue<ResultFilms>> {
        val data = MutableLiveData<RequestResultValue<ResultFilms>>()

        executor.execute {
            doRequest({
                var response = starWarApi.getFilms().execute()

                if (response.isSuccessful) {
                    val requestResultCode = RequestResultCode.valueOf(response.code())
                    val result = RequestResultValue(response.body(), requestResultCode)

                    filmsDao.deleteFilms()
                    response.body()?.results?.forEach { filmsDao.insertFilm(it) }
                    result.value = response.body()
                    data.postValue(result)
                } else {
                    data.postValue(getFilmsDataBase(response.code()))
                }
            }, { data.postValue(getFilmsDataBase()) })
        }

        return data
    }

    private fun getFilmsDataBase(errorCode: Int = 999): RequestResultValue<ResultFilms> {
        val resultFilms = ResultFilms()
        val result = RequestResultValue(resultFilms, RequestResultCode.valueOf(errorCode))
        resultFilms.results = filmsDao.getFilms()
        return result
    }
}