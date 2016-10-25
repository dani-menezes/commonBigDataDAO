package com.architecture.bigdata.common;

import java.io.Serializable;
import java.math.BigInteger;

/**
 * Implementa as funções básicas de um bean.
 * @author daniel.menezes - <tt>daniel.afmenezes@gmail.com</tt>
 */
public abstract class AbstractCommonBean implements CommonBean, Serializable {

	/** Generated Serial UID Version. */
	private static final long serialVersionUID = -2050248527788932359L;
	
	/** Atributo de identificação do bean. */
	protected BigInteger key;

	/**
	 * {@inheritDoc}
	 */
	public BigInteger getKey() {
		return key;
	}
	
	/**
	 * {@inheritDoc}
	 */
	public void setKey(BigInteger key) {
		this.key = key;
	}

	/**
	 * Realiza a comparação básica sobre a identificação do bean.
	 * @param bean Bean a ser comparado
	 */
	public int compareTo(AbstractCommonBean bean) {
		if(this.key == null || bean.getKey() == null) {
			return 1;
		}
		return key.compareTo(bean.getKey());
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((key == null) ? 0 : key.hashCode());
		return result;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		AbstractCommonBean other = (AbstractCommonBean) obj;
		if (key == null) {
			if (other.key != null) {
				return false;
			}
		} else if (!key.equals(other.key)) {
			return false;
		}
		return true;
	}

}