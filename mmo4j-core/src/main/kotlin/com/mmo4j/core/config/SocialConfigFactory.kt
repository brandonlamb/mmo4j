package com.mmo4j.core.config

import com.typesafe.config.Config
import io.micronaut.context.annotation.Bean
import io.micronaut.context.annotation.Factory
import javax.inject.Singleton

@Factory
class SocialConfigFactory {
  @Bean
  @Singleton
  fun createSocial(config: Config): SocialConfig = with(config.getConfig("mmo4j.social")) {
    SocialConfig(
      friendLimit = getInt("friend-limit"),
      ignoreLimit = getInt("ignore-limit")
    )
  }
}
