package cn.reiyans.RYSbank.Form;

import cn.nukkit.Player;
import cn.nukkit.form.element.ElementInput;
import cn.nukkit.form.window.FormWindowCustom;
import cn.reiyans.RYSbank.PluginMain;

public class BankTakeUi {
    public static final int MENU = 1833; //The menu id can help you use the ui's contents correctly
    public static void banktakeui(Player player) {
        String msg8 = PluginMain.getInstance().getConfig().getString("BankUITitle");
        String msg9 = PluginMain.getInstance().getConfig().getString("BankUISaveMoneyTitle");
        FormWindowCustom banktakeform = new FormWindowCustom(msg8);
        banktakeform.addElement(new ElementInput(msg9,"100"));
        player.showFormWindow(banktakeform, MENU);
    }
}