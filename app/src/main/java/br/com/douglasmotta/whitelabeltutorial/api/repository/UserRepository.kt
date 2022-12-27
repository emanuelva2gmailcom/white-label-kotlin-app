package br.com.douglasmotta.whitelabeltutorial.api.repository

import br.com.douglasmotta.whitelabeltutorial.api.datasource.user.UserDataSource
import br.com.douglasmotta.whitelabeltutorial.domain.model.User
import javax.inject.Inject

class UserRepository @Inject constructor(
    private val dataSource: UserDataSource
) {

    suspend fun getProfile(uid: String): User = dataSource.getProfile(uid)

    suspend fun createUser(user: User): User = dataSource.createUser(user)
}