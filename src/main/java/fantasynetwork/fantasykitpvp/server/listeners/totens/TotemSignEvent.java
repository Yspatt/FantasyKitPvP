package fantasynetwork.fantasykitpvp.server.listeners.totens;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.SignChangeEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class TotemSignEvent implements Listener {
    @EventHandler
    public void onSignChangeEvent(SignChangeEvent e){
        Player p = e.getPlayer();
        if (p.isOp()){
            if (e.getLine(0).equalsIgnoreCase("[REFIL]")){
                e.setLine(0,"§8[§c§lRefil§8]");
                e.setLine(2,"§7Clique aqui");
            }
        }
    }
    @EventHandler
    public void onInteract(PlayerInteractEvent e){
        Player p = e.getPlayer();
        if (e.getAction() == Action.RIGHT_CLICK_BLOCK){
            if (e.getClickedBlock().getType() == Material.WALL_SIGN || e.getClickedBlock().getType() == Material.SIGN_POST){
                Inventory inv = Bukkit.createInventory(null,6*9,"§5§lTOTEM ➜ §aPegue suas sopas.");
                for (int i = 0;i< inv.getSize();i++){
                    inv.addItem(new ItemStack(Material.MUSHROOM_SOUP));
                }
                p.openInventory(inv);
            }
        }
    }
    @EventHandler
    public void onClick(InventoryClickEvent e){
        if (e.getInventory().getName().equalsIgnoreCase("§5§lTOTEM ➜ §aPegue suas sopas.")){
            if (e.getCurrentItem() == null || e.getCurrentItem().getType() == Material.AIR || e.getSlotType() == null)return;
            Player p = (Player)e.getWhoClicked();
            if (e.getClickedInventory().equals(p.getInventory())){
                e.setCancelled(true);
            }else{

            }
        }
    }
}
