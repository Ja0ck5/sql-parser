package com.duowan.statement;

import java.util.ArrayList;
import java.util.List;


public class SqlCreateStatement {
	protected String 			 tableName;
	protected boolean            ifNotExiists = false;
    protected List<String> 		 createElements = new ArrayList<String>();
	
    public String getTableName() {
		return tableName;
	}
	public void setTableName(String tableName) {
		this.tableName = tableName;
	}
	public boolean isIfNotExiists() {
		return ifNotExiists;
	}
	public void setIfNotExiists(boolean ifNotExiists) {
		this.ifNotExiists = ifNotExiists;
	}
	public List<String> getCreateElements() {
		return createElements;
	}
	public void setCreateElements(List<String> createElements) {
		this.createElements = createElements;
	}

    
}
