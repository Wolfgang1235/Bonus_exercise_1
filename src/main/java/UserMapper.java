import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class UserMapper {

    DBconnector dBconnector;

    public UserMapper(DBconnector dBconnector) {
        this.dBconnector = dBconnector;
    }

    public ArrayList<String> listUsersByName() {
        ArrayList<String> listOfUsers = new ArrayList<>();

        String sql = "SELECT fname FROM usertable";

        try (Connection connection = dBconnector.connection()){
            try (PreparedStatement ps = connection.prepareStatement(sql)){
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    String fName = rs.getString("fName");
                    listOfUsers.add(fName);
                }
            } catch (SQLException sqlException) {
                System.out.println("Fejl med sql");
            }
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return listOfUsers;
    }

    public User getUser(int id) {
        User user = null;

        String sql = "SELECT fName, lName, phone, address FROM usertable where id = ?";

        try (Connection connection = dBconnector.connection()){
            try (PreparedStatement ps = connection.prepareStatement(sql)){
                ps.setInt(1,id);
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    String fName = rs.getString("fName");
                    String lName = rs.getString("lName");
                    String phone = rs.getString("phone");
                    String address = rs.getString("address");
                    user = new User(fName,lName,phone,address);
                }


            } catch (SQLException sqlException) {
                System.out.println("Fejl med sql");
            }
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return user;

    }

    public User editUserData(int id, String property, String newValue) {
        User user = null;
        String sql = "";
        if (property.equals("pw")) {
            sql = "UPDATE usertable SET pw = ? where id = ?";
        }
        if (property.equals("phone")) {
            sql = "UPDATE usertable SET phone = ? where id = ?";
        }
        if (property.equals("address")) {
            sql = "UPDATE usertable SET address = ? where id = ?";
        }
        try (Connection connection = dBconnector.connection()){
            try (PreparedStatement ps = connection.prepareStatement(sql)){
                ps.setString(1,newValue);
                ps.setInt(2,id);
                ps.executeUpdate();
                user = getUser(id);
            } catch (SQLException sqlException) {
                System.out.println("Fejl med sql");
            }
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return user;
    }
}
