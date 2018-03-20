package com.wemeCity.admin.news.service.impl;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.wemeCity.admin.news.dto.BabietaSaveDTO;
import com.wemeCity.admin.news.exception.NewsException;
import com.wemeCity.admin.news.mapper.NewsMapper;
import com.wemeCity.admin.news.model.News;
import com.wemeCity.admin.news.model.NewsBabieta;
import com.wemeCity.admin.news.service.NewsBabietaService;
import com.wemeCity.admin.news.service.NewsService;
import com.wemeCity.admin.news.utils.NewsConstants;
import com.wemeCity.admin.sysUser.model.SysUser;
import com.wemeCity.common.utils.Constants;
import com.wemeCity.common.utils.GsonUtils;

/**
 * NewsServiceImpl AppService类
 *
 * @author 向小文
 * @since JDK1.8
 * @history 2017-11-18 新建
 */
@Service
public class NewsServiceImpl implements NewsService {

	@Autowired
	private NewsBabietaService newsBabietaService;

	private Logger logger = LoggerFactory.getLogger(NewsServiceImpl.class);

	/** 数据访问接口 */
	@Autowired
	private NewsMapper newsMapper;

	/**
	 * 新增news
	 *
	 * @param news
	 * @return 新增的对象
	 * @author 向小文 2017-11-18 新建
	 */
	@Override
	public void insertNews(News news) throws NewsException {
		try {
			newsMapper.insertNews(news);
		} catch (Exception e) {
			logger.error("新增News时报错", e);
			throw new NewsException("新增News时报错", e);
		}
	}

	/**
	 * 删除news
	 *
	 * @param id 主键
	 * @return
	 * @author 向小文 2017-11-18 新建
	 */
	@Override
	public int deleteNews(long id) throws NewsException {
		try {
			return this.newsMapper.deleteNews(id);
		} catch (Exception e) {
			logger.error("删除News时报错", e);
			throw new NewsException("删除News时报错", e);
		}
	}

	/**
	 * 修改news
	 *
	 * @param condition
	 * @return
	 * @author 向小文 2017-11-18 新建
	 */
	@Override
	public int updateNews(Map<String, Object> condition) throws NewsException {
		try {
			return this.newsMapper.updateNews(condition);
		} catch (Exception e) {
			logger.error("修改News时报错", e);
			throw new NewsException("修改News时报错", e);
		}
	}

	/**
	 * 查询news
	 *
	 * @param id 主键
	 * @return 根据主键查找的内容
	 * @author 向小文 2017-11-18 新建
	 */
	@Override
	public News readNews(long id) throws NewsException {
		try {
			return this.newsMapper.readNews(id);
		} catch (Exception e) {
			logger.error("查询News时报错", e);
			throw new NewsException("查询News时报错", e);
		}
	}

	/**
	 * 查询news集合
	 *
	 * @param condition 查询条件
	 * @return 符合查询条件的记录
	 * @author 向小文 2017-11-18 新建
	 */
	@Override
	public List<News> queryNewsList(Map<String, Object> condition) {
		try {
			return this.newsMapper.queryNewsList(condition);
		} catch (Exception e) {
			logger.error("查询News时报错", e);
			return null;
		}
	}

	/**
	 * 查询news集合
	 *
	 * @param condition 查询条件
	 * @return 符合查询条件的记录数
	 * @author 向小文 2017-11-18 新建
	 */
	@Override
	public long queryNewsCount(Map<String, Object> condition) throws NewsException {
		try {
			return this.newsMapper.queryNewsCount(condition);
		} catch (Exception e) {
			logger.error("查询News时报错", e);
			throw new NewsException("查询News时报错", e);
		}
	}

	@Override
	public News saveBabieta(BabietaSaveDTO saveDTO, SysUser user) throws NewsException {
		try {
			News news = null;
			if (saveDTO.getId() > 0) {
				news = newsMapper.readNews(saveDTO.getId());
				Map<String, Object> condition = new HashMap<>();
				condition.put("id", news.getId());
				condition.put("coverPicture", saveDTO.getCoverPicture());
				condition.put("title", saveDTO.getTitle());
				condition.put("modifyBy", user.getId());
				condition.put("modifyTime", LocalDateTime.now());
				newsMapper.updateNews(condition);
			} else {
				news = new News();
				news.setTitle(saveDTO.getTitle());
				news.setCoverPicture(saveDTO.getCoverPicture());
				news.setNavigationCode("babieta");
				news.setNavigationName("巴别塔");
				news.setSubNavigationCode("babieta");
				news.setSubNavigationName("巴别塔");
				news.setCreateBy(user.getId());
				news.setCreateTime(LocalDateTime.now());
				news.setStatus(NewsConstants.NEWS_STATUS_NEW);
				news.setIsDeleted(Constants.NO);
				newsMapper.insertNews(news);
			}
			List<NewsBabieta> lstBabieta = saveDTO.getLstBabieta();
			if (!CollectionUtils.isEmpty(lstBabieta)) {
				Map<String, Object> babietaCondition = new HashMap<>();
				for (NewsBabieta babieta : lstBabieta) {
					if (babieta.getId() <= 0) {
						// 新增
						babieta.setCreateBy(user.getId());
						babieta.setCreateTime(LocalDateTime.now());
						babieta.setIsDeleted(Constants.NO);
						babieta.setNewsId(news.getId());
						newsBabietaService.insertNewsBabieta(babieta);
					} else {
						babietaCondition.put("id", babieta.getId());
						babietaCondition.put("content", babieta.getContent());
						babietaCondition.put("modifyBy", user.getId());
						babietaCondition.put("modifyTime", LocalDateTime.now());
						newsBabietaService.updateNewsBabieta(babietaCondition);
					}
				}
			}
			return news;
		} catch (Exception e) {
			logger.error("保存巴别塔出错：服务器内部错误！saveDTO={}", GsonUtils.toSimpleJson(saveDTO), e);
			throw new NewsException("保存巴别塔出错：服务器内部错误！", e);
		}
	}

}