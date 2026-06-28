package io.github.farmitzdugan.trainingmod.item;

import io.github.farmitzdugan.trainingmod.TrainingMod;
import net.fabricmc.fabric.api.creativetab.v1.CreativeModeTabEvents;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.Item;

import java.util.function.Function;

public final class ModItems {
    public static final Item TRAINING_TOKEN = register(
            "training_token",
            Item::new,
            new Item.Properties()
    );

    private ModItems() {
    }

    private static <T extends Item> T register(
            String name,
            Function<Item.Properties, T> itemFactory,
            Item.Properties properties
    ) {
        ResourceKey<Item> itemKey = ResourceKey.create(
                Registries.ITEM,
                TrainingMod.id(name)
        );

        T item = itemFactory.apply(properties.setId(itemKey));
        Registry.register(BuiltInRegistries.ITEM, itemKey, item);

        return item;
    }

    public static void initialize() {
        CreativeModeTabEvents.modifyOutputEvent(CreativeModeTabs.INGREDIENTS)
                .register(entries -> entries.accept(TRAINING_TOKEN));
    }
}