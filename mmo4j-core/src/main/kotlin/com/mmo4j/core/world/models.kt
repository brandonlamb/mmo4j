package com.mmo4j.core.world

data class WorldConfig(
  val playerLimit: Int,
  val rate: RateConfig
) {
  data class RateConfig(
    val health: Double,
    val mana: Double,
    val focus: Double,
    val energy: Double,
    val petXpKill: Double,
    val rage: RageConfig,
    val runicPower: RunicPowerConfig,
    val skill: SkillConfig,
    val drop: DropConfig,
    val experience: ExperienceConfig,
    val reputation: ReputationConfig,
    val creature: CreatureConfig,
    val rest: RestConfig
  ) {
    data class RageConfig(val income: Double, val loss: Double)
    data class RunicPowerConfig(val income: Double, val loss: Double)
    data class SkillConfig(val discovery: Double)

    data class DropConfig(
      val money: Double,
      val currency: Double,
      val currencyAmount: Double,
      val item: ItemConfig
    ) {
      data class ItemConfig(
        val poor: Double,
        val normal: Double,
        val uncommon: Double,
        val rare: Double,
        val epic: Double,
        val legendary: Double,
        val artifact: Double,
        val referenced: Double,
        val quest: Double
      )
    }

    data class ExperienceConfig(val kill: Double, val quest: Double, val explore: Double)

    data class ReputationConfig(val gain: Double, val lowLevel: LowLevelConfig) {
      data class LowLevelConfig(val kill: Double, val quest: Double)
    }

    data class CreatureConfig(
      val aggro: Double,
      val normal: NormalConfig,
      val elite: EliteConfig
    ) {
      data class NormalConfig(val hp: Double, val damage: Double, val spellDamage: Double)

      data class EliteConfig(
        val elite: EliteConfig,
        val rareElite: RareEliteConfig,
        val rare: RareConfig,
        val worldBoss: WorldBossConfig
      ) {
        data class EliteConfig(val hp: Double, val damage: Double, val spellDamage: Double)
        data class RareEliteConfig(val hp: Double, val damage: Double, val spellDamage: Double)
        data class RareConfig(val hp: Double, val damage: Double, val spellDamage: Double)
        data class WorldBossConfig(val hp: Double, val damage: Double, val spellDamage: Double)
      }
    }

    data class RestConfig(val inGame: Double, val offline: OfflineConfig) {
      data class OfflineConfig(val inTavernOrCity: Double, val inWilderness: Double)
    }
  }
}
