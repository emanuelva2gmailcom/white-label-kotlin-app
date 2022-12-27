package br.com.douglasmotta.whitelabeltutorial.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class UserAuth(
    val uid: String,
    val email: String
): Parcelable
