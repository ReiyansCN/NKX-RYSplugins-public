package cn.reiyans.RYSgift.Form;

import cn.nukkit.Player;
import cn.nukkit.form.element.ElementButton;
import cn.nukkit.utils.Config;
import cn.reiyans.RYSgift.PluginMain;
import moe.him188.gui.window.FormSimple;

public class MessageUI {
    public static void messageui(Player player) {
        Config config003 = new Config(PluginMain.getInstance().getDataFolder()+"/config.yml",Config.YAML);
        FormSimple form = new FormSimple(config003.getString("MessageUITitle"),config003.getString("MessageMax"));
        form.addButton(new ElementButton(config003.getString("SendMsgButton")));
        form.addButton(new ElementButton(config003.getString("ReceiveMsgButton")));
        player.showFormWindow(form.onClicked(id -> { 
            if(id == 0){
                MessageUIsend.ui(player);
            }
            if(id == 1){
                MessageUIreceive.ui(player);
            }
        }));
    }

}
