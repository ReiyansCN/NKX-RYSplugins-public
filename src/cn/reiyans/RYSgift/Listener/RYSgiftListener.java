package cn.reiyans.RYSgift.Listener;

import cn.nukkit.event.EventHandler;
import cn.nukkit.event.Listener;
import cn.nukkit.event.player.PlayerJoinEvent;
import cn.nukkit.utils.Config;
import cn.reiyans.RYSgift.PluginMain;

public class RYSgiftListener implements Listener {
    @EventHandler
    public void PlayerJoin(PlayerJoinEvent player) {
        Config configplayer = new Config(PluginMain.getInstance().getDataFolder() + "/players/" + player.getPlayer().getName() + "/info.yml", Config.YAML);
        Config configplayer1 = new Config(PluginMain.getInstance().getDataFolder() + "/players/" + player.getPlayer().getName() + "/send.yml", Config.YAML);
        Config configplayer2 = new Config(PluginMain.getInstance().getDataFolder() + "/players/" + player.getPlayer().getName() + "/receive.yml", Config.YAML);
        Config configplayer3 = new Config(PluginMain.getInstance().getDataFolder() + "/gifts.yml", Config.YAML);
        Config configlevel = new Config(PluginMain.getInstance().getDataFolder() + "/level.yml", Config.YAML);
        if (configplayer.exists("charm")) {
            int i = 1;
            while (configplayer3.exists("gift" + i + "Name")) {
                if (!configplayer.exists(configplayer3.getString("gift" + i + "Name"))) {
                    configplayer.set(configplayer3.getString("gift" + i + "Name"), 0);
                    configplayer.save();
                }else{
                    i++;
                }
            }
        }else{
            configplayer.set("charm", 0);
            configplayer.set("level", configlevel.getString("Level0Name"));
            configplayer.save();
            configplayer1.set("data", 1);
            configplayer1.save();
            configplayer2.set("data", 1);
            configplayer2.save();
        }

    }
}
