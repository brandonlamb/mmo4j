package com.mmo4j.core.world

import com.mmo4j.core.config.WorldConfig
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class World @Inject constructor(
  private val worldConfig: WorldConfig,
  private var motd: String
) {
  init {

  }
}
