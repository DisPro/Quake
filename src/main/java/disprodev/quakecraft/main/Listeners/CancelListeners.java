package disprodev.quakecraft.main.Listeners;

import disprodev.quakecraft.main.Main;
import disprodev.quakecraft.main.utils.TitleManager;
import org.bukkit.Bukkit;
import org.bukkit.Instrument;
import org.bukkit.Note;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.FoodLevelChangeEvent;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import xyz.xenondevs.particle.ParticleBuilder;
import xyz.xenondevs.particle.ParticleEffect;

import java.awt.*;

public class CancelListeners implements Listener {
    private Main main;

    public CancelListeners(Main main) {
        this.main = main;
    }

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

    /*
        @EventHandler
        public void onProjectileHit(ProjectileHitEvent e) {
            Projectile p = e.getEntity();
            for (Entity entity : p.g) {
                if (entity instanceof Player) {
                    Player player = (Player) entity;
                    Player shooter = (Player) e.getEntity().getShooter();
                    Bukkit.broadcastMessage("Shooter = " + shooter);
                    Bukkit.broadcastMessage("Damager = " + player);

                    player.damage(100);
                    if (!(player == e.getEntity().getShooter())) {
                        main.getKill().get(shooter).intValue();

                        Main.getInstance().getKill().put(shooter, main.getKill().get(shooter).intValue() - 1);
                        TitleManager.sendActionBar((Player) e.getEntity().getShooter(), "§a Vous avez tué §6" + player.getName() + "§a il vous reste " + main.getKill().get(shooter) + "§7/§e25 §a kill");
                    }
                }
            }
        }*/
    @EventHandler(priority = EventPriority.LOW, ignoreCancelled = false)
    public void onSnowballHit(EntityDamageByEntityEvent event) {
        Entity damaged = event.getEntity();
        Entity damageEntity = event.getDamager();

        if (damaged instanceof Player)
            if (damageEntity instanceof Snowball) {
                Snowball snowball = (Snowball) damageEntity;
                LivingEntity entityThrower = (LivingEntity) snowball.getShooter();
                if (entityThrower instanceof Player) {
                    Player playerThrower = (Player) entityThrower;
                    Player playerHit = (Player) damaged;
                    Bukkit.broadcastMessage("Je suis aigris " + playerThrower);
                    Bukkit.broadcastMessage(Main.getInstance().getKill().get(playerThrower).intValue() + "");

                        Bukkit.broadcastMessage("Je suis aigris  1" + playerThrower);

                        playerHit.damage(100);
                        Bukkit.broadcastMessage("Je suis aigris 2" + playerThrower);

                        Bukkit.broadcastMessage(Main.getInstance().getKill().get(playerThrower).intValue() + "");
                        Main.getInstance().getKill().put(playerThrower, Main.getInstance().getKill().get(playerThrower).intValue() + 1);
                        Bukkit.broadcastMessage(Main.getInstance().getKill().get(playerThrower).intValue() + "");

                        TitleManager.sendActionBar(playerThrower, "§a Vous avez tué §6" + playerHit.getName() + "§a il vous reste " + Main.getInstance().getKill().get(playerThrower) + "§7/§e25 §a kill");
                    }
                }
            }

}


