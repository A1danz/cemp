package component

import com.cemp.SharedRes
import com.cemp.feature.auth.domain.model.error.InvalidField
import dev.icerock.moko.resources.desc.StringDesc
import dev.icerock.moko.resources.format

interface BaseAuthComponent {
    fun getUiTextForInvalidField(invalidField: InvalidField): StringDesc {
        return when (invalidField) {
            is InvalidField.LengthRangeRule -> SharedRes.strings.feature_auth_range_len_error.format(
                invalidField.range.first,
                invalidField.range.last
            )

            is InvalidField.MaskRule -> SharedRes.strings.feature_auth_mask_error.format(
                invalidField.rightFormat
            )

            is InvalidField.MaxLengthRule -> SharedRes.strings.feature_auth_max_len_error.format(
                invalidField.max
            )

            is InvalidField.MinLengthRule -> SharedRes.strings.feature_auth_max_len_error.format(
                invalidField.min
            )

        }
    }
}