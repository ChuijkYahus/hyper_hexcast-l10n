package io.github.janipen.hyperhexcast.networking

import dev.architectury.networking.NetworkChannel
import io.github.janipen.hyperhexcast.Hyperhexcast
import io.github.janipen.hyperhexcast.networking.msg.HyperhexcastMessageCompanion

object HyperhexcastNetworking {
    val CHANNEL: NetworkChannel = NetworkChannel.create(Hyperhexcast.id("networking_channel"))

    fun init() {
        for (subclass in HyperhexcastMessageCompanion::class.sealedSubclasses) {
            subclass.objectInstance?.register(CHANNEL)
        }
    }
}
