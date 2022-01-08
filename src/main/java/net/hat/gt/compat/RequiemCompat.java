package net.hat.gt.compat;

import ladysnake.requiem.api.v1.RequiemApi;
import ladysnake.requiem.api.v1.RequiemPlugin;
import ladysnake.requiem.api.v1.entity.ability.*;
import net.hat.gt.entities.GoblinTraderEntity;
import net.hat.gt.init.ModEntities;
import org.jetbrains.annotations.NotNull;

public class RequiemCompat implements RequiemPlugin {
    private static void requiemCompat() {
    }

    @Override
    public void onRequiemInitialize() {
        RequiemPlugin.super.onRequiemInitialize();
    }
}
