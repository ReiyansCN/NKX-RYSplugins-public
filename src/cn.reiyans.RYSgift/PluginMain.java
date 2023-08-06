package cn.reiyans.RYSgift;

import cn.nukkit.plugin.PluginBase;
import cn.nukkit.utils.Config;
import cn.reiyans.RYSgift.Form.MainUI;
import cn.reiyans.RYSgift.Listener.RYSgiftListener;
import cn.nukkit.Player;
import cn.nukkit.command.Command;
import cn.nukkit.command.CommandSender;
import tip.utils.Api;

public class PluginMain extends PluginBase {
    private static PluginMain instance;

    @Override
    public void onLoad() {
        this.getLogger().info("§6加载插件 §aRYSbank §6中，这个插件免费，开源在§ahttps://github.com/ReiyansCN/RYSplugins-public/tree/RYSgift");
    }

    @Override
    public void onEnable() {
        instance = this;
        this.saveDefaultConfig();
        this.getServer().getPluginManager().registerEvents(new RYSgiftListener(), this);
        String Version = getInstance().getConfig().getString("Version");
        this.getLogger().info("§6成功加载插件 §aRYSgift §6!现在的版本是: " + Version);
        this.getLogger().info("§6插件作者是§aReiyans!§6有问题你可以联系我 (qq2777807622) 或者 discord (ID是Reiyans)");
        Config LevelConfig1 = new Config(PluginMain.getInstance().getDataFolder()+"/level.yml",Config.YAML);
        Config giftsConfig = new Config(PluginMain.getInstance().getDataFolder()+"/gifts.yml",Config.YAML);
        if(LevelConfig1.exists("LevelDefault")){
        }else{
            LevelConfig1.set("Level0Name","毫无魅力");
            LevelConfig1.set("Level0Need",0);
            LevelConfig1.set("Level1Name","略有耳闻");
            LevelConfig1.set("Level1Need",100);
            LevelConfig1.set("Level2Name","响彻一方");
            LevelConfig1.set("Level2Need",200);
            LevelConfig1.save();
        }
        if(giftsConfig.exists("gift1Name")){
        }else{
            giftsConfig.set("gift1Name","芬芳玫瑰");
            giftsConfig.set("gift1Charm",10);
            giftsConfig.set("gift1Price",100);
            giftsConfig.set("gift2Name","蓝色妖姬");
            giftsConfig.set("gift2Charm",20);
            giftsConfig.set("gift2Price",200);
            giftsConfig.save();
        }
        try {
            Class.forName("tip.Main");
            if (getServer().getPluginManager().getPlugin("Tips").isDisabled()) {
                throw new RuntimeException("Not Loaded");
            }
            Api.registerVariables("RYSgift", TipV.class);
        } catch (Exception ignored) {

        }


    }

    @Override
    public void onDisable() {
        String Version  = getInstance().getConfig().getString("Version");
        this.getLogger().info("§6关闭插件 §aRYSgift §6成功!§6当前版本: " + Version);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if ("gift".equals(command.getName())) { //think command name
            MainUI.playerui((Player) sender);
            return true;
        }
        return false;
    }

    public static PluginMain getInstance () {
        return instance;
    }
}
