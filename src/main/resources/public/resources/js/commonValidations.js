function validateExtension(uploadfile , allowedFiles) {
	
	var uf = uploadfile.value;
	//alert(uf);
	var result = false;
	if (uf != "") {
		if (1 < uf.match(/\./g).length) {
			alert("Invalid File Name(Multidots found)");
			result = false;
		} else {
			var regex = new RegExp("([a-zA-Z0-9\s_\\.\-:])+("
					+ allowedFiles.join('|') + ")$");
			if (!regex.test(uploadfile.value.toLowerCase())) {
			
				alert("Please upload files having extensions: "
						+ allowedFiles.join(', ') + " only.");

				uploadfile.value = "";
				result = false;
			} else 
				result = true;
		}
	} else 
		result = false;
	return result;
}

function UploadImage(fileUpload) {
    
    if(validateExtension(fileUpload,[ ".jpeg",  ".jpg",  ".png", ".gif" ] )){
    
        if (typeof (fileUpload.files) != "undefined") {
            var size = parseFloat(fileUpload.files[0].size / 1024).toFixed(2);
            //alert(size);
            if(size > 2048 ){
            
            	fileUpload.value="";
            	alert("Please upload image with max size of 2MB");
            }
            
        } 
    }
    
    
}

function UploadFile(fileUpload, sizeInMB) {
    
    if(validateExtension(fileUpload,[ ".jpeg",  ".jpg",  ".png", ".pdf" ] )){
    
        if (typeof (fileUpload.files) != "undefined") {
            var size = parseFloat(fileUpload.files[0].size / 1024).toFixed(2);
            if(size > (sizeInMB * 1000) ){
            
            	fileUpload.value="";
            	alert("Please upload image with max size of "+sizeInMB+"MB");
            }
            
        } 
    }
    
    
}

function integersOnly(evt) {
	    
    var theEvent = evt || window.event;
    var key = theEvent.keyCode || theEvent.which;
    var keyVal = String.fromCharCode( key );
    var regex = /[0-9]/;
    if(evt.shiftKey)
    	return false;
    if( !regex.test(keyVal) && ![8,16,20,35,36,37,38,39].includes(key) ) {
      theEvent.returnValue = false;
      if(theEvent.preventDefault) theEvent.preventDefault();
    }
	   
}

function integersAndDOTsOnly(evt) {
    
    var theEvent = evt || window.event;
    var key = theEvent.keyCode || theEvent.which;
    var keyVal = String.fromCharCode( key );
    var regex = /[0-9]/;
    if( !regex.test(keyVal) && ![8,16,20,35,36,37,38,39,46].includes(key)  ) {
      theEvent.returnValue = false;
      if(theEvent.preventDefault) theEvent.preventDefault();
    }
    
}

function alphabetsAndSpacesOnly(evt) {
    
    /*var theEvent = evt || window.event;
    var key = theEvent.keyCode || theEvent.which;
    var keyVal = String.fromCharCode( key );
    var regex = /[a-zA-Z]|\s/;
    alert(key);
    if(!regex.test(keyVal) && ![8,16,20,35,36,37,38,39,46].includes(key)  ) {
      theEvent.returnValue = false;
      if(theEvent.preventDefault) theEvent.preventDefault();
    }*/
	var k=evt.keyCode;
	
	if(evt.keyCode != 9)
		document.all ? k = evt.keyCode : k = evt.which;
	 
	
    return ( (k > 64 && k < 91) || (k > 96 && k < 123) || k == 8 || k == 32 || k==9 );//|| (k >= 48 && k <= 57)
    
}

function alphabetsOnly(evt) {
    
    var theEvent = evt || window.event;
    var key = theEvent.keyCode || theEvent.which;
    var keyVal = String.fromCharCode( key );
    if(evt.shiftKey)
    	return false;
    var regex = /[a-zA-Z]/;
    if(!regex.test(keyVal) && ![8,16,20,35,36,37,38,39,46].includes(key)  ) {
      theEvent.returnValue = false;
      if(theEvent.preventDefault) theEvent.preventDefault();
    }
    
}

function validateTextArea(evt) {
    
    var theEvent = evt || window.event;
    var key = theEvent.keyCode || theEvent.which;
    var keyVal = String.fromCharCode( key );
    var regex = /[a-zA-Z0-9]|\s|&|\/|:|-/;
    if( !regex.test(keyVal) && ![8,16,20,35,36,37,38,39,46].includes(key)  ) {
      theEvent.returnValue = false;
      if(theEvent.preventDefault) theEvent.preventDefault();
    }
    
}

function toUpper(obj) {
	
    var val = obj.value;
    obj.value = val.toUpperCase();
}

function wordsWithSingelSpaceBetween(obj) {
	
	/*var val= obj.value;
	obj.value = val.replace("\\s{2,}", " "); /^(\w+\s?)*\s*$/
    if (!val.match(new RegExp(/^[a-zA-Z]|\s\s+|\s+$/g))) {
        obj.value =  val.substring(0, val.length - 1);
    }*/
   // return 'NOT ALLOWED';
	
}

function trimValue(obj){
	obj.value = obj.value.trim();
	
}
function checkDateFormat(evt){
	 var theEvent = evt || window.event;
    var key = theEvent.keyCode || theEvent.which;
    key = String.fromCharCode( key ); 
   // var value = obj.value;
    var regex = /^(18|19|20|21)\d{2}\-(0[1-9]|1\d|2\d|3[01])\-(0[1-9]|1[0-2])$/;
    //var regex = /(0?[1-9]|[12][0-9]|3[01])/(0?[1-9]|1[012])/((19|20)\d\d)/;
   // var rr = /^([1-9]{2})\d{2}\/(0[1-9]|1\d|2\d|3[01])/\(0[1-9]|1[0-2])$/;
   alert(key.match(regex) );
    /* if( !key.match(regex) ) {
      theEvent.returnValue = false;
      if(theEvent.preventDefault) theEvent.preventDefault(); 
     // alert("failed");
    } */
	
}

function numberValidation(obj){
	var value = obj.value;
	var numberRegex = /^\d*(?:\.\d{0,2})?$/;
	if(!numberRegex.test( value) ){
		obj.value = "";
		obj.focus();
		alert("Invalid number. correct ex: 1000.20");
		
	}
	
	
}

	function IsMobileNumber(obj) {
		/*var val = obj.value;
    var regex = /^[5-9]{1}[0-9]{9}$/;
    if(!regex.test(val)  ) {
    	obj.value = "";
    	obj.focus();
    	alert("Invalid Phone Number");
    }*/
    }
	
	/*var mob = /^[1-9]{1}[0-9]{9}$/;
	var txtMobile = document.getElementById(txtMobId);
	if (mob.test(txtMobile.value) == false) {
	    alert("Please enter valid mobile number.");
	    txtMobile.focus();
	    return false;
	}*/
	//return true;
