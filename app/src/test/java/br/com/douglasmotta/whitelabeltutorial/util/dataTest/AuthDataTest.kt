package br.com.douglasmotta.whitelabeltutorial.util.dataTest

import br.com.douglasmotta.whitelabeltutorial.domain.model.LoggedInUser
import br.com.douglasmotta.whitelabeltutorial.domain.model.UserAuth
import br.com.douglasmotta.whitelabeltutorial.ui.login.models.LoggedInUserView
import br.com.douglasmotta.whitelabeltutorial.ui.login.models.LoginResult


const val USER_AUTH_EMAIL_TEST = "emailvalid@gmail.com"

const val USER_AUTH_UID_TEST = "1"

const val USER_AUTH_PASSWORD_TEST = "12345678"

const val USER_AUTH_INVALID_EMAIL_TEST = "emailinvalidgmail.com"

const val USER_AUTH_INVALID_PASSWORD_TEST = "12345678910"

val LOGGED_IN_USER_TEST = LoggedInUser(
    USER_AUTH_EMAIL_TEST,
    USER_AUTH_PASSWORD_TEST
)

val USER_AUTH_TEST = UserAuth(
    USER_AUTH_UID_TEST,
    USER_AUTH_EMAIL_TEST
)

val LOGGED_IN_USER_VIEW_TEST = LoggedInUserView(
    USER_AUTH_EMAIL_TEST
)
