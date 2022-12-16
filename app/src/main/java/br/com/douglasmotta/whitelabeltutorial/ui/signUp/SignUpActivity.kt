package br.com.douglasmotta.whitelabeltutorial.ui.signUp

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.EditText
import android.widget.Toast
import androidx.activity.viewModels
import androidx.annotation.StringRes
import androidx.lifecycle.Observer
import br.com.douglasmotta.whitelabeltutorial.R
import br.com.douglasmotta.whitelabeltutorial.databinding.ActivitySignUpBinding
import br.com.douglasmotta.whitelabeltutorial.domain.model.SignUpForm
import br.com.douglasmotta.whitelabeltutorial.ui.login.LoginActivity
import br.com.douglasmotta.whitelabeltutorial.ui.login.afterTextChanged
import br.com.douglasmotta.whitelabeltutorial.ui.login.models.LoggedInUserView
import br.com.douglasmotta.whitelabeltutorial.ui.product.MainActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SignUpActivity : AppCompatActivity() {

    private val viewModel: SignUpViewModel by viewModels()

    private var _binding: ActivitySignUpBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        _binding = ActivitySignUpBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setListeners()
        observeVMEvents()
    }

    private fun observeVMEvents() {
        with(binding) {

            viewModel.signUpFormState.observe(this@SignUpActivity, Observer {
                val signupState = it ?: return@Observer

                // disable login button unless both username / password is valid
                signUpBtn.isEnabled = signupState.isDataValid

                if (signupState.usernameError != null) {
                    username.error = getString(signupState.usernameError)
                }
                if (signupState.passwordError != null) {
                    password.error = getString(signupState.passwordError)
                }
            })

            viewModel.signUpResult.observe(this@SignUpActivity, Observer {
                val signUpResult = it ?: return@Observer

                loading.visibility = View.GONE
                if (signUpResult.error != null) {
                    showSignUpFailed(signUpResult.error)
                    return@Observer
                }
                setResult(Activity.RESULT_OK)

                //Complete and destroy login activity once successful
                finish()
                val intent = Intent(this@SignUpActivity, LoginActivity::class.java)
                startActivity(intent)
            })
        }
    }

    private fun setListeners() {
        with(binding) {
            loginLink.setOnClickListener {
                finish()
                val intent = Intent(this@SignUpActivity, LoginActivity::class.java)
                startActivity(intent)
            }

            name.afterTextChanged {
                viewModel.signUpDataChanged(
                    SignUpForm(
                        name.text.toString().trim(),
                        username.text.toString().trim(),
                        email.text.toString().trim(),
                        password.text.toString().trim()
                    )
                )
            }

            username.afterTextChanged {
                viewModel.signUpDataChanged(
                    SignUpForm(
                        name.text.toString().trim(),
                        username.text.toString().trim(),
                        email.text.toString().trim(),
                        password.text.toString().trim()
                    )
                )
            }

            email.afterTextChanged {
                viewModel.signUpDataChanged(
                    SignUpForm(
                        name.text.toString().trim(),
                        username.text.toString().trim(),
                        email.text.toString().trim(),
                        password.text.toString().trim()
                    )
                )
            }

            password.apply {
                afterTextChanged {
                    viewModel.signUpDataChanged(
                        SignUpForm(
                            name.text.toString().trim(),
                            username.text.toString().trim(),
                            email.text.toString().trim(),
                            password.text.toString().trim()
                        )
                    )
                }

                setOnEditorActionListener { _, actionId, _ ->
                    when (actionId) {
                        EditorInfo.IME_ACTION_DONE ->
                            viewModel.signUp(
                                SignUpForm(
                                    name.text.toString().trim(),
                                    username.text.toString().trim(),
                                    email.text.toString().trim(),
                                    password.text.toString().trim()
                                )
                            )
                    }
                    false
                }

            }

            signUpBtn.setOnClickListener {
                loading.visibility = View.VISIBLE
                viewModel.signUp(
                    SignUpForm(
                        name.text.toString().trim(),
                        username.text.toString().trim(),
                        email.text.toString().trim(),
                        password.text.toString().trim()
                    )
                )
            }
        }
    }

    private fun showSignUpFailed(@StringRes errorString: Int) {
        Toast.makeText(applicationContext, errorString, Toast.LENGTH_SHORT).show()
    }
}

/**
 * Extension function to simplify setting an afterTextChanged action to EditText components.
 */
fun EditText.afterTextChanged(afterTextChanged: (String) -> Unit) {
    this.addTextChangedListener(object : TextWatcher {
        override fun afterTextChanged(editable: Editable?) {
            afterTextChanged(editable.toString())
        }

        override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}

        override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {}
    })
}