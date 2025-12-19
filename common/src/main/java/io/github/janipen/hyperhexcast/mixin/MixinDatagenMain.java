package io.github.janipen.hyperhexcast.mixin;

import io.github.janipen.hyperhexcast.Hyperhexcast;
import org.spongepowered.asm.mixin.Mixin;
import com.llamalad7.mixinextras.injector.wrapmethod.WrapMethod;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;

// scuffed workaround for https://github.com/architectury/architectury-loom/issues/189
@Mixin({
    net.minecraft.data.Main.class,
    net.minecraft.server.Main.class,
})
public abstract class MixinDatagenMain {
    @WrapMethod(method = "main", remap = false)
    private static void hyperhexcast$systemExitAfterDatagenFinishes(String[] strings, Operation<Void> original) {
        try {
            original.call((Object) strings);
        } catch (Throwable throwable) {
            Hyperhexcast.LOGGER.error("Datagen failed!", throwable);
            System.exit(1);
        }
        Hyperhexcast.LOGGER.info("Datagen succeeded, terminating.");
        System.exit(0);
    }
}
