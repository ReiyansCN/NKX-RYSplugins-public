package cn.reiyans.RYSshout;

import cn.nukkit.plugin.PluginBase;
import cn.nukkit.command.Command;
import cn.nukkit.command.CommandSender;
import cn.reiyans.RYSshout.Form.PlayerUI;
public class PluginMain extends PluginBase {
    private static PluginMain instance;
    @Override//loading plugin
    public void onLoad() {
        this.getLogger().info("Loading RYSshout, version 0.1");
    }
    @Override//enabling plugin
    public void onEnable() {
        instance = this;
        this.saveDefaultConfig();
        this.getLogger().info("loaded RYSshout, thanks your choice, the author is Reiyans");
        this.getLogger().info("If you have some questions, you can add my qq (2777807622) or discord (Reiyans)");
    }
    @Override//stop plugin
    public void onDisable() {
        this.getLogger().info("Stoping RYSshout, version 0.1");
    }
    @Override//register command of shouting
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if("shout".equals(command.getName())){ //think command name
            String msg3 = getConfig().getString("UseShoutCommandLogger", "A player shouting");
            this.getLogger().info(msg3);
            PlayerUI.playermenu(sender.asPlayer());
            return true;
        }
        else if("shoutset".equals(command.getName())){
            String msg4 = getConfig().getString("UseShoutsetCommandLogger","A op seting config");
            this.getLogger().info(msg4);
            return true;
        }
        return false;
        }
    public static PluginMain getInstance() {
        return instance;
    }
}