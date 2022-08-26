import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.assertEquals;

class DBconnectorTest {

    private static DBconnector dBconnector;


    @BeforeEach
    void setUp() {
        System.out.println("TESTINNNNGGGG");
        Connection con = null;
        try {
            con = DBconnector.connection();
            String delete = "DROP TABLE IF EXISTS usertable";
            con.prepareStatement(delete).executeUpdate();
            String createTable = "CREATE TABLE IF NOT EXISTS `startcode_test`.`usertable` (\n" +
                    "  `id` INT NOT NULL AUTO_INCREMENT,\n" +
                    "  `fname` VARCHAR(45) NULL,\n" +
                    "  `lname` VARCHAR(45) NULL,\n" +
                    "  `pw` VARCHAR(45) NULL,\n" +
                    "  `phone` VARCHAR(45) NULL,\n" +
                    "  `address` VARCHAR(45) NULL,\n" +
                    "  PRIMARY KEY (`id`));";
            con.prepareStatement(createTable).executeUpdate();
            String SQL = "INSERT INTO startcode_test.usertable (fname, lname, pw, phone, address) VALUES (?,?,?,?,?)";
            PreparedStatement ps = con.prepareStatement(SQL, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, "Hans");
            ps.setString(2, "Hansen");
            ps.setString(3, "Hemmelig123");
            ps.setString(4, "40404040");
            ps.setString(5,"Rolighedsvej 3");
            ps.executeUpdate();
            ps.setString(1, "Bob");
            ps.setString(2, "Bobsen");
            ps.setString(3, "Hemmelig123");
            ps.setString(4, "40404040");
            ps.setString(5,"Rolighedsvej 3");
            ps.executeUpdate();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testFindUser() {
        UserMapper userMapper = new UserMapper(dBconnector);
        ArrayList<String> actual = userMapper.listUsersByName();
        assertEquals("Bob",actual.get(1));
    }

    @Test
    public void testGetUserData() {
        UserMapper userMapper = new UserMapper(dBconnector);
        User actual = userMapper.getUser(2);
        User expected = new User("Bob","Bobsen","40404040","Rolighedsvej 3");
        assertEquals(expected,actual);
    }

    @Test
    public void testChangeData() {
        UserMapper userMapper = new UserMapper(dBconnector);
        User actual = userMapper.editUserData(2,"phone","50505050");
        User expected = new  User("Bob","Bobsen","50505050","Rolighedsvej 3");
        assertEquals(expected,actual);
    }
}