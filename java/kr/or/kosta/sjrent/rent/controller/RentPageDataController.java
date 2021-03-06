package kr.or.kosta.sjrent.rent.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.kosta.sjrent.common.controller.Controller;
import kr.or.kosta.sjrent.common.controller.ModelAndView;
import kr.or.kosta.sjrent.common.factory.XMLObjectFactory;
import kr.or.kosta.sjrent.insurance.domain.Insurance;
import kr.or.kosta.sjrent.insurance.service.InsuranceService;
import kr.or.kosta.sjrent.insurance.service.InsuranceServiceImpl;
import kr.or.kosta.sjrent.user.domain.User;
import kr.or.kosta.sjrent.user.service.UserService;
import kr.or.kosta.sjrent.user.service.UserServiceImpl;

/**
 * 대여를 위한 정보와 유저정보를 예약 페이지로 넘기는 컨트롤러
 * @author 유예겸. 남수현
 *
 */
public class RentPageDataController implements Controller{
	// 컨트롤러 사용을 위한 객체 선언
	private XMLObjectFactory factory;
	private UserService userService;
	private InsuranceService insuranceService;
	private ModelAndView mav;

   @Override
   public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response)
         throws ServletException {
	   // 컨트롤러 사용을 위한 객체 생성
	  factory = (XMLObjectFactory) request.getServletContext().getAttribute("objectFactory");
      userService = (UserService) factory.getBean(UserServiceImpl.class);
      insuranceService = (InsuranceService) factory.getBean(InsuranceServiceImpl.class);
      mav = new ModelAndView();
      List<Map<String,String>> dataMapList = new ArrayList<Map<String,String>>();
      User user = null;
      try {
    	  //로그인 된 유저에 대한 유저 정보 읽기
    	  user = userService.read((String)request.getAttribute("loginId")); 
      }
      catch (Exception e) {
         mav.addObject("result", "fail");
         mav.setView("/rent/search.jsp");
         return mav;
      }
      // 필요한 값 수신(다중 수신)
      String[] checked = request.getParameterValues("checked");
      String[] startDate = request.getParameterValues("startDate");
      String[] endDate = request.getParameterValues("endDate");
      String[] pickupPlace = request.getParameterValues("pickupPlace");
      String[] amountMoney = request.getParameterValues("amountMoney");
      String[] type = request.getParameterValues("type");
      String[] picture = request.getParameterValues("picture");
      Map<String, String> checkedModelName = new HashMap<String,String>();
      if(checked!=null) {
    	  for(int i = 0; i < checked.length; i++) {
    		  // checkbox 체크 된 모델 이름 리스트 받기
    		  if(checked[i]!=null) {
    			  checkedModelName.put(checked[i], checked[i]);
    		  }
    	  }
          for(int i = 0; i < startDate.length; i++) {
        	  // 체크 박스 된 모델 네임과 비교하여 해당하는 값만 결제 정보 송신을 위한 처리
        	  if(!checkedModelName.containsKey(picture[i].substring(0, picture[i].indexOf("."))))continue;
        	  Map<String, String> temp = new HashMap<String,String>();
        	  temp.put("startDate", startDate[i]);
        	  temp.put("endDate", endDate[i]);
        	  if(pickupPlace == null) temp.put("pickupPlace", "방문수령");
        	  else temp.put("pickupPlace", pickupPlace[i]);
        	  temp.put("amountMoney", amountMoney[i]);
        	  temp.put("type", type[i]);
        	  temp.put("picture", picture[i]);
        	  dataMapList.add(temp);
          }
      }else {
    	  //체크 정보가 전혀 넘어오지 않았을 때 전부 처리(논리상 하나)
          for(int i = 0; i < startDate.length; i++) {
        	  Map<String, String> temp = new HashMap<String,String>();
        	  temp.put("startDate", startDate[i]);
        	  temp.put("endDate", endDate[i]);
        	  if(pickupPlace == null) temp.put("pickupPlace", "방문수령");
        	  else temp.put("pickupPlace", pickupPlace[i]);
        	  temp.put("amountMoney", amountMoney[i]);
        	  temp.put("type", type[i]);
        	  temp.put("picture", picture[i]);
        	  dataMapList.add(temp);
          }
      }

      List<Insurance> insuranceList = new ArrayList<Insurance>();
      try {
		insuranceList = insuranceService.listAll();
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
      // 오브젝트 담아서 보내기
      mav.addObject("insuranceList", insuranceList);
      mav.addObject("resultMap", dataMapList);
      mav.addObject("userName", user.getName());
      mav.addObject("userCellphone", user.getCellphone());
      mav.addObject("userPoint", user.getPoint());
      mav.addObject("userEmail", user.getEmail());
      mav.setView("/rent/rent.jsp");
      return mav;
   }
}
