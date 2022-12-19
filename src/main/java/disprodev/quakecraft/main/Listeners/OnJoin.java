package disprodev.quakecraft.main.Listeners;

import disprodev.quakecraft.main.GStats;
import disprodev.quakecraft.main.Main;
import disprodev.quakecraft.main.ScordBoard.FastBoard;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import xyz.xenondevs.particle.ParticleEffect;

public class OnJoin implements Listener {
    private final Main main;
    public OnJoin(Main main) {
        this.main = main;
    }
    @EventHandler
    public void OnJoin(PlayerJoinEvent e) {
        ParticleEffect.FLAME.display(e.getPlayer().getLocation());

        Bukkit.broadcastMessage("test");
        Player p = e.getPlayer();
        if (!main.getPlayers().contains(p)){
            main.getPlayers().add(p);
        }
        if (main.getPlayers().size() == 8 || main.getStat() == GStats.WAITING|| main.getStat() == GStats.STARTING) {
            p.setGameMode(GameMode.SPECTATOR);
            Location loc = new Location(p.getWorld(), 50, 139, 42, 4F, 9F);
            p.teleport(loc);
        }


        FastBoard board = new FastBoard(e.getPlayer());

        if (!(main.getPlayers().size() == 8 || main.getPlayers().size() == 7 || main.getPlayers().size() == 6 || main.getPlayers().size() == 5)) {
            board.updateTitle(main.prf);
            Bukkit.broadcastMessage("test 2 ");
            board.updateLines(
                    "§6§lProfil",
                    "   §6Pseudo §f» §f" + board.getPlayer().getName(),
                    "",
                    "§6§lGame",
                    "   §6Statut §f» §cAttente...",
                    "   §6Joueur §f» §c" + main.getPlayers().size() + "§e/8",
                    "",
                    main.prf
            );
            Bukkit.broadcastMessage("test 3 ");

        }
        if (main.getPlayers().size() == 1 ) {
            main.setStat(GStats.STARTING);
        }
    if(main.getStat() == GStats.STARTING) {
        Bukkit.broadcastMessage("test 4 ");
        board.updateTitle(main.prf);
        board.updateLines(
                "§6§lProfil",
                "   §6Pseudo §f» §f" + board.getPlayer().getName(),
                "",
                "§6§lGame",
                "   §6Statut §f» §bLancement...",
                "   §6Joueur §f» §c" + main.getPlayers().size() + "§e/8",
                "",
                main.prf
        );
    }

    }

}

