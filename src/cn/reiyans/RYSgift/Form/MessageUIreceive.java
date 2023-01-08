package cn.reiyans.RYSgift.Form;

import cn.nukkit.Player;
import cn.nukkit.form.element.ElementLabel;
import cn.nukkit.utils.Config;
import cn.reiyans.RYSgift.PluginMain;
import moe.him188.gui.window.FormCustom;

public class MessageUIreceive {
    public static void ui(Player player) {
        Config config006 = new Config(PluginMain.getInstance().getDataFolder()+"/config.yml",Config.YAML);
        Config playerdata11 = new Config(PluginMain.getInstance().getDataFolder()+"/players/"+player.getName()+"/receive.yml",Config.YAML);
        FormCustom form = new FormCustom(config006.getString("ReceiveMsgButton"));
        int n = 1;
        while (playerdata11.exists(n+"")){
            form.addElement(new ElementLabel(playerdata11.getString(n+"")));
            n++;
        }
        player.showFormWindow(form.onResponded(response -> {
        }));
    }
}