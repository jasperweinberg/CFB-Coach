package Simulation;

import java.text.DecimalFormat;
import java.util.ArrayList;

/**
 * Base player class that others extend. Has name, overall, potential, and football IQ.
 *
 * @author Achi
 */
public class Player {

    public Team team;
    public String name;
    public String position;
    public int year;
    public int ratOvr;
    public int ratPot;
    public int ratFootIQ;
    public int ratDur;
    public int ratImprovement;
    public int cost;
    public int progression;
    public int region;
    public int personality;
    public int height;
    public int weight;
    public boolean isSuspended;
    public int weeksSuspended;
    public int troubledTimes;

    public int gamesStarted;
    public int gamesPlayed;
    public int statsWins;
    public boolean wonHeisman;
    public boolean wonAllAmerican;
    public boolean wonAllConference;

    public int careerGames;
    public int careerHeismans;
    public int careerAllAmerican;
    public int careerAllConference;
    public int careerWins;

    public boolean isRedshirt;
    public boolean wasRedshirt;
    public boolean isMedicalRS;
    public boolean isTransfer;
    public boolean isGradTransfer;
    public boolean isWalkOn;

    public int recruitRating; // 0 - 5 0 = walk-on ; 1-5 = star scout rating

    public boolean isInjured;
    public Injury injury;

    public int heightBase = 60;
    public int weightBase = 160;

    public int attrBase = 50;
    public int ratBase = 60;
    public int yearFactor = 5;
    public double starFactor = 2.5;
    public double customFactor = 4.5;
    public int ratTolerance = 20;
    public int recruitTolerance = 50;
    public int costBaseRating = 35;
    public int locationDiscount = 15;
    public int minGamesPot = 4;
    public int allConfPotBonus = 5;
    public int allAmericanBonus = 5;

    public double qbImportance = 1;
    public double rbImportance = 1.5;
    public double wrImportance = 2;
    public double teImportance = 4;
    public double olImportance = 2.5;
    public double kImportance = 5;
    public double dlImportance = 2.5;
    public double lbImportance = 2.5;
    public double cbImportance = 2.5;
    public double sImportance = 1.5;

    //game simulation data
    public int gameSnaps;
    public int gameFatigue;
    public double gameSim; //will be used for game sim calculations
    public int posDepth;
    public int gamePassAtempts;
    public int gamePassComplete;
    public int gamePassYards;
    public int gamePassTDs;
    public int gamePassInts;
    public int gameRushAttempts;
    public int gameRushYards;
    public int gameRushTDs;
    public int gameTargets;
    public int gameReceptions;
    public int gameRecYards;
    public int gameRecTDs;
    public int gameDrops;
    public int gameFumbles;
    public int gameTackles;
    public int gameSacks;
    public int gameInterceptions;
    public int gameDefended;
    public int gameIncomplete;
    public int gameFGAttempts;
    public int gameFGMade;
    public int gameXPAttempts;
    public int gameXPMade;
    public DecimalFormat df2 = new DecimalFormat(".##");

    protected final String[] letterGrades = {"F", "F+", "D", "D+", "C", "C+", "B", "B+", "A", "A+"};

    public String getYrStr() {
        if (year == 0) {
            return "RS";
        } else if (year == 1) {
            return "Fr";
        } else if (year == 2) {
            return "So";
        } else if (year == 3) {
            return "Jr";
        } else if (year == 4) {
            return "Sr";
        }
        return "ERROR";
    }

    public void advanceSeason() {
        //add stuff
        if (!isMedicalRS) year++;
    }

    public int getHeismanScore() {
        int adjGames = gamesStarted;
        if (adjGames > 11) adjGames = 11;
        return ratOvr * adjGames + team.confPrestige * 5;
    }

    public int getCareerScore() {
        int adjGames = careerGames;
        return ratOvr * adjGames + team.confPrestige * 5;
    }

    public String getInitialName() {
        String[] names = name.split(" ");
        return names[0].substring(0, 1) + ". " + names[1];
    }

    public int getScoutingGrade(){
        int pRat;
        if (year < 2) {
            if (ratOvr > team.five) pRat = 5;
            else if (ratOvr > team.four) pRat = 4;
            else if (ratOvr > team.three) pRat = 3;
            else if (ratOvr > team.two) pRat = 2;
            else pRat = 1;
        } else {
            int calcOvr = ratOvr - (year * (100-ratPot)/7);
            if (calcOvr > team.five) pRat = 5;
            else if (calcOvr > team.four) pRat = 4;
            else if (calcOvr > team.three) pRat = 3;
            else if (calcOvr> team.two) pRat = 2;
            else pRat = 1;
        }

        return pRat;
    }

    public String getScoutingGradeString() {
        String grade;

        if (recruitRating == 0) {
            grade = "Walk-On";
        } else if (recruitRating == 1) {
            grade = "1 Star";
        } else if (recruitRating == 2) {
            grade = "2 Star";
        } else if (recruitRating == 3) {
            grade = "3 Star";
        } else if (recruitRating == 4) {
            grade = "4 star";
        } else {
            grade = "5 Star";
        }

        return grade;
    }

    public String getHCString() {
        return "Head Coach " + name + "> ";
    }

    public String getPosNameYrOvrPot_Str() {
        if (injury != null) {
            return "[I]" + position + " " + getInitialName() + " [" + getYrStr() + "] Ovr: " + ratOvr + ">" + injury.toString();
        }
        if (isTransfer) {
            return "[T]" + position + " " + getInitialName() + " [" + getYrStr() + "] Ovr: " + ratOvr + ">Transfer";
        }
        if (isSuspended) {
            return "[S]" + position + " " + getInitialName() + " [" + getYrStr() + "] Ovr: " + ratOvr + ">Suspended (" + weeksSuspended + "wk)";
        }
        if (isWalkOn) {
            return position + " " + name + " [" + getYrStr() + "]>" + "Ovr: " + ratOvr + ", Pot: " + getLetterGrade(ratPot) + " [WO]";
        }
        return position + " " + name + " [" + getYrStr() + "]>" + "Ovr: " + ratOvr + ", Pot: " + getLetterGrade(ratPot);
    }

    public String getPosNameYrOvrPotTra_Str() {
        return position + " " + name + " [" + getYrStr() + "]>" + "Ovr: " + ratOvr + " [Transfer]";
    }

    public String getPosNameYrOvrPot_OneLine() {
        if (injury != null) {
            return position + " " + getInitialName() + " [" + getYrStr() + "] Ovr: " + ratOvr + " " + injury.toString();
        }
        return position + " " + getInitialName() + " [" + getYrStr() + "] " + "Ovr: " + ratOvr + ", Pot: " + getLetterGrade(ratPot);
    }

    public String getPosNameYrOvr_Str() {
        return team.name + " " + position + " " + name + " [" + getYrStr() + "] Ovr: " + ratOvr;
    }

    public String getYrOvrPot_Str() {
        return "[" + getYrStr() + "] Ovr: " + ratOvr + ", Pot: " + getLetterGrade(ratPot);
    }

    public String getPosNameYrOvrPot_NoInjury() {
        return position + " " + getInitialName() + " [" + getYrStr() + "] Ovr: " + ratOvr + ", Pot: " + getLetterGrade(ratPot);
    }

    public String getMockDraftStr() {
        return position + " " + getInitialName() + " [" + getYrStr() + "]>" + team.strRep();
    }

    /**
     * Convert a rating into a letter grade. 90 -> A, 80 -> B, etc
     */
    protected String getLetterGrade(String num) {
        int ind = (Integer.parseInt(num) - 50) / 5;
        if (ind > 9) ind = 9;
        if (ind < 0) ind = 0;
        return letterGrades[ind];
    }

    /**
     * Convert a rating into a letter grade for potential, so 50 is a C instead of F
     */
    protected String getLetterGradePot(String num) {
        int ind = (Integer.parseInt(num)) / 10;
        if (ind > 9) ind = 9;
        if (ind < 0) ind = 0;
        return letterGrades[ind];
    }

    /**
     * Convert a rating into a letter grade. 90 -> A, 80 -> B, etc
     */
    protected String getLetterGrade(int num) {
        int ind = (num - 50) / 5;
        if (ind > 9) ind = 9;
        if (ind < 0) ind = 0;
        return letterGrades[ind];
    }

    /**
     * Convert a rating into a letter grade for potential, so 50 is a C instead of F
     */
    protected String getLetterGradePot(int num) {
        int ind = num / 10;
        if (ind > 9) ind = 9;
        if (ind < 0) ind = 0;
        return letterGrades[ind];
    }

    public ArrayList<String> getDetailStatsList(int games) {
        return null;
    }

    public ArrayList<String> getDetailAllStatsList(int games) {
        return null;
    }

    public ArrayList<String> getCareerStatsList() {
        ArrayList<String> pStats = new ArrayList<>();
        pStats.add("Games: " + (gamesStarted + careerGames) + " (" + (statsWins + careerWins) + "-" + (gamesStarted + careerGames - (statsWins + careerWins)) + ")"
                + ">Yrs: " + getYearsPlayed());
        pStats.add("Awards: " + getAwards() + "> ");
        return pStats;
    }

    public String getYearsPlayed() {
        int startYear = team.league.getYear() - year + 1;
        int endYear = team.league.getYear();
        return startYear + "-" + endYear;
    }

    public String getAwards() {
        ArrayList<String> awards = new ArrayList<>();
        int heis = careerHeismans + (wonHeisman ? 1 : 0);
        int aa = careerAllAmerican + (wonAllAmerican ? 1 : 0);
        int ac = careerAllConference + (wonAllConference ? 1 : 0);
        if (heis > 0) awards.add(heis + "x POTY");
        if (aa > 0) awards.add(aa + "x All-Amer");
        if (ac > 0) awards.add(ac + "x All-Conf");

        String awardsStr = "";
        for (int i = 0; i < awards.size(); ++i) {
            awardsStr += awards.get(i);
            if (i != awards.size() - 1) awardsStr += ", ";
        }

        return awardsStr;
    }

    public String getInfoForLineup() {
        return null;
    }

    public String getInfoLineupInjury() {
        if (injury != null) {
            return getInitialName() + " [" + getYrStr() + "] " + injury.toString();
        }
        return getInitialName() + " [" + getYrStr() + "] " + "Ovr: " + ratOvr + ", Pot: " + getLetterGrade(ratPot);
    }

    public String getInfoLineupTransfer() {
        return getInitialName() + " [" + getYrStr() + "] " + "Ovr: " + ratOvr + "  Transfer";
    }

    public String getInfoLineupSuspended() {
        return getInitialName() + " [" + getYrStr() + "] " + "Ovr: " + ratOvr + "  Suspended";

    }
        public int getGames() {
        int games = gamesStarted + (gamesPlayed - gamesStarted);
        if (games == 0) return 1;
        else return games;
    }

    public static int getPosNumber(String pos) {
        switch (pos) {
            case "QB":
                return 0;
            case "RB":
                return 1;
            case "WR":
                return 2;
            case "TE":
                return 3;
            case "OL":
                return 4;
            case "K":
                return 5;
            case "DL":
                return 6;
            case "LB":
                return 7;
            case "CB":
                return 8;
            case "S":
                return 9;
            default:
                return 10;

        }
    }


    public String getRegion(int region) {
        String location;
        if (region == 0) location = "West";
        else if (region == 1) location = "Mid-West";
        else if (region == 2) location = "Central";
        else if (region == 3) location = "East";
        else location = "South";
        return location;
    }


    public String getPersonality(int personality) {
        String trait = "";
        if (personality > 91) trait = "Leader";
        else if (personality > 84) trait = "Motivated";
        else if (personality > 75) trait = "Team Player";
        else if (personality > 67) trait = "Average";
        else if (personality > 59) trait = "Team Player";
        else if (personality > 54) trait = "Team Player";
        else trait = "Undisciplined";

        return trait;
    }

    public String getStatus() {
        if (isTransfer) {
            return "Transfer";
        } else if (isRedshirt) {
            return "Redshirt";
        } else if (isMedicalRS) {
            return "Medical";
        } else if (isInjured) {
            return "Injured";
        } else if (isSuspended) {
            return "Suspended";
        } else {
            return "Active";
        }
    }

    public String getTransferStatus() {
        if (isGradTransfer) return "Grad";
        else return getYrStr();
    }

    public String getHeight() {

        int feet = height / 12;
        int leftover = height % 12;

        return feet + "'' " + leftover + "\"";
    }

    public String getWeight() {
        return weight + " lbs";
    }
}
