package cn.reiyans.RYSgift;

import cn.nukkit.Player;
import cn.nukkit.utils.Config;
import tip.utils.variables.BaseVariable;
public class TipV extends BaseVariable{
    public TipV(Player player) {
        super(player);
    }
    @Override
    public void strReplace() {
        Config rysgvar = new Config(PluginMain.getInstance().getDataFolder()+"/players/"+player.getName()+"/info.yml",Config.YAML);
        addStrReplaceString("rysgift-charm", rysgvar.getString("charm"));
        addStrReplaceString("rysgift-level", rysgvar.getString("level"));
    }
}
