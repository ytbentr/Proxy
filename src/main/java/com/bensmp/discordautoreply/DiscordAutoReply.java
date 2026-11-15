package com.bensmp.discordautoreply;

import io.papermc.paper.event.player.AsyncChatEvent;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.event.ClickEvent;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.format.TextDecoration;
import net.kyori.adventure.text.serializer.plain.PlainTextComponentSerializer;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.regex.Pattern;

public final class DiscordAutoReply extends JavaPlugin implements Listener {

    private static final String DISCORD_URL = "https://discord.gg/TrhuutnjF";
    private static final Component REPLY = Component.text("[BenSmP]Discord: ")
            .append(Component.text(DISCORD_URL)
                    .color(NamedTextColor.AQUA)
                    .decorate(TextDecoration.UNDERLINED)
                    .clickEvent(ClickEvent.openUrl(DISCORD_URL)));

    private static final Pattern DISCORD_WORD =
            Pattern.compile("(?iu)(?<![\\p{L}\\p{N}_])(dc|discord)(?![\\p{L}\\p{N}_])");

    @Override
    public void onEnable() {
        getServer().getPluginManager().registerEvents(this, this);
        getLogger().info("DiscordAutoReply enabled.");
    }

    @Override
    public void onDisable() {
        getLogger().info("DiscordAutoReply disabled.");
    }

    @EventHandler(priority = EventPriority.MONITOR, ignoreCancelled = true)
    public void onChat(AsyncChatEvent event) {
        String message = PlainTextComponentSerializer.plainText().serialize(event.message());

        if (!DISCORD_WORD.matcher(message).find()) {
            return;
        }

        Runnable broadcast = () -> Bukkit.broadcast(REPLY);

        // AsyncChatEvent normally runs asynchronously. Send chat output on the
        // main server thread to keep Bukkit API access thread-safe.
        if (event.isAsynchronous()) {
            Bukkit.getScheduler().runTask(this, broadcast);
        } else {
            broadcast.run();
        }
    }
}
