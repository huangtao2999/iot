package com.dsw.iot.dal.base;

import com.dsw.iot.model.RoleMenuDo;
import com.dsw.iot.model.RoleMenuDoExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface RoleMenuDoMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table role_menu
     *
     * @mbggenerated Thu Mar 01 11:47:52 CST 2018
     */
    int countByExample(RoleMenuDoExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table role_menu
     *
     * @mbggenerated Thu Mar 01 11:47:52 CST 2018
     */
    int deleteByPrimaryKey(RoleMenuDo record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table role_menu
     *
     * @mbggenerated Thu Mar 01 11:47:52 CST 2018
     */
    int insert(RoleMenuDo record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table role_menu
     *
     * @mbggenerated Thu Mar 01 11:47:52 CST 2018
     */
    int insertSelective(RoleMenuDo record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table role_menu
     *
     * @mbggenerated Thu Mar 01 11:47:52 CST 2018
     */
    List<RoleMenuDo> selectByExample(RoleMenuDoExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table role_menu
     *
     * @mbggenerated Thu Mar 01 11:47:52 CST 2018
     */
    RoleMenuDo selectByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table role_menu
     *
     * @mbggenerated Thu Mar 01 11:47:52 CST 2018
     */
    int updateByExampleSelective(@Param("record") RoleMenuDo record, @Param("example") RoleMenuDoExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table role_menu
     *
     * @mbggenerated Thu Mar 01 11:47:52 CST 2018
     */
    int updateByExample(@Param("record") RoleMenuDo record, @Param("example") RoleMenuDoExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table role_menu
     *
     * @mbggenerated Thu Mar 01 11:47:52 CST 2018
     */
    int updateByPrimaryKeySelective(RoleMenuDo record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table role_menu
     *
     * @mbggenerated Thu Mar 01 11:47:52 CST 2018
     */
    int updateByPrimaryKey(RoleMenuDo record);
}