package disprodev.quakecraft.main.utils;

import net.minecraft.server.v1_8_R3.IChatBaseComponent;
import net.minecraft.server.v1_8_R3.PacketPlayOutChat;
import net.minecraft.server.v1_8_R3.PacketPlayOutPlayerListHeaderFooter;
import net.minecraft.server.v1_8_R3.PacketPlayOutTitle;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;

import java.lang.reflect.Field;

public class TitleManager {

    public static void setPlayerList(Player player, String header, String footer) {
        IChatBaseComponent hj = IChatBaseComponent.ChatSerializer.a("{\"text\":\"" + header + "\"}");
        IChatBaseComponent fj = IChatBaseComponent.ChatSerializer.a("{\"text\":\"" + footer + "\"}");
        PacketPlayOutPlayerListHeaderFooter packet = (PacketPlayOutPlayerListHeaderFooter) construcHeaderAndFooterPacket(
                hj, fj);
        ((CraftPlayer) player).getHandle().playerConnection.sendPacket(packet);
    }

    private static Object construcHeaderAndFooterPacket(Object header, Object footer) {
        try {
            Object packet = PacketPlayOutPlayerListHeaderFooter.class.newInstance();
            if (header != null) {
                Field field = PacketPlayOutPlayerListHeaderFooter.class.getDeclaredField("a");
                field.setAccessible(true);
                field.set(packet, header);
                field.setAccessible(false);
            }
            if (footer != null) {
                Field field = PacketPlayOutPlayerListHeaderFooter.class.getDeclaredField("b");
                field.setAccessible(true);
                field.set(packet, footer);
                field.setAccessible(false);
            }
            return packet;
        } catch (InstantiationException | IllegalAccessException | NoSuchFieldException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static final void sendTitle(Player player, String msgTitle, String msgSubTitle, int ticks) {
        IChatBaseComponent chatTitle = IChatBaseComponent.ChatSerializer.a("{\"text\":\"" + msgTitle + "\"}");
        IChatBaseComponent chatSubTitle = IChatBaseComponent.ChatSerializer.a("{\"text\":\"" + msgSubTitle + "\"}");
        PacketPlayOutTitle p = new PacketPlayOutTitle(PacketPlayOutTitle.EnumTitleAction.TITLE, chatTitle);
        PacketPlayOutTitle p2 = new PacketPlayOutTitle(PacketPlayOutTitle.EnumTitleAction.SUBTITLE, chatSubTitle);
        ((CraftPlayer) player).getHandle().playerConnection.sendPacket(p);
        ((CraftPlayer) player).getHandle().playerConnection.sendPacket(p2);
        sendTime(player, ticks);
    }

    public static final void sendTime(Player player, int ticks) {
        PacketPlayOutTitle p = new PacketPlayOutTitle(PacketPlayOutTitle.EnumTitleAction.TIMES, null, 20, ticks, 20);
        ((CraftPlayer) player).getHandle().playerConnection.sendPacket(p);

    }

    public static final void sendActionBar(Player player, String message) {
        IChatBaseComponent cbc = IChatBaseComponent.ChatSerializer.a("{\"text\" :\"" + message + "\"}");
        PacketPlayOutChat ppoc = new PacketPlayOutChat(cbc, (byte) 2);
        ((CraftPlayer) player).getHandle().playerConnection.sendPacket(ppoc);
    }
}