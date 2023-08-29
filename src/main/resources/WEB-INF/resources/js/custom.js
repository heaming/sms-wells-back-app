$(document).ready(function(){

/******** Menu Show Hide Sub Menu ********/
$('#menu > ul > li').mouseover(function() {
			$screensize = $(window).width();
			if ($screensize > 801) {
			$(this).find('> div').css('display', 'block');
			}
			$(this).bind('mouseleave', function() {
				$screensize = $(window).width();
			if ($screensize > 801) {
				$(this).find('> div').css('display', 'none');
			}
		});
			});
/******** Navigation Menu **********/
$('#menu > span').click(function () {
	  $(this).toggleClass("active");
	  $(this).parent().find("> ul").slideToggle('medium');
	});

/******** Accordion **********/
$('.accordion-heading, .checkout-heading').on('click', function() {
	$('.accordion-content, .checkout-content').slideUp('slow');
	$(this).parent().find('.accordion-content, .checkout-content').slideDown('slow');
});

/******** Footer Link **********/
$(".column h3").click(function () {
			$screensize = $(window).width();
			if ($screensize < 801) {
				$(this).toggleClass("active");
				$(this).parent().find("ul").slideToggle('medium');
			}
		});

/******** Span wrap in Title Heading **********/
$('.box-heading').wrapInner('<span></span>')

/******** Colorbox **********/

//		$('.colorbox').colorbox({
//			overlayClose: true,
//			opacity: 0.8,
//			maxHeight: 550,
//			maxWidth: 550,
//			width:'100%'
//		});

/******** Tabs **********/
//$('#tabs a').tabs();

/******** plus mines button in qty **********/
$(".qtyBtn").click(function(){
		if($(this).hasClass("plus")){
			var qty = $("#qty").val();
			qty++;
			$("#qty").val(qty);
		}else{
			var qty = $("#qty").val();
			qty--;
			if(qty>0){
				$("#qty").val(qty);
			}
		}
	});

/******** Ajax Cart **********/
	$('#cart > .heading a').on('click', function() {
		$('#cart').addClass('active');
		$('#cart').on('mouseleave', function() {
			$(this).removeClass('active');
		});
	});

/******** Mega Menu **********/
	$('#menu ul > li > a + div').each(function(index, element) {
		// IE6 & IE7 Fixes
		//if ($.browser.msie && ($.browser.version == 7 || $.browser.version == 6)) {
		if ($("body").hasClass("ie6") || $("body").hasClass("ie7")) {
			var category = $(element).find('a');
			var columns = $(element).find('ul').length;

			$(element).css('width', (columns * 143) + 'px');
			$(element).find('ul').css('float', 'left');
		}

		var menu = $('#menu').offset();
		var dropdown = $(this).parent().offset();

		i = (dropdown.left + $(this).outerWidth()) - (menu.left + $('#menu').outerWidth());

		if (i > 0) {
			$(this).css('margin-left', '-' + (i + 5) + 'px');
		}
	});

/********Category Accordion **********/
$(document).ready(function() {
	$('#custom_accordion').customAccordion({
		classExpand : 'cid0',
		menuClose: false,
		autoClose: true,
		saveState: false,
		disableLink: false,
		autoExpand: true
	});
});

/******** Carousel **********/
//$('#carousel ul').jcarousel({
//	vertical: false,
//	visible: 5,
//	scroll: 3
//});

/******** Panel CloseBtn **********/
$('.close-btn').on("click",function (e) {
		e.preventDefault();
		var _this = $(this);

		_this.find('> span').toggleClass("active");

		_this.parents(".panel").find("> div").filter(function(index){
			    return index !== 0 ;
		}).stop().toggle();

	});


/******** Radio TabBtn **********/
$('.tabCont > div').each(function(index, element) {
		$('.radioTabBtn'+index).on("click",function(e){
			$('.tabCont > div').hide();
			$('.tab'+index).show();
		});

	});


/******** TabBtn **********/
$('.tabG > button').each(function(index, element) {
		$('.tabBtn'+index).on("click",function(e){
			$('.tabConts').hide();
			$('.tabCon'+index).show();
			$('.tabG > button').removeClass('active');
			$('.tabBtn'+index).addClass('active');

		});

	});


});