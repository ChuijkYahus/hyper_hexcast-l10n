package io.github.janipen.hyperhexcast.casting.actions

import at.petrak.hexcasting.api.casting.asActionResult
import at.petrak.hexcasting.api.casting.castables.ConstMediaAction
import at.petrak.hexcasting.api.casting.eval.CastingEnvironment
import at.petrak.hexcasting.api.casting.getPositiveInt
import at.petrak.hexcasting.api.casting.iota.DoubleIota
import at.petrak.hexcasting.api.casting.iota.Iota

object OpRange : ConstMediaAction {
    override val argc = 1

    override fun execute(args: List<Iota>, env: CastingEnvironment): List<Iota> {
        val target = args.getPositiveInt(0, argc)
        val out = mutableListOf<DoubleIota>()
        for (i in 0..<target) {
            out.add(DoubleIota(i.toDouble()))
        }
        return out.asActionResult
    }
}