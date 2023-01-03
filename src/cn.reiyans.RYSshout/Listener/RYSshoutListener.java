package cn.reiyans.RYSshout.Listener;

import cn.nukkit.Server;
import cn.nukkit.event.EventHandler;
import cn.nukkit.event.Listener;
import cn.nukkit.event.player.PlayerJoinEvent;
import cn.nukkit.utils.Config;
import cn.reiyans.RYSshout.PluginMain;
import cn.nukkit.event.player.PlayerFormRespondedEvent;
import cn.nukkit.Player;
import cn.nukkit.form.response.FormResponseCustom;
import me.onebone.economyapi.EconomyAPI;
import java.util.HashMap;
import java.util.Map;
import cn.nukkit.event.EventPriority;

public class RYSshoutListener implements Listener {
    @EventHandler(priority = EventPriority.HIGH,ignoreCancelled = true)//Listening player join the server and run function
    public void onPlayerJoin(PlayerJoinEvent jplayer){
        //jplayer is player of geting joinevent.
        PluginMain.getInstance().getLogger().info(jplayer.getPlayer().getName()+PluginMain.getInstance().getDataFolder());
        PluginMain.getInstance().getLogger().info("6666666");
        jplayer.getPlayer().sendMessage("§lWelcome to this server "+jplayer.getPlayer().getName()+", use '/shout' to show your text in the server. ");
    }
    @EventHandler//After Listening player ues 'Shout Page' and run function
    public void FormResponse1(PlayerFormRespondedEvent event) {
        Player player = event.getPlayer();
        int id = event.getFormID(); //get form's id
        if(id == 1931){
            FormResponseCustom Response1 = (FormResponseCustom) event.getResponse();
            //From 0 to star make a sign of the data you need
            String MoodDropDown = Response1.getDropdownResponse(0).getElementContent();
            String ColorDropDown = Response1.getDropdownResponse(1).getElementContent();
            String Textinput = Response1.getInputResponse(3);
            String ModeDropDown = Response1.getDropdownResponse(4).getElementContent();
            int money4 = PluginMain.getInstance().getConfig().getInt("ChoseMoodPrice", 100);
            int money5 = PluginMain.getInstance().getConfig().getInt("ChoseColorPrice", 100);
            int money6 = PluginMain.getInstance().getConfig().getInt("ChoseTextPrice", 100);
            String msg9 = PluginMain.getInstance().getConfig().getString("ShoutSuccess");
            String msg10 = PluginMain.getInstance().getConfig().getString("ShoutFail");
            String msg12 = PluginMain.getInstance().getConfig().getString("ShoutTitle");
            Map<String, String> color = new HashMap<>();
            color.put("white", "§f");
            color.put("red", "§4");
            color.put("orange", "§6");
            color.put("yellow", "§e");
            color.put("green", "§a");
            color.put("cyan", "§3");
            color.put("blue", "§1");
            color.put("purple", "§5");
            String colorC = color.get(ColorDropDown);
            int needmoney = money4+money5+money6;
            if(EconomyAPI.getInstance().myMoney(player) < (double)needmoney){
                player.sendMessage(msg10);
            } else {
                EconomyAPI.getInstance().reduceMoney(player, needmoney);
                String neednoney1 = ""+needmoney;
                player.sendMessage(msg9+neednoney1);
                switch (ModeDropDown){//the mood'type only String, so don't switch
                    case "Chatbar":
                        Server.getInstance().broadcastMessage(msg12+player.getName()+" "+MoodDropDown+":"+colorC+Textinput);
                        break;
                    case "Toast":
                        for(Player playerOnline:Server.getInstance().getOnlinePlayers().values())
                            playerOnline.sendToast("     "+msg12, "     "+player.getName()+" "+MoodDropDown+":"+colorC+Textinput);
                        break;
                    case "Actonbar":
                        for(Player playerOnline:Server.getInstance().getOnlinePlayers().values())
                            playerOnline.sendActionBar(msg12+player.getName()+" "+MoodDropDown+":"+colorC+Textinput);
                        break;
                    case "Tip":
                        for(Player playerOnline:Server.getInstance().getOnlinePlayers().values())
                            playerOnline.sendTip(msg12+player.getName()+" "+MoodDropDown+":"+colorC+Textinput);
                        break;
                    case "Popup":
                        for(Player playerOnline:Server.getInstance().getOnlinePlayers().values())
                            playerOnline.sendPopup(msg12+player.getName()+" "+MoodDropDown+":"+colorC+Textinput);
                        break;
                        }
                }
            }
        }
    @EventHandler//After Listening op ues 'Shoutset Page' and run function
    public void FormResponse2(PlayerFormRespondedEvent event) {
        Player player = event.getPlayer();
        int id = event.getFormID(); //get form's id
        if(id == 1932){
            FormResponseCustom Response2 = (FormResponseCustom) event.getResponse();
            String ChoseMoodPrice = Response2.getInputResponse(1);
            String ChoseColorPrice = Response2.getInputResponse(2);
            String SendTextPrice = Response2.getInputResponse(3);
            Config config = new Config(PluginMain.getInstance().getDataFolder()+"/config.yml",Config.YAML);
            config.set("ChoseMoodPrice",Integer.parseInt(ChoseMoodPrice));
            config.set("ChoseColorPrice",Integer.parseInt(ChoseColorPrice));//change config.yml's key's value
            config.set("SendTextPrice",Integer.parseInt(SendTextPrice));
            config.save();
            player.sendMessage("Save config successfully!");
        }
    }
}

