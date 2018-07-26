package cf.singnet;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class CommandBedwarsDestoryTitle
  implements CommandExecutor
{
  private BedwarsDestoryTitle plugin;
  
  public CommandBedwarsDestoryTitle(BedwarsDestoryTitle plugin)
  {
    this.plugin = plugin;
  }
  
  public boolean onCommand(CommandSender sender, Command command, String label, String[] args)
  {
    if (args.length == 0)
    {
      sendHelpMessage(sender);
      return true;
    }
    if (args[0].equalsIgnoreCase("reload"))
    {
      if (args.length != 1)
      {
        sendWrongArgsMessage(sender);
        return true;
      }
      this.plugin.reloadConfig();
      sender.sendMessage("插件重载成功");
      return true;
    }
    if (args[0].equalsIgnoreCase("help"))
    {
      if (args.length != 1)
      {
        sendWrongArgsMessage(sender);
        return true;
      }
      sendHelpMessage(sender);
      return true;
    }
    sender.sendMessage("未知命令. 使用\"/BedwarsDestoryTitle help\"查看帮助");
    return false;
  }
  
  private void sendWrongArgsMessage(CommandSender sender)
  {
    sender.sendMessage("参数错误");
  }
  
  private void sendHelpMessage(CommandSender sender)
  {
    sender.sendMessage("BedwarsDestoryTitle 帮助");
    sender.sendMessage("/BedwarsDestoryTitle reload: 重载插件");
    sender.sendMessage("/BedwarsDestoryTitle help: 查看帮助");
  }
}

