package br.com.douglasmotta.whitelabeltutorial.api.datasource.product

import android.net.Uri
import android.util.Log
import androidx.core.net.toUri
import br.com.douglasmotta.whitelabeltutorial.BuildConfig
import br.com.douglasmotta.whitelabeltutorial.domain.model.Product
import br.com.douglasmotta.whitelabeltutorial.util.COLLECTION_PRODUCTS
import br.com.douglasmotta.whitelabeltutorial.util.COLLECTION_ROOT
import br.com.douglasmotta.whitelabeltutorial.util.STORAGE_IMAGES
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage
import java.lang.Exception
import java.util.*
import javax.inject.Inject
import kotlin.coroutines.suspendCoroutine

class FirebaseProductDataSource @Inject constructor(
    firebaseFirestore: FirebaseFirestore,
    firebaseStorage: FirebaseStorage
) : ProductDataSource {

    private val documentReference = firebaseFirestore
        .document("$COLLECTION_ROOT/${BuildConfig.FIREBASE_FLAVOR_COLLECTION}/")

    private val storageReference = firebaseStorage.reference

    override suspend fun getProductById(id: String): Product {
        return suspendCoroutine { continuation ->
            documentReference
                .collection(COLLECTION_PRODUCTS)
                .whereEqualTo("id", id)
                .get()
                .addOnSuccessListener { documents ->
                    continuation.resumeWith(Result.success(
                        documents.first().toObject(Product::class.java)
                    ))
                }
                .addOnFailureListener { exception ->
                    continuation.resumeWith(Result.failure(exception))
                }
        }
    }

    override suspend fun getProducts(): List<Product> {
        return suspendCoroutine { continuation ->
            val productsReference = documentReference.collection(COLLECTION_PRODUCTS)
            productsReference.get().addOnSuccessListener { documents ->
                val products = mutableListOf<Product>()
                for (document in documents) {
                    document.toObject(Product::class.java).run {
                        products.add(this)
                    }
                }
                continuation.resumeWith(Result.success(products))
            }

            productsReference.get().addOnFailureListener { exception ->
                continuation.resumeWith(Result.failure(exception))
            }
        }
    }

    override suspend fun uploadProductImage(imageUri: Uri): String {
        return suspendCoroutine { continuation ->
            val randomKey = UUID.randomUUID()
            val childReference = storageReference.child(
                "$STORAGE_IMAGES/${BuildConfig.FIREBASE_FLAVOR_COLLECTION}/$randomKey"
            )

            childReference.putFile(imageUri)
                .addOnSuccessListener { taskSnapshot ->
                    taskSnapshot.storage.downloadUrl.addOnSuccessListener { uri ->
                        val path = uri.toString()
                        continuation.resumeWith(Result.success(path))
                    }
                }.addOnFailureListener { exception ->
                    continuation.resumeWith(Result.failure(exception))
                }
        }
    }

    override suspend fun deleteProductImage(imageId: String): Boolean {
        val imageUri = imageId.toUri().lastPathSegment!!

        return suspendCoroutine { continuation ->
            val childReference = storageReference.child(imageUri)

            childReference.delete()
                .addOnSuccessListener {
                    continuation.resumeWith(Result.success(true))
                }.addOnFailureListener { exception ->
                    continuation.resumeWith(Result.failure(exception))
                }
        }
    }

    override suspend fun updateProductImage(id: String, uri: Uri): String {
        val product = getProductById(id)
        val imagePath = product.imageUrl.toUri().lastPathSegment!!

        return suspendCoroutine { continuation ->
            val childReference = storageReference.child(imagePath)

            childReference.putFile(uri)
                .addOnSuccessListener { taskSnapshot ->
                    taskSnapshot.storage.downloadUrl.addOnSuccessListener { uri ->
                        val path = uri.toString()
                        continuation.resumeWith(Result.success(path))
                    }
                }.addOnFailureListener { exception ->
                    continuation.resumeWith(Result.failure(exception))
                }
        }
    }

    override suspend fun createProduct(product: Product): Product {
        return suspendCoroutine { continuation ->
            documentReference
                .collection(COLLECTION_PRODUCTS)
                .document(System.currentTimeMillis().toString())
                .set(product)
                .addOnSuccessListener {
                    continuation.resumeWith(Result.success(product))
                }
                .addOnFailureListener { exception ->
                    continuation.resumeWith(Result.failure(exception))
                }
        }
    }

    override suspend fun deleteProduct(id: String): Product {
        return suspendCoroutine { continuation ->
            documentReference
                .collection(COLLECTION_PRODUCTS)
                .whereEqualTo("id", id)
                .get()
                .addOnSuccessListener { documents ->
                    documentReference
                        .collection(COLLECTION_PRODUCTS)
                        .document(documents.first().reference.id)
                        .delete()
                        .addOnSuccessListener {
                            continuation.resumeWith(
                                Result.success(documents.first().toObject(Product::class.java))
                            )
                        }
                        .addOnFailureListener { exception ->
                            continuation.resumeWith(Result.failure(exception))
                        }
                }
                .addOnFailureListener { exception ->
                    continuation.resumeWith(Result.failure(exception))
                }
        }
    }

    override suspend fun updateProduct(product: Product): Product {
        return suspendCoroutine { continuation ->
            documentReference
                .collection(COLLECTION_PRODUCTS)
                .whereEqualTo("id", product.id)
                .get()
                .addOnSuccessListener { documents ->
                    Log.d(null, documents.first().reference.path)
                    if (product.imageUrl.isEmpty()) {
                        product.imageUrl = documents.first().get("image_url").toString()
                    }
                    documentReference
                        .collection(COLLECTION_PRODUCTS)
                        .document(documents.first().reference.id)
                        .set(product)
                        .addOnSuccessListener {
                            continuation.resumeWith(Result.success(product))
                        }
                        .addOnFailureListener { exception ->
                            continuation.resumeWith(Result.failure(exception))
                        }
                }
                .addOnFailureListener { exception ->
                    continuation.resumeWith(Result.failure(exception))
                }
        }
    }

}