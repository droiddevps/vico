/*
 * Copyright 2023 by Patryk Goworowski and Patrick Michalik.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.patrykandpatrick.vico.core.chart.layout

import com.patrykandpatrick.vico.core.axis.horizontal.HorizontalAxis
import com.patrykandpatrick.vico.core.chart.Chart

/**
 * Defines how a chart’s content is positioned horizontally. This affects the [Chart] and the [HorizontalAxis]
 * instances.
 */
public sealed interface HorizontalLayout {
    /**
     * When this is applied, the [Chart] centers each major entry in a designated segment. Some empty space is visible
     * at the start and end of the [Chart]. [HorizontalAxis] instances display ticks and guidelines at the edges of the
     * segments.
     */
    public object Segmented : HorizontalLayout {
        /**
         * Returns an instance of [HorizontalLayout.Segmented].
         */
        @Deprecated(
            """`HorizontalLayout.Segmented` is now an object. Replace `HorizontalLayout.Segmented()` with
                `HorizontalLayout.Segmented`.""",
        )
        public operator fun invoke(): Segmented = this
    }

    /**
     * When this is applied, the [Chart]’s content takes up the [Chart]’s entire width (unless padding is added).
     * [HorizontalAxis] instances display a tick and a guideline for each label, with the tick, guideline, and label
     * vertically centered relative to one another. [startPaddingDp] and [endPaddingDp] control the amount of empty
     * space at the start and end of the [Chart], respectively.
     */
    public class FullWidth(public val startPaddingDp: Float = 0f, public val endPaddingDp: Float = 0f) :
        HorizontalLayout

    public companion object
}
