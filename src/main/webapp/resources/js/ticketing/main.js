
   $( document ).ready(function() {

	var aa = '{\
	  "boxofs": {\
	    "boxof": [\
	      {\
	        "area": "서울",\
	        "prfdtcnt": 0,\
	        "prfpd": "2014.09.26~2019.02.24",\
	        "cate": "연극",\
	        "prfplcnm": "바탕골소극장",\
	        "prfnm": "극적인 하룻밤",\
	        "rnum": 1,\
	        "seatcnt": 192,\
	        "poster": "/upload/pfmPoster/PF_PF121749_160202_133331.jpg",\
	        "mt20id": "PF121749"\
	      }\
		]\
	  }\
	}\
	';
	
	var bb = JSON.parse(aa);

	$("#cate").text(bb.boxofs.boxof[0].cate);
	$("#prfnm").text(bb.boxofs.boxof[0].prfnm);
	$("#area").text(bb.boxofs.boxof[0].area);
	$("#prfplcnm").text(bb.boxofs.boxof[0].prfplcnm);
	$("#prfpd").text(bb.boxofs.boxof[0].prfpd);
	$("#poster").text(bb.boxofs.boxof[0].poster);

  });

  
$(function() {

  // rome(inline_cal, { time: false });


	rome(inline_cal, {time: false, inputFormat: 'YYYY년 MMMM DD일'}).on('data', function (value) {

	  result.value = value;

	  $("#date").text(result.value);

	});
	
	
	
});



	
  


