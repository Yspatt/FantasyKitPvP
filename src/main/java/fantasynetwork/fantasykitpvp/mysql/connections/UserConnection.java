package fantasynetwork.fantasykitpvp.mysql.connections;

import fantasynetwork.fantasykitpvp.FantasyKitPvP;
import fantasynetwork.fantasykitpvp.mysql.DatabaseConnection;
import fantasynetwork.fantasykitpvp.users.User;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class UserConnection extends DatabaseConnection {

    public UserConnection(DatabaseConnectionFactory factory) {
        super(factory);
    }

    private static UserConnection instance = new UserConnection(
            DatabaseConnectionFactory.builder()
                    .withHost("35.192.181.25")
                    .withPort(3306)
                    .withDatabase("kitpvp")
                    .withUsername("root")
                    .withPassword("71KltrMgFiI8u7OE")
    );

    public static UserConnection getInstance() {
        return instance;
    }

    public void checkDatabase() {
        try {
            this.getPreparedStatement("CREATE TABLE IF NOT EXISTS kitpvp_user(name VARCHAR(100) primary key,value longtext);").executeUpdate();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void delete(String name){
        try {
            this.getPreparedStatement("delete from kitpvp_user where name='" + name + "'").executeUpdate();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }


    public User getUser(String uuid) {
        User c = null;
        try {
            ResultSet rs = this.getPreparedStatement("SELECT * FROM kitpvp_user WHERE name='" + uuid + "'").executeQuery();
            while (rs.next()) {

                c = (User) FantasyKitPvP.gson.fromJson(rs.getString("value"),User.class);
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return c;
    }

    public List<User> getAllUsers(){
        List<User> lista = new ArrayList<>();
        try{
            ResultSet rs = this.getPreparedStatement("SELECT * FROM kitpvp_user").executeQuery();
            while(rs.next()){
                try {
                    User c = (User) FantasyKitPvP.gson.fromJson(rs.getString("value"),User.class);
                    lista.add(c);
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        } catch(SQLException | ClassNotFoundException e){
            e.printStackTrace();
        }
        return lista;
    }




    public void save(User c) {
        try {
            UserConnection.getInstance().getPreparedStatement("INSERT INTO kitpvp_user (name,value) " +
                    "VALUES ('" +c.getUuid() + "', '" + FantasyKitPvP.gson.toJson(c) + "') on duplicate key update value = '" + FantasyKitPvP.gson.toJson(c) + "'").executeUpdate();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    }
