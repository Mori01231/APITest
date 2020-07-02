package com.github.mori01231.apitest;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import static org.bukkit.Bukkit.getLogger;
import static org.bukkit.Bukkit.getServer;


public class ShowBossBarExecutor implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        Player player = (Player) sender;

        String PlayerName = player.getName();

        Integer points = Integer.valueOf(args[0]);

        String MMItemName = APITest.getInstance().getConfig().getString("MythicMobsItem");

        if (APITest.getInstance().getPlayerPoints().getAPI().take(PlayerName, points)) {
            //Success
            getServer().dispatchCommand(getServer().getConsoleSender(), "mm i give " + player.getName() + " " + MMItemName + " " + points);
            getLogger().info(PlayerName + "にMMアイテム " + MMItemName + " を " + points + " 個与えました。");

        } else {
            //Failed, probably not enough points
            sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&3&lポイントが足りません。"));
        }



        return true;
    }
}
