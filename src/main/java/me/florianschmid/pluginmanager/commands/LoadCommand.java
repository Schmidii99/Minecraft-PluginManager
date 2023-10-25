package me.florianschmid.pluginmanager.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginManager;

import java.util.Objects;

public class LoadCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if (strings.length == 0) {
            commandSender.sendMessage(ChatColor.RED + "No plugin to load provided!");
            return false;
        }

        final PluginManager manager = Bukkit.getPluginManager();
        for (String pluginName : strings) {
            final Plugin plugin = manager.getPlugin(pluginName);
            if (Objects.isNull(plugin)) {
                commandSender.sendMessage(ChatColor.RED + "Plugin '" + ChatColor.BLUE + pluginName +"' not found!");
                continue;
            }

            manager.enablePlugin(plugin);
            commandSender.sendMessage(ChatColor.YELLOW + "Plugin '" + ChatColor.BLUE + pluginName +"' enabled");
        }

        return true;
    }
}