package com.scarry.makakumbha.ui.theme

import androidx.compose.foundation.shape.CornerBasedShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Shapes
import androidx.compose.runtime.Immutable
import androidx.compose.ui.unit.dp

@Immutable
class Shapes (

    val small: CornerBasedShape = RoundedCornerShape(4.dp),
    val medium: CornerBasedShape = RoundedCornerShape(4.dp),
    val large: CornerBasedShape = RoundedCornerShape(0.dp)

){
    fun copy(
        small: CornerBasedShape = this.small,
        medium: CornerBasedShape = this.small,
        large: CornerBasedShape = this.small,
    ): Shapes {
        return Shapes(small, medium, large)
    }
}
