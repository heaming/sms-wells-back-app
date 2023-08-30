//						jso_configure({
//							"sppilot" : {
//								client_id : "client1",
//								redirect_uri : "http://localhost:8080/sppilot",//"http://localhost:9000/OAuth2.0_AuthCode_part_3/handler",
//								debug : true,
//								authorization : "http://localhost:8080/auth/oauth/authorize"
//							}
//						});
//
//						jso_ensureTokens({
//							"sppilot" : [ "read","write", "delete" ]
//						});
//
//						jso_dump();


if (typeof(COMMON_JS) == 'undefined') { // 한번만 실행
    // 전역 변수
    var COMMON_JS = true;
    var errmsg = "";
    var errfld;

    /**
     * 알파벳과 숫자만 허용
     * @param val
     * @returns {boolean}
     */
    function gfn_isAlpNum(val){
        var regEx = /^[A-Za-z0-9]*$/;
        return !regEx.test(val);
    }

    /**
     * 유리수 인지 판별
     * 유리수가 아니면 false, 숫자면 true
     * @param val
     * @returns {boolean}
     */
    function gfn_isRNumber(n) {
        return !isNaN(parseFloat(n)) && isFinite(n);
    }

    /**
     * 0 || 양의 정수 면 true 아니면 false
     * @param n
     * @returns {boolean}
     */
    function gfn_isNumber(n) {
        var regEx = /^[0-9]*$/;
        return regEx.test(n);
    }

    /**
     * 유리수인지 판별하고 유리수면 false return 아니면 alert 출력하고 true return
     * @param id
     * @returns {boolean}
     */
    function gfn_rNumberValid(id){
        var val = $("#" + id).val();

        if(isRNumber(val)) {
            return false;
        }else{
            alert($("#" + id).attr("title") + "은(는) 유리수만 입력할 수 있습니다" );
            $("#" + id).focus();
            return true;
        }
    }

    /**
     * 0 || 양의 정수 판별하고 맞다면 false return 아니면 alert 출력하고 true return
     * @param id
     * @returns {boolean}
     */
    function gfn_numberValid(id){
        var val = $("#" + id).val();

        if(gfn_isNumber(val)) {
            return false;
        }else{
            alert($("#" + id).attr("title") + "은(는) 0 혹은 양의정수만 입력할 수 있습니다" );
            $("#" + id).focus();
            return true;
        }
    }

    /**
     * 문자 변경
     * @param str
     * @param search
     * @param dst
     * @returns {*}
     */
    function gfn_replaceAll(str, search, dst) {
        try{
            while (str.indexOf(search) !== -1) {
                str = str.replace(search, dst);
            }
        }catch(e){
            return str;
        }
        return str;
    }
    
    /**
     * 바코드 스캔시 허용문자를 제외하고 반환하는 공통함수
     * 
     * 허용하는 특수문자
     * -_[]()
     * 
     * 허용하는 문자
     * 영문 알파벳과 숫자
     * 
     * @param str 입력 문자열
     * @return 허용문자를 제외한 문자열만 반환(영문자는 대문자 처리하고 문자열 앞뒤 공백 제거함)
     */
    function gfn_getBarcodeValue(str) {
    	var resultValue= "";
    	try {
    		// 추가로 허용해야 하는 특수문자 있을 경우 아래의 정규표현식에 추가하면 됨.
    		resultValue = str.replace(/[^a-zA-Z0-9\-_\[\]()]/g, "").toUpperCase().trim();
    	} catch(e) {
    		console.log(e);
    		return str;
    	}
    	return resultValue;
    }

    /**
     * 소요시간을 출력하는 공통함수
     * @param start 시작시간
     * @return 소요시간을 HH:mm:ss.SSS 포맷으로 반환
     */
    function gfn_getElapsedTime(start) {
    
		let end = new Date(); // 종료
		
		let elapsedMSec = end - start;

		var hour = "0" + parseInt(elapsedMSec / 1000 / 60 / 60);
		var min  = "0" + parseInt(elapsedMSec / 1000 / 60);
		var sec  = "0" + parseInt(elapsedMSec / 1000);
		var msec = parseInt(elapsedMSec) + "000";
		
		hour = hour.split("").reverse().join("").substring(0, 2).split("").reverse().join("");
		min  = min.split("").reverse().join("").substring(0, 2).split("").reverse().join("");
		sec  = sec.split("").reverse().join("").substring(0, 2).split("").reverse().join("");
		msec = msec.substring(0, 3);
		
		return hour + ":" + min + ":" + sec + "." + msec;
    }
    
    /**
     * 공백제거
     * @param s
     * @returns {string}
     */
    function gfn_trim(s)
    {
        var t = "";
        var from_pos = to_pos = 0;
        s = s + "";

        for (i=0; i<s.length; i++)
        {
            if (s.charAt(i) == ' ')
                continue;
            else
            {
                from_pos = i;
                break;
            }
        }

        for (i=s.length; i>=0; i--)
        {
            if (s.charAt(i-1) == ' ')
                continue;
            else
            {
                to_pos = i;
                break;
            }
        }

        t = s.substring(from_pos, to_pos);
        //				alert(from_pos + ',' + to_pos + ',' + t+'.');
        return t;
    }

    /**
     * 자바스크립트로 PHP의 number_format 흉내를 냄
     * 숫자에 , 를 출력
     * @param data
     * @returns {string}
     */
    function gfn_number_format(data)
    {
        if(gfn_isNull(data)){
            return "0";
        }

        var tmp = '';
        var number = '';
        var cutlen = 3;
        var comma = ',';
        var i;
        var isMinus = false;


        data = data + "";
        if(data.indexOf("-") >= 0){
            isMinus = true;
            data = data.replace("-", "");
        }
        len = data.length;
        mod = (len % cutlen);
        k = cutlen - mod;
        for (i=0; i<len; i++)
        {
            number = number + data.charAt(i);

            if (i < data.length - 1)
            {
                k++;
                if ((k % cutlen) == 0)
                {
                    number = number + comma;
                    k = 0;
                }
            }
        }

        if(isMinus){
            number = "-" + number;
        }

        return number;
    }

    /**
     * , 를 없앤다.
     * @param data
     * @returns {string}
     */
    function gfn_no_comma(data)
    {
        var tmp = '';
        var comma = ',';
        var i;

        for (i=0; i<data.length; i++)
        {
            if (data.charAt(i) != comma)
                tmp += data.charAt(i);
        }
        return tmp;
    }

    /**
     * 라디오 버튼 값 가져오기
     * @param radioObj
     * @return
     */
    function gfn_getRadioCheckedValue(radioObj) {
        if(!radioObj)
            return "";
        var radioLength = radioObj.length;
        if(radioLength == undefined)
            if(radioObj.checked)
                return radioObj.value;
            else
                return "";
        for(var i = 0; i < radioLength; i++) {
            if(radioObj[i].checked) {
                return radioObj[i].value;
            }
        }
        return "";
    }

    /**
     * 라디오 버튼 값 설정
     * @param radioObj
     * @param newValue
     * @return
     */
    function gfn_setRadioCheckedValue(radioObj, newValue) {
        if(!radioObj)
            return;
        var radioLength = radioObj.length;
        if(radioLength == undefined) {
            radioObj.checked = (radioObj.value == newValue.toString());
            return;
        }
        for(var i = 0; i < radioLength; i++) {
            radioObj[i].checked = false;
            if(radioObj[i].value == newValue.toString()) {
                radioObj[i].checked = true;
            }
        }
    }

    /**
     * Null 값인지 확인
     * @param val
     * @returns {boolean}
     */
    function gfn_isNull(val) {
        if (val == undefined || val == "undefined") return true;
        if (val == null) return true;

        if (val === 0)	return false;
        if (val == "") return true;
        if (val.length <= 0) return true;

        return false;
    }
    function gfn_isEmpty(obj){
        return $.isEmptyObject(obj);
    }
    /**
     * Null 또는 값이 비어있는지 확인
     * @param obj
     * @returns {boolean}
     */
    function gfn_isNullOrEmpty(obj) {
    	return gfn_isNull(obj) || gfn_isEmpty(obj);
    }
    /**
     * Null String을 "" String으로 바꿔준다.
     *
     * @param val
     */
    function gfn_nvl(val,replaceStr){
        if(gfn_isNull(val)){
            if(gfn_isNull(replaceStr)){
                return "";
            }else{
                return replaceStr;
            }
        }else{
            return val;
        }
    }

    /**
     * 이메일 유효성 체크
     * @param email
     * @returns {boolean}
     */
    function gfn_check_email_addr(email) {
        //이메일 주소 확인
        var reg = /^((([a-z]|\d|[!#\$%&'\*\+\-\/=\?\^_`{\|}~]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])+(\.([a-z]|\d|[!#\$%&'\*\+\-\/=\?\^_`{\|}~]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])+)*)|((\x22)((((\x20|\x09)*(\x0d\x0a))?(\x20|\x09)+)?(([\x01-\x08\x0b\x0c\x0e-\x1f\x7f]|\x21|[\x23-\x5b]|[\x5d-\x7e]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(\\([\x01-\x09\x0b\x0c\x0d-\x7f]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF]))))*(((\x20|\x09)*(\x0d\x0a))?(\x20|\x09)+)?(\x22)))@((([a-z]|\d|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(([a-z]|\d|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])*([a-z]|\d|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])))\.)+(([a-z]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(([a-z]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])*([a-z]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])))\.?$/i;
        if (!reg.test(email)) {
            return false;
        }

        return true;
    }

    // 전화번호를 999-9999-9999 형태로 리턴
    function gfn_maskPhoneNumber(vParam){
        try{
            if( gfn_isNull(vParam) ){
                return "";
            }
            var strPhoneNum = vParam.replace(/\D/g, "");	// 숫자만 저장
            var strPhoneNum1 = "";
            var strPhoneNum2 = "";
            var strPhoneNum3 = "";

            // 전화 지역 번호
            if( strPhoneNum.substring(0,2) == "02" ){
                strPhoneNum1 = strPhoneNum.substring(0,2);
            }else{
                strPhoneNum1 = strPhoneNum.substring(0,3);
            }

            // 전화 국 번호
            strPhoneNum2 = strPhoneNum.substring(strPhoneNum1.length, (Number(strPhoneNum.length) - 4))

            // 전화 일련 번호
            strPhoneNum3 = strPhoneNum.substring((Number(strPhoneNum.length) - 4));

            return strPhoneNum1 + "-" + strPhoneNum2 + "-" + strPhoneNum3;
        }catch(e){
            console.log(e);
            return "ERROR";
        }

    }
    // YYYYMMDD 날짜를 YYYY-MM-DD 형태로 리턴
    // vParam 값이 비어있을 경우 접합문자 한글자 반환되는 문제 수정. 2023-05-11 MCM
    function gfn_maskDateString(vParam, str){
    	const defaultStr = ".";
    	if(str == null || str == ''){			//접합문자 없을시 default = . 있을경우 해당 접합문자_20180820
    		str = defaultStr;
    	}
    	
        try{
            var dataString = vParam + "";
            dataString = gfn_trim(dataString);
            if( gfn_isNull(dataString) || dataString == "0" ){
                return "";
            }
            dataString = dataString.replace(/\D/g, "");
            var y = dataString.substring(0,4);
            var m = dataString.substring(4,6);
            var d = dataString.substring(6,8);
            var rtnStr = y + str + m;

            if( dataString.length > 6 ){
                rtnStr += str + d;
            }
            if (rtnStr == defaultStr || rtnStr == str) {
                return "";
            } else {
                return rtnStr;
            }
        }catch(e){
            console.log(e);
            return dataString;
        }

    }
    // YYYYMMDDHHMISS 날짜를 YYYY-MM-DD HH:MI:SS 형태로 리턴
    function gfn_maskDateTimeString(vParam){
        try{
            var dataString = vParam + "";
            dataString = gfn_trim(dataString);
            if( gfn_isNull(dataString) || gfn_trim(dataString) == "0" ){
                return "";
            }
            dataString = dataString.replace(/\D/g, "");
            var y = dataString.substring(0,4);
            var m = dataString.substring(4,6);
            var d = dataString.substring(6,8);
            var h = dataString.substring(8,10);
            var mm = dataString.substring(10,12);
            var s = dataString.substring(12,14);
            var rtnString = y + "." + m + "." + d;
            if( !gfn_isNull(h) && !gfn_isNull(mm) && !gfn_isNull(s) ){
                rtnString = rtnString + " " + h + ":" + mm + ":" + s;
            }
            return rtnString;
        }catch(e){
            console.log(e);
            return dataString;
        }

    }

    // HHMISS 시간을 HH:MI:SS 형태로 리턴
    function gfn_maskTimeString(vParam){
        try{
            var dataString = vParam + "";
            dataString = gfn_trim(dataString);
            if( gfn_isNull(dataString) || gfn_trim(dataString) == "0" ){
                return "";
            }
            dataString = dataString.replace(/\D/g, "");
            var h = dataString.substring(0,2);
            var mm = dataString.substring(2,4);
            var s = dataString.substring(4,6);
            var rtnString = h + ":" + mm;
            if( !gfn_isNull(s) ){
                rtnString = rtnString + ":" + s;
            }
            return rtnString;
        }catch(e){
            console.log(e);
            return dataString;
        }

    }

    // 현재 날짜를 YYYYMMDD형태로 리턴
    function gfn_getToday(){
        var d = new Date();
        var year = d.getFullYear();
        var month = (Number(d.getMonth()) + 1);
        month = (month<10?"0":"") + month;
        var date = d.getDate();
        date = (date<10?"0":"") + date;

        return year + "" + month + "" + date;
    }

    // 현재 시간을 HHMMSS형태로 리턴
    function gfn_getCurTime(){
        var d = new Date();
        var hour = d.getHours();
        hour = (hour<10?"0":"") + hour;
        var min = d.getMinutes();
        min = (min<10?"0":"") + min;
        var sec = d.getSeconds();
        sec = (sec<10?"0":"") + sec;

        return hour + "" + min + "" + sec;
    }

    // 입력받은 date 형식을 YYYYMMDD형태로 리턴
    function gfn_dateToYYYYMMDD(d){
        //var d = new Date();
        var year = d.getFullYear();
        var month = (Number(d.getMonth()) + 1);
        month = (month<10?"0":"") + month;
        var date = d.getDate();
        date = (date<10?"0":"") + date;

        return year + "" + month + "" + date;
    }

    // 현재 월의 1일을 YYYYMMDD형태로 리턴
    function gfn_getFirstday(){
        var d = new Date();
        var year = d.getFullYear();
        var month = (Number(d.getMonth()) + 1);
        month = (month<10?"0":"") + month;
        date = "01" ;

        return year + "" + month + "" + date;
    }

    // 현재 월의 마지막 날을 YYYYMMDD형태로 리턴
    function gfn_getLastday(){
        var getFirstDay = gfn_getFirstday() ;
        var getNextFirstDay = gfn_getAddMonth(getFirstDay, 1) ;
        var getLastDay = gfn_getAddDate(getNextFirstDay, -1)

        return getLastDay ;
    }

    // 입력 월의 마지막 날을 YYYYMMDD형태로 리턴
    function gfn_getMonthLastday(yyyymmdd){
    	var getNextFirstDay = gfn_getAddMonth(yyyymmdd, 1) ;
    	var getLastDay = gfn_getAddDate(getNextFirstDay, -1)
    	
    	return getLastDay ;
    }

    // 입력 월의 첫 월요일을 찾는다. 
    // YYYYMMDD형태로 리턴 6일 이하일경우 다음달의 마지막 월요일일 리턴.
    function gfn_getMonthFitstMonday(yyyymmdd){
    	var getFirstDay = yyyymmdd.substring(0, 6) + '01';
//
		var week = new Array('0', '1', '2', '3', '4', '5', '6');		// 0:일,1:월,2:화,3:수,4:목,5:금,6:토 
		var dayOfWeek = "";												// 학습 시작일 시작요일
		var date = "";													// 기본 시작일 셋팅
		
		for (var i = 0; i < 7; i++) {
			date = gfn_getAddDate(getFirstDay, i);
			dayOfWeek = week[new Date(date.substr(0,4) +"-"+ date.substr(4,2) +"-"+ date.substr(6,2)).getDay()];
			if(dayOfWeek =="1"){
	    		break;
			} 
		}
		
		return date;
    }

    // 입력 월의 마지막 월요일을 찾는다. 
    // YYYYMMDD형태로 리턴 6일 이하일경우 다음달의 마지막 월요일일 리턴.
    function gfn_getMonthLastMonday(yyyymmdd){
    	var getLastDay = gfn_getAddDate(gfn_getAddMonth(yyyymmdd.substring(0, 6) + '01', 1), -1) ;

		var week = new Array('0', '1', '2', '3', '4', '5', '6');		// 0:일,1:월,2:화,3:수,4:목,5:금,6:토 
		var dayOfWeek = "";												// 학습 시작일 시작요일
		var date = "";													// 기본 시작일 셋팅
    	
		if (parseInt(getLastDay) - parseInt(gfn_getToday()) < 6 && (week[new Date(getLastDay.substr(0,4) +"-"+ getLastDay.substr(4,2) +"-"+ getLastDay.substr(6,2)).getDay()]) != "1"){
    		getLastDay = gfn_getAddDate(gfn_getAddMonth(yyyymmdd.substring(0, 6) + '01', 2), -1);
    	}

		for(var i = 0; i < 7; i++){
			date = gfn_getAddDate(getLastDay, -1 * i);
			
			dayOfWeek = week[new Date(date.substr(0,4) +"-"+ date.substr(4,2) +"-"+ date.substr(6,2)).getDay()];	
			if(dayOfWeek =="1"){
        		break;
			}
		}
		
		return date;
    }

    // 현재 입력월의 마지막 날을 YYYYMMDD형태로 리턴
    function gfn_getAddMonthLastday(yyyymmdd){
        var getNextFirstDay = gfn_getAddMonth(yyyymmdd, 2) ;
        var getLastDay = gfn_getAddDate(getNextFirstDay, -1)
        return getLastDay ;
    }
    
    // 파라미터로 전달 받은 날짜에 +- 날짜 계산 하여 리턴
    function gfn_getAddDate(strDate, dayCnt){
        try{
            if( gfn_isNull(strDate) || strDate.length != 8 ){
                d = new Date();
            }else{
                d = new Date(strDate.substring(0,4), (strDate.substring(4,6)-1), strDate.substring(6,8));
            }
            d.setDate( (d.getDate() + dayCnt) );

            var year = d.getFullYear();
            var month = (Number(d.getMonth()) + 1);
            month = (month<10?"0":"") + month;
            var date = d.getDate();
            date = (date<10?"0":"") + date;

            return year + "" + month + "" + date;
        }catch(e){
            return strDate
        }
    }
    // 파라미터로 전달 받은 날짜에 +- 달 계산 하여 리턴
    function gfn_getAddMonth(strDate, monthCnt){
        try{
            if( gfn_isNull(strDate) || strDate.length < 6 ){
                return "";
            }

            var strYear = strDate.substring(0,4);
            var strMonth = strDate.substring(4,6);
            var strDay = "01";
            if(strDate.length > 6){
                strDay = strDate.substring(6,8);
            }

            // 계산할 달 수를 12로 나눈 몫으로 년도 계산, 나머지로 월 계산
            // 월 계산 결과가 0보다 작거나 같으면 년도 -1, 월 + 12
            //           12보다 크면 년도 + 1, 월 -12
            var shareVal = 0;	// 12로 나눈 몫
            if(monthCnt >0){
                shareVal = Math.floor(monthCnt / 12);
            }else{
                shareVal = Math.ceil(monthCnt / 12);
            }
            var remainder = monthCnt % 12;				// 12로 나눈 나머지
            var calMonth = Number(strMonth) + remainder;
            // 계산된 월이 0보다 같거나 작을 경우 년도 -1, 월 + 12
            if(calMonth <= 0){
                shareVal = shareVal - 1;
                calMonth = calMonth + 12;
            // 계산된 월이 12보다 클 경우 년도 + 1, 월 - 12
            }else if(calMonth > 12){
                shareVal = shareVal + 1;
                calMonth = calMonth - 12;
            }
            var rsltYear = Number(strYear) + shareVal;
            var rsltMonth = (calMonth<10?"0":"") + calMonth;
            var rsltDay = Number(strDay);
            
            var lastDay = (new Date(rsltYear, rsltMonth, 0)).getDate();
            //날짜가 계산된 년월의 마지막날보다 클 경우 마지막날로 날짜세팅(예: 20170231 -> 20170228)
            if(lastDay < strDay){
            	rsltDay = Number(lastDay);
            }

            rsltDay = (rsltDay<10?"0":"") + rsltDay;


            return rsltYear + "" + rsltMonth + "" + rsltDay;
        }catch(e){
            return strDate
        }
    }
    /**
     * 시작일 ~ 종료일 사이의 일 수 리턴
     * @param v_strDate1 시작일
     * @param v_strDate2 종료일
     * @returns
     */
    function gfn_daysBetween ( v_strDate1, v_strDate2 ) {
    	
    	  var date1 = gfn_toDate(v_strDate1);
    	  var date2 = gfn_toDate(v_strDate2);
    	  
    	  //Get 1 day in milliseconds
    	  var one_day=1000*60*60*24;

    	  // Convert both dates to milliseconds
    	  var date1_ms = date1.getTime();
    	  var date2_ms = date2.getTime();

    	  // Calculate the difference in milliseconds
    	  var difference_ms = date2_ms - date1_ms;
    	    
    	  // Convert back to days and return
    	  return Math.round(difference_ms/one_day); 
    }
    
    /**
     * @param pStartDate - 시작일
     * @param pEndDate  - 마지막일
     * @param pType       - 'D':일수, 'M':개월수
     * @returns {Number}
     */
    function gfn_calcDayMonthCount(pStartDate, pEndDate, pType) {
        var strSDT = new Date(pStartDate.substring(0,4),pStartDate.substring(4,6)-1,pStartDate.substring(6,8));
        var strEDT = new Date(pEndDate.substring(0,4),pEndDate.substring(4,6)-1,pEndDate.substring(6,8));
        var strTermCnt = 0;
         
        if(pType == 'D') {  //일수 차이
            strTermCnt = (strEDT.getTime()-strSDT.getTime())/(1000*60*60*24);
        } else {            //개월수 차이
            //년도가 같으면 단순히 월을 마이너스 한다.
            // => 20090301-20090201 의 경우(윤달이 있는 경우) 아래 else의 로직으로는 정상적인 1이 리턴되지 않는다.
            if(pEndDate.substring(0,4) == pStartDate.substring(0,4)) {
                strTermCnt = pEndDate.substring(4,6) * 1 - pStartDate.substring(4,6) * 1;
            } else {
                //strTermCnt = Math.floor((strEDT.getTime()-strSDT.getTime())/(1000*60*60*24*365.25/12));
                strTermCnt = Math.round((strEDT.getTime()-strSDT.getTime())/(1000*60*60*24*365/12));
            }
        }
        
        return strTermCnt;
    }    
    /**
	* 'YYYYMMDD'형태의 String 을 Date 타입으로 변환
	*/
    function gfn_toDate(str) {
        if(!/^(\d){8}$/.test(str)) return "invalid date";
        var y = str.substr(0,4),
            m = str.substr(4,2) * 1 - 1,
            d = str.substr(6,2);
        return new Date(y,m,d);
    }
    /**
     * 현재 주차의 원하는 요일의 날짜 조회 (0 : 일, 1: 월, 2: 화, 3:수. 4:목, 5:금. 6:토)
     * @param dayNum
     */
    function getCurrentWeekSelectDay(dayNum){
        if(dayNum < 0 || dayNum > 6){
            console.log('요일 숫자가 범위를 벗어났습니다.');
            return;
        }
        var currentDate = new Date();
        var selectDate = currentDate.getDate() - currentDate.getDay() + dayNum;
        var resultDate = new Date(currentDate.setDate(selectDate));
        return resultDate;
    }


    /**
     * 문장속에서 URL을 찾아 리턴
     * @param str
     */
    function gfn_findURL(str){
        var p = /(http(s)?:\/\/([\w-]+\.)+[\w-]+(\/[\w-.\/?%&=]*)?)/gi;
        var result = str.match(p);
        return result;
    }

    /**
     *  URL 여부 체크
     */
    function gfn_isUrl(str){
        var urlList = findURL(str);
        var returnValue = false;
        if( gfn_isNull(urlList) ){
            returnValue = false;
        }else{
            returnValue = true;
        }

        return returnValue;
    }

    /**
     *  URL을 찾아서 링크 처리
     */
    function gfn_makeLink(urlStr){
        var p = /(http(s)?:\/\/([\w-]+\.)+[\w-]+(\/[\w-.\/?%&=]*)?)/gi;
        return urlStr.replace(p,'<a href="$1">$1</a>');
    }

    //2byte -> 1byte 처리
    function gfn_fmtKuText(val){
        var x_char = val + "";
        if (x_char == null || x_char == '') {
               return '';
        } else {
            var x_2byteChar = new String;
            var len = x_char.length;
            for (var i = 0; i < len; i++) {
                var c = x_char.charCodeAt(i);

                if (c >= 65281 && c <= 65374 && c != 65340) {
                    x_2byteChar += String.fromCharCode(c - 65248);
                 } else if (c == 8217) {
                   x_2byteChar += String.fromCharCode(39);
                 } else if (c == 8221) {
                   x_2byteChar += String.fromCharCode(34);
                 } else if (c == 12288) {
                   x_2byteChar += String.fromCharCode(32);
                 } else if (c == 65507) {
                   x_2byteChar += String.fromCharCode(126);
                 } else if (c == 65509) {
                   x_2byteChar += String.fromCharCode(92);
                 } else {
                   x_2byteChar += x_char.charAt(i);
                 }
           }
           return x_2byteChar;
        }
    }

    //JSON(key,value) 추출 후 2byte -> 1byte 처리
    function gfn_fmtKuTextJson(jsonObj){
        if( jsonObj == null || jsonObj == "" || jsonObj == "undefined"){
            return;
        }
        var jsonObj2 = jsonObj;
        var jsonObjArr = Object.keys(jsonObj2);
        for(var i=0;i<jsonObjArr.length;i++){
            jsonObj2[jsonObjArr[i]] = fmtKuText(jsonObj2[jsonObjArr[i]]);
        }
        return jsonObj2
    }

    //계좌/카드번호 마스킹 처리
    function gfn_maskingCardno(s){
        if(gfn_isNull(s)) return "";
        var t = gfn_trim(s);
        var star = "";
        try{
            if( s.length > 7 ){
                for(var i=0;i<s.length - 7;i++) star += "*";
                t = s.substring(0, 4) + star + s.substring(s.length - 3, s.length);
            }
            return t;
        }catch(e){
            return t;
        }
    }

/**
 * SP상품정보를 DB에서 불러오는 방식으로 변경
 * 상품정보 호출하는 페이지 내에서 직접 구현하면서 이 변수와 함수는 삭제처리
 */
//    var gfn_ObjSbjcInfo = [
//        {"sbjcCd":"1"   ,"sbjcNm":"빨간펜지도"           ,"sbjcNm1":"빨간펜지도　" ,"sbjcNm2":"빨간펜　" ,"url":"http://images.freesam.kyowon.co.kr/uploadFiles/image/2014/12099.png"}
//        ,{"sbjcCd":"4"   ,"sbjcNm":"눈으로보는세계인물"   ,"sbjcNm1":"눈보세계인물" ,"sbjcNm2":"세계인물" ,"url":"http://images.freesam.kyowon.co.kr/uploadFiles/image/2015/20511.png"}
//        ,{"sbjcCd":"7"   ,"sbjcNm":"３Ｄ애니메이션관리"   ,"sbjcNm1":"３Ｄ애니관리" ,"sbjcNm2":"３Ｄ관리" ,"url":"http://images.freesam.kyowon.co.kr/uploadFiles/image/2015/20267.png"}
//        ,{"sbjcCd":"8"   ,"sbjcNm":"솔루토이한국사"       ,"sbjcNm1":"솔루한국사　" ,"sbjcNm2":"솔한국사" ,"url":"http://images.freesam.kyowon.co.kr/uploadFiles/image/2015/12149.png"}
//        ,{"sbjcCd":"9"   ,"sbjcNm":"눈으로보는우리역사"   ,"sbjcNm1":"우리역사지도" ,"sbjcNm2":"우리역사" ,"url":"http://images.freesam.kyowon.co.kr/uploadFiles/image/2015/20271.png"}
//        ,{"sbjcCd":"10"  ,"sbjcNm":"솔루토이　지리"       ,"sbjcNm1":"솔루토이지리" ,"sbjcNm2":"솔루지리" ,"url":"http://images.freesam.kyowon.co.kr/uploadFiles/image/2015/20327.png"}
//        ,{"sbjcCd":"13"  ,"sbjcNm":"솔루토이위인지도"     ,"sbjcNm1":"솔루위인지도" ,"sbjcNm2":"솔루위인" ,"url":"http://images.freesam.kyowon.co.kr/uploadFiles/image/2015/20331.png"}
//        ,{"sbjcCd":"14"  ,"sbjcNm":"눈으로세계역사지도"   ,"sbjcNm1":"세계역사지도" ,"sbjcNm2":"세계역사" ,"url":"http://images.freesam.kyowon.co.kr/uploadFiles/image/2015/12140.png"}
//        ,{"sbjcCd":"16"  ,"sbjcNm":"한국을이끄는사람들"   ,"sbjcNm1":"한국이지도　" ,"sbjcNm2":"한국이끄" ,"url":"http://images.freesam.kyowon.co.kr/uploadFiles/image/2015/21268.png"}
//        ,{"sbjcCd":"17"  ,"sbjcNm":"테마삼국유사삼국사기" ,"sbjcNm1":"테마삼삼지도" ,"sbjcNm2":"테삼지도" ,"url":""}
//        ,{"sbjcCd":"18"  ,"sbjcNm":"세계를이끄는사람들"   ,"sbjcNm1":"세계사람들　" ,"sbjcNm2":"인물３　" ,"url":""}
//        ,{"sbjcCd":"19"  ,"sbjcNm":"솔루토이경제관리"     ,"sbjcNm1":"솔루토이경제" ,"sbjcNm2":"솔루경제" ,"url":"http://images.freesam.kyowon.co.kr/uploadFiles/image/2015/20315.png"}
//        ,{"sbjcCd":"20"  ,"sbjcNm":"Ｎ호야토야　첫번째"   ,"sbjcNm1":"Ｎ호야토야１" ,"sbjcNm2":"Ｎ호야１" ,"url":"http://images.freesam.kyowon.co.kr/uploadFiles/image/2015/20087.png"}
//        ,{"sbjcCd":"23"  ,"sbjcNm":"꼬잉꼬잉이솝극장철학" ,"sbjcNm1":"이솝극장철학" ,"sbjcNm2":"이솝극장" ,"url":"http://images.freesam.kyowon.co.kr/uploadFiles/image/2015/20506.png"}
//        ,{"sbjcCd":"24"  ,"sbjcNm":"교원소설삼국지지도"   ,"sbjcNm1":"삼국지지도　" ,"sbjcNm2":"삼국지　" ,"url":""}
//        ,{"sbjcCd":"25"  ,"sbjcNm":"위즈퍼니세계명작지도" ,"sbjcNm1":"세계명작지도" ,"sbjcNm2":"세계명작" ,"url":"http://images.freesam.kyowon.co.kr/uploadFiles/image/2015/12177.png"}
//        ,{"sbjcCd":"26"  ,"sbjcNm":"세계옛이야기지도"     ,"sbjcNm1":"세계옛지도　" ,"sbjcNm2":"세계옛이" ,"url":"http://images.freesam.kyowon.co.kr/uploadFiles/image/2015/20074.png"}
//        ,{"sbjcCd":"27"  ,"sbjcNm":"눈으로한국고전지도"   ,"sbjcNm1":"한국고전지도" ,"sbjcNm2":"한국고전" ,"url":"http://images.freesam.kyowon.co.kr/uploadFiles/image/2015/20019.png"}
//        ,{"sbjcCd":"28"  ,"sbjcNm":"Ｎ호야토야　두번째"   ,"sbjcNm1":"Ｎ호야토야２" ,"sbjcNm2":"Ｎ호야２" ,"url":"http://images.freesam.kyowon.co.kr/uploadFiles/image/2015/20091.png"}
//        ,{"sbjcCd":"29"  ,"sbjcNm":"솔루토이한자"         ,"sbjcNm1":"솔루토이한자" ,"sbjcNm2":"솔루한자" ,"url":""}
//        ,{"sbjcCd":"30"  ,"sbjcNm":"눈으로보는그리스로마" ,"sbjcNm1":"그리스로마신" ,"sbjcNm2":"로마신화" ,"url":"http://images.freesam.kyowon.co.kr/uploadFiles/image/2015/20026.png"}
//        ,{"sbjcCd":"31"  ,"sbjcNm":"자연의신비지도"       ,"sbjcNm1":"자연신비지도" ,"sbjcNm2":"자연신비" ,"url":"http://images.freesam.kyowon.co.kr/uploadFiles/image/2015/20372.png"}
//        ,{"sbjcCd":"33"  ,"sbjcNm":"사이언스프렌드지도"   ,"sbjcNm1":"사이언스지도" ,"sbjcNm2":"사이언스" ,"url":""}
//        ,{"sbjcCd":"34"  ,"sbjcNm":"수학개념동화지도"     ,"sbjcNm1":"수학개념지도" ,"sbjcNm2":"수학개념" ,"url":""}
//        ,{"sbjcCd":"35"  ,"sbjcNm":"과학개념동화지도"     ,"sbjcNm1":"과학개념지도" ,"sbjcNm2":"과학개념" ,"url":""}
//        ,{"sbjcCd":"36"  ,"sbjcNm":"자연이소곤소곤"       ,"sbjcNm1":"자연이소곤　" ,"sbjcNm2":"자연소곤" ,"url":"http://images.freesam.kyowon.co.kr/uploadFiles/image/2015/20392.png"}
//        ,{"sbjcCd":"37"  ,"sbjcNm":"교과서자연의신비"     ,"sbjcNm1":"교자연신비　" ,"sbjcNm2":"교자연신" ,"url":"http://images.freesam.kyowon.co.kr/uploadFiles/image/2015/20372.png"}
//        ,{"sbjcCd":"38"  ,"sbjcNm":"솔루토이수학"         ,"sbjcNm1":"솔루토이수학" ,"sbjcNm2":"솔루수학" ,"url":"http://images.freesam.kyowon.co.kr/uploadFiles/image/2015/20377.png"}
//        ,{"sbjcCd":"39"  ,"sbjcNm":"눈으로보는과학"       ,"sbjcNm1":"눈으로과학　" ,"sbjcNm2":"보는과학" ,"url":"http://images.freesam.kyowon.co.kr/uploadFiles/image/2015/20126.png"}
//        ,{"sbjcCd":"40"  ,"sbjcNm":"눈으로보는세계고전"   ,"sbjcNm1":"눈보세계고전" ,"sbjcNm2":"세계고전" ,"url":"http://images.freesam.kyowon.co.kr/uploadFiles/image/2015/21281.png"}
//        ,{"sbjcCd":"48"  ,"sbjcNm":"부엉이세계창작"       ,"sbjcNm1":"부엉이창작　" ,"sbjcNm2":"부엉창작" ,"url":"http://images.freesam.kyowon.co.kr/uploadFiles/image/2015/ADM/KEP/PRD/21690.png"}
//        ,{"sbjcCd":"49"  ,"sbjcNm":"눈으로보는통합교과"   ,"sbjcNm1":"눈보통합교과" ,"sbjcNm2":"눈통합교" ,"url":"http://images.freesam.kyowon.co.kr/uploadFiles/image/2015/21277.png"}
//        ,{"sbjcCd":"50"  ,"sbjcNm":"눈으로보는한국인물"   ,"sbjcNm1":"눈보한국인물" ,"sbjcNm2":"눈한인물" ,"url":"http://images.freesam.kyowon.co.kr/uploadFiles/image/2015/21268.png"}
//        ,{"sbjcCd":"52"  ,"sbjcNm":"빨간펜Ｓ한자"         ,"sbjcNm1":"빨간펜Ｓ한자" ,"sbjcNm2":"Ｓ한자　" ,"url":"http://images.freesam.kyowon.co.kr/uploadFiles/image/2015/12152.png"}
//        ,{"sbjcCd":"53"  ,"sbjcNm":"빨간펜Ｓ국어"         ,"sbjcNm1":"빨간펜Ｓ국어" ,"sbjcNm2":"Ｓ국어　" ,"url":"http://images.freesam.kyowon.co.kr/uploadFiles/image/2015/12150.png"}
//        ,{"sbjcCd":"54"  ,"sbjcNm":"빨간펜Ｓ수학"         ,"sbjcNm1":"빨간펜Ｓ수학" ,"sbjcNm2":"Ｓ수학　" ,"url":"http://images.freesam.kyowon.co.kr/uploadFiles/image/2015/12151.png"}
//        ,{"sbjcCd":"55"  ,"sbjcNm":"속성우리역사"         ,"sbjcNm1":"속성우리역사" ,"sbjcNm2":"속우리역" ,"url":"http://images.freesam.kyowon.co.kr/uploadFiles/image/2015/20271.png"}
//        ,{"sbjcCd":"56"  ,"sbjcNm":"Ｎｅｗ속성호야토야１" ,"sbjcNm1":"Ｎ속성호야１" ,"sbjcNm2":"Ｎ호야１" ,"url":"http://images.freesam.kyowon.co.kr/uploadFiles/image/2015/20087.png"}
//        ,{"sbjcCd":"57"  ,"sbjcNm":"Ｎｅｗ속성호야토야２" ,"sbjcNm1":"Ｎ속성호야２" ,"sbjcNm2":"Ｎ호야２" ,"url":"http://images.freesam.kyowon.co.kr/uploadFiles/image/2015/20091.png"}
//        ,{"sbjcCd":"58"  ,"sbjcNm":"속성그리스로마신화"   ,"sbjcNm1":"속성그리스로" ,"sbjcNm2":"속성그리" ,"url":"http://images.freesam.kyowon.co.kr/uploadFiles/image/2015/20026.png"}
//        ,{"sbjcCd":"59"  ,"sbjcNm":"속성눈으로보는한국고" ,"sbjcNm1":"속성한국고전" ,"sbjcNm2":"속성한고" ,"url":"http://images.freesam.kyowon.co.kr/uploadFiles/image/2015/20019.png"}
//        ,{"sbjcCd":"60"  ,"sbjcNm":"속성솔루토이지리"     ,"sbjcNm1":"속성솔루지리" ,"sbjcNm2":"속성솔지" ,"url":"http://images.freesam.kyowon.co.kr/uploadFiles/image/2015/20327.png"}
//        ,{"sbjcCd":"63"  ,"sbjcNm":"속성솔루토이위인지도" ,"sbjcNm1":"속성인물１　" ,"sbjcNm2":"속성인１" ,"url":"http://images.freesam.kyowon.co.kr/uploadFiles/image/2015/20331.png"}
//        ,{"sbjcCd":"66"  ,"sbjcNm":"속성이솝극장지도"     ,"sbjcNm1":"속성이솝지도" ,"sbjcNm2":"속성이솝" ,"url":"http://images.freesam.kyowon.co.kr/uploadFiles/image/2015/20506.png"}
//        ,{"sbjcCd":"67"  ,"sbjcNm":"속성삼국지지도"       ,"sbjcNm1":"속성삼국지도" ,"sbjcNm2":"속성삼국" ,"url":"http://images.freesam.kyowon.co.kr/uploadFiles/image/2015/20514.png"}
//        ,{"sbjcCd":"68"  ,"sbjcNm":"속성자연의신비지도"   ,"sbjcNm1":"속성자비지도" ,"sbjcNm2":"속성자비" ,"url":"http://images.freesam.kyowon.co.kr/uploadFiles/image/2015/20372.png"}
//        ,{"sbjcCd":"69"  ,"sbjcNm":"속성눈으로보는세계역" ,"sbjcNm1":"속성세계역２" ,"sbjcNm2":"속성역２" ,"url":"http://images.freesam.kyowon.co.kr/uploadFiles/image/2015/12140.png"}
//        ,{"sbjcCd":"70"  ,"sbjcNm":"속성한국을이끄는사람" ,"sbjcNm1":"속성한국이끄" ,"sbjcNm2":"속성인２" ,"url":"http://images.freesam.kyowon.co.kr/uploadFiles/image/2015/21268.png"}
//        ,{"sbjcCd":"71"  ,"sbjcNm":"속성테마삼국유사사기" ,"sbjcNm1":"속성테마삼삼" ,"sbjcNm2":"속성테삼" ,"url":""}
//        ,{"sbjcCd":"73"  ,"sbjcNm":"속성세계명작지도"     ,"sbjcNm1":"속성세계명작" ,"sbjcNm2":"속성명작" ,"url":"http://images.freesam.kyowon.co.kr/uploadFiles/image/2015/12177.png"}
//        ,{"sbjcCd":"74"  ,"sbjcNm":"속성세계옛이야기"     ,"sbjcNm1":"속성세계옛이" ,"sbjcNm2":"속성세계" ,"url":"http://images.freesam.kyowon.co.kr/uploadFiles/image/2015/20074.png"}
//        ,{"sbjcCd":"76"  ,"sbjcNm":"속성수학개념동화지도" ,"sbjcNm1":"속성수학개념" ,"sbjcNm2":"속성수학" ,"url":""}
//        ,{"sbjcCd":"77"  ,"sbjcNm":"속성과학개념동화지도" ,"sbjcNm1":"속성과학개념" ,"sbjcNm2":"속성과학" ,"url":""}
//        ,{"sbjcCd":"78"  ,"sbjcNm":"속성사이언스프랜드"   ,"sbjcNm1":"속성사이언스" ,"sbjcNm2":"속성사프" ,"url":""}
//        ,{"sbjcCd":"79"  ,"sbjcNm":"속성솔루토이경제"     ,"sbjcNm1":"속성솔루경제" ,"sbjcNm2":"속성솔경" ,"url":"http://images.freesam.kyowon.co.kr/uploadFiles/image/2015/20315.png"}
//        ,{"sbjcCd":"80"  ,"sbjcNm":"속성세계를이끄는사람" ,"sbjcNm1":"속성세계이끄" ,"sbjcNm2":"속성인３" ,"url":""}
//        ,{"sbjcCd":"87"  ,"sbjcNm":"솔루토이세계사"       ,"sbjcNm1":"솔루토이세계" ,"sbjcNm2":"솔루세계" ,"url":"http://images.freesam.kyowon.co.kr/uploadFiles/image/2015/21280.png"}
//        ,{"sbjcCd":"93"  ,"sbjcNm":"유아독서부엉이책장"   ,"sbjcNm1":"부엉이책장　" ,"sbjcNm2":"부엉이책" ,"url":"http://images.freesam.kyowon.co.kr/uploadFiles/image/2015/12161.png"}
//        ,{"sbjcCd":"94"  ,"sbjcNm":"단독부엉이책장"       ,"sbjcNm1":"단독부엉이책" ,"sbjcNm2":"단독부엉" ,"url":"http://images.freesam.kyowon.co.kr/uploadFiles/image/2015/12161.png"}
//        ,{"sbjcCd":"95"  ,"sbjcNm":"열려라지식문"         ,"sbjcNm1":"열려라지식문" ,"sbjcNm2":"열려라지" ,"url":"http://images.freesam.kyowon.co.kr/uploadFiles/image/2015/21278.png"}
//        ,{"sbjcCd":"96"  ,"sbjcNm":"생각이반짝이는지식별" ,"sbjcNm1":"생각반짝지식" ,"sbjcNm2":"반짝지식" ,"url":"http://images.freesam.kyowon.co.kr/uploadFiles/image/2015/12157.png"}
//        ,{"sbjcCd":"97"  ,"sbjcNm":"속성솔루한국사"       ,"sbjcNm1":"속성솔루한국" ,"sbjcNm2":"속솔한　" ,"url":"http://images.freesam.kyowon.co.kr/uploadFiles/image/2015/12149.png"}
//        ,{"sbjcCd":"98"  ,"sbjcNm":"월드인포세계지리"     ,"sbjcNm1":"월드인포세계" ,"sbjcNm2":"월드세계" ,"url":""}
//        ,{"sbjcCd":"99"  ,"sbjcNm":"월드인포한국지리"     ,"sbjcNm1":"월드인포한국" ,"sbjcNm2":"월드한국" ,"url":""}
//        ,{"sbjcCd":"101" ,"sbjcNm":"속성３Ｄ애니메이션"   ,"sbjcNm1":"속성３Ｄ애니" ,"sbjcNm2":"속성３Ｄ" ,"url":"http://images.freesam.kyowon.co.kr/uploadFiles/image/2015/20267.png"}
//        ,{"sbjcCd":"102" ,"sbjcNm":"속성열려라지식문"     ,"sbjcNm1":"속성열려라지" ,"sbjcNm2":"속열려라" ,"url":"http://images.freesam.kyowon.co.kr/uploadFiles/image/2015/21278.png"}
//        ,{"sbjcCd":"103" ,"sbjcNm":"속성반짝이는지식별"   ,"sbjcNm1":"속성지식별　" ,"sbjcNm2":"속성지식" ,"url":"http://images.freesam.kyowon.co.kr/uploadFiles/image/2015/12157.png"}
//        ,{"sbjcCd":"104" ,"sbjcNm":"속성월드인포세계지리" ,"sbjcNm1":"속성세계지리" ,"sbjcNm2":"속성세계" ,"url":""}
//        ,{"sbjcCd":"105" ,"sbjcNm":"속성월드인포한국지리" ,"sbjcNm1":"속성한국지리" ,"sbjcNm2":"속성한지" ,"url":""}
//        ,{"sbjcCd":"106" ,"sbjcNm":"속성자연이소곤소곤"   ,"sbjcNm1":"속성자연소곤" ,"sbjcNm2":"속성소곤" ,"url":"http://images.freesam.kyowon.co.kr/uploadFiles/image/2015/20392.png"}
//        ,{"sbjcCd":"107" ,"sbjcNm":"속성교과서자연의신비" ,"sbjcNm1":"속성교과자연" ,"sbjcNm2":"속성교자" ,"url":"http://images.freesam.kyowon.co.kr/uploadFiles/image/2015/20372.png"}
//        ,{"sbjcCd":"108" ,"sbjcNm":"속성솔루토이수학"     ,"sbjcNm1":"속성솔루수학" ,"sbjcNm2":"속성솔수" ,"url":"http://images.freesam.kyowon.co.kr/uploadFiles/image/2015/20377.png"}
//        ,{"sbjcCd":"109" ,"sbjcNm":"속성눈으로보는과학"   ,"sbjcNm1":"속성눈보과학" ,"sbjcNm2":"속눈보과" ,"url":"http://images.freesam.kyowon.co.kr/uploadFiles/image/2015/20126.png"}
//        ,{"sbjcCd":"110" ,"sbjcNm":"속성눈보는세계인물"   ,"sbjcNm1":"속성세계인물" ,"sbjcNm2":"속성세인" ,"url":"http://images.freesam.kyowon.co.kr/uploadFiles/image/2015/20511.png"}
//        ,{"sbjcCd":"111" ,"sbjcNm":"속성눈보는통합교과"   ,"sbjcNm1":"속성통합교과" ,"sbjcNm2":"속성통합" ,"url":"http://images.freesam.kyowon.co.kr/uploadFiles/image/2015/21277.png"}
//        ,{"sbjcCd":"112" ,"sbjcNm":"속성눈보는세계고전"   ,"sbjcNm1":"속성세계고전" ,"sbjcNm2":"속성고전" ,"url":"http://images.freesam.kyowon.co.kr/uploadFiles/image/2015/21281.png"}
//        ,{"sbjcCd":"113" ,"sbjcNm":"속성부엉이세계창작"   ,"sbjcNm1":"속성부엉창작" ,"sbjcNm2":"속성창작" ,"url":"http://images.freesam.kyowon.co.kr/uploadFiles/image/2015/ADM/KEP/PRD/21690.png"}
//        ,{"sbjcCd":"114" ,"sbjcNm":"속성눈보는한국인물"   ,"sbjcNm1":"속성한국인물" ,"sbjcNm2":"속성한인" ,"url":"http://images.freesam.kyowon.co.kr/uploadFiles/image/2015/21268.png"}
//        ,{"sbjcCd":"115" ,"sbjcNm":"속성솔루토이세계사"   ,"sbjcNm1":"속성솔루세계" ,"sbjcNm2":"속솔세계" ,"url":"http://images.freesam.kyowon.co.kr/uploadFiles/image/2015/21280.png"}
//        ,{"sbjcCd":"116" ,"sbjcNm":"속성호시탐탐과학"     ,"sbjcNm1":"속성호탐과학" ,"sbjcNm2":"속성호탐" ,"url":"http://images.freesam.kyowon.co.kr/uploadFiles/image/2015/21264.png"}
//        ,{"sbjcCd":"117" ,"sbjcNm":"속성똑똑수학단추"     ,"sbjcNm1":"속성똑똑수학" ,"sbjcNm2":"속똑수학" ,"url":"http://images.freesam.kyowon.co.kr/uploadFiles/image/2015/21265.png"}
//        ,{"sbjcCd":"118" ,"sbjcNm":"속성솔루토이국어"     ,"sbjcNm1":"속성솔루국어" ,"sbjcNm2":"속솔국어" ,"url":"http://images.freesam.kyowon.co.kr/uploadFiles/image/2015/20075.png"}
//        ,{"sbjcCd":"119" ,"sbjcNm":"속성눈보는정치경제"   ,"sbjcNm1":"속눈보는정경" ,"sbjcNm2":"속성정경" ,"url":"http://images.freesam.kyowon.co.kr/uploadFiles/image/2015/20298.png"}
//        ,{"sbjcCd":"120" ,"sbjcNm":"꾸러기책장"           ,"sbjcNm1":"꾸러기책장　" ,"sbjcNm2":"꾸러기책" ,"url":"http://images.freesam.kyowon.co.kr/uploadFiles/image/2015/12162.png"}
//        ,{"sbjcCd":"121" ,"sbjcNm":"솔루토이국어"         ,"sbjcNm1":"솔루토이국어" ,"sbjcNm2":"솔루국어" ,"url":"http://images.freesam.kyowon.co.kr/uploadFiles/image/2015/20075.png"}
//        ,{"sbjcCd":"122" ,"sbjcNm":"이야기솜사탕"         ,"sbjcNm1":"이야기솜사탕" ,"sbjcNm2":"이솜사탕" ,"url":"http://images.freesam.kyowon.co.kr/uploadFiles/image/2015/20138.png"}
//        ,{"sbjcCd":"123" ,"sbjcNm":"눈으로보는중국고전"   ,"sbjcNm1":"눈보중국고전" ,"sbjcNm2":"중국고전" ,"url":"http://images.freesam.kyowon.co.kr/uploadFiles/image/2015/20073.png"}
//        ,{"sbjcCd":"124" ,"sbjcNm":"눈으로보는우리나라"   ,"sbjcNm1":"눈보는우리나" ,"sbjcNm2":"눈보우리" ,"url":"http://images.freesam.kyowon.co.kr/uploadFiles/image/2015/20296.png"}
//        ,{"sbjcCd":"125" ,"sbjcNm":"솔루토이인물"         ,"sbjcNm1":"솔루토이인물" ,"sbjcNm2":"솔루인물" ,"url":"http://images.freesam.kyowon.co.kr/uploadFiles/image/2015/21261.png"}
//        ,{"sbjcCd":"126" ,"sbjcNm":"솔루토이정치경제"     ,"sbjcNm1":"솔루정치경제" ,"sbjcNm2":"솔루정경" ,"url":"http://images.freesam.kyowon.co.kr/uploadFiles/image/2015/20315.png"}
//        ,{"sbjcCd":"127" ,"sbjcNm":"Ｎｅｗ솔루토이과학"   ,"sbjcNm1":"Ｎ솔루과학　" ,"sbjcNm2":"Ｎ솔루과" ,"url":"http://images.freesam.kyowon.co.kr/uploadFiles/image/2015/ADM/KEP/PRD/29114.png"}
//        ,{"sbjcCd":"128" ,"sbjcNm":"조물조물자연놀이"     ,"sbjcNm1":"조물조물자연" ,"sbjcNm2":"조물자연" ,"url":"http://images.freesam.kyowon.co.kr/uploadFiles/image/2015/12148.png"}
//        ,{"sbjcCd":"129" ,"sbjcNm":"호시탐탐세계문화"     ,"sbjcNm1":"호탐세계문화" ,"sbjcNm2":"호탐세계" ,"url":"http://images.freesam.kyowon.co.kr/uploadFiles/image/2015/21282.png"}
//        ,{"sbjcCd":"130" ,"sbjcNm":"눈으로보는정치경제"   ,"sbjcNm1":"눈보정치경제" ,"sbjcNm2":"정치경제" ,"url":"http://images.freesam.kyowon.co.kr/uploadFiles/image/2015/20298.png"}
//        ,{"sbjcCd":"131" ,"sbjcNm":"올스펜부엉이영어책장" ,"sbjcNm1":"올펜부엉영어" ,"sbjcNm2":"펜부엉영" ,"url":"http://images.freesam.kyowon.co.kr/uploadFiles/image/2015/12160.png"}
//        ,{"sbjcCd":"132" ,"sbjcNm":"올스토리펜부엉이책장" ,"sbjcNm1":"올펜부엉책장" ,"sbjcNm2":"펜부엉책" ,"url":"http://images.freesam.kyowon.co.kr/uploadFiles/image/2015/12161.png"}
//        ,{"sbjcCd":"133" ,"sbjcNm":"올스펜단독부엉이책장" ,"sbjcNm1":"올펜단독부엉" ,"sbjcNm2":"올펜단부" ,"url":"http://images.freesam.kyowon.co.kr/uploadFiles/image/2015/12161.png"}
//        ,{"sbjcCd":"134" ,"sbjcNm":"올스토리펜빨간Ｓ국어" ,"sbjcNm1":"올펜빨간국어" ,"sbjcNm2":"펜빨Ｓ국" ,"url":"http://images.freesam.kyowon.co.kr/uploadFiles/image/2015/12150.png"}
//        ,{"sbjcCd":"140" ,"sbjcNm":"속성눈보는실험과학"   ,"sbjcNm1":"속성실험과학" ,"sbjcNm2":"속실험과" ,"url":"http://images.freesam.kyowon.co.kr/uploadFiles/image/2015/12143.png"}
//        ,{"sbjcCd":"141" ,"sbjcNm":"올씽킹맵스쿨１단계"   ,"sbjcNm1":"올씽킹맵１단" ,"sbjcNm2":"올맵１단" ,"url":"http://images.freesam.kyowon.co.kr/uploadFiles/image/2015/12163.png"}
//        ,{"sbjcCd":"142" ,"sbjcNm":"올씽킹맵스쿨２단계"   ,"sbjcNm1":"올씽킹맵２단" ,"sbjcNm2":"올맵２단" ,"url":"http://images.freesam.kyowon.co.kr/uploadFiles/image/2015/12163.png"}
//        ,{"sbjcCd":"143" ,"sbjcNm":"올씽킹맵스쿨３단계"   ,"sbjcNm1":"올씽킹맵３단" ,"sbjcNm2":"올맵３단" ,"url":"http://images.freesam.kyowon.co.kr/uploadFiles/image/2015/12163.png"}
//        ,{"sbjcCd":"144" ,"sbjcNm":"올씽킹맵스쿨４단계"   ,"sbjcNm1":"올씽킹맵４단" ,"sbjcNm2":"올맵４단" ,"url":"http://images.freesam.kyowon.co.kr/uploadFiles/image/2015/12163.png"}
//        ,{"sbjcCd":"145" ,"sbjcNm":"놀이재미베베똑"       ,"sbjcNm1":"놀이재미베베" ,"sbjcNm2":"놀재미베" ,"url":"http://images.freesam.kyowon.co.kr/uploadFiles/image/2015/ADM/KEP/PRD/31752.png"}
//        ,{"sbjcCd":"146" ,"sbjcNm":"말재미베베똑"         ,"sbjcNm1":"말재미베베똑" ,"sbjcNm2":"말재미베" ,"url":"http://images.freesam.kyowon.co.kr/uploadFiles/image/2015/20268.png"}
//        ,{"sbjcCd":"147" ,"sbjcNm":"개정솔루토이과학"     ,"sbjcNm1":"개정솔루과학" ,"sbjcNm2":"개솔과학" ,"url":"http://images.freesam.kyowon.co.kr/uploadFiles/image/2015/ADM/KEP/PRD/29114.png"}
//        ,{"sbjcCd":"148" ,"sbjcNm":"개정솔루과학한정판"   ,"sbjcNm1":"개정솔과한정" ,"sbjcNm2":"개솔과한" ,"url":"http://images.freesam.kyowon.co.kr/uploadFiles/image/2015/ADM/KEP/PRD/29114.png"}
//        ,{"sbjcCd":"149" ,"sbjcNm":"호시탐탐논리국어"     ,"sbjcNm1":"호탐논리국어" ,"sbjcNm2":"호논리국" ,"url":"http://images.freesam.kyowon.co.kr/uploadFiles/image/2015/ADM/KEP/PRD/37186.png"}
//        ,{"sbjcCd":"150" ,"sbjcNm":"호시탐탐과학"         ,"sbjcNm1":"호시탐탐과학" ,"sbjcNm2":"호탐과학" ,"url":"http://images.freesam.kyowon.co.kr/uploadFiles/image/2015/21264.png"}
//        ,{"sbjcCd":"151" ,"sbjcNm":"똑똑수학단추"         ,"sbjcNm1":"똑똑수학단추" ,"sbjcNm2":"수학단추" ,"url":"http://images.freesam.kyowon.co.kr/uploadFiles/image/2015/21265.png"}
//        ,{"sbjcCd":"152" ,"sbjcNm":"눈으로보는실험과학"   ,"sbjcNm1":"눈보는실험과" ,"sbjcNm2":"눈실험과" ,"url":"http://images.freesam.kyowon.co.kr/uploadFiles/image/2015/12143.png"}
//        ,{"sbjcCd":"153" ,"sbjcNm":"솔루토이삼국사기유사" ,"sbjcNm1":"솔루삼국사기" ,"sbjcNm2":"솔삼국사" ,"url":"http://images.freesam.kyowon.co.kr/uploadFiles/image/2015/20514.png"}
//        ,{"sbjcCd":"154" ,"sbjcNm":"호시탐탐문화유산"     ,"sbjcNm1":"호탐문화유산" ,"sbjcNm2":"호탐문화" ,"url":"http://images.freesam.kyowon.co.kr/uploadFiles/image/2015/21263.png"}
//        ,{"sbjcCd":"155" ,"sbjcNm":"솔루토이예술"         ,"sbjcNm1":"솔루토이예술" ,"sbjcNm2":"솔루예술" ,"url":"http://images.freesam.kyowon.co.kr/uploadFiles/image/2015/20513.png"}
//        ,{"sbjcCd":"156" ,"sbjcNm":"놀이자연이소곤소곤"   ,"sbjcNm1":"놀이자연소곤" ,"sbjcNm2":"놀이자연" ,"url":"http://images.freesam.kyowon.co.kr/uploadFiles/image/2015/20392.png"}
//        ,{"sbjcCd":"157" ,"sbjcNm":"호시탐탐세계사"       ,"sbjcNm1":"호시탐세계사" ,"sbjcNm2":"호탐세계" ,"url":"http://images.freesam.kyowon.co.kr/uploadFiles/image/2015/21353.png"}
//        ,{"sbjcCd":"158" ,"sbjcNm":"솔루토이탐구"         ,"sbjcNm1":"솔루토이탐구" ,"sbjcNm2":"솔루탐구" ,"url":"http://images.freesam.kyowon.co.kr/uploadFiles/image/2015/21266.png"}
//        ,{"sbjcCd":"159" ,"sbjcNm":"조물조물사회놀이"     ,"sbjcNm1":"조물조물사회" ,"sbjcNm2":"조물사회" ,"url":"http://images.freesam.kyowon.co.kr/uploadFiles/image/2015/21472.png"}
//        ,{"sbjcCd":"160" ,"sbjcNm":"호시탐탐박물관"       ,"sbjcNm1":"호시탐탐박물" ,"sbjcNm2":"호탐박물" ,"url":"http://images.freesam.kyowon.co.kr/uploadFiles/image/2015/ADM/KEP/PRD/25432.png"}
//        ,{"sbjcCd":"161" ,"sbjcNm":"빨간펜누리똑１단계"   ,"sbjcNm1":"누리똑１단계" ,"sbjcNm2":"누리１단" ,"url":"http://images.freesam.kyowon.co.kr/uploadFiles/image/2015/ADM/KEP/PRD/22560.png"}
//        ,{"sbjcCd":"162" ,"sbjcNm":"빨간펜누리똑２단계"   ,"sbjcNm1":"누리똑２단계" ,"sbjcNm2":"누리２단" ,"url":"http://images.freesam.kyowon.co.kr/uploadFiles/image/2015/ADM/KEP/PRD/22560.png"}
//        ,{"sbjcCd":"163" ,"sbjcNm":"빨간펜누리똑３단계"   ,"sbjcNm1":"누리똑３단계" ,"sbjcNm2":"누리３단" ,"url":"http://images.freesam.kyowon.co.kr/uploadFiles/image/2015/ADM/KEP/PRD/22560.png"}
//        ,{"sbjcCd":"164" ,"sbjcNm":"빨간펜누리똑４단계"   ,"sbjcNm1":"누리똑４단계" ,"sbjcNm2":"누리４단" ,"url":"http://images.freesam.kyowon.co.kr/uploadFiles/image/2015/ADM/KEP/PRD/22560.png"}
//        ,{"sbjcCd":"165" ,"sbjcNm":"속성호시탐탐문화유산" ,"sbjcNm1":"속성호시문화" ,"sbjcNm2":"속호시문" ,"url":"http://images.freesam.kyowon.co.kr/uploadFiles/image/2015/21263.png"}
//        ,{"sbjcCd":"166" ,"sbjcNm":"속성눈으로보는중국고" ,"sbjcNm1":"속눈보중국고" ,"sbjcNm2":"속중국고" ,"url":"http://images.freesam.kyowon.co.kr/uploadFiles/image/2015/20073.png"}
//        ,{"sbjcCd":"167" ,"sbjcNm":"속성솔루토이예술"     ,"sbjcNm1":"속성솔루예술" ,"sbjcNm2":"속솔예술" ,"url":"http://images.freesam.kyowon.co.kr/uploadFiles/image/2015/20513.png"}
//        ,{"sbjcCd":"168" ,"sbjcNm":"속성눈보는우리나라"   ,"sbjcNm1":"속눈보우리나" ,"sbjcNm2":"속눈우리" ,"url":"http://images.freesam.kyowon.co.kr/uploadFiles/image/2015/20296.png"}
//        ,{"sbjcCd":"169" ,"sbjcNm":"속성솔루토이인물"     ,"sbjcNm1":"속성솔루인물" ,"sbjcNm2":"속솔인물" ,"url":"http://images.freesam.kyowon.co.kr/uploadFiles/image/2015/21261.png"}
//        ,{"sbjcCd":"170" ,"sbjcNm":"속성솔루토이테마삼삼" ,"sbjcNm1":"속성솔루삼삼" ,"sbjcNm2":"속성솔삼" ,"url":"http://images.freesam.kyowon.co.kr/uploadFiles/image/2015/20514.png"}
//        ,{"sbjcCd":"171" ,"sbjcNm":"속성이야기솜사탕"     ,"sbjcNm1":"속성이야기솜" ,"sbjcNm2":"속성이솜" ,"url":"http://images.freesam.kyowon.co.kr/uploadFiles/image/2015/20138.png"}
//        ,{"sbjcCd":"172" ,"sbjcNm":"속성Ｎｅｗ솔루과학"   ,"sbjcNm1":"속성Ｎ솔루과" ,"sbjcNm2":"속Ｎ솔과" ,"url":"http://images.freesam.kyowon.co.kr/uploadFiles/image/2015/ADM/KEP/PRD/29114.png"}
//        ,{"sbjcCd":"173" ,"sbjcNm":"속성솔루토이정치경제" ,"sbjcNm1":"속성솔루정경" ,"sbjcNm2":"속성정경" ,"url":"http://images.freesam.kyowon.co.kr/uploadFiles/image/2015/20315.png"}
//        ,{"sbjcCd":"174" ,"sbjcNm":"속성솔루토이한자"     ,"sbjcNm1":"속성솔루한자" ,"sbjcNm2":"속성한자" ,"url":"http://images.freesam.kyowon.co.kr/uploadFiles/image/2015/ADM/KEP/PRD/60569.png"}
//        ,{"sbjcCd":"175" ,"sbjcNm":"속성조물조물사회놀이" ,"sbjcNm1":"속성조물사회" ,"sbjcNm2":"속조물사" ,"url":"http://images.freesam.kyowon.co.kr/uploadFiles/image/2015/21472.png"}
//        ,{"sbjcCd":"176" ,"sbjcNm":"３Ｄ애니메이션∥"     ,"sbjcNm1":"３Ｄ애니∥"   ,"sbjcNm2":"３Ｄ앤∥" ,"url":"http://images.freesam.kyowon.co.kr/uploadFiles/image/2015/ADM/KEP/PRD/42569.png"}
//        ,{"sbjcCd":"177" ,"sbjcNm":"스마트통우리역사"     ,"sbjcNm1":"스마우리역사" ,"sbjcNm2":"Ｓ우역사" ,"url":"http://images.freesam.kyowon.co.kr/uploadFiles/image/2015/ADM/KEP/PRD/50795.png"}
//        ,{"sbjcCd":"178" ,"sbjcNm":"ＮＥＷ생각반짝지식별" ,"sbjcNm1":"Ｎ생각지식별" ,"sbjcNm2":"Ｎ지식별" ,"url":"http://images.freesam.kyowon.co.kr/uploadFiles/image/2015/12157.png"}
//        ,{"sbjcCd":"179" ,"sbjcNm":"Ｎ눈으로보는우리역사" ,"sbjcNm1":"Ｎ눈우리역사" ,"sbjcNm2":"Ｎ눈역사" ,"url":"http://images.freesam.kyowon.co.kr/uploadFiles/image/2015/20271.png"}
//        ,{"sbjcCd":"180" ,"sbjcNm":"뉴열려라지식문개정"   ,"sbjcNm1":"뉴열려라지식" ,"sbjcNm2":"뉴지식문" ,"url":"http://images.freesam.kyowon.co.kr/uploadFiles/image/2015/ADM/KEP/PRD/55749.png"}
//        ,{"sbjcCd":"181" ,"sbjcNm":"속성개정솔루토이과학" ,"sbjcNm1":"속성개정솔과" ,"sbjcNm2":"속성개솔" ,"url":"http://images.freesam.kyowon.co.kr/uploadFiles/image/2015/ADM/KEP/PRD/29114.png"}
//        ,{"sbjcCd":"182" ,"sbjcNm":"속성호시탐탐박물관"   ,"sbjcNm1":"속성호시박물" ,"sbjcNm2":"속성박물" ,"url":"http://images.freesam.kyowon.co.kr/uploadFiles/image/2015/ADM/KEP/PRD/25432.png"}
//        ,{"sbjcCd":"183" ,"sbjcNm":"속성뉴생각반짝지식별" ,"sbjcNm1":"속성뉴지식별" ,"sbjcNm2":"속성Ｎ지" ,"url":"http://images.freesam.kyowon.co.kr/uploadFiles/image/2015/12157.png"}
//        ,{"sbjcCd":"184" ,"sbjcNm":"속성Ｎ눈보는우리역사" ,"sbjcNm1":"속성Ｎ우리역" ,"sbjcNm2":"속Ｎ역사" ,"url":"http://images.freesam.kyowon.co.kr/uploadFiles/image/2015/20271.png"}
//        ,{"sbjcCd":"185" ,"sbjcNm":"똑똑한글단추"         ,"sbjcNm1":"똑똑한글단추" ,"sbjcNm2":"똑똑한글" ,"url":"http://images.freesam.kyowon.co.kr/uploadFiles/image/2016/ADM/KEP/PRD/69099.png"}
//        ,{"sbjcCd":"186" ,"sbjcNm":"스마트통과학"         ,"sbjcNm1":"스마트통과학" ,"sbjcNm2":"스통과학" ,"url":"http://images.freesam.kyowon.co.kr/uploadFiles/image/2016/ADM/KEP/PRD/73958.png"}
//        ,{"sbjcCd":"187" ,"sbjcNm":"호야토야의옛이야기"   ,"sbjcNm1":"호야토야옛이" ,"sbjcNm2":"호토옛이" ,"url":"http://images.freesam.kyowon.co.kr/uploadFiles/image/2016/ADM/KEP/PRD/84061.png"}
//        ,{"sbjcCd":"188" ,"sbjcNm":"속성호시탐탐논리국어" ,"sbjcNm1":"속성호탐국어" ,"sbjcNm2":"속성호탐" ,"url":"http://images.freesam.kyowon.co.kr/uploadFiles/image/2015/ADM/KEP/PRD/37186.png"}
//        ,{"sbjcCd":"189" ,"sbjcNm":"속성스마트통우리역사" ,"sbjcNm1":"속성스통역사" ,"sbjcNm2":"속성스역" ,"url":"http://images.freesam.kyowon.co.kr/uploadFiles/image/2015/ADM/KEP/PRD/50795.png"}
//        ,{"sbjcCd":"190" ,"sbjcNm":"속성열려라지식문개정" ,"sbjcNm1":"속성개정열지" ,"sbjcNm2":"속성개열" ,"url":"http://images.freesam.kyowon.co.kr/uploadFiles/image/2015/ADM/KEP/PRD/55749.png"}
//        ,{"sbjcCd":"201" ,"sbjcNm":"키즈스마트빨간펜"     ,"sbjcNm1":"키즈스마트빨" ,"sbjcNm2":"키즈빨간" ,"url":"http://images.freesam.kyowon.co.kr/uploadFiles/image/2016/ADM/KEP/PRD/81472.png"}
//    ];
//    function gfn_getSbjcImgUrl(cd){
//    	for(var i=0;i<gfn_ObjSbjcInfo.length;i++){
//    		if(gfn_ObjSbjcInfo[i].sbjcCd == cd && !gfn_isNull(gfn_ObjSbjcInfo[i].url)){
//    			return gfn_ObjSbjcInfo[i].url;
//    		}
//    	}
//    	return ksosAppInfo.contextPath + "/resources/app/assets/img/spBi_cessation.svg";
//    }

    // 과목 단계명 가져오기
    function gfn_sbjStepNm(s){
        var step = "" ;
        var tmpStr = "" ;
        var trimStr = gfn_trim(s) ;

        try{
            if (trimStr.length == 5) {
                tmpStr = trimStr.substring(0, 1) ;
            } else if (trimStr.length == 6) {
                tmpStr = trimStr.substring(0, 2) ;
            } else {
                return step ;
            }
            switch (tmpStr) {
                case "8" : step = "4A" ; break;
                case "9" : step = "3A" ; break;
                case "10" : step = "2A" ; break;
                case "11" : step = "A" ; break;
                case "12" : step = "B" ; break;
                case "13" : step = "C" ; break;
                case "14" : step = "D" ; break;
                case "15" : step = "E" ; break;
                case "16" : step = "F" ; break;
            }
            return step + "단계";
        }catch(e){
            return step;
        }
    }

  //과목 호수 가져오기
    function gfn_sbjHoNm(s){

        var trimStr = gfn_trim(s) ;

        if (gfn_isNull(trimStr) || trimStr==='0') {//값이 0인 경우 0호로 표시되는거 보정
            return "" ;
        }

        if (trimStr.length == 6) {
            return trimStr.substring(3, 6) + "호" ;
        } else if (trimStr.length == 5) {
            return trimStr.substring(2, 5) + "호" ; ;
        } else {
            return trimStr + "호" ;
        }
    }

    // 과목 단계코드 가져오기(진도변경팝업)
    function gfn_sbjStepCd(s){
        var step = "" ;
        var tmpStr = "" ;
        var trimStr = gfn_trim(s) ;

        try{
            if (trimStr.length == 5) {
                tmpStr = trimStr.substring(0, 1) ;
            } else if (trimStr.length == 6) {
                tmpStr = trimStr.substring(0, 2) ;
            } else {
                return step ;
            }
            return tmpStr;
        }catch(e){
            return tmpStr;
        }
    }

    // 과목 단계변환코드 가져오기(진도변경팝업)
    function gfn_sbjStepConvCd(s){
        var step = "" ;
        var tmpStr = "" ;
        var trimStr = gfn_trim(s) ;

        try{
            if (trimStr.length == 5) {
                tmpStr = trimStr.substring(0, 1) ;
            } else if (trimStr.length == 6) {
                tmpStr = trimStr.substring(0, 2) ;
            } else {
                return step ;
            }
            switch (tmpStr) {
                case "8" : step = "4A" ; break;
                case "9" : step = "3A" ; break;
                case "10" : step = "2A" ; break;
                case "11" : step = "A" ; break;
                case "12" : step = "B" ; break;
                case "13" : step = "C" ; break;
                case "14" : step = "D" ; break;
                case "15" : step = "E" ; break;
                case "16" : step = "F" ; break;
            }
            return step;
        }catch(e){
            return step;
        }
    }

    //과목 호수코드 가져오기(진도변경팝업)
    function gfn_sbjHoCd(s){

        var trimStr = gfn_trim(s) ;

        if (gfn_isNull(trimStr)) {
            return "" ;
        }

        if (trimStr.length == 6) {
            return trimStr.substring(3, 6);
        } else if (trimStr.length == 5) {
            return trimStr.substring(2, 5);
        } else {
            return trimStr;
        }
    }

    /**
     * 전달 받은 파라미터 s에 l 길이만큼 c 캐릭터를 채워준다
     * @param s 변경 대상 값
     * @param l 왼쪽으로 채워줄 길지
     * @param c 채워줄 값(기본값 '0')
     */
    function gfn_lpad(s, l, c){
        try{
            var rtnVal = String(s);
            var sLen = String(s).length;
            var filChar = "0";
            if(c){
                filChar = c;
            }
            for(var i=sLen;i<l;i++){
                rtnVal = filChar + rtnVal;
            }
            return rtnVal;
        }catch(e){
            console.warn("gfn_lpad :: " + e);
            return s;
        }
    }

    /**
     * JSON data 의 각 항목들에 trim 처리
     */
    function gfn_trim_all(jsonObj){
        if( jsonObj == null || jsonObj == "" || jsonObj == "undefined"){
            return;
        }
        var jsonObj2 = jsonObj;
        var jsonObjArr = Object.keys(jsonObj2);
        for(var i=0;i<jsonObjArr.length;i++){
            jsonObj2[jsonObjArr[i]] = gfn_trim(jsonObj2[jsonObjArr[i]]);
        }
        return jsonObj2
    }

    /**
     * str이 초성 한글이면 1 아니면 0
     * @param str
     * @returns {Number}
     */
    function gfn_is_cho_hangul(str) {
        str = gfn_trim(str) ;

        var cho = ["ㄱ","ㄲ","ㄴ","ㄷ","ㄸ","ㄹ","ㅁ","ㅂ","ㅃ","ㅅ","ㅆ","ㅇ","ㅈ","ㅉ","ㅊ","ㅋ","ㅌ","ㅍ","ㅎ"];
        var onlyNum = "Y" ;

        for(i=0;i<str.length;i++) {

            var code = str.charCodeAt(i) - 44032 ;

            if (code>-1 && code<11172) {
                onlyNum = "N";
            }
        }

        if (onlyNum == "Y") {
            return 0;
        } else {
            return 1;
        }
    }

    /**
     * str을 초성 한글로 변환
     * @param str
     * @returns {String}
     */
    function gfn_cho_hangul(str) {
        str = gfn_trim(str) ;

        var cho = ["ㄱ","ㄲ","ㄴ","ㄷ","ㄸ","ㄹ","ㅁ","ㅂ","ㅃ","ㅅ","ㅆ","ㅇ","ㅈ","ㅉ","ㅊ","ㅋ","ㅌ","ㅍ","ㅎ"];
        var result = "";
        var onlyNum = "Y" ;

        for(i=0;i<str.length;i++) {

            var code = str.charCodeAt(i) - 44032 ;

            /* 코드 값이 초성인 경우 */
            if (code>-1 && code<11172) {
                result += cho[Math.floor(code/588)];
                onlyNum = "N";
            } else{
                code = code + 44032;
                result += String.fromCharCode(code) ;
            }
        }
        return result;
    }

    /**
     * str이 Date 포멧이 맞는지 확인
     * @param str
     */
    function gfn_isDate(str){
        // param이 null 이거나 6, 8자리가 아니면 false
        if(gfn_isNull(str)
        || (str.length != 6 && str.length != 8)){
            return false;
        }

        try{
            var y = str.substr(0,4),
            m = str.substr(4,2) - 1,
            d = str.substr(6,2);
            var D = new Date(y,m,d);
            return (D.getFullYear() == y && D.getMonth() == m && D.getDate() == d) ? true : false;
        }catch(e){
            return false;
        }

        return true;
    }

   /**
     * str이 Time 포멧이 맞는지 확인
     * @param str
     */
    function gfn_isTime(str){
        // param이 null 이거나 4자리가 아니면 false
        if(gfn_isNull(str) || (str.length != 4)) return false;

        try{
            var h = str.substr(0,2),
            m = str.substr(2,2);
            //console.log('['+h+']['+m+']');
            if(h<'00' || h>'23') return false;
            if(m<'00' || m>'59') return false;
        }catch(e){
            return false;
        }

        return true;
    }


    var isMobile = {
        Android: function() {
            return navigator.userAgent.match(/Android/i);
        },
        BlackBerry: function() {
            return navigator.userAgent.match(/BlackBerry/i);
        },
        iOS: function() {
            return navigator.userAgent.match(/iPhone|iPad|iPod/i);
        },
        Opera: function() {
            return navigator.userAgent.match(/Opera Mini/i);
        },
        Windows: function() {
            return navigator.userAgent.match(/IEMobile/i);
        },
        any: function() {
            return (isMobile.Android() || isMobile.BlackBerry() || isMobile.iOS() || isMobile.Opera() || isMobile.Windows());
        }
    };

    /**
     *  문자열의 바이트수를 계산하여 리턴
     */
    function gfn_getByteLength(inputText){
        return (function(s,b,i,c){
                for(b=i=0;c=s.charCodeAt(i++);b+=c>>11?2:c>>7?2:1);
                return b
        })(inputText);
    }

    /**
     * POST 방식의 동적 URL 호출함수
     * @param a (호출할 URL)
     * @param b (호출시 넘겨줄 파라미터:JSON형식의 Object로 정의)
     * @param c (form의 target을 지정)
     * @param d (method를 지정 [post 또는 get]) (default:post)
     * @returns void
     */
    var gfn_goUrl=function(a,b,c,d){
        // 기존 폼이 있는 경우 삭제 후 재생성
        if($('#_sys_dynamic_form').length > 0) {
            $('#_sys_dynamic_form').remove();
        }
        var frm_mthd;
        if(b == null) b = {};
        if(gfn_isNull(d)) frm_mthd = "post";
        else frm_mthd = d;
        var e=document.createElement('form');
        e.setAttribute('id','_sys_dynamic_form')
        e.setAttribute('method',frm_mthd);
        e.setAttribute('action',a);
        e.setAttribute('target',c);
        for(var f in b){
            if($.isArray(b[f])){
                for( var i=0;i < b[f].length; i++ ){
                    var g=document.createElement('input');
                    g.setAttribute('type','hidden');
                    g.setAttribute('name',f);
                    g.setAttribute('value',b[f][i]);
                    e.appendChild(g);
                }
            }else{
                var g=document.createElement('input');
                g.setAttribute('type','hidden');
                g.setAttribute('name',f);
                g.setAttribute('value',b[f]);
                e.appendChild(g);
            }
        }
        document.body.appendChild(e);
        e.submit();
    }

    /**
     *  IE버전 리턴함수
     */
    function gfn_get_version_of_IE () {
        var word;
        var version = "N/A";
        var agent = navigator.userAgent.toLowerCase();
        var name = navigator.appName;
        // IE old version ( IE 10 or Lower )
        if ( name == "Microsoft Internet Explorer" ) word = "msie ";
        else {
            // IE 11
            if ( agent.search("trident") > -1 ) word = "trident/.*rv:";
            // Microsoft Edge
            else if ( agent.search("edge/") > -1 ) word = "edge/";
        }
        var reg = new RegExp( word + "([0-9]{1,})(\\.{0,}[0-9]{0,1})" );
        if (  reg.exec( agent ) != null  ) version = RegExp.$1 + RegExp.$2;
        return version;
    }


    /**
     * 은행 이미지 정보
     *  bankCd : 두자리수 은행코드
     *  bankCd : 세자리수 은행코드(가상계좌)
     *  bankNm : 은행명
     *  imgSrc : 은행 이미지
     *  sImgSrc: 작은 은행 이미지
     */
    var gfn_bankImgInfo = [
         {"bankCd":"02", "bankCd3":"002"  ,"bankNm":"산업은행", 	"imgSrc":"", 											"sImgSrc":"" }
        ,{"bankCd":"03", "bankCd3":"003"  ,"bankNm":"기업은행", 	"imgSrc":"/resources/app/assets/img/static/bank08.png", "sImgSrc":"/resources/app/assets/img/static/small-bank08.png" }
        ,{"bankCd":"04", "bankCd3":"004"  ,"bankNm":"국민은행", 	"imgSrc":"/resources/app/assets/img/static/bank01.png", "sImgSrc":"/resources/app/assets/img/static/small-bank01.png" }
        ,{"bankCd":"05", "bankCd3":"005"  ,"bankNm":"하나은행", 	"imgSrc":"/resources/app/assets/img/static/bank06.png", "sImgSrc":"/resources/app/assets/img/static/small-bank06.png" }
        ,{"bankCd":"06", "bankCd3":"006"  ,"bankNm":"주택은행", 	"imgSrc":"", 											"sImgSrc":"" }
        ,{"bankCd":"07", "bankCd3":"007"  ,"bankNm":"수협중앙회", 	"imgSrc":"", 											"sImgSrc":"" }
        ,{"bankCd":"11", "bankCd3":"011"  ,"bankNm":"농협은행", 	"imgSrc":"/resources/app/assets/img/static/bank03.png", "sImgSrc":"/resources/app/assets/img/static/small-bank03.png" }
        ,{"bankCd":"12", "bankCd3":"012"  ,"bankNm":"지역농축협", 	"imgSrc":"", 											"sImgSrc":"" }
        ,{"bankCd":"20", "bankCd3":"020"  ,"bankNm":"우리은행", 	"imgSrc":"/resources/app/assets/img/static/bank04.png", "sImgSrc":"/resources/app/assets/img/static/small-bank04.png" }
        ,{"bankCd":"23", "bankCd3":"023"  ,"bankNm":"ＳＣ은행", 	"imgSrc":"/resources/app/assets/img/static/bank05.png", "sImgSrc":"/resources/app/assets/img/static/small-bank05.png" }
        ,{"bankCd":"25", "bankCd3":"025"  ,"bankNm":"서울은행", 	"imgSrc":"", 											"sImgSrc":"" }
        ,{"bankCd":"27", "bankCd3":"027"  ,"bankNm":"한국씨티은행", "imgSrc":"/resources/app/assets/img/static/bank11.png","sImgSrc":"/resources/app/assets/img/static/small-bank11.png" }
        ,{"bankCd":"31", "bankCd3":"031"  ,"bankNm":"대구은행", 	"imgSrc":"/resources/app/assets/img/static/bank12.png", "sImgSrc":"/resources/app/assets/img/static/small-bank12.png" }
        ,{"bankCd":"32", "bankCd3":"032"  ,"bankNm":"부산은행", 	"imgSrc":"/resources/app/assets/img/static/bank09.png", "sImgSrc":"/resources/app/assets/img/static/small-bank09.png" }
        ,{"bankCd":"34", "bankCd3":"034"  ,"bankNm":"광주은행", 	"imgSrc":"", 											"sImgSrc":"" }
        ,{"bankCd":"35", "bankCd3":"035"  ,"bankNm":"제주은행", 	"imgSrc":"", 											"sImgSrc":"" }
        ,{"bankCd":"37", "bankCd3":"037"  ,"bankNm":"전북은행", 	"imgSrc":"", 											"sImgSrc":"" }
        ,{"bankCd":"39", "bankCd3":"039"  ,"bankNm":"경남은행", 	"imgSrc":"/resources/app/assets/img/static/bank10.png", "sImgSrc":"/resources/app/assets/img/static/small-bank10.png" }
        ,{"bankCd":"45", "bankCd3":"045"  ,"bankNm":"새마을금고", 	"imgSrc":"", 											"sImgSrc":"" }
        ,{"bankCd":"48", "bankCd3":"048"  ,"bankNm":"신협중앙회", 	"imgSrc":"", 											"sImgSrc":"" }
        ,{"bankCd":"50", "bankCd3":"050"  ,"bankNm":"상호신용은행", "imgSrc":"", 											"sImgSrc":"" }
        ,{"bankCd":"57", "bankCd3":"057"  ,"bankNm":"제이피모간체", "imgSrc":"", 											"sImgSrc":"" }
        ,{"bankCd":"64", "bankCd3":"064"  ,"bankNm":"산림조합중앙", "imgSrc":"", 											"sImgSrc":"" }
        ,{"bankCd":"71", "bankCd3":"071"  ,"bankNm":"우체국", 		"imgSrc":"", 											"sImgSrc":"" }
        ,{"bankCd":"81", "bankCd3":"081"  ,"bankNm":"하나은행", 	"imgSrc":"/resources/app/assets/img/static/bank06.png", "sImgSrc":"/resources/app/assets/img/static/small-bank06.png" }
        ,{"bankCd":"88", "bankCd3":"088"  ,"bankNm":"신한은행", 	"imgSrc":"/resources/app/assets/img/static/bank07.png", "sImgSrc":"/resources/app/assets/img/static/small-bank07.png" }
    ];
    /*은행코드 이미지 조회
     * bankCd 은행코드 (2자리, 3자리 모두 가능)
     * imgOpt 이미지 옵션(기본갑 기본 이미지, 1:작은 이미지)
     */
    function gfn_getBankImgUrl(bankCd, imgOpt){
        for(var i=0;i<gfn_bankImgInfo.length;i++){
            if(gfn_bankImgInfo[i].bankCd == bankCd || gfn_bankImgInfo[i].bankCd3 == bankCd){
                if(imgOpt == "1"){
                    return ksosAppInfo.contextPath + gfn_bankImgInfo[i].sImgSrc;
                }else{
                    return ksosAppInfo.contextPath + gfn_bankImgInfo[i].imgSrc;
                }
            }
        }
        return "";
    }


    function gfn_addContextPath(contextPath,url){
    	return url.indexOf("http") > -1?url:contextPath + url;
    }
    // 사업자 등록번호를 3-2-5 형태로 리턴
    function gfn_maskBzopRgsnNo(vParam){
		try{
			if( gfn_isNull(vParam)){
				return "";
			}
			
			
			var strBzopRgsnNo = vParam.replace(/\D/g, "");	// 숫자만 저장
			if(strBzopRgsnNo.length != 10){
				return strBzopRgsnNo;
			}
			var strBzopRgsnNo1 = "";
			var strBzopRgsnNo2 = "";
			var strBzopRgsnNo3 = "";
			
			strBzopRgsnNo1 = strBzopRgsnNo.substring(0,3);
			strBzopRgsnNo2 = strBzopRgsnNo.substring(3,5)
			strBzopRgsnNo3 = strBzopRgsnNo.substring(5);

			return strBzopRgsnNo1 + "-" + strBzopRgsnNo2 + "-" + strBzopRgsnNo3;
		}catch(e){
			console.log(e);
			return vParam;
		}
    }        
    // 카드번호 포멧 셋팅 4-4-4-4
    function gfn_maskCreditCardNo(vParam){
		try{
			if( gfn_isNull(vParam)){
				return "";
			}
			
			
			var cardNo = vParam.replace(/\D/g, "");	// 숫자만 저장
			if(cardNo.length != 16){
				return cardNo;
			}
			var cardNo1 = "";
			var cardNo2 = "";
			var cardNo3 = "";
			var cardNo4 = "";
			
			cardNo1 = cardNo.substring(0,4);
			cardNo2 = cardNo.substring(4,8)
			cardNo3 = cardNo.substring(8,12);
			cardNo4 = cardNo.substring(12);

			return cardNo1 + "-" + cardNo2 + "-" + cardNo3 + "-" + cardNo4;
		}catch(e){
			console.log(e);
			return vParam;
		}
	}        
    // 주문번호를 4-7-3 형태로 리턴
    function gfn_maskKwOrdrNo(vParam){
		try{
			var rtnVal = "";
			if( gfn_isNull(vParam)){
				return "";
			}
			
			
			var ordrNo = vParam.replace(/\D/g, "");	// 숫자만 저장
			if(ordrNo.length < 4){
				return ordrNo;
			}
			var ordrNo1 = "";
			var ordrNo2 = "";
			var ordrNo3 = "";
			
			ordrNo1 = ordrNo.substring(0,4);
			ordrNo2 = gfn_lpad(ordrNo.substring(4,11),7)
			
			rtnVal = ordrNo1 + "-" + ordrNo2;
			if(ordrNo.length > 11){
				rtnVal = rtnVal + "-" + ordrNo3;
			}

			return rtnVal;
		}catch(e){
			console.log(e);
			return vParam;
		}
    }
    
    /**
     * 스페이스가 2개 이상인 문자를
     * 캐리지 리턴함
     * @param orgMsg 원본 메시지
     * @return 캐리지 리턴된 메세지
     */
    function gfn_space2CarriageMessage(orgMsg){
    	
  	  var regExp=/(\s){2,}/g;    	  
	  var msg=orgMsg.replace(regExp, '\n');
	  return msg;
    }
    
    /**
     * 주말인 검사한다.
     * 입력형식 : YYYYMMDD (sample : 2017년 2월 10일 --> 20170210 )
     * 주말이면 true 반환
     * @param string
     * @return boolean
     */
    function gfn_isWeekend(vDt) {
        var yyyy = parseInt( vDt.substring( 0, 4 ), 10 );
        var mm  = ( parseInt( vDt.substring( 4, 6 ), 10 ) - 1 );
        var dd  = parseInt( vDt.substring( 6, 8 ), 10 );
        var date = new Date( yyyy, mm, dd ); //Date 개체를 만듭니다.
    
        // 토요일, 일요일인 경우는 true반환
        if ( date.getDay() == 6 || date.getDay() == 0 ) {
            return true;
        } else {
            return false;
        }
    }
    
    /**
     * 음력을 양력으로 바꾸어서 반환한다.
     *
     * 입력형식 : YYYYMMDD (sample : 2017년 2월 10일 --> 20170210 )
     * 입력된 날자의 양력 날자를 리턴
     */
    function gfn_lunarToSolar (vDt) {
        var kk = [[1, 2, 4, 1, 1, 2, 1, 2, 1, 2, 2, 1],   /* 1841 */
                  [2, 2, 1, 2, 1, 1, 2, 1, 2, 1, 2, 1],
                  [2, 2, 2, 1, 2, 1, 4, 1, 2, 1, 2, 1],
                  [2, 2, 1, 2, 1, 2, 1, 2, 1, 2, 1, 2],
                  [1, 2, 1, 2, 2, 1, 2, 1, 2, 1, 2, 1],
                  [2, 1, 2, 1, 5, 2, 1, 2, 2, 1, 2, 1],
                  [2, 1, 1, 2, 1, 2, 1, 2, 2, 2, 1, 2],
                  [1, 2, 1, 1, 2, 1, 2, 1, 2, 2, 2, 1],
                  [2, 1, 2, 3, 2, 1, 2, 1, 2, 1, 2, 2],
                  [2, 1, 2, 1, 1, 2, 1, 1, 2, 2, 1, 2],
                  [2, 2, 1, 2, 1, 1, 2, 1, 2, 1, 5, 2],   /* 1851 */
                  [2, 1, 2, 2, 1, 1, 2, 1, 2, 1, 1, 2],
                  [2, 1, 2, 2, 1, 2, 1, 2, 1, 2, 1, 2],
                  [1, 2, 1, 2, 1, 2, 5, 2, 1, 2, 1, 2],
                  [1, 1, 2, 1, 2, 2, 1, 2, 2, 1, 2, 1],
                  [2, 1, 1, 2, 1, 2, 1, 2, 2, 2, 1, 2],
                  [1, 2, 1, 1, 5, 2, 1, 2, 1, 2, 2, 2],
                  [1, 2, 1, 1, 2, 1, 1, 2, 2, 1, 2, 2],
                  [2, 1, 2, 1, 1, 2, 1, 1, 2, 1, 2, 2],
                  [2, 1, 6, 1, 1, 2, 1, 1, 2, 1, 2, 2],
                  [1, 2, 2, 1, 2, 1, 2, 1, 2, 1, 1, 2],   /* 1861 */
                  [2, 1, 2, 1, 2, 2, 1, 2, 2, 3, 1, 2],
                  [1, 2, 2, 1, 2, 1, 2, 2, 1, 2, 1, 2],
                  [1, 1, 2, 1, 2, 1, 2, 2, 1, 2, 2, 1],
                  [2, 1, 1, 2, 4, 1, 2, 2, 1, 2, 2, 1],
                  [2, 1, 1, 2, 1, 1, 2, 2, 1, 2, 2, 2],
                  [1, 2, 1, 1, 2, 1, 1, 2, 1, 2, 2, 2],
                  [1, 2, 2, 3, 2, 1, 1, 2, 1, 2, 2, 1],
                  [2, 2, 2, 1, 1, 2, 1, 1, 2, 1, 2, 1],
                  [2, 2, 2, 1, 2, 1, 2, 1, 1, 5, 2, 1],
                  [2, 2, 1, 2, 2, 1, 2, 1, 2, 1, 1, 2],   /* 1871 */
                  [1, 2, 1, 2, 2, 1, 2, 1, 2, 2, 1, 2],
                  [1, 1, 2, 1, 2, 4, 2, 1, 2, 2, 1, 2],
                  [1, 1, 2, 1, 2, 1, 2, 1, 2, 2, 2, 1],
                  [2, 1, 1, 2, 1, 1, 2, 1, 2, 2, 2, 1],
                  [2, 2, 1, 1, 5, 1, 2, 1, 2, 2, 1, 2],
                  [2, 2, 1, 1, 2, 1, 1, 2, 1, 2, 1, 2],
                  [2, 2, 1, 2, 1, 2, 1, 1, 2, 1, 2, 1],
                  [2, 2, 4, 2, 1, 2, 1, 1, 2, 1, 2, 1],
                  [2, 1, 2, 2, 1, 2, 2, 1, 2, 1, 1, 2],
                  [1, 2, 1, 2, 1, 2, 5, 2, 2, 1, 2, 1],   /* 1881 */
                  [1, 2, 1, 2, 1, 2, 1, 2, 2, 1, 2, 2],
                  [1, 1, 2, 1, 1, 2, 1, 2, 2, 2, 1, 2],
                  [2, 1, 1, 2, 3, 2, 1, 2, 2, 1, 2, 2],
                  [2, 1, 1, 2, 1, 1, 2, 1, 2, 1, 2, 2],
                  [2, 1, 2, 1, 2, 1, 1, 2, 1, 2, 1, 2],
                  [2, 2, 1, 5, 2, 1, 1, 2, 1, 2, 1, 2],
                  [2, 1, 2, 2, 1, 2, 1, 1, 2, 1, 2, 1],
                  [2, 1, 2, 2, 1, 2, 1, 2, 1, 2, 1, 2],
                  [1, 5, 2, 1, 2, 2, 1, 2, 1, 2, 1, 2],
                  [1, 2, 1, 2, 1, 2, 1, 2, 2, 1, 2, 2],   /* 1891 */
                  [1, 1, 2, 1, 1, 5, 2, 2, 1, 2, 2, 2],
                  [1, 1, 2, 1, 1, 2, 1, 2, 1, 2, 2, 2],
                  [1, 2, 1, 2, 1, 1, 2, 1, 2, 1, 2, 2],
                  [2, 1, 2, 1, 5, 1, 2, 1, 2, 1, 2, 1],
                  [2, 2, 2, 1, 2, 1, 1, 2, 1, 2, 1, 2],
                  [1, 2, 2, 1, 2, 1, 2, 1, 2, 1, 2, 1],
                  [2, 1, 5, 2, 2, 1, 2, 1, 2, 1, 2, 1],
                  [2, 1, 2, 1, 2, 1, 2, 2, 1, 2, 1, 2],
                  [1, 2, 1, 1, 2, 1, 2, 5, 2, 2, 1, 2],
                  [1, 2, 1, 1, 2, 1, 2, 1, 2, 2, 2, 1],   /* 1901 */
                  [2, 1, 2, 1, 1, 2, 1, 2, 1, 2, 2, 2],
                  [1, 2, 1, 2, 3, 2, 1, 1, 2, 2, 1, 2],
                  [2, 2, 1, 2, 1, 1, 2, 1, 1, 2, 2, 1],
                  [2, 2, 1, 2, 2, 1, 1, 2, 1, 2, 1, 2],
                  [1, 2, 2, 4, 1, 2, 1, 2, 1, 2, 1, 2],
                  [1, 2, 1, 2, 1, 2, 2, 1, 2, 1, 2, 1],
                  [2, 1, 1, 2, 2, 1, 2, 1, 2, 2, 1, 2],
                  [1, 5, 1, 2, 1, 2, 1, 2, 2, 2, 1, 2],
                  [1, 2, 1, 1, 2, 1, 2, 1, 2, 2, 2, 1],
                  [2, 1, 2, 1, 1, 5, 1, 2, 2, 1, 2, 2],   /* 1911 */
                  [2, 1, 2, 1, 1, 2, 1, 1, 2, 2, 1, 2],
                  [2, 2, 1, 2, 1, 1, 2, 1, 1, 2, 1, 2],
                  [2, 2, 1, 2, 5, 1, 2, 1, 2, 1, 1, 2],
                  [2, 1, 2, 2, 1, 2, 1, 2, 1, 2, 1, 2],
                  [1, 2, 1, 2, 1, 2, 2, 1, 2, 1, 2, 1],
                  [2, 3, 2, 1, 2, 2, 1, 2, 2, 1, 2, 1],
                  [2, 1, 1, 2, 1, 2, 1, 2, 2, 2, 1, 2],
                  [1, 2, 1, 1, 2, 1, 5, 2, 2, 1, 2, 2],
                  [1, 2, 1, 1, 2, 1, 1, 2, 2, 1, 2, 2],
                  [2, 1, 2, 1, 1, 2, 1, 1, 2, 1, 2, 2],   /* 1921 */
                  [2, 1, 2, 2, 3, 2, 1, 1, 2, 1, 2, 2],
                  [1, 2, 2, 1, 2, 1, 2, 1, 2, 1, 1, 2],
                  [2, 1, 2, 1, 2, 2, 1, 2, 1, 2, 1, 1],
                  [2, 1, 2, 5, 2, 1, 2, 2, 1, 2, 1, 2],
                  [1, 1, 2, 1, 2, 1, 2, 2, 1, 2, 2, 1],
                  [2, 1, 1, 2, 1, 2, 1, 2, 2, 1, 2, 2],
                  [1, 5, 1, 2, 1, 1, 2, 2, 1, 2, 2, 2],
                  [1, 2, 1, 1, 2, 1, 1, 2, 1, 2, 2, 2],
                  [1, 2, 2, 1, 1, 5, 1, 2, 1, 2, 2, 1],
                  [2, 2, 2, 1, 1, 2, 1, 1, 2, 1, 2, 1],   /* 1931 */
                  [2, 2, 2, 1, 2, 1, 2, 1, 1, 2, 1, 2],
                  [1, 2, 2, 1, 6, 1, 2, 1, 2, 1, 1, 2],
                  [1, 2, 1, 2, 2, 1, 2, 2, 1, 2, 1, 2],
                  [1, 1, 2, 1, 2, 1, 2, 2, 1, 2, 2, 1],
                  [2, 1, 4, 1, 2, 1, 2, 1, 2, 2, 2, 1],
                  [2, 1, 1, 2, 1, 1, 2, 1, 2, 2, 2, 1],
                  [2, 2, 1, 1, 2, 1, 4, 1, 2, 2, 1, 2],
                  [2, 2, 1, 1, 2, 1, 1, 2, 1, 2, 1, 2],
                  [2, 2, 1, 2, 1, 2, 1, 1, 2, 1, 2, 1],
                  [2, 2, 1, 2, 2, 4, 1, 1, 2, 1, 2, 1],   /* 1941 */
                  [2, 1, 2, 2, 1, 2, 2, 1, 2, 1, 1, 2],
                  [1, 2, 1, 2, 1, 2, 2, 1, 2, 2, 1, 2],
                  [1, 1, 2, 4, 1, 2, 1, 2, 2, 1, 2, 2],
                  [1, 1, 2, 1, 1, 2, 1, 2, 2, 2, 1, 2],
                  [2, 1, 1, 2, 1, 1, 2, 1, 2, 2, 1, 2],
                  [2, 5, 1, 2, 1, 1, 2, 1, 2, 1, 2, 2],
                  [2, 1, 2, 1, 2, 1, 1, 2, 1, 2, 1, 2],
                  [2, 2, 1, 2, 1, 2, 3, 2, 1, 2, 1, 2],
                  [2, 1, 2, 2, 1, 2, 1, 1, 2, 1, 2, 1],
                  [2, 1, 2, 2, 1, 2, 1, 2, 1, 2, 1, 2],   /* 1951 */
                  [1, 2, 1, 2, 4, 2, 1, 2, 1, 2, 1, 2],
                  [1, 2, 1, 1, 2, 2, 1, 2, 2, 1, 2, 2],
                  [1, 1, 2, 1, 1, 2, 1, 2, 2, 1, 2, 2],
                  [2, 1, 4, 1, 1, 2, 1, 2, 1, 2, 2, 2],
                  [1, 2, 1, 2, 1, 1, 2, 1, 2, 1, 2, 2],
                  [2, 1, 2, 1, 2, 1, 1, 5, 2, 1, 2, 2],
                  [1, 2, 2, 1, 2, 1, 1, 2, 1, 2, 1, 2],
                  [1, 2, 2, 1, 2, 1, 2, 1, 2, 1, 2, 1],
                  [2, 1, 2, 1, 2, 5, 2, 1, 2, 1, 2, 1],
                  [2, 1, 2, 1, 2, 1, 2, 2, 1, 2, 1, 2],   /* 1961 */
                  [1, 2, 1, 1, 2, 1, 2, 2, 1, 2, 2, 1],
                  [2, 1, 2, 3, 2, 1, 2, 1, 2, 2, 2, 1],
                  [2, 1, 2, 1, 1, 2, 1, 2, 1, 2, 2, 2],
                  [1, 2, 1, 2, 1, 1, 2, 1, 1, 2, 2, 1],
                  [2, 2, 5, 2, 1, 1, 2, 1, 1, 2, 2, 1],
                  [2, 2, 1, 2, 2, 1, 1, 2, 1, 2, 1, 2],
                  [1, 2, 2, 1, 2, 1, 5, 2, 1, 2, 1, 2],
                  [1, 2, 1, 2, 1, 2, 2, 1, 2, 1, 2, 1],
                  [2, 1, 1, 2, 2, 1, 2, 1, 2, 2, 1, 2],
                  [1, 2, 1, 1, 5, 2, 1, 2, 2, 2, 1, 2],   /* 1971 */
                  [1, 2, 1, 1, 2, 1, 2, 1, 2, 2, 2, 1],
                  [2, 1, 2, 1, 1, 2, 1, 1, 2, 2, 2, 1],
                  [2, 2, 1, 5, 1, 2, 1, 1, 2, 2, 1, 2],
                  [2, 2, 1, 2, 1, 1, 2, 1, 1, 2, 1, 2],
                  [2, 2, 1, 2, 1, 2, 1, 5, 2, 1, 1, 2],
                  [2, 1, 2, 2, 1, 2, 1, 2, 1, 2, 1, 1],
                  [2, 2, 1, 2, 1, 2, 2, 1, 2, 1, 2, 1],
                  [2, 1, 1, 2, 1, 6, 1, 2, 2, 1, 2, 1],
                  [2, 1, 1, 2, 1, 2, 1, 2, 2, 1, 2, 2],
                  [1, 2, 1, 1, 2, 1, 1, 2, 2, 1, 2, 2],   /* 1981 */
                  [2, 1, 2, 3, 2, 1, 1, 2, 2, 1, 2, 2],
                  [2, 1, 2, 1, 1, 2, 1, 1, 2, 1, 2, 2],
                  [2, 1, 2, 2, 1, 1, 2, 1, 1, 5, 2, 2],
                  [1, 2, 2, 1, 2, 1, 2, 1, 1, 2, 1, 2],
                  [1, 2, 2, 1, 2, 2, 1, 2, 1, 2, 1, 1],
                  [2, 1, 2, 2, 1, 5, 2, 2, 1, 2, 1, 2],
                  [1, 1, 2, 1, 2, 1, 2, 2, 1, 2, 2, 1],
                  [2, 1, 1, 2, 1, 2, 1, 2, 2, 1, 2, 2],
                  [1, 2, 1, 1, 5, 1, 2, 1, 2, 2, 2, 2],
                  [1, 2, 1, 1, 2, 1, 1, 2, 1, 2, 2, 2],   /* 1991 */
                  [1, 2, 2, 1, 1, 2, 1, 1, 2, 1, 2, 2],
                  [1, 2, 5, 2, 1, 2, 1, 1, 2, 1, 2, 1],
                  [2, 2, 2, 1, 2, 1, 2, 1, 1, 2, 1, 2],
                  [1, 2, 2, 1, 2, 2, 1, 5, 2, 1, 1, 2],
                  [1, 2, 1, 2, 2, 1, 2, 1, 2, 2, 1, 2],
                  [1, 1, 2, 1, 2, 1, 2, 2, 1, 2, 2, 1],
                  [2, 1, 1, 2, 3, 2, 2, 1, 2, 2, 2, 1],
                  [2, 1, 1, 2, 1, 1, 2, 1, 2, 2, 2, 1],
                  [2, 2, 1, 1, 2, 1, 1, 2, 1, 2, 2, 1],
                  [2, 2, 2, 3, 2, 1, 1, 2, 1, 2, 1, 2],   /* 2001 */
                  [2, 2, 1, 2, 1, 2, 1, 1, 2, 1, 2, 1],
                  [2, 2, 1, 2, 2, 1, 2, 1, 1, 2, 1, 2],
                  [1, 5, 2, 2, 1, 2, 1, 2, 2, 1, 1, 2],
                  [1, 2, 1, 2, 1, 2, 2, 1, 2, 2, 1, 2],
                  [1, 1, 2, 1, 2, 1, 5, 2, 2, 1, 2, 2],
                  [1, 1, 2, 1, 1, 2, 1, 2, 2, 2, 1, 2],
                  [2, 1, 1, 2, 1, 1, 2, 1, 2, 2, 1, 2],
                  [2, 2, 1, 1, 5, 1, 2, 1, 2, 1, 2, 2],
                  [2, 1, 2, 1, 2, 1, 1, 2, 1, 2, 1, 2],
                  [2, 1, 2, 2, 1, 2, 1, 1, 2, 1, 2, 1],   /* 2011 */
                  [2, 1, 6, 2, 1, 2, 1, 1, 2, 1, 2, 1],
                  [2, 1, 2, 2, 1, 2, 1, 2, 1, 2, 1, 2],
                  [1, 2, 1, 2, 1, 2, 1, 2, 5, 2, 1, 2],
                  [1, 2, 1, 1, 2, 1, 2, 2, 2, 1, 2, 2],
                  [1, 1, 2, 1, 1, 2, 1, 2, 2, 1, 2, 2],
                  [2, 1, 1, 2, 3, 2, 1, 2, 1, 2, 2, 2],
                  [1, 2, 1, 2, 1, 1, 2, 1, 2, 1, 2, 2],
                  [2, 1, 2, 1, 2, 1, 1, 2, 1, 2, 1, 2],
                  [2, 1, 2, 5, 2, 1, 1, 2, 1, 2, 1, 2],
                  [1, 2, 2, 1, 2, 1, 2, 1, 2, 1, 2, 1],   /* 2021 */
                  [2, 1, 2, 1, 2, 2, 1, 2, 1, 2, 1, 2],
                  [1, 5, 2, 1, 2, 1, 2, 2, 1, 2, 1, 2],
                  [1, 2, 1, 1, 2, 1, 2, 2, 1, 2, 2, 1],
                  [2, 1, 2, 1, 1, 5, 2, 1, 2, 2, 2, 1],
                  [2, 1, 2, 1, 1, 2, 1, 2, 1, 2, 2, 2],
                  [1, 2, 1, 2, 1, 1, 2, 1, 1, 2, 2, 2],
                  [1, 2, 2, 1, 5, 1, 2, 1, 1, 2, 2, 1],
                  [2, 2, 1, 2, 2, 1, 1, 2, 1, 1, 2, 2],
                  [1, 2, 1, 2, 2, 1, 2, 1, 2, 1, 2, 1],
                  [2, 1, 5, 2, 1, 2, 2, 1, 2, 1, 2, 1],   /* 2031 */
                  [2, 1, 1, 2, 1, 2, 2, 1, 2, 2, 1, 2],
                  [1, 2, 1, 1, 2, 1, 5, 2, 2, 2, 1, 2],
                  [1, 2, 1, 1, 2, 1, 2, 1, 2, 2, 2, 1],
                  [2, 1, 2, 1, 1, 2, 1, 1, 2, 2, 1, 2],
                  [2, 2, 1, 2, 1, 4, 1, 1, 2, 1, 2, 2],
                  [2, 2, 1, 2, 1, 1, 2, 1, 1, 2, 1, 2],
                  [2, 2, 1, 2, 1, 2, 1, 2, 1, 1, 2, 1],
                  [2, 2, 1, 2, 5, 2, 1, 2, 1, 2, 1, 1],
                  [2, 1, 2, 2, 1, 2, 2, 1, 2, 1, 2, 1],
                  [2, 1, 1, 2, 1, 2, 2, 1, 2, 2, 1, 2],   /* 2041 */
                  [1, 5, 1, 2, 1, 2, 1, 2, 2, 2, 1, 2],
                  [1, 2, 1, 1, 2, 1, 1, 2, 2, 1, 2, 2]];

        var gan = new Array("甲","乙","丙","丁","戊","己","庚","辛","壬","癸");
        var jee = new Array("子","丑","寅","卯","辰","巳","午","未","申","酉","戌","亥");
        var ddi = new Array("쥐","소","범","토끼","용","뱀","말","양","원숭이","닭","개","돼지");
        var week = new Array("일","월","화","수","목","금","토");
        
        var md = new Array(31,0,31,30,31,30,31,31,30,31,30,31);
        
        var lyear =vDt.substring(0,4);
        var lmonth =vDt.substring(4,6);
        var lday =vDt.substring(6,8);
        
        // 음력에서 양력으로 변환
        var leapyes;
        var syear, smonth, sday;
        var mm, y1, y2, m1;
        var i, j, k1, k2, leap, w;
        var td, y;
        y1 = lyear - 1841;
        m1 = lmonth - 1;
        leapyes = 0;
        if (kk[y1][m1] > 2)  {
            if (document.frmTest.yoon[0].checked) {
                leapyes = 1;
                switch (kk[y1][m1]) {
                    case 3 :
                    case 5 : mm = 29; break;
                    case 4 :
                    case 6 : mm = 30; break;
                }
            } else {
                switch (kk[y1][m1]) {
                    case 1:
                    case 3:
                    case 4 : mm = 29; break;
                    case 2:
                    case 5:
                    case 6 : mm = 30; break;
                } // end of switch
            } // end of if
        } // end of if
        
        td = 0;
        for (i=0; i<y1; i++) {
            for (j=0; j<12; j++) {
                switch (kk[i][j]) {
                    case 1: td = td + 29; break;
                    case 2: td = td + 30; break;
                    case 3: td = td + 58; break;
                    case 4: td = td + 59; break;
                    case 5: td = td + 59; break;
                    case 6: td = td + 60; break;
                } // end of switch
            } // end of for
        } // end of for

        for (j=0; j<m1; j++) {
            switch (kk[y1][j]) {
                case 1: td = td + 29; break;
                case 2: td = td + 30; break;
                case 3: td = td + 58; break;
                case 4: td = td + 59; break;
                case 5: td = td + 59; break;
                case 6: td = td + 60; break;
            } // end of switch
        } // end of for

        if (leapyes == 1) {
            switch(kk[y1][m1]) {
                case 3:
                case 4: td = td + 29; break;
                case 5:
                case 6: td = td + 30; break;
            } // end of switch
        } // end of switch

        td =  td + parseFloat(lday) + 22;
      // td : 1841 년 1 월 1 일 부터 원하는 날짜까지의 전체 날수의 합
        y1 = 1840;
        do {
            y1 = y1 +1;
            if  ((y1 % 400 == 0) || ((y1 % 100 != 0) && (y1 % 4 == 0))) { y2 = 366; }
            else { y2 = 365; }
            if (td <= y2) { break; }
            else { td = td- y2; }
        } while(1); // end do-While

        syear = y1;
        md[1] = parseInt(y2) -337;
        m1 = 0;
        do {
            m1= m1 + 1;
            if (td <= md[m1-1]) { break; }
            else { td = td - md[m1-1]; }
        } while(1); // end of do-While

        smonth = parseInt(m1);
        sday = parseInt(td);

        // 월이 한자리인경우에는 앞에 0을 붙혀서 반환
        if ( smonth < 10 ) {
            smonth = "0" + smonth;
        }
        // 일이 한자리인경우에는 앞에 0을 붙혀서 반환
        if ( sday < 10 ) {
            sday = "0" + sday;
        }

        return new String( syear + smonth + sday );
    }
    
    /**
     * 입력한 날짜가 법정 공휴일인지 검사를 한다.
     * 공휴일인 경우에는 true 리턴
     * 입력형식 : 2004년 3월 1일 --> 20040301
     *
     * @param string
     */
    function gfn_isStatHoliday(vDt) {
        // 검사년도
        var yyyy = vDt.substring( 0, 4 );
        var holidays = new Array();

        // 음력 공휴일을 양력으로 바꾸어서 입력
        var tmp01 = gfn_lunarToSolar( yyyy + "0101" );// 음력설날
        var tmp02 = gfn_lunarToSolar( yyyy + "0815" );// 음력추석
        holidays[0] = tmp01 - 1; // 음력설 첫째날
        holidays[1] = tmp01;   // 음력설 둘째날
        holidays[2] = tmp01 + 1; // 음력설 셋째날
        holidays[3] = tmp02 - 1; // 추석 첫째날
        holidays[4] = tmp02;   // 추석 둘째날
        holidays[5] = tmp02 + 1; // 추석 셋째날 
        holidays[6] = gfn_lunarToSolar( yyyy + "0408" ); // 석가탄신일

        // 양력 공휴일 입력
        holidays[7] = yyyy + "0101";  // 양력설날
        holidays[8] = yyyy + "0301";  // 삼일절
        //holidays[9] = yyyy + "0405";  // 식목일 // 공휴일제외
        holidays[9] = yyyy + "0505";  // 어린이날
        holidays[10] = yyyy + "0606";  // 현충일
        holidays[11] = yyyy + "0717";  // 제헌절
        holidays[12] = yyyy + "0815";  // 광복절
        holidays[13] = yyyy + "1003";  // 개천절
        holidays[14] = yyyy + "1225";  // 성탄절

        for ( var i=0; i<holidays.length ; i++ ) {
            if ( holidays[i] == vDt ) {
                return true ;
            }
        }
    }
    
    /**
     * 입력한 날짜가 토요일, 일요일, 공휴일인지 검사를 한다.
     * 입력형식 : 2004년 3월 1일 --> 20040301
     * 주말 및 공유일이면 true 
     * @param string
     */
    function gfn_isHoliday(vDt) {
        if ( gfn_isStatHoliday(vDt) || gfn_isWeekend(vDt) ) {
            return true;
        } else {
            return false;
        }
    }
    
    
    /**
     * JSON을 CVS파일로 Export (한글처리 OK)
     * @param iFileName   - CSV 파일명
     * @param iJsonData   - JsonData
     * @param bUseHead    - Head 출력여부
     * @param sDelimiter  - Delimiter (default comma)
     * @param sQuotes     - 텍스트 자료 Quotation
     */
    function gfn_exportCSV(iFileName, iJsonData, bUseHead, sDelimiter, sQuotes) {
    	var useHead = false;
    	var delimiter = ',';
    	var quotes = '"';
    	var fileName = 'exportCSV.csv';
    	
    	if (useHead != undefined && useHead == true) { useHead = true;}
    	if (sDelimiter != undefined) { delimiter = sDelimiter;}
    	if (sQuotes != undefined) { quotes = sQuotes;}
    	if (iFileName != undefined && iFileName != "") { fileName = iFileName;}
    	
        var csvContent = arrayToCsv(iJsonData, useHead, null, delimiter, quotes);

        var blob = new Blob( ['\uFEFF' + csvContent], {type: "text/csv;charset=UTF-8;"});
        
        gfnDownloadLinkCsv.setAttribute('download', fileName);
        gfnDownloadLinkCsv.setAttribute('href', window.URL.createObjectURL(blob));
        gfnDownloadLinkCsv.click();

    }
    
    /**
     * JSON을 CVS파일로 Export (한글처리 OK)
     * @param iFileName   - CSV 파일명
     * @param iJsonData   - JsonData
     * @param bUseHead    - Head 출력여부
     * @param sDelimiter  - Delimiter (default comma)
     * @param sQuotes     - 텍스트 자료 Quotation
     */
    function gfn_exportCSVNoBOM(iFileName, iJsonData, bUseHead, sDelimiter, sQuotes) {
    	var useHead = false;
    	var delimiter = ',';
    	var quotes = '"';
    	var fileName = 'exportCSV.csv';
    	
    	if (useHead != undefined && useHead == true) { useHead = true;}
    	if (sDelimiter != undefined) { delimiter = sDelimiter;}
    	if (sQuotes != undefined) { quotes = sQuotes;}
    	if (iFileName != undefined && iFileName != "") { fileName = iFileName;}
    	
        var csvContent = arrayToCsv2(iJsonData, useHead, null, delimiter, quotes);

        var blob = new Blob( [csvContent], {type: "text/csv;charset=UTF-8;"});
        
        gfnDownloadLinkCsv.setAttribute('download', fileName);
        gfnDownloadLinkCsv.setAttribute('href', window.URL.createObjectURL(blob));
        gfnDownloadLinkCsv.click();

    }

    /**
     * 'gfn_calcDayMonthCount' 펑션 월단위 계산시 윤달 오류 해결
     * @param ymd1 - 시작일자
     * @param ymd2 - 종료일자
     * @returns {Number}
     */
    function gfn_calcMonthCount(ymd1,ymd2){
    	if(ymd1.length < 6 || ymd2.length < 6){//6자리이상 입력필수
    		return;
    	}
    	var minusGubnVal = 1;
    	if(ymd1 > ymd2){//값 교체 
    		var ymd1_t = ymd1; 
    		var ymd2_t = ymd2;
    		ymd1 = ymd2_t;
    		ymd2 = ymd1_t;
    		minusGubnVal = -1;
    	}
    	var srcYmd1 = ymd1.substring(0,6)
    	var srcYmd2 = ymd2.substring(0,6)
    	var calcVal = srcYmd2 - srcYmd1;
    	var gapMonth = 0;
    	if(calcVal >= 100){//년단위 계산
    		gapMonth = 12 * parseInt(calcVal/100); 
    	}
    	if(calcVal%100 < 12){//월단위 계산
    		gapMonth += calcVal%100;
    	}else{
    		gapMonth += 12 - (100 - calcVal%100); 
    	}
    	
    	return gapMonth * minusGubnVal;
    }
    
    /**
     * 'gfn_getDeptArrKum' 구몬의 조직코드 array
     * 2개지구 지소의 가상 지국을 제거하고 정보를 return
     */
    function gfn_getDeptArrKum(onlyBusn, arrDeptsStr) {
//    	var arrDepts = $rootScope.loginInfo.upDepts.split(',');
    	var arrDepts = arrDeptsStr.split(',');
//    	console.log(arrDepts);
    	var re = new RegExp("[A-Z]");

    	var concatStr = "";
    	for (var i = 1; i < arrDepts.length; i++) {
    		if (re.exec(arrDepts[i]) != undefined) {
    			if (re.exec(arrDepts[i]) != null && re.exec(arrDepts[i +1 ]) != null) { // 사업장 소속만 추출
    				if ((arrDepts[i].substring(0,3) == arrDepts[i+1].substring(0,3))
    					&& (arrDepts[i].substring(3,4) != '0' && arrDepts[i+1].substring(3,4) != '0')
    				    && (arrDepts[i].substring(4,5) == '0' && arrDepts[i+1].substring(4,5) == '0')
    						) {
    					// console.log('skip');
    				} else {
    					if (concatStr == '') {
    						concatStr = arrDepts[i];
    					} else {
    						concatStr = concatStr + ',' + arrDepts[i];
    					}
    				}
    			} else {
    				if (concatStr == '') {
    					concatStr = arrDepts[i];
    				} else {
    					concatStr = concatStr + ',' + arrDepts[i];
    				}
    			}
    		} else {
    			if (onlyBusn != 'Y') {
    				if (concatStr == '') {
    					concatStr = arrDepts[i];
    				} else {
    					concatStr = concatStr + ',' + arrDepts[i];
    				}
    			}
    		}
    	}

    	return concatStr.split(',');
     }
    
    /**
     * 'gfn_ssnCheck' 주민번호 유효성 검사
     */
    function gfn_ssnCheck(_ssn1, _ssn2)
    {
        var ssn1    = _ssn1,
            ssn2    = _ssn2,
            ssn     = ssn1+''+ssn2,
            arr_ssn = [],
            compare = [2,3,4,5,6,7,8,9,2,3,4,5],
            sum     = 0;
     
        // 입력여부 체크
        if (ssn1 == '')
        {
            alert('주민등록번호를 기입해주세요.');
            return false;
        }
     
        if (ssn2 == '')
        {
            alert('주민등록번호를 기입해주세요.');
            return false;
        }    
     
        // 입력값 체크
        if (ssn1.match('[^0-9]'))
        {
            alert("주민등록번호는 숫자만 입력하셔야 합니다."); 
            return false; 
        }
        if (ssn2.match('[^0-9]'))
        {
            alert("주민등록번호는 숫자만 입력하셔야 합니다."); 
            return false; 
        }
     
        // 자리수 체크
        if (ssn.length != 13)
        {
            alert("올바른 주민등록 번호를 입력하여 주세요.");return false;
        }    
     
     
        // 공식: M = (11 - ((2×A + 3×B + 4×C + 5×D + 6×E + 7×F + 8×G + 9×H + 2×I + 3×J + 4×K + 5×L) % 11)) % 11
        for (var i = 0; i<13; i++) 
        { 
            arr_ssn[i] = ssn.substring(i,i+1); 
        }
         
        for (var i = 0; i<12; i++)
        {
            sum = sum + (arr_ssn[i] * compare[i]); 
        }
     
        sum = (11 - (sum % 11)) % 10;
         
        if (sum != arr_ssn[12])
        { 
            alert("올바른 주민등록 번호를 입력하여 주세요.");
            return false; 
        }
     
        return true;
    }
    
    /**
     * 생년월일에 대한 나이 값 가저오기
     */
    function gfn_age(brdt){
    	var byr=parseInt(brdt.substring(0,4));
    	var age;
    	var now = new Date();
    	var tyr=(now.getFullYear()); 
   
   	    age=byr-1;
    
    	return tyr-age; 
    }
    
	/* 
	IE 7.0 이상, 크롬, 파이어폭스
	*/
	function gfn_getServerTime() {
		var xhr = new XMLHttpRequest();
		//헤더 정보만 받기 위해 HEAD방식으로 요청.
		//현재 화면 url 임.
		var thisUrl = window.location.href.toString();
		xhr.open('HEAD', thisUrl , false); // window.location.href.toString() or Target URL
		xhr.setRequestHeader("Content-Type", "text/html");
		xhr.send('');
		var serverDate = xhr.getResponseHeader("Date");
		var date = new Date(serverDate);
		return date;


	}
	
	

	function gfn_exlDecrypt(Upload,files,readYN,callBackFunc){
		
		// 엑셀 암호화 해제 모듈
		/*
		 * 파라미터 정보
		 * Upload (고정)
		 * files (암호화 해제 요청 엑셀 파일)
		 * read (엑셀데이터값 받을지 여부 Y,N)  Y이면 결과값resultList를 받을수 있음
		 * callBackFunc (콜백 함수)
		 * */
		var urlStr = ksosAppInfo.contextPath + '/api/ksscomn/gnrl/getExcelData';  
	    //var urlStr = ksosAppInfo.contextPath + '/api/comn/wells/comn/getExcelData' ;
		Upload.upload({
	        url: urlStr,
	        file: files,
	        data: {readYN : readYN},
	        method: 'POST'
	    })
		.success(function(result) {
			//commDecryptRslt(result);
			//console.log("typeof callBackFunc : "+typeof callBackFunc);
			if( typeof callBackFunc == 'function' ) {
				callBackFunc(result);
				
			}
		});
	}

	
	Date.prototype.format = function (f) {

		if (!this.valueOf()) return " ";
		var weekKorName = ["일요일", "월요일", "화요일", "수요일", "목요일", "금요일", "토요일"];
		var weekKorShortName = ["일", "월", "화", "수", "목", "금", "토"];
		var weekEngName = ["Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday"];
		var weekEngShortName = ["Sun", "Mon", "Tue", "Wed", "Thu", "Fri", "Sat"];
		var d = this;
		return f.replace(/(yyyy|yy|MM|dd|KS|KL|ES|EL|HH|hh|mm|ss|a\/p)/gi, function ($1) {
			switch ($1) {
				case "yyyy": return d.getFullYear(); // 년 (4자리)
				case "yy": return (d.getFullYear() % 1000).zf(2); // 년 (2자리)
				case "MM": return (d.getMonth() + 1).zf(2); // 월 (2자리)
				case "dd": return d.getDate().zf(2); // 일 (2자리)
				case "KS": return weekKorShortName[d.getDay()]; // 요일 (짧은 한글)
				case "KL": return weekKorName[d.getDay()]; // 요일 (긴 한글)
				case "ES": return weekEngShortName[d.getDay()]; // 요일 (짧은 영어)
				case "EL": return weekEngName[d.getDay()]; // 요일 (긴 영어)
				case "HH": return d.getHours().zf(2); // 시간 (24시간 기준, 2자리)
				case "hh": return ((h = d.getHours() % 12) ? h : 12).zf(2); // 시간 (12시간 기준, 2자리)
				case "mm": return d.getMinutes().zf(2); // 분 (2자리)
				case "ss": return d.getSeconds().zf(2); // 초 (2자리)
				case "a/p": return d.getHours() < 12 ? "오전" : "오후"; // 오전/오후 구분
				default: return $1;
			}
		});
	};
String.prototype.string = function (len) { var s = '', i = 0; while (i++ < len) { s += this; } return s; };
String.prototype.zf = function (len) { return "0".string(len - this.length) + this; };
Number.prototype.zf = function (len) { return this.toString().zf(len); };


    
}

