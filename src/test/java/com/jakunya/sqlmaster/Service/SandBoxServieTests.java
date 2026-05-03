package com.jakunya.sqlmaster.Service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@ExtendWith(MockitoExtension.class)
@ActiveProfiles("test")
public class SandBoxServieTests {
    private SandBoxService service = new SandBoxService();

    @Test
    public void TestSandBoxServie() throws SQLException {
        String initScript = "CREATE TABLE users (id INT, name VARCHAR(255)); INSERT INTO users (id, name) VALUES (1, 'Jakunya'), (2, 'Admin');";
        String query = "select id, name from users";
        String query2 = "SELECT * FROM users";
        List<Map<String, Object>> result = new ArrayList<>();
        List<Map<String, Object>> result2 = new ArrayList<>();
        result = service.listQuery(initScript, query);
        result2 = service.listQuery(initScript, query2);
        Assertions.assertNotNull(result);
        Assertions.assertNotNull(result2);
        Assertions.assertEquals(result.size(), result2.size());
        Assertions.assertEquals(result, result2);
    }
}
