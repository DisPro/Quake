package disprodev.quakecraft.main;

import net.md_5.bungee.api.ChatColor;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.List;

public final class Main extends JavaPlugin {
    public List<Player> getPlayers() {
        return players;
    }

    public void setPlayers(List<Player> players) {
        this.players = players;
    }

    List<Player>players = new ArrayList<>();
    private Main instance;
    public String prf= "§eQuake";
    @Override
    public void onEnable() {
        instance = this;
        msgconsole("§aOn",prf);
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
}
