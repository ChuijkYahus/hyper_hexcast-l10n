package io.github.janipen.hyperhexcast

import net.minecraft.resources.ResourceLocation
import org.apache.logging.log4j.LogManager
import org.apache.logging.log4j.Logger
import io.github.janipen.hyperhexcast.config.HyperhexcastServerConfig
import io.github.janipen.hyperhexcast.networking.HyperhexcastNetworking
import io.github.janipen.hyperhexcast.registry.HyperhexcastActions

object Hyperhexcast {
    const val MODID = "hyperhexcast"

    @JvmField
    val LOGGER: Logger = LogManager.getLogger(MODID)

    @JvmStatic
    fun id(path: String) = ResourceLocation(MODID, path)

    fun init() {
        HyperhexcastServerConfig.init()
        initRegistries(
            HyperhexcastActions,
        )
        HyperhexcastNetworking.init()
    }

    fun initServer() {
        HyperhexcastServerConfig.initServer()
    }
}
