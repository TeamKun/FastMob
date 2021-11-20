package net.kunmc.lab.fastmob;

import dev.kotx.flylib.FlyLib;
import org.bukkit.plugin.java.JavaPlugin;

public final class FastMob extends JavaPlugin {
    @Override
    public void onEnable() {
        FlyLib.create(this, builder -> {
            builder.command(new MainCommand());
        });
    }

    @Override
    public void onDisable() {
    }
}
