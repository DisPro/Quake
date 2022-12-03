package disprodev.quakecraft.main;

import disprodev.quakecraft.main.ScordBoard.FastBoard;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.*;

public final class Main extends JavaPlugin implements Listener {
    public void setStat(GStats stat) {
        this.stat = stat;
    }

    public GStats getStat() {
        return stat;
    }

    public List<Player> getPlayers() {
        return players;
    }
    private GStats stat;

    public void setPlayers(List<Player> players) {
        this.players = players;
    }
    private final Map<UUID, FastBoard> boards = new HashMap<>();
    List<Player>players = new ArrayList<>();
    private Main instance;
    public String prf= "§6Quake";
    @Override
    public void onEnable() {
        instance = this;
        msgconsole("§aOn",prf);
        getServer().getPluginManager().registerEvents(this, this);

        getServer().getScheduler().runTaskTimer(this, () -> {
            for (FastBoard board : this.boards.values()) {
                updateBoard(board);
            }
        }, 0, 20);
    }

    private void msgconsole(String s,String n) {
        Bukkit.getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&', "&5=========="+ n+"§5=========="));
        Bukkit.getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&', "&eCeci est le plugin de DisPro"));
        Bukkit.getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&', "§e"));
        Bukkit.getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&', "§eEtat du plugin :" + s));
        Bukkit.getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&', "&5=========="+ n+"§5=========="));
    }
    @Override
    public void onDisable() {
        msgconsole("§cOFF",prf);
    }
    @EventHandler
    public void onJoin(PlayerJoinEvent e) {
        Player player = e.getPlayer();

        FastBoard board = new FastBoard(player);

        board.updateTitle(prf);

        this.boards.put(player.getUniqueId(), board);
    }

    @EventHandler
    public void onQuit(PlayerQuitEvent e) {
        Player player = e.getPlayer();

        FastBoard board = this.boards.remove(player.getUniqueId());

        if (board != null) {
            board.delete();
        }
    }

    private void updateBoard(FastBoard board) {
        board.updateLines(
                "",
                "§6§lProfil",
                "   §6Pseudo §f» §f" + board.getPlayer().getName(),
                "   §6Stats §f» Loadings...",
                "",
                "§6§lServeur",
                "   §6Connectés §f» §f" + getPlayers().size(),
                "",
                prf
        );
    }

}
