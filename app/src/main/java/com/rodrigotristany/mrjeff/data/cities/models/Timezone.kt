package com.rodrigotristany.mrjeff.data.cities.models

data class Timezone(
    val dstOffset: Float,
    val gmtOffset: Float,
    val timeZoneId: String
)