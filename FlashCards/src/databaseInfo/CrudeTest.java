package databaseInfo;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.sql.*;

public class CrudeTest {

    private Crude crude;

    @BeforeEach
    public void setUp() {
        crude = new Crude();
    }

    @AfterEach
    public void tearDown() {
        crude.closeConnection();
    }

    @Test
    public void testCreateUser() {
        crude.create("user", "testuser", "testuser@example.com", "password123", null);
        String query = "SELECT * FROM user WHERE username = 'testuser';";
        try {
            ResultSet rs = crude.st.executeQuery(query);
            assertTrue(rs.next(), "User creation failed.");
            assertEquals("testuser@example.com", rs.getString("email"));
            // Add additional assertions as needed
        } catch (SQLException e) {
            fail("SQLException: " + e.getMessage());
        }
    }

    @Test
    public void testCreateCard() {
        crude.create("card", "Test Title", "Test Paragraph", "blue", "1");
        String query = "SELECT * FROM card WHERE title = 'Test Title';";
        try {
            ResultSet rs = crude.st.executeQuery(query);
            assertTrue(rs.next(), "Card creation failed.");
            assertEquals("Test Paragraph", rs.getString("paragraph"));
            // Add additional assertions as needed
        } catch (SQLException e) {
            fail("SQLException: " + e.getMessage());
        }
    }

    @Test
    public void testDeleteUser() {
        crude.create("user", "testuser2", "testuser2@example.com", "password123", null);
        crude.delete("user", "username", "testuser2");
        String query = "SELECT * FROM user WHERE username = 'testuser2';";
        try {
            ResultSet rs = crude.st.executeQuery(query);
            assertFalse(rs.next(), "User deletion failed.");
        } catch (SQLException e) {
            fail("SQLException: " + e.getMessage());
        }
    }

    @Test
    public void testUpdateUserPassword() {
        crude.create("user", "testuser3", "testuser3@example.com", "password123", null);
        crude.update("user", "password", "password123", "newpassword123");
        String query = "SELECT * FROM user WHERE username = 'testuser3';";
        try {
            ResultSet rs = crude.st.executeQuery(query);
            assertTrue(rs.next(), "User password update failed.");
            // Assuming password is stored as a hash, this test needs to compare hashes
            // Add the correct hash comparison here
        } catch (SQLException e) {
            fail("SQLException: " + e.getMessage());
        }
    }

    // Additional tests for subgroup and group creations
    @Test
    public void testCreateSubgroup() {
        crude.create("subgroup", "Test Subgroup", "red", "1");
        String query = "SELECT * FROM subgroup WHERE name = 'Test Subgroup';";
        try {
            ResultSet rs = crude.st.executeQuery(query);
            assertTrue(rs.next(), "Subgroup creation failed.");
            assertEquals("red", rs.getString("boja"));
            // Add additional assertions as needed
        } catch (SQLException e) {
            fail("SQLException: " + e.getMessage());
        }
    }

    @Test
    public void testCreateGroup() {
        crude.create("grupa", "Test Group", "1", "green");
        String query = "SELECT * FROM grupa WHERE name = 'Test Group';";
        try {
            ResultSet rs = crude.st.executeQuery(query);
            assertTrue(rs.next(), "Group creation failed.");
            assertEquals("green", rs.getString("boja"));
            // Add additional assertions as needed
        } catch (SQLException e) {
            fail("SQLException: " + e.getMessage());
        }
    }
}