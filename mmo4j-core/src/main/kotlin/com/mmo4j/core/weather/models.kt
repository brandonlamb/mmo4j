package com.mmo4j.core.weather

data class WeatherSeasonChances(val rain: Int, val snow: Int, val storm: Int)
data class WeatherZoneChances(val data:WeatherSeasonChances)
