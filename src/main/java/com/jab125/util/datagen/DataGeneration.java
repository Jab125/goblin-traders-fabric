package com.jab125.util.datagen;

import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.hat.gt.datagen.GoblinTradeProvider;

public class DataGeneration implements DataGeneratorEntrypoint {
    @Override
    public void onInitializeDataGenerator(FabricDataGenerator fabricDataGenerator) {
        //console.log(await res.json());
        LanguageProvider languageProvider = new LanguageProvider(fabricDataGenerator, "en_us");
        fabricDataGenerator.addProvider(new GoblinTradeProvider(fabricDataGenerator));
        fabricDataGenerator.addProvider(languageProvider);
    }
}
