package com.dsw.iot.model;

import java.util.ArrayList;
import java.util.List;

import com.dsw.iot.util.PageDto;

public class SwytJjDoExample {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table SWYT_JJ
     *
     * @mbggenerated Wed May 02 13:36:13 CST 2018
     */
    protected String orderByClause;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table SWYT_JJ
     *
     * @mbggenerated Wed May 02 13:36:13 CST 2018
     */
    protected boolean distinct;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table SWYT_JJ
     *
     * @mbggenerated Wed May 02 13:36:13 CST 2018
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
     * This method corresponds to the database table SWYT_JJ
     *
     * @mbggenerated Wed May 02 13:36:13 CST 2018
     */
    public SwytJjDoExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table SWYT_JJ
     *
     * @mbggenerated Wed May 02 13:36:13 CST 2018
     */
    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table SWYT_JJ
     *
     * @mbggenerated Wed May 02 13:36:13 CST 2018
     */
    public String getOrderByClause() {
        return orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table SWYT_JJ
     *
     * @mbggenerated Wed May 02 13:36:13 CST 2018
     */
    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table SWYT_JJ
     *
     * @mbggenerated Wed May 02 13:36:13 CST 2018
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
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table SWYT_JJ
     *
     * @mbggenerated Wed May 02 13:36:13 CST 2018
     */
    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table SWYT_JJ
     *
     * @mbggenerated Wed May 02 13:36:13 CST 2018
     */
    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table SWYT_JJ
     *
     * @mbggenerated Wed May 02 13:36:13 CST 2018
     */
    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table SWYT_JJ
     *
     * @mbggenerated Wed May 02 13:36:13 CST 2018
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
     * This method corresponds to the database table SWYT_JJ
     *
     * @mbggenerated Wed May 02 13:36:13 CST 2018
     */
    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table SWYT_JJ
     *
     * @mbggenerated Wed May 02 13:36:13 CST 2018
     */
    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table SWYT_JJ
     *
     * @mbggenerated Wed May 02 13:36:13 CST 2018
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

        public Criteria andJjbhIsNull() {
            addCriterion("JJBH is null");
            return (Criteria) this;
        }

        public Criteria andJjbhIsNotNull() {
            addCriterion("JJBH is not null");
            return (Criteria) this;
        }

        public Criteria andJjbhEqualTo(String value) {
            addCriterion("JJBH =", value, "jjbh");
            return (Criteria) this;
        }

        public Criteria andJjbhNotEqualTo(String value) {
            addCriterion("JJBH <>", value, "jjbh");
            return (Criteria) this;
        }

        public Criteria andJjbhGreaterThan(String value) {
            addCriterion("JJBH >", value, "jjbh");
            return (Criteria) this;
        }

        public Criteria andJjbhGreaterThanOrEqualTo(String value) {
            addCriterion("JJBH >=", value, "jjbh");
            return (Criteria) this;
        }

        public Criteria andJjbhLessThan(String value) {
            addCriterion("JJBH <", value, "jjbh");
            return (Criteria) this;
        }

        public Criteria andJjbhLessThanOrEqualTo(String value) {
            addCriterion("JJBH <=", value, "jjbh");
            return (Criteria) this;
        }

        public Criteria andJjbhLike(String value) {
            addCriterion("JJBH like", value, "jjbh");
            return (Criteria) this;
        }

        public Criteria andJjbhNotLike(String value) {
            addCriterion("JJBH not like", value, "jjbh");
            return (Criteria) this;
        }

        public Criteria andJjbhIn(List<String> values) {
            addCriterion("JJBH in", values, "jjbh");
            return (Criteria) this;
        }

        public Criteria andJjbhNotIn(List<String> values) {
            addCriterion("JJBH not in", values, "jjbh");
            return (Criteria) this;
        }

        public Criteria andJjbhBetween(String value1, String value2) {
            addCriterion("JJBH between", value1, value2, "jjbh");
            return (Criteria) this;
        }

        public Criteria andJjbhNotBetween(String value1, String value2) {
            addCriterion("JJBH not between", value1, value2, "jjbh");
            return (Criteria) this;
        }

        public Criteria andJqbhIsNull() {
            addCriterion("JQBH is null");
            return (Criteria) this;
        }

        public Criteria andJqbhIsNotNull() {
            addCriterion("JQBH is not null");
            return (Criteria) this;
        }

        public Criteria andJqbhEqualTo(String value) {
            addCriterion("JQBH =", value, "jqbh");
            return (Criteria) this;
        }

        public Criteria andJqbhNotEqualTo(String value) {
            addCriterion("JQBH <>", value, "jqbh");
            return (Criteria) this;
        }

        public Criteria andJqbhGreaterThan(String value) {
            addCriterion("JQBH >", value, "jqbh");
            return (Criteria) this;
        }

        public Criteria andJqbhGreaterThanOrEqualTo(String value) {
            addCriterion("JQBH >=", value, "jqbh");
            return (Criteria) this;
        }

        public Criteria andJqbhLessThan(String value) {
            addCriterion("JQBH <", value, "jqbh");
            return (Criteria) this;
        }

        public Criteria andJqbhLessThanOrEqualTo(String value) {
            addCriterion("JQBH <=", value, "jqbh");
            return (Criteria) this;
        }

        public Criteria andJqbhLike(String value) {
            addCriterion("JQBH like", value, "jqbh");
            return (Criteria) this;
        }

        public Criteria andJqbhNotLike(String value) {
            addCriterion("JQBH not like", value, "jqbh");
            return (Criteria) this;
        }

        public Criteria andJqbhIn(List<String> values) {
            addCriterion("JQBH in", values, "jqbh");
            return (Criteria) this;
        }

        public Criteria andJqbhNotIn(List<String> values) {
            addCriterion("JQBH not in", values, "jqbh");
            return (Criteria) this;
        }

        public Criteria andJqbhBetween(String value1, String value2) {
            addCriterion("JQBH between", value1, value2, "jqbh");
            return (Criteria) this;
        }

        public Criteria andJqbhNotBetween(String value1, String value2) {
            addCriterion("JQBH not between", value1, value2, "jqbh");
            return (Criteria) this;
        }

        public Criteria andBjxsIsNull() {
            addCriterion("BJXS is null");
            return (Criteria) this;
        }

        public Criteria andBjxsIsNotNull() {
            addCriterion("BJXS is not null");
            return (Criteria) this;
        }

        public Criteria andBjxsEqualTo(String value) {
            addCriterion("BJXS =", value, "bjxs");
            return (Criteria) this;
        }

        public Criteria andBjxsNotEqualTo(String value) {
            addCriterion("BJXS <>", value, "bjxs");
            return (Criteria) this;
        }

        public Criteria andBjxsGreaterThan(String value) {
            addCriterion("BJXS >", value, "bjxs");
            return (Criteria) this;
        }

        public Criteria andBjxsGreaterThanOrEqualTo(String value) {
            addCriterion("BJXS >=", value, "bjxs");
            return (Criteria) this;
        }

        public Criteria andBjxsLessThan(String value) {
            addCriterion("BJXS <", value, "bjxs");
            return (Criteria) this;
        }

        public Criteria andBjxsLessThanOrEqualTo(String value) {
            addCriterion("BJXS <=", value, "bjxs");
            return (Criteria) this;
        }

        public Criteria andBjxsLike(String value) {
            addCriterion("BJXS like", value, "bjxs");
            return (Criteria) this;
        }

        public Criteria andBjxsNotLike(String value) {
            addCriterion("BJXS not like", value, "bjxs");
            return (Criteria) this;
        }

        public Criteria andBjxsIn(List<String> values) {
            addCriterion("BJXS in", values, "bjxs");
            return (Criteria) this;
        }

        public Criteria andBjxsNotIn(List<String> values) {
            addCriterion("BJXS not in", values, "bjxs");
            return (Criteria) this;
        }

        public Criteria andBjxsBetween(String value1, String value2) {
            addCriterion("BJXS between", value1, value2, "bjxs");
            return (Criteria) this;
        }

        public Criteria andBjxsNotBetween(String value1, String value2) {
            addCriterion("BJXS not between", value1, value2, "bjxs");
            return (Criteria) this;
        }

        public Criteria andJqlbIsNull() {
            addCriterion("JQLB is null");
            return (Criteria) this;
        }

        public Criteria andJqlbIsNotNull() {
            addCriterion("JQLB is not null");
            return (Criteria) this;
        }

        public Criteria andJqlbEqualTo(String value) {
            addCriterion("JQLB =", value, "jqlb");
            return (Criteria) this;
        }

        public Criteria andJqlbNotEqualTo(String value) {
            addCriterion("JQLB <>", value, "jqlb");
            return (Criteria) this;
        }

        public Criteria andJqlbGreaterThan(String value) {
            addCriterion("JQLB >", value, "jqlb");
            return (Criteria) this;
        }

        public Criteria andJqlbGreaterThanOrEqualTo(String value) {
            addCriterion("JQLB >=", value, "jqlb");
            return (Criteria) this;
        }

        public Criteria andJqlbLessThan(String value) {
            addCriterion("JQLB <", value, "jqlb");
            return (Criteria) this;
        }

        public Criteria andJqlbLessThanOrEqualTo(String value) {
            addCriterion("JQLB <=", value, "jqlb");
            return (Criteria) this;
        }

        public Criteria andJqlbLike(String value) {
            addCriterion("JQLB like", value, "jqlb");
            return (Criteria) this;
        }

        public Criteria andJqlbNotLike(String value) {
            addCriterion("JQLB not like", value, "jqlb");
            return (Criteria) this;
        }

        public Criteria andJqlbIn(List<String> values) {
            addCriterion("JQLB in", values, "jqlb");
            return (Criteria) this;
        }

        public Criteria andJqlbNotIn(List<String> values) {
            addCriterion("JQLB not in", values, "jqlb");
            return (Criteria) this;
        }

        public Criteria andJqlbBetween(String value1, String value2) {
            addCriterion("JQLB between", value1, value2, "jqlb");
            return (Criteria) this;
        }

        public Criteria andJqlbNotBetween(String value1, String value2) {
            addCriterion("JQLB not between", value1, value2, "jqlb");
            return (Criteria) this;
        }

        public Criteria andFxddIsNull() {
            addCriterion("FXDD is null");
            return (Criteria) this;
        }

        public Criteria andFxddIsNotNull() {
            addCriterion("FXDD is not null");
            return (Criteria) this;
        }

        public Criteria andFxddEqualTo(String value) {
            addCriterion("FXDD =", value, "fxdd");
            return (Criteria) this;
        }

        public Criteria andFxddNotEqualTo(String value) {
            addCriterion("FXDD <>", value, "fxdd");
            return (Criteria) this;
        }

        public Criteria andFxddGreaterThan(String value) {
            addCriterion("FXDD >", value, "fxdd");
            return (Criteria) this;
        }

        public Criteria andFxddGreaterThanOrEqualTo(String value) {
            addCriterion("FXDD >=", value, "fxdd");
            return (Criteria) this;
        }

        public Criteria andFxddLessThan(String value) {
            addCriterion("FXDD <", value, "fxdd");
            return (Criteria) this;
        }

        public Criteria andFxddLessThanOrEqualTo(String value) {
            addCriterion("FXDD <=", value, "fxdd");
            return (Criteria) this;
        }

        public Criteria andFxddLike(String value) {
            addCriterion("FXDD like", value, "fxdd");
            return (Criteria) this;
        }

        public Criteria andFxddNotLike(String value) {
            addCriterion("FXDD not like", value, "fxdd");
            return (Criteria) this;
        }

        public Criteria andFxddIn(List<String> values) {
            addCriterion("FXDD in", values, "fxdd");
            return (Criteria) this;
        }

        public Criteria andFxddNotIn(List<String> values) {
            addCriterion("FXDD not in", values, "fxdd");
            return (Criteria) this;
        }

        public Criteria andFxddBetween(String value1, String value2) {
            addCriterion("FXDD between", value1, value2, "fxdd");
            return (Criteria) this;
        }

        public Criteria andFxddNotBetween(String value1, String value2) {
            addCriterion("FXDD not between", value1, value2, "fxdd");
            return (Criteria) this;
        }

        public Criteria andJjrIsNull() {
            addCriterion("JJR is null");
            return (Criteria) this;
        }

        public Criteria andJjrIsNotNull() {
            addCriterion("JJR is not null");
            return (Criteria) this;
        }

        public Criteria andJjrEqualTo(String value) {
            addCriterion("JJR =", value, "jjr");
            return (Criteria) this;
        }

        public Criteria andJjrNotEqualTo(String value) {
            addCriterion("JJR <>", value, "jjr");
            return (Criteria) this;
        }

        public Criteria andJjrGreaterThan(String value) {
            addCriterion("JJR >", value, "jjr");
            return (Criteria) this;
        }

        public Criteria andJjrGreaterThanOrEqualTo(String value) {
            addCriterion("JJR >=", value, "jjr");
            return (Criteria) this;
        }

        public Criteria andJjrLessThan(String value) {
            addCriterion("JJR <", value, "jjr");
            return (Criteria) this;
        }

        public Criteria andJjrLessThanOrEqualTo(String value) {
            addCriterion("JJR <=", value, "jjr");
            return (Criteria) this;
        }

        public Criteria andJjrLike(String value) {
            addCriterion("JJR like", value, "jjr");
            return (Criteria) this;
        }

        public Criteria andJjrNotLike(String value) {
            addCriterion("JJR not like", value, "jjr");
            return (Criteria) this;
        }

        public Criteria andJjrIn(List<String> values) {
            addCriterion("JJR in", values, "jjr");
            return (Criteria) this;
        }

        public Criteria andJjrNotIn(List<String> values) {
            addCriterion("JJR not in", values, "jjr");
            return (Criteria) this;
        }

        public Criteria andJjrBetween(String value1, String value2) {
            addCriterion("JJR between", value1, value2, "jjr");
            return (Criteria) this;
        }

        public Criteria andJjrNotBetween(String value1, String value2) {
            addCriterion("JJR not between", value1, value2, "jjr");
            return (Criteria) this;
        }

        public Criteria andJjsjIsNull() {
            addCriterion("JJSJ is null");
            return (Criteria) this;
        }

        public Criteria andJjsjIsNotNull() {
            addCriterion("JJSJ is not null");
            return (Criteria) this;
        }

        public Criteria andJjsjEqualTo(String value) {
            addCriterion("JJSJ =", value, "jjsj");
            return (Criteria) this;
        }

        public Criteria andJjsjNotEqualTo(String value) {
            addCriterion("JJSJ <>", value, "jjsj");
            return (Criteria) this;
        }

        public Criteria andJjsjGreaterThan(String value) {
            addCriterion("JJSJ >", value, "jjsj");
            return (Criteria) this;
        }

        public Criteria andJjsjGreaterThanOrEqualTo(String value) {
            addCriterion("JJSJ >=", value, "jjsj");
            return (Criteria) this;
        }

        public Criteria andJjsjLessThan(String value) {
            addCriterion("JJSJ <", value, "jjsj");
            return (Criteria) this;
        }

        public Criteria andJjsjLessThanOrEqualTo(String value) {
            addCriterion("JJSJ <=", value, "jjsj");
            return (Criteria) this;
        }

        public Criteria andJjsjLike(String value) {
            addCriterion("JJSJ like", value, "jjsj");
            return (Criteria) this;
        }

        public Criteria andJjsjNotLike(String value) {
            addCriterion("JJSJ not like", value, "jjsj");
            return (Criteria) this;
        }

        public Criteria andJjsjIn(List<String> values) {
            addCriterion("JJSJ in", values, "jjsj");
            return (Criteria) this;
        }

        public Criteria andJjsjNotIn(List<String> values) {
            addCriterion("JJSJ not in", values, "jjsj");
            return (Criteria) this;
        }

        public Criteria andJjsjBetween(String value1, String value2) {
            addCriterion("JJSJ between", value1, value2, "jjsj");
            return (Criteria) this;
        }

        public Criteria andJjsjNotBetween(String value1, String value2) {
            addCriterion("JJSJ not between", value1, value2, "jjsj");
            return (Criteria) this;
        }

        public Criteria andJjdwIsNull() {
            addCriterion("JJDW is null");
            return (Criteria) this;
        }

        public Criteria andJjdwIsNotNull() {
            addCriterion("JJDW is not null");
            return (Criteria) this;
        }

        public Criteria andJjdwEqualTo(String value) {
            addCriterion("JJDW =", value, "jjdw");
            return (Criteria) this;
        }

        public Criteria andJjdwNotEqualTo(String value) {
            addCriterion("JJDW <>", value, "jjdw");
            return (Criteria) this;
        }

        public Criteria andJjdwGreaterThan(String value) {
            addCriterion("JJDW >", value, "jjdw");
            return (Criteria) this;
        }

        public Criteria andJjdwGreaterThanOrEqualTo(String value) {
            addCriterion("JJDW >=", value, "jjdw");
            return (Criteria) this;
        }

        public Criteria andJjdwLessThan(String value) {
            addCriterion("JJDW <", value, "jjdw");
            return (Criteria) this;
        }

        public Criteria andJjdwLessThanOrEqualTo(String value) {
            addCriterion("JJDW <=", value, "jjdw");
            return (Criteria) this;
        }

        public Criteria andJjdwLike(String value) {
            addCriterion("JJDW like", value, "jjdw");
            return (Criteria) this;
        }

        public Criteria andJjdwNotLike(String value) {
            addCriterion("JJDW not like", value, "jjdw");
            return (Criteria) this;
        }

        public Criteria andJjdwIn(List<String> values) {
            addCriterion("JJDW in", values, "jjdw");
            return (Criteria) this;
        }

        public Criteria andJjdwNotIn(List<String> values) {
            addCriterion("JJDW not in", values, "jjdw");
            return (Criteria) this;
        }

        public Criteria andJjdwBetween(String value1, String value2) {
            addCriterion("JJDW between", value1, value2, "jjdw");
            return (Criteria) this;
        }

        public Criteria andJjdwNotBetween(String value1, String value2) {
            addCriterion("JJDW not between", value1, value2, "jjdw");
            return (Criteria) this;
        }

        public Criteria andCjbsIsNull() {
            addCriterion("CJBS is null");
            return (Criteria) this;
        }

        public Criteria andCjbsIsNotNull() {
            addCriterion("CJBS is not null");
            return (Criteria) this;
        }

        public Criteria andCjbsEqualTo(String value) {
            addCriterion("CJBS =", value, "cjbs");
            return (Criteria) this;
        }

        public Criteria andCjbsNotEqualTo(String value) {
            addCriterion("CJBS <>", value, "cjbs");
            return (Criteria) this;
        }

        public Criteria andCjbsGreaterThan(String value) {
            addCriterion("CJBS >", value, "cjbs");
            return (Criteria) this;
        }

        public Criteria andCjbsGreaterThanOrEqualTo(String value) {
            addCriterion("CJBS >=", value, "cjbs");
            return (Criteria) this;
        }

        public Criteria andCjbsLessThan(String value) {
            addCriterion("CJBS <", value, "cjbs");
            return (Criteria) this;
        }

        public Criteria andCjbsLessThanOrEqualTo(String value) {
            addCriterion("CJBS <=", value, "cjbs");
            return (Criteria) this;
        }

        public Criteria andCjbsLike(String value) {
            addCriterion("CJBS like", value, "cjbs");
            return (Criteria) this;
        }

        public Criteria andCjbsNotLike(String value) {
            addCriterion("CJBS not like", value, "cjbs");
            return (Criteria) this;
        }

        public Criteria andCjbsIn(List<String> values) {
            addCriterion("CJBS in", values, "cjbs");
            return (Criteria) this;
        }

        public Criteria andCjbsNotIn(List<String> values) {
            addCriterion("CJBS not in", values, "cjbs");
            return (Criteria) this;
        }

        public Criteria andCjbsBetween(String value1, String value2) {
            addCriterion("CJBS between", value1, value2, "cjbs");
            return (Criteria) this;
        }

        public Criteria andCjbsNotBetween(String value1, String value2) {
            addCriterion("CJBS not between", value1, value2, "cjbs");
            return (Criteria) this;
        }

        public Criteria andBjrxmIsNull() {
            addCriterion("BJRXM is null");
            return (Criteria) this;
        }

        public Criteria andBjrxmIsNotNull() {
            addCriterion("BJRXM is not null");
            return (Criteria) this;
        }

        public Criteria andBjrxmEqualTo(String value) {
            addCriterion("BJRXM =", value, "bjrxm");
            return (Criteria) this;
        }

        public Criteria andBjrxmNotEqualTo(String value) {
            addCriterion("BJRXM <>", value, "bjrxm");
            return (Criteria) this;
        }

        public Criteria andBjrxmGreaterThan(String value) {
            addCriterion("BJRXM >", value, "bjrxm");
            return (Criteria) this;
        }

        public Criteria andBjrxmGreaterThanOrEqualTo(String value) {
            addCriterion("BJRXM >=", value, "bjrxm");
            return (Criteria) this;
        }

        public Criteria andBjrxmLessThan(String value) {
            addCriterion("BJRXM <", value, "bjrxm");
            return (Criteria) this;
        }

        public Criteria andBjrxmLessThanOrEqualTo(String value) {
            addCriterion("BJRXM <=", value, "bjrxm");
            return (Criteria) this;
        }

        public Criteria andBjrxmLike(String value) {
            addCriterion("BJRXM like", value, "bjrxm");
            return (Criteria) this;
        }

        public Criteria andBjrxmNotLike(String value) {
            addCriterion("BJRXM not like", value, "bjrxm");
            return (Criteria) this;
        }

        public Criteria andBjrxmIn(List<String> values) {
            addCriterion("BJRXM in", values, "bjrxm");
            return (Criteria) this;
        }

        public Criteria andBjrxmNotIn(List<String> values) {
            addCriterion("BJRXM not in", values, "bjrxm");
            return (Criteria) this;
        }

        public Criteria andBjrxmBetween(String value1, String value2) {
            addCriterion("BJRXM between", value1, value2, "bjrxm");
            return (Criteria) this;
        }

        public Criteria andBjrxmNotBetween(String value1, String value2) {
            addCriterion("BJRXM not between", value1, value2, "bjrxm");
            return (Criteria) this;
        }

        public Criteria andBjrxbIsNull() {
            addCriterion("BJRXB is null");
            return (Criteria) this;
        }

        public Criteria andBjrxbIsNotNull() {
            addCriterion("BJRXB is not null");
            return (Criteria) this;
        }

        public Criteria andBjrxbEqualTo(String value) {
            addCriterion("BJRXB =", value, "bjrxb");
            return (Criteria) this;
        }

        public Criteria andBjrxbNotEqualTo(String value) {
            addCriterion("BJRXB <>", value, "bjrxb");
            return (Criteria) this;
        }

        public Criteria andBjrxbGreaterThan(String value) {
            addCriterion("BJRXB >", value, "bjrxb");
            return (Criteria) this;
        }

        public Criteria andBjrxbGreaterThanOrEqualTo(String value) {
            addCriterion("BJRXB >=", value, "bjrxb");
            return (Criteria) this;
        }

        public Criteria andBjrxbLessThan(String value) {
            addCriterion("BJRXB <", value, "bjrxb");
            return (Criteria) this;
        }

        public Criteria andBjrxbLessThanOrEqualTo(String value) {
            addCriterion("BJRXB <=", value, "bjrxb");
            return (Criteria) this;
        }

        public Criteria andBjrxbLike(String value) {
            addCriterion("BJRXB like", value, "bjrxb");
            return (Criteria) this;
        }

        public Criteria andBjrxbNotLike(String value) {
            addCriterion("BJRXB not like", value, "bjrxb");
            return (Criteria) this;
        }

        public Criteria andBjrxbIn(List<String> values) {
            addCriterion("BJRXB in", values, "bjrxb");
            return (Criteria) this;
        }

        public Criteria andBjrxbNotIn(List<String> values) {
            addCriterion("BJRXB not in", values, "bjrxb");
            return (Criteria) this;
        }

        public Criteria andBjrxbBetween(String value1, String value2) {
            addCriterion("BJRXB between", value1, value2, "bjrxb");
            return (Criteria) this;
        }

        public Criteria andBjrxbNotBetween(String value1, String value2) {
            addCriterion("BJRXB not between", value1, value2, "bjrxb");
            return (Criteria) this;
        }

        public Criteria andBjrcsrqIsNull() {
            addCriterion("BJRCSRQ is null");
            return (Criteria) this;
        }

        public Criteria andBjrcsrqIsNotNull() {
            addCriterion("BJRCSRQ is not null");
            return (Criteria) this;
        }

        public Criteria andBjrcsrqEqualTo(String value) {
            addCriterion("BJRCSRQ =", value, "bjrcsrq");
            return (Criteria) this;
        }

        public Criteria andBjrcsrqNotEqualTo(String value) {
            addCriterion("BJRCSRQ <>", value, "bjrcsrq");
            return (Criteria) this;
        }

        public Criteria andBjrcsrqGreaterThan(String value) {
            addCriterion("BJRCSRQ >", value, "bjrcsrq");
            return (Criteria) this;
        }

        public Criteria andBjrcsrqGreaterThanOrEqualTo(String value) {
            addCriterion("BJRCSRQ >=", value, "bjrcsrq");
            return (Criteria) this;
        }

        public Criteria andBjrcsrqLessThan(String value) {
            addCriterion("BJRCSRQ <", value, "bjrcsrq");
            return (Criteria) this;
        }

        public Criteria andBjrcsrqLessThanOrEqualTo(String value) {
            addCriterion("BJRCSRQ <=", value, "bjrcsrq");
            return (Criteria) this;
        }

        public Criteria andBjrcsrqLike(String value) {
            addCriterion("BJRCSRQ like", value, "bjrcsrq");
            return (Criteria) this;
        }

        public Criteria andBjrcsrqNotLike(String value) {
            addCriterion("BJRCSRQ not like", value, "bjrcsrq");
            return (Criteria) this;
        }

        public Criteria andBjrcsrqIn(List<String> values) {
            addCriterion("BJRCSRQ in", values, "bjrcsrq");
            return (Criteria) this;
        }

        public Criteria andBjrcsrqNotIn(List<String> values) {
            addCriterion("BJRCSRQ not in", values, "bjrcsrq");
            return (Criteria) this;
        }

        public Criteria andBjrcsrqBetween(String value1, String value2) {
            addCriterion("BJRCSRQ between", value1, value2, "bjrcsrq");
            return (Criteria) this;
        }

        public Criteria andBjrcsrqNotBetween(String value1, String value2) {
            addCriterion("BJRCSRQ not between", value1, value2, "bjrcsrq");
            return (Criteria) this;
        }

        public Criteria andBjrsfzhIsNull() {
            addCriterion("BJRSFZH is null");
            return (Criteria) this;
        }

        public Criteria andBjrsfzhIsNotNull() {
            addCriterion("BJRSFZH is not null");
            return (Criteria) this;
        }

        public Criteria andBjrsfzhEqualTo(String value) {
            addCriterion("BJRSFZH =", value, "bjrsfzh");
            return (Criteria) this;
        }

        public Criteria andBjrsfzhNotEqualTo(String value) {
            addCriterion("BJRSFZH <>", value, "bjrsfzh");
            return (Criteria) this;
        }

        public Criteria andBjrsfzhGreaterThan(String value) {
            addCriterion("BJRSFZH >", value, "bjrsfzh");
            return (Criteria) this;
        }

        public Criteria andBjrsfzhGreaterThanOrEqualTo(String value) {
            addCriterion("BJRSFZH >=", value, "bjrsfzh");
            return (Criteria) this;
        }

        public Criteria andBjrsfzhLessThan(String value) {
            addCriterion("BJRSFZH <", value, "bjrsfzh");
            return (Criteria) this;
        }

        public Criteria andBjrsfzhLessThanOrEqualTo(String value) {
            addCriterion("BJRSFZH <=", value, "bjrsfzh");
            return (Criteria) this;
        }

        public Criteria andBjrsfzhLike(String value) {
            addCriterion("BJRSFZH like", value, "bjrsfzh");
            return (Criteria) this;
        }

        public Criteria andBjrsfzhNotLike(String value) {
            addCriterion("BJRSFZH not like", value, "bjrsfzh");
            return (Criteria) this;
        }

        public Criteria andBjrsfzhIn(List<String> values) {
            addCriterion("BJRSFZH in", values, "bjrsfzh");
            return (Criteria) this;
        }

        public Criteria andBjrsfzhNotIn(List<String> values) {
            addCriterion("BJRSFZH not in", values, "bjrsfzh");
            return (Criteria) this;
        }

        public Criteria andBjrsfzhBetween(String value1, String value2) {
            addCriterion("BJRSFZH between", value1, value2, "bjrsfzh");
            return (Criteria) this;
        }

        public Criteria andBjrsfzhNotBetween(String value1, String value2) {
            addCriterion("BJRSFZH not between", value1, value2, "bjrsfzh");
            return (Criteria) this;
        }
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table SWYT_JJ
     *
     * @mbggenerated do_not_delete_during_merge Wed May 02 13:36:13 CST 2018
     */
    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table SWYT_JJ
     *
     * @mbggenerated Wed May 02 13:36:13 CST 2018
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