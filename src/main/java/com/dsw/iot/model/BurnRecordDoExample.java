package com.dsw.iot.model;

import com.dsw.iot.util.PageDto;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class BurnRecordDoExample {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table burn_record
     *
     * @mbggenerated Fri Apr 13 18:00:19 CST 2018
     */
    protected String orderByClause;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table burn_record
     *
     * @mbggenerated Fri Apr 13 18:00:19 CST 2018
     */
    protected boolean distinct;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table burn_record
     *
     * @mbggenerated Fri Apr 13 18:00:19 CST 2018
     */
    protected List<Criteria> oredCriteria;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table burn_record
     *
     * @mbggenerated Fri Apr 13 18:00:19 CST 2018
     */
    protected PageDto pageDto;

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table burn_record
     *
     * @mbggenerated Fri Apr 13 18:00:19 CST 2018
     */
    public BurnRecordDoExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table burn_record
     *
     * @mbggenerated Fri Apr 13 18:00:19 CST 2018
     */
    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table burn_record
     *
     * @mbggenerated Fri Apr 13 18:00:19 CST 2018
     */
    public String getOrderByClause() {
        return orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table burn_record
     *
     * @mbggenerated Fri Apr 13 18:00:19 CST 2018
     */
    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table burn_record
     *
     * @mbggenerated Fri Apr 13 18:00:19 CST 2018
     */
    public boolean isDistinct() {
        return distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table burn_record
     *
     * @mbggenerated Fri Apr 13 18:00:19 CST 2018
     */
    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table burn_record
     *
     * @mbggenerated Fri Apr 13 18:00:19 CST 2018
     */
    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table burn_record
     *
     * @mbggenerated Fri Apr 13 18:00:19 CST 2018
     */
    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table burn_record
     *
     * @mbggenerated Fri Apr 13 18:00:19 CST 2018
     */
    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table burn_record
     *
     * @mbggenerated Fri Apr 13 18:00:19 CST 2018
     */
    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table burn_record
     *
     * @mbggenerated Fri Apr 13 18:00:19 CST 2018
     */
    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table burn_record
     *
     * @mbggenerated Fri Apr 13 18:00:19 CST 2018
     */
    public void setPageDto(PageDto pageDto) {
        this.pageDto=pageDto;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table burn_record
     *
     * @mbggenerated Fri Apr 13 18:00:19 CST 2018
     */
    public PageDto getPageDto() {
        return pageDto;
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table burn_record
     *
     * @mbggenerated Fri Apr 13 18:00:19 CST 2018
     */
    protected abstract static class GeneratedCriteria {
        protected List<Criterion> criteria;

        protected GeneratedCriteria() {
            super();
            criteria = new ArrayList<Criterion>();
        }

        public boolean isValid() {
            return criteria.size() > 0;
        }

        public List<Criterion> getAllCriteria() {
            return criteria;
        }

        public List<Criterion> getCriteria() {
            return criteria;
        }

        protected void addCriterion(String condition) {
            if (condition == null) {
                throw new RuntimeException("Value for condition cannot be null");
            }
            criteria.add(new Criterion(condition));
        }

        protected void addCriterion(String condition, Object value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value));
        }

        protected void addCriterion(String condition, Object value1, Object value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value1, value2));
        }

        public Criteria andIdIsNull() {
            addCriterion("id is null");
            return (Criteria) this;
        }

        public Criteria andIdIsNotNull() {
            addCriterion("id is not null");
            return (Criteria) this;
        }

        public Criteria andIdEqualTo(Long value) {
            addCriterion("id =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(Long value) {
            addCriterion("id <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(Long value) {
            addCriterion("id >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(Long value) {
            addCriterion("id >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(Long value) {
            addCriterion("id <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(Long value) {
            addCriterion("id <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<Long> values) {
            addCriterion("id in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<Long> values) {
            addCriterion("id not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(Long value1, Long value2) {
            addCriterion("id between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(Long value1, Long value2) {
            addCriterion("id not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andRegisterIdIsNull() {
            addCriterion("register_id is null");
            return (Criteria) this;
        }

        public Criteria andRegisterIdIsNotNull() {
            addCriterion("register_id is not null");
            return (Criteria) this;
        }

        public Criteria andRegisterIdEqualTo(Long value) {
            addCriterion("register_id =", value, "registerId");
            return (Criteria) this;
        }

        public Criteria andRegisterIdNotEqualTo(Long value) {
            addCriterion("register_id <>", value, "registerId");
            return (Criteria) this;
        }

        public Criteria andRegisterIdGreaterThan(Long value) {
            addCriterion("register_id >", value, "registerId");
            return (Criteria) this;
        }

        public Criteria andRegisterIdGreaterThanOrEqualTo(Long value) {
            addCriterion("register_id >=", value, "registerId");
            return (Criteria) this;
        }

        public Criteria andRegisterIdLessThan(Long value) {
            addCriterion("register_id <", value, "registerId");
            return (Criteria) this;
        }

        public Criteria andRegisterIdLessThanOrEqualTo(Long value) {
            addCriterion("register_id <=", value, "registerId");
            return (Criteria) this;
        }

        public Criteria andRegisterIdIn(List<Long> values) {
            addCriterion("register_id in", values, "registerId");
            return (Criteria) this;
        }

        public Criteria andRegisterIdNotIn(List<Long> values) {
            addCriterion("register_id not in", values, "registerId");
            return (Criteria) this;
        }

        public Criteria andRegisterIdBetween(Long value1, Long value2) {
            addCriterion("register_id between", value1, value2, "registerId");
            return (Criteria) this;
        }

        public Criteria andRegisterIdNotBetween(Long value1, Long value2) {
            addCriterion("register_id not between", value1, value2, "registerId");
            return (Criteria) this;
        }

        public Criteria andRegisterNameIsNull() {
            addCriterion("register_name is null");
            return (Criteria) this;
        }

        public Criteria andRegisterNameIsNotNull() {
            addCriterion("register_name is not null");
            return (Criteria) this;
        }

        public Criteria andRegisterNameEqualTo(String value) {
            addCriterion("register_name =", value, "registerName");
            return (Criteria) this;
        }

        public Criteria andRegisterNameNotEqualTo(String value) {
            addCriterion("register_name <>", value, "registerName");
            return (Criteria) this;
        }

        public Criteria andRegisterNameGreaterThan(String value) {
            addCriterion("register_name >", value, "registerName");
            return (Criteria) this;
        }

        public Criteria andRegisterNameGreaterThanOrEqualTo(String value) {
            addCriterion("register_name >=", value, "registerName");
            return (Criteria) this;
        }

        public Criteria andRegisterNameLessThan(String value) {
            addCriterion("register_name <", value, "registerName");
            return (Criteria) this;
        }

        public Criteria andRegisterNameLessThanOrEqualTo(String value) {
            addCriterion("register_name <=", value, "registerName");
            return (Criteria) this;
        }

        public Criteria andRegisterNameLike(String value) {
            addCriterion("register_name like", value, "registerName");
            return (Criteria) this;
        }

        public Criteria andRegisterNameNotLike(String value) {
            addCriterion("register_name not like", value, "registerName");
            return (Criteria) this;
        }

        public Criteria andRegisterNameIn(List<String> values) {
            addCriterion("register_name in", values, "registerName");
            return (Criteria) this;
        }

        public Criteria andRegisterNameNotIn(List<String> values) {
            addCriterion("register_name not in", values, "registerName");
            return (Criteria) this;
        }

        public Criteria andRegisterNameBetween(String value1, String value2) {
            addCriterion("register_name between", value1, value2, "registerName");
            return (Criteria) this;
        }

        public Criteria andRegisterNameNotBetween(String value1, String value2) {
            addCriterion("register_name not between", value1, value2, "registerName");
            return (Criteria) this;
        }

        public Criteria andPoliceNoIsNull() {
            addCriterion("police_no is null");
            return (Criteria) this;
        }

        public Criteria andPoliceNoIsNotNull() {
            addCriterion("police_no is not null");
            return (Criteria) this;
        }

        public Criteria andPoliceNoEqualTo(String value) {
            addCriterion("police_no =", value, "policeNo");
            return (Criteria) this;
        }

        public Criteria andPoliceNoNotEqualTo(String value) {
            addCriterion("police_no <>", value, "policeNo");
            return (Criteria) this;
        }

        public Criteria andPoliceNoGreaterThan(String value) {
            addCriterion("police_no >", value, "policeNo");
            return (Criteria) this;
        }

        public Criteria andPoliceNoGreaterThanOrEqualTo(String value) {
            addCriterion("police_no >=", value, "policeNo");
            return (Criteria) this;
        }

        public Criteria andPoliceNoLessThan(String value) {
            addCriterion("police_no <", value, "policeNo");
            return (Criteria) this;
        }

        public Criteria andPoliceNoLessThanOrEqualTo(String value) {
            addCriterion("police_no <=", value, "policeNo");
            return (Criteria) this;
        }

        public Criteria andPoliceNoLike(String value) {
            addCriterion("police_no like", value, "policeNo");
            return (Criteria) this;
        }

        public Criteria andPoliceNoNotLike(String value) {
            addCriterion("police_no not like", value, "policeNo");
            return (Criteria) this;
        }

        public Criteria andPoliceNoIn(List<String> values) {
            addCriterion("police_no in", values, "policeNo");
            return (Criteria) this;
        }

        public Criteria andPoliceNoNotIn(List<String> values) {
            addCriterion("police_no not in", values, "policeNo");
            return (Criteria) this;
        }

        public Criteria andPoliceNoBetween(String value1, String value2) {
            addCriterion("police_no between", value1, value2, "policeNo");
            return (Criteria) this;
        }

        public Criteria andPoliceNoNotBetween(String value1, String value2) {
            addCriterion("police_no not between", value1, value2, "policeNo");
            return (Criteria) this;
        }

        public Criteria andPoliceNameIsNull() {
            addCriterion("police_name is null");
            return (Criteria) this;
        }

        public Criteria andPoliceNameIsNotNull() {
            addCriterion("police_name is not null");
            return (Criteria) this;
        }

        public Criteria andPoliceNameEqualTo(String value) {
            addCriterion("police_name =", value, "policeName");
            return (Criteria) this;
        }

        public Criteria andPoliceNameNotEqualTo(String value) {
            addCriterion("police_name <>", value, "policeName");
            return (Criteria) this;
        }

        public Criteria andPoliceNameGreaterThan(String value) {
            addCriterion("police_name >", value, "policeName");
            return (Criteria) this;
        }

        public Criteria andPoliceNameGreaterThanOrEqualTo(String value) {
            addCriterion("police_name >=", value, "policeName");
            return (Criteria) this;
        }

        public Criteria andPoliceNameLessThan(String value) {
            addCriterion("police_name <", value, "policeName");
            return (Criteria) this;
        }

        public Criteria andPoliceNameLessThanOrEqualTo(String value) {
            addCriterion("police_name <=", value, "policeName");
            return (Criteria) this;
        }

        public Criteria andPoliceNameLike(String value) {
            addCriterion("police_name like", value, "policeName");
            return (Criteria) this;
        }

        public Criteria andPoliceNameNotLike(String value) {
            addCriterion("police_name not like", value, "policeName");
            return (Criteria) this;
        }

        public Criteria andPoliceNameIn(List<String> values) {
            addCriterion("police_name in", values, "policeName");
            return (Criteria) this;
        }

        public Criteria andPoliceNameNotIn(List<String> values) {
            addCriterion("police_name not in", values, "policeName");
            return (Criteria) this;
        }

        public Criteria andPoliceNameBetween(String value1, String value2) {
            addCriterion("police_name between", value1, value2, "policeName");
            return (Criteria) this;
        }

        public Criteria andPoliceNameNotBetween(String value1, String value2) {
            addCriterion("police_name not between", value1, value2, "policeName");
            return (Criteria) this;
        }

        public Criteria andTargetBurnPathIsNull() {
            addCriterion("target_burn_path is null");
            return (Criteria) this;
        }

        public Criteria andTargetBurnPathIsNotNull() {
            addCriterion("target_burn_path is not null");
            return (Criteria) this;
        }

        public Criteria andTargetBurnPathEqualTo(String value) {
            addCriterion("target_burn_path =", value, "targetBurnPath");
            return (Criteria) this;
        }

        public Criteria andTargetBurnPathNotEqualTo(String value) {
            addCriterion("target_burn_path <>", value, "targetBurnPath");
            return (Criteria) this;
        }

        public Criteria andTargetBurnPathGreaterThan(String value) {
            addCriterion("target_burn_path >", value, "targetBurnPath");
            return (Criteria) this;
        }

        public Criteria andTargetBurnPathGreaterThanOrEqualTo(String value) {
            addCriterion("target_burn_path >=", value, "targetBurnPath");
            return (Criteria) this;
        }

        public Criteria andTargetBurnPathLessThan(String value) {
            addCriterion("target_burn_path <", value, "targetBurnPath");
            return (Criteria) this;
        }

        public Criteria andTargetBurnPathLessThanOrEqualTo(String value) {
            addCriterion("target_burn_path <=", value, "targetBurnPath");
            return (Criteria) this;
        }

        public Criteria andTargetBurnPathLike(String value) {
            addCriterion("target_burn_path like", value, "targetBurnPath");
            return (Criteria) this;
        }

        public Criteria andTargetBurnPathNotLike(String value) {
            addCriterion("target_burn_path not like", value, "targetBurnPath");
            return (Criteria) this;
        }

        public Criteria andTargetBurnPathIn(List<String> values) {
            addCriterion("target_burn_path in", values, "targetBurnPath");
            return (Criteria) this;
        }

        public Criteria andTargetBurnPathNotIn(List<String> values) {
            addCriterion("target_burn_path not in", values, "targetBurnPath");
            return (Criteria) this;
        }

        public Criteria andTargetBurnPathBetween(String value1, String value2) {
            addCriterion("target_burn_path between", value1, value2, "targetBurnPath");
            return (Criteria) this;
        }

        public Criteria andTargetBurnPathNotBetween(String value1, String value2) {
            addCriterion("target_burn_path not between", value1, value2, "targetBurnPath");
            return (Criteria) this;
        }

        public Criteria andStateIsNull() {
            addCriterion("state is null");
            return (Criteria) this;
        }

        public Criteria andStateIsNotNull() {
            addCriterion("state is not null");
            return (Criteria) this;
        }

        public Criteria andStateEqualTo(Integer value) {
            addCriterion("state =", value, "state");
            return (Criteria) this;
        }

        public Criteria andStateNotEqualTo(Integer value) {
            addCriterion("state <>", value, "state");
            return (Criteria) this;
        }

        public Criteria andStateGreaterThan(Integer value) {
            addCriterion("state >", value, "state");
            return (Criteria) this;
        }

        public Criteria andStateGreaterThanOrEqualTo(Integer value) {
            addCriterion("state >=", value, "state");
            return (Criteria) this;
        }

        public Criteria andStateLessThan(Integer value) {
            addCriterion("state <", value, "state");
            return (Criteria) this;
        }

        public Criteria andStateLessThanOrEqualTo(Integer value) {
            addCriterion("state <=", value, "state");
            return (Criteria) this;
        }

        public Criteria andStateIn(List<Integer> values) {
            addCriterion("state in", values, "state");
            return (Criteria) this;
        }

        public Criteria andStateNotIn(List<Integer> values) {
            addCriterion("state not in", values, "state");
            return (Criteria) this;
        }

        public Criteria andStateBetween(Integer value1, Integer value2) {
            addCriterion("state between", value1, value2, "state");
            return (Criteria) this;
        }

        public Criteria andStateNotBetween(Integer value1, Integer value2) {
            addCriterion("state not between", value1, value2, "state");
            return (Criteria) this;
        }

        public Criteria andPercentIsNull() {
            addCriterion("percent is null");
            return (Criteria) this;
        }

        public Criteria andPercentIsNotNull() {
            addCriterion("percent is not null");
            return (Criteria) this;
        }

        public Criteria andPercentEqualTo(Integer value) {
            addCriterion("percent =", value, "percent");
            return (Criteria) this;
        }

        public Criteria andPercentNotEqualTo(Integer value) {
            addCriterion("percent <>", value, "percent");
            return (Criteria) this;
        }

        public Criteria andPercentGreaterThan(Integer value) {
            addCriterion("percent >", value, "percent");
            return (Criteria) this;
        }

        public Criteria andPercentGreaterThanOrEqualTo(Integer value) {
            addCriterion("percent >=", value, "percent");
            return (Criteria) this;
        }

        public Criteria andPercentLessThan(Integer value) {
            addCriterion("percent <", value, "percent");
            return (Criteria) this;
        }

        public Criteria andPercentLessThanOrEqualTo(Integer value) {
            addCriterion("percent <=", value, "percent");
            return (Criteria) this;
        }

        public Criteria andPercentIn(List<Integer> values) {
            addCriterion("percent in", values, "percent");
            return (Criteria) this;
        }

        public Criteria andPercentNotIn(List<Integer> values) {
            addCriterion("percent not in", values, "percent");
            return (Criteria) this;
        }

        public Criteria andPercentBetween(Integer value1, Integer value2) {
            addCriterion("percent between", value1, value2, "percent");
            return (Criteria) this;
        }

        public Criteria andPercentNotBetween(Integer value1, Integer value2) {
            addCriterion("percent not between", value1, value2, "percent");
            return (Criteria) this;
        }

        public Criteria andIsDeletedIsNull() {
            addCriterion("is_deleted is null");
            return (Criteria) this;
        }

        public Criteria andIsDeletedIsNotNull() {
            addCriterion("is_deleted is not null");
            return (Criteria) this;
        }

        public Criteria andIsDeletedEqualTo(String value) {
            addCriterion("is_deleted =", value, "isDeleted");
            return (Criteria) this;
        }

        public Criteria andIsDeletedNotEqualTo(String value) {
            addCriterion("is_deleted <>", value, "isDeleted");
            return (Criteria) this;
        }

        public Criteria andIsDeletedGreaterThan(String value) {
            addCriterion("is_deleted >", value, "isDeleted");
            return (Criteria) this;
        }

        public Criteria andIsDeletedGreaterThanOrEqualTo(String value) {
            addCriterion("is_deleted >=", value, "isDeleted");
            return (Criteria) this;
        }

        public Criteria andIsDeletedLessThan(String value) {
            addCriterion("is_deleted <", value, "isDeleted");
            return (Criteria) this;
        }

        public Criteria andIsDeletedLessThanOrEqualTo(String value) {
            addCriterion("is_deleted <=", value, "isDeleted");
            return (Criteria) this;
        }

        public Criteria andIsDeletedLike(String value) {
            addCriterion("is_deleted like", value, "isDeleted");
            return (Criteria) this;
        }

        public Criteria andIsDeletedNotLike(String value) {
            addCriterion("is_deleted not like", value, "isDeleted");
            return (Criteria) this;
        }

        public Criteria andIsDeletedIn(List<String> values) {
            addCriterion("is_deleted in", values, "isDeleted");
            return (Criteria) this;
        }

        public Criteria andIsDeletedNotIn(List<String> values) {
            addCriterion("is_deleted not in", values, "isDeleted");
            return (Criteria) this;
        }

        public Criteria andIsDeletedBetween(String value1, String value2) {
            addCriterion("is_deleted between", value1, value2, "isDeleted");
            return (Criteria) this;
        }

        public Criteria andIsDeletedNotBetween(String value1, String value2) {
            addCriterion("is_deleted not between", value1, value2, "isDeleted");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIsNull() {
            addCriterion("create_time is null");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIsNotNull() {
            addCriterion("create_time is not null");
            return (Criteria) this;
        }

        public Criteria andCreateTimeEqualTo(Date value) {
            addCriterion("create_time =", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotEqualTo(Date value) {
            addCriterion("create_time <>", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeGreaterThan(Date value) {
            addCriterion("create_time >", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("create_time >=", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeLessThan(Date value) {
            addCriterion("create_time <", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeLessThanOrEqualTo(Date value) {
            addCriterion("create_time <=", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIn(List<Date> values) {
            addCriterion("create_time in", values, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotIn(List<Date> values) {
            addCriterion("create_time not in", values, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeBetween(Date value1, Date value2) {
            addCriterion("create_time between", value1, value2, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotBetween(Date value1, Date value2) {
            addCriterion("create_time not between", value1, value2, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateUserIsNull() {
            addCriterion("create_user is null");
            return (Criteria) this;
        }

        public Criteria andCreateUserIsNotNull() {
            addCriterion("create_user is not null");
            return (Criteria) this;
        }

        public Criteria andCreateUserEqualTo(String value) {
            addCriterion("create_user =", value, "createUser");
            return (Criteria) this;
        }

        public Criteria andCreateUserNotEqualTo(String value) {
            addCriterion("create_user <>", value, "createUser");
            return (Criteria) this;
        }

        public Criteria andCreateUserGreaterThan(String value) {
            addCriterion("create_user >", value, "createUser");
            return (Criteria) this;
        }

        public Criteria andCreateUserGreaterThanOrEqualTo(String value) {
            addCriterion("create_user >=", value, "createUser");
            return (Criteria) this;
        }

        public Criteria andCreateUserLessThan(String value) {
            addCriterion("create_user <", value, "createUser");
            return (Criteria) this;
        }

        public Criteria andCreateUserLessThanOrEqualTo(String value) {
            addCriterion("create_user <=", value, "createUser");
            return (Criteria) this;
        }

        public Criteria andCreateUserLike(String value) {
            addCriterion("create_user like", value, "createUser");
            return (Criteria) this;
        }

        public Criteria andCreateUserNotLike(String value) {
            addCriterion("create_user not like", value, "createUser");
            return (Criteria) this;
        }

        public Criteria andCreateUserIn(List<String> values) {
            addCriterion("create_user in", values, "createUser");
            return (Criteria) this;
        }

        public Criteria andCreateUserNotIn(List<String> values) {
            addCriterion("create_user not in", values, "createUser");
            return (Criteria) this;
        }

        public Criteria andCreateUserBetween(String value1, String value2) {
            addCriterion("create_user between", value1, value2, "createUser");
            return (Criteria) this;
        }

        public Criteria andCreateUserNotBetween(String value1, String value2) {
            addCriterion("create_user not between", value1, value2, "createUser");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeIsNull() {
            addCriterion("update_time is null");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeIsNotNull() {
            addCriterion("update_time is not null");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeEqualTo(Date value) {
            addCriterion("update_time =", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeNotEqualTo(Date value) {
            addCriterion("update_time <>", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeGreaterThan(Date value) {
            addCriterion("update_time >", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("update_time >=", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeLessThan(Date value) {
            addCriterion("update_time <", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeLessThanOrEqualTo(Date value) {
            addCriterion("update_time <=", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeIn(List<Date> values) {
            addCriterion("update_time in", values, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeNotIn(List<Date> values) {
            addCriterion("update_time not in", values, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeBetween(Date value1, Date value2) {
            addCriterion("update_time between", value1, value2, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeNotBetween(Date value1, Date value2) {
            addCriterion("update_time not between", value1, value2, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateUserIsNull() {
            addCriterion("update_user is null");
            return (Criteria) this;
        }

        public Criteria andUpdateUserIsNotNull() {
            addCriterion("update_user is not null");
            return (Criteria) this;
        }

        public Criteria andUpdateUserEqualTo(String value) {
            addCriterion("update_user =", value, "updateUser");
            return (Criteria) this;
        }

        public Criteria andUpdateUserNotEqualTo(String value) {
            addCriterion("update_user <>", value, "updateUser");
            return (Criteria) this;
        }

        public Criteria andUpdateUserGreaterThan(String value) {
            addCriterion("update_user >", value, "updateUser");
            return (Criteria) this;
        }

        public Criteria andUpdateUserGreaterThanOrEqualTo(String value) {
            addCriterion("update_user >=", value, "updateUser");
            return (Criteria) this;
        }

        public Criteria andUpdateUserLessThan(String value) {
            addCriterion("update_user <", value, "updateUser");
            return (Criteria) this;
        }

        public Criteria andUpdateUserLessThanOrEqualTo(String value) {
            addCriterion("update_user <=", value, "updateUser");
            return (Criteria) this;
        }

        public Criteria andUpdateUserLike(String value) {
            addCriterion("update_user like", value, "updateUser");
            return (Criteria) this;
        }

        public Criteria andUpdateUserNotLike(String value) {
            addCriterion("update_user not like", value, "updateUser");
            return (Criteria) this;
        }

        public Criteria andUpdateUserIn(List<String> values) {
            addCriterion("update_user in", values, "updateUser");
            return (Criteria) this;
        }

        public Criteria andUpdateUserNotIn(List<String> values) {
            addCriterion("update_user not in", values, "updateUser");
            return (Criteria) this;
        }

        public Criteria andUpdateUserBetween(String value1, String value2) {
            addCriterion("update_user between", value1, value2, "updateUser");
            return (Criteria) this;
        }

        public Criteria andUpdateUserNotBetween(String value1, String value2) {
            addCriterion("update_user not between", value1, value2, "updateUser");
            return (Criteria) this;
        }
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table burn_record
     *
     * @mbggenerated do_not_delete_during_merge Fri Apr 13 18:00:19 CST 2018
     */
    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table burn_record
     *
     * @mbggenerated Fri Apr 13 18:00:19 CST 2018
     */
    public static class Criterion {
        private String condition;

        private Object value;

        private Object secondValue;

        private boolean noValue;

        private boolean singleValue;

        private boolean betweenValue;

        private boolean listValue;

        private String typeHandler;

        public String getCondition() {
            return condition;
        }

        public Object getValue() {
            return value;
        }

        public Object getSecondValue() {
            return secondValue;
        }

        public boolean isNoValue() {
            return noValue;
        }

        public boolean isSingleValue() {
            return singleValue;
        }

        public boolean isBetweenValue() {
            return betweenValue;
        }

        public boolean isListValue() {
            return listValue;
        }

        public String getTypeHandler() {
            return typeHandler;
        }

        protected Criterion(String condition) {
            super();
            this.condition = condition;
            this.typeHandler = null;
            this.noValue = true;
        }

        protected Criterion(String condition, Object value, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.typeHandler = typeHandler;
            if (value instanceof List<?>) {
                this.listValue = true;
            } else {
                this.singleValue = true;
            }
        }

        protected Criterion(String condition, Object value) {
            this(condition, value, null);
        }

        protected Criterion(String condition, Object value, Object secondValue, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.secondValue = secondValue;
            this.typeHandler = typeHandler;
            this.betweenValue = true;
        }

        protected Criterion(String condition, Object value, Object secondValue) {
            this(condition, value, secondValue, null);
        }
    }
}