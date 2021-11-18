package net.hat.gt.datagen;

import com.jab125.util.datagen.DataGeneraton;
import net.minecraft.data.DataGenerator;

import java.io.File;
import java.io.IOException;

public class DataGen {
    public static final boolean ENABLED = System.getProperty("fabric-api.datagen") != null;
    public static void run() throws IOException {
        DataGenerator dataGenerator = new DataGenerator(new File("../src/main/generated/resources").toPath(), null);
        DataGeneraton.registerCommonProviders(dataGenerator);
    }
}
