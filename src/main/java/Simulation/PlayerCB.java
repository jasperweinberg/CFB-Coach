package Simulation;

import java.util.ArrayList;

public class PlayerCB extends Player {

    //CBCov affects how good he is at covering the pass
    public int ratCoverage;
    //CBSpd affects how good he is at not letting up deep passes
    public int ratSpeed;
    //CBTkl affects how good he is at tackling
    public int ratTackle;
    public int ratJump;

    //Stats
    public int statsTackles;
    public int statsSacks;
    public int statsFumbles;
    public int statsInts;
    public int statsTargets;
    public int statsIncomplete;
    public int statsDefended;

    public int careerTackles;
    public int careerSacks;
    public int careerFumbles;
    public int careerInts;
    public int careerTargets;
    public int careerIncomplete;
    public int careerDefended;

    public PlayerCB(Team t, String nm, int yr, int reg, int trait, int iq, int scout, boolean transfer, boolean wasRS, int pot, int dur, boolean rs, int cov, int spd, int tkl, int jmp) {
        team = t;
        name = nm;
        year = yr;
        gamesStarted = 0;
        gamesPlayed = 0;
        isInjured = false;
        ratOvr = (cov * 2 + spd + tkl) / 4;
        ratPot = pot;
        ratFootIQ = iq;
        ratDur = dur;
        ratCoverage = cov;
        ratSpeed = spd;
        ratTackle = tkl;
        ratJump = jmp;
        isRedshirt = rs;
        if (isRedshirt) year = 0;
        wasRedshirt = wasRS;

        position = "CB";
        region = reg;
        personality = trait;
        recruitRating = scout;

        troubledTimes = 0;

        wonHeisman = false;
        wonAllAmerican = false;
        wonAllConference = false;
        statsWins = 0;

        careerGames = 0;
        careerHeismans = 0;
        careerAllAmerican = 0;
        careerAllConference = 0;
        careerWins = 0;

        statsTackles = 0;
        statsSacks = 0;
        statsFumbles = 0;
        statsInts = 0;
        statsTargets = 0;
        statsIncomplete = 0;
        statsDefended = 0;

        careerTackles = 0;
        careerSacks = 0;
        careerFumbles = 0;
        careerInts = 0;
        careerTargets = 0;
        careerDefended = 0;
    }

    public PlayerCB(Team t, String nm, int yr, int reg, int trait, int iq, int scout, boolean transfer, boolean wasRS, int pot, int dur, boolean rs, int cGamesPlayed, int cWins, int cHeismans, int cAA, int cAC,
                    int cov, int spd, int tkl, int jmp, int cTackles, int cSacks, int cFumbles, int cInts, int cTar, int cInc, int cDef) {
        team = t;
        name = nm;
        year = yr;
        gamesStarted = 0;
        gamesPlayed = 0;
        isInjured = false;
        ratOvr = (cov * 2 + spd + tkl + jmp) / 5;
        ratPot = pot;
        ratFootIQ = iq;
        ratDur = dur;
        ratCoverage = cov;
        ratSpeed = spd;
        ratTackle = tkl;
        ratJump = jmp;
        isRedshirt = rs;
        if (isRedshirt) year = 0;
        wasRedshirt = wasRS;

        isTransfer = transfer;
        region = reg;
        personality = trait;
        troubledTimes = 0;
        recruitRating = scout;

        position = "CB";

        wonHeisman = false;
        wonAllAmerican = false;
        wonAllConference = false;
        statsWins = 0;

        careerGames = cGamesPlayed;
        careerHeismans = cHeismans;
        careerAllAmerican = cAA;
        careerAllConference = cAC;
        careerWins = cWins;

        statsTackles = 0;
        statsSacks = 0;
        statsFumbles = 0;
        statsInts = 0;
        statsTargets = 0;
        statsIncomplete = 0;
        statsDefended = 0;

        careerTackles = cTackles;
        careerSacks = cSacks;
        careerFumbles = cFumbles;
        careerInts = cInts;
        careerTargets = cTar;
        careerIncomplete = cInc;
        careerDefended = cDef;
    }

    public PlayerCB(String nm, int yr, int stars, Team t) {
        name = nm;
        year = yr;
        team = t;
        gamesStarted = 0;
        gamesPlayed = 0;
        isInjured = false;
        ratPot = (int) (attrBase + 50 * Math.random());
        ratFootIQ = (int) (attrBase + 50 * Math.random());
        ratDur = (int) (attrBase + 50 * Math.random());
        ratCoverage = (int) (ratBase + year*yearFactor + stars*starFactor - ratTolerance*Math.random());
        ratSpeed = (int) (ratBase + year*yearFactor + stars*starFactor - ratTolerance*Math.random());
        ratTackle = (int) ((ratBase-10) + year*yearFactor + stars*starFactor - ratTolerance*Math.random());
        ratJump = (int) (ratBase + year*yearFactor + stars*starFactor - ratTolerance*Math.random());
        ratOvr = (ratCoverage * 2 + ratSpeed + ratTackle + ratJump) / 5;
        position = "CB";
        region = (int)(Math.random()*5);
        personality = (int) (attrBase + 50 * Math.random());

        recruitRating = getScoutingGrade();

        recruitTolerance = (int)((60 - team.teamPrestige)/cbImportance);
        cost = (int)((Math.pow((float) ratOvr - costBaseRating, 2)/5) + (int)Math.random()*recruitTolerance);

        cost = (int)(cost/cbImportance);

        double locFactor = Math.abs(team.location - region) - 2.5;
        cost = cost + (int)(Math.random()*(locFactor * locationDiscount));
        if (cost < 0) cost = (int)Math.random()*7+1;

        troubledTimes = 0;

        wonHeisman = false;
        wonAllAmerican = false;
        wonAllConference = false;
        statsWins = 0;

        careerGames = 0;
        careerHeismans = 0;
        careerAllAmerican = 0;
        careerAllConference = 0;
        careerWins = 0;

        statsTackles = 0;
        statsSacks = 0;
        statsFumbles = 0;
        statsInts = 0;
        statsTargets = 0;
        statsIncomplete = 0;
        statsDefended = 0;

        careerTackles = 0;
        careerSacks = 0;
        careerFumbles = 0;
        careerInts = 0;
        careerTargets = 0;
        careerIncomplete = 0;
        careerDefended = 0;
    }

    public PlayerCB(String nm, int yr, int stars, Team t, boolean custom) {
        name = nm;
        year = yr;
        team = t;
        gamesStarted = 0;
        gamesPlayed = 0;
        isInjured = false;
        ratPot = (int) (attrBase + 50 * Math.random());
        ratFootIQ = (int) (attrBase + 50 * Math.random());
        ratDur = (int) (attrBase + 50 * Math.random());
        ratCoverage = (int) (ratBase + stars * customFactor - ratTolerance * Math.random());
        ratSpeed = (int) (ratBase + stars * customFactor - ratTolerance * Math.random());
        ratTackle = (int) (ratBase + stars * customFactor - ratTolerance * Math.random());
        ratJump = (int) (ratBase + stars * customFactor - ratTolerance * Math.random());
        ratOvr = (ratCoverage * 2 + ratSpeed + ratTackle + ratJump) / 5;
        position = "CB";
        region = (int)(Math.random()*5);
        personality = (int) (attrBase + 50 * Math.random());

        if (yr == 1) {
            recruitRating = 0;
        } else {
            recruitRating = getScoutingGrade();
        }

        troubledTimes = 0;

        wonHeisman = false;
        wonAllAmerican = false;
        wonAllConference = false;
        statsWins = 0;

        careerGames = 0;
        careerHeismans = 0;
        careerAllAmerican = 0;
        careerAllConference = 0;
        careerWins = 0;

        statsTackles = 0;
        statsSacks = 0;
        statsFumbles = 0;
        statsInts = 0;
        statsTargets = 0;
        statsIncomplete = 0;
        statsDefended = 0;

        careerTackles = 0;
        careerSacks = 0;
        careerFumbles = 0;
        careerInts = 0;
        careerTargets = 0;
        careerIncomplete = 0;
        careerDefended = 0;
    }

    @Override
    public int getHeismanScore() {
        return statsTackles*25 + statsSacks*425 + statsFumbles*425 + statsInts*425 + statsDefended*100 + ratOvr*10 + team.teamPrestige*2 + team.confPrestige*3 + (120 - team.rankTeamPollScore)*2;
    }

    @Override
    public int getCareerScore() {
        return statsTackles*25 + statsSacks*425 + statsFumbles*425 + statsInts*425 + statsDefended*100 + ratOvr*10 +
                careerTackles*25 + careerSacks*425 + careerFumbles*425 + careerInts*425 + careerDefended*100 + ratOvr*year*10;
    }

    @Override
    public void advanceSeason() {
        int oldOvr = ratOvr;
        progression = (ratPot * 3 + team.HC.get(0).ratTalent * 2 + team.HC.get(0).ratDef) / 6;
        int games = gamesStarted + (gamesPlayed-gamesStarted)/3;


        if (!isMedicalRS) {
            year++;
            if (wonAllConference) ratPot += (int)Math.random()*allConfPotBonus;
            if (wonAllAmerican) ratPot += (int)Math.random()*allAmericanBonus;
            if (year > 2 && games < minGamesPot) ratPot -= (int) (Math.random() * 15);

            ratFootIQ += (int) (Math.random() * (progression + games - 35)) / 10;
            ratCoverage += (int) (Math.random() * (progression + games - 35)) / 10;
            ratSpeed += (int) (Math.random() * (progression + games - 35)) / 10;
            ratTackle += (int) (Math.random() * (progression + games - 35)) / 10;
            ratJump += (int) (Math.random() * (progression + games - 25)) / 10;
            if (Math.random() * 100 < progression) {
                //breakthrough
                ratCoverage += (int) (Math.random() * (progression + games - 40)) / 10;
                ratSpeed += (int) (Math.random() * (progression + games - 40)) / 10;
                ratTackle += (int) (Math.random() * (progression + games - 40)) / 10;
                ratJump += (int) (Math.random() * (progression + games - 30)) / 10;
            }
        }
        
        ratOvr = (ratCoverage * 2 + ratSpeed + ratTackle + ratJump) / 5;
        ratImprovement = ratOvr - oldOvr;

        careerGames += gamesPlayed;
        careerWins += statsWins;

        careerTackles += statsTackles;
        careerSacks += statsSacks;
        careerFumbles += statsFumbles;
        careerInts += statsInts;
        careerTargets += statsTargets;
        careerIncomplete += statsIncomplete;
        careerDefended += statsDefended;

        statsTackles = 0;
        statsSacks = 0;
        statsFumbles = 0;
        statsInts = 0;
        statsTargets = 0;
        statsIncomplete = 0;
        statsDefended = 0;

        if (wonHeisman) careerHeismans++;
        if (wonAllAmerican) careerAllAmerican++;
        if (wonAllConference) careerAllConference++;

        if (isTransfer) {
            isTransfer = false;
            year -= 1;
        }

        if (isRedshirt) wasRedshirt = true;

    }

    @Override
    public ArrayList<String> getDetailStatsList(int games) {
        ArrayList<String> pStats = new ArrayList<>();
        pStats.add("Tackles: " + (statsTackles) + " >Sacks: " + (statsSacks));
        pStats.add("Fumbles: " + (statsFumbles) + " >Interceptions: " + (statsInts));
        pStats.add("Defended: " + statsDefended + ">Shutdown Pct: " + (100 * (statsIncomplete) / (statsTargets + 1)) + "%");
        pStats.add("Games: " + gamesPlayed + " (" + statsWins + "-" + (gamesStarted - statsWins) + ")" + "> ");
        pStats.add("Coverage: " + getLetterGrade(ratCoverage) + ">Jumping: " + getLetterGrade(ratJump));
        pStats.add("Tackling: " + getLetterGrade(ratTackle) + ">Speed: " + getLetterGrade(ratSpeed));
        pStats.add("Durability: " + getLetterGrade(ratDur) + ">Football IQ: " + getLetterGrade(ratFootIQ));
        pStats.add("Home Region: " + getRegion(region) + ">Personality: " + getLetterGrade(personality));
        pStats.add("Scout Grade: " + getScoutingGradeString() + " > " + getStatus());
        pStats.add(" > ");
        return pStats;
    }

    @Override
    public ArrayList<String> getDetailAllStatsList(int games) {
        ArrayList<String> pStats = new ArrayList<>();
        pStats.add("Tackles: " + (statsTackles) + " >Sacks: " + (statsSacks));
        pStats.add("Fumbles: " + (statsFumbles) + " >Interceptions: " + (statsInts));
        pStats.add("Defended: " + statsDefended + ">Shutdown Pct: " + (100 * (statsIncomplete) / (statsTargets + 1)) + "%");
        pStats.add("Coverage: " + getLetterGrade(ratCoverage) + ">Jumping: " + getLetterGrade(ratJump));
        pStats.add("Tackling: " + getLetterGrade(ratTackle) + ">Speed: " + getLetterGrade(ratSpeed));
        pStats.add("Durability: " + getLetterGrade(ratDur) + ">Football IQ: " + getLetterGrade(ratFootIQ));
        pStats.add("Home Region: " + getRegion(region) + ">Personality: " + getLetterGrade(personality));
        pStats.add("Scout Grade: " + getScoutingGradeString() + " > " + getStatus());
        pStats.add(" > ");
        pStats.add("[B]CAREER STATS:");
        pStats.addAll(getCareerStatsList());
        return pStats;
    }

    @Override
    public ArrayList<String> getCareerStatsList() {
        ArrayList<String> pStats = new ArrayList<>();
        pStats.add("Tackles: " + (statsTackles + careerTackles) + " >Sacks: " + (statsSacks + careerSacks));
        pStats.add("Fumbles: " + (statsFumbles + careerFumbles) + " >Interceptions: " + (statsInts + careerInts));
        pStats.add("Defended: " + (statsDefended + careerDefended) + ">Shutdown Pct: " + (100 * (statsIncomplete + careerIncomplete) / (statsTargets + careerTargets + 1)) + "%");
        pStats.addAll(super.getCareerStatsList());
        return pStats;
    }


    @Override
    public String getInfoForLineup() {
        if (injury != null)
            return getInitialName() + " [" + getYrStr() + "] " + ratOvr + "/" + getLetterGrade(ratPot) + " " + injury.toString();
        return getInitialName() + " [" + getYrStr() + "] " + ratOvr + "/" + getLetterGrade(ratPot) + " (" +
                getLetterGrade(ratCoverage) + ", " + getLetterGrade(ratSpeed) + ", " + getLetterGrade(ratTackle) + ", " + getLetterGrade(ratJump) + ")";
    }

}
