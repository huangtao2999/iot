package com.dsw.iot;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

@RunWith(SpringRunner.class)
@SpringBootTest
public class GuankongApplicationTests {

    @Autowired
    @Qualifier("dmDataSource")
    private DataSource dmDataSource;

    @Test
    public void testDataSource() {
        try {
            Connection connection = dmDataSource.getConnection();
            Statement stmt = connection.createStatement();
            String sql = "select * from SWYT_ZD";
            ResultSet set = stmt.executeQuery(sql);
            StringBuilder sb = null;
            while (set.next()) {
                sb = new StringBuilder();
                sb.append(set.getString("DNAME"));
                sb.append("-");
                sb.append(set.getString("CODE"));
                sb.append("-");
                sb.append(set.getString("ITEM"));
                sb.append("\n");
                System.out.println(sb.toString());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
