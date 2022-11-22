package br.com.douglasmotta.whitelabeltutorial.ui.auth.signIn

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import br.com.douglasmotta.whitelabeltutorial.R
import br.com.douglasmotta.whitelabeltutorial.databinding.FragmentSignInBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SignInFragment : Fragment() {

    private var _binding: FragmentSignInBinding? = null
    private val binding get() = _binding!!

    private val viewModel: SignInViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSignInBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        observeVMEvents()
        setListeners()
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }

    private fun observeVMEvents() {
        viewModel.msg.observe(viewLifecycleOwner) {
            if (!it.isNullOrBlank())
                Toast.makeText(requireContext(), it, Toast.LENGTH_LONG).show()
        }

        viewModel.status.observe(viewLifecycleOwner) {
            if (it)
                findNavController().navigate(R.id.action_SignInFragment_to_HomeFragment)
        }
    }

    private fun setListeners() {

        with(binding) {
            buttonSignUp.setOnClickListener {
                val email = inputEmail.text.toString()
                val password = inputPassword.text.toString()

                viewModel.signIn(email, password)
            }

            signUpLink.setOnClickListener {
                findNavController().navigate(R.id.action_SignInFragment_to_SignUpFragment)
            }
        }
    }

}