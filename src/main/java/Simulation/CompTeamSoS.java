package Simulation;

import java.util.Comparator;

/**
 * Created by ahngu on 11/13/2017.
 */

class CompTeamSoS implements Comparator<Team> {
    @Override
    public int compare(Team a, Team b) {
        return a.teamSOS > b.teamSOS ? -1 : a.teamSOS == b.teamSOS ? 0 : 1;
    }
}
