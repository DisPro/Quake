package disprodev.quakecraft.main.Listeners;

import disprodev.quakecraft.main.Main;

import disprodev.quakecraft.main.utils.Random;
import net.minecraft.server.v1_8_R3.PacketPlayOutEntityDestroy;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Player;
import org.bukkit.entity.Snowball;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.metadata.MetadataValue;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;
import xyz.xenondevs.particle.ParticleBuilder;
import xyz.xenondevs.particle.ParticleEffect;

import java.awt.*;

public class InteractListener implements Listener {
    private Main main;


    public InteractListener(Main main) {
        this.main = main;
    }

    @EventHandler
    public void onInteract(PlayerInteractEvent e) {

        if (!(e.getAction() == Action.RIGHT_CLICK_AIR)) return;
        if (!(e.getItem().getType() == Material.IRON_HOE)) return;
        Player pla = e.getPlayer();
        Snowball s = e.getPlayer().launchProjectile(Snowball.class);
        for(Player p : Bukkit.getOnlinePlayers()){
            PacketPlayOutEntityDestroy packet = new PacketPlayOutEntityDestroy(new int[] {s.getEntityId()});
            ((CraftPlayer)p).getHandle().playerConnection.sendPacket(packet);
        }

        Bukkit.getScheduler().scheduleSyncRepeatingTask(main, new BukkitRunnable() {
            public void run() {
                for(Player p : Bukkit.getOnlinePlayers()){
                    PacketPlayOutEntityDestroy packet = new PacketPlayOutEntityDestroy(new int[] {s.getEntityId()});
                    ((CraftPlayer)p).getHandle().playerConnection.sendPacket(packet);
                }
                if (!s.isDead()) {
                    new ParticleBuilder(ParticleEffect.REDSTONE, s.getLocation())
                            .setColor(main.getParticleColor().get(new Random().Random()))
                            .setOffsetY(1f)
                            .setSpeed(0.1f)
                            .display();
                }
            }
        }, 0, 1);

    }
}
/*
        Player player = event.getPlayer();
        switch (event.getAction()) {

            case LEFT_CLICK_AIR:
            case LEFT_CLICK_BLOCK:

                if (player.getItemInHand().equals(Material.IRON_HOE)) {

                    Snowball snowball = player.getWorld().spawn(player.getLocation(), Snowball.class);

                    snowball.setVelocity(player.getLocation().getDirection().multiply(100));

                    //todo: donner un effet d'invi à la snowball

                    Bukkit.getScheduler().scheduleSyncRepeatingTask((main.getInstance()), new BukkitRunnable() {
                        @Override
                        public void run() {

                            if (!snowball.isDead()) {

                                ParticleEffect.FLAME.display(snowball.getLocation());

                            }

                        }
                    }, 0l, 100000000L);

                }

        }

    }*/


