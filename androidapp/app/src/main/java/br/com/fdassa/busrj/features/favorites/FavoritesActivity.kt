package br.com.fdassa.busrj.features.favorites

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import br.com.fdassa.busrj.R
import br.com.fdassa.busrj.databinding.ActivityFavoritesBinding
import br.com.fdassa.busrj.utils.hide
import br.com.fdassa.busrj.utils.show
import br.com.fdassa.busrj.views.BusLineAdapter
import org.koin.androidx.viewmodel.ext.android.viewModel

class FavoritesActivity : AppCompatActivity() {

    private val viewModel: FavoritesViewModel by viewModel()
    private lateinit var binding: ActivityFavoritesBinding
    private val busLineAdapter: BusLineAdapter by lazy {
        BusLineAdapter { busLine ->
            viewModel.navigateToBusesByLine(this, busLine)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityFavoritesBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        setupViews()
    }

    override fun onResume() {
        super.onResume()

        viewModel.getFavorites().let { favorites ->
            busLineAdapter.loadBusLineList(favorites)
            if (favorites.isEmpty()) showEmptyState()
            else binding.stateView.hide()
        }
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

    private fun setupViews() {
        binding.rvFavorites.apply {
            layoutManager = LinearLayoutManager(this@FavoritesActivity)
            adapter = busLineAdapter
        }
    }

    private fun showEmptyState() {
        binding.stateView.show()
        binding.stateView.showEmptyState(
            iconRes = R.drawable.ic_favorite,
            titleRes = R.string.empty_favorites_title,
            descriptionRes = R.string.empty_favorites_description
        )
    }
}