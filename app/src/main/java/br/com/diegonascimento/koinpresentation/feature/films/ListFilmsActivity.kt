package br.com.diegonascimento.koinpresentation.feature.films

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.widget.Toast
import br.com.diegonascimento.koinpresentation.R
import br.com.diegonascimento.koinpresentation.StarWarsApplication
import br.com.diegonascimento.koinpresentation.feature.base.BaseActivity
import br.com.diegonascimento.koinpresentation.feature.films.adapters.ItemFilmAdapter
import br.com.diegonascimento.koinpresentation.model.ResultFilms
import br.com.diegonascimento.koinpresentation.network.RequestResultCode
import br.com.diegonascimento.koinpresentation.network.RequestResultValue
import br.com.diegonascimento.koinpresentation.network.starwars.StarWarsApi
import br.com.diegonascimento.koinpresentation.persistence.dao.FilmsDao
import br.com.diegonascimento.koinpresentation.persistence.database.StarWarsDatabase
import kotlinx.android.synthetic.main.activity_main.*
import okhttp3.OkHttpClient
import org.kodein.di.KodeinAware
import org.kodein.di.android.closestKodein
import org.kodein.di.generic.instance
import org.koin.android.viewmodel.ext.android.viewModel
import retrofit2.Retrofit
import java.util.concurrent.Executor

class ListFilmsActivity : BaseActivity(), KodeinAware {

//    private val viewModel: ListFilmsViewModel by viewModel()
    private lateinit var adapter: ItemFilmAdapter

    override val kodein by closestKodein()
    private val executor: Executor by instance()

    private val okhttp: OkHttpClient by instance("OkHttpClient")
    private val retrofit: Retrofit by instance()
    private val api: StarWarsApi by instance()
    private val starWarsDatabase:StarWarsDatabase  by instance()
    private val filmsDao:FilmsDao  by instance()

    private val viewModeFactory: ViewModelProvider.Factory by instance()
    private val viewModel: ListFilmsViewModel by lazy {
        ViewModelProviders.of(this, viewModeFactory).get(ListFilmsViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initView()
        subscribeUi(viewModel)
    }

    private fun initView() {
        val layoutManager = LinearLayoutManager(baseContext)
        lst_films.setHasFixedSize(false)
        lst_films.layoutManager = layoutManager
    }

    private fun subscribeUi(viewModel: ListFilmsViewModel) {
        // Update the list when the data changes
        viewModel.getFilms()?.observe(this, Observer<RequestResultValue<ResultFilms>> { films ->
            val list = films?.value?.results ?: listOf()
            adapter = ItemFilmAdapter(list)
            lst_films.adapter = adapter

            when (films?.requestResultCode) {
                !is RequestResultCode.Success -> {
                    Toast.makeText(baseContext, "Dados Off-line", Toast.LENGTH_SHORT).show()
                }
            }
        })
    }
}
