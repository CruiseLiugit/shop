package com.shop.common.base;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSession;
import org.mybatis.spring.support.SqlSessionDaoSupport;

import cn.org.rapid_framework.page.Page;
import cn.org.rapid_framework.page.PageRequest;

/**
 * @version 1.0
 */
public abstract class BaseMybatisDao<E,PK extends Serializable> extends SqlSessionDaoSupport {
    protected final Log log = LogFactory.getLog(getClass());
 
    public boolean saveObject(E entity) {
		prepareObjectForSaveOrUpdate(entity);
		return getSqlSessionTemplate().insert(getInsertStatement(), entity)==1?true:false;    	
    }
    
	public boolean deleteObjectById(String id) {
		return getSqlSessionTemplate().delete(getDeleteStatement(), id)>=1?true:false;
		
	}
    
	public boolean updateObject(E entity) {
		prepareObjectForSaveOrUpdate(entity);
		return  getSqlSessionTemplate().update(getUpdateStatement(), entity)==1?true:false; 
	}
	
	public Object getObjectById(PK primaryKey) {
	        Object object = getSqlSessionTemplate().selectOne(getOneByIdStatement(), primaryKey);
	        return object;
	}
	/**
	 * 查询集合（不分页）
	 * @param map
	 * @return
	 */
	public List queryObjectAll(Map filtersMap) {
		List list = getSqlSessionTemplate().selectList(getQueryAllStatement(), filtersMap);
		return list;
	}
	

	
	
	/**
	 * 分页查询
	 * 分页条件 PageRequest类中设置
	 * @param statementName
	 * @param countStatementName
	 * @param pageRequest
	 * @return
	 */
	public List queryObjectPage(PageRequest pageRequest) {
		Integer totalCount = (Integer) getSqlSessionTemplate().selectOne(getQueryAllCount(),pageRequest.getFilters());
		if(totalCount == null || totalCount.longValue() <= 0) {
			return null;
		}
		Page page = new Page(pageRequest,totalCount.intValue());
		
		//其它分页参数,用于不喜欢或是因为兼容性而不使用方言(Dialect)的分页用户使用. 与getSqlMapClientTemplate().queryForList(statementName, parameterObject)配合使用
		Map filters = new HashMap();
		filters.put("offset", page.getFirstResult());
		filters.put("pageSize", page.getPageSize());
		filters.put("lastRows", page.getFirstResult() + page.getPageSize());
		filters.put("sortColumns", pageRequest.getSortColumns());

		filters.putAll((Map)pageRequest.getFilters());
		List list = getSqlSessionTemplate().selectList(getQueryAllStatement(), filters,new RowBounds(page.getFirstResult(),page.getPageSize()));
		return list;
	}
	/**
	 * 分页查询
	 * 分页条件 PageRequest类中设置
	 * @param queryAllCountSql  总条数sql
	 * @param queryAllStatementSql  所有的数据sql
	 * @param statementName
	 * @param countStatementName
	 * @param pageRequest
	 * @return
	 */
	public List queryObjectPage(PageRequest pageRequest,String queryAllCountSql,String queryAllStatementSql) {
		Integer totalCount = (Integer) getSqlSessionTemplate().selectOne(queryAllCountSql,pageRequest.getFilters());
		if(totalCount == null || totalCount.longValue() <= 0) {
			return null;
		}
		Page page = new Page(pageRequest,totalCount.intValue());
		
		//其它分页参数,用于不喜欢或是因为兼容性而不使用方言(Dialect)的分页用户使用. 与getSqlMapClientTemplate().queryForList(statementName, parameterObject)配合使用
		Map filters = new HashMap();
		filters.put("offset", page.getFirstResult());
		filters.put("pageSize", page.getPageSize());
		filters.put("lastRows", page.getFirstResult() + page.getPageSize());
		filters.put("sortColumns", pageRequest.getSortColumns());
		
		filters.putAll((Map)pageRequest.getFilters());
		List list = getSqlSessionTemplate().selectList(queryAllStatementSql, filters,new RowBounds(page.getFirstResult(),page.getPageSize()));
		return list;
	}
//	public List queryObjectPage(Map<String, Object> searchConditionMap,PageSpliter pageSpliter,String queryAllCountSql,String queryAllStatementSql) {
//		Integer totalCount = (Integer) getSqlSessionTemplate().selectOne(queryAllCountSql,searchConditionMap);
//		pageSpliter.setPageCount(totalCount);
//		if(totalCount == null || totalCount.longValue() <= 0) {
//			return null;
//		}
//		List list = getSqlSessionTemplate().selectList(queryAllStatementSql, searchConditionMap,new RowBounds((pageSpliter.getPageNum()-1)*pageSpliter.getPageSize(),pageSpliter.getPageSize()));
//		return list;
//	}
//	
	/**
	 * 用于子类覆盖,在insert,update之前调用
	 * @param o
	 */
    protected void prepareObjectForSaveOrUpdate(E o) {
    }

    public  abstract String getMybatisMapperNamesapce();
    

    public String getInsertStatement() {
        return getMybatisMapperNamesapce()+".insert"; 
    }

    public String getUpdateStatement() {
    	return getMybatisMapperNamesapce()+".update";
    }

    public String getDeleteStatement() {
    	return getMybatisMapperNamesapce()+".delete";
    }
    public String getOneByIdStatement() {
    	return getMybatisMapperNamesapce()+".getById";
    }
    public String getQueryAllStatement() {
    	return getMybatisMapperNamesapce()+".queryAll";
    }

    public String getQueryAllCount() {
		return getMybatisMapperNamesapce() +".queryAllCount";
	}
    public String getCountStatementForPaging(String statementName) {
    	return statementName +".count";
    }
   
	public boolean isUnique(E entity, String uniquePropertyNames) {
		throw new UnsupportedOperationException();
	}
	
 	
	public static interface SqlSessionCallback {		
		public Object doInSession(SqlSession session);
		
	}
	
	
}
