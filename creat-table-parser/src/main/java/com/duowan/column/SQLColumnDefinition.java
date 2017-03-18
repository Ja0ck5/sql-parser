package com.duowan.column;


public class SQLColumnDefinition {
	protected String          name;
    protected String          dataType;
    protected int             dataLen;
	
    public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDataType() {
		return dataType;
	}
	public void setDataType(String dataType) {
		this.dataType = dataType;
	}
	public int getDataLen() {
		return dataLen;
	}
	public void setDataLen(int dataLen) {
		this.dataLen = dataLen;
	}
    
    
}

