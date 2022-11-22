package br.com.douglasmotta.whitelabeltutorial.data

import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseUser

interface AuthService {

    fun singIn(email: String, password: String): Task<AuthResult>

    fun createUserWithEmailAndPassword(email: String, password: String): Task<AuthResult>

    fun getUser(): FirebaseUser

    fun signOut()
}