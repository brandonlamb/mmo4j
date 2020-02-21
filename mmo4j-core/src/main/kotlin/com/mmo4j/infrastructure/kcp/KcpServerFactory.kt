package com.mmo4j.infrastructure.kcp

import io.micronaut.context.annotation.Bean
import io.micronaut.context.annotation.Factory
import io.netty.util.NettyRuntime.availableProcessors
import kcp.ChannelConfig
import kcp.KcpServer
import javax.inject.Singleton

@Factory
class KcpServerFactory {
  @Bean
  @Singleton
  fun create(config: ChannelConfig, gameListener: GameListener): KcpServer {
    val server = KcpServer()
    server.init(availableProcessors(), gameListener, config, 1337)

    return server
  }
}
