package com.cemp.feature.auth.domain.model.error

sealed interface InvalidField {
    class MaxLengthRule(val max: Int) : InvalidField
    class MinLengthRule(val min: Int) : InvalidField
    class LengthRangeRule(val range: IntRange) : InvalidField
    class MaskRule(val rightFormat: String) : InvalidField
}