package com.mmo4j.core.weather

import com.mmo4j.infrastructure.proto.WeatherProto.WeatherState
import com.mmo4j.infrastructure.proto.WeatherProto.WeatherType
import kotlin.random.Random

/**
 * Timers
 *  - ChangeWeatherInterval
 */
class Weather(
  private val zoneId: Int,
  private var weatherType: WeatherType,
  private var weatherGrade: Float,
  private var weatherZoneChances: WeatherZoneChances,
  private var permanentWeather: Boolean
) {
  fun sendWeatherUpdateToPlayer(player: Long) {

  }

  fun setWeather(weatherType: WeatherType, weatherGrade: Float, map: Int, permanentWeather: Boolean) {
    this.permanentWeather = permanentWeather

    if (this.weatherType == weatherType && this.weatherGrade == weatherGrade) {
      return
    }

    this.weatherType = weatherType
    this.weatherGrade = weatherGrade

    // Send event weather updated
    sendWeatherForPlayersInZone()
  }

  /**
   *
   */
  private fun sendWeatherForPlayersInZone() {
    normalizeWeatherGrade()
    val state = getWeatherState()

    // bus.publish(WeatherUpdated(zoneId = zoneId, weatherType = weatherType, weatherState = weatherState))
  }

  /**
   * Launch a weather update
   */
  fun update(delta: Int, map: Int) {
    // re-generate weather
    // fire weather update event to players in zone
  }

  /**
   * Calculate the new weather, returns true if and only if the weather changed
   */
  private fun reGenerate(): Boolean {
    if (permanentWeather) {
      return false
    }

    val oldWeatherType = weatherType
    val oldWeatherGrade = weatherGrade

    if (false) {
      weatherType = WeatherType.WT_FINE
      weatherGrade = 0.0f

      return oldWeatherType != weatherType || oldWeatherGrade != weatherGrade
    }

    // Weather statistics:
    //- 30% - no change
    //- 30% - weather gets better (if not fine) or change weather type
    //- 30% - weather worsens (if not fine)
    //- 10% - radical change (if not fine)
    val u = Random.nextInt(0, 99)

    if (u < 30) {
      return false
    }

    // 78 days between January 1st and March 20nd; 365/4=91 days by season
    // season source http://aa.usno.navy.mil/data/docs/EarthSeasons.html
    // val gameTime = world.gameTime
    // val localtime = Instant.now()
    // val season = ((localTime->tm_yday - 78 + 365 / 91) %4))

    return false
  }

  fun getWeatherState(): WeatherState {
    if (weatherGrade < 0.27f) {
      return WeatherState.WS_FINE
    }

    return when (weatherType) {
      WeatherType.WT_RAIN -> when {
        weatherGrade < 0.4f -> WeatherState.WS_LIGHT_RAIN
        weatherGrade < 0.7f -> WeatherState.WS_MEDIUM_RAIN
        else -> WeatherState.WS_HEAVY_RAIN
      }

      WeatherType.WT_SNOW -> when {
        weatherGrade < 0.4f -> WeatherState.WS_LIGHT_SNOW
        weatherGrade < 0.7f -> WeatherState.WS_MEDIUM_SNOW
        else -> WeatherState.WS_HEAVY_SNOW
      }

      WeatherType.WT_SANDSTORM -> when {
        weatherGrade < 0.4f -> WeatherState.WS_LIGHT_SANDSTORM
        weatherGrade < 0.7f -> WeatherState.WS_MEDIUM_SANDSTORM
        else -> WeatherState.WS_HEAVY_SANDSTORM
      }

      WeatherType.WT_BLACK_RAIN -> WeatherState.WS_BLACK_RAIN
      WeatherType.WT_THUNDERS -> WeatherState.WS_THUNDERS

      else -> WeatherState.WS_FINE
    }
  }

  private fun normalizeWeatherGrade() {
    weatherGrade = if (weatherGrade >= 1) 0.9999f else 0.0001f
  }

  /**
   * TODO - not needed
   */
  fun isValidWeatherType(type: WeatherType): Boolean = when (type) {
    WeatherType.WT_FINE -> true
    WeatherType.WT_RAIN -> true
    WeatherType.WT_SNOW -> true
    WeatherType.WT_SANDSTORM -> true
    WeatherType.WT_THUNDERS -> true
    WeatherType.WT_BLACK_RAIN -> true
    else -> false
  }
}
