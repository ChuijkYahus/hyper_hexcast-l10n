package io.github.janipen.hyperhexcast.networking.msg

import dev.architectury.networking.NetworkChannel
import dev.architectury.networking.NetworkManager.PacketContext
import io.github.janipen.hyperhexcast.Hyperhexcast
import io.github.janipen.hyperhexcast.networking.HyperhexcastNetworking
import io.github.janipen.hyperhexcast.networking.handler.applyOnClient
import io.github.janipen.hyperhexcast.networking.handler.applyOnServer
import net.fabricmc.api.EnvType
import net.minecraft.network.FriendlyByteBuf
import net.minecraft.server.level.ServerPlayer
import java.util.function.Supplier

sealed interface HyperhexcastMessage

sealed interface HyperhexcastMessageC2S : HyperhexcastMessage {
    fun sendToServer() {
        HyperhexcastNetworking.CHANNEL.sendToServer(this)
    }
}

sealed interface HyperhexcastMessageS2C : HyperhexcastMessage {
    fun sendToPlayer(player: ServerPlayer) {
        HyperhexcastNetworking.CHANNEL.sendToPlayer(player, this)
    }

    fun sendToPlayers(players: Iterable<ServerPlayer>) {
        HyperhexcastNetworking.CHANNEL.sendToPlayers(players, this)
    }
}

sealed interface HyperhexcastMessageCompanion<T : HyperhexcastMessage> {
    val type: Class<T>

    fun decode(buf: FriendlyByteBuf): T

    fun T.encode(buf: FriendlyByteBuf)

    fun apply(msg: T, supplier: Supplier<PacketContext>) {
        val ctx = supplier.get()
        when (ctx.env) {
            EnvType.SERVER, null -> {
                Hyperhexcast.LOGGER.debug("Server received packet from {}: {}", ctx.player.name.string, this)
                when (msg) {
                    is HyperhexcastMessageC2S -> msg.applyOnServer(ctx)
                    else -> Hyperhexcast.LOGGER.warn("Message not handled on server: {}", msg::class)
                }
            }
            EnvType.CLIENT -> {
                Hyperhexcast.LOGGER.debug("Client received packet: {}", this)
                when (msg) {
                    is HyperhexcastMessageS2C -> msg.applyOnClient(ctx)
                    else -> Hyperhexcast.LOGGER.warn("Message not handled on client: {}", msg::class)
                }
            }
        }
    }

    fun register(channel: NetworkChannel) {
        channel.register(type, { msg, buf -> msg.encode(buf) }, ::decode, ::apply)
    }
}
