package com.wemeCity.admin.news.controller;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.wemeCity.admin.news.dto.BabietaSaveDTO;
import com.wemeCity.admin.news.dto.NewsQueryDTO;
import com.wemeCity.admin.news.model.Navigation;
import com.wemeCity.admin.news.model.News;
import com.wemeCity.admin.news.model.NewsBabieta;
import com.wemeCity.admin.news.model.NewsContent;
import com.wemeCity.admin.news.service.NewsBabietaService;
import com.wemeCity.admin.news.service.NewsContentService;
import com.wemeCity.admin.news.service.NewsService;
import com.wemeCity.admin.news.utils.NewsConstants;
import com.wemeCity.admin.sysUser.model.SysUser;
import com.wemeCity.common.controller.BaseController;
import com.wemeCity.common.dto.BaseDTO;
import com.wemeCity.common.enums.RequestResultEnum;
import com.wemeCity.common.utils.ConditionUtils;
import com.wemeCity.common.utils.Constants;
import com.wemeCity.common.utils.GsonUtils;
import com.wemeCity.common.utils.YamlUtils;

@Controller
@RequestMapping("/news")
public class NewsController extends BaseController {

	@Autowired
	private YamlUtils yamlUtils;

	@Autowired
	private NewsService newsService;

	@Autowired
	private NewsContentService newsContentService;

	@Autowired
	private NewsBabietaService newsBabietaService;

	@SuppressWarnings("unchecked")
	@GetMapping("/list/{pageNum}")
	public String queryNewsList(@PathVariable int pageNum, NewsQueryDTO dto, ModelMap modelMap) throws Exception {
		// 栏目
		List<Navigation> lstNavigation = (List<Navigation>) yamlUtils.getObject("system.news.navigation", List.class);
		modelMap.put("lstNavigation", lstNavigation);

		// 新闻列表
		Map<String, Object> condition = new HashMap<>();
		condition.put("isDeleted", Constants.NO);
		condition.put("sortColumn", "id");
		condition.put("sortType", "desc");
		ConditionUtils.addStr(condition, "navigationCode", dto.getNavigationCode());
		ConditionUtils.addStr(condition, "subNavigationCode", dto.getSubNavigationCode());
		ConditionUtils.addStr(condition, "title", dto.getTitle());
		ConditionUtils.addStr(condition, "status", dto.getStatus());
		ConditionUtils.addStr(condition, "keyWords", dto.getKeyWords());
		Page<News> page = PageHelper.startPage(pageNum, Constants.DEFAULT_PAGE_SIZE).doSelectPage(() -> newsService.queryNewsList(condition));
		modelMap.put("page", page);
		modelMap.put("queryDto", dto);
		return "news/newsList";
	}

	@GetMapping("/initUpdate")
	@SuppressWarnings("unchecked")
	public String initUpdate(long id, ModelMap modelMap) throws Exception {
		News news = newsService.readNews(id);
		modelMap.put("news", news);
		modelMap.put("newsContent", newsContentService.readNewsContentByNewsId(id));
		// 栏目
		List<Navigation> lstNavigation = (List<Navigation>) yamlUtils.getObject("system.news.navigation", List.class);
		modelMap.put("lstNavigation", lstNavigation);
		return "news/newsEdit";
	}

	@GetMapping("/initInsert")
	@SuppressWarnings("unchecked")
	public String initInsert(ModelMap modelMap) throws Exception {
		News news = new News();
		modelMap.put("news", news);
		modelMap.put("newsContent", new NewsContent());
		// 栏目
		List<Navigation> lstNavigation = (List<Navigation>) yamlUtils.getObject("system.news.navigation", List.class);
		modelMap.put("lstNavigation", lstNavigation);
		return "news/newsEdit";
	}

	@ResponseBody
	@PostMapping("/save")
	public BaseDTO save(@RequestBody News news, HttpSession session) throws Exception {
		SysUser user = getCurUser(session);
		if (news.getId() > 0) {
			// 修改
			Map<String, Object> condition = new HashMap<>(20);
			condition.put("id", news.getId());
			condition.put("title", news.getTitle());
			condition.put("summary", news.getSummary());
			condition.put("coverPicture", news.getCoverPicture());
			condition.put("author", news.getAuthor());
			condition.put("keyWords", news.getKeyWords());
			condition.put("navigationCode", news.getNavigationCode());
			condition.put("navigationName", news.getNavigationName());
			condition.put("subNavigationCode", news.getSubNavigationCode());
			condition.put("subNavigationName", news.getSubNavigationName());
			condition.put("readCount", news.getReadCount());
			condition.put("likeCount", news.getLikeCount());
			condition.put("markCount", news.getMarkCount());
			condition.put("modifyBy", user.getId());
			condition.put("modifyTime", LocalDateTime.now());
			newsService.updateNews(condition);

			// 读取内容
			NewsContent content = newsContentService.readNewsContentByNewsId(news.getId());
			condition.clear();
			condition.put("id", content.getId());
			condition.put("content", news.getContent());
			condition.put("modifyBy", user.getId());
			condition.put("modifyTime", LocalDateTime.now());
			newsContentService.updateNewsContent(condition);
		} else {
			// 新增
			news.setStatus(NewsConstants.NEWS_STATUS_NEW);
			news.setIsDeleted(Constants.NO);
			news.setCreateBy(user.getId());
			news.setCreateTime(LocalDateTime.now());
			newsService.insertNews(news);

			NewsContent content = new NewsContent();
			content.setNewsId(news.getId());
			content.setContent(news.getContent());
			content.setIsDeleted(Constants.NO);
			content.setCreateBy(user.getId());
			content.setCreateTime(LocalDateTime.now());
			newsContentService.insertNewsContent(content);
		}
		return new BaseDTO(RequestResultEnum.SUCCESS, news.getId());
	}

	@ResponseBody
	@PostMapping("/delete")
	public BaseDTO delete(String idStr) throws Exception {
		String[] arrId = idStr.split(",");
		for (String id : arrId) {
			newsService.deleteNews(Long.valueOf(id));
		}
		return new BaseDTO(RequestResultEnum.SUCCESS, null);
	}

	@ResponseBody
	@PostMapping("/putOnLine")
	public BaseDTO putOnLine(long id, HttpSession session) throws Exception {
		SysUser user = getCurUser(session);
		Map<String, Object> condition = new HashMap<>();
		condition.put("id", id);
		condition.put("status", NewsConstants.NEWS_STATUS_ON_LINE);
		condition.put("modifyBy", user.getId());
		condition.put("modifyTime", LocalDateTime.now());
		newsService.updateNews(condition);
		return new BaseDTO(RequestResultEnum.SUCCESS, null);
	}

	@ResponseBody
	@PostMapping("/putOffLine")
	public BaseDTO putOffLine(long id, HttpSession session) throws Exception {
		SysUser user = getCurUser(session);
		Map<String, Object> condition = new HashMap<>();
		condition.put("id", id);
		condition.put("status", NewsConstants.NEWS_STATUS_OFF_LINE);
		condition.put("modifyBy", user.getId());
		condition.put("modifyTime", LocalDateTime.now());
		newsService.updateNews(condition);
		return new BaseDTO(RequestResultEnum.SUCCESS, null);
	}

	@GetMapping("/queryBabietaList/{pageNum}")
	public String queryBabietaList(@PathVariable int pageNum, ModelMap modelMap) throws Exception {
		Map<String, Object> condition = new HashMap<>();
		condition.put("subNavigationCode", "babieta");
		condition.put("isDeleted", Constants.NO);
		condition.put("sortColumn", "id");
		condition.put("sortType", "desc");
		Page<News> page = PageHelper.startPage(pageNum, Constants.DEFAULT_PAGE_SIZE).doSelectPage(() -> newsService.queryNewsList(condition));
		modelMap.put("page", page);
		return "news/babietaList";
	}

	@GetMapping("/initUpdateBabieta/{id}")
	public String initUpdateBabieta(@PathVariable long id, ModelMap modelMap) throws Exception {
		News news = newsService.readNews(id);
		Map<String, Object> condition = new HashMap<>(5);
		condition.put("isDeleted", Constants.NO);
		condition.put("newsId", news.getId());
		news.setLstBabieta(newsBabietaService.queryNewsBabietaList(condition));
		modelMap.put("news", news);
		return "news/babietaEdit";
	}

	@GetMapping("/initInsertBabieta")
	public String initInsertBabieta(ModelMap modelMap) throws Exception {
		News news = new News();
		news.setLstBabieta(new ArrayList<NewsBabieta>());
		modelMap.put("news", news);
		return "news/babietaEdit";
	}

	/**
	 *
	 * @param saveDTO
	 * @param modelMap
	 * @return
	 * @history 2017年12月19日 新建
	 * @auther xiaowen
	 */
	@ResponseBody
	@PostMapping("/saveBabieta")
	public BaseDTO saveBabieta(@RequestBody BabietaSaveDTO saveDTO, ModelMap modelMap, HttpSession session) {
		try {
			SysUser user = getCurUser(session);
			News news = newsService.saveBabieta(saveDTO, user);
			return new BaseDTO(RequestResultEnum.SUCCESS, news);
		} catch (Exception e) {
			logger.error("保存巴别塔失败：服务器内部错误！saveDTO={}", GsonUtils.toSimpleJson(saveDTO), e);
			return new BaseDTO(RequestResultEnum.FAILURE, null);
		}
	}
	
	
	@ResponseBody
	@PostMapping("/deleteBabieta/{id}")
	public BaseDTO deleteBabieta(@PathVariable long id, HttpSession session){
		try {
	        newsBabietaService.deleteNewsBabieta(id);
	        return new BaseDTO(RequestResultEnum.SUCCESS, null);
        } catch (Exception e) {
	        logger.error("删除巴别塔出错：服务器内部出错！id={}", id);
	        return new BaseDTO(RequestResultEnum.FAILURE, null);
        }
	}

}
