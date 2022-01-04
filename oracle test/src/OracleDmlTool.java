import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public class OracleDmlTool implements Dml{

	@Override
	public ResultSet select(Connection con, String column, String tableName) throws SQLException {
		StringBuilder query = new StringBuilder("select ");
		if(column.equals("*")) {
			query.append("* from ").append(tableName);
		}else {
			String[] queryArr = column.split(",");
			for(int i=0; i<queryArr.length; i++) {
				if(i==queryArr.length-1) {
					query.append(" ").append(queryArr[i]).append(" ");
				}else {
					query.append(" ").append(queryArr[i]).append(",").append(" ");					
				}
			}
			query.append("from ").append(tableName);
		}
		try {
			return con.prepareStatement(query.toString()).executeQuery();
		}catch(Exception e) {
			e.printStackTrace();
		}
		throw new SQLException();
	}

	@Override
	public ResultSet select(Connection con, String column, String where, String tableName) throws SQLException {
		StringBuilder query = new StringBuilder("select ");
		if(column.equals("*")) {
			query.append("* from ").append(tableName);
		}else {
			String[] queryArr = column.split(",");
			for(int i=0; i<queryArr.length; i++) {
				if(i==queryArr.length-1) {
					query.append(" ").append(queryArr[i]).append(" ");
				}else {
					query.append(" ").append(queryArr[i]).append(",").append(" ");					
				}
			}
			query.append("from ").append(tableName);
		}
		try {
			return con.prepareStatement(query.append(" where ").append(where).toString()).executeQuery();
		}catch(Exception e) {
			e.printStackTrace();
		}
		throw new SQLException();
	}
//"update tblTest set name='황현우' where seq=3"
	@Override
	public boolean update(Connection con, String setData, String tableName) throws SQLException {
		StringBuilder query = new StringBuilder("update ");
		query.append(tableName);
		query.append(tableName);
		return false;
	}

	@Override
	public boolean update(Connection con, String setData, String where, String tableName) throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean insert(Connection con, Object[] column, String tableName) throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean insert(Connection con, String tableName) throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean delete(Connection con, String where, String tableName) throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}
	
}
