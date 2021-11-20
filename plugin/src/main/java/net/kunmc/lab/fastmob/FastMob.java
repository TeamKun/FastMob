package net.kunmc.lab.fastmob;

import dev.kotx.flylib.FlyLib;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeInstance;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.event.vehicle.VehicleEnterEvent;
import org.bukkit.event.vehicle.VehicleExitEvent;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

public final class FastMob extends JavaPlugin {
    public static double maxEnderDragonDistance = 256.0;

    @Override
    public void onEnable() {
        FlyLib.create(this, builder -> {
            builder.command(new MainCommand());

            builder.listen(VehicleEnterEvent.class, e -> {
                if (e.getVehicle() instanceof LivingEntity) {
                    AttributeInstance attr = ((LivingEntity) e.getVehicle()).getAttribute(Attribute.GENERIC_MOVEMENT_SPEED);
                    if (attr != null) {
                        attr.setBaseValue(attr.getDefaultValue() * 0.5 * Config.otherMobs);
                    }
                }
            });

            builder.listen(VehicleExitEvent.class, e -> {
                if (e.getVehicle() instanceof LivingEntity) {
                    AttributeInstance attr = ((LivingEntity) e.getVehicle()).getAttribute(Attribute.GENERIC_MOVEMENT_SPEED);
                    if (attr != null) {
                        attr.setBaseValue(attr.getDefaultValue() * 0.5);
                    }
                }
            });

            new BukkitRunnable() {
                @Override
                public void run() {
                    World world = Bukkit.getWorlds().stream()
                            .filter(w -> w.getEnvironment().equals(World.Environment.THE_END))
                            .findFirst()
                            .get();

                    world.getLivingEntities().stream()
                            .filter(e -> e.getType().equals(EntityType.ENDER_DRAGON))
                            .forEach(e -> {
                                Location loc = e.getLocation();
                                if (loc.toVector().length() > maxEnderDragonDistance) {
                                    e.teleportAsync(toUnit(loc.toVector()).multiply(maxEnderDragonDistance * 0.8).toLocation(loc.getWorld()));
                                }
                            });
                }
            }.runTaskTimerAsynchronously(this, 0, 1);
        });
    }

    private Vector toUnit(Vector vector) {
        return vector.divide(new Vector(vector.length(), vector.length(), vector.length()));
    }

    @Override
    public void onDisable() {
    }
}
