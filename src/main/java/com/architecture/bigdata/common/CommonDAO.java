package com.architecture.bigdata.common;

import java.math.BigInteger;
import java.util.List;

import com.architecture.bigdata.exception.CommonException;
import com.datastax.driver.mapping.Mapper;
import com.datastax.driver.mapping.MappingManager;

/**
 * Define o contrato das operações básicas disponíveis
 * @author daniel.menezes - <tt>daniel.afmenezes@gmail.com</tt> - <tt>daniel.afmenezes@gmail.com</tt>
 */
public interface CommonDAO<E extends CommonBean> {
	
	/**
	 * Open the connection with database.
	 */
	public void openConnection();
	
	/**
	 * Close the connection with database.
	 */
	public void closeConnection();
	
	/** 
	 * Getting the manager. 
	 */
	public MappingManager getManager();
	
	/**
	 * Getting the mapper.
	 * @return getMapper
	 */
	public Mapper<E> getMapper();

	/**
	 * Retorna todos os registros da tabela
	 * @return List<E> de entidades
	 * @throws CommonException Em caso de exceção
	 */
	public List<E> findAll() throws CommonException;
	/**
	 * Encontra um entidade pelo identificador
	 * @param key Identificador da entidade
	 * @return entidade
	 * @throws CommonException Em caso de exceção
	 */
	public E findByKey(BigInteger key) throws CommonException;
	/**
	 * Remove a entidade
	 * @param E Entity a ser removida
	 * @return <tt>TRUE</tt> caso a entidade tenha sido removida com sucesso,<br><tt>FALSE</tt> caso contrário.
	 * @throws CommonException Em caso de exceção
	 */
	public void delete(E entity) throws CommonException;
	/**
	 * Cria ou atualiza a entidade passada como parâmetro
	 * @param bean Entidade a ser salva/ atualizada
	 * @throws CommonException Em caso de exceção
	 */
	public void save(E bean) throws CommonException;

}
