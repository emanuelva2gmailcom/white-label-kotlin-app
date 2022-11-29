package br.com.douglasmotta.whitelabeltutorial.ui.home

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import br.com.douglasmotta.whitelabeltutorial.R
import br.com.douglasmotta.whitelabeltutorial.databinding.ActivityHomeBinding
import br.com.douglasmotta.whitelabeltutorial.ui.login.LoginActivity
import br.com.douglasmotta.whitelabeltutorial.ui.product.MainActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeActivity : AppCompatActivity() {

    private var _binding: ActivityHomeBinding? = null
    private val binding get() = _binding!!

    private val viewModel: HomeViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        _binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setListeners()
    }

    override fun onDestroy() {
        _binding = null
        super.onDestroy()
    }

    private fun setListeners() {
        with(binding) {
            mainTitle.text = "Welcome, ${viewModel.getUserEmail()}"

            buttonLinkProducts.setOnClickListener {
                val intent = Intent(this@HomeActivity, MainActivity::class.java)
                startActivity(intent)
            }

            buttonLogout.setOnClickListener {
                viewModel.logout()
                val intent = Intent(this@HomeActivity, LoginActivity::class.java)
                startActivity(intent)
            }
        }
    }

}