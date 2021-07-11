package br.com.fdassa.busrj.views

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
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
        binding.stateViewContainer.show()
        binding.progressBar.show()
        binding.groupErrorState.hide()
    }

    fun showError() {
        binding.stateViewContainer.show()
        binding.groupErrorState.show()
        binding.progressBar.hide()
    }
}