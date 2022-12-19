package disprodev.quakecraft.main.Listeners;

import disprodev.quakecraft.main.Main;
import org.bukkit.Bukkit;
import org.bukkit.Effect;
import org.bukkit.Material;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.entity.Snowball;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;
import xyz.xenondevs.particle.ParticleBuilder;
import xyz.xenondevs.particle.ParticleEffect;
import xyz.xenondevs.particle.data.color.NoteColor;
import xyz.xenondevs.particle.data.color.RegularColor;

public class InteractListener implements Listener {
    private Main main;

    public InteractListener(Main main) {
        this.main = main;
    }

    @EventHandler
    public void onInteract(PlayerInteractEvent e) {

        if (!(e.getAction() == Action.RIGHT_CLICK_AIR)) return;
        if (!(e.getItem().getType() == Material.IRON_HOE)) return;
        Snowball s = e.getPlayer().launchProjectile(Snowball.class);
        Bukkit.getScheduler().scheduleSyncRepeatingTask(main, new Runnable() {
            public void run() {
                if (!s.isDead()) {
                    ParticleEffect.FLAME.display(s.getLocation());
                }
            }
        }, 1, 200);

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


