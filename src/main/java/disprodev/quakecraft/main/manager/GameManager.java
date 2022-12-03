package disprodev.quakecraft.main.manager;

import disprodev.quakecraft.main.Main;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

public class GameManager {

    private Player player;

    private int timer = 3;

    public GameManager(Player player) {

        this.player = player;

    }

    public void loadGame() {

        Main.getInstance().getStartingPlayer().add(player);
        Bukkit.getScheduler().runTaskLater(Main.getInstance(), new BukkitRunnable() {
            @Override
            public void run() {

                timer--;
                if (timer == 0) {

                    this.cancel();
                    timer = 3;
                    Main.getInstance().getStartingPlayer().remove(player);

                }

            }
        }, 0L);

    }

}
