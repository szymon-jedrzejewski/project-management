package pl.ttpsc.javaupdate.project.utility;

import org.junit.Test;
import pl.ttpsc.javaupdate.project.model.Document;
import pl.ttpsc.javaupdate.project.model.Project;
import pl.ttpsc.javaupdate.project.model.User;
import pl.ttpsc.javaupdate.project.persistence.Operator;
import pl.ttpsc.javaupdate.project.persistence.QueryOperator;
import pl.ttpsc.javaupdate.project.persistence.QuerySpec;
import pl.ttpsc.javaupdate.project.persistence.SearchCondition;

import static org.junit.Assert.assertEquals;

public class SqlQueryUtilityTest {


    @Test
    public void shouldReturnFindQueryWithOneCondition() {
        QuerySpec qs = new QuerySpec();
        qs.setTableName(Document.class);
        qs.append(QueryOperator.WHERE, new SearchCondition("title", Operator.EQUAL_TO, "Test"));
        assertEquals("SELECT * FROM documents WHERE title = 'Test';", SqlQueryUtility.findQueryOf(qs));
    }

    @Test
    public void shouldReturnFindQueryWithAnd() {
        QuerySpec qs = new QuerySpec();
        qs.setTableName(Project.class);
        qs.append(QueryOperator.WHERE, new SearchCondition("name", Operator.EQUAL_TO, "Test"));
        qs.append(QueryOperator.AND, new SearchCondition("creator", Operator.EQUAL_TO, 1));
        assertEquals("SELECT * FROM projects WHERE name = 'Test' AND creator = 1;", SqlQueryUtility.findQueryOf(qs));
    }

    @Test
    public void shouldReturnFindQueryWithOr() {
        QuerySpec qs = new QuerySpec();
        qs.setTableName(Project.class);
        qs.append(QueryOperator.WHERE, new SearchCondition("id", Operator.GREATER_OR_EQUAL, 2));
        qs.append(QueryOperator.OR, new SearchCondition("name", Operator.EQUAL_TO, "Test"));
        assertEquals("SELECT * FROM projects WHERE id >= 2 OR name = 'Test';", SqlQueryUtility.findQueryOf(qs));
    }

    @Test
    public void shouldReturnFindQueryWithNotEqual() {
        QuerySpec qs = new QuerySpec();
        qs.setTableName(User.class);
        qs.append(QueryOperator.WHERE, new SearchCondition("id", Operator.NOT_EQUAL, 2));
        assertEquals("SELECT * FROM users WHERE id != 2;", SqlQueryUtility.findQueryOf(qs));
    }

    @Test
    public void shouldReturnFindQueryWithThreeConditions() {
        QuerySpec qs = new QuerySpec();
        qs.setTableName(Document.class);
        qs.append(QueryOperator.WHERE, new SearchCondition("title", Operator.EQUAL_TO, "Test"));
        qs.append(QueryOperator.AND, new SearchCondition("project", Operator.EQUAL_TO, 1));
        qs.append(QueryOperator.AND, new SearchCondition("project", Operator.EQUAL_TO, "kappa"));
        assertEquals("SELECT * FROM documents WHERE title = 'Test' AND project = 1 AND project = 'kappa';", SqlQueryUtility.findQueryOf(qs));
    }

    @Test
    public void shouldReturnDeleteQuery() {
        assertEquals(SqlQueryUtility.generateDeleteQuery(User.class, 5)
                , "DELETE FROM users WHERE id=5;");
    }

    @Test
    public void shouldReturnCreateQuery() {
        assertEquals(SqlQueryUtility.createQueryOf(
                new User("Tomasz",
                        "Dubiel",
                        "Wundaty",
                        new Character[]{'p', 'a', 's', 's'},
                        false)),
                "INSERT INTO users (firstname, lastname, username, password, isadmin) " +
                        "VALUES ('Tomasz', 'Dubiel', 'Wundaty', 'pass', FALSE);");
    }

}