package cn.reiyans.RYSgift.Form;

import cn.nukkit.form.element.ElementButtonImageData;
import cn.reiyans.RYSgift.PluginMain;
import moe.him188.gui.element.ResponsibleButton;
import moe.him188.gui.window.FormSimple;
import cn.nukkit.Player;
import cn.nukkit.utils.Config;

public class MainUI {
    public static void playerui(Player player) {
        Config configplayer11 = new Config(PluginMain.getInstance().getDataFolder()+"/players/"+player.getName()+"/info.yml",Config.YAML);
        Config config001 = new Config(PluginMain.getInstance().getDataFolder()+"/config.yml",Config.YAML);
        FormSimple form = new FormSimple(config001.getString("GiftUITitle"), config001.getString("GiftUILine1")+configplayer11.getInt("charm")+"\n"+config001.getString("GiftUILine2")+configplayer11.getString("level")+"\n"+config001.getString("GiftUILine3"));
        form.addButton(new ResponsibleButton(config001.getString("GiftUIButton1"), new ElementButtonImageData("path", config001.getString("GiftUIButton1Image")),
                () -> SendUI.sendui(player)));
        form.addButton(new ResponsibleButton(config001.getString("GiftUIButton2"), new ElementButtonImageData("path", config001.getString("GiftUIButton2Image")),
                () -> ShopUI.shopui(player)));
        form.addButton(new ResponsibleButton(config001.getString("GiftUIButton3"), new ElementButtonImageData("path", config001.getString("GiftUIButton3Image")),
                () -> MessageUI.messageui(player)));
        form.addButton(new ResponsibleButton(config001.getString("GiftUIButton4"), new ElementButtonImageData("path", config001.getString("GiftUIButton4Image")),
                () -> CharmUI.charmui(player)));
        player.showFormWindow(form.onClicked(id -> {
        }));
    }
}