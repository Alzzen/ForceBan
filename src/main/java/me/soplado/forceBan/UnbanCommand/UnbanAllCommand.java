package me.soplado.forceBan.UnbanCommand;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import java.util.Set;

public class UnbanAllCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        // Verificar que el comando sea ejecutado desde la consola
        if (!(sender instanceof org.bukkit.command.ConsoleCommandSender)) {
            sender.sendMessage(ChatColor.RED + "Este comando solo puede ser ejecutado desde la consola.");
            return true;
        }

        // Obtener la lista de baneos
        Set<String> bannedPlayers = Bukkit.getBanList(org.bukkit.BanList.Type.NAME).getBanEntries()
                .stream()
                .map(entry -> entry.getTarget())
                .collect(java.util.stream.Collectors.toSet());

        if (bannedPlayers.isEmpty()) {
            sender.sendMessage(ChatColor.GREEN + "No hay jugadores baneados.");
            return true;
        }

        // Desbanear a todos los jugadores
        for (String playerName : bannedPlayers) {
            Bukkit.getBanList(org.bukkit.BanList.Type.NAME).pardon(playerName);

            // Anunciar en el chat público
            Bukkit.broadcastMessage(ChatColor.YELLOW + "El jugador " + ChatColor.GREEN + playerName
                    + ChatColor.YELLOW + " ha sido desbaneado.");
        }

        sender.sendMessage(ChatColor.GREEN + "Todos los jugadores han sido desbaneados.");
        return true;
    }
}