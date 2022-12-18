package disprodev.quakecraft.main.manager;

import disprodev.quakecraft.main.Main;
import disprodev.quakecraft.main.utils.ItemBuilder;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;

public class GameManager {

    private Player player;

    private int timer = 3;

    public GameManager(Player player) {

        this.player = player;

    }

    public void loadGame() {

        Main.getInstance().getPlayers().add(player);

        player.getInventory().setItem(0, new ItemBuilder(Material.IRON_HOE)
                .setName("§e§lPistolet")
                .toItemStack());
        player.updateInventory();

        player.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 2, 255, true, false));

        player.playSound(player.getLocation(), Sound.PORTAL_TRAVEL, 10000, 1f);

    }

}
