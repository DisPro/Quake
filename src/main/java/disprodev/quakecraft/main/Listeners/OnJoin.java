package disprodev.quakecraft.main.Listeners;

import disprodev.quakecraft.main.Main;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class OnJoin implements Listener {
    private Main main;


    public OnJoin(PlayerJoinEvent e){
        Player p = e.getPlayer();
        main.getPlayers().add(p);
        if()


    }

}
