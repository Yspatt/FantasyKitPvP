package fantasynetwork.fantasykitpvp.utils.item;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

import org.bukkit.Bukkit;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.EnchantmentStorageMeta;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.LeatherArmorMeta;
import org.bukkit.inventory.meta.SkullMeta;

public class ItemStackBuilder {
    private ItemStack item;
    private ItemMeta meta;
    private Color color;
    private EnchantmentStorageMeta storage;
    private List<String> lore;
    private boolean glow;
    private SkullMeta skullMeta;

    private static HashMap<ItemStack, InventoryAction> inventoryActions = new HashMap<>();

    public ItemStackBuilder(final ItemStack item) {
        this.glow = false;
        this.item = item;
        if (item.getType() == Material.ENCHANTED_BOOK) {
            this.storage = (EnchantmentStorageMeta) item.getItemMeta();
            this.lore = ((this.storage != null && this.storage.hasLore()) ? this.storage.getLore()
                    : new ArrayList<String>());
        } else if (item.getType() == Material.SKULL_ITEM || item.getType() == Material.SKULL) {
            this.skullMeta = (SkullMeta) item.getItemMeta();
            this.lore = ((this.skullMeta != null && this.skullMeta.hasLore()) ? this.skullMeta.getLore()
                    : new ArrayList<String>());
        } else {
            this.meta = item.getItemMeta();
            this.lore = ((this.meta != null && this.meta.hasLore()) ? this.meta.getLore() : new ArrayList<String>());
        }
    }

    public ItemStackBuilder(final Material material) {
        this(new ItemStack(material));
    }

    public ItemStackBuilder setLore(List<String> lore) {
        this.lore = lore;
        return this;
    }

    public ItemStackBuilder setType(final Material type) {
        this.item.setType(type);
        return this;
    }

    public ItemStackBuilder setOwner(final String owner) {
        if (this.item.getType() == Material.SKULL_ITEM || this.item.getType() == Material.SKULL) {
            this.skullMeta.setOwner(owner);
            return this;
        }
        return this;
    }

    public ItemStackBuilder setColor(final Color color){
        LeatherArmorMeta meta = (LeatherArmorMeta) this.meta;
        meta.setColor(color);
        return this;
    }

    public ItemStackBuilder setName(final String name) {
        if (this.item.getType() == Material.ENCHANTED_BOOK) {
            this.storage.setDisplayName(name);
            return this;
        }
        if (this.item.getType() == Material.SKULL_ITEM || this.item.getType() == Material.SKULL) {
            this.skullMeta.setDisplayName(name);
            return this;
        }
        this.meta.setDisplayName(name);
        return this;
    }

    public ItemStackBuilder addLore(final String... l) {
        for (final String x : l) {
            this.lore.add(x);
        }
        return this;
    }

    public ItemStackBuilder addLoreList(final List<String> l) {
        for (final String s : l) {
            this.lore.add(s.replace("&", "�"));
        }
        return this;
    }

    public ItemStackBuilder addStoredEnchantment(final Enchantment e, final int level) {
        this.storage.addStoredEnchant(e, level, true);
        return this;
    }

    public ItemStackBuilder addEnchantment(final Enchantment e, final int level) {
        this.meta.addEnchant(e, level, true);
        return this;
    }

    public ItemStackBuilder setDurability(final int durability) {
        this.item.setDurability((short) durability);
        return this;
    }

    public ItemStackBuilder setAmount(final int amount) {
        this.item.setAmount(amount);
        return this;
    }

    public ItemStackBuilder clearLore() {
        this.lore = new ArrayList<>();
        return this;
    }

    public ItemStackBuilder replaceLore(final String oldLore, final String newLore) {
        for (int i = 0; i < this.lore.size(); ++i) {
            if (this.lore.get(i).contains(oldLore)) {
                this.lore.remove(i);
                this.lore.add(i, newLore);
                break;
            }
        }
        return this;
    }

    public ItemStack build() {
        if (this.item.getType() == Material.ENCHANTED_BOOK) {
            if (!this.lore.isEmpty()) {
                this.storage.setLore(this.lore);
                this.lore.clear();
            }
            this.item.setItemMeta((ItemMeta) this.storage);
            return this.item;
        }
        if (this.item.getType() == Material.SKULL || this.item.getType() == Material.SKULL_ITEM) {
            if (!this.lore.isEmpty()) {
                this.skullMeta.setLore(this.lore);
                this.lore.clear();
            }
            this.item.setItemMeta((ItemMeta) this.skullMeta);
            return this.item;
        }
        if (!this.lore.isEmpty()) {
            this.meta.setLore(this.lore);
            this.lore.clear();
        }
        this.item.setItemMeta(this.meta);
        if (this.glow) {
            this.setGlow(this.glow);
        }
        return this.item;
    }

    public ItemStackBuilder addInventoryAction(Runnable runnable, boolean cancelEvent, Player player,
                                               Inventory inventory) {
        inventoryActions.put(this.build(), new InventoryAction("" + inventoryActions.size() + 1, runnable, this.build(),
                player, cancelEvent, inventory));
        return this;
    }

    public ItemStackBuilder setGlow(boolean glow) {
        if (glow) {
            addFlag(ItemFlag.HIDE_ENCHANTS);
        } else {
            removeFlag(ItemFlag.HIDE_ENCHANTS);
        }
        return this;
    }

    public ItemStackBuilder addFlag(ItemFlag... flags) {
        if (item.getType() == Material.ENCHANTED_BOOK) {
            this.storage.addItemFlags(flags);
        } else if (item.getType() == Material.SKULL_ITEM || item.getType() == Material.SKULL) {
            this.skullMeta.addItemFlags(flags);
        } else {
            this.meta.addItemFlags(flags);
        }
        return this;
    }

    public ItemStackBuilder removeFlag(ItemFlag... flags) {
        if (item.getType() == Material.ENCHANTED_BOOK) {
            this.storage.removeItemFlags(flags);
        } else if (item.getType() == Material.SKULL_ITEM || item.getType() == Material.SKULL) {
            this.skullMeta.removeItemFlags(flags);
        } else {
            this.meta.removeItemFlags(flags);
        }
        return this;
    }

    public static class InventoryBuilder {

        private String name;
        private HashMap<Integer, HashMap<Integer, ItemStackBuilder>> itens;
        private HashMap<Integer, Inventory> paginas;

        public InventoryBuilder(String name, Integer slot) {
            this.setName(name);
            this.itens = new HashMap<Integer, HashMap<Integer, ItemStackBuilder>>() {
                private static final long serialVersionUID = 1L;
                {
                    put(0, new HashMap<>());
                }
            };
            this.paginas = new HashMap<Integer, Inventory>() {
                private static final long serialVersionUID = 1L;
                {
                    put(0, Bukkit.createInventory(new ItemStackBuilderInventoryHolder(), slot, getName()));
                }
            };
        }

        public void setItem(int pagina, int slot, ItemStackBuilder item) {
            this.itens.get(pagina).put(slot, item);
        }

        public void removeItem(int pagina, int slot) {
            this.itens.get(pagina).remove(slot);
        }

        public List<ItemStackBuilder> getItens(int pagina) {
            return itens.get(pagina).values().stream().collect(Collectors.toList());
        }

        public void addInventory(String name, int slot) {
            this.paginas.put(paginas.size(), Bukkit.createInventory(new ItemStackBuilderInventoryHolder(), slot, name));
            this.itens.put(itens.size(), new HashMap<Integer, ItemStackBuilder>());
        }

        public void openInventory(Player player, int pagina) {
            this.itens.get(pagina).entrySet().forEach(item -> {
                this.paginas.get(pagina).setItem(item.getKey(), item.getValue().build());
            });
            player.openInventory(this.paginas.get(pagina));
        }

        public Inventory getInventory(int pagina) {
            return paginas.get(pagina);
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

    }

    public class InventoryAction {

        private String actionId;
        private Runnable runnable;
        private ItemStack item;
        private Player player;
        private boolean cancelEvent;
        private Inventory inventory;

        public InventoryAction(String actionId, Runnable runnable, ItemStack item, Player player, boolean cancelEvent,
                               Inventory inventory) {
            this.actionId = actionId;
            this.runnable = runnable;
            this.item = item;
            this.player = player;
            this.cancelEvent = cancelEvent;
            this.setInventory(inventory);
        }

        public Player getPlayer() {
            return player;
        }

        public void setPlayer(Player player) {
            this.player = player;
        }

        public boolean isCancelEvent() {
            return cancelEvent;
        }

        public void setCancelEvent(boolean cancelEvent) {
            this.cancelEvent = cancelEvent;
        }

        public String getActionId() {
            return actionId;
        }

        public void setActionId(String actionId) {
            this.actionId = actionId;
        }

        public Runnable getRunnable() {
            return runnable;
        }

        public void setRunnable(Runnable runnable) {
            this.runnable = runnable;
        }

        public ItemStack getItem() {
            return item;
        }

        public void setItem(ItemStack item) {
            this.item = item;
        }

        public Inventory getInventory() {
            return inventory;
        }

        public void setInventory(Inventory inventory) {
            this.inventory = inventory;
        }

    }

    public static class ItemStackBuilderEvents implements Listener {

        @EventHandler
        public void inventoryClick(InventoryClickEvent e) {
            Player player = (Player) e.getWhoClicked();
            ItemStack currentItem = e.getCurrentItem();
            if (currentItem != null && currentItem.getType() != Material.AIR) {
                    if (inventoryActions.get(currentItem) != null) {
                        InventoryAction action = inventoryActions.get(currentItem);
                        if (action.getInventory().equals(action.getInventory())) {
                            if (action.getPlayer().equals(player)) {
                                e.setCancelled(action.isCancelEvent());
                                action.getRunnable().run();
                            }
                        }
                }
            }
        }

        public static void clearData() {
            inventoryActions.clear();
        }

    }

    public static class ItemStackBuilderInventoryHolder implements InventoryHolder {

        @Override
        public Inventory getInventory() {
            return null;
        }

    }

}
