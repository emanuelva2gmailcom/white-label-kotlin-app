package br.com.douglasmotta.whitelabeltutorial.domain.model

import android.os.Parcelable
import com.google.firebase.firestore.PropertyName
import kotlinx.parcelize.Parcelize

@Parcelize
data class Product(
    val id: String = "",
    val description: String = "",
    val price: Double = 0.0,
    @set:PropertyName("image_url")
    @get:PropertyName("image_url")
    var imageUrl: String = "" // image_url
) : Parcelable
