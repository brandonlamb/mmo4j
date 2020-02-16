package com.mmo4j.core.realm

import com.mmo4j.infrastructure.proto.realm.RealmProto.BuildInfo
import com.mmo4j.infrastructure.proto.realm.RealmProto.Realm
import com.typesafe.config.Config
import java.time.Instant
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RealmList @Inject constructor(private val config: Config) {
  private var realmsMap: RealmMap = emptyMap()
  private var updateInterval: Int = 0
  private var nextUpdateTime: Instant = Instant.now()

  private val expectedRealmdClientBuilds = setOf(
    BuildInfo.Builder().buildVersion(1000).majorVersion(1).minorVersion(0).bugfixVersion(0).hotfixVersion(0).build(),
    BuildInfo.Builder().buildVersion(900).majorVersion(0).minorVersion(2).bugfixVersion(5).hotfixVersion(0).build(),
    BuildInfo.Builder().buildVersion(800).majorVersion(0).minorVersion(1).bugfixVersion(50).hotfixVersion(0).build()
  )

  fun updateIfNeeded() {
    if (updateInterval == 0 || nextUpdateTime.isAfter(Instant.now())) {
      return
    }

    updateRealms()
  }

  fun updateRealms() {
    // Call repository to fetch latest
    realmsMap = mutableMapOf()
  }

  /**
   * Load the realm list from the database
   */
  fun initialize(updateInterval: Int) {
    this.updateInterval = updateInterval

    // Get the content of the realm_list table in the database
    updateRealms()
  }

  fun size(): Int = realmsMap.size

  fun findBuildInfo(buildVersion: Int): BuildInfo? {
    // first build is low bound of always accepted range
    if (buildVersion >= expectedRealmdClientBuilds.first().buildVersion) {
      return expectedRealmdClientBuilds.first()
    }

    // continue from 1 with explicit equal check
    return expectedRealmdClientBuilds.firstOrNull { it.buildVersion == buildVersion }
  }
}
