package io.github.janipen.hyperhexcast.forge

import io.github.janipen.hyperhexcast.Hyperhexcast
import net.minecraftforge.fml.event.lifecycle.FMLDedicatedServerSetupEvent

object ForgeHyperhexcastServer {
    @Suppress("UNUSED_PARAMETER")
    fun init(event: FMLDedicatedServerSetupEvent) {
        Hyperhexcast.initServer()
    }
}
