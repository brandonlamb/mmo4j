package com.mmo4j.core.social

import com.mmo4j.infrastructure.proto.SocialProto.FriendInfo

typealias PlayerSocialMap = Map<Int, FriendInfo>
typealias SocialMap = Map<Int, PlayerSocial>

class PlayerSocial
