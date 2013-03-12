package bjwxsytx.common;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;




//import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
//import org.hibernate.SessionFactory;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.stat.SecondLevelCacheStatistics;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
//import org.springframework.stereotype.Repository;


//import cn.gzjp.common.util.Page;

/**
 * 
 * 功能描述:Dao基类，其他dao可以继承该类，也可以在其他服务类中直接注入该类
 * <p>
 * 版权所有：金鹏科技
 * <p>
 * 未经本公司许可，不得以任何方式复制或使用本程序任何部分
 * 
 * @author dengcd 新增日期：2008-10-13
 * @author 你的姓名 修改日期：2008-10-13
 * @since wapportal_manager version(2.0)
 */
@SuppressWarnings("unchecked")

public class CommonDAO<T> extends HibernateDaoSupport {
	private static Logger _log = Logger.getLogger(CommonDAO.class);
	/***
	 * 
	* 方法用途和描述: EASYUI 分页查询（可以分多行编写）
	* @param page
	* @param params
	* @return
	* @author 刘小明 新增日期：2013-1-14
	* @author 你的姓名 修改日期：2013-1-14
	* @since gx-cms
	 */
	public Page findResult(Page page,List params){
		//Result result = null;
		Object[] values = null;
		if(BlankUtil.isBlank(params)){
			values = new Object[]{};
		}else{
			values = params.toArray();
		}
		try{
			final StringBuffer hql = new StringBuffer();
			hql.append("select  ");
			if(!BlankUtil.isBlank(page.getField())){
				hql.append(" count( ");
				hql.append( "*" );
				hql.append(" ) ");
			}else{
				hql.append(" count(*) ");
			}
			hql.append(" from ");
			hql.append(page.getTableName());
			if(!BlankUtil.isBlank(page.getCondition())){
				hql.append(" where 1=1 ");
				hql.append(page.getCondition());
			}
			//_log.info(hql.toString());
			Query query = getSession().createQuery(hql.toString());
			for(int i = 0 ; i < values.length ; i++){
				query.setParameter(i, values[i]);
			}
			long totalCount = Long.valueOf(query.list().get(0).toString());
			hql.delete(0, hql.length());
			if(!BlankUtil.isBlank(page.getField())){
				hql.append(" select ");
				hql.append(page.getField());
			}
			hql.append(" from ");
			hql.append(page.getTableName());
			if(!BlankUtil.isBlank(page.getCondition())){
				hql.append(" where 1=1 ");
				hql.append(page.getCondition());
			}
			if(!BlankUtil.isBlank(page.getGroupBy())){
				hql.append(" ");
				hql.append(page.getGroupBy());
			}
			if(!BlankUtil.isBlank(page.getSort())&&!BlankUtil.isBlank(page.getDir())){
				hql.append(" order by ");
				hql.append(page.getSort());
				hql.append(" ");
				hql.append(page.getDir());
			}
			//_log.info(hql.toString());
			//int start = 0;
			//if(page.getStart()>0)
			//	start = page.getStart()/page.getLimit();
			query = getSession().createQuery(hql.toString());
			for(int i= 0 ;i < values.length ; i++){
				query.setParameter(i,values[i]);
			}
			query.setFirstResult((page.getPage()-1)*page.getRows());
			query.setMaxResults(page.getRows());
			List list = query.list();
			page.setData(list);
			page.setList(list);
			page.setTotalCount(totalCount);
			return page;
		}catch(Exception ex){
			ex.printStackTrace();
		}
		return null;
	}
	/**
	 * 查询缓存名称
	 */
	public static String queryCache;
	/**
	 * 是否开启查询缓存
	 */
	private static String openCache;

	public void setQueryCache(String queryCache) {
		//_log.info("-----setQueryCache-------" + queryCache);
		if (queryCache != null) {
			CommonDAO.queryCache = queryCache;
		}
	}

	public String getQueryCache() {
		return queryCache;
	}

	public String getOpenCache() {
		return openCache;
	}

	public void setOpenCache(String openCache) {
		if (openCache != null) {
			CommonDAO.openCache = openCache;
		}
	}

	/**
	 * 根据指定的标识获取特定的持久化实体对象，如果找不到指定的实体对象，返回null
	 * 
	 * @param entityClass
	 *            持久化实体类
	 * @param id
	 *            实体对象标识
	 * @return 指定的实体对象
	 * @author dengcd 新增日期：2008-1-7
	 * @since 2.0
	 */

	public T get(Class<T> entityClass, Serializable id) {

		return (T) this.getHibernateTemplate().get(entityClass, id);
	}

	/**
	 * 根据指定的标识获取特定的持久化实体对象，如果找不到指定的实体对象，返回null。<br/>
	 * 如果存入的实体名对应的类与泛型指定的类不相同，则改方法可能抛出ClassCastException。
	 * 
	 * @param entityClass
	 *            持久化实体名
	 * @param id
	 *            实体对象标识
	 * @return 指定的实体对象
	 * @author dengcd 新增日期：2008-1-7
	 * @since 2.0
	 */
	public T get(String entityName, Serializable id) {

		return (T) this.getHibernateTemplate().get(entityName, id);
	}

	/**
	 * 根据指定的标识获取特定的持久化实体对象，如果找不到指定的实体对象，抛出异常。<br/> 不要用此方法判断对象是否存在，应该使用get()方法。
	 * 
	 * @param entityClass
	 *            持久化实体类
	 * @param id
	 *            实体对象标识
	 * @return 指定的实体对象 @ * 对象不存在、底层数据访问出错
	 * @author dengcd 新增日期：2008-1-7
	 * @since 2.0
	 */
	public T load(Class<T> entityClass, Serializable id) {

		T t = (T) this.getHibernateTemplate().get(entityClass, id);

		return t;
	}

	/**
	 * 根据指定的标识获取特定的持久化实体对象，如果找不到指定的实体对象，抛出异常。<br/>
	 * 如果存入的实体名对应的类与泛型指定的类不相同，则改方法可能抛出ClassCastException。<br/>
	 * 不要用此方法判断对象是否存在，应该使用get()方法。
	 * 
	 * @param entityClass
	 *            持久化实体名
	 * @param id
	 *            实体对象标识
	 * @return 指定的实体对象 @ * 对象不存在、底层数据访问出错
	 * @author dengcd 新增日期：2008-1-7
	 * @since 2.0
	 */
	public T load(String entityName, Serializable id) {
		T t = (T) this.getHibernateTemplate().get(entityName, id);

		return t;
	}

	/**
	 * 读取指定的实体类的所有持久化对象
	 * 
	 * @param entityClass
	 *            实体类
	 * @return 持久化对象列表（如果为空，返回一个size=0的空列表） @ * 底层数据访问出错
	 * @author dengcd 新增日期：2008-1-7
	 * @since 2.0
	 */
	public List<T> listAll(Class<T> entityClass) {
		return (List<T>) this.getHibernateTemplate().loadAll(entityClass);
	}

	/**
	 * 分页读取指定的部分持久化对象
	 * 
	 * @param entityClass
	 *            实体类
	 * @param startIndex
	 *            开始索引值（从0开始）
	 * @param maxResult
	 *            每页最大读取数量
	 * @return 持久化对象列表（如果为空，返回一个size=0的空列表） @ * 底层数据访问出错
	 * @author dengcd 新增日期：2008-1-7
	 * @since 2.0
	 */
	public List<T> listPartly(Class<T> entityClass, int startIndex,
			int maxResult) {
		return (List<T>) this.getQueryObj(
				"from " + entityClass.getName()).setFirstResult(startIndex)
				.setMaxResults(maxResult).list();
	}

	/**
	 * 根据HQL语句进行查询，返回符合条件的所有记录
	 * 
	 * @param hql
	 *            HQL查询语句
	 * @param values
	 *            条件参数，对应HQL的“？”
	 * @return 符合条件的记录集合（如果为空，返回一个size=0的空列表） @ * HQL语法错误、底层数据访问出错
	 * @author dengcd 新增日期：2008-1-7
	 * @since 2.0
	 */
	public List<T> searchAll(String hql, Object[] values) {
		Query query = getQueryObj(hql);
		if (values != null) {
			for (int i = 0; i < values.length; i++) {
				query.setParameter(i, values[i]);
			}
		}

		return (List<T>) query.list();
	}

	/**
	 * 
	* 方法用途和描述: 根据HQL语句进行查询，返回符合条件的所有记录
	* @param hql
	* @param values  以hql参数名作为map 的 key，以hql参数值作为 map 的 value(支持List参数)
	* @return
	* @author libu 新增日期：2008-12-25
	* @author 你的姓名 修改日期：2008-12-25
	* @since wapportal_manager version(2.0)
	 */
	public List<T> searchAll(String hql,Map values){
		Query query = getQueryObj(hql);
		if (values != null) {
			for(Object key : values.keySet()){
				Object value = values.get(key);
				if(value instanceof List){
					query.setParameterList((String)key, (List)value);
				}else{
					query.setParameter((String)key, value);
				}
			}
		}

		return (List<T>) query.list();
	}
	/***
	 * 方法用途和描述：根据criteria查询，返回条件的所有记录。
	 * @param criteria
	 * @return
	 */
	public List<T> searchAll(DetachedCriteria criteria){
		return this.getHibernateTemplate().findByCriteria(criteria);
	}
	
	public Query getQueryObj(String hql) {
		Query query = this.getSession().createQuery(hql);

		if ("yes".equals(this.getOpenCache())) {
			// 激活查询缓存
			query.setCacheable(true);
			// 使用自定义的查询缓存区域,若不设置,则使用标准查询缓存区域
			query.setCacheRegion(this.getQueryCache());
		}
		return query;
	}
	
	public SQLQuery getSQLQueryObj(String hql) {
		SQLQuery query = this.getSession().createSQLQuery(hql);
		return query;
	}

	/**
	 * 根据HQL语句进行查询，分页返回符合条件的记录
	 * 
	 * @param hql
	 *            HQL查询语句
	 * @param values
	 *            条件参数，对应HQL的“？”
	 * @param startIndex
	 *            开始索引值（从0开始）
	 * @param maxResult
	 *            每页最大读取数量
	 * @return 符合条件的记录集合（如果为空，返回一个size=0的空列表） @ * HQL语法错误、底层数据访问出错
	 * @author dengcd 新增日期：2008-1-7
	 * @since 2.0
	 */
	public List<T> searchPartly(String hql, Object[] values, int startIndex,
			int maxResult) {
		Query query = this.getQueryObj(hql).setFirstResult(
				startIndex).setMaxResults(maxResult);
		if (values != null) {
			for (int i = 0; i < values.length; i++) {
				query.setParameter(i, values[i]);
			}
		}

		return (List<T>) query.list();

	}

	/**
	 * 返回指定实体类的所有记录数
	 * 
	 * @param entityClass
	 *            实体类
	 * @return 实体类的所有记录数 @ * 底层数据访问出错
	 * @author dengcd 新增日期：2008-1-7
	 * @since 2.0
	 */
	public long count(Class<T> entityClass) {
		return (Long) this.getQueryObj(
				"select count(*) from " + entityClass).list().get(0);
	}

	/**
	 * 持久化实体
	 * 
	 * @param entity
	 *            实体对象
	 * @return 实体的持久化标识 @ * 底层数据访问出错
	 * @author dengcd 新增日期：2008-1-7
	 * @since 2.0
	 */
	public Serializable save(T entity) {
		return this.getHibernateTemplate().save(entity);
		
	}

	/**
	 * 批量持久化实体
	 * 
	 * @param entities
	 *            实体集合
	 * @return 实体的持久化标识集合 @ * 底层数据访问出错
	 * @author dengcd 新增日期：2008-1-7
	 * @since 2.0
	 */
	public List<? extends Serializable> saveAll(Collection<T> entities) {
		List<Serializable> ids = new ArrayList<Serializable>();

		for (T entity : entities) {
			ids.add(this.getHibernateTemplate().save(entity));
		}

		return ids;
	}

	/**
	 * 更新持久化对象
	 * 
	 * @param entity
	 *            实体对象 @ * 对象不存在、底层数据访问出错
	 * @author dengcd 新增日期：2008-1-7
	 * @since 2.0
	 */
	public void update(T entity) {
		this.getHibernateTemplate().update(entity);
	}

	/**
	 * 批量更新持久化对象
	 * 
	 * @param entities
	 *            实体对象集合 @ * 对象不存在、底层数据访问出错
	 * @author dengcd 新增日期：2008-1-7
	 * @since 2.0
	 */
	public void updateAll(Collection<T> entities) {
		for (T entity : entities) {
			this.getHibernateTemplate().update(entity);
		}
	}

	/**
	 * 
	 * 将detached对象的属性拷贝到持久化对象
	 * 
	 * @param entity
	 * @author chuzq 新增日期：2008-11-23
	 * @author 你的姓名 修改日期：2008-11-23
	 * @since wapportal_manager version(2.0)
	 */
	public void merge(T entity) {
		this.getHibernateTemplate().merge(entity);
	}

	/**
	 * 删除持久化对象
	 * 
	 * @param entities
	 *            实体对象 @ * 对象不存在、底层数据访问出错
	 * @author dengcd 新增日期：2008-1-7
	 * @since 2.0
	 */
	public void delete(T entity) {
		this.getHibernateTemplate().delete(entity);
	}

	/**
	 * 删除持久化对象
	 * 
	 * @param entityClass
	 *            实体类
	 * @param entityId
	 *            实体标识 @ * 对象不存在、底层数据访问出错
	 * @author dengcd 新增日期：2008-1-7
	 * @since 2.0
	 */
	public void delete(Class<T> entityClass, Serializable entityId) {

		T entity = this.get(entityClass, entityId);

		this.getHibernateTemplate().delete(entity);

	}

	/**
	 * 批量删除持久化对象
	 * 
	 * @param entities
	 *            实体对象集合 @ * 对象不存在、底层数据访问出错
	 * @author dengcd 新增日期：2008-1-7
	 * @since 2.0
	 */
	public void deleteAll(Collection<T> entities) {
		this.getHibernateTemplate().deleteAll(entities);
	}

	/**
	 * 批量删除持久化对象
	 * 
	 * @param entityClass
	 *            实体类
	 * @param entityIds
	 *            实体标识集合 @ * 对象不存在、底层数据访问出错
	 * @author dengcd 新增日期：2008-1-7
	 * @since 2.0
	 */
	public void deleteAll(Class<T> entityClass,
			List<? extends Serializable> entityIds) {
		for (Serializable entityId : entityIds) {
			T entity = this.get(entityClass, entityId);
			this.getHibernateTemplate().delete(entity);
		}
	}

	/**
	 * 
	 * 通过hql删除实体
	 * 
	 * @param hql
	 * @param values
	 * @return
	 * @author chuzq 新增日期：2008-11-25
	 * @author 你的姓名 修改日期：2008-11-25
	 * @since wapportal_manager version(2.0)
	 */
	public int deleteByHql(String hql, Object[] values) {
		Query query = getQueryObj(hql);
		if (values != null) {
			for (int i = 0; i < values.length; i++) {
				query.setParameter(i, values[i]);
			}
		}

		int deleteNum = query.executeUpdate();
		return deleteNum;

	}

	/**
	 * 分页读取指定的部分持久化对象
	 * 
	 * @param entityClass
	 *            实体类
	 * @param pageNo
	 *            页码
	 * @param pageSize
	 *            每页最大读取数量
	 * @return 持久化对象列表 @ * 底层数据访问出错
	 * @author dengcd 新增日期：2008-1-7
	 * @since 2.0
	 */
//	public Page listByPage(Class<T> entityClass, Page queryPage) {
//		return this.pagedQuery("from " + entityClass.getName(), queryPage,
//				new Object[] {});
//
//	}

	/**
	 * 根据HQL语句进行查询，分页返回符合条件的记录
	 * 
	 * @param hql
	 *            HQL查询语句
	 * @param values
	 *            条件参数，对应HQL的“？”
	 * @param pageNo
	 *            页码
	 * @param pageSize
	 *            每页最大读取数量
	 * @return 符合条件的记录集合 @ * HQL语法错误、底层数据访问出错
	 * @author dengcd 新增日期：2008-1-7
	 * @since 2.0
	 */
//	public Page searchByPage(String hql, Object[] values, Page queryPage) {
//		if (values == null) {
//			values = new Object[] {};
//		}
//		return this.pagedQuery(hql, queryPage, values);
//
//	}

	/**
	 * 
	 * 方法用途和描述: 执行分页查询
	 * 
	 * @param hql
	 * @param pageNo
	 * @param pageSize
	 * @param args
	 * @return
	 * @author dengcd 新增日期：2008-10-9
	 * @author 你的姓名 修改日期：2008-10-9
	 * @since wapportal_manager version(2.0)
	 */
//	private Page pagedQuery(String hql, Page queryPage, Object... args) {
//		int pageNo = 0;
//		int pageSize = 0;
//		if (queryPage.getPageSize() > 0) {
//			pageSize = queryPage.getPageSize();
//		}
//		if (queryPage.getPageNo() > 0) {
//			pageNo = queryPage.getPageNo();
//		}
//		Query query = this.getQueryObj(hql);
//		for (int i = 0; i < args.length; i++) {
//			query.setParameter(i, args[i]);
//		}
//
//		String countQueryString = (new StringBuilder(" SELECT count(*) "))
//				.append(removeSelect(removeOrders(hql))).toString();
//		_log.info("-----countQueryString-------" + countQueryString);
//		List countlist = getHibernateTemplate().find(countQueryString, args);
//		long totalCount = ((Long) countlist.get(0)).longValue();
//		_log.info("-----totalCount-------" + totalCount);
//		if (totalCount < 1L) {
//			return new Page(pageSize);
//		}
//
//		// 添加页码判断，判断当前提交过来的页码是否处于有效的页码范围之内
//		long size = (totalCount / pageSize)
//				+ (totalCount % pageSize != 0 ? 1 : 0);
//		if (size < pageNo) {
//			pageNo = 1;
//		}
//
//		int startIndex = Page.getStartOfPage(pageNo, pageSize);
//		List list = query.setFirstResult(startIndex).setMaxResults(pageSize)
//				.list();
//		return new Page(startIndex, totalCount, pageSize, list);
//	}

	/**
	 * 
	 * 方法用途和描述: 去除hql中的排序部分
	 * 
	 * @param hql
	 * @return
	 * @author dengcd 新增日期：2008-10-9
	 * @author 你的姓名 修改日期：2008-10-9
	 * @since wapportal_manager version(2.0)
	 */
//	private static String removeOrders(String hql) {
//		// Assert.hasText(hql);
//		Pattern p = Pattern.compile("ORDER\\s*by[\\w|\\W|\\s|\\S]*", 2);
//		Matcher m = p.matcher(hql);
//		StringBuffer sb = new StringBuffer();
//		for (; m.find(); m.appendReplacement(sb, ""))
//			;
//		m.appendTail(sb);
//		return sb.toString();
//	}

	/**
	 * 
	 * 方法用途和描述: 去除hql中的select部分
	 * 
	 * @author dengcd 新增日期：2008-10-9
	 * @author 你的姓名 修改日期：2008-10-9
	 * @since wapportal_manager version(2.0)
	 */
//	private static String removeSelect(String hql) {
//		Assert.hasText(hql);
//		int beginPos = hql.toLowerCase().indexOf("from");
//		// Assert.isTrue(beginPos >= 0, (new
//		// StringBuilder(" hql : ")).append(hql
//		// ).append(" must has a keyword 'from'").toString());
//		return hql.substring(beginPos);
//	}

	//===================测试查询缓存===================
	private SecondLevelCacheStatistics getSLCS(Class className) {
		getSessionFactory().getStatistics().setStatisticsEnabled(true);
		return this.getSessionFactory().getStatistics()
				.getSecondLevelCacheStatistics(className.getName());
	}

	private void resultMessage(String operator, Class className) {
		StringBuilder sb = new StringBuilder();
		sb.append("->");
		sb.append(operator);
		SecondLevelCacheStatistics s = getSLCS(className);
		if (s != null) {
			sb.append(" 内存记录数:").append(s.getElementCountInMemory());
//			sb.append(" 缓存实体的ID:").append(
//					java.util.Arrays
//							.toString(s.getEntries().keySet().toArray()));
			sb.append(" 磁盘记录数:").append(s.getElementCountOnDisk());
			//sb.append(" 总记录数:").append(s.getEntries().size());
			sb.append(" 命中次数:").append(s.getHitCount());
			sb.append(" 失败次数:").append(s.getMissCount());
			sb.append(" 缓存实例次数:").append(s.getPutCount());
			sb.append(" SizeInMemory:").append(s.getSizeInMemory());
			sb.append(" CategoryName:").append(s.getCategoryName());
			sb.append("<-");
		}
		//_log.info(sb.toString());
	}

	public void before(String operator, Class className) {
		this.resultMessage(operator, className);
	}

	public void after(String operator, Class className) {
		this.resultMessage(operator, className);
	}
	
	/**
	 * 
	* package_name: bjwxsytx.common
	* file_name:    CommonDAO.java
	* description: 根据HQL语句，返回查询结果list
	* 2013-3-12上午11:04:09
	* Author: chenhui
	 * @param hql
	 * @return
	 */
	public List<T> searchObjectsByHql(String hql) {
		Query query = getQueryObj(hql);
		return (List<T>) query.list();
	}

}
