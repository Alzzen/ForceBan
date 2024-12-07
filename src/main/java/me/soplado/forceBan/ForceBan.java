package me.soplado.forceBan;

import me.soplado.forceBan.BanImplements.BanInventory;
import me.soplado.forceBan.BanMenu.Commands.BanMenuCommand;
import me.soplado.forceBan.UnbanCommand.UnbanAllCommand;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

public final class ForceBan extends JavaPlugin {

    @Override
    public void onEnable() {
        registerCommands();
        registerEvents();

        Bukkit.getConsoleSender().sendMessage(
                ChatColor.DARK_GRAY + "("
                        + ChatColor.DARK_GREEN + "FBAN"
                        + ChatColor.DARK_GRAY + "/"
                        + ChatColor.GREEN + "ON"
                        + ChatColor.DARK_GRAY + ")");
    }

    @Override
    public void onDisable() {
        Bukkit.getConsoleSender().sendMessage(
                ChatColor.DARK_GRAY + "("
                        + ChatColor.DARK_RED + "FBAN"
                        + ChatColor.DARK_GRAY + "/"
                        + ChatColor.RED + "OFF"
                        + ChatColor.DARK_GRAY + ")");
    }

    public void registerCommands() {
        this.getCommand("unbanall").setExecutor(new UnbanAllCommand());
        this.getCommand("banmenu").setExecutor(new BanMenuCommand());
    }
    public void registerEvents(){
        getServer().getPluginManager().registerEvents((Listener) new BanInventory(), this);

    }

}