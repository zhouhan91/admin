package com.wemeCity.common.taglibs;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.github.pagehelper.PageInfo;
import com.wemeCity.common.utils.GsonUtils;
import com.wemeCity.common.utils.StringUtils;

public class PageTag<T> extends TagSupport {

	private static final long serialVersionUID = 2992837637941915339L;

	private Logger logger = LoggerFactory.getLogger(PageTag.class);

	private PageInfo<T> pageInfo;

	private String function;

	private String url;

	@Override
	public int doStartTag() throws JspException {
		logger.debug("pageInfo={}", GsonUtils.toSimpleJson(pageInfo));
		if (pageInfo == null) {
			return SKIP_BODY;
		}
		StringBuilder sb = new StringBuilder();
		if (pageInfo.getTotal() <= 0) {
			sb.append("<div style='text-align: center;font-size: 16px;'>暂无数据</div>");
		} else {
			int pre = pageInfo.getPageNum() - 1 > 0 ? pageInfo.getPageNum() - 1 : 1;
			int next = pageInfo.getPageNum() == pageInfo.getPages() ? pageInfo.getPages() : pageInfo.getPageNum() + 1;
			List<String> lst = this.getPageChoosed(pageInfo.getPages(), 6, pageInfo.getPageNum());
			sb.append("<div>");
			sb.append("<nav aria-label='Page navigation'>");
			sb.append("<ul class='pagination pagination-lg'>");
			sb.append("<li>");
			sb.append("<a href='").append(getLinkStr(pre + "")).append("' aria-label='Previous'>");
			sb.append("<span aria-hidden='true'>上一页</span>");
			sb.append("</a>");
			sb.append("</li>");
			//
			for (int i = 1; i <= lst.size(); i++) {
				String var = lst.get(i - 1);
				if (i == pageInfo.getPageNum()) {
					sb.append("<li class='active'><a href='").append(getLinkStr(var)).append("'>").append(var).append("</a></li>");
				} else {
					sb.append("<li><a href='").append(getLinkStr(var)).append("'>").append(var).append("</a></li>");
				}
			}
			//
			sb.append("<li>");
			sb.append("<a href='").append(getLinkStr(next + "")).append("' aria-label='Next'>");
			sb.append("<span aria-hidden='true'>下一页</span>");
			sb.append("</a>");
			sb.append("</li>");
			sb.append("</ul>");
			sb.append("</nav>");
			sb.append("</div>");
		}
		try {
			pageContext.getOut().write(sb.toString());
		} catch (Exception e) {
			logger.error("文本标签输出失败！");
		}
		return SKIP_BODY;
	}

	private String getLinkStr(String page) {
		if (!StringUtils.isEmpty(function)) {
			return "javascript:" + function + "(" + page + ");";
		} else if (!StringUtils.isEmpty(url)) {
			return url + page;
		} else {
			logger.error("page tag error, null function && url");
			return "javascript:void(0);";
		}
	}

	public List<String> getPageChoosed(long pageCount, int pageChooseCount, int pageNum) {
		List<String> page = new ArrayList<String>();
		if (pageCount <= pageChooseCount || pageNum < pageChooseCount && pageChooseCount >= pageCount) {
			for (int i = 1; i <= pageCount; i++) {
				page.add("" + i);
			}
		} else if (pageNum < pageChooseCount && pageChooseCount < pageCount && pageNum <= 4) {
			page.add("1");
			page.add("2");
			page.add("3");
			page.add("4");
			page.add("...");
			page.add(pageCount + "");
		} else if (pageCount > pageChooseCount && pageNum <= pageCount - 3) {
			page.add("1");
			page.add("2");
			page.add("...");
			page.add(pageNum - 1 + "");
			page.add(pageNum + "");
			page.add(pageNum + 1 + "");
			page.add("...");
			page.add(pageCount + "");
		} else {
			page.add("1");
			page.add("2");
			page.add("...");
			page.add(pageCount - 2 + "");
			page.add(pageCount - 1 + "");
			page.add(pageCount + "");
		}
		return page;
	}

	public PageInfo<T> getPageInfo() {
		return pageInfo;
	}

	public void setPageInfo(PageInfo<T> pageInfo) {
		this.pageInfo = pageInfo;
	}

	public String getFunction() {
		return function;
	}

	public void setFunction(String function) {
		this.function = function;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

}
