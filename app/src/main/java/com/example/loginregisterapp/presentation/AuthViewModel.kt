package com.example.loginregisterapp.presentation
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.loginregisterapp.data.model.AuthRequest
import com.example.loginregisterapp.domain.repository.AuthRepository
import com.example.loginregisterapp.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(
    private val repository: AuthRepository
) : ViewModel() {

    private val _authResult = MutableStateFlow<Resource<String>?>(null)
    val authResult = _authResult.asStateFlow()

    fun register(username: String, password: String) {
        viewModelScope.launch {
            repository.registerUser(AuthRequest(username, password)).collect {
                _authResult.value = it
            }
        }
    }

    fun login(username: String, password: String) {
        viewModelScope.launch {
            repository.loginUser(AuthRequest(username, password)).collect {
                _authResult.value = it
            }
        }
    }

    fun clearResult() {
        _authResult.value = Resource.Loading()
    }
}
