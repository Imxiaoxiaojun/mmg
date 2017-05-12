var getElementById = function(val){
	if(null == val || "" == val.trim())return null;
	return document.getElementById(val);
}

var ajaxpost = function(url,param,succallback,errcallback){
	debugger;
	$.ajax({  
        type: "post",  
        url: url,  
        data: param || {},  
        dataType: "json",  
        success: succallback,  
        error: function (messsage) {  
           getElementById("EEE").innerHTML=messsage;
        }  
    });  
} 


var ajaxget = function(url,param,succallback,errcallback){
	$.ajax({  
        type: "get",  
        url: url,  
        data: param || {},  
        dataType: "json",  
        success: succallback,  
        error: function (messsage) {  
           getElementById("EEE").innerHTML=messsage;
        }  
    });  
} 

var FormDataUtil = function (){
	this.obj = {};
	this.param = "";
	this.put = function(key,val){
		if(Object.prototype.toString.call(key)!="[object String]")return;
		if(!key||""==key.trim())return;
		this.obj[key] = val;
		this.param += key + "=" + val + "&";
	}
	this.get = function(key){
//		return this.array[key];
	}
	this.getajaxdata = function(){
		return this.param.substring(0,this.param.lastIndexOf('&'));
	}
};
