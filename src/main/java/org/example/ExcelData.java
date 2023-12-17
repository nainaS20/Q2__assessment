package org.example;

public class ExcelData {
    String date;
    String Month;
    String Team;
    String Skill,Candidate_Current_Loc,Candidate_Preferred_location,Candidate_name;
    String Time;


    public ExcelData(String date, String month, String team,
                     String panel_Name, String round, String skill,
                     String candidate_Current_Loc, String candidate_Preferred_location,
                     String candidate_name, String time) {
        this.date = date;
        Month = month;
        Team = team;
        Panel_Name = panel_Name;
        Round = round;
        Skill = skill;
        Candidate_Current_Loc = candidate_Current_Loc;
        Candidate_Preferred_location = candidate_Preferred_location;
        Candidate_name = candidate_name;
        Time = time;
    }

    String Panel_Name;
    String Round;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getMonth() {
        return Month;
    }

    public void setMonth(String month) {
        Month = month;
    }

    public String getTeam() {
        return Team;
    }

    public void setTeam(String team) {
        Team = team;
    }

    public String getPanel_Name() {
        return Panel_Name;
    }

    public void setPanel_Name(String panel_Name) {
        Panel_Name = panel_Name;
    }

    public String getRound() {
        return Round;
    }

    public void setRound(String round) {
        Round = round;
    }

    public String getSkill() {
        return Skill;
    }

    public void setSkill(String skill) {
        Skill = skill;
    }

    public String getCandidate_Current_Loc() {
        return Candidate_Current_Loc;
    }

    public void setCandidate_Current_Loc(String candidate_Current_Loc) {
        Candidate_Current_Loc = candidate_Current_Loc;
    }

    public String getCandidate_Preferred_location() {
        return Candidate_Preferred_location;
    }

    public void setCandidate_Preferred_location(String candidate_Preferred_location) {
        Candidate_Preferred_location = candidate_Preferred_location;
    }

    public String getCandidate_name() {
        return Candidate_name;
    }

    public void setCandidate_name(String candidate_name) {
        Candidate_name = candidate_name;
    }

    public String getTime() {
        return Time;
    }

    public void setTime(String time) {
        Time = time;
    }


}
