package com.mmo4j.core.account

import com.mmo4j.core.account.api.AccountReadRepository
import com.mmo4j.core.account.api.AccountWriteRepository
import com.mmo4j.core.config.AccountConfig
import com.mmo4j.infrastructure.proto.AccountProto.AccountOpResult
import javax.inject.Inject
import javax.inject.Singleton

/**
 * This basically seems like a domain service for account
 */
@Singleton
class AccountService @Inject constructor(
  private val accountConfig: AccountConfig,
  private val accountReadRepository: AccountReadRepository,
  private val accountWriteRepository: AccountWriteRepository
) {
  fun createAccount(username: String, password: String): AccountOpResult {
    if (username.length > accountConfig.maxAccountLength) {
      return AccountOpResult.AOR_NAME_TOO_LONG
    }

    if (accountReadRepository.exists(username)) {
      return AccountOpResult.AOR_NAME_ALREADY_EXIST
    }

    // Hash password
    // Insert into database
    accountWriteRepository.create(username, password)

    return AccountOpResult.AOR_OK
  }

  fun deleteByUsername(username: String): AccountOpResult {
    accountWriteRepository.removeByUsername(username)

    return AccountOpResult.AOR_OK
  }
}
