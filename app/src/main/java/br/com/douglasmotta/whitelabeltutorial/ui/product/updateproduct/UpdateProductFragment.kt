package br.com.douglasmotta.whitelabeltutorial.ui.product.updateproduct

import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import br.com.douglasmotta.whitelabeltutorial.databinding.FragmentUpdateProductBinding
import br.com.douglasmotta.whitelabeltutorial.domain.model.Product
import br.com.douglasmotta.whitelabeltutorial.util.CurrencyTextWatcher
import br.com.douglasmotta.whitelabeltutorial.util.toCurrency
import com.bumptech.glide.Glide
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.google.android.material.textfield.TextInputLayout
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class UpdateProductFragment : BottomSheetDialogFragment() {

    private var _binding: FragmentUpdateProductBinding? = null
    private val binding get() = _binding!!

    private var imageuri: Uri? = null

    private val productArgs: UpdateProductFragmentArgs by navArgs()
    private var productActive: Product? = null

    private val getContent =
        registerForActivityResult(ActivityResultContracts.GetContent()) { uri ->
            imageuri = uri
            binding.imageProduct.setImageURI(uri)
        }

    private val viewModel: UpdateProductViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentUpdateProductBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        productActive = productArgs.productDataArgs
        setValues(productActive!!)
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

        viewModel.productUpdateErrorResId.observe(viewLifecycleOwner) { resId ->
            resId?.let {
                Toast.makeText(activity, getString(resId), Toast.LENGTH_SHORT).show()
            }
        }

        viewModel.productUpdated.observe(viewLifecycleOwner) { product ->
            findNavController().run {
                previousBackStackEntry?.savedStateHandle?.set("refresh", product)
                popBackStack()
            }
        }
    }

    private fun setValues(product: Product) {
        binding.run {
            Glide.with(requireView())
                .load(product.imageUrl)
                .fitCenter()
                .into(imageProduct)

            inputDescription.setText(product.description)
            inputPrice.setText(product.price.toCurrency())
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

        binding.buttonUpdateProduct.setOnClickListener {
            val description = binding.inputDescription.text.toString()
            val price = binding.inputPrice.text.toString()
            viewModel.updateProduct(productActive!!.id, description, price, imageuri)
        }

        binding.inputPrice.run {
            addTextChangedListener(CurrencyTextWatcher(this))
        }
    }

    private fun chooseImage() {
        getContent.launch("image/*")
    }
}