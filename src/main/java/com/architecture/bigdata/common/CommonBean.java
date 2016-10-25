package com.architecture.bigdata.common;

import java.io.Serializable;
import java.math.BigInteger;


/**
 * Interface com o contrato de um bean.
 * @author daniel.menezes - <tt>daniel.afmenezes@gmail.com</tt>
 */
public interface CommonBean extends Serializable {
	
	/**
	 * Recupera a chave da entidade.
	 * @return chave da entidade
	 */
	public BigInteger getKey();
	
	/**
	 * Atribui a chave da entidade
	 * @param chave da entidade
	 */
	public void setKey(BigInteger key);
	
}