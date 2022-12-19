package disprodev.quakecraft.main.Listeners;

import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.FoodLevelChangeEvent;

public class CancelListeners implements Listener {

    @EventHandler
    public void onFood(FoodLevelChangeEvent event) {

        event.setCancelled(true);

    }

    @EventHandler
    public void onPlace(BlockPlaceEvent event) {

        event.setCancelled(true);

    }

    @EventHandler
    public void onBreak(BlockBreakEvent event) {

        event.setCancelled(true);

    }

    @EventHandler
    public void onDamage(EntityDamageByEntityEvent event) {

        Player player = (Player) event.getEntity();
        Entity snowball = event.getDamager();

        if (snowball.getType().equals(EntityType.SNOWBALL)) {

            //todo: kill le joueur

        } else {

            event.setCancelled(true);

        }

    }

}
