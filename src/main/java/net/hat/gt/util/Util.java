package net.hat.gt.util;

import com.google.gson.JsonArray;
import it.unimi.dsi.fastutil.ints.Int2ObjectArrayMap;
import net.fabricmc.loader.api.FabricLoader;
import net.hat.gt.GobT;
<<<<<<< Updated upstream
import net.hat.gt.entities.ai.ExcludeCreativeModeRevengeGoal;
=======
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.MinecraftClientGame;
>>>>>>> Stashed changes
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.crash.CrashReport;
import org.jetbrains.annotations.Nullable;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;


public class Util {


    public static boolean isModInstalled(String modid) {
        return FabricLoader.getInstance().isModLoaded(modid);
    }
    public static boolean isOverflowInstalled() {
        return isModInstalled("overflow");
    }
    public static boolean isEnchantmentDisplaysInstalled() {
        return isModInstalled("enchantment-displays");
    }
    public static boolean maxEnchantTextConfig() {
        return GobT.config.MAX_ENCHANTMENT_TEXT && !isEnchantmentDisplaysInstalled();
    }

    public static boolean isInCreativeMode(Entity entity) {
        if (entity.isPlayer()) {
            PlayerEntity playerEntity = (PlayerEntity)entity;
            if (playerEntity.isCreative()) {
                return true;
            }
        }
        return false;
    }
<<<<<<< Updated upstream
    public static boolean isInCreativeModeStop(Entity entity, ExcludeCreativeModeRevengeGoal goal) {
        if (isInCreativeMode(entity)) {
            goal.stop();
        }
        return isInCreativeMode(entity);
=======

    public static int secondsToTick(double seconds) {
        //System.out.println((int)seconds/60 + ", " + seconds%60);
        return (int) (seconds * 20);
    }

    public static int minutesToTick(double minutes, double seconds) {
        return secondsToTick(minutes*60 + seconds);
    }
    @Nullable
    public static Int2ObjectArrayMap trades() throws FileNotFoundException {
        File game = MinecraftClient.getInstance().runDirectory;
        File trades = new File(game, "trades.json");
        Scanner scanner = new Scanner(trades);
        try {

            FileReader tradesS = new FileReader(trades);
            while (scanner.hasNextLine()) {
                scanner.nextLine();
                String data = scanner.nextLine();
                System.out.println(data);
            }


            //JsonArray tradesArray = (JsonArray) tradesS.;
        } catch (IOException e) {
            MinecraftClient.getInstance().setCrashReport(new CrashReport("Damn", new Exception("WHAT")));
        }
        return null;
>>>>>>> Stashed changes
    }
}
