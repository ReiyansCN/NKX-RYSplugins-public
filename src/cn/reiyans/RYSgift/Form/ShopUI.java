package cn.reiyans.RYSgift.Form;

import cn.nukkit.form.element.ElementDropdown;
import cn.nukkit.form.element.ElementInput;
import cn.nukkit.form.element.ElementLabel;
import cn.nukkit.utils.Config;
import cn.reiyans.RYSgift.PluginMain;
import cn.nukkit.Player;
import moe.him188.gui.window.FormCustom;
import me.onebone.economyapi.EconomyAPI;

import java.util.*;

public class ShopUI {
    public static void shopui(Player player){
        Config config005 = new Config(PluginMain.getInstance().getDataFolder()+"/config.yml",Config.YAML);
        Config giftinfo = new Config(PluginMain.getInstance().getDataFolder()+"/gifts.yml",Config.YAML);
        Config playerinfo = new Config(PluginMain.getInstance().getDataFolder()+"/players/"+player.getName()+"/info.yml",Config.YAML);
        FormCustom form = new FormCustom(config005.getString("ShopUITitle"));
        List<String> gifts1 = new ArrayList<>();
        int i = 1;
        while (giftinfo.exists("gift" + i + "Name")) {
            form.addElement(new ElementLabel(giftinfo.getString("gift" + i + "Name")+config005.getString("ShopUIGift1")+playerinfo.getInt(giftinfo.getString("gift" + i + "Name"))+config005.getString("ShopUIGift2")
            +giftinfo.getInt("gift" + i + "Charm")+config005.getString("ShopUIGift3")+giftinfo.getInt("gift" + i + "Price")));
            gifts1.add(giftinfo.getString("gift"+i+"Name"));
            i++;
        }
        final int j = i - 1 ;
        final int k = i;
        form.addElement(new ElementDropdown(config005.getString("ShopUILine1"), gifts1));
        form.addElement(new ElementInput(config005.getString("ShopUILine2")));
        player.showFormWindow(form.onResponded(response -> {
            Scanner isint = new Scanner(response.getInputResponse(k));
            if(isint.hasNextInt()) {
                int price = giftinfo.getInt("gift"+(gifts1.indexOf(response.getDropdownResponse(j).getElementContent())+1) + "Price");
                int number = Integer.parseInt(response.getInputResponse(k));
                if(EconomyAPI.getInstance().myMoney(player) >= number*price){
                    EconomyAPI.getInstance().reduceMoney(player, number*price);
                    player.sendMessage(config005.getString("ShopSuccess"));
                    playerinfo.set(response.getDropdownResponse(j).getElementContent(),playerinfo.getInt(response.getInputResponse(k))+number);
                    playerinfo.save();
                }else{
                    player.sendMessage(config005.getString("ShopFail"));
                }
            }else{
                player.sendMessage(config005.getString("UnfairValue"));
            }
        }));
    }

}
