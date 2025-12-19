package io.github.janipen.hyperhexcast.networking.msg

import io.github.janipen.hyperhexcast.config.HyperhexcastServerConfig
import net.minecraft.network.FriendlyByteBuf

data class MsgSyncConfigS2C(val serverConfig: HyperhexcastServerConfig.ServerConfig) : HyperhexcastMessageS2C {
    companion object : HyperhexcastMessageCompanion<MsgSyncConfigS2C> {
        override val type = MsgSyncConfigS2C::class.java

        override fun decode(buf: FriendlyByteBuf) = MsgSyncConfigS2C(
            serverConfig = HyperhexcastServerConfig.ServerConfig().decode(buf),
        )

        override fun MsgSyncConfigS2C.encode(buf: FriendlyByteBuf) {
            serverConfig.encode(buf)
        }
    }
}
