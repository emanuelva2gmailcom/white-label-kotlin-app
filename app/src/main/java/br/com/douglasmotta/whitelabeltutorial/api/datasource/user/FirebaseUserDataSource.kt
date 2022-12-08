package br.com.douglasmotta.whitelabeltutorial.api.datasource.user

import br.com.douglasmotta.whitelabeltutorial.BuildConfig
import br.com.douglasmotta.whitelabeltutorial.domain.model.User
import br.com.douglasmotta.whitelabeltutorial.util.COLLECTION_ROOT
import br.com.douglasmotta.whitelabeltutorial.util.COLLECTION_USERS
import com.google.firebase.firestore.FirebaseFirestore
import javax.inject.Inject
import kotlin.coroutines.suspendCoroutine

class FirebaseUserDataSource @Inject constructor(
    firebaseFirestore: FirebaseFirestore
) : UserDataSource {

    private val documentReference = firebaseFirestore
        .collection("$COLLECTION_ROOT/${BuildConfig.FIREBASE_FLAVOR_COLLECTION}/" +
                "${BuildConfig.FIREBASE_FLAVOR_AUTH_COLLECTION}/")

    override suspend fun getProfile(uid: String): User {
        return suspendCoroutine { continuation ->
            documentReference
                .whereEqualTo("uid", uid).get()
                .addOnSuccessListener { documents ->
                    val product = documents.first().toObject(User::class.java)
                    continuation.resumeWith(Result.success(product))
                }
                .addOnFailureListener {
                    continuation.resumeWith(Result.failure(it))
                }
        }
    }

    override suspend fun createUser(user: User): User {
        return suspendCoroutine { continuation ->
            documentReference
                .document(user.uid)
                .set(user)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        continuation.resumeWith(Result.success(user))
                    } else {
                        continuation.resumeWith(Result.failure(task.exception!!))
                    }
                }

        }
    }
}