package com.sottti.android.app.template.presentation.design.system.images.local.data

import com.sottti.android.app.template.presentation.design.system.images.R
import com.sottti.android.app.template.presentation.design.system.images.local.model.ImageState

public object Images {
    public object AndroidStatue {
        private val description: Int = R.string.description_image_android_statue
        public val state: ImageState =
            ImageState(
                resId = R.drawable.img_android_statue,
                descriptionResId = description,
            )
    }

    public object AvatarPlaceholder {
        private val description: Int = R.string.description_image_avatar_placeholder
        public val state: ImageState =
            ImageState(
                resId = R.drawable.img_avatar_placeholder,
                descriptionResId = description,
            )
    }
}
