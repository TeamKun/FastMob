package net.kunmc.lab.fastmob;

import dev.kotx.flylib.command.Command;
import dev.kotx.flylib.command.CommandContext;

public class MainCommand extends Command {
    public MainCommand() {
        super("fastmob");

        children(new Command("enderdragon") {
            {
                usage(builder -> {
                    builder.doubleArgument("speed")
                            .executes(ctx -> {
                                Config.enderdragon = ((Double) ctx.getTypedArgs().get(0));
                                ctx.success("enderdragonのspeedを" + Config.enderdragon + "に変更しました.");
                            });
                });
            }
        });

        children(new Command("otherMobs") {
            {
                usage(builder -> {
                    builder.doubleArgument("speed")
                            .executes(ctx -> {
                                Config.otherMobs = ((Double) ctx.getTypedArgs().get(0));
                                ctx.success("otherMobsのspeedを" + Config.otherMobs + "に変更しました.");
                            });
                });
            }
        });

        children(new Command("check") {
            @Override
            public void execute(CommandContext ctx) {
                ctx.success("enderdragon: " + Config.enderdragon);
                ctx.success("otherMobs: " + Config.otherMobs);
                ctx.success("maxEnderDragonDistance: " + FastMob.maxEnderDragonDistance);
            }
        });

        children(new Command("maxEnderDragonDistance") {
            {
                usage(builder -> {
                    builder.doubleArgument("distance")
                            .executes(ctx -> {
                                FastMob.maxEnderDragonDistance = ((Double) ctx.getTypedArgs().get(0));
                                ctx.success("maxEnderDragonDistanceの値を" + FastMob.maxEnderDragonDistance + "に変更しました.");
                            });
                });
            }
        });
    }
}
