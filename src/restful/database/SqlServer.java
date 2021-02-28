package restful.database;

import org.hibernate.cfg.ImprovedNamingStrategy;
import org.hibernate.cfg.NamingStrategy;
import org.hibernate.internal.util.StringHelper;

public class SqlServer extends ImprovedNamingStrategy{
	
	public static final NamingStrategy INSTANCE = new SqlServer();  
	
	private static final long serialVersionUID = 1383021413247872469L;
	
	@Override
	public String tableName(String tableName) {
		return tableName;
	}
	
	@Override
	public String columnName(String columnName) {
		  return columnName;
		}
	
	@Override
	public String propertyToColumnName(String propertyName) {
		return propertyName;
	}
	
	@Override
	public String joinKeyColumnName(String arg0, String arg1) {
		return columnName(arg0);
	}

}
