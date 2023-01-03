package cn.reiyans.RYSshout.Form;

import cn.nukkit.form.element.ElementInput;
import cn.nukkit.form.element.ElementLabel;
import cn.nukkit.form.window.FormWindowCustom;
import cn.nukkit.Player;
import cn.reiyans.RYSshout.PluginMain;

public class OpUI {
    public static final int MENU = 1932; //please look playerUI to learn
    public static void opmenu(Player player) {
        String msg21 = PluginMain.getInstance().getConfig().getInt("ChoseMoodPrice") + "";
        String msg22 = PluginMain.getInstance().getConfig().getInt("ChoseColorPrice") + "";
        String msg23 = PluginMain.getInstance().getConfig().getInt("SendTextPrice") + "";
        String msg24 = PluginMain.getInstance().getConfig().getString("ShoutSetUITitle");
        String msg25 = PluginMain.getInstance().getConfig().getString("ShoutSetInformation");
        FormWindowCustom opform = new FormWindowCustom(msg24);
        opform.addElement(new ElementLabel(msg25));
        opform.addElement(new ElementInput(" ",msg21));
        opform.addElement(new ElementInput(" ",msg22));
        opform.addElement(new ElementInput(" ",msg23));
        player.showFormWindow(opform, MENU);
    }
}