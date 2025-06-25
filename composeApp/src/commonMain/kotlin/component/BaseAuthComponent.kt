package component

import com.cemp.feature.auth.domain.model.error.InvalidField

interface BaseAuthComponent {
    fun getUiTextForInvalidField(invalidField: InvalidField): String {
        return when(invalidField) {
//            is InvalidField.LengthRangeRule -> resourceManager.getString(R.string.field_interval_error, invalidField.range.first, invalidField.range.last)
//            is InvalidField.MaskRule -> resourceManager.getString(R.string.field_mask_error, invalidField.rightFormat)
//            is InvalidField.MaxLengthRule -> resourceManager.getString(R.string.field_max_length_error, invalidField.max)
//            is InvalidField.MinLengthRule -> resourceManager.getString(R.string.field_min_length_error, invalidField.min)

            is InvalidField.LengthRangeRule -> "Длина поля должна быть от ${invalidField.range.first} до ${invalidField.range.last}"
            is InvalidField.MaskRule -> "Поле должно соответсвовать формату: ${invalidField.rightFormat}"
            is InvalidField.MaxLengthRule -> "Максимальная длина поля - ${invalidField.max}"
            is InvalidField.MinLengthRule -> "Минимальная длина поля - ${invalidField.min}"

        }
    }
}