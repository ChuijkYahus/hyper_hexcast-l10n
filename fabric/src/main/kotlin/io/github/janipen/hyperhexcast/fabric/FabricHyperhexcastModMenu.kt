package io.github.janipen.hyperhexcast.fabric

import com.terraformersmc.modmenu.api.ConfigScreenFactory
import com.terraformersmc.modmenu.api.ModMenuApi
import io.github.janipen.hyperhexcast.HyperhexcastClient

object FabricHyperhexcastModMenu : ModMenuApi {
    override fun getModConfigScreenFactory() = ConfigScreenFactory(HyperhexcastClient::getConfigScreen)
}
