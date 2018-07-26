package cf.singnet;

import java.util.logging.Logger;
import org.bukkit.Server;
import org.bukkit.command.PluginCommand;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class BedwarsDestoryTitle
  extends JavaPlugin
{
  public void onEnable()
  {
    saveDefaultConfig();
    getCommand("BedwarsDestoryTitle").setExecutor(new CommandBedwarsDestoryTitle(this));
    getServer().getPluginManager().registerEvents(new BedwarsListener(this), this);
    getLogger().info("BedwarsDestoryTitle enabled");
  }
  
  public void onDisable()
  {
    getLogger().info("BedwarsDestoryTitle disabled");
  }
}