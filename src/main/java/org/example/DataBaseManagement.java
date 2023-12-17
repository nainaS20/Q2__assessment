package org.example;
import org.apache.commons.dbcp2.BasicDataSource;
import java.sql.*;
import java.util.*;
import java.sql.PreparedStatement;
public class DataBaseManagement {
    private static final String JDBC_URL="jdbc:mysql://localhost:3306/accolite";
    private static final String USERNAME="root";
    private static final String PASSWORD="Naina2000@.";
    private static final String TABLE_NAME="InterviewData";

    private static final BasicDataSource dataSource=new BasicDataSource();

    static{
        dataSource.setUrl(JDBC_URL);
        dataSource.setUsername(USERNAME);
        dataSource.setPassword(PASSWORD);
    }
    public static void createTable(){
        try(Connection connection=dataSource.getConnection();
            Statement statement=connection.createStatement()){
            String createTableQuery="CREATE TABLE IF NOT EXISTS"+TABLE_NAME+"("+
            "id INT AUTO_INCREMENT PRIMARY KEY,"+"date VARCHAR(255),"+ "month VARCHAR(255),"+
            "team VARCHAR(255),"+    "panelName VARCHAR(255),"+"round VARCHAR(255)," +"skill VARCHAR(255)"  +
                    "candidate_current_loc VARCHAR(255),"+   "candidate_pref_loc VARCHAR(255),"+ "candidate_name VARCHAR(255)," +")";

            statement.executeUpdate(createTableQuery);
            System.out.println("Table"+TABLE_NAME+"CREATED SUCCESSFULLY");
        }
        catch(SQLException e){
            e.printStackTrace();
        }
    }
    public static void insertData(List<ExcelData>data1) {
        data1.parallelStream().forEach(record -> {
            try (Connection connection = dataSource.getConnect();
                 PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO" + TABLE_NAME +
                         "(date,month,team,panelName,round,skill,time," + "candidate_current_loc,candidate_pref_loc,candidate_name)VALUES(?,?,?,?,?,?,?,?,?,?)")) {
                preparedStatement.setString(1, record.getDate());
                preparedStatement.setString(2, record.getMonth());
                preparedStatement.setString(3, record.getTeam());
                preparedStatement.setString(4, record.getPanel_Name());
                preparedStatement.setString(5, record.getRound());
                preparedStatement.setString(6, record.getSkill());
                preparedStatement.setString(7, record.getTime());
                preparedStatement.setString(8, record.getCandidate_Current_Loc());
                preparedStatement.setString(9, record.getCandidate_Preferred_location());
                preparedStatement.setString(10, record.getCandidate_name());

                preparedStatement.executeUpdate();

            } catch (SQLException e) {
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }
    public static void teamWithMaxInterviews(){
        try(Connection connection =dataSource.getConnection();
            Statement statement=connection.createStatement()){
            String query="SELECT team, COUNT(*) as interviewCount\n"+
                    "FROM accolite_date\n"+
                    "WHERE month IN('OCT-23','NOV-23')\n"+
                    "GROUP BY team\n"+
                    "ORDER BY interviewCount DESC\n"+
                    "LIMIT 1;";
            ResultSet resultSet= statement.executeQuery(query);
            if(resultSet.next()){
                System.out.println("Team with Maximum Interviews: "+resultSet.getString("team"));
            }

        }catch(SQLException e){
            e.printStackTrace();
        }

    }
    public static void teamWithMinInterviews() {
        try (Connection connection = dataSource.getConnection();
             Statement statement = connection.createStatement()) {
            String query = "SELECT team, COUNT(*) as interviewCount\n" +
                    "FROM accolit_data\n" +
                    "WHERE month IN('Oct-23','Nov-23')\n" +
                    "GROUP BY team\n" +
                    "ORDER BY interviewCount ASC\n" +
                    "Limit 1;";
            ResultSet resultSet = statement.executeQuery(query);
            if (resultSet.next()) {
                System.out.println("Team with Minimum Interviews: " + resultSet.getString("team"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void top3Panels(){
        try(Connection connection=dataSource.getConnection();
            Statement statement= connection.createStatement()) {
            String query = "SELECT panelName, COUNT(*) as interviewCount\n" +
                    "FROM accolite_data\n" +
                    "WHERE month IN('Oct-23','Nov-23')\n"+

                    "GROUP BY panelName\n"+
                    "ORDER BY interviewCount DESC\n"+
                    "LIMIT 3;";
            ResultSet resultSet=statement.executeQuery(query);
            while(resultSet.next()){
                System.out.println("Panel: "+resultSet.getString("panelName")+
                        ",Interview Count: "+ resultSet.getInt("interviewCount"));
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
    }

    public static void top3Skills(){
        try(Connection connection=dataSource.getConnection();
            Statement statement=connection.createStatement()) {
            String query = "CREATE OR REPLACE VIEW top_skills_view AS\n" +
                    "SELECT skill,COUNT(*) as interviewCount\n" +
                    "FROM accolite_data\n" +
                    "WHERE month IN('Oct -23, 'Nov-23')\n" +
                    "GROUP BY skill\n" +
                    "ORDER BY interviewCount DESC\n" +
                    "LIMIT 3;\n" +
                    "\n";
            statement.executeUpdate(query);
            String Query2 = "SELECT 8 FROM top_skills_view;";
            ResultSet resultSet = statement.executeQuery(Query2);
            while (resultSet.next()) {
                System.out.println("Skill: " + resultSet.getString("skill") +
                        ", Interview Count: " + resultSet.getInt("interviewCount"));
            }
        }
        catch(SQLException e){
            e.printStackTrace();
        }
    }

//    public static void skillsInPeakTime()
//    {
//        try(Connection connection=dataSource.getConnection();
//            Statement statement=connection.createStatement()){
//
//        }catch(SQLException e){
//            e.printStackTrace();
//        }
//    }

    public static void skillsInPeakTime(){
        try(Connection connection=dataSource.getConnection();
            Statement statement=connection.createStatement()){
            String query="SELECT skill, COUNT(*) as interviewCount\n"+
                    "FROM accolite =_data\n"+
                    "WHERE time='Peak Time'\n"+
                    "GROUP BY skill\n"+
                    "ORDER BY interviewCount DESC\n"+
                    "LIMIT 3;";
            ResultSet resultSet=statement.executeQuery(query);
            while(resultSet.next()){
                System.out.println("Skill in Peak Time: "+resultSet.getString("skill")+", Interview Count: "+ resultSet.getInt("interviewCount"));
            }

        }catch(SQLException e){
            e.printStackTrace();
        }
    }

}



