package disprodev.quakecraft.main;

import disprodev.quakecraft.main.Listeners.CancelListeners;
import disprodev.quakecraft.main.Listeners.InteractListener;
import disprodev.quakecraft.main.ScordBoard.FastBoard;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.plugin.java.JavaPlugin;

import java.awt.*;
import java.util.*;
import java.util.List;

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


    public Map<Player, Integer> getKill() {
        return Kill;
    }

    Map<Player,Integer>Kill = new HashMap();

    public List<Color> getParticleColor() {
        return ParticleColor;
    }

    List<Color>ParticleColor = new ArrayList<>();

    private static Main instance;
    public String prf= "§6Quake";
    @Override
    public void onEnable() {
        instance = this;
        msgconsole("§aOn",prf);
        setup();
        setupcolor();
        getServer().getPluginManager().registerEvents(this, this);

        getServer().getScheduler().runTaskTimer(this, () -> {
            for (FastBoard board : this.boards.values()) {
                updateBoard(board);
            }
        }, 0, 20);
    }

    private void setupcolor() {
        getParticleColor().add(Color.RED);
        getParticleColor().add(Color.BLACK);
        getParticleColor().add(Color.WHITE);
        getParticleColor().add(Color.YELLOW);
        getParticleColor().add(Color.ORANGE);
        getParticleColor().add(Color.green);
        getParticleColor().add(Color.DARK_GRAY);
        getParticleColor().add(Color.GRAY);
        getParticleColor().add(Color.pink);
        getParticleColor().add(Color.magenta);
        Bukkit.getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&', "§a Couleur généré"));








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

    public static Main getInstance() {
        return instance;
    }

    private void setup() {

        getServer().getPluginManager().registerEvents(new InteractListener(this), this);
        getServer().getPluginManager().registerEvents(new CancelListeners(this), this);

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
