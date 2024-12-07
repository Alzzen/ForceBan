package me.soplado.forceBan.BanImplements;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.List;

public class BanInventory {

    public static void openBanMenu(Player player, List<String> bannedPlayersList, int page) {
        Inventory inventory = Bukkit.createInventory(null, 54, ChatColor.RED + "Lista de Baneados");

        // Calcular el rango de jugadores a mostrar en la página actual
        int start = page * 45;
        int end = Math.min(start + 45, bannedPlayersList.size());

        for (int i = start; i < end; i++) {
            String bannedPlayer = bannedPlayersList.get(i);

            // Crear una cabeza de jugador para cada jugador baneado
            ItemStack skull = new ItemStack(Material.SKULL_ITEM, 1, (short) 3); // Cabeza de jugador
            ItemMeta meta = skull.getItemMeta();
            if (meta != null) {
                meta.setDisplayName(ChatColor.YELLOW + bannedPlayer);
                skull.setItemMeta(meta);
            }
            inventory.addItem(skull);
        }

        // Flecha para la página siguiente
        if (end < bannedPlayersList.size()) {
            ItemStack nextPage = new ItemStack(Material.ARROW);
            ItemMeta meta = nextPage.getItemMeta();
            if (meta != null) {
                meta.setDisplayName(ChatColor.GREEN + "Siguiente Página");
                nextPage.setItemMeta(meta);
            }
            inventory.setItem(53, nextPage); // Última casilla
        }

        // Flecha para la página anterior
        if (page > 0) {
            ItemStack previousPage = new ItemStack(Material.ARROW);
            ItemMeta meta = previousPage.getItemMeta();
            if (meta != null) {
                meta.setDisplayName(ChatColor.GREEN + "Página Anterior");
                previousPage.setItemMeta(meta);
            }
            inventory.setItem(45, previousPage); // Primera casilla de la última fila
        }

        // Abrir el inventario para el jugador
        player.openInventory(inventory);
    }
}