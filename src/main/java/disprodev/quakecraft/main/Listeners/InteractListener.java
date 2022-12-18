package disprodev.quakecraft.main.Listeners;

import disprodev.quakecraft.main.Main;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.entity.Snowball;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

public class InteractListener implements Listener {

    @EventHandler
    public void onInteract(PlayerInteractEvent event) {

        Player player = event.getPlayer();

        switch (event.getAction()) {

            case LEFT_CLICK_AIR:
            case LEFT_CLICK_BLOCK:

                if (player.getItemInHand().equals(Material.IRON_HOE)) {

                    Snowball snowball = player.getWorld().spawn(player.getLocation(), Snowball.class);

                    snowball.setVelocity(player.getLocation().getDirection().multiply(100));

                    //todo: donner un effet d'invi à la snowball

                    Bukkit.getScheduler().scheduleSyncRepeatingTask(Main.getInstance(), new BukkitRunnable() {
                        @Override
                        public void run() {

                            if (!snowball.isDead()) {

                                Entity particle = snowball.getWorld().spawn(snowball.getLocation(), EntityType.);


                            }

                        }
                    }, 0l, 100000000L);

                }

        }

    }

}
