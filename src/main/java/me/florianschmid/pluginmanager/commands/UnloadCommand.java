package me.florianschmid.pluginmanager.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginManager;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class UnloadCommand implements TabExecutor {
    final PluginManager manager = Bukkit.getPluginManager();

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if (strings.length == 0) {
            commandSender.sendMessage(ChatColor.RED + "No plugin to unload provided!");
            return false;
        }

        for (String pluginName : strings) {
            final Plugin plugin = manager.getPlugin(pluginName);
            if (Objects.isNull(plugin)) {
                commandSender.sendMessage(ChatColor.RED + "Plugin '" + ChatColor.BLUE + pluginName + ChatColor.RED +"' not found!");
                continue;
            }

            manager.disablePlugin(plugin);
            commandSender.sendMessage(ChatColor.YELLOW + "Plugin '" + ChatColor.BLUE + pluginName + ChatColor.YELLOW +"' disabled");
        }

        return true;
    }

    @Override
    public List<String> onTabComplete(CommandSender commandSender, Command command, String s, String[] strings) {
        ArrayList<String> availablePlugins = new ArrayList<String>();
        Plugin[] plugins = manager.getPlugins();

        for (final Plugin plugin : plugins) {
            // checks if plugin is disabled and not added to the command yet
            if (plugin.isEnabled() && !Arrays.toString(strings).contains(plugin.getName())) {
                availablePlugins.add(plugin.getName());
            }
        }

        return availablePlugins;
    }
}
