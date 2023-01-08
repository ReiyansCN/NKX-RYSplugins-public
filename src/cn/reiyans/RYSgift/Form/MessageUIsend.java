package cn.reiyans.RYSgift.Form;

import cn.nukkit.Player;
import cn.nukkit.form.element.ElementLabel;
import cn.nukkit.utils.Config;
import cn.reiyans.RYSgift.PluginMain;
import moe.him188.gui.window.FormCustom;

public class MessageUIsend {
    public static void ui(Player player) {
        Config config006 = new Config(PluginMain.getInstance().getDataFolder()+"/config.yml",Config.YAML);
        Config playerdata1 = new Config(PluginMain.getInstance().getDataFolder()+"/players/"+player.getName()+"/send.yml",Config.YAML);
        FormCustom form = new FormCustom(config006.getString("SendMsgButton"));
        int i = 1;
        while (playerdata1.exists(i+"")){
            form.addElement(new ElementLabel(playerdata1.getString(i+"")));
            i++;
        }
        player.showFormWindow(form.onResponded(response -> {
        }));
    }
}
