@file:JvmName("HyperhexcastAbstractionsImpl")

package io.github.janipen.hyperhexcast.fabric

import io.github.janipen.hyperhexcast.registry.HyperhexcastRegistrar
import net.minecraft.core.Registry

fun <T : Any> initRegistry(registrar: HyperhexcastRegistrar<T>) {
    val registry = registrar.registry
    registrar.init { id, value -> Registry.register(registry, id, value) }
}
