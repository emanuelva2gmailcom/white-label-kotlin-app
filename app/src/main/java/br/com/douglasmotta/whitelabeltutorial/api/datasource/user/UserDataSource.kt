package br.com.douglasmotta.whitelabeltutorial.api.datasource.user

import br.com.douglasmotta.whitelabeltutorial.domain.model.User

interface UserDataSource {

    suspend fun getProfile(uid: String): User

    fun createUser(user: User): User
}