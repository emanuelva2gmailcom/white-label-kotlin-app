package br.com.douglasmotta.whitelabeltutorial.ui.products.addproduct

import android.net.Uri
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.contract.ActivityResultContracts
import br.com.douglasmotta.whitelabeltutorial.R
import br.com.douglasmotta.whitelabeltutorial.databinding.FragmentAddProductBinding
import br.com.douglasmotta.whitelabeltutorial.util.CurrencyTextWatcher
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.google.android.material.textfield.TextInputLayout

class AddProductFragment : BottomSheetDialogFragment() {

    private var _binding: FragmentAddProductBinding? = null
    private val binding get() = _binding!!

    private var imageuri: Uri? = null

    private val getContent =
        registerForActivityResult(ActivityResultContracts.GetContent()) { uri ->
            imageuri = uri
            binding.imageProduct.setImageURI(uri)
        }

    private lateinit var viewModel: AddProductViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentAddProductBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        observeVMEvents()
        setListeners()
    }

    private fun observeVMEvents() {
        viewModel.imageUriErrorResId.observe(viewLifecycleOwner) { drawableResId ->
            binding.imageProduct.setBackgroundColor(drawableResId)
        }

        viewModel.descriptionFieldErrorResId.observe(viewLifecycleOwner) { stringResId ->
            binding.inputLayoutDescription.setError(stringResId)
        }

        viewModel.priceFieldErrorResId.observe(viewLifecycleOwner) { stringResId ->
            binding.inputLayoutPrice.setError(stringResId)
        }
    }

    private fun TextInputLayout.setError(stringResId: Int?) {
        error = if (stringResId != null) {
            getString(stringResId)
        } else null
    }

    private fun setListeners() {
        binding.imageProduct.setOnClickListener {
            chooseImage()
        }

        binding.buttonAddProduct.setOnClickListener {
            val description = binding.inputDescription.text.toString()
            val price = binding.inputPrice.text.toString()

            viewModel.createProduct(description, price, imageuri!!)
        }

        binding.inputPrice.run {
            addTextChangedListener(CurrencyTextWatcher(this))
        }
    }

    private fun chooseImage() {
        getContent.launch("image/*")
    }
}