package com.haiyang.sca.generator.planb;

/**
 * 代码生辰column类
 *
 */
public class Columns {

	private String colName;
	private String colType;
	private int dataLength;
	private int dataPre;
	private int dataScale;
	private String colComments;
	
	protected String getColName() {
		return colName;
	}
	protected void setColName(String colName) {
		this.colName = colName;
	}
	protected String getColType() {
		return colType;
	}
	protected void setColType(String colType) {
		this.colType = colType;
	}
	protected int getDataLength() {
		return dataLength;
	}
	protected void setDataLength(int dataLength) {
		this.dataLength = dataLength;
	}
	protected int getDataPre() {
		return dataPre;
	}
	protected void setDataPre(int dataPre) {
		this.dataPre = dataPre;
	}
	protected int getDataScale() {
		return dataScale;
	}
	protected void setDataScale(int dataScale) {
		this.dataScale = dataScale;
	}
	protected String getColComments() {
		return colComments;
	}
	protected void setColComments(String colComments) {
		this.colComments = colComments;
	}
	
}
