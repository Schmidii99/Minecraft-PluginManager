package me.florianschmid.pluginmanager;

import me.florianschmid.pluginmanager.commands.*;
import org.bukkit.plugin.java.JavaPlugin;

public final class PluginManager extends JavaPlugin {

    @Override
    public void onEnable() {
        // Plugin startup logic

        this.getCommand("unload").setExecutor(new UnloadCommand());
        this.getCommand("load").setExecutor(new LoadCommand());

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
