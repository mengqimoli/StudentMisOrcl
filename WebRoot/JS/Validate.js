// 判断日期类型是否为YYYY-MM-DD格式的类型    
function IsDate(str)
{     
	var reg = /^(\d{1,4})(-|\/)(\d{1,2})\2(\d{1,2})$/;
	return reg.test(str);
}   

// 判断日期类型是否为YYYY-MM-DD hh:mm:ss格式的类型    
function IsDateTime(str)
{     
	var reg = /^(\d{1,4})(-|\/)(\d{1,2})\2(\d{1,2}) (\d{1,2}):(\d{1,2}):(\d{1,2})$/;   
	return reg.test(str);
}    

// 判断日期类型是否为YYYY-MM-DD hh:mm:ss格式的类型    
function IsDateTime(str)
{     
	var reg = /^(\d{1,4})(-|\/)(\d{1,2})\2(\d{1,2}) (\d{1,2}):(\d{1,2}):(\d{1,2})$/;   
	return reg.test(str);
}  

// 判断日期类型是否为hh:mm:ss格式的类型    
function IsTime(str)     
{     
	var reg = /^((20|21|22|23|[0-1]\d)\:[0-5][0-9])(\:[0-5][0-9])?$/; 
	return reg.test(str);
} 

// 判断输入的字符是否为英文字母    
function IsLetter(str)     
{     
	var reg = /^[a-zA-Z]+$/; 
	return reg.test(str);
}  

// 判断输入的字符是否为整数    
function IsInteger(str)     
{       
	var reg = /^[-+]?\d*$/; 
	return reg.test(str);
}   

// 判断输入的字符是否为双精度    
function IsDouble(str)     
{     
	var reg = /^[-\+]?\d+(\.\d+)?$/; 
	return reg.test(str);
}   

// 判断输入的字符是否为:a-z,A-Z,0-9    
function IsString(str)     
{     
	var reg = /^[a-zA-Z0-9_]+$/;  
	return reg.test(str);
}  

// 判断输入的字符是否为中文    
function IsChinese(str)     
{     
	var reg = /^[\u0391-\uFFE5]+$/; 
	return reg.test(str);
} 

// 判断输入的EMAIL格式是否正确    
function IsEmail(str)     
{     
	var reg = /^\w+([-+.]\w+)*@\w+([-.]\w+)*\.\w+([-.]\w+)*$/;
	return reg.test(str);
}     

// 判断输入的邮编(只能为六位)是否正确    
function IsZIP()     
{     
	var reg = /^\d{6}$/; 
	return reg.test(str);
}     



