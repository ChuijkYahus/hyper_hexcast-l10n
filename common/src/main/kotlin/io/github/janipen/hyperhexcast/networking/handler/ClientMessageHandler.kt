package io.github.janipen.hyperhexcast.networking.handler

import dev.architectury.networking.NetworkManager.PacketContext
import io.github.janipen.hyperhexcast.config.HyperhexcastServerConfig
import io.github.janipen.hyperhexcast.networking.msg.*

fun HyperhexcastMessageS2C.applyOnClient(ctx: PacketContext) = ctx.queue {
    when (this) {
        is MsgSyncConfigS2C -> {
            HyperhexcastServerConfig.onSyncConfig(serverConfig)
        }

        // add more client-side message handlers here
    }
}
