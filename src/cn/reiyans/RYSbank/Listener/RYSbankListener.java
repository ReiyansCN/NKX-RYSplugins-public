package cn.reiyans.RYSbank.Listener;

import cn.nukkit.Player;
import cn.nukkit.event.EventHandler;
import cn.nukkit.event.EventPriority;
import cn.nukkit.event.Listener;
import cn.nukkit.event.player.PlayerFormRespondedEvent;
import cn.nukkit.event.player.PlayerJoinEvent;
import cn.nukkit.form.response.FormResponseCustom;
import cn.nukkit.form.response.FormResponseSimple;
import cn.nukkit.utils.Config;
import cn.reiyans.RYSbank.Form.*;
import cn.reiyans.RYSbank.PluginMain;
import me.onebone.economyapi.EconomyAPI;
import java.util.Scanner;

public class RYSbankListener implements Listener{
    @EventHandler(priority = EventPriority.HIGH,ignoreCancelled = true)//Listening player join the server and run function
    public void onPlayerJoin(PlayerJoinEvent jelayer){
        //jplayer is player of geting joinevent.
        Config config1 = new Config(PluginMain.getInstance().getDataFolder()+"/Accounts/"+jelayer.getPlayer().getName()+".yml",Config.YAML);
        if (config1.exists("account")) {
        }else{
            PluginMain.getInstance().saveResource(PluginMain.getInstance().getDataFolder() + "/Accounts/" + jelayer.getPlayer().getName() + ".yml", true);
            config1.set("account", 0);
            config1.save();
        }
    }
    @EventHandler//After Listening player ues 'Shout Page' and run function
    public void FormResponse1(PlayerFormRespondedEvent event) {
        Player player = event.getPlayer();
        int id = event.getFormID(); //get form's id
        if (id == 1831) {
            FormResponseSimple Response1 = (FormResponseSimple) event.getResponse();
            int clickedButtonId = Response1.getClickedButtonId();
            switch (clickedButtonId) {
                case 0:
                    BankSaveUi.banksaveui(player);
                    break;
                case 1:
                    BankTakeUi.banktakeui(player);
                    break;
            }
        }
    }
    @EventHandler//After Listening op ues 'Shoutset Page' and run function
    public void FormResponse2(PlayerFormRespondedEvent event) {
        Player player = event.getPlayer();
        int id = event.getFormID(); //get form's id
        if(id == 1832){
            FormResponseCustom Response2 = (FormResponseCustom) event.getResponse();
            String savemoney = Response2.getInputResponse(0);
            Config config1 =  new Config(PluginMain.getInstance().getDataFolder()+"/config.yml",Config.YAML);
            String msg21 = config1.getString("SaveMoneySuccess");
            String msg22 = config1.getString("SaveMoneyFail");
            Config config2 = new Config(PluginMain.getInstance().getDataFolder()+"/Accounts/"+player.getName()+".yml",Config.YAML);
            Scanner moneyisint = new Scanner(savemoney);
            if(moneyisint.hasNextInt()){
                if(EconomyAPI.getInstance().myMoney(player) >= Integer.parseInt(savemoney)) {
                    config2.set("account", config2.getInt("account") + Integer.parseInt(savemoney));
                    EconomyAPI.getInstance().reduceMoney(player, Integer.parseInt(savemoney));
                    config2.save();
                    player.sendMessage(msg21+savemoney);
                }else{
                    player.sendMessage(msg22);
                }
            }
        }
    }
    @EventHandler//After Listening op ues 'Shoutset Page' and run function
    public void FormResponse3(PlayerFormRespondedEvent event) {
        Player player = event.getPlayer();
        int id = event.getFormID(); //get form's id
        if(id == 1833){
            FormResponseCustom Response3 = (FormResponseCustom) event.getResponse();
            String takemoney = Response3.getInputResponse(0);
            Config config4 = new Config(PluginMain.getInstance().getDataFolder()+"/config.yml",Config.YAML);
            String msg23 = config4.getString("TakeMoneySuccess");
            String msg24 = config4.getString("TakeMoneyFail");
            Config config3 = new Config(PluginMain.getInstance().getDataFolder()+"/Accounts/"+player.getName()+".yml",Config.YAML);
            Scanner moneyisint = new Scanner(takemoney);
            if(moneyisint.hasNextInt()){
                if(config3.getInt("account") >= Integer.parseInt(takemoney)) {
                    config3.set("account", config3.getInt("account") - Integer.parseInt(takemoney));
                    EconomyAPI.getInstance().addMoney(player, Integer.parseInt(takemoney));
                    config3.save();
                    player.sendMessage(msg23+takemoney);
                }else{
                    player.sendMessage(msg24);
                }
            }
        }
    }
}
