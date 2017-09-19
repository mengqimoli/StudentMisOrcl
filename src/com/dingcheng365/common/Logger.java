package com.dingcheng365.common;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

public class Logger
{
	private static PrintWriter log;
	
	public synchronized  static void Log(String strMessage)
	{
		Logger.LogByFile(Config.strLogPath,strMessage);
	}
	
	private static void LogByFile (String strPath,String strMessage)
	{		
		Date datNow = new Date();
		
		String strLogTime = CommonFunction.GetFormatDateTime(datNow, "yyyyMMdd");
		String strFile = strPath + "/" + strLogTime + ".txt";
		File fileObject = new File(strFile);
		if(!fileObject.exists()||Logger.log==null)
		{
			// 找不到文件则新建
			try
			{
				Logger.log = new PrintWriter(new FileWriter(strFile,true),true) ;
			}
			catch(IOException e)
			{
				
			}
		}

		// 记录信息
		String logtime2 = CommonFunction.GetFormatDateTime(datNow, Config.strDateTimeFormat);
		Logger.log.println(logtime2 + " " + strMessage );
	}
}
