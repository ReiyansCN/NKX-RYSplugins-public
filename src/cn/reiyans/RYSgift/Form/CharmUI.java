package cn.reiyans.RYSgift.Form;

import cn.nukkit.Player;
import cn.nukkit.form.element.ElementLabel;
import cn.nukkit.utils.Config;
import cn.reiyans.RYSgift.PluginMain;
import moe.him188.gui.window.FormCustom;

public class CharmUI {
    public static void charmui(Player player) {
        Config config002 = new Config(PluginMain.getInstance().getDataFolder()+"/config.yml",Config.YAML);
        Config leveldata = new Config(PluginMain.getInstance().getDataFolder()+"/level.yml",Config.YAML);
        Config playerconfig = new Config(PluginMain.getInstance().getDataFolder()+"/players/"+player.getName()+"/info.yml",Config.YAML);
        FormCustom form = new FormCustom(config002.getString("LevelUITitle"));
        form.addElement(new ElementLabel(config002.getString("LevelUIInformation")));
        int i = 0;
        while (leveldata.exists("Level"+i+"Name")){
            form.addElement(new ElementLabel(leveldata.getString("Level"+i+"Name")+leveldata.getString("Level"+i+"Need")));
            if(playerconfig.getInt("charm") >= leveldata.getInt("Level"+i+"Need")){
                playerconfig.set("level", leveldata.getString("Level"+i+"Name"));
            }
            i++;
        }
        playerconfig.save();
        player.showFormWindow(form.onResponded(response -> {
        }));
    }
}
