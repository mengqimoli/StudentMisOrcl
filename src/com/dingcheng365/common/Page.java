package com.dingcheng365.common;

public class Page
{
	// 变量 *********************************************************************
	public int intCurrentPage = 1;		// 当前页数
	public int intTotalRecord = 0; 	// select选出的库中所有记录总数
	public int intTotalPage = 1; 		// 总页数
	public int intPageSize = 15; 		// 每页显示的记录总数 
	
	// 函数 *********************************************************************
	// 构造,strSql为获取记录数量的sql语句,例如: select count(*) from tablename
	public Page(int intCurrentPage,String strSql)
	{
		this.intCurrentPage = intCurrentPage;
		if(this.intCurrentPage < 1 )
		{
			this.intCurrentPage = 1;
		}
		
		this.intPageSize = Config.intPageSize;
		
		this.Init(strSql);
	}
	
	// 初始化参数,strSql为获取记录数量的sql语句,例如: select count(*) from tablename
	private void Init(String strSql)
	{
		int intCount = 0;
		intCount = CommonFunction.DBGetResultAsInt(strSql);
		if(intCount==-1)
		{
			return;
		}
		
		this.intTotalRecord = intCount;
		this.intTotalPage = (this.intTotalRecord + this.intPageSize - 1) / this.intPageSize;
        
		if(this.intCurrentPage > this.intTotalPage )
		{
			this.intCurrentPage = this.intTotalPage;
		}
	}
	
	
}
