package br.com.douglasmotta.whitelabeltutorial.api.auth

import android.util.Log
import br.com.douglasmotta.whitelabeltutorial.domain.model.LoggedInUser
import com.google.firebase.auth.FirebaseAuth
import java.io.IOException
import java.util.*
import javax.inject.Inject
import kotlin.coroutines.suspendCoroutine

/**
 * Class that handles authentication w/ login credentials and retrieves user information.
 */
class LoginDataSource @Inject constructor(
    private val firebaseAuth: FirebaseAuth
) {

    suspend fun login(username: String, password: String): Result<LoggedInUser> {
        return suspendCoroutine { continuation ->
            Log.d("LoginDataSource", "start")
            firebaseAuth
                .signInWithEmailAndPassword(username, password)
                .addOnCompleteListener {
                    Log.d("LoginDataSource", "complete")
                    if (it.isSuccessful) {
                        Log.d("LoginDataSource", "success")
                        it.result!!.user?.let {
                           continuation.resumeWith(
                               kotlin.Result.success(
                                   Result.Success(LoggedInUser(it.uid, it.email!!))
                               )
                           )
                        }
                    } else {
                        Log.d("LoginDataSource", "failure")
                        continuation.resumeWith(
                            kotlin.Result.success(
                                Result.Error(IOException("Error logging in", it.exception!!))
                            )
                        )
                    }
                }
        }
    }

    fun logout() {
        // TODO: revoke authentication
    }
}