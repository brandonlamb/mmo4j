package com.mmo4j.infrastructure.kcp

import com.backblaze.erasure.fec.Snmp
import io.netty.buffer.ByteBuf
import kcp.KcpListener
import kcp.Ukcp
import org.slf4j.LoggerFactory
import javax.inject.Singleton

@Singleton
class GameListener : KcpListener {
  private val logger = LoggerFactory.getLogger(javaClass)

  override fun onConnected(ukcp: Ukcp) {
    logger.info("New connection from {}", ukcp.user().remoteAddress)
  }

  override fun handleReceive(byteBuf: ByteBuf, ukcp: Ukcp, protocolType: Int) {
    val curCount = byteBuf.getShort(byteBuf.readerIndex())
    logger.info("count={}", curCount)

    ukcp.writeOrderedReliableMessage(byteBuf)

    if (curCount == CLOSE_EVENT) {
      ukcp.notifyCloseEvent()
    }
  }

  override fun handleClose(ukcp: Ukcp) {
    logger.info("remoteAddress={}, snmp={}", ukcp.user().remoteAddress, Snmp.snmp)
  }

  override fun handleException(ex: Throwable, ukcp: Ukcp) {
    logger.error("remoteAddress={}, message={}", ukcp.user().remoteAddress, ex.message)
  }

  private companion object {
    private const val CLOSE_EVENT: Short = -1
  }
}
