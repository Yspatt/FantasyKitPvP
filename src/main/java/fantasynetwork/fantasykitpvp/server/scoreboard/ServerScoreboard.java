package fantasynetwork.fantasykitpvp.server.scoreboard;

import fantasynetwork.fantasykitpvp.FantasyKitPvP;
import fantasynetwork.fantasykitpvp.users.User;
import fantasynetwork.fantasykitpvp.utils.score.LineAdder;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.Team;

public class ServerScoreboard {

    public static BukkitTask task;

    public static void build(Player p){
        Scoreboard sb = Bukkit.getServer().getScoreboardManager().getNewScoreboard();
        Objective obj = sb.registerNewObjective("score1", "dummy");
        obj.setDisplaySlot(DisplaySlot.SIDEBAR);
        obj.setDisplayName("§5§lFANTASY §8§l- §6§lKITPVP");
        LineAdder line = new LineAdder(sb, obj);
        p.getScoreboard().clearSlot(DisplaySlot.SIDEBAR);


        line.addLine(" ", "§0", "", 10);
        line.addLine(" ", "§fNome:§a ", "Indefinido", 9);
        line.addLine(" ", "§fCargo: ", "Indefinido", 8);
       // line.addLine(" ", "§fClan: §7", "Indefinido", 8);
        line.addLine(" ", "§1", "", 7);
        line.addLine(" ", "§fMatou:§a ","Indefinido" , 6);
        line.addLine(" ", "§fMorreu:§c ", "Indefinido", 5);
        line.addLine(" ", "§fCombo:§6 ", "Indefinido", 4);
        line.addLine(" ", "§2", "", 3);
        line.addLine(" ", "§fFNCoins: §2", "Indefinido", 2);
        line.addLine(" ", "§3", "", 1);
        line.addLine("", "§efantasynetwork.com.br", "", 0);

        p.setScoreboard(sb);

    }
    public static void startTask(){
        task = new BukkitRunnable(){
            @Override
            public void run() {
                Bukkit.getOnlinePlayers().forEach(p ->{
                    User user = FantasyKitPvP.user.getUser(p.getUniqueId());
                    Scoreboard sb = p.getScoreboard();
                    Team name = sb.getTeam("lineScore9");
                    Team cargo = sb.getTeam("lineScore8");
                   // Team clan = sb.getTeam("lineScore8");
                    Team matou = sb.getTeam("lineScore6");
                    Team morreu = sb.getTeam("lineScore5");
                    Team combo = sb.getTeam("lineScore4");
                    Team coins = sb.getTeam("lineScore2");
                    cargo.setSuffix(ChatColor.translateAlternateColorCodes('&', FantasyKitPvP.chat.getPlayerPrefix(p)));
                    name.setSuffix(p.getDisplayName());
                    //clan.setSuffix("[Clan]");
                    matou.setSuffix("" +user.getKills());
                    morreu.setSuffix("" +user.getDeaths());
                    combo.setSuffix("" +user.getStreak());
                    coins.setSuffix("" + user.getCoins());
                });
            }
        }.runTaskTimerAsynchronously(FantasyKitPvP.instance,0,2*20);
    }
}
