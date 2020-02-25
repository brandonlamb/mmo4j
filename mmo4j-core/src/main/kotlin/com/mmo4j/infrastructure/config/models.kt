package com.mmo4j.infrastructure.config

data class UdpServerConfig(
  val host: String,
  val port: Int,
  val connectTimeoutMillis: Int,
  val selectorThreads: Int,
  val workerThreads: Int,
  val bindInSeconds: Long
)
