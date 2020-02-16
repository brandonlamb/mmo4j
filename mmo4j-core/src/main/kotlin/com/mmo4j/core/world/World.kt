package com.mmo4j.core.world

import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class World @Inject constructor(private val worldConfig: WorldConfig) {
  init {
    worldConfig.rate.drop.currency
  }
}
