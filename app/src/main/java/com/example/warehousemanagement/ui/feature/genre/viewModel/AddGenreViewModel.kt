package com.example.warehousemanagement.ui.feature.customer.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.warehousemanagement.domain.model.Customer
import com.example.warehousemanagement.domain.model.Genre
import com.example.warehousemanagement.domain.repository.WareHouseRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddGenreViewModel @Inject constructor(
    private val wareHouseRepository: WareHouseRepository
) : ViewModel() {

    fun addNewGenre(genre: Genre) {
        try {
            viewModelScope.launch {
                val response = wareHouseRepository.addNewGenre(genre = genre)
            }
        } catch (e: Exception) {
            println("Iriss $e")
        }
    }
}