package cn.reiyans.RYSshout;

import cn.nukkit.plugin.PluginBase;
import cn.nukkit.command.Command;
import cn.nukkit.command.CommandSender;
import cn.reiyans.RYSshout.Form.PlayerUI;
import cn.reiyans.RYSshout.Form.OpUI;
import cn.reiyans.RYSshout.Listener.RYSshoutListener;

import java.util.Objects;

public class PluginMain extends PluginBase {
    private static PluginMain instance;
    @Override//loading plugin
    public void onLoad() {this.getLogger().info("§6Loading §aRYSshout §6the plugin is free and open origin code§ahttps://github.com/ReiyansCN/RYSplugins-public/tree/main/RYSshout");
    }
    @Override//enabling plugin
    public void onEnable() {
        instance = this;
        this.saveDefaultConfig();
        this.getServer().getPluginManager().registerEvents(new RYSshoutListener(),this);
        String msg18 = PluginMain.getInstance().getConfig().getString("Version");
        this.getLogger().info("§6loaded §aRYSshout §6Successfully!§6Now Version: "+msg18);
        this.getLogger().info("§6the author is Reiyans!you can connect my qq (2777807622) or discord (Reiyans)");
    }
    @Override//stop plugin
    public void onDisable() {
        String msg19 = PluginMain.getInstance().getConfig().getString("Version");
        this.getLogger().info("§4Stoping RYSshout, §6version :"+msg19);
    }
    @Override//register command of shouting
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if("shout".equals(command.getName())){ //think command name
            String msg3 = getConfig().getString("UseShoutCommandLogger", "A player shouting");
            this.getLogger().info(msg3);
            PlayerUI.playermenu(Objects.requireNonNull(sender.asPlayer()));
            return true;
        }
        else if("shoutset".equals(command.getName())){
            String msg4 = getConfig().getString("UseShoutsetCommandLogger","A op seting config");
            this.getLogger().info(msg4);
            OpUI.opmenu(Objects.requireNonNull(sender.asPlayer()));
            return true;
        }
        return false;
        }
    public static PluginMain getInstance() {
        return instance;
    }
}