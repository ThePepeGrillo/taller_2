package com.example.taller2

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class UserViewModel(private val userDao: UserDao) : ViewModel() {

    // Exponemos la lista de usuarios como un StateFlow para que la UI pueda observarla

    val allUsers: StateFlow<List<User>> = userDao.getAllUsers()
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000), // El Flow se activa cuando la UI esta visible

                    initialValue = emptyList() // Valor inicial mientras se cargan los datos
        )

    // Función para añadir un nuevo usuario, se llama desde la UI.
    fun addUser(name: String, age: Int) {
        // viewModelScope es una CoroutineScope que se cancela automáticamente
        // cuando el ViewModel se destruye.
        viewModelScope.launch {
            val newUser = User(name = name, age = age)
            userDao.insertUser(newUser)
        }
    }
}