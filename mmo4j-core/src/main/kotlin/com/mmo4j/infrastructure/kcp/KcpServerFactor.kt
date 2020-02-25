package com.mmo4j.infrastructure.kcp

import com.mmo4j.infrastructure.config.UdpServerConfig
import com.mmo4j.kcp.netty.ChannelOptionHelper
import com.mmo4j.kcp.netty.UkcpChannelOption
import com.mmo4j.kcp.netty.UkcpServerChannel
import io.micronaut.context.annotation.Bean
import io.micronaut.context.annotation.Factory
import io.netty.bootstrap.UkcpServerBootstrap
import io.netty.channel.ChannelOption
import io.netty.channel.nio.NioEventLoopGroup
import io.netty.util.internal.SocketUtils
import javax.inject.Singleton

@Factory
class KcpServerFactor {
  @Bean
  @Singleton
  fun create(c: UdpServerConfig): UkcpServerBootstrap {
    val bootstrap = UkcpServerBootstrap()
      .group(NioEventLoopGroup(4))
      .localAddress(SocketUtils.socketAddress(c.host, c.port))
      .channel(UkcpServerChannel::class.java)
      .option(ChannelOption.CONNECT_TIMEOUT_MILLIS, c.connectTimeoutMillis)

    ChannelOptionHelper.nodelay(bootstrap, true, 20, 2, true).childOption(UkcpChannelOption.UKCP_MTU, 512)

    return bootstrap
  }
}
