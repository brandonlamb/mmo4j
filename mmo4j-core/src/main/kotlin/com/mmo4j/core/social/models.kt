package com.mmo4j.core.social

import com.mmo4j.infrastructure.proto.SocialProto.FriendInfo
import com.mmo4j.infrastructure.proto.SocialProto.FriendStatus

typealias PlayerSocialMap = MutableMap<Int, FriendInfo>
typealias SocialMap = Map<Int, PlayerSocial>

class PlayerSocial(private val playerId: Long, val socialMap: PlayerSocialMap) {
  fun addToSocialList(friendId: Int, ignore: Boolean): Boolean {
    socialMap[friendId] = FriendInfo(FriendStatus.FS_ONLINE, 0, 0, 16, 1, "")
    return true
  }

  fun removeFromSocialList(friendId: Int, ignore: Boolean) {
    socialMap.remove(friendId)
  }

  fun setFriendNote(friendId: Int, note: String) {
    // val friend = socialMap[friendId].clone(note = note)
  }

  /**
   * Would send network packet/message?
   */
  fun sendSocialList() {

  }

  fun hasFriend(friendId: Int) = socialMap.containsKey(friendId)
  fun hasIgnore(friendId: Int) = socialMap[friendId]?.flags == 1

  /**
   * remove?
   */
  fun setPlayerGuid(guid: Int) {

  }
}
