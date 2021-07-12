package br.com.fdassa.busrj.application

import br.com.fdassa.busrj.features.details.BusesByLineViewModel
import br.com.fdassa.busrj.features.home.HomeViewModel
import br.com.fdassa.busrj.features.search.SearchBusLineViewModel
import br.com.fdassa.busrj.navigation.AppNavigation
import br.com.fdassa.busrj.network.BusApi
import br.com.fdassa.busrj.network.BusRepository
import br.com.fdassa.busrj.network.NetworkConfig.provideApi
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    single { provideApi(BusApi::class.java) }
    single { BusRepository(get()) }
    single { AppNavigation() }
    viewModel { HomeViewModel(get()) }
    viewModel { BusesByLineViewModel(get()) }
    viewModel { SearchBusLineViewModel(get(), get()) }
}

