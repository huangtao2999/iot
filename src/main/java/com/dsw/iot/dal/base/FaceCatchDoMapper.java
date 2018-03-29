package com.dsw.iot.dal.base;

import com.dsw.iot.model.FaceCatchDo;
import com.dsw.iot.model.FaceCatchDoExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface FaceCatchDoMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table face_catch
     *
     * @mbggenerated Thu Mar 15 17:22:06 CST 2018
     */
    int countByExample(FaceCatchDoExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table face_catch
     *
     * @mbggenerated Thu Mar 15 17:22:06 CST 2018
     */
    int deleteByPrimaryKey(FaceCatchDo record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table face_catch
     *
     * @mbggenerated Thu Mar 15 17:22:06 CST 2018
     */
    int insert(FaceCatchDo record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table face_catch
     *
     * @mbggenerated Thu Mar 15 17:22:06 CST 2018
     */
    int insertSelective(FaceCatchDo record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table face_catch
     *
     * @mbggenerated Thu Mar 15 17:22:06 CST 2018
     */
    List<FaceCatchDo> selectByExample(FaceCatchDoExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table face_catch
     *
     * @mbggenerated Thu Mar 15 17:22:06 CST 2018
     */
    FaceCatchDo selectByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table face_catch
     *
     * @mbggenerated Thu Mar 15 17:22:06 CST 2018
     */
    int updateByExampleSelective(@Param("record") FaceCatchDo record, @Param("example") FaceCatchDoExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table face_catch
     *
     * @mbggenerated Thu Mar 15 17:22:06 CST 2018
     */
    int updateByExample(@Param("record") FaceCatchDo record, @Param("example") FaceCatchDoExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table face_catch
     *
     * @mbggenerated Thu Mar 15 17:22:06 CST 2018
     */
    int updateByPrimaryKeySelective(FaceCatchDo record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table face_catch
     *
     * @mbggenerated Thu Mar 15 17:22:06 CST 2018
     */
    int updateByPrimaryKey(FaceCatchDo record);
}