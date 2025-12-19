package io.github.janipen.hyperhexcast.fabric

import io.github.janipen.hyperhexcast.Hyperhexcast
import net.fabricmc.api.ModInitializer

object FabricHyperhexcast : ModInitializer {
    override fun onInitialize() {
        Hyperhexcast.init()
    }
}
