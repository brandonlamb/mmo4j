package com.mmo4j.core.config

import com.typesafe.config.Config
import io.micronaut.context.annotation.Bean
import io.micronaut.context.annotation.Factory
import javax.inject.Singleton

/**
 * Implementing a factory over the creation of the WorldConfig class helps abstract out using whatever configuration
 * library of choice. In this case, we're using TypeSafe Config, but this could just as easily(is) be swapped for Micronaut
 */
@Factory
class WorldConfigFactory {
  @Bean
  @Singleton
  fun createWorld(config: Config): WorldConfig = with(config.getConfig("mmo4j.world")) {
// getDouble("").toFloat(),
    WorldConfig(
      addonChannel = getBoolean("addon-channel"),
      artifactItemDropRate = getDouble("item-drop-rate.artifact").toFloat(),
      auctionCutRate = getDouble("auction.cut-rate").toFloat(),
      auctionDepositMin = getInt("auction.deposit-min"),
      auctionDepositRate = getDouble("auction.deposit-rate").toFloat(),
      auctionTimeRate = getDouble("auction.time-rate").toFloat(),
      changeWeatherInterval = getDuration("change-weather-interval"),
      cleanCharacterDb = getBoolean("clean-character-db"),
      corpseDecayLootedRate = getDouble("corpse-decay-looted-rate").toFloat(),
      creatureAggroRate = getDouble("creature.aggro-rate").toFloat(),
      creatureFamilyAssistanceRadius = getDouble("creature-family.assistance-radius").toFloat(),
      creatureFamilyFleeAssistanceRadius = getDouble("creature-family.flee-assistance-radius").toFloat(),
      currencyAmountDropRate = getDouble("currency-amount-drop-rate").toFloat(),
      currencyDropRate = getDouble("currency-drop-rate").toFloat(),
      durabilityLossChanceAbsorb = getDouble("durability-loss-chance.absorb").toFloat(),
      durabilityLossChanceBlock = getDouble("durability-loss-chance.block").toFloat(),
      durabilityLossChanceDamage = getDouble("durability-loss-chance.damage").toFloat(),
      durabilityLossChanceParry = getDouble("durability-loss-chance.parry").toFloat(),
      eliteCreatureDamageRate = getDouble("creature.elite.damage-rate").toFloat(),
      eliteCreatureHpRate = getDouble("creature.elite.hp-rate").toFloat(),
      eliteCreatureSpellDamageRate = getDouble("creature.elite.spell-damage-rate").toFloat(),
      energyRate = getDouble("energy-rate").toFloat(),
      epicItemDropRate = getDouble("item-drop-rate.epic").toFloat(),
      exploreXpRate = getDouble("explore-xp-rate").toFloat(),
      fallDamage = getDouble("fall-damage").toFloat(),
      focusRate = getDouble("focus-rate").toFloat(),
      gameType = getInt("game-type"),
      gridCleanupDelay = getDuration("grid-cleanup-delay"),
      gridUnload = getBoolean("grid-unload"),
      guarderSight = getDouble("guarder-sight").toFloat(),
      healthRate = getDouble("health-rate").toFloat(),
      honorRate = getDouble("honor-rate").toFloat(),
      instanceResetTimeRate = getDouble("instance-reset-time-rate").toFloat(),
      killXpRate = getDouble("kill-xp-rate").toFloat(),
      legendaryItemDropRate = getDouble("item-drop-rate.legendary").toFloat(),
      listenRangeSay = getDouble("listen-range.say").toFloat(),
      listenRangeTextEmote = getDouble("listen-range.text-emote").toFloat(),
      listenRangeYell = getDouble("listen-range.yell").toFloat(),
      manaRate = getDouble("mana-rate").toFloat(),
      mapUpdateInterval = getDuration("map-update-interval"),
      maxGroupXpDistance = getDouble("max-group-xp-distance").toFloat(),
      maxWhoListReturns = getInt("max-who-list-returns"),
      miningAmountRate = getDouble("mining-amount-rate").toFloat(),
      miningNextRate = getDouble("mining-next-rate").toFloat(),
      moneyDropRate = getDouble("money-drop-rate").toFloat(),
      monsterSight = getDouble("monster-sight").toFloat(),
      normalCreatureDamageRate = getDouble("creature.normal.damage-rate").toFloat(),
      normalCreatureHpRate = getDouble("creature.normal.hp-rate").toFloat(),
      normalCreatureSpellDamageRate = getDouble("creature.normal.spell-damage-rate").toFloat(),
      normalItemDropRate = getDouble("item-drop-rate.normal").toFloat(),
      petXpKillRate = getDouble("pet-xp-kill-rate").toFloat(),
      playerLimit = getInt("player-limit"),
      playerSaveInterval = getDuration("player-save.interval"),
      playerSaveStatsMinLevel = getInt("player-save.stats-min-level"),
      playerSaveStatsSaveOnlyOnLogout = getBoolean("player-save.stats-save-only-on-logout"),
      poorItemDropRate = getDouble("item-drop-rate.poor").toFloat(),
      questItemDropRate = getDouble("item-drop-rate.quest").toFloat(),
      questXpRate = getDouble("quest-xp-rate").toFloat(),
      rageIncomeRate = getDouble("rage.income-rate").toFloat(),
      rageLossRate = getDouble("rage.loss-rate").toFloat(),
      rareCreatureDamageRate = getDouble("creature.rare.damage-rate").toFloat(),
      rareCreatureHpRate = getDouble("creature.rare.hp-rate").toFloat(),
      rareCreatureSpellDamageRate = getDouble("creature.rare.spell-damage-rate").toFloat(),
      rareEliteCreatureDamageRate = getDouble("creature.rare-elite.damage-rate").toFloat(),
      rareEliteCreatureHpRate = getDouble("creature.rare-elite.hp-rate").toFloat(),
      rareEliteCreatureSpellDamageRate = getDouble("rare-elite-creature-spell-damage-rate").toFloat(),
      rareItemDropRate = getDouble("item-drop-rate.rare").toFloat(),
      realmZone = getInt("realm-zone"),
      referencedItemDropRate = getDouble("item-drop-rate.referenced").toFloat(),
      reputationGainRate = getDouble("reputation-gain-rate").toFloat(),
      reputationLowLevelKillRate = getDouble("reputation-low-level-kill-rate").toFloat(),
      reputationLowLevelQuestRate = getDouble("reputation-low-level-quest-rate").toFloat(),
      restInGameRate = getDouble("rest-in-game-rate").toFloat(),
      restInWildernessRate = getDouble("rest-in-wilderness-rate").toFloat(),
      restOfflineInTavernOrCityRate = getDouble("rest-offline-in-tavern-or-city-rate").toFloat(),
      runicPowerIncomeRate = getDouble("runic-power.income-rate").toFloat(),
      runicPowerLossRate = getDouble("runic-power.loss-rate").toFloat(),
      skillDiscoveryRate = getDouble("skill-discovery-rate").toFloat(),
      talentRate = getDouble("talent-rate").toFloat(),
      uncommonItemDropRate = getDouble("item-drop-rate.uncommon").toFloat(),
      worldBossCreatureDamageRate = getDouble("creature.world-boss.damage-rate").toFloat(),
      worldBossCreatureHpRate = getDouble("creature.world-boss.hp-rate").toFloat(),
      worldBossCreatureSpellDamageRate = getDouble("creature.world-boss.spell-damage-rate").toFloat(),
      worldServerPort = getInt("world-server-port")
    )
  }
}
