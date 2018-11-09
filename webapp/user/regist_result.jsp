<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="context" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
   <jsp:include page="../common/commoncss.jsp" />
</head>
<body class="tg-home tg-homevone">

<!--************************************
			Nav 시작
	*************************************-->
<jsp:include page="../include/nav.jsp" />
<!--************************************
			Nav 종료
	*************************************-->

<!--************************************
			Wrapper 시작
	*************************************-->
<div id="tg-wrapper" class="tg-wrapper tg-haslayout">

<!--************************************
		Header 시작
*************************************-->
<jsp:include page="../include/header.jsp" />
<!--************************************
		Header 종료
*************************************-->

<!--************************************
		Main 시작
*************************************-->
<main id="tg-main" class="tg-main tg-sectionspace tg-haslayout tg-bglight">
	<div class="container">
		<div class="row">
			<div class="tg-homebannerslider tg-homebannerslider tg-haslayout">
				<div class="tg-homeslider tg-homeslidervtwo tg-haslayout">
					<div class="container">
						<div class="tg-tabcontent tab-content" style="padding: 0px 350px 0;">
							<div role="tabpanel" class="tab-pane active fade in" id="home">
								<div style="text-align: center; margin: 50px 0px;">
									<h2>회원가입 완료</h2>
								</div>

									<div class="form-group">
										<div style="display: inline-block;">
									<h4 style="width: 100px">아이디 : ${id} </h4>
                                    <input type="text" name="id" style="border: none;" maxlength="10">
									</div>
									<div style="display: inline-block">
									<!-- <input type="text" name="id" style="border: none;"
									maxlength="10"> -->
										</div>
									</div>

									<div class="form-group">
										<div style="display: inline-block;">
									<h4 style="width: 100px">이메일 : ${email }</h4>
                                    <input type="text" name="email" style="border: none;" maxlength="10">
									</div>
									<div style="display: inline-block">
									<<!-- input type="text" name="email" style="border: none;"
									maxlength="10"> -->
										</div>
									</div>
									
									<div class="form-group">
										<div style="display: inline-block;">
									<h4 style="width: 100px">휴대폰 : ${cellphone }</h4>
                                    <input type="text" name="cellphone" style="border: none;" maxlength="10">
									</div>
									<div style="display: inline-block">
									<!-- <input type="text" name="phone" style="border: none;"
									maxlength="10"> -->
										</div>
									</div>

								<form class="tg-formtheme tg-formlogin">
									<div style="margin: 50px 0px; text-align: center;">
										<div style="display: inline-block; vertical-align: middle; padding: 0px 20px">
											<button type="button" onclick="location.href='/sjrent/user/login2.jsp' " class="tg-btn tg-btn-lg" style="padding: 0px 30px;">
												<span style="font-size: 14pt; width: 30%">로그인</span>
											</button>
										</div>
										
										<div style="display: inline-block; vertical-align: middle; padding: 0px 20px">
											<button type="button" onclick="location.href='/sjrent/index.jsp' " class="tg-btn tg-btn-lg" style="padding: 0px 30px;">
												<span style="font-size: 14pt; width: 10%">홈으로</span>
											</button>
										</div>
									</div>
								</form>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>
</main>
<!--************************************
		Main 종료
*************************************-->

<!--************************************
			Wrapper 종료
*************************************-->
</div>


	<jsp:include page="../common/commonjs.jsp" />
</body>
</html>