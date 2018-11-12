package br.com.diegonascimento.koinpresentation.feature.films

import android.arch.lifecycle.Observer
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
import kotlinx.android.synthetic.main.activity_main.*
import org.kodein.di.KodeinAware
import org.kodein.di.android.closestKodein
import org.kodein.di.generic.instance
import org.koin.android.viewmodel.ext.android.viewModel
import java.util.concurrent.Executor

class ListFilmsActivity : BaseActivity(), KodeinAware {

    private val viewModel: ListFilmsViewModel by viewModel()
    private lateinit var adapter: ItemFilmAdapter

    override val kodein by closestKodein()
    private val executor: Executor by instance()

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
