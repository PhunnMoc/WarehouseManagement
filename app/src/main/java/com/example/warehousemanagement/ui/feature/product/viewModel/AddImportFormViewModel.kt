package com.example.warehousemanagement.ui.feature.product.viewModel

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.example.warehousemanagement.domain.model.Product
import com.example.warehousemanagement.domain.repository.WareHouseRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AddImportFormViewModel @Inject constructor(
    private val wareHouseRepository: WareHouseRepository,
    private val savedStateHandle: SavedStateHandle,
) : ViewModel() {

    private val _productList = mutableStateListOf<Product>()
    val productList: List<Product> = _productList

    private var _currentIndex = -1
    val currentIndex: Int
        get() = _currentIndex

    fun addOrUpdateProduct(product: Product) {
        if (_currentIndex in _productList.indices) {
            _productList[_currentIndex] = product // Cập nhật sản phẩm hiện tại
        } else {
            _productList.add(product) // Thêm sản phẩm mới
            _currentIndex = _productList.size - 1
        }
    }

    fun nextProduct() {
        if (_currentIndex < _productList.size - 1) {
            _currentIndex++
        } else {
            _currentIndex = _productList.size // Chuẩn bị cho sản phẩm mới
        }
    }

    // Quay lại sản phẩm trước đó
    fun previousProduct() {
        if (_currentIndex > 0) {
            _currentIndex--
        }
    }

    // Lấy sản phẩm tại một chỉ số
    fun getProductAt(index: Int): Product? {
        return if (index in _productList.indices) {
            _productList[index]
        } else {
            null
        }
    }

    // Xóa sản phẩm tại một chỉ số
    fun removeProductAt(index: Int) {
        if (index in _productList.indices) {
            _productList.removeAt(index)
            if (_currentIndex >= _productList.size) {
                _currentIndex = _productList.size - 1
            }
        }
    }
}

