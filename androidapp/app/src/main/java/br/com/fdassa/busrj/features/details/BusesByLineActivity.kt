package br.com.fdassa.busrj.features.details

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import br.com.fdassa.busrj.R
import br.com.fdassa.busrj.databinding.ActivityBusesByLineBinding
import br.com.fdassa.busrj.navigation.AppNavigation.Companion.EXTRA_BUS_LINE
import br.com.fdassa.busrj.network.models.Bus
import br.com.fdassa.busrj.network.models.BusLine
import br.com.fdassa.busrj.network.observeOnError
import br.com.fdassa.busrj.network.observeOnLoading
import br.com.fdassa.busrj.network.observeOnSuccess
import br.com.fdassa.busrj.network.setLifecycleOwner
import br.com.fdassa.busrj.utils.bitmapDescriptorFromVector
import br.com.fdassa.busrj.utils.hide
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.LatLngBounds
import com.google.android.gms.maps.model.MarkerOptions
import org.koin.androidx.viewmodel.ext.android.viewModel

class BusesByLineActivity : AppCompatActivity() {

    private val viewModel: BusesByLineViewModel by viewModel()
    private lateinit var googleMap: GoogleMap
    private lateinit var binding: ActivityBusesByLineBinding
    private val busLine by lazy { intent.getSerializableExtra(EXTRA_BUS_LINE) as BusLine }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityBusesByLineBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupViews()
        setupObservables()
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
        title = getString(R.string.line, busLine.name)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        binding.stateView.onButtonClick = {
            viewModel.getBusesByLine(busLine.id)
        }

        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync {
            googleMap = it
            googleMap.setMinZoomPreference(10f)
            googleMap.setMaxZoomPreference(25f)
            viewModel.getBusesByLine(busLine.id)
        }
    }

    private fun setupObservables() {
        viewModel.busesByLine
            .setLifecycleOwner(this)
            .observeOnLoading {
                binding.stateView.showLoading()
            }
            .observeOnError {
                binding.stateView.showError()
            }
            .observeOnSuccess {
                binding.stateView.hide()
                displayBusesLocations(it)
            }
    }

    private fun displayBusesLocations(buses: List<Bus>) {
        buses.forEach {
            val location = LatLng(it.latitude, it.longitude)
            googleMap.addMarker(
                MarkerOptions()
                    .position(location)
                    .icon(bitmapDescriptorFromVector(R.drawable.ic_bus_marker))
            )
        }
        val southwest = LatLng(
            buses.minByOrNull { it.latitude }!!.latitude,
            buses.minByOrNull { it.longitude }!!.longitude
        )
        val northeast = LatLng(
            buses.maxByOrNull { it.latitude }!!.latitude,
            buses.maxByOrNull { it.longitude }!!.longitude
        )
        val bounds = LatLngBounds(southwest, northeast)
        googleMap.setLatLngBoundsForCameraTarget(bounds)
    }
}