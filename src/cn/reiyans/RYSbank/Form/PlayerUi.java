package cn.reiyans.RYSbank.Form;

import cn.nukkit.Player;
import cn.nukkit.form.element.ElementButton;
import cn.nukkit.form.window.FormWindowSimple;
import cn.nukkit.utils.Config;
import cn.reiyans.RYSbank.PluginMain;
import me.onebone.economyapi.EconomyAPI;

public class PlayerUi {
    public static final int MENU = 1831; //The menu id can help you use the ui's contents correctly
    public static void playerui(Player player) {//A Custom ui for creating
        String money1 = EconomyAPI.getInstance().myMoney(player) + "";
        Config config1 = new Config(PluginMain.getInstance().getDataFolder()+"/Accounts/"+player.getPlayer().getName()+".yml",Config.YAML);
        Config config2 = new Config(PluginMain.getInstance().getDataFolder()+"/config.yml",Config.YAML);
        String msg11 = config1.getInt("account")+"";
        String msg3 = config2.getString("BankUITitle");
        String msg4 = config2.getString("BankUISaveMoney");
        String msg5 = config2.getString("BankUITakeMoney");
        String msg6 = config2.getString("BankUIPlayMoney");
        String msg7 = config2.getString("BankUIAccountMoney");
        FormWindowSimple playerform = new FormWindowSimple(msg3,msg6+money1+"\n"+msg7+msg11);
        playerform.addButton(new ElementButton(msg4));
        playerform.addButton(new ElementButton(msg5));
        player.showFormWindow(playerform, MENU); //Send form to player, and add a tag "menu" to the form
    }

}
