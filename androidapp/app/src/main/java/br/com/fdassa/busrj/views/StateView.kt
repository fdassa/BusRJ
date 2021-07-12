package br.com.fdassa.busrj.views

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import br.com.fdassa.busrj.R
import br.com.fdassa.busrj.databinding.ViewStateBinding
import br.com.fdassa.busrj.utils.hide
import br.com.fdassa.busrj.utils.show

class StateView : ConstraintLayout {
    private val binding: ViewStateBinding = ViewStateBinding.inflate(
        LayoutInflater.from(context), this, true
    )
    var onButtonClick: () -> Unit = {}

    constructor(context: Context) : this(context, null)

    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 0)

    constructor(
        context: Context, attrs: AttributeSet?, defStyleAttr: Int
    ) : super(context, attrs, defStyleAttr) {
        init()
    }

    private fun init() {
        binding.btTryAgain.setOnClickListener {
            onButtonClick.invoke()
        }
    }

    fun showLoading() {
        binding.progressBar.show()
        binding.groupErrorState.hide()
        binding.btTryAgain.hide()
    }

    fun showError() {
        binding.groupErrorState.show()
        binding.progressBar.hide()
        binding.ivIcon.setImageResource(R.drawable.ic_error)
        binding.tvTitle.setText(R.string.error_title)
        binding.tvDescription.setText(R.string.error_description)
        binding.btTryAgain.show()
    }

    fun showEmptyState() {
        binding.groupErrorState.show()
        binding.progressBar.hide()
        binding.ivIcon.setImageResource(R.drawable.ic_search)
        binding.tvTitle.setText(R.string.empty_title)
        binding.tvDescription.setText(R.string.empty_description)
        binding.btTryAgain.hide()
    }
}