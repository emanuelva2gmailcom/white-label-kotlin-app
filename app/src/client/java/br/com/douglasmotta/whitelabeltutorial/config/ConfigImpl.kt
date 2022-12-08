package br.com.douglasmotta.whitelabeltutorial.config

import android.view.View
import javax.inject.Inject

class ConfigImpl @Inject constructor() : Config {

    override val addButtonBoolean: Int = View.GONE
    override val signUpLink: Int = View.VISIBLE
}