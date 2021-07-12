package br.com.fdassa.busrj.features.search

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import br.com.fdassa.busrj.R
import br.com.fdassa.busrj.databinding.ActivitySearchBusLineBinding
import br.com.fdassa.busrj.network.observeOnError
import br.com.fdassa.busrj.network.observeOnLoading
import br.com.fdassa.busrj.network.observeOnSuccess
import br.com.fdassa.busrj.network.setLifecycleOwner
import br.com.fdassa.busrj.utils.hide
import br.com.fdassa.busrj.utils.show
import br.com.fdassa.busrj.views.BusLineAdapter
import org.koin.androidx.viewmodel.ext.android.viewModel

class SearchBusLineActivity : AppCompatActivity() {

    private val viewModel: SearchBusLineViewModel by viewModel()
    private lateinit var binding: ActivitySearchBusLineBinding
    private val busLineAdapter: BusLineAdapter by lazy {
        BusLineAdapter { busLine ->
            viewModel.navigateToBusesByLine(this, busLine)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivitySearchBusLineBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        setupObservables()
        setupViews()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                onBackPressed()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun setupObservables() {
        viewModel.searchBusLine
            .setLifecycleOwner(this)
            .observeOnLoading {
                binding.stateView.show()
                binding.stateView.showLoading()
            }
            .observeOnError {
                binding.stateView.show()
                binding.stateView.showError()
            }
            .observeOnSuccess {
                busLineAdapter.loadBusLineList(it)
                if (it.isEmpty()) {
                    binding.rvSearchResults.hide()
                    showEmptyState()
                } else {
                    binding.stateView.hide()
                    binding.rvSearchResults.show()
                }
            }
    }

    private fun setupViews() {
        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean = handleQuery(query)
            override fun onQueryTextChange(newText: String?): Boolean = handleQuery(newText)
        })

        binding.rvSearchResults.apply {
            layoutManager = LinearLayoutManager(this@SearchBusLineActivity)
            adapter = busLineAdapter
        }

        binding.stateView.onButtonClick = {
            viewModel.retrySearch()
        }
    }

    private fun handleQuery(query: String?): Boolean = query?.let {
        if (it.length > 1) viewModel.searchBusLine(it)
        else busLineAdapter.loadBusLineList(emptyList())
        true
    } ?: false

    private fun showEmptyState() {
        binding.stateView.showEmptyState(
            iconRes = R.drawable.ic_search,
            titleRes = R.string.empty_search_title,
            descriptionRes = R.string.empty_search_description
        )
    }
}