package io.github.janipen.hyperhexcast.casting.actions

import at.petrak.hexcasting.api.casting.ParticleSpray
import at.petrak.hexcasting.api.casting.RenderedSpell
import at.petrak.hexcasting.api.casting.asActionResult
import at.petrak.hexcasting.api.casting.castables.ConstMediaAction
import at.petrak.hexcasting.api.casting.castables.SpellAction
import at.petrak.hexcasting.api.casting.eval.CastingEnvironment
import at.petrak.hexcasting.api.casting.getEntity
import at.petrak.hexcasting.api.casting.iota.EntityIota
import at.petrak.hexcasting.api.casting.iota.Iota
import at.petrak.hexcasting.api.casting.mishaps.Mishap
import at.petrak.hexcasting.api.misc.MediaConstants
import at.petrak.hexcasting.api.pigment.FrozenPigment
import net.minecraft.network.chat.Component
import net.minecraft.world.entity.Entity
import net.minecraft.world.entity.player.Player
import net.minecraft.world.item.DyeColor

object GetMount : ConstMediaAction {
    override val argc = 1

    override fun execute(args: List<Iota>, env: CastingEnvironment): List<Iota> {
        val target = args.getEntity(0, argc)
        return target.vehicle.asActionResult
    }
}
object GetPsgr : ConstMediaAction {
    override val argc = 1

    override fun execute(args: List<Iota>, env: CastingEnvironment): List<Iota> {
        val target = args.getEntity(0, argc)
        return (target.passengers as List<Entity>).map { en -> EntityIota(en) }.asActionResult
    }
}
object IsDriver : ConstMediaAction {
    override val argc = 1

    override fun execute(args: List<Iota>, env: CastingEnvironment): List<Iota> {
        val target = args.getEntity(0, argc)
        if (target.vehicle == null) return null.asActionResult
        return (target.controlledVehicle != null).asActionResult
    }
}

class MishapEntitesTooFar(val en1: Entity, val en2: Entity) : Mishap() {
    override fun accentColor(ctx: CastingEnvironment, errorCtx: Context): FrozenPigment =
        dyeColor(DyeColor.PINK)

    override fun execute(env: CastingEnvironment, errorCtx: Context, stack: MutableList<Iota>) {
        en1.push(en1.x - en2.x, en1.y - en2.y, en1.z - en2.z)
        en2.push(en2.x - en1.x, en2.y - en1.y, en2.z - en1.z)
        // apparently these are needed? huh
        en1.hurtMarked = true
        en2.hurtMarked = true
    }

    override fun errorMessage(ctx: CastingEnvironment, errorCtx: Context): Component =
        error("entities_too_far", en1.displayName, en2.displayName)
}

object Mount : SpellAction {
    override val argc = 2

    override fun execute(args: List<Iota>, env: CastingEnvironment): SpellAction.Result {
        val rider = args.getEntity(1, argc)
        val mount = args.getEntity(0, argc)

        if (rider.position().distanceToSqr(mount.position()) > 64)
            throw MishapEntitesTooFar(rider, mount)

        return SpellAction.Result(
            Spell(rider, mount),
            if (rider == env.castingEntity) MediaConstants.DUST_UNIT else (if (rider is Player) (5L * MediaConstants.DUST_UNIT) else (2L * MediaConstants.DUST_UNIT)),
            listOf(ParticleSpray.cloud(mount.position().add(0.0, mount.eyeHeight / 2.0, 0.0), 1.0))
        )
    }

    private data class Spell(val rider: Entity, val mount: Entity) : RenderedSpell {
        // IMPORTANT: do not throw mishaps in this method! mishaps should ONLY be thrown in SpellAction.execute
        override fun cast(env: CastingEnvironment) {
            rider.startRiding(mount)
        }
    }
}

object Dismount : SpellAction {
    override val argc = 1

    override fun execute(args: List<Iota>, env: CastingEnvironment): SpellAction.Result {
        val rider = args.getEntity(0, argc)

        return SpellAction.Result(
            Spell(rider),
            if (rider == env.castingEntity) (MediaConstants.DUST_UNIT / 1000) else MediaConstants.DUST_UNIT,
            listOf(ParticleSpray.cloud(rider.position().add(0.0, rider.eyeHeight / 2.0, 0.0), 1.0))
        )
    }

    private data class Spell(val rider: Entity) : RenderedSpell {
        // IMPORTANT: do not throw mishaps in this method! mishaps should ONLY be thrown in SpellAction.execute
        override fun cast(env: CastingEnvironment) {
            rider.stopRiding()
        }
    }
}