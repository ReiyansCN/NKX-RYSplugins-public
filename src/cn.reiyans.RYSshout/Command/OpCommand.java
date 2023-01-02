package cn.reiyans.RYSshout.Command;

import cn.nukkit.command.CommandSender;
import cn.nukkit.command.simple.Arguments;
import cn.nukkit.command.simple.Command;

public class OpCommand {//register command shoutset
    @Command(name = "shoutset", description = "reset some informations about shout", usageMessage = "/shoutset")
    @Arguments(max = 10, min = 0)
    public boolean onHelloCommand(CommandSender sender, String label, String[] args) {
        //now, star run your code

        return true;
    }
}
