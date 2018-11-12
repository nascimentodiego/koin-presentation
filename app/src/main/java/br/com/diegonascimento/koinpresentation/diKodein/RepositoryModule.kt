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
package br.com.diegonascimento.koinpresentation.diKodein

import br.com.diegonascimento.koinpresentation.persistence.repository.FilmsRepository
import br.com.diegonascimento.koinpresentation.persistence.repository.FilmsRepositoryContract
import org.kodein.di.Kodein
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.singleton


val kodeinrepositoryModule = Kodein.Module(name = "kodeinrepositoryModule") {
    bind<FilmsRepositoryContract>() with singleton {
        FilmsRepository(instance(), instance(), instance("executor"))
    }
}