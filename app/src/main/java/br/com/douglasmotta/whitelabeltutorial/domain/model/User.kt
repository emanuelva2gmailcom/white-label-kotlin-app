package br.com.douglasmotta.whitelabeltutorial.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class User(
    val uid: String,
    val email: String,
    val name: String = "Desconhecido",
    val admin: Boolean = false,
    val bio: String = "Sem descrição"
) : Parcelable