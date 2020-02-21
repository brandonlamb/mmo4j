package com.mmo4j.infrastructure.kcp

import io.micronaut.context.annotation.Bean
import io.micronaut.context.annotation.Factory
import kcp.ChannelConfig
import javax.inject.Singleton

@Factory
class ChannelConfigFactory {
  @Bean
  @Singleton
  fun create(): ChannelConfig {
    val config = ChannelConfig()

    config.nodelay(true, 40, 2, true)
    config.sndwnd = 300
    config.rcvwnd = 300
    config.mtu = 512
    config.isAckNoDelay = false
    config.isCrc32Check = true
    config.timeoutMillis = 10000

    return config
  }
}
