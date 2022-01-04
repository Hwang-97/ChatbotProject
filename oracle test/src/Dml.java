import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public interface Dml {
	public ResultSet select(Connection con, String column, String tableName) throws SQLException;
	public ResultSet select(Connection con, String column, String where, String tableName) throws SQLException;
	public boolean update(Connection con, String setData, String tableName) throws SQLException;
	public boolean update(Connection con, String setData, String where, String tableName) throws SQLException;
	public boolean insert(Connection con, Object[] column, String tableName) throws SQLException;
	public boolean insert(Connection con, String tableName) throws SQLException;
	public boolean delete(Connection con, String where, String tableName) throws SQLException;
}
