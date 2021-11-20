package net.kunmc.lab.fastmob;

import dev.kotx.flylib.command.Command;
import dev.kotx.flylib.command.CommandContext;

public class MainCommand extends Command {
    public MainCommand() {
        super("fastmob");

        children(new Command("enderDragonSpeed") {
            {
                usage(builder -> {
                    builder.doubleArgument("speed")
                            .executes(ctx -> {
                                Config.enderDragonSpeed = ((Double) ctx.getTypedArgs().get(0));
                                ctx.success("enderDragonSpeedを" + Config.enderDragonSpeed + "に変更しました.");
                            });
                });
            }
        });

        children(new Command("otherMobsSpeed") {
            {
                usage(builder -> {
                    builder.doubleArgument("speed")
                            .executes(ctx -> {
                                Config.otherMobsSpeed = ((Double) ctx.getTypedArgs().get(0));
                                ctx.success("otherMobsSpeedを" + Config.otherMobsSpeed + "に変更しました.");
                            });
                });
            }
        });

        children(new Command("check") {
            @Override
            public void execute(CommandContext ctx) {
                ctx.success("enderDragonSpeed: " + Config.enderDragonSpeed);
                ctx.success("otherMobsSpeed: " + Config.otherMobsSpeed);
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
