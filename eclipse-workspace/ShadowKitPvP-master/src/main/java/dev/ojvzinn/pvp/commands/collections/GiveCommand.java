package dev.ojvzinn.pvp.commands.collections;

import dev.ojvzinn.pvp.commands.SubCommand;
import dev.slickcollections.kiwizin.booster.Booster;
import dev.slickcollections.kiwizin.player.Profile;
import org.bukkit.command.CommandSender;

public class GiveCommand extends SubCommand {
  
  public GiveCommand() {
    super("dar", "dar [jogador] [booster/coins]", "Dar multiplicadores e coins.", false);
  }
  
  @Override
  public void perform(CommandSender sender, String[] args) {
    if (args.length <= 1) {
      sender.sendMessage(" \n§eAjuda - Dar\n \n§6/kp dar [jogador] coins [quantia]\n§6/kp dar [jogador] booster [private/network] [multiplicador] [horas]\n ");
      return;
    }
    
    Profile target = Profile.getProfile(args[0]);
    if (target == null) {
      sender.sendMessage("§cUsuário não encontrado.");
      return;
    }
    
    String action = args[1];
    if (action.equalsIgnoreCase("booster")) {
      if (args.length < 5) {
        sender.sendMessage("§cUtilize /kp dar [jogador] booster [private/network] [multiplicador] [horas]");
        return;
      }
      
      try {
        Booster.BoosterType type = Booster.BoosterType.valueOf(args[2].toUpperCase());
        try {
          double multiplier = Double.parseDouble(args[3]);
          long hours = Long.parseLong(args[4]);
          if (multiplier < 1.0D || hours < 1) {
            throw new Exception();
          }
          
          target.getBoostersContainer().addBooster(type, multiplier, hours);
          sender.sendMessage("§aMultiplicador adicionado.");
        } catch (Exception ex) {
          sender.sendMessage("§cUtilize números válidos.");
        }
      } catch (Exception ex) {
        sender.sendMessage("§cUtilize /kp dar [jogador] booster [private/network] [multiplicador] [horas]");
      }
    } else if (action.equalsIgnoreCase("coins")) {
      if (args.length < 3) {
        sender.sendMessage("§cUtilize /kp dar [jogador] coins [quantia]");
        return;
      }
      
      try {
        double coins = Double.parseDouble(args[2]);
        if (coins < 1.0D) {
          throw new Exception();
        }
        
        target.addCoins("FancyCoreKitPvP", coins);
        sender.sendMessage("§aCoins adicionados.");
      } catch (Exception ex) {
        sender.sendMessage("§cUtilize números válidos.");
      }
    } else {
      sender.sendMessage(" \n§eAjuda - Dar\n \n§6/kp dar [jogador] coins [quantia]\n§6/kp dar [jogador] booster [private/network] [multiplicador] [horas]\n ");
    }
  }
}
