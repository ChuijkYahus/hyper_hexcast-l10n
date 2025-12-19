package io.github.janipen.hyperhexcast.fabric

import io.github.janipen.hyperhexcast.Hyperhexcast
import net.fabricmc.api.DedicatedServerModInitializer

object FabricHyperhexcastServer : DedicatedServerModInitializer {
    override fun onInitializeServer() {
        Hyperhexcast.initServer()
    }
}
