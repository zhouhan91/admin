package com.wemeCity.common.service.impl;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.wemeCity.admin.community.service.FacilitiesService;
import com.wemeCity.components.dictionary.service.DictionaryService;

@Component
public class CacheResourceIninter {

	@Autowired
	private DictionaryService dictionaryService;
	
	@Autowired
	private FacilitiesService facilitiesService;

	@PostConstruct
	public void init() {
		// 加载字典项
		dictionaryService.initDictionary();
		// 加载房源设施
		facilitiesService.initFacilitiesCache();
		// 加载国家、城市
		
	}

}
