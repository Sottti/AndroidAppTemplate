package com.sottti.android.app.template.presentation.design.system.illustrations.data

import com.sottti.android.app.template.presentation.design.system.illustrations.R
import com.sottti.android.app.template.presentation.design.system.illustrations.model.IllustrationState

public object Illustrations {
    public object FamilyBeachSunset {
        private val description: Int = R.string.description_illustration_family_sunset_on_the_beach
        public val state: IllustrationState =
            IllustrationState(
                resId = R.drawable.il_family_sunset_on_the_beach,
                descriptionResId = description,
            )
    }

    public object AndroidRobotMatrix {
        private val description: Int = R.string.description_illustration_android_robot_matrix
        public val state: IllustrationState =
            IllustrationState(
                resId = R.drawable.il_android_robot_matrix,
                descriptionResId = description,
            )
    }
}
