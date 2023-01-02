package cn.reiyans.RYSshout.Command;

import cn.nukkit.command.CommandSender;
import cn.nukkit.command.simple.Arguments;
import cn.nukkit.command.simple.Command;
import cn.reiyans.RYSshout.Form.PlayerUI;
import cn.nukkit.Player;


public class PlayerCommand {//register command shout
    @Command(name = "shout", description = "you can shout by this command", usageMessage = "/shout")
    @Arguments(max = 10, min = 0)
    public boolean PlayerCallCommand(CommandSender sender) {//you can run more here
        PlayerUI.playermenu((Player) sender);
        return true;
    }
}