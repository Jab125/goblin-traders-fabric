package net.hat.gt.compat;

import ladysnake.requiem.api.v1.RequiemPlugin;

public class RequiemCompat implements RequiemPlugin {
    private static void requiemCompat() {
    }

    @Override
    public void onRequiemInitialize() {
        RequiemPlugin.super.onRequiemInitialize();
    }
}
