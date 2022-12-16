package br.com.douglasmotta.whitelabeltutorial.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import java.sql.Timestamp

@Parcelize
data class User(
    val uid: String,
    val email: String,
    val name: String = "Desconhecido",
    val username: String = "desconhecido",
    val created: Long,
    val updated: Long,
) : Parcelable
