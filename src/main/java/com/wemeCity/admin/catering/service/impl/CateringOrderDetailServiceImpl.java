package com.wemeCity.admin.catering.service.impl;/**
 * @history create in 2018/3/12
 * @author Neal
 * @since JDK1.8
 */

import com.wemeCity.admin.catering.mapper.CateringOrderDetailMapper;
import com.wemeCity.admin.catering.model.CateringOrderDetail;
import com.wemeCity.admin.catering.service.CateringOrderDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @author zhouhan
 * @create 2018-03-12 20:43
 * @desc
 **/
@Service
public class CateringOrderDetailServiceImpl implements CateringOrderDetailService {

    @Autowired
    private CateringOrderDetailMapper cateringOrderDetailMapper;

    @Override
    public List<CateringOrderDetail> queryCateringOrderList(Map<String,Object> condition) {
        List<CateringOrderDetail> cateringOrderDetailList = cateringOrderDetailMapper.queryCateringOrderDetailList(condition);
        return cateringOrderDetailList;
    }
}
