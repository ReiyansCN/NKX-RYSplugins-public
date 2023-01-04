package cn.reiyans.RYSbank.Tasks;

import cn.nukkit.scheduler.PluginTask;
import cn.nukkit.utils.Config;
import cn.reiyans.RYSbank.PluginMain;
import cn.nukkit.Player;

public class Inerest extends PluginTask<PluginMain> {
    private Player player;
    public Inerest(PluginMain owner, Player player) {
        super(owner);
        this.player = player;
    }
    @Override
    public void onRun(int i) {
        String msg99 = PluginMain.getInstance().getConfig().getString("GotTodayInterestMessage");
        player.sendMessage(msg99);
        Config config4 = new Config(PluginMain.getInstance().getDataFolder()+"/Accounts/"+player.getName()+".yml",Config.YAML);
        int interests = PluginMain.getInstance().getConfig().getInt("InterestRate");
        config4.set("account", interests*config4.getInt("account"));
        config4.save();
    }
}