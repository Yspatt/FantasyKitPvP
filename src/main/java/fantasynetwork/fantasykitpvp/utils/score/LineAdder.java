package fantasynetwork.fantasykitpvp.utils.score;

import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.Team;

public class LineAdder {

    private Scoreboard sb;
    private Objective obj;

    public LineAdder(Scoreboard sb, Objective obj){
        this.sb = sb;
        this.obj = obj;
    }

    public void addLine(String prefix, String center, String suffix, int index){
        Team t = sb.registerNewTeam("lineScore" + index);
        t.setPrefix(prefix);
        t.setSuffix(suffix);
        FastOfflinePlayer fast = new FastOfflinePlayer(center);
        t.addPlayer(fast);
        obj.getScore(fast.getName()).setScore(index);
    }

}
