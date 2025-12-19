package io.github.janipen.hyperhexcast.forge.datagen

import at.petrak.hexcasting.forge.datagen.TagsProviderEFHSetter
import io.github.janipen.hyperhexcast.datagen.HyperhexcastActionTags
import net.minecraft.data.DataProvider
import net.minecraft.data.PackOutput
import net.minecraftforge.data.event.GatherDataEvent

object ForgeHyperhexcastDatagen {
    fun init(event: GatherDataEvent) {
        event.apply {
            // common datagen
            if (System.getProperty("hyperhexcast.common-datagen") == "true") {
                // TODO: add datagen providers
            }

            // Forge-only datagen
            if (System.getProperty("hyperhexcast.forge-datagen") == "true") {
                addVanillaProvider(includeServer()) { HyperhexcastActionTags(it, lookupProvider) }
            }
        }
    }
}

private fun <T : DataProvider> GatherDataEvent.addProvider(run: Boolean, factory: (PackOutput) -> T) =
    generator.addProvider(run, DataProvider.Factory { factory(it) })

private fun <T : DataProvider> GatherDataEvent.addVanillaProvider(run: Boolean, factory: (PackOutput) -> T) =
    addProvider(run) { packOutput ->
        factory(packOutput).also {
            (it as TagsProviderEFHSetter).setEFH(existingFileHelper)
        }
    }
