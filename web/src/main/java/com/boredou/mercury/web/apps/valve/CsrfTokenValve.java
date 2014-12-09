package com.boredou.mercury.web.apps.valve;

import static com.alibaba.citrus.turbine.util.TurbineUtil.getTurbineRunData;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.citrus.service.pipeline.PipelineContext;
import com.alibaba.citrus.service.pipeline.support.AbstractValve;
import com.alibaba.citrus.turbine.TurbineRunData;
import com.alibaba.citrus.turbine.util.CsrfToken;
import com.alibaba.fastjson.JSONArray;

/**
 * CsrfTokenValve
 * CopyRright (c) 2014:   <般豆网络>
 * Project:     <mercury-web>
 * File Name:   <CsrfTokenValve.java>
 * Module ID:   <(模块)类编号，可以引用系统设计中的类编号>
 * Comments:  <对此类的描述，可以引用系统设计中的描述>
 * JDK version used:      <JDK1.6> 
 * @author wsh(王树辉) [wsh.ck@qq.com]
 * @since Date： 2014-3-26 上午9:36:11
 */
public class CsrfTokenValve extends AbstractValve {

	@Autowired
	private HttpServletRequest request;

	@Autowired
	private HttpServletResponse response;

	@Override
	public void invoke(PipelineContext pipelineContext) throws Exception {
		TurbineRunData turbineRunData = getTurbineRunData(request);
		String target = turbineRunData.getTarget();

		// 如果不是JSON请求，那么直接跳到下一个阀门
		if (!target.trim().startsWith("/json/")) {
			pipelineContext.invokeNext();
			return;
		}

		if (!CsrfToken.check(request)) {
			response.setContentType("application/json");
			response.getWriter().write(JSONArray.toJSONString("抱歉，你提交的数据已经过期，请刷新页面重新提交"));
			response.getWriter().close();
			return;
		}

		pipelineContext.invokeNext();

	}

}
