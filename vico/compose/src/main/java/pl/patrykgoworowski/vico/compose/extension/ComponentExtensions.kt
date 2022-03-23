/*
 * Copyright (c) 2021. Patryk Goworowski
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

@file:Suppress("ComposableNaming")

package pl.patrykgoworowski.vico.compose.extension

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import pl.patrykgoworowski.vico.core.DEF_SHADOW_COLOR
import pl.patrykgoworowski.vico.core.component.marker.MarkerComponent
import pl.patrykgoworowski.vico.core.component.shape.ShapeComponent

/**
 * The indicator size.
 */
public var MarkerComponent.indicatorSize: Dp
    get() = indicatorSizeDp.dp
    set(value) { indicatorSizeDp = value.value }

/**
 * Applies a drop shadow to this [ShapeComponent].
 * @param radius the blur radius.
 * @param dx the horizontal offset.
 * @param dy the vertical offset.
 * @param color the shadow color.
 * @param applyElevationOverlay whether to apply an elevation overlay to the [ShapeComponent].
 */
public fun ShapeComponent.setShadow(
    radius: Dp,
    dx: Dp = 0.dp,
    dy: Dp = 0.dp,
    color: Color = Color(DEF_SHADOW_COLOR),
    applyElevationOverlay: Boolean = false,
): ShapeComponent = setShadow(
    radius = radius.value,
    dx = dx.value,
    dy = dy.value,
    color = color.toArgb(),
    applyElevationOverlay = applyElevationOverlay,
)
