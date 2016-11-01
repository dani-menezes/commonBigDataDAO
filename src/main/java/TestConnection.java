import com.architecture.bigdata.common.CassandraAbstractDAO;
import com.architecture.bigdata.common.CommonBean;
import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.ResultSet;
import com.datastax.driver.core.Row;
import com.datastax.driver.core.Session;
import com.datastax.driver.mapping.Mapper;

public class TestConnection extends CassandraAbstractDAO<CommonBean> {
	/** Serial UID Version. */
	private static final long serialVersionUID = 886279381933779455L;

	public static void main(String ... args) {
		TestConnection tc = new TestConnection();
		tc.testConnection();
	}
	
	public String getClusterVersion () {
		Cluster cluster = null;
		try {
		    cluster = Cluster.builder().withPort(9042).addContactPoint("35.161.238.129").build();         // (1)
		            
		    Session session = cluster.connect();                                           // (2)

		    ResultSet rs = session.execute("select release_version from system.local");    // (3)
		    Row row = rs.one();
		    return row.getString("release_version");                          // (4)
		} finally {
		    if (cluster != null) cluster.close();                                          // (5)
		}
	}

	@Override
	public Mapper<CommonBean> getMapper() {
		return null;
	}
}
