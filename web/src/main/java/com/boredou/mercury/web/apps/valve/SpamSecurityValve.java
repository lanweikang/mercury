package com.boredou.mercury.web.apps.valve;

import static com.alibaba.citrus.turbine.util.TurbineUtil.getTurbineRunData;
import java.util.Set;
import javax.servlet.http.HttpServletRequest;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import com.alibaba.citrus.service.pipeline.PipelineContext;
import com.alibaba.citrus.service.pipeline.support.AbstractValve;
import com.alibaba.citrus.turbine.TurbineRunData;
import com.boredou.mercury.repository.entity.User;

/**
 * webx权限过滤
 * 
 * CopyRright (c) 2014:   <般豆网络>
 * Project:     <mercury-web>
 * File Name:   <SpamSecurityValve.java>
 * Module ID:   <(模块)类编号，可以引用系统设计中的类编号>
 * Comments:  <对此类的描述，可以引用系统设计中的描述>
 * JDK version used:      <JDK1.6> 
 * @author wsh(王树辉) [wsh.ck@qq.com]
 * @since Date： 2014-3-26 上午9:43:33
 */
public class SpamSecurityValve extends AbstractValve {

	@Autowired
	private HttpServletRequest request;

	/**
	 * 跳出流程控制的特殊target,直接进入invokeNext()
	 */
	private Set<String> brokeTargetSet;

	@Override
	public void invoke(PipelineContext pipelineContext) throws Exception {
		TurbineRunData turbineRunData = getTurbineRunData(request);
		String target = turbineRunData.getTarget().trim();
		System.out.println("------------>进入webx权限过滤...");

		if (target.contains(".do")) {
			pipelineContext.invokeNext();
			return;
		}
		if (target.contains("RechargeAjax")) {
			pipelineContext.invokeNext();
			return;
		}

		if (brokeTargetSet.contains(target)) {
			pipelineContext.invokeNext();
			return;
		}

		// 验证是否登录
		if (!SecurityUtils.getSubject().isAuthenticated()) {
				System.out.println("未登录...");
				turbineRunData.setRedirectLocation("/login.htm");
			return;
		}
		
		pipelineContext.invokeNext();

	}

	public void setBrokeTargetSet(Set<String> brokeTargetSet) {
		this.brokeTargetSet = brokeTargetSet;
	}
}
