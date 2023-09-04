
/**
 * 가격을 받아서 맞는 포맷으로 리턴
 */
function fn_getPrice(price){
    if(price == undefined)
        return '0';
    else
        return gfn_isNumber(price)?gfn_number_format(price):price;  //상품 가격이 문자로 들어올 수 있음..숫자일경우 숫자 포맷 콤마(,) 찍어줌.
}

 $(window).load(function() {
     $("body > div.loading").addClass("hide");
 });
