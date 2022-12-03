package disprodev.quakecraft.main.Listeners;

import disprodev.quakecraft.main.GStats;
import disprodev.quakecraft.main.Main;
import disprodev.quakecraft.main.ScordBoard.FastBoard;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class OnJoin implements Listener {
    private Main main;


    public OnJoin(PlayerJoinEvent e) {
        Player p = e.getPlayer();
        if(main.getPlayers().size() == 8 || main.getStat() != GStats.WAITING){
            p.setGameMode(GameMode.SPECTATOR);
            Location loc = new Location(p.getWorld(),50 ,139 ,42 ,4F ,9F );
            p.teleport(loc);
        }
        if (!main.getPlayers().contains(p)){
            main.getPlayers().add(p);
    }
    }
    private void updateBoard(FastBoard board) {
        board.updateLines(
                "",
                "§6§lProfil",
                "   §6Pseudo §f» §f" + board.getPlayer().getName(),
                "   §6Stats §f» §cAttente",
                "",
                "§6§lServeur",
                "   §6Connectés §f» §f" + main.getPlayers().size(),
                "",
                main.prf
        );


}


}
