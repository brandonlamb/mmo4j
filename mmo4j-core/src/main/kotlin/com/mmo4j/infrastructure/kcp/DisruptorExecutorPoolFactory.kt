package com.mmo4j.infrastructure.kcp

import io.micronaut.context.annotation.Bean
import io.micronaut.context.annotation.Factory
import threadpool.thread.DisruptorExecutorPool
import javax.inject.Singleton

@Factory
class DisruptorExecutorPoolFactory {
  @Bean(preDestroy = "stop")
  @Singleton
  fun create(): DisruptorExecutorPool = DisruptorExecutorPool()
}
