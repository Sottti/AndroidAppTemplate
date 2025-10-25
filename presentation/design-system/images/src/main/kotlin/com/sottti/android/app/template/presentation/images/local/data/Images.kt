package com.sottti.android.app.template.presentation.images.local.data

import com.sottti.android.app.template.presentation.images.R
import com.sottti.android.app.template.presentation.images.local.model.ImageState

public object Images {
    public object AndroidStatue {
        private val description: Int = R.string.description_image_android_statue
        public val state: ImageState =
            ImageState(
                resId = R.drawable.img_android_statue,
                descriptionResId = description,
            )
    }
}
