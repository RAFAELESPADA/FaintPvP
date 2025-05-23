package dev.ojvzinn.pvp.menu;

import dev.ojvzinn.pvp.Main;
import dev.ojvzinn.pvp.lobby.Lobby;
import dev.slickcollections.kiwizin.Core;
import dev.slickcollections.kiwizin.libraries.menu.UpdatablePlayerMenu;
import dev.slickcollections.kiwizin.player.Profile;
import dev.slickcollections.kiwizin.utils.BukkitUtils;
import dev.slickcollections.kiwizin.utils.StringUtils;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.HandlerList;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.inventory.ItemStack;

public class MenuLobbies extends UpdatablePlayerMenu {
  
  public MenuLobbies(Profile profile) {
    super(profile.getPlayer(), Lobby.CONFIG.getString("title"), Lobby.CONFIG.getInt("rows"));
    
    this.update();
    this.register(Main.getInstance(), 20);
    this.open();
  }
  
  @EventHandler
  public void onInventoryClick(InventoryClickEvent evt) {
    if (evt.getInventory().equals(this.getInventory())) {
      evt.setCancelled(true);
      
      if (evt.getWhoClicked().equals(this.player)) {
        Profile profile = Profile.getProfile(this.player.getName());
        if (profile == null) {
          this.player.closeInventory();
          return;
        }
        
        if (evt.getClickedInventory() != null && evt.getClickedInventory().equals(this.getInventory())) {
          ItemStack item = evt.getCurrentItem();
          
          if (item != null && item.getType() != Material.AIR) {
            Lobby lobby = Lobby.listLobbies()
                .stream().filter(s -> s.getSlot() == evt.getSlot()).findFirst().orElse(null);
            if (lobby != null && !Main.currentServerName.contentEquals(lobby.getServerName()) && lobby.getPlayers() < lobby.getMaxPlayers()) {
              Core.sendServer(profile, lobby.getServerName());
              this.player.closeInventory();
            }
          }
        }
      }
    }
  }
  
  @Override
  public void update() {
    for (Lobby lobby : Lobby.listLobbies()) {
      ItemStack icon = BukkitUtils.deserializeItemStack(lobby.getIcon().replace("{players}", StringUtils.formatNumber(lobby.getPlayers()))
          .replace("{maxplayers}", StringUtils.formatNumber(lobby.getMaxPlayers())).replace("{description}",
              StringUtils.formatColors(Lobby.CONFIG.getString("messages.description." + (Main.currentServerName.equals(lobby.getServerName()) ? "current" : "connect")))));
      if (Main.currentServerName.equals(lobby.getServerName())) {
        BukkitUtils.putGlowEnchantment(icon);
      }
      
      this.setItem(lobby.getSlot(), icon);
    }
  }
  
  public void cancel() {
    super.cancel();
    HandlerList.unregisterAll(this);
  }
  
  @EventHandler
  public void onPlayerQuit(PlayerQuitEvent evt) {
    if (evt.getPlayer().equals(this.player)) {
      this.cancel();
    }
  }
  
  @EventHandler
  public void onInventoryClose(InventoryCloseEvent evt) {
    if (evt.getPlayer().equals(this.player) && evt.getInventory().equals(this.getInventory())) {
      this.cancel();
    }
  }
}
