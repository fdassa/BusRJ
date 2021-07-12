package br.com.fdassa.busrj.features.home

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import br.com.fdassa.busrj.databinding.ActivityHomeBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeActivity : AppCompatActivity() {

    private val viewModel: HomeViewModel by viewModel()
    private lateinit var binding: ActivityHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupViews()
    }

    private fun setupViews() {
        binding.menuSearch.setOnClickListener {
            viewModel.navigateToSearchBusLine(this)
        }
    }
}