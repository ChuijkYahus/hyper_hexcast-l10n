package io.github.janipen.hyperhexcast

import io.github.janipen.hyperhexcast.config.HyperhexcastClientConfig
import me.shedaniel.autoconfig.AutoConfig
import net.minecraft.client.gui.screens.Screen

object HyperhexcastClient {
    fun init() {
        HyperhexcastClientConfig.init()
    }

    fun getConfigScreen(parent: Screen): Screen {
        return AutoConfig.getConfigScreen(HyperhexcastClientConfig.GlobalConfig::class.java, parent).get()
    }
}
