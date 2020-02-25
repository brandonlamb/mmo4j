package com.mmo4j.infrastructure.kcp

import com.mmo4j.kcp.netty.UkcpChannel
import io.netty.buffer.ByteBuf
import io.netty.channel.ChannelHandlerContext
import io.netty.channel.ChannelInboundHandlerAdapter
import io.netty.util.internal.logging.InternalLoggerFactory
import javax.inject.Singleton

@Singleton
class FireAndForgetHandler : ChannelInboundHandlerAdapter() {
  private val logger = InternalLoggerFactory.getInstance(FireAndForgetHandler::class.java)

  override fun channelActive(ctx: ChannelHandlerContext) {
    (ctx.channel() as UkcpChannel).conv(10)
  }

  /**
   * This is where we would deserialize the message and throw to akka
   */
  override fun channelRead(ctx: ChannelHandlerContext, msg: Any) {
    val buf = msg as ByteBuf
    buf.release()
//    val curCount = buf.getShort(buf.readerIndex())
//    ctx.writeAndFlush(msg)
  }

  override fun exceptionCaught(ctx: ChannelHandlerContext, cause: Throwable) {
    // Close the connection when an exception is raised.
    logger.error("exceptionCaught", cause)
    cause.printStackTrace()
    ctx.close()
  }
}
