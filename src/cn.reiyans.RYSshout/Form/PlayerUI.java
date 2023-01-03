package cn.reiyans.RYSshout.Form;

import cn.nukkit.form.window.FormWindowCustom;
import cn.nukkit.Player;
import cn.reiyans.RYSshout.PluginMain;
import cn.nukkit.form.element.ElementLabel;
import cn.nukkit.form.element.ElementDropdown;
import cn.nukkit.form.element.ElementInput;
import java.util.Arrays;

public class PlayerUI {
    public static final int MENU = 1931; //The menu id can help you use the ui's contents correctly
    public static void playermenu(Player player) {//A Custom ui for creating
        String msg5 = PluginMain.getInstance().getConfig().getString("ShoutUITitle");
        String msg6 = PluginMain.getInstance().getConfig().getString("ShoutMoodTitle");
        String msg7 = PluginMain.getInstance().getConfig().getString("ShoutColorTitle");
        String msg8 = PluginMain.getInstance().getConfig().getString("ShoutInputTitle");
        String msg11 = PluginMain.getInstance().getConfig().getString("ShoutModeTitle");
        //others msg in the Listener class
        int money1 = PluginMain.getInstance().getConfig().getInt("ChoseMoodPrice", 100);
        int money2 = PluginMain.getInstance().getConfig().getInt("ChoseColorPrice", 100);
        int money3 = PluginMain.getInstance().getConfig().getInt("SendTextPrice", 100);
        FormWindowCustom playerform = new FormWindowCustom(msg5);
        playerform.addElement(new ElementDropdown(msg6+money1, Arrays.asList("Happily", "sadly", "coldly", "silently", "angrily", "afraidly", "excitedly", "doubtfully", "crazily", "joltily")));
        playerform.addElement(new ElementDropdown(msg7+money2, Arrays.asList("white", "red", "orange", "yellow", "green", "cyan", "blue", "purple")));
        playerform.addElement(new ElementLabel(msg8+money3));
        playerform.addElement(new ElementInput(" "));
        playerform.addElement(new ElementDropdown(msg11, Arrays.asList("Chatbar", "Toast", "Actionbar", "Tip", "Popup")));
        player.showFormWindow(playerform, MENU); //Send form to player, and add a tag "menu" to the form
    }

}

