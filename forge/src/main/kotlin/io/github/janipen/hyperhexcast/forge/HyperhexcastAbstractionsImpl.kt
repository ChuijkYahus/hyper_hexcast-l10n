@file:JvmName("HyperhexcastAbstractionsImpl")

package io.github.janipen.hyperhexcast.forge

import io.github.janipen.hyperhexcast.registry.HyperhexcastRegistrar
import net.minecraftforge.registries.RegisterEvent
import thedarkcolour.kotlinforforge.forge.MOD_BUS

fun <T : Any> initRegistry(registrar: HyperhexcastRegistrar<T>) {
    MOD_BUS.addListener { event: RegisterEvent ->
        event.register(registrar.registryKey) { helper ->
            registrar.init(helper::register)
        }
    }
}
