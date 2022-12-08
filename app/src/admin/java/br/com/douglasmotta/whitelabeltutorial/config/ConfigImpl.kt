package br.com.douglasmotta.whitelabeltutorial.config

import android.view.View
import javax.inject.Inject

class ConfigImpl @Inject constructor(): Config {

    override val addButtonBoolean: Int = View.VISIBLE
    override val signUpLink: Int = View.GONE
}