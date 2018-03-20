package com.wemeCity.admin.catering.dto;


import com.wemeCity.admin.catering.model.CateringOrder;

import java.time.LocalDateTime;

public class CateringOrderDTO extends CateringOrder{

	/** 订单对应餐馆名 */
	private String restaurantName;

	/** 查询日期起点 */
	private LocalDateTime dateStart;

	/** 查询日期终点 */
	private  LocalDateTime dateEnd;

	public String getRestaurantName() {
		return restaurantName;
	}

	public void setRestaurantName(String restaurantName) {
		this.restaurantName = restaurantName;
	}

	public LocalDateTime getDateStart() {
		return dateStart;
	}

	public void setDateStart(LocalDateTime dateStart) {
		this.dateStart = dateStart;
	}

	public LocalDateTime getDateEnd() {
		return dateEnd;
	}

	public void setDateEnd(LocalDateTime dateEnd) {
		this.dateEnd = dateEnd;
	}
}
