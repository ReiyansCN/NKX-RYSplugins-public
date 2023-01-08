package cn.reiyans.RYSgift.Form;

import cn.nukkit.Player;
import cn.nukkit.Server;
import cn.nukkit.form.element.ElementDropdown;
import cn.nukkit.form.element.ElementInput;
import cn.nukkit.utils.Config;
import cn.reiyans.RYSgift.PluginMain;
import moe.him188.gui.window.FormCustom;
import java.text.SimpleDateFormat;
import java.util.*;

public class SendUI {
    public static void sendui(Player player) {
        Config config004 = new Config(PluginMain.getInstance().getDataFolder()+"/config.yml",Config.YAML);
        Config configgifts1 = new Config(PluginMain.getInstance().getDataFolder()+"/gifts.yml",Config.YAML);
        Config playergift = new Config(PluginMain.getInstance().getDataFolder()+"/players/"+player.getName()+"/info.yml",Config.YAML);
        Config playerdata1 = new Config(PluginMain.getInstance().getDataFolder()+"/players/"+player.getName()+"/send.yml",Config.YAML);
        int i = 1;
        List<String> gifts = new ArrayList<>();
        while (configgifts1.exists("gift" + i + "Name")){
            gifts.add(configgifts1.getString("gift"+i+"Name"));
            i++;
        }
        FormCustom form = new FormCustom(config004.getString("SendUITitle"));
        form.addElement(new ElementDropdown(config004.getString("SendUILine1"), gifts));
        form.addElement(new ElementInput(config004.getString("SendUILine2")));
        form.addElement(new ElementInput(config004.getString("SendUILine3")));
        form.addElement(new ElementInput(config004.getString("SendUILine4"),"友谊天长地久"));
        player.showFormWindow(form.onResponded(response -> {
            String player2 = response.getInputResponse(2);
            Config realplayer = new Config(PluginMain.getInstance().getDataFolder()+"/players/"+response.getInputResponse(2)+"/info.yml",Config.YAML);
            if(!response.getInputResponse(2).equals(player.getName())){
                if(realplayer.exists("charm")){
                    Scanner isint = new Scanner(response.getInputResponse(1));
                    if(isint.hasNextInt()) {
                        if (playergift.getInt(response.getDropdownResponse(0).getElementContent()) >= Integer.parseInt(response.getInputResponse(1))) {
                            player.sendMessage(config004.getString("SendSuccess1") + response.getDropdownResponse(0).getElementContent() + "§fx§f" + response.getInputResponse(1) + config004.getString("SendSuccess2"));
                            playergift.set(response.getDropdownResponse(0).getElementContent(), playergift.getInt(response.getDropdownResponse(0).getElementContent()) - Integer.parseInt(response.getInputResponse(1)));
                            playergift.save();
                            Date time1 = new Date();
                            SimpleDateFormat time2 = new SimpleDateFormat("yyyy/MM/dd HH:mm");
                            playerdata1.set(playerdata1.getInt("data") + "", "[" + time2.format(time1) + "]" + "您送给" + player2 + response.getDropdownResponse(0).getElementContent() + "§fx§f" + response.getInputResponse(1) + "并留言:" + response.getInputResponse(3));
                            playerdata1.set("data", playerdata1.getInt("data") + 1);
                            if (playerdata1.getInt("data") > 10) {
                                playerdata1.set("data", 1);
                            }
                            playerdata1.save();
                            Config playerdata2 = new Config(PluginMain.getInstance().getDataFolder()+"/players/" + player2 + "/receive.yml", Config.YAML);
                            playerdata2.set(playerdata2.getInt("data") + "", "[" + time2.format(time1) + "]" + player.getName() + "送给您" + response.getDropdownResponse(0).getElementContent() + "§fx§f" + response.getInputResponse(1) + "并留言:" + response.getInputResponse(3));
                            if(Server.getInstance().getPlayer(player2) != null){
                                Server.getInstance().getPlayer(player2).sendMessage(player.getName() + "送给您" + response.getDropdownResponse(0).getElementContent() + "§fx§f" + response.getInputResponse(1) + "并留言:" + response.getInputResponse(3));
                            }
                            playerdata2.set("data", playerdata2.getInt("data") + 1);
                            if (playerdata2.getInt("data") > 10) {
                                playerdata2.set("data", 1);
                                playerdata2.save();
                            }
                            playerdata2.save();
                            realplayer.set("charm", realplayer.getInt("charm") + configgifts1.getInt("gift" + (gifts.indexOf(response.getDropdownResponse(0)) + 1) + "Charm") * Integer.parseInt(response.getInputResponse(1)));
                            realplayer.save();
                        } else {
                            player.sendMessage(config004.getString("GiftisLess"));
                        }
                    }else{
                        player.sendMessage(config004.getString("UnfairValue"));
                    }
                }else{
                    player.sendMessage(config004.getString("PlayerisNull"));
                }
            }else{
                player.sendMessage(config004.getString("IsPlayerself"));
            }
        }));
    }
}
