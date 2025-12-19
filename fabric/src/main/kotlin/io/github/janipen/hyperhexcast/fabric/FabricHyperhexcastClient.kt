package io.github.janipen.hyperhexcast.fabric

import io.github.janipen.hyperhexcast.HyperhexcastClient
import net.fabricmc.api.ClientModInitializer

object FabricHyperhexcastClient : ClientModInitializer {
    override fun onInitializeClient() {
        HyperhexcastClient.init()
    }
}
