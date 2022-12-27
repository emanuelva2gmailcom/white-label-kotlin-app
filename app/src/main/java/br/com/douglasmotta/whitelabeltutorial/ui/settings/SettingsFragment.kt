package br.com.douglasmotta.whitelabeltutorial.ui.settings

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import br.com.douglasmotta.whitelabeltutorial.R
import br.com.douglasmotta.whitelabeltutorial.databinding.FragmentSettingsBinding
import br.com.douglasmotta.whitelabeltutorial.ui.login.LoginActivity
import br.com.douglasmotta.whitelabeltutorial.util.Result
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SettingsFragment : Fragment() {

    private var _binding: FragmentSettingsBinding? = null
    private val binding get() = _binding!!

    private val viewModel: SettingsViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSettingsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        observeVMEvents()
        setListeners()
    }

    private fun observeVMEvents() {
        viewModel.resultData.observe(viewLifecycleOwner) { result ->
            if (result is Result.Success) {
                val intent = Intent(context, LoginActivity::class.java)
                startActivity(intent)
            } else {
                Toast
                    .makeText(
                        activity,
                        getString(R.string.error_message_default),
                        Toast.LENGTH_SHORT
                    )
                    .show()
            }
        }
    }

    private fun setListeners() {

        binding.buttonLogout.setOnClickListener {
            viewModel.logout()
        }

    }

}