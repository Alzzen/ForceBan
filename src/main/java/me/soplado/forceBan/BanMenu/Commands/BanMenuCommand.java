package me.soplado.forceBan.BanMenu.Commands;

import me.soplado.forceBan.BanImplements.BanInventory;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class BanMenuCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage(ChatColor.RED + "Este comando solo puede ser usado por jugadores.");
            return true;
        }

        Player player = (Player) sender;

        // Obtener la lista de jugadores baneados manualmente
        List<String> bannedPlayersList = new ArrayList<>();

        // Obtener todos los jugadores baneados usando BanList
        Bukkit.getBanList(org.bukkit.BanList.Type.NAME).getBanEntries().forEach(banEntry -> {
            bannedPlayersList.add(banEntry.getTarget()); // Obtener el nombre del jugador baneado
        });

        // Abrir el menú con los jugadores baneados
        if (bannedPlayersList.isEmpty()) {
            player.sendMessage(ChatColor.RED + "No hay jugadores baneados actualmente.");
        } else {
            BanInventory.openBanMenu(player, bannedPlayersList, 0); // Abrir la página inicial
        }
        return true;
    }
}