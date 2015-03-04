package com.kirkk.cust;

import java.math.BigDecimal;

/**
 * 相较于前一版本，具体方法变为抽象方法（由bill包转移至cust包）
 *
 */
public interface Bill {
	public BigDecimal getChargeAmount();
	public BigDecimal pay();

}