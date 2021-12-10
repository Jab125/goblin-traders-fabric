package com.jab125.util.datagen;


import net.hat.gt.datagen.GoblinTradeProvider;
import net.minecraft.data.DataGenerator;

public class DataGeneraton {
    public static void registerCommonProviders(DataGenerator generator) {
        generator.addProvider(new GoblinTradeProvider(generator));
        try {
            generator.run();
        } catch (Exception e) {
            e.printStackTrace();
        }
        //generator.install(new GoblinLootTableProvider(generator));
    }
}

