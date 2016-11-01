package com.architecture.bigdata.common;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.List;

import com.architecture.bigdata.exception.CommonException;
import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.ResultSet;
import com.datastax.driver.core.Row;
import com.datastax.driver.core.Session;
import com.datastax.driver.mapping.Mapper;
import com.datastax.driver.mapping.MappingManager;

/**
 * Responsável por provir os métodos comuns a todos os Services(DAOs) <tt>(Data access objects)</tt>.
 * @author daniel.menezes - <tt>daniel.afmenezes@gmail.com</tt>
 */
public abstract class CassandraAbstractDAO<E extends CommonBean> implements CommonDAO<E>, Serializable  {

	/** Serial UID Version. */
	private static final long serialVersionUID = -7011709255779459760L;
	/** {@link Cluster}  */
	private Cluster cluster;
	/** {@link Session} */
	private Session session;
	/** {@link MappingManager} */
	protected MappingManager manager;
	/** {@link Mapper} */
	protected Mapper<E> mapper;

	/** Open the connection. */
	public void openConnection() {
		try {
			cluster = Cluster.builder().withPort(9042).addContactPoint("35.161.238.129").build();
			session = cluster.connect();
			manager = new MappingManager(session);
		} catch (Exception e) {
			e.printStackTrace();
			closeConnection();
		}
	}

	/** Close the connection. */
	public void closeConnection() {
		try {
			if (this.cluster != null) {
				this.manager = null;
				this.session.close();
				this.cluster.close();
			}
		} catch (Exception e ) {
			e.printStackTrace();
		}
	}

	/* (non-Javadoc)
	 * @see com.architecture.bigdata.common.CommonDAO#getManager()
	 */
	@Override
	public MappingManager getManager() {
		return this.manager;
	}

	/* (non-Javadoc)
	 * @see com.architecture.bigdata.common.CommonDAO#findAll()
	 */
	@Override
	public List<E> findAll() throws CommonException {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see com.architecture.bigdata.common.CommonDAO#findByKey(java.math.BigInteger)
	 */
	@Override
	public E findByKey(BigInteger key) throws CommonException {
		E entity = null;
		try {
			this.openConnection();
			entity = this.getMapper().get(key);
			return entity;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			this.closeConnection();
		}
		return entity;
	}

	/* (non-Javadoc)
	 * @see com.architecture.bigdata.common.CommonDAO#delete(com.architecture.bigdata.common.CommonBean)
	 */
	@Override
	public void delete(E entity) throws CommonException {
		try {
			this.openConnection();
			this.getMapper().deleteAsync(entity);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			this.closeConnection();
		}
	}

	/* (non-Javadoc)
	 * @see com.architecture.bigdata.common.CommonDAO#save(com.architecture.bigdata.common.CommonBean)
	 */
	@Override
	public void save(E bean) throws CommonException {
		try {
			this.openConnection();
			this.getMapper().save(bean);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			this.closeConnection();
		}
	}

	public void testConnection() {
		this.openConnection();
		try {
			ResultSet result = session.execute("SELECT voltage, amperage FROM manageenergyks.connectorData;");
			while(!result.isExhausted()){
				Row row = result.one();
				System.out.println("voltage = " + row.getDouble("voltage"));
				System.out.println("amperage = " + row.getDouble("amperage"));
			};
		} catch (Exception e ) {
			e.printStackTrace();
		}
		this.closeConnection();
	}

}
