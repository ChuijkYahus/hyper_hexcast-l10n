@file:JvmName("HyperhexcastAbstractions")

package io.github.janipen.hyperhexcast

import dev.architectury.injectables.annotations.ExpectPlatform
import io.github.janipen.hyperhexcast.registry.HyperhexcastRegistrar

fun initRegistries(vararg registries: HyperhexcastRegistrar<*>) {
    for (registry in registries) {
        initRegistry(registry)
    }
}

@ExpectPlatform
fun <T : Any> initRegistry(registrar: HyperhexcastRegistrar<T>) {
    throw AssertionError()
}
