package br.com.fdassa.busrj.views

import android.content.Context
import android.content.res.TypedArray
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import br.com.fdassa.busrj.R
import br.com.fdassa.busrj.databinding.ViewMenuItemBinding

@Suppress("JoinDeclarationAndAssignment")
class MenuItemView : ConstraintLayout {
    private val binding: ViewMenuItemBinding = ViewMenuItemBinding.inflate(
        LayoutInflater.from(context), this, true
    )

    private val attributes: TypedArray

    constructor(context: Context) : this(context, null)

    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 0)

    constructor(
        context: Context, attrs: AttributeSet?, defStyleAttr: Int
    ) : super(context, attrs, defStyleAttr) {
        attributes = context.theme.obtainStyledAttributes(
            attrs, R.styleable.menu_item_view, 0 , 0
        )
        init()
    }

    private fun init() = with(attributes) {
        binding.ivIcon.setImageResource(
            getResourceId(R.styleable.menu_item_view_menu_icon, R.drawable.ic_search)
        )
        binding.tvTitle.text = getString(R.styleable.menu_item_view_menu_title)
        binding.tvDescription.text = getString(R.styleable.menu_item_view_menu_description)
    }
}