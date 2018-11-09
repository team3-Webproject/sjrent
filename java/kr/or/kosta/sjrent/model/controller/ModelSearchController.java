package kr.or.kosta.sjrent.model.controller;

import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import kr.or.kosta.sjrent.common.controller.Controller;
import kr.or.kosta.sjrent.common.controller.ModelAndView;
import kr.or.kosta.sjrent.common.factory.XMLObjectFactory;
import kr.or.kosta.sjrent.model.domain.Model;
import kr.or.kosta.sjrent.model.params.ModelParams;
import kr.or.kosta.sjrent.model.service.ModelService;
import kr.or.kosta.sjrent.model.service.ModelServiceImpl;

/**
 * 검색된 모델 목록을 뷰로 보내는 컨트롤러
 * 
 * @author 유예겸
 *
 */
public class ModelSearchController implements Controller {
	private ModelService modelService;
	private ModelAndView mav;
	private XMLObjectFactory factory;
	private JSONArray jsonArray;

	@Override
	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response)
			throws ServletException {
		mav = new ModelAndView();
		factory = (XMLObjectFactory) request.getServletContext().getAttribute("objectFactory");
		modelService = (ModelService) factory.getBean(ModelServiceImpl.class);
		jsonArray = new JSONArray();
		
		String startDate = request.getParameter("rent_start_date");
		String endDate = request.getParameter("rent_end_date");
		String type = request.getParameter("model_type");
		if(startDate == null) {
			startDate="2018-11-12";
			endDate="2018-11-12";
			type="all";
		}
		// 인자로 받은 date와 type
		if (type.equals("all"))
			type = null;


		// 검색 인자를 Params에 저장
		ModelParams modelParams = new ModelParams();
		modelParams.setStartDate(startDate);
		modelParams.setEndDate(startDate);
		modelParams.setType(type);

		List<Model> list = null;

		try {
			// Params로 검색한 리스트를 list에 저장
			list = modelService.listBySearch(modelParams);
			for (Model model : list) {
				JSONObject modelObject = new JSONObject();
				modelObject.put("name", model.getName());
				modelObject.put("fuelType", model.getFuelType());
				modelObject.put("fuelEfficiency", model.getFuelEfficiency());
				modelObject.put("seater", model.getSeater());
				modelObject.put("transmission", model.getTransmission());
				modelObject.put("navigation", model.getNavigation());
				modelObject.put("cameraRear", model.getCameraRear());
				modelObject.put("year", model.getYear());
				modelObject.put("highpass", model.getHighpass());
				modelObject.put("blackBox", model.getBlackBox());
				modelObject.put("options", model.getOption());
				modelObject.put("picture", model.getPicture());
				modelObject.put("type", model.getType());
				modelObject.put("weekdayPrice", model.getWeekdayPrice());
				modelObject.put("weekendPrice", model.getWeekendPrice());
				modelObject.put("evalScore", model.getEvalScore());
				modelObject.put("rentalCount", model.getRentalCount());
				jsonArray.add(modelObject);
			}

			response.setCharacterEncoding("utf-8");
			response.getWriter().print(jsonArray);
			return null;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return mav;
	}

}
