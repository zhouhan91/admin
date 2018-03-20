package com.wemeCity.admin.catering.service;

import com.wemeCity.admin.catering.model.CateringOrderDetail;

import java.util.List;
import java.util.Map;

/**
 * CateringOrderDetailService Service接口
 *
 * @author 周瀚
 * @since JDK1.8
 * @history 周瀚 2018-3-12 新建
 */
public interface CateringOrderDetailService {

	/**
	 * 查询cateringOrderList
	 *
	 * @param condition
	 * @return 新增的对象
	 * @author 周瀚 2018-3-12 新建
	 */
	List<CateringOrderDetail> queryCateringOrderList(Map<String,Object> condition);


}