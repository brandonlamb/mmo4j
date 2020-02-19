package com.mmo4j.core.account.api

import com.mmo4j.core.account.Account
import com.mmo4j.core.account.AccountId

interface AccountReadRepository {
  fun exists(username: String): Boolean
  fun findByUsername(username: String): Account?
  fun findById(id: AccountId): Account?
}
