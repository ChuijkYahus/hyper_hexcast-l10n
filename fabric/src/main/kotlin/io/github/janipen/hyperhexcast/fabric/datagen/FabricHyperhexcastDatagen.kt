package io.github.janipen.hyperhexcast.fabric.datagen

import io.github.janipen.hyperhexcast.datagen.HyperhexcastActionTags
import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator

object FabricHyperhexcastDatagen : DataGeneratorEntrypoint {
    override fun onInitializeDataGenerator(gen: FabricDataGenerator) {
        val pack = gen.createPack()

        pack.addProvider(::HyperhexcastActionTags)
    }
}
