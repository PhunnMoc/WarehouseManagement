package com.example.warehousemanagement.ui.feature.user.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.warehousemanagement.data.network.dto.InformationRequest
import com.example.warehousemanagement.data.network.dto.UserRequest
import com.example.warehousemanagement.domain.repository.WareHouseRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddNewUserViewModel @Inject constructor(
    private val wareHouseRepository: WareHouseRepository,
) : ViewModel() {
    fun addNewUser(
        username: String,
        password: String,
        firstName: String,
        lastName: String,
        email: String,
        role: String,
        picture: String,
    ) {
        viewModelScope.launch {
            wareHouseRepository.addNewUser(
                UserRequest(
                    username = username,
                    password = password,
                    information = InformationRequest(
                        email = email,
                        firstName = firstName,
                        lastName = lastName,
                        role = role,
                        picture = picture,
                    )
                )
            )
        }
    }
}