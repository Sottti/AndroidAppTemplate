package com.sottti.android.app.template.presentation.design.system.icons.data

import com.sottti.android.app.template.presentation.design.system.icon.resources.IconResourcesDrawables
import com.sottti.android.app.template.presentation.design.system.icons.R
import com.sottti.android.app.template.presentation.design.system.icons.model.IconState

public object Icons {


    public object Arrow {
        public object Back {
            private val description: Int = R.string.description_icon_arrow_back
            public val filled: IconState = IconState(
                resId = IconResourcesDrawables.ic_arrow_back_rounded_filled,
                descriptionResId = description,
            )
        }
    }
}
