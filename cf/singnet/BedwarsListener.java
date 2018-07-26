package cf.singnet;

import io.github.bedwarsrel.events.BedwarsTargetBlockDestroyedEvent;
import io.github.bedwarsrel.game.Game;
import io.github.bedwarsrel.game.Team;
import java.util.HashMap;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class BedwarsListener
  implements Listener
{
  private BedwarsDestoryTitle plugin;
  
  public BedwarsListener(BedwarsDestoryTitle plugin)
  {
    this.plugin = plugin;
  }
  
  @EventHandler
  public void onBedwarsTargetBlockDestroyed(BedwarsTargetBlockDestroyedEvent event)
  {
    int fadeIn = this.plugin.getConfig().getInt("times.fade-in");
    int stay = this.plugin.getConfig().getInt("times.stay");
    int fadeOut = this.plugin.getConfig().getInt("times.fade-out");
    String selfTitle = replaceVars(getMessage("self-title"), event);
    String selfSubtitle = replaceVars(getMessage("self-subtitle"), event);
    String othersTitle = replaceVars(getMessage("others-title"), event);
    String othersSubtitle = replaceVars(getMessage("others-subtitle"), event);
    for (Player player : event.getGame().getFreePlayers()) {
      TitleUtils.sendTitle(player, Integer.valueOf(fadeIn), Integer.valueOf(stay), Integer.valueOf(fadeOut), othersTitle, othersSubtitle);
    }
    for (Team team : event.getGame().getTeams().values()) {
      if (team.getName().equals(event.getTeam().getName())) {
        for (Player player : team.getPlayers()) {
          TitleUtils.sendTitle(player, Integer.valueOf(fadeIn), Integer.valueOf(stay), Integer.valueOf(fadeOut), selfTitle, selfSubtitle);
        }
      } else {
        for (Player player : team.getPlayers()) {
          TitleUtils.sendTitle(player, Integer.valueOf(fadeIn), Integer.valueOf(stay), Integer.valueOf(fadeOut), othersTitle, othersSubtitle);
        }
      }
    }
  }
  
  private String getMessage(String s)
  {
    return ChatColor.translateAlternateColorCodes('&', this.plugin.getConfig().getString("messages." + s, ""));
  }
  
  private String replaceVars(String s, BedwarsTargetBlockDestroyedEvent event)
  {
    Team destoryerTeam = event.getGame().getPlayerTeam(event.getPlayer());
    Team destoriedTeam = event.getTeam();
    return s.replace("%player_name%", event.getPlayer().getName())
      .replace("%player_displayname%", event.getPlayer().getDisplayName())
      .replace("%destoryer_team_name%", destoryerTeam.getName())
      .replace("%destoryer_team_displayname%", 
      destoryerTeam.getChatColor() + destoryerTeam.getDisplayName())
      .replace("%destoried_team_name%", destoriedTeam.getName())
      .replace("%destoried_team_displayname%", 
      destoriedTeam.getChatColor() + destoriedTeam.getDisplayName());
  }
}
