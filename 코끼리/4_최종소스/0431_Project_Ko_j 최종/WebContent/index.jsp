<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html xmlns="http:
//www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>Koggiri Community</title>

<meta name="viewport" content="width=device-width, initial-scale=1.0">

<!-- CSS
================================================== -->
<link href='http://fonts.googleapis.com/css?family=Oswald' rel='stylesheet' type='text/css'>
<link rel="stylesheet" href="<%= request.getContextPath() %>/css/bootstrap.css">
<link rel="stylesheet" href="<%= request.getContextPath() %>/css/bootstrap-responsive.css">
<link rel="stylesheet" href="<%= request.getContextPath() %>/css/prettyPhoto.css" />
<link rel="stylesheet" href="<%= request.getContextPath() %>/css/flexslider.css" />
<link rel="stylesheet" href="<%= request.getContextPath() %>/css/custom-styles.css">

<!-- Favicons
================================================== -->
<link rel="shortcut icon" href="<%= request.getContextPath() %>/img/titlelogo.ico">
<link rel="apple-touch-icon" href="<%= request.getContextPath() %>/img/titlelogo.ico">
<link rel="apple-touch-icon" sizes="72x72"  href="<%= request.getContextPath() %>/img/titlelogo.ico">
<link rel="apple-touch-icon" sizes="114x114" href="<%= request.getContextPath() %>/img/titlelogo.ico">

<!-- JS
================================================== -->
<script src="http://code.jquery.com/jquery-1.8.3.min.js"></script>
<script src="<%= request.getContextPath() %>/js/bootstrap.js"></script>
<script src="<%= request.getContextPath() %>/js/jquery.prettyPhoto.js"></script>
<script src="<%= request.getContextPath() %>/js/jquery.flexslider.js"></script>
<script src="<%= request.getContextPath() %>/js/jquery.custom.js"></script>


<script type="text/javascript">
$(document).ready(function () {

    $("#btn-blog-next").click(function () {
      $('#blogCarousel').carousel('next')
    });
     $("#btn-blog-prev").click(function () {
      $('#blogCarousel').carousel('prev')
    });

     $("#btn-client-next").click(function () {
      $('#clientCarousel').carousel('next')
    });
     $("#btn-client-prev").click(function () {
      $('#clientCarousel').carousel('prev')
    });
    
});

 $(window).load(function(){

    $('.flexslider').flexslider({
        animation: "slide",
        slideshow: true,
        start: function(slider){
          $('body').removeClass('loading');
        }
    });  
});
</script>

<script type="text/javascript">
//지도 클릭시 비동기 처리하는 ajax
$(function(){	
					$('.Mimage').click(function() {
						var id=$(this).attr('id');
						console.log(id);
							$.ajax({				
								url:"Apart.etc",
								data:{ ApartName : $(this).attr('id')},
								dataType: "text",
								success: function(data){
									$('#apartlist').html(data);
								}				
						});
					});
					
			//아파트 리스트 비동기 검색처리 ajax		
				$('.Mimage').click(function() {
							var id=$(this).attr('id');
							console.log(id);
									$('#searchtext').keyup(function() {
											console.log(id);
											var text=$(this).val();
											console.log(text);
												$.ajax({
														url:"ApartSearch.etc",
														data:{
																SearchString :$(this).val(),
															    ApartName : id
														      },
														dataType: "text",
														success:function(data){
															$('#apartlist').html(data);
														}
												});				
									});
				        }); 
					
    	});
</script>

</head>

<body class="home">
    <!-- Color Bars (above header)-->
	<div class="color-bar-1"></div>
    <div class="color-bar-2 color-bg"></div>    
    <div class="container">    
		      <div class="row header"><!-- Begin Header -->      
			        <!-- Logo
			        ================================================== -->
			        <div class="span5 logo">
			        	<a href="index.jsp"><img src="<%=request.getContextPath()%>/img/piccolo-logo.png" alt="" /></a>
			            <h5>Apartment Community</h5>
			        </div>
			        
			        <!-- Main Navigation
			        ================================================== -->
			        <div class="span7 navigation">
			            <div class="navbar hidden-phone">            
				            <ul class="nav">
					            <li class="dropdown active">
					                <a class="dropdown-toggle" data-toggle="dropdown" href="index.jsp"> &nbsp&nbsp Home &nbsp&nbsp</a>
					            </li>
					            <li><a href="#Map2">&nbsp&nbsp Map &nbsp&nbsp</a></li> 
					            <li><a href="view/index_login.jsp">&nbsp&nbsp Login &nbsp&nbsp </a></li>
				            </ul>           
			            </div>
			        </div>        
		      </div><!-- End Header -->
		     
			    <div class="row headline"><!-- Begin Headline -->    
				     	<!-- Slider Carousel
				        ================================================== -->
				        <div class="span8">
				            <div class="flexslider">
				              <ul class="slides">
				                <li><a href="gallery-single.htm"><img src="<%=request.getContextPath() %>/img/gallery/hi.png" alt="slider" /></a></li>
				                <li><a href="gallery-single.htm"><img src="<%=request.getContextPath() %>/img/gallery/hi2.png" alt="slider" /></a></li>
				              </ul>
				            </div>
				        </div>
			        
			        <!-- Headline Text
			        ================================================== -->
				        <div class="span4">
				        	<h3>Apartment Community<br/>
				            made in Team5</h3>
				            <p class="lead">Our apartment community is very nice.It's quite extraordinary that we've done this kind of UI in a week. </p>
				            <p>Thank you for your continued efforts, and I hope you will do well in the next project.I love team5!</p>
				            <a href="#"><i class="icon-plus-sign"></i>Read More</a> 
				        </div>
			    </div><!-- End Headline -->
		    
		    
		    <div class="row"><!-- Begin Bottom Section -->    
			    	<!-- Map Preview
			        ================================================== -->
			    	<div class="span6.5">
			            <h5 class="title-bg">From the Map 
			                <small>All the Seoul Apartment</small>
			            </h5>
			       		<div id="blogCarousel" class="carousel slide ">
				                 <div class="carousel-inner">		
					                <div class="active item">                 
										<br> <img src="<%= request.getContextPath() %>/img/homeMap/mapSeoul.png"alt="서울시지도" usemap="#Map2" />
					                </div>		
					            </div>
			             </div> 	
			        </div>
			        
			        <!--서울시 지도 클릭  -->
			        <map name="Map2" id="Map2">
							<area shape="poly"coords="385,6,379,20,370,27,375,36,386,49,390,59,380,79,388,93,417,120,427,103,438,109,425,62,431,30,417,20,407,28,403,8"
							 id="도봉구" class="Mimage"/>
							<area shape="poly"
								coords="456,168,431,177,420,187,415,197,405,205,395,214,398,226,414,217,428,228,449,240,457,242,466,222,459,199,451,185"
							 id="동대문구" class="Mimage"/>
							<area shape="poly"
								coords="351,70,336,83,342,92,341,105,348,117,366,127,375,141,378,153,396,155,413,148,425,138,409,115,380,94,376,82,383,60,368,43,353,41,350,45"
							 id="강북구" class="Mimage" />
							<area shape="poly"
								coords="489,34,474,31,467,25,473,16,452,22,437,32,430,58,437,76,441,101,439,113,430,112,422,120,431,139,442,148,455,148,477,140,489,146,499,143,500,132,514,122,500,102,492,103,484,105,486,94,474,85,486,75,489,54,481,44"
							  id="노원구" class="Mimage" />
							<area shape="poly"
								coords="321,117,304,96,303,87,286,79,278,92,252,103,242,117,244,129,234,143,237,167,235,182,226,198,216,197,233,214,239,203,243,193,251,198,254,204,259,197,268,192,271,183,284,175,288,163,293,156,292,147,299,131"
							  id="은평구" class="Mimage" />
							<area shape="poly"
								coords="331,122,344,153,347,173,341,179,348,187,359,190,371,197,385,205,393,207,405,191,422,180,447,164,457,159,437,152,428,145,404,159,382,161,372,152,368,139,354,124,340,116"
							  id="성북구" class="Mimage"/>
							<area shape="poly"
								coords="325,124,304,135,299,148,299,162,306,178,307,194,306,205,303,210,315,222,317,229,329,224,349,226,376,226,390,220,383,211,368,215,369,204,359,199,347,192,335,184,340,172,343,156"
							   id="종로구" class="Mimage" />
							<area shape="poly"
								coords="294,168,284,179,274,185,273,193,266,200,253,209,244,200,237,214,249,226,266,233,271,242,294,243,315,236,298,214,302,196,305,180"
								id="서대문구" class="Mimage" />
							<area shape="poly"
								coords="516,150,507,142,493,153,481,145,466,153,463,168,457,178,463,197,469,218,481,225,493,225,495,213,506,199,510,191,518,184,510,175,518,169"
								id="중량구" class="Mimage"/>
							<area shape="poly"
								coords="204,192,198,206,188,217,174,218,183,233,203,243,227,259,249,275,272,276,284,289,288,282,297,278,306,265,312,256,308,250,277,250,263,246,261,239,231,220"
								id="마포구" class="Mimage"/>
							<area shape="poly"
								coords="375,262,365,261,350,252,317,252,316,242,323,233,331,227,344,231,364,232,385,228,394,234"
								id="중구" class="Mimage"/>
							<area shape="poly"
								coords="438,286,406,275,392,284,384,274,378,262,395,247,398,232,410,225,424,231,444,243,455,248,445,254,446,269"
								id="성동구" class="Mimage"/>
							<area shape="poly"
								coords="509,245,495,240,498,226,490,232,477,226,465,237,453,258,450,273,440,288,451,300,466,301,486,299,504,280,513,253"
								id="광진구" class="Mimage"/>
							<area shape="poly"
								coords="516,242,520,255,513,268,524,275,526,288,521,297,533,300,558,310,561,299,565,286,574,268,583,265,596,268,605,254,594,236,593,221,595,209,586,205,543,229"
								id="강동구" class="Mimage"/>
							<area shape="poly"
								coords="174,221,103,170,99,180,103,189,93,204,79,220,67,238,72,244,55,251,81,262,91,278,116,273,126,274,134,259,145,263,142,274,151,288,162,300,172,293,180,280,178,259,186,257,202,263,211,265,214,257,182,236,171,231"
								id="강서구" class="Mimage"/>
							<area shape="poly"
								coords="135,287,129,298,136,313,131,325,141,327,147,340,166,322,178,328,188,333,197,324,205,300,216,290,207,271,184,263,185,278,181,293,176,299,163,306,153,301,141,283,139,266,135,267,130,277"
								id="양천구" class="Mimage"/>
							<area shape="poly"
								coords="229,360,217,339,223,327,207,314,211,300,222,290,217,273,219,264,229,271,241,278,251,281,271,284,294,310,263,313,258,331,253,341,239,351,233,361"
								id="영등포구" class="Mimage"/>
							<area shape="poly"
								coords="338,321,324,315,315,321,292,300,294,285,303,279,308,270,318,259,328,258,338,257,351,263,362,265,373,271,375,280,387,283,376,294,368,311"
								id="용산구" class="Mimage"/>
							<area shape="poly"
								coords="408,280,383,295,391,317,397,341,404,356,417,367,426,377,434,391,452,389,455,382,470,382,488,397,501,409,513,408,527,396,511,371,500,358,480,349,461,344,453,332,453,311,449,304,426,290"
								id="강남구" class="Mimage"/>
							<area shape="poly"
								coords="512,275,499,296,479,305,462,305,458,325,464,336,486,343,503,351,512,360,518,372,522,381,533,395,543,399,531,381,540,379,552,387,561,378,571,360,577,343,561,339,548,337,548,322,549,317,530,311,517,302,518,290,524,282"
								id="송파구" class="Mimage"/>
							<area shape="poly"
								coords="129,330,118,344,129,355,123,366,129,377,139,378,153,386,160,378,164,369,177,359,182,349,194,357,204,366,214,376,228,377,232,370,218,358,216,344,217,329,204,322,196,334,194,343,174,334,165,330,155,343,145,346,140,344,138,334"
								id="구로구"class="Mimage" />
							<area shape="poly"
								coords="202,368,202,382,209,394,214,410,222,419,227,437,241,439,256,434,263,423,250,413,242,398,244,388,237,379,224,384,205,374"
								id="금천구"class="Mimage"/>
							<area shape="poly"
								coords="340,378,337,356,344,345,332,337,331,326,322,322,312,325,298,313,272,315,262,333,257,347,244,355,238,363,257,361,270,351,286,353,303,354,313,350,313,367,319,377,334,382"
								id="동작구"class="Mimage" />
							<area shape="poly"
								coords="318,428,316,438,309,442,285,443,270,430,264,414,257,411,249,399,249,388,252,379,234,373,239,366,252,366,263,363,270,357,277,357,288,359,296,362,307,357,308,371,318,383,334,386,341,392,349,406,340,412,332,427"
								id="관악구" class="Mimage" />
							<area shape="poly"
								coords="408,397,396,407,382,417,370,404,367,393,352,403,343,383,343,372,342,361,347,351,347,342,340,336,335,328,347,323,366,316,380,302,386,318,396,349,407,368,422,379,430,398,446,398,456,389,467,389,475,391,481,404,487,413,481,424,481,433,469,437,463,439,462,448,458,459,450,458,437,458,428,447,413,446,413,431,424,425,413,417"
								id ="서초구" class="Mimage" />
			    	</map>
				<!-- 서울시 지도 이미지맵 끝 -->
			        
			        <!-- Client Area
			        ================================================== -->
			        <div class="span5">	
				            <h5 class="title-bg">Search Apart
				                <small>into community site</small>
				                <button id="btn-client-next" class="btn btn-inverse btn-mini" type="button">&raquo;</button>
				                <button id="btn-client-prev" class="btn btn-inverse btn-mini" type="button">&laquo;</button>
				            </h5>
				
				            <!-- Client Testimonial Slider-->
				            <div id="clientCarousel" class="carousel slide no-margin">
				            <!-- Carousel items -->
					            <div class="carousel-inner">		               
						            <section>
						                <div class="input-append">
						                    <form action="#">
						                        <input id="searchtext" size="16" type="text" placeholder="Search">
						                        <button class="btn" type="button"><i class="icon-search"></i></button>
						                    </form>
						                </div>
						            </section>			
						            <!--ApartList-->
						            <h5 class="title-bg">ApartList</h5>
						            <ul class="post-category-list" id="apartlist">
						                <li><a href="#Map2" style="font-size:15px"><i class="icon-plus-sign" ></i>우측 본인의 구를 선택해주세요</a></li>                
						            </ul>                
					            </div>
				            </div>           
			        </div> <!-- Client Area end-->			        
		    </div><!-- End Bottom Section -->    
    </div> <!-- End Container -->

    <!-- Footer Area
        ================================================== -->

   <div class="footer-container"><!-- Begin Footer -->
       <div class="container">
           
            <div class="row"><!-- Begin Sub Footer -->
                <div class="span12 footer-col footer-sub">
                    <div class="row no-margin">
                        <div class="span6"><span class="left">Copyright 2017 Koggiri Theme. All rights reserved.</span></div>
                        <div class="span6">
                            <span class="right">
                            <a href="#">Home</a>&nbsp;&nbsp;&nbsp;|&nbsp;&nbsp;&nbsp;
                            <a href="#">Board</a>&nbsp;&nbsp;&nbsp;|&nbsp;&nbsp;&nbsp;
                            <a href="#">Gallery</a>&nbsp;&nbsp;&nbsp;|&nbsp;&nbsp;&nbsp;
                            <a href="#">Calendar</a>&nbsp;&nbsp;&nbsp;|&nbsp;&nbsp;&nbsp;
                            <a href="#">Reservation</a>&nbsp;&nbsp;&nbsp;|&nbsp;&nbsp;&nbsp;
                            <a href="<%= request.getContextPath() %>/MemberList.member">Manager</a>
                            </span>
                        </div>
                    </div>
                </div>
            </div><!-- End Sub Footer -->
         </div>
      </div>
      <!-- End Footer -->     
    <!-- Scroll to Top -->  
    <div id="toTop" class="hidden-phone hidden-tablet">Back to Top</div>
</body>
</html>