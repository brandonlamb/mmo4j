package com.mmo4j.infrastructure.akka

import io.micronaut.context.annotation.Bean
import io.micronaut.context.annotation.Factory
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors
import javax.inject.Named
import javax.inject.Singleton

@Factory
class ThreadFactory {
  @Bean(preDestroy = "shutdown")
  @Singleton
  @Named("akka-es-1")
  fun createThreadPool1(): ExecutorService = Executors.newCachedThreadPool()

  @Bean(preDestroy = "shutdown")
  @Singleton
  @Named("akka-es-2")
  fun createThreadPool2(): ExecutorService = Executors.newFixedThreadPool(8)
}
