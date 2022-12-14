package br.com.douglasmotta.whitelabeltutorial.ui.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import br.com.douglasmotta.whitelabeltutorial.R
import br.com.douglasmotta.whitelabeltutorial.databinding.FragmentHomeBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment() {

    private val viewModel: HomeViewModel by viewModels()

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.getUserEmail()
        observeVMEvents()
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }

    private fun displayName(name: String) {
        with(binding) {
            mainTitle.text = getString(R.string.welcome_message).replace(":name", name)
        }
    }

    private fun observeVMEvents() {
        viewModel.errorMessageData.observe(viewLifecycleOwner) { resId ->
            resId?.let {
                Toast.makeText(activity, getString(resId), Toast.LENGTH_SHORT).show()
            }
        }

        viewModel.userInformationData.observe(viewLifecycleOwner) { name ->
            displayName(name)
        }
    }

}