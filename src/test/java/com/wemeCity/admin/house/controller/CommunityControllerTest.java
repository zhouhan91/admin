package com.wemeCity.admin.house.controller;

import org.junit.Test;

import com.wemeCity.common.controller.BaseControllerTest;

public class CommunityControllerTest extends BaseControllerTest {

	@Test
	public void testList() throws Exception {
		testRestFul("/community/list/1", "get");
	}
}
