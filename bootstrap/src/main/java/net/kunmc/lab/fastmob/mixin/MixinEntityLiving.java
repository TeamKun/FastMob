package net.kunmc.lab.fastmob.mixin;

import net.kunmc.lab.fastmob.Config;
import net.minecraft.server.v1_16_R3.*;
import org.bukkit.craftbukkit.v1_16_R3.entity.CraftEntity;
import org.bukkit.entity.EnderDragon;
import org.bukkit.entity.Player;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(value = EntityLiving.class, remap = false)
public abstract class MixinEntityLiving extends Entity {
    public MixinEntityLiving(EntityTypes<?> entitytypes, World world) {
        super(entitytypes, world);
    }

    @Override
    public void move(EnumMoveType type, Vec3D vec) {
        CraftEntity e = getBukkitEntity();
        if (e instanceof Player) {
            super.move(type, vec);
            return;
        }

        if (e instanceof EnderDragon) {
            super.move(type, vec.d(Config.enderdragon, 1.0, Config.enderdragon));
            return;
        }

        super.move(type, vec.d(Config.others, 1.0, Config.others));
    }
}
