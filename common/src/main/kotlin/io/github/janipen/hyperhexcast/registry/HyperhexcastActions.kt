package io.github.janipen.hyperhexcast.registry

import at.petrak.hexcasting.api.casting.ActionRegistryEntry
import at.petrak.hexcasting.api.casting.castables.Action
import at.petrak.hexcasting.api.casting.math.HexDir
import at.petrak.hexcasting.api.casting.math.HexPattern
import at.petrak.hexcasting.common.lib.HexRegistries
import at.petrak.hexcasting.common.lib.hex.HexActions
import io.github.janipen.hyperhexcast.casting.actions.Dismount
import io.github.janipen.hyperhexcast.casting.actions.GetMount
import io.github.janipen.hyperhexcast.casting.actions.GetPsgr
import io.github.janipen.hyperhexcast.casting.actions.IsDriver
import io.github.janipen.hyperhexcast.casting.actions.Mount
import io.github.janipen.hyperhexcast.casting.actions.OpRange

object HyperhexcastActions : HyperhexcastRegistrar<ActionRegistryEntry>(
    HexRegistries.ACTION,
    { HexActions.REGISTRY },
) {
    val RANGE = make("range", HexDir.NORTH_EAST, "qedd", OpRange)

    val GETMOUNT = make("getmt", HexDir.SOUTH_EAST, "eaded", GetMount)
    val GETPSGR = make("getpsgr", HexDir.SOUTH_WEST, "aqadq", GetPsgr)
    val ISDRIVER = make("isdrvr", HexDir.SOUTH_WEST, "aqadea", IsDriver)

    val MOUNT = make("mount", HexDir.SOUTH_EAST, "eadewde", Mount)
    val DISMOUNT = make("dismount", HexDir.WEST, "qawqadq", Dismount)

    private fun make(name: String, startDir: HexDir, signature: String, action: Action) =
        make(name, startDir, signature) { action }

    private fun make(name: String, startDir: HexDir, signature: String, getAction: () -> Action) = register(name) {
        ActionRegistryEntry(HexPattern.fromAngles(signature, startDir), getAction())
    }
}
