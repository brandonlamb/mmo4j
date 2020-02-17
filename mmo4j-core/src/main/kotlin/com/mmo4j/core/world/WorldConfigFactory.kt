package com.mmo4j.core.world

import com.mmo4j.core.world.WorldConfig.AuctionConfig
import com.mmo4j.core.world.WorldConfig.RateConfig
import com.mmo4j.core.world.WorldConfig.RateConfig.CreatureConfig
import com.mmo4j.core.world.WorldConfig.RateConfig.CreatureConfig.EliteConfig
import com.mmo4j.core.world.WorldConfig.RateConfig.CreatureConfig.EliteConfig.RareConfig
import com.mmo4j.core.world.WorldConfig.RateConfig.CreatureConfig.EliteConfig.RareEliteConfig
import com.mmo4j.core.world.WorldConfig.RateConfig.CreatureConfig.EliteConfig.WorldBossConfig
import com.mmo4j.core.world.WorldConfig.RateConfig.CreatureConfig.NormalConfig
import com.mmo4j.core.world.WorldConfig.RateConfig.DamageConfig
import com.mmo4j.core.world.WorldConfig.RateConfig.DropConfig
import com.mmo4j.core.world.WorldConfig.RateConfig.DropConfig.ItemConfig
import com.mmo4j.core.world.WorldConfig.RateConfig.ExperienceConfig
import com.mmo4j.core.world.WorldConfig.RateConfig.MiningConfig
import com.mmo4j.core.world.WorldConfig.RateConfig.RageConfig
import com.mmo4j.core.world.WorldConfig.RateConfig.ReputationConfig
import com.mmo4j.core.world.WorldConfig.RateConfig.ReputationConfig.LowLevelConfig
import com.mmo4j.core.world.WorldConfig.RateConfig.RestConfig
import com.mmo4j.core.world.WorldConfig.RateConfig.RestConfig.OfflineConfig
import com.mmo4j.core.world.WorldConfig.RateConfig.RunicPowerConfig
import com.mmo4j.core.world.WorldConfig.RateConfig.SkillConfig
import com.typesafe.config.Config
import io.micronaut.context.annotation.Bean
import io.micronaut.context.annotation.Factory
import javax.inject.Singleton

@Factory
class WorldConfigFactory {
  @Bean
  @Singleton
  fun createWorld(
    config: Config,
    rate: RateConfig,
    auction: AuctionConfig
  ): WorldConfig =
    with(config.getConfig("mmo4j.world")) {
      WorldConfig(
        playerLimit = getInt("player-limit"),
        rate = rate,
        auction = auction
      )
    }
}
