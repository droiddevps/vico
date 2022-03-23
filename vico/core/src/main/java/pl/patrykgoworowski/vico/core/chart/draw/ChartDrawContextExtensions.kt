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

package pl.patrykgoworowski.vico.core.chart.draw

import android.graphics.Canvas
import android.graphics.RectF
import pl.patrykgoworowski.vico.core.annotation.LongParameterListDrawFunction
import pl.patrykgoworowski.vico.core.axis.model.ChartModel
import pl.patrykgoworowski.vico.core.chart.segment.SegmentProperties
import pl.patrykgoworowski.vico.core.context.DrawContext
import pl.patrykgoworowski.vico.core.context.Extras
import pl.patrykgoworowski.vico.core.context.MeasureContext
import pl.patrykgoworowski.vico.core.model.Point

private const val DEFAULT_SCALE = 1f

/**
 * The anonymous implementation of [ChartDrawContext].
 *
 * @param canvas the canvas to draw the chart on.
 * @param elevationOverlayColor the color of elevation overlay, applied to components that cast shadows in
 * [pl.patrykgoworowski.vico.core.component.shape.ShapeComponent].
 * @param measureContext the measuring context that holds the data used for component measurements.
 * @param markerTouchPoint the point inside the chart’s coordinates where physical touch is occurring.
 * @param segmentProperties holds information about the width of each individual segment on the x-axis.
 * @param chartModel the model used by the chart. This holds information about the values on both the y-axis and the
 * x-axis.
 * @param chartBounds the bounds in which the [pl.patrykgoworowski.vico.core.chart.Chart] will be drawn.
 *
 * @see [pl.patrykgoworowski.vico.core.component.shape.ShapeComponent.setShadow]
 */
@LongParameterListDrawFunction
public fun chartDrawContext(
    canvas: Canvas,
    elevationOverlayColor: Long,
    measureContext: MeasureContext,
    markerTouchPoint: Point?,
    segmentProperties: SegmentProperties,
    chartModel: ChartModel,
    chartBounds: RectF,
): ChartDrawContext = object : ChartDrawContext, Extras by measureContext {
    override val canvasBounds: RectF = measureContext.canvasBounds
    override val chartBounds: RectF = chartBounds
    override var canvas: Canvas = canvas
    override val elevationOverlayColor: Long = elevationOverlayColor
    override val chartModel: ChartModel = chartModel
    override val markerTouchPoint: Point? = markerTouchPoint
    override val horizontalScroll: Float = measureContext.horizontalScroll
    override val density: Float = measureContext.density
    override val fontScale: Float = measureContext.fontScale
    override val isLtr: Boolean = measureContext.isLtr
    override val isHorizontalScrollEnabled: Boolean = measureContext.isHorizontalScrollEnabled
    override val chartScale: Float = calculateDrawScale()
    override val segmentProperties: SegmentProperties = segmentProperties.scaled(chartScale)

    override fun withOtherCanvas(canvas: Canvas, block: (DrawContext) -> Unit) {
        val originalCanvas = this.canvas
        this.canvas = canvas
        block(this)
        this.canvas = originalCanvas
    }

    private fun calculateDrawScale(): Float =
        if (isHorizontalScrollEnabled) {
            measureContext.chartScale
        } else {
            (chartBounds.width() / (segmentProperties.segmentWidth * chartModel.chartEntryModel.getDrawnEntryCount()))
                .coerceAtMost(DEFAULT_SCALE)
        }
}
