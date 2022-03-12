package com.example.serverjaxws.utils;

import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

class ConnectionHelperTest {

    @Test
    void getConnection() throws SQLException {
        Connection conn = ConnectionHelper.getConnection();
        PreparedStatement preparedStatement = conn.prepareStatement("insert into employees (name, salary) values ('Huong', 15000)");
        preparedStatement.execute();
    }

}