
package com.wemeCity.admin.news.model;

import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;
import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * NewsComment实体类
 *
 * @author 向小文
 * @since JDK1.8
 * @history 2017-11-18 新建
 */
public class NewsComment {
	/** (id,BIGINT(20),not null)自增主键 */
	private long id;

	/** (user_id,BIGINT(20), null)用户id */
	private long userId;

	/** (nick_name,VARCHAR(50), null)用户昵称 */
	private String nickName;

	/** (news_id,BIGINT(20), null)新闻id */
	private long newsId;

	/** (title,VARCHAR(500), null)新闻标题 */
	private String title;

	/** (cover_picture,VARCHAR(500), null)封面图片 */
	private String coverPicture;

	/** (author,VARCHAR(200), null)作者 */
	private String author;

	/** (navigation_code,VARCHAR(50), null)栏目编码 */
	private String navigationCode;

	/** (navigation_name,VARCHAR(50), null)栏目名称 */
	private String navigationName;

	/** (sub_navigation_code,VARCHAR(50), null)子栏目编码 */
	private String subNavigationCode;

	/** (sub_navigation_name,VARCHAR(50), null)子栏目名称 */
	private String subNavigationName;

	/** (key_words,VARCHAR(200), null)关键词 */
	private String keyWords;

	/** (content,VARCHAR(1024), null)评论内容 */
	private String content;

	/** (parent_id,BIGINT(20), null)上层评论id */
	private long parentId;

	/** (parent_tree,VARCHAR(1024), null)盖楼顺 */
	private String parentTree;

	/** (reply_count,INT(11), null)回复数 */
	private int replyCount;

	/** (like_count,INT(11), null)点赞数 */
	private int likeCount;

	/** (is_deleted,VARCHAR(255), null)是否已删除(Y/N) */
	private String isDeleted;

	/** (create_by,BIGINT(20), null)创建人 */
	private long createBy;

	/** (create_time,DATETIME, null)创建时间 */
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private LocalDateTime createTime;

	/** (modify_by,BIGINT(20), null)最后修改人 */
	private long modifyBy;

	/** (modify_time,DATETIME, null)最后修改时间 */
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private LocalDateTime modifyTime;

	public void setId(long id){
		this.id=id;
	}

	public long getId(){
		return this.id;
	}

	public void setUserId(long userId){
		this.userId=userId;
	}

	public long getUserId(){
		return this.userId;
	}

	public void setNickName(String nickName){
		this.nickName=nickName;
	}

	public String getNickName(){
		return this.nickName;
	}

	public void setNewsId(long newsId){
		this.newsId=newsId;
	}

	public long getNewsId(){
		return this.newsId;
	}

	public void setTitle(String title){
		this.title=title;
	}

	public String getTitle(){
		return this.title;
	}

	public void setCoverPicture(String coverPicture){
		this.coverPicture=coverPicture;
	}

	public String getCoverPicture(){
		return this.coverPicture;
	}

	public void setAuthor(String author){
		this.author=author;
	}

	public String getAuthor(){
		return this.author;
	}

	public void setNavigationCode(String navigationCode){
		this.navigationCode=navigationCode;
	}

	public String getNavigationCode(){
		return this.navigationCode;
	}

	public void setNavigationName(String navigationName){
		this.navigationName=navigationName;
	}

	public String getNavigationName(){
		return this.navigationName;
	}

	public void setSubNavigationCode(String subNavigationCode){
		this.subNavigationCode=subNavigationCode;
	}

	public String getSubNavigationCode(){
		return this.subNavigationCode;
	}

	public void setSubNavigationName(String subNavigationName){
		this.subNavigationName=subNavigationName;
	}

	public String getSubNavigationName(){
		return this.subNavigationName;
	}

	public void setKeyWords(String keyWords){
		this.keyWords=keyWords;
	}

	public String getKeyWords(){
		return this.keyWords;
	}

	public void setContent(String content){
		this.content=content;
	}

	public String getContent(){
		return this.content;
	}

	public void setParentId(long parentId){
		this.parentId=parentId;
	}

	public long getParentId(){
		return this.parentId;
	}

	public void setParentTree(String parentTree){
		this.parentTree=parentTree;
	}

	public String getParentTree(){
		return this.parentTree;
	}

	public void setReplyCount(int replyCount){
		this.replyCount=replyCount;
	}

	public int getReplyCount(){
		return this.replyCount;
	}

	public void setLikeCount(int likeCount){
		this.likeCount=likeCount;
	}

	public int getLikeCount(){
		return this.likeCount;
	}

	public void setIsDeleted(String isDeleted){
		this.isDeleted=isDeleted;
	}

	public String getIsDeleted(){
		return this.isDeleted;
	}

	public void setCreateBy(long createBy){
		this.createBy=createBy;
	}

	public long getCreateBy(){
		return this.createBy;
	}

	public void setCreateTime(LocalDateTime createTime){
		this.createTime=createTime;
	}

	public LocalDateTime getCreateTime(){
		return this.createTime;
	}

	public void setModifyBy(long modifyBy){
		this.modifyBy=modifyBy;
	}

	public long getModifyBy(){
		return this.modifyBy;
	}

	public void setModifyTime(LocalDateTime modifyTime){
		this.modifyTime=modifyTime;
	}

	public LocalDateTime getModifyTime(){
		return this.modifyTime;
	}

}