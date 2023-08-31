    // var akdcde = "<%=request.getAttribute("akdcde")%>";
    console.log("akdcde : " + akdcde);
	// var profile = "<%=request.getAttribute("profileGubn")%>"
	// profile = profile != null?profile:"";
    // let home = 'http://10.1.64.71:8311';
    // let home = 'http://kstagent.kyowon.co.kr:8311';
    let home = 'https://kstagent.kyowon.co.kr:4311';
    let URL = home + "/kkosync/v1/kakao_oauth";
    let URL_POPUP = home + "/kkosync/v1/kakao_oauth_popup";
    let URL_QR = home + "/sync.html";

    Kakao.init('f700e57981e83536bd690379a8e5da6c');

    function kakaoLoginHandlerP() {
        Kakao.Auth.login({
            success: function (response) {
                let data = {
                    token: response.access_token,
                    employee_id: new URLSearchParams(location.search).get("akdcde")
                };
                let xhr = new XMLHttpRequest();
                xhr.onload = function () {
                    if (xhr.status === 200 || xhr.status === 201) {
                        location.href = xhr.responseText;
                    } else {
                        console.error(xhr.responseText);
                    }
                };
                xhr.open('POST', URL_POPUP);
                xhr.setRequestHeader('Content-Type', 'application/json;charset=UTF-8');
                xhr.send(JSON.stringify(data));
            },
            fail: function (error) {
                console.error(error);
            }
        });
    }

    // 리다이렉트 방식
    function kakaoLoginHandlerR() {
        // 싱크 동의창에서 취소시 되돌아올 현재 로그인창 url
        let originUrlEncoded = encodeURIComponent(`${location.protocol}//${location.hostname}${location.port ? ':' + location.port : ''}/kyowon.html`);
        let state = JSON.stringify({originUrl: originUrlEncoded})
        Kakao.Auth.authorize({
            redirectUri: URL,   // 싱크 에이전트 URL
            state: state
        })
    }

    $(document).ready(function() {
    	profile = profile!=null?profile:"";

    	const params = new URLSearchParams(location.search);
    	const employee_id = params.get("akdcde");
        let url = URL_QR + "?employee_id=" + employee_id;
        let urlEncoded = encodeURIComponent(url);
        let qr = "https://chart.googleapis.com/chart?cht=qr&chs=400x400&chl="+urlEncoded;
        $(".URLResult").text("생성된 url : " + url);
        $( ".QRResult" ).attr("src", qr);

      	//검증
    	if(typeof(akdcde)=="undefined" || akdcde.length < 6){
    		alert("판매자 번호 오류 입니다.");
    		return;
    	}
    });

	//로그인 페이지이동 (사용X)
    function goLoginInpt(){
    	var callUrl = "${pageContext.request.contextPath}/smplJoin/loginInpt";
    	var sendData = {};
    	gfn_goUrl(callUrl,sendData,'_self','GET');
    }

    //카카오싱크 페이지이동
    function goKakaoSync(){
    	//location.href="http://52.78.212.157:9100/sync.html?employee_id="+akdcde;
    	if(typeof(akdcde)=="undefined" || akdcde.length!=7){
    		console.log(akdcde);
    		console.log(akdcde.length);
    		console.log(typeof(akdcde));
    		alert("영업부사번 없음");
    		return;
    	}else{
    		kakaoLoginHandlerP();
    	}
    }

    //회원가입 페이지이동 (사용X)
    function goJoinAgree(){
    	var callUrl = "${pageContext.request.contextPath}/smplJoin/joinAgree";
    	var sendData = {akdcde : akdcde}
    	gfn_goUrl(callUrl,sendData,'_self','GET');
    }
