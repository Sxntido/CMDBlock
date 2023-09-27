package org.github.sxntido;

import java.util.List;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.github.sxmuray.CMDBlock;
import org.github.sxmuray.tools.Utils;

public class CMDBlock implements Listener {
  private List<String> enabledWorlds = CMDBlock.GetCfg().getStringList("worlds_events.worls");
  
  private List<String> getEnabledWorlds() {
    return this.enabledWorlds;
  }
  
  @EventHandler
  public void playerInteract(PlayerInteractEvent e) {
    Player p = e.getPlayer();
    Block b = e.getClickedBlock();
    for (int i = 0; i < getEnabledWorlds().size(); i++) {
      if (((String)this.enabledWorlds.get(i)).equals(p.getWorld().getName()) && 
        CMDBlock.GetCfg().getBoolean("block.switch") && 
        e.getAction() == Action.RIGHT_CLICK_BLOCK && e.getClickedBlock().getType() == Material.valueOf(CMDBlock.GetCfg().getString("block.material")))
        if (e.getPlayer().hasPermission("CMDBlock.block")) {
          p.performCommand(CMDBlock.GetCfg().getString("block.command"));
        } else {
          Utils.sendMessage(p, CMDBlock.GetCfg().getString("block.noperm"), false);
        }  
    } 
  }
}
