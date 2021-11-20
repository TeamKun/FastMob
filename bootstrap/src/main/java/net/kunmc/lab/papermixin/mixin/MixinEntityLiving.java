package net.kunmc.lab.papermixin.mixin;

import net.minecraft.server.v1_16_R3.*;
import org.bukkit.craftbukkit.v1_16_R3.entity.CraftEntity;
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
        } else {
            super.move(type, vec.d(8.0, 1.0, 8.0));
        }
    }
}
