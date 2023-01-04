package cn.reiyans.RYSbank;

import cn.nukkit.event.Listener;
import cn.nukkit.plugin.PluginBase;
import cn.nukkit.scheduler.ServerScheduler;
import cn.nukkit.utils.Config;
import cn.reiyans.RYSbank.Listener.RYSbankListener;
import cn.nukkit.Player;
import cn.nukkit.command.Command;
import cn.nukkit.command.CommandSender;
import cn.reiyans.RYSbank.Form.PlayerUi;
import cn.nukkit.event.EventHandler;
import cn.nukkit.event.EventPriority;
import cn.nukkit.event.player.PlayerJoinEvent;
import cn.reiyans.RYSbank.Tasks.Inerest;

public class PluginMain extends PluginBase implements Listener {
    private static PluginMain instance;
    @Override
    public void onLoad() {
        this.getLogger().info("§6Loading §aRYSbank §6the plugin is free and open origin code§ahttps://github.com/ReiyansCN/RYSplugins-public/tree/RYSbank");
    }
    @Override
    public void onEnable() {
        instance = this;
        this.saveDefaultConfig();
        this.getServer().getPluginManager().registerEvents(new RYSbankListener(), this);
        ServerScheduler scheduler = this.getServer().getScheduler();
        String msg1 = getInstance().getConfig().getString("Version");
        this.getLogger().info("§6loaded §aRYSbank §6Successfully!§6Now Version: " + msg1);
        this.getLogger().info("§6the author is Reiyans!you can connect my qq (2777807622) or discord (Reiyans)");
    }
    @Override
    public void onDisable() {
        String msg2 = getInstance().getConfig().getString("Version");
        this.getLogger().info("§6Stoping §aRYSbank §6Successfully!§6Now Version: " + msg2);
    }
    @Override//register command of shouting
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if("bank".equals(command.getName())){ //think command name
            PlayerUi.playerui((Player) sender);
            return true;
        }
        return false;
    }
    @EventHandler(priority = EventPriority.LOW,ignoreCancelled = true)
    public void JoinPlayer(PlayerJoinEvent event){
        Player player = event.getPlayer();
        int hour = getInstance().getConfig().getInt("InterestRateNeedHour");
        this.getServer().getScheduler().scheduleDelayedTask(new Inerest(this,event.getPlayer()), hour*60*60*20);

    }

    public static PluginMain getInstance() {
        return instance;
    }
}