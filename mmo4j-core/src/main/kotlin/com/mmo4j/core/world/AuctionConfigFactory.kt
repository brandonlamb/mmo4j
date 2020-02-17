package com.mmo4j.core.world

import com.mmo4j.core.world.WorldConfig.AuctionConfig
import com.mmo4j.core.world.WorldConfig.AuctionConfig.DepositConfig
import com.typesafe.config.Config
import io.micronaut.context.annotation.Bean
import io.micronaut.context.annotation.Factory
import javax.inject.Singleton

@Factory
class AuctionConfigFactory {
  @Bean
  @Singleton
  fun createWorldAuction(deposit: DepositConfig) = AuctionConfig(deposit = deposit)

  @Bean
  @Singleton
  fun createWorldAuctionDeposit(config: Config) =
    with(config.getConfig("mmo4j.world.auction")) {
      DepositConfig(getInt("deposit-min"))
    }
}
