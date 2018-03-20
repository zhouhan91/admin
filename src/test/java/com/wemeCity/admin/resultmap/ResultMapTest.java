package com.wemeCity.admin.resultmap;

import com.wemeCity.admin.catering.dto.CateringOrderDTO;
import com.wemeCity.admin.catering.mapper.CateringOrderMapper;
import com.wemeCity.admin.catering.model.CateringOrder;
import com.wemeCity.common.controller.BaseControllerTest;
import junit.framework.TestCase;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ResultMapTest extends TestCase{

	SqlSessionFactory sqlSessionFactory;

	@Override
	protected void setUp() throws Exception {
		super.setUp();
		String resource = "mybatis-config.xml";
		InputStream inputStream = Resources.getResourceAsStream(resource);
		sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
	}

	@Test
	public void findOrderById() throws Exception {
		SqlSession openSession = sqlSessionFactory.openSession();
		CateringOrderMapper mapper = openSession.getMapper(CateringOrderMapper.class);
		Map<String,Object> condition = new HashMap<>();

		List<CateringOrderDTO> queryCateringOrderDTOList = mapper.queryCateringOrderDTOList(condition);

		for(int i=0; i<queryCateringOrderDTOList.size(); i++){
			System.out.println(queryCateringOrderDTOList.get(i));
		}

		openSession.close();
	}
}
