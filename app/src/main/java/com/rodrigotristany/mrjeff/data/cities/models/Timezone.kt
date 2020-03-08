package com.rodrigotristany.mrjeff.data.cities.models

data class Timezone(
    val dstOffset: Int,
    val gmtOffset: Int,
    val timeZoneId: String
)