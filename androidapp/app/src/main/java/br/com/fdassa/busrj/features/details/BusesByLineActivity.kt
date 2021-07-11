package br.com.fdassa.busrj.features.details

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import br.com.fdassa.busrj.R
import br.com.fdassa.busrj.databinding.ActivityBusesByLineBinding
import br.com.fdassa.busrj.network.observeOnError
import br.com.fdassa.busrj.network.observeOnLoading
import br.com.fdassa.busrj.network.observeOnSuccess
import br.com.fdassa.busrj.network.setLifecycleOwner
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import org.koin.androidx.viewmodel.ext.android.viewModel

class BusesByLineActivity : AppCompatActivity(), OnMapReadyCallback {

    private val viewModel: BusesByLineViewModel by viewModel()
    private lateinit var mMap: GoogleMap
    private lateinit var binding: ActivityBusesByLineBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityBusesByLineBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
        setupObservables()
    }

    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap

        // Add a marker in Sydney and move the camera
        val sydney = LatLng(-34.0, 151.0)
        mMap.addMarker(MarkerOptions().position(sydney).title("Marker in Sydney"))
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney))
        viewModel.getBusesByLine("urn:ngsi-ld:Linha:343")
    }

    private fun setupObservables() {
        viewModel.busesByLine
            .setLifecycleOwner(this)
            .observeOnLoading {}
            .observeOnError {
                showToast("Erro")
            }
            .observeOnSuccess {
                showToast("Sucesso")
            }
    }

    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
    }
}