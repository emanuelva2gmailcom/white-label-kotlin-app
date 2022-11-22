package br.com.douglasmotta.whitelabeltutorial.ui.auth.signUp

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import br.com.douglasmotta.whitelabeltutorial.R
import br.com.douglasmotta.whitelabeltutorial.databinding.FragmentSignUpBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SignUpFragment : Fragment() {

    private var _binding: FragmentSignUpBinding? = null
    private val binding get() = _binding!!

    private val viewModel: SignUpViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSignUpBinding.inflate(inflater, container, false)
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

        viewModel.status.observe(viewLifecycleOwner) {
            if (it)
                findNavController().navigate(R.id.action_signUpFragment_to_SignIn)
        }

        viewModel.msg.observe(viewLifecycleOwner) {
            if (!it.isNullOrBlank())
                Toast.makeText(requireContext(), it, Toast.LENGTH_LONG).show()
        }
    }

    private fun setListeners() {

        with(binding) {
            buttonSignUp.setOnClickListener {
                val email = inputEmail.text.toString()
                val password = inputPassword.text.toString()

                viewModel.signUp(email, password)
            }

            signInLink.setOnClickListener {
                findNavController().navigate(R.id.action_signUpFragment_to_SignIn)
            }
        }
    }
}