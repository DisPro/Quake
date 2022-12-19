package disprodev.quakecraft.main.Listeners;

import disprodev.quakecraft.main.Main;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

import java.util.List;

public class OnQuit implements Listener {
    private final Main main;
    public OnQuit(Main main) {
        this.main = main;
    }

    @EventHandler
    public void OnQuit(PlayerQuitEvent e){
        Player p = e.getPlayer();
        if(main.getPlayers().contains(p)){
            main.getPlayers().remove(p);
        }
    }
}
