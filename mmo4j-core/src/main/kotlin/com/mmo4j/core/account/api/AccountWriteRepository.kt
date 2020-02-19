package com.mmo4j.core.account.api

import com.mmo4j.core.account.AccountId

interface AccountWriteRepository {
  fun create(username: String, password: String): AccountId
  fun removeById(id: AccountId)
  fun removeByUsername(username: String)
  fun changePassword(accountId: AccountId, password: String)
}
