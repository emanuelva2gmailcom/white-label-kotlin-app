package br.com.douglasmotta.whitelabeltutorial.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class SignUpForm(
    val name: String,
    val username: String,
    val email: String,
    val password: String,
): Parcelable
