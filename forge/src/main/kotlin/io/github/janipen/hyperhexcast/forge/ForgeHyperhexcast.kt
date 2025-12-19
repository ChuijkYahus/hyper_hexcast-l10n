package io.github.janipen.hyperhexcast.forge

import dev.architectury.platform.forge.EventBuses
import io.github.janipen.hyperhexcast.Hyperhexcast
import io.github.janipen.hyperhexcast.forge.datagen.ForgeHyperhexcastDatagen
import net.minecraftforge.fml.common.Mod
import thedarkcolour.kotlinforforge.forge.MOD_BUS

@Mod(Hyperhexcast.MODID)
class ForgeHyperhexcast {
    init {
        MOD_BUS.apply {
            EventBuses.registerModEventBus(Hyperhexcast.MODID, this)
            addListener(ForgeHyperhexcastClient::init)
            addListener(ForgeHyperhexcastDatagen::init)
            addListener(ForgeHyperhexcastServer::init)
        }
        Hyperhexcast.init()
    }
}
