package com.mmo4j.core.world

import com.mmo4j.core.world.WorldConfig.RateConfig
import com.mmo4j.core.world.WorldConfig.RateConfig.CreatureConfig
import com.mmo4j.core.world.WorldConfig.RateConfig.CreatureConfig.EliteConfig
import com.mmo4j.core.world.WorldConfig.RateConfig.CreatureConfig.EliteConfig.RareConfig
import com.mmo4j.core.world.WorldConfig.RateConfig.CreatureConfig.EliteConfig.RareEliteConfig
import com.mmo4j.core.world.WorldConfig.RateConfig.CreatureConfig.EliteConfig.WorldBossConfig
import com.mmo4j.core.world.WorldConfig.RateConfig.CreatureConfig.NormalConfig
import com.mmo4j.core.world.WorldConfig.RateConfig.DropConfig
import com.mmo4j.core.world.WorldConfig.RateConfig.DropConfig.ItemConfig
import com.mmo4j.core.world.WorldConfig.RateConfig.ExperienceConfig
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
  fun createWorld(config: Config, rate: RateConfig): WorldConfig =
    with(config.getConfig("mmo4j.world")) {
      WorldConfig(
        playerLimit = getInt("player-limit"),
        rate = rate
      )
    }

  @Bean
  @Singleton
  fun createWorldRate(
    config: Config,
    rage: RageConfig,
    runicPower: RunicPowerConfig,
    skill: SkillConfig,
    drop: DropConfig,
    experience: ExperienceConfig,
    reputation: ReputationConfig,
    creature: CreatureConfig,
    rest: RestConfig
  ) = with(config.getConfig("mmo4j.world.rate")) {
    RateConfig(
      health = getDouble("health"),
      mana = getDouble("mana"),
      focus = getDouble("focus"),
      energy = getDouble("energy"),
      petXpKill = getDouble("pet-xp-kill"),
      rage = rage,
      runicPower = runicPower,
      skill = skill,
      drop = drop,
      experience = experience,
      reputation = reputation,
      creature = creature,
      rest = rest
    )
  }

  @Bean
  @Singleton
  fun createRage(config: Config) =
    with(config.getConfig("mmo4j.world.rate.rage")) {
      RageConfig(income = getDouble("income"), loss = getDouble("loss"))
    }

  @Bean
  @Singleton
  fun createRunicPower(config: Config) =
    with(config.getConfig("mmo4j.world.rate.runic-power")) {
      RunicPowerConfig(income = getDouble("income"), loss = getDouble("loss"))
    }

  @Bean
  @Singleton
  fun createSkill(config: Config) =
    with(config.getConfig("mmo4j.world.rate.skill")) {
      SkillConfig(discovery = getDouble("discovery"))
    }

  @Bean
  @Singleton
  fun createWorldRateDrop(config: Config) =
    with(config.getConfig("mmo4j.world.rate.drop")) {
      DropConfig(
        money = getDouble("money"),
        currency = getDouble("currency"),
        currencyAmount = getDouble("currency-amount"),
        item = createWorldRateDropItem(config)
      )
    }

  @Bean
  @Singleton
  fun createWorldRateDropItem(config: Config) =
    with(config.getConfig("mmo4j.world.rate.drop.item")) {
      ItemConfig(
        poor = getDouble("poor"),
        normal = getDouble("normal"),
        uncommon = getDouble("uncommon"),
        rare = getDouble("rare"),
        epic = getDouble("epic"),
        legendary = getDouble("legendary"),
        artifact = getDouble("artifact"),
        referenced = getDouble("referenced"),
        quest = getDouble("quest")
      )
    }

  @Bean
  @Singleton
  fun createWorldRateXp(config: Config) =
    with(config.getConfig("mmo4j.world.rate.xp")) {
      ExperienceConfig(kill = getDouble("kill"), quest = getDouble("quest"), explore = getDouble("explore"))
    }

  @Bean
  @Singleton
  fun createWorldRateReputation(config: Config, lowLevel: LowLevelConfig) =
    with(config.getConfig("mmo4j.world.rate.reputation")) {
      ReputationConfig(gain = getDouble("gain"), lowLevel = lowLevel)
    }

  @Bean
  @Singleton
  fun createWorldRateReputationLowLevel(config: Config) =
    with(config.getConfig("mmo4j.world.rate.reputation.low-level")) {
      LowLevelConfig(kill = getDouble("kill"), quest = getDouble("quest"))
    }

  @Bean
  @Singleton
  fun createWorldRateCreature(config: Config, normal: NormalConfig, elite: EliteConfig) =
    with(config.getConfig("mmo4j.world.rate.creature")) {
      CreatureConfig(aggro = getDouble("aggro"), normal = normal, elite = elite)
    }

  @Bean
  @Singleton
  fun createWorldRateCreatureNormal(config: Config) =
    with(config.getConfig("mmo4j.world.rate.creature.normal")) {
      NormalConfig(hp = getDouble("hp"), damage = getDouble("damage"), spellDamage = getDouble("spell-damage"))
    }

  @Bean
  @Singleton
  fun createWorldRateCreatureElite(
    elite: EliteConfig.EliteConfig,
    rareElite: RareEliteConfig,
    rare: RareConfig,
    worldBoss: WorldBossConfig
  ) = EliteConfig(elite = elite, rareElite = rareElite, rare = rare, worldBoss = worldBoss)

  @Bean
  @Singleton
  fun createWorldRateCreatureEliteElite(config: Config) =
    with(config.getConfig("mmo4j.world.rate.creature.elite.elite")) {
      EliteConfig.EliteConfig(hp = getDouble("hp"), damage = getDouble("damage"), spellDamage = getDouble("spell-damage"))
    }

  @Bean
  @Singleton
  fun createWorldRateCreatureEliteRareElite(config: Config) =
    with(config.getConfig("mmo4j.world.rate.creature.elite.rare-elite")) {
      RareEliteConfig(hp = getDouble("hp"), damage = getDouble("damage"), spellDamage = getDouble("spell-damage"))
    }

  @Bean
  @Singleton
  fun createWorldRateCreatureEliteRare(config: Config) =
    with(config.getConfig("mmo4j.world.rate.creature.elite.rare")) {
      RareConfig(hp = getDouble("hp"), damage = getDouble("damage"), spellDamage = getDouble("spell-damage"))
    }

  @Bean
  @Singleton
  fun createWorldRateCreatureEliteWorldBoss(config: Config) =
    with(config.getConfig("mmo4j.world.rate.creature.elite.world-boss")) {
      WorldBossConfig(hp = getDouble("hp"), damage = getDouble("damage"), spellDamage = getDouble("spell-damage"))
    }

  @Bean
  @Singleton
  fun createWorldRateRest(config: Config, offline: OfflineConfig) =
    with(config.getConfig("mmo4j.world.rate.rest.offline")) {
      RestConfig(inGame = getDouble("in-game"), offline = offline)
    }

  @Bean
  @Singleton
  fun createWorldRateRestOffline(config: Config) =
    with(config.getConfig("mmo4j.world.rate.rest.offline")) {
      OfflineConfig(inTavernOrCity = getDouble("tavern-or-city"), inWilderness = getDouble("wilderness"))
    }
}
