package com.mmo4j.infrastructure.kcp

import com.mmo4j.kcp.netty.UkcpChannel
import io.netty.channel.ChannelDuplexHandler
import io.netty.channel.ChannelHandlerContext
import io.netty.channel.ChannelPromise
import io.netty.util.internal.logging.InternalLoggerFactory
import java.net.SocketAddress
import javax.inject.Singleton

@Singleton
class DuplexHandler : ChannelDuplexHandler() {
  private val logger = InternalLoggerFactory.getInstance(DuplexHandler::class.java)

  override fun connect(ctx: ChannelHandlerContext?, remoteAddress: SocketAddress?, localAddress: SocketAddress?, promise: ChannelPromise?) {
    super.connect(ctx, remoteAddress, localAddress, promise)
    logger.info("CONNECT DUPLEX - remoteAddress={}", remoteAddress?.toString())
  }

  override fun disconnect(ctx: ChannelHandlerContext?, promise: ChannelPromise?) {
    super.disconnect(ctx, promise)
    logger.info("DISCONNECT")
  }

  override fun channelActive(ctx: ChannelHandlerContext) {
    val kcpCh = ctx.channel() as UkcpChannel
    kcpCh.conv(10)
    logger.info("channelActive")
  }

  override fun channelRead(ctx: ChannelHandlerContext, msg: Any) {
//    ctx.write(msg)
    logger.info("got message")
  }

  override fun channelReadComplete(ctx: ChannelHandlerContext) {
    ctx.flush()
    logger.info("flushed ctx")
  }

  override fun write(ctx: ChannelHandlerContext?, msg: Any?, promise: ChannelPromise?) {
    super.write(ctx, msg, promise)

    logger.info("sent message")
  }

  override fun exceptionCaught(ctx: ChannelHandlerContext, cause: Throwable) {
    // Close the connection when an exception is raised.
    logger.error("exceptionCaught={}", cause.message)
    cause.printStackTrace()
    ctx.close()
  }
}
