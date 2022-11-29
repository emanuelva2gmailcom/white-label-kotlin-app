package br.com.douglasmotta.whitelabeltutorial.api.datasource.user

import br.com.douglasmotta.whitelabeltutorial.domain.model.User
import javax.inject.Inject

class UserRepository @Inject constructor(
    private val dataSource: UserDataSource
) {

    suspend fun getProfile(uid: String): User = dataSource.getProfile(uid)

    fun createUser(user: User): User = dataSource.createUser(user)
}