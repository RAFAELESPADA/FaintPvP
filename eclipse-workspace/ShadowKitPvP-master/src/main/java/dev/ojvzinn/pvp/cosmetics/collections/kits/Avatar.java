package dev.ojvzinn.pvp.cosmetics.collections.kits;


import dev.ojvzinn.pvp.Main;
import dev.ojvzinn.pvp.api.DelayKits;
import dev.ojvzinn.pvp.cosmetics.Cosmetic;
import dev.ojvzinn.pvp.cosmetics.CosmeticType;
import dev.ojvzinn.pvp.cosmetics.collections.Kit;
import dev.ojvzinn.pvp.game.KitPvP;
import dev.slickcollections.kiwizin.player.Profile;
import dev.slickcollections.kiwizin.utils.BukkitUtils;
import org.bukkit.*;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Snowball;
import org.bukkit.event.EventHandler;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.metadata.MetadataValue;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.BlockIterator;
import org.bukkit.util.Vector;

public class Avatar extends Kit {

    public Avatar(String key, YamlConfiguration CONFIG) {
        super(key, CONFIG);
    }

    @Override
    public void applyKit(Player player) {
        PlayerInventory inventory = player.getInventory();
        Profile.getProfile(player.getName()).setHotbar(null);
        inventory.clear();

        ItemStack sword = BukkitUtils.deserializeItemStack("272 : 1");
        ItemMeta meta = sword.getItemMeta();
        meta.spigot().setUnbreakable(true);
        sword.setItemMeta(meta);
        inventory.setItem(0, sword);
        inventory.setItem(1, BukkitUtils.deserializeItemStack("BEACON : 1 : nome>&6Avatar &7(Clique direito)"));
        inventory.setItem(8, BukkitUtils.deserializeItemStack("COMPASS : 1 : nome>&aBússola"));
        inventory.setItem(15, new ItemStack(Material.RED_MUSHROOM, 16));
        inventory.setItem(14, new ItemStack(Material.BROWN_MUSHROOM, 16));
        inventory.setItem(13, new ItemStack(Material.BOWL, 16));

        for (int i = 0; i < inventory.getSize(); i++) {
            if (inventory.getItem(i) == null || inventory.getItem(i).getType() == Material.AIR) {
                inventory.setItem(i, new ItemStack(Material.MUSHROOM_SOUP));
            }
        }
        DelayKits.createDelayProfile(player, this);
        addProtection(player.getName());
        player.sendMessage("§aVocê equipou o kit Avatar.");
    }

    @Override
    public void setupListeners() {
        Bukkit.getPluginManager().registerEvents(this, Main.getInstance());
    }


    public static ItemStack getItem(ItemStack item) {
        ItemManager itemmanager = new ItemManager(Material.WOOL, "§aAvatar");
        if (item.getType() == Material.BEACON) {
            itemmanager.setMaterial(Material.WOOL);
            itemmanager.setNome("§fAr");
            item.setType(Material.WOOL);
            return item;
        }
        if (item.getType() == Material.WOOL) {
            itemmanager.setMaterial(Material.REDSTONE_BLOCK);
            itemmanager.setNome("§cFogo");
            item.setType(Material.REDSTONE_BLOCK);
            return item;
        }
        if (item.getType() == Material.REDSTONE_BLOCK) {
            itemmanager.setMaterial(Material.LAPIS_BLOCK);
            itemmanager.setNome("§bÁgua");
            item.setType(Material.LAPIS_BLOCK);
            return item;
        }
        if (item.getType() == Material.LAPIS_BLOCK) {
            itemmanager.setMaterial(Material.GRASS);
            itemmanager.setNome("§6Terra");
            item.setType(Material.GRASS);
            return item;
        }
        if (item.getType() == Material.GRASS) {
            itemmanager.setMaterial(Material.WOOL);
            itemmanager.setNome("§fAr");
            item.setType(Material.WOOL);
            return item;
        }
        itemmanager.setNome("§aAvatar");
        return item;
    }
    @EventHandler
    public void asd(PlayerDropItemEvent e) {
        Profile profile = Profile.getProfile(e.getPlayer().getName());
        if (KitPvP.isPlayingKitPvP(e.getPlayer())) {
            ItemStack item = e.getItemDrop().getItemStack();


            if (e.getItemDrop().getItemStack().getType() == Material.LAPIS_BLOCK && item.getItemMeta() != null && item.getItemMeta().hasDisplayName() && item.getItemMeta().getDisplayName().equals("§6Avatar §7(Clique direito)")) {
                e.setCancelled(true);
            } else if (e.getItemDrop().getItemStack().getType() == Material.REDSTONE_BLOCK && item.getItemMeta() != null && item.getItemMeta().hasDisplayName() && item.getItemMeta().getDisplayName().equals("§6Avatar §7(Clique direito)")) {
                e.setCancelled(true);
            } else if (e.getItemDrop().getItemStack().getType() == Material.BEACON && item.getItemMeta() != null && item.getItemMeta().hasDisplayName() && item.getItemMeta().getDisplayName().equals("§6Avatar §7(Clique direito)")) {
                e.setCancelled(true);
            } else if (e.getItemDrop().getItemStack().getType() == Material.GRASS && item.getItemMeta() != null && item.getItemMeta().hasDisplayName() && item.getItemMeta().getDisplayName().equals("§6Avatar §7(Clique direito)")) {
                e.setCancelled(true);
            } else if (e.getItemDrop().getItemStack().getType() == Material.WOOL && item.getItemMeta() != null && item.getItemMeta().hasDisplayName() && item.getItemMeta().getDisplayName().equals("§6Avatar §7(Clique direito)")) {
                e.setCancelled(true);
            }
        }
    }
    @EventHandler
    public void asd(PlayerInteractEvent e) {
        Player player = e.getPlayer();
        Profile profileClicked = Profile.getProfile(e.getPlayer().getName());
        if (KitPvP.isPlayingKitPvP(profileClicked.getPlayer())) {
            if (player.getItemInHand().getType() == Material.AIR)
                return;
            if (player.getItemInHand() == null)
                return;
            if (player.getItemInHand().getType() == Material.AIR)
                return;
            if (!player.getItemInHand().hasItemMeta())
                return;
            if (!player.getItemInHand().getItemMeta().hasDisplayName())
                return;
            if (!player.getItemInHand().getItemMeta().getDisplayName().equals("§6Avatar §7(Clique direito)"))
                return;


            if (e.getAction() == Action.LEFT_CLICK_AIR || e.getAction() == Action.LEFT_CLICK_BLOCK) {
                player.setItemInHand(getItem(player.getItemInHand()));

            } else {
                if (!DelayKits.loadDelayProfiles(player).canUse()) {
                    player.sendMessage("§cAguarde " + DelayKits.loadDelayProfiles(player).getDelayTime(true) + "s para utilizar este kit novamente.");
                    return;
                }
                DelayKits.loadDelayProfiles(player).addDelay(30);
                e.setCancelled(true);
                Material item = player.getItemInHand().getType();
                if (item == Material.WOOL) {
                    Vector Ferro = player.getLocation().getDirection().normalize().multiply(55);
                    Snowball FerroH = (Snowball)player.launchProjectile(Snowball.class);
                    FerroH.setVelocity(Ferro);
                    FerroH.setMetadata("Vento", (MetadataValue)new FixedMetadataValue(Main.getInstance(), Boolean.valueOf(true)));
                    Location pegou = player.getEyeLocation();
                    BlockIterator Ferrao = new BlockIterator(pegou, 0.0D, 40);
                    while (Ferrao.hasNext()) {
                        Location Ferrao2 = Ferrao.next().getLocation();
                        Effect camelo = Effect.STEP_SOUND;
                        player.playSound(player.getLocation(), Sound.STEP_WOOL, 5.5F, 5.5F);
                        player.playSound(player.getLocation(), Sound.STEP_WOOL, 1.5F, 1.5F);
                        player.playSound(player.getLocation(), Sound.STEP_WOOL, 2.5F, 2.5F);
                        player.playSound(player.getLocation(), Sound.STEP_WOOL, 3.5F, 3.5F);
                        player.playSound(player.getLocation(), Sound.STEP_WOOL, 4.5F, 4.5F);
                        player.getWorld().playEffect(Ferrao2, camelo, 35);
                    }
                } else if (item == Material.LAPIS_BLOCK) {
                    Vector Ferro = player.getLocation().getDirection().normalize().multiply(55);
                    Snowball FerroH = (Snowball)player.launchProjectile(Snowball.class);
                    FerroH.setVelocity(Ferro);
                    FerroH.setMetadata("Agua", (MetadataValue)new FixedMetadataValue(Main.getInstance(), Boolean.valueOf(true)));
                    Location pegou = player.getEyeLocation();
                    BlockIterator Ferrao = new BlockIterator(pegou, 0.0D, 40);
                    while (Ferrao.hasNext()) {
                        Location Ferrao2 = Ferrao.next().getLocation();
                        Effect camelo = Effect.WATERDRIP;
                        player.playSound(player.getLocation(), Sound.STEP_WOOD, 5.5F, 5.5F);
                        player.playSound(player.getLocation(), Sound.STEP_WOOD, 1.5F, 1.5F);
                        player.playSound(player.getLocation(), Sound.STEP_WOOD, 2.5F, 2.5F);
                        player.playSound(player.getLocation(), Sound.STEP_WOOD, 3.5F, 3.5F);
                        player.playSound(player.getLocation(), Sound.STEP_WOOD, 4.5F, 4.5F);
                        player.getWorld().playEffect(Ferrao2, camelo, 35);
                    }
                } else if (item == Material.REDSTONE_BLOCK) {
                    Vector Ferro = player.getLocation().getDirection().normalize().multiply(55);
                    Snowball FerroH = (Snowball)player.launchProjectile(Snowball.class);
                    FerroH.setVelocity(Ferro);
                    FerroH.setMetadata("Fogo", (MetadataValue)new FixedMetadataValue(Main.getInstance(), Boolean.valueOf(true)));
                    Location pegou = player.getEyeLocation();
                    BlockIterator Ferrao = new BlockIterator(pegou, 0.0D, 40);
                    while (Ferrao.hasNext()) {
                        Location Ferrao2 = Ferrao.next().getLocation();
                        Effect camelo = Effect.LAVADRIP;
                        player.playSound(player.getLocation(), Sound.FIRE, 5.5F, 5.5F);
                        player.playSound(player.getLocation(), Sound.FIRE, 1.5F, 1.5F);
                        player.playSound(player.getLocation(), Sound.FIRE, 2.5F, 2.5F);
                        player.playSound(player.getLocation(), Sound.FIRE, 3.5F, 3.5F);
                        player.playSound(player.getLocation(), Sound.FIRE, 4.5F, 4.5F);
                        player.getWorld().playEffect(Ferrao2, camelo, 35);
                    }
                } else if (item == Material.GRASS) {
                    Vector Ferro = player.getLocation().getDirection().normalize().multiply(55);
                    Snowball FerroH = (Snowball)player.launchProjectile(Snowball.class);
                    FerroH.setVelocity(Ferro);
                    FerroH.setMetadata("Terra", (MetadataValue)new FixedMetadataValue(Main.getInstance(), Boolean.valueOf(true)));
                    Location pegou = player.getEyeLocation();
                    BlockIterator Ferrao = new BlockIterator(pegou, 0.0D, 40);
                    while (Ferrao.hasNext()) {
                        Location Ferrao2 = Ferrao.next().getLocation();
                        Effect camelo = Effect.CRIT;
                        player.playSound(player.getLocation(), Sound.STEP_GRASS, 5.5F, 5.5F);
                        player.playSound(player.getLocation(), Sound.STEP_GRASS, 1.5F, 1.5F);
                        player.playSound(player.getLocation(), Sound.STEP_GRASS, 2.5F, 2.5F);
                        player.playSound(player.getLocation(), Sound.STEP_GRASS, 3.5F, 3.5F);
                        player.playSound(player.getLocation(), Sound.STEP_GRASS, 4.5F, 4.5F);
                        player.getWorld().playEffect(Ferrao2, camelo, 65);
                    }
                }
            }
        }
    }

    @EventHandler
    public void asd(EntityDamageByEntityEvent e) {
        Entity entity = e.getEntity();

        if (e.getDamager() instanceof Snowball) {
            Snowball snow = (Snowball)e.getDamager();
            if (snow.hasMetadata("Terra")) {
                ((LivingEntity)entity).damage(10.0D);
                Vector vector = entity.getLocation().getDirection();
                vector.multiply(-3.5F);
                entity.setVelocity(vector);
            } else if (snow.hasMetadata("Fogo")) {
                ((LivingEntity)entity).damage(9.0D);
                entity.setFireTicks(200);
            } else if (snow.hasMetadata("Agua")) {
                ((LivingEntity)entity).damage(8.0D);
                ((LivingEntity)entity).addPotionEffect(new PotionEffect(PotionEffectType.POISON, 100, 3));
                Vector vector = entity.getLocation().getDirection();
                vector.multiply(-1.0F);
                entity.setVelocity(vector);
            } else if (snow.hasMetadata("Vento")) {
                ((LivingEntity)entity).damage(12.0D);
                ((LivingEntity)entity).addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, 60, 3));
            }
        }
    }
}

