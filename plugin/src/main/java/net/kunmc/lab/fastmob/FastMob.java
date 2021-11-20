package net.kunmc.lab.fastmob;

import dev.kotx.flylib.FlyLib;
import io.papermc.paper.event.entity.EntityMoveEvent;
import org.bukkit.Location;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeInstance;
import org.bukkit.entity.EnderDragon;
import org.bukkit.entity.LivingEntity;
import org.bukkit.event.vehicle.VehicleEnterEvent;
import org.bukkit.event.vehicle.VehicleExitEvent;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.util.Vector;

public final class FastMob extends JavaPlugin {
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

            builder.listen(EntityMoveEvent.class, e -> {
                LivingEntity entity = e.getEntity();
                if (!(entity instanceof EnderDragon)) {
                    return;
                }

                Location loc = entity.getLocation();
                if (loc.toVector().length() > 120.0) {
                    entity.teleport(toUnit(loc.toVector()).multiply(120.0).toLocation(loc.getWorld()));
                }
            });
        });
    }

    private Vector toUnit(Vector vector) {
        return vector.divide(vector);
    }

    @Override
    public void onDisable() {
    }
}
