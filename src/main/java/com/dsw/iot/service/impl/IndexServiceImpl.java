package com.dsw.iot.service.impl;

import com.dsw.iot.constant.CommConfig;
import com.dsw.iot.dal.PersonRegisterDoMapperExt;
import com.dsw.iot.dto.DictionaryRequest;
import com.dsw.iot.dto.IndexChartRequest;
import com.dsw.iot.model.DictionaryDo;
import com.dsw.iot.model.PersonRegisterDo;
import com.dsw.iot.service.*;
import com.dsw.iot.util.BizException;
import com.dsw.iot.util.DateUtil;
import com.dsw.iot.vo.IndexChartVo;
import com.dsw.iot.vo.IndexDelayVo;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Service
public class IndexServiceImpl implements IndexService {

    @Autowired(required = false)
    PersonRegisterDoMapperExt personRegisterDoMapperExt;
    @Autowired
    CurrentUserService currentUserService;
    @Autowired
    LogService logService;
    @Autowired
    DictionaryService dictionaryService;
    @Autowired
    FileUploadService fileUploadService;

    /**
     * 获得延期留置预警
     */
    @Override
    public List<IndexDelayVo> getDalayList() {
        // 定义参数
        List<IndexDelayVo> resList = new ArrayList<>();
        IndexDelayVo eightHour = new IndexDelayVo();
        eightHour.setTitle("入办案区8小时预警");
        eightHour.setType("8");
        IndexDelayVo twelveHour = new IndexDelayVo();
        twelveHour.setTitle("入办案区12小时预警");
        twelveHour.setType("12");
        IndexDelayVo twentyFourHour = new IndexDelayVo();
        twentyFourHour.setTitle("入办案区24小时预警");
        twentyFourHour.setType("24");
        IndexDelayVo fortyEightHour = new IndexDelayVo();
        fortyEightHour.setTitle("入办案区48小时预警");
        fortyEightHour.setType("48");
        // 获得所有需要延期的集合
        List<PersonRegisterDo> list = personRegisterDoMapperExt.selectDelayAlarmList();
        if (CollectionUtils.isNotEmpty(list)) {
            // 给每个预警对象填入数据
            for (PersonRegisterDo personRegisterDo : list) {
                String hour = personRegisterDo.getDelayHour();
                String name = personRegisterDo.getName();
                if ("8".equals(hour)) {
                    if (StringUtils.isBlank(eightHour.getNames())) {
                        eightHour.setNames(name);
                    } else {
                        eightHour.setNames(eightHour.getNames() + "," + name);
                    }
                } else if ("12".equals(hour)) {
                    if (StringUtils.isBlank(twelveHour.getNames())) {
                        twelveHour.setNames(name);
                    } else {
                        twelveHour.setNames(twelveHour.getNames() + "," + name);
                    }
                } else if ("24".equals(hour)) {
                    if (StringUtils.isBlank(twentyFourHour.getNames())) {
                        twentyFourHour.setNames(name);
                    } else {
                        twentyFourHour.setNames(twentyFourHour.getNames() + "," + name);
                    }
                } else if ("48".equals(hour)) {
                    if (StringUtils.isBlank(fortyEightHour.getNames())) {
                        fortyEightHour.setNames(name);
                    } else {
                        fortyEightHour.setNames(fortyEightHour.getNames() + "," + name);
                    }
                }
            }
        }
        // 返回
        resList.add(eightHour);
        resList.add(twelveHour);
        resList.add(twentyFourHour);
        resList.add(fortyEightHour);
        return resList;
    }

    /**
     * 获得延期留置预警 单个
     */
    @Override
    public List<IndexDelayVo> getDelayAlarmSingle(int delayHour) {
        // 定义参数
        List<IndexDelayVo> res = new ArrayList<>();
        // 获得所有需要延期的集合
        List<PersonRegisterDo> list = personRegisterDoMapperExt.selectDelayAlarmSingle(delayHour);
        if (CollectionUtils.isNotEmpty(list)) {
            // 给每个预警对象填入数据
            IndexDelayVo iVo = null;
            for (PersonRegisterDo personRegisterDo : list) {
                iVo = new IndexDelayVo();
                BeanUtils.copyProperties(personRegisterDo, iVo);
//                String personImgId = fileUploadService.getAttachIds(personRegisterDo.getId(), CommConfig.ATTACH_TYPE.PERSON_REGISTER_PERSON_IMGS.getType());
//                iVo.setPersonImgId(personImgId);
                res.add(iVo);
            }
        }
        return res;
    }

    /**
     * 获得当天前包括当天共7天，一周的入办案区人数统计
     */
    @Override
    public IndexChartVo getSevenDayCount(IndexChartRequest request) {
        IndexChartVo res = new IndexChartVo();// 返回实体
        String weeks = "";
        String datas = "";
        Date today = new Date();
        String[] weekDays = {"周日", "周一", "周二", "周三", "周四", "周五", "周六"};
        String[] resNames = new String[7];
        String[] resDatas = new String[7];
        if (null == request.getCountDate()) {
            request.setCountDate(today);
        }
        Calendar c = Calendar.getInstance();
        c.setTime(request.getCountDate());
        int weekDay = c.get(Calendar.DAY_OF_WEEK) - 1;// 今天是周几，周天-1，周一-1
        List<IndexChartVo> list = personRegisterDoMapperExt.selectOneWeekCount(request);
        String temp = "";
        int j = 6;
        for (int i = 0; i < 7; i++) {
            temp = "0";
            for (IndexChartVo chartVo : list) {
                // 拼接分数
                if (chartVo.getInWeek() == weekDay) {
                    resDatas[j] = chartVo.getCnt() + "";
                    temp = "0";
                    break;
                } else {
                    temp = "1";
                }
            }
            // 如果是7，就指星期天，在数组里是0
            if (weekDay > 6) {
                weekDay = 0;
            } else if (weekDay < 0) {
                weekDay = 6;
            }
            // 拼接日期
            resNames[j] = weekDays[weekDay];
            if ("1".equals(temp)) {
                resDatas[j] = 0 + "";
            }
            j--;
            weekDay--;
        }
        for (int m = 0; m < resNames.length; m++) {
            if (StringUtils.isBlank(weeks)) {
                weeks = resNames[m];
                datas = resDatas[m];
            } else {
                weeks += "," + resNames[m];
                datas += "," + resDatas[m];
            }
        }
        res.setWeeks(weeks);
        res.setDatas(datas);
        return res;
    }

    /**
     * 拼接字典图表返回数据
     *
     * @param request
     * @return
     * @throws BizException
     */
    private IndexChartVo getChartTypeVo(IndexChartVo indexChartVo, List<IndexChartVo> list,
                                        String code) throws BizException {
        DictionaryRequest dictionaryRequest = new DictionaryRequest();
        dictionaryRequest.setCode(code);
        List<DictionaryDo> dictionaryDos = dictionaryService.queryComboList(dictionaryRequest);
        String types = "";
        String datas = "";
        String names = "";
        String temp = "0";
        if (CollectionUtils.isNotEmpty(dictionaryDos)) {
            for (DictionaryDo dictionaryDo : dictionaryDos) {
                if (CollectionUtils.isNotEmpty(list)) {
                    for (IndexChartVo chartVo : list) {
                        temp = "1";
                        if (null != chartVo.getInType()) {
                            if (chartVo.getInType().equals(dictionaryDo.getCode())) {
                                if (StringUtils.isBlank(types)) {
                                    names = dictionaryDo.getName();
                                    types = chartVo.getInType();
                                    datas = chartVo.getCnt() + "";
                                } else {
                                    names += "," + dictionaryDo.getName();
                                    types += "," + chartVo.getInType();
                                    datas += "," + chartVo.getCnt();
                                }
                                temp = "0";
                                break;
                            }
                        }
                    }
                    if ("1".equals(temp)) {
                        // 拼接名称和数量
                        if (StringUtils.isBlank(names)) {
                            names = dictionaryDo.getName();
                            types = dictionaryDo.getCode();
                            datas = 0 + "";
                        } else {
                            names += "," + dictionaryDo.getName();
                            types += "," + dictionaryDo.getCode();
                            datas += "," + 0;
                        }
                    }
                } else {
                    // 拼接名称和数量
                    if (StringUtils.isBlank(names)) {
                        names = dictionaryDo.getName();
                        types = dictionaryDo.getCode();
                        datas = 0 + "";
                    } else {
                        names += "," + dictionaryDo.getName();
                        types += "," + dictionaryDo.getCode();
                        datas += "," + 0;
                    }
                }

            }
        }
        indexChartVo.setDatas(datas);
        indexChartVo.setNames(names);
        indexChartVo.setTypes(types);
        return indexChartVo;
    }

    /**
     * 日期区间，[a,b)
     */
    @Override
    public IndexChartVo getInTypeDateBetweenCount(IndexChartRequest request) throws BizException {
        IndexChartVo indexChartVo = new IndexChartVo();
        if (null == request.getStartDate() || null == request.getEndDate()) {
            return getInTypeWeekCount(request);
        }
        List<IndexChartVo> list = personRegisterDoMapperExt.selectInTypeDateBetweenCount(request);
        getChartTypeVo(indexChartVo, list, "IN_TYPE");
        indexChartVo.setInTime(request.getInWeek());
        return indexChartVo;
    }

    /**
     * 周到案方式统计，传入格式201701，17年第一周
     *
     * @throws BizException
     */
    @Override
    public IndexChartVo getInTypeWeekCount(IndexChartRequest request) throws BizException {
        IndexChartVo indexChartVo = new IndexChartVo();
        if (StringUtils.isBlank(request.getInWeek())) {
            request.setInWeek(DateUtil.getWeekOfYear());
        }
        List<IndexChartVo> list = personRegisterDoMapperExt.selectInTypeWeekCount(request);
        getChartTypeVo(indexChartVo, list, "IN_TYPE");
        indexChartVo.setInTime(request.getInWeek());
        return indexChartVo;
    }

    /**
     * 月到案方式统计
     *
     * @throws BizException
     */
    @Override
    public IndexChartVo getInTypeMonthCount(IndexChartRequest request) throws BizException {
        IndexChartVo indexChartVo = new IndexChartVo();
        if (StringUtils.isBlank(request.getInMonth())) {
            request.setInMonth(DateUtil.getYearMonth());
        }
        List<IndexChartVo> list = personRegisterDoMapperExt.selectInTypeMonthCount(request);
        getChartTypeVo(indexChartVo, list, "IN_TYPE");
        indexChartVo.setInTime(request.getInMonth());
        return indexChartVo;
    }

    /**
     * 年到案方式统计
     *
     * @throws BizException
     */
    @Override
    public IndexChartVo getInTypeYearCount(IndexChartRequest request) throws BizException {
        IndexChartVo indexChartVo = new IndexChartVo();
        if (StringUtils.isBlank(request.getInYear())) {
            request.setInYear(DateUtil.getYear());
        }
        List<IndexChartVo> list = personRegisterDoMapperExt.selectInTypeYearCount(request);
        getChartTypeVo(indexChartVo, list, "IN_TYPE");
        indexChartVo.setInTime(request.getInYear());
        return indexChartVo;
    }

    /**
     * 人员类型区间统计 [a,b)
     *
     * @param request
     * @return
     * @throws BizException
     */
    @Override
    public IndexChartVo getPersonTypeDateBetweenCount(IndexChartRequest request) throws BizException {
        IndexChartVo indexChartVo = new IndexChartVo();
        if (null == request.getStartDate() || null == request.getEndDate()) {
            return getPersonTypeWeekCount(request);
        }
       /* List<IndexChartVo> list = personRegisterDoMapperExt.selectPersonTypeDateBetweenCount(request);
        getChartTypeVo(indexChartVo, list, "PER_INFO_POLITICAL");*/
        
        List<IndexChartVo> list = personRegisterDoMapperExt.selectPersonTypeDateBetweenCountTwo(request);
        getChartTypeVoTwo(indexChartVo, list);
        indexChartVo.setInTime(request.getInWeek());
        return indexChartVo;
    }

    /**
     * 周人员类型PER_INFO_POLITICAL统计，传入格式201701，17年第一周
     *
     * @throws BizException
     */
    @Override
    public IndexChartVo getPersonTypeWeekCount(IndexChartRequest request) throws BizException {
        IndexChartVo indexChartVo = new IndexChartVo();
        if (StringUtils.isBlank(request.getInWeek())) {
            request.setInWeek(DateUtil.getWeekOfYear());
        }
/*        List<IndexChartVo> list = personRegisterDoMapperExt.selectPersonTypeWeekCount(request);
        getChartTypeVo(indexChartVo, list, "PER_INFO_POLITICAL");*/
        
        List<IndexChartVo> list = personRegisterDoMapperExt.selectPersonTypeWeekCountTwo(request);
        getChartTypeVoTwo(indexChartVo, list);
        indexChartVo.setInTime(request.getInWeek());
        return indexChartVo;
    }

    /**
     * 月人员类型PER_INFO_POLITICAL统计
     *
     * @throws BizException
     */
    @Override
    public IndexChartVo getPersonTypeMonthCount(IndexChartRequest request) throws BizException {
        IndexChartVo indexChartVo = new IndexChartVo();
        if (StringUtils.isBlank(request.getInMonth())) {
            request.setInMonth(DateUtil.getYearMonth());
        }
/*        List<IndexChartVo> list = personRegisterDoMapperExt.selectPersonTypeMonthCount(request);
        getChartTypeVo(indexChartVo, list, "PER_INFO_POLITICAL");*/
        
        List<IndexChartVo> list = personRegisterDoMapperExt.selectPersonTypeMonthCountTwo(request);
        getChartTypeVoTwo(indexChartVo, list);
        indexChartVo.setInTime(request.getInMonth());
        return indexChartVo;
    }

    /**
     * 年人员类型PER_INFO_POLITICAL统计
     *
     * @throws BizException
     */
    @Override
    public IndexChartVo getPersonTypeYearCount(IndexChartRequest request) throws BizException {
        IndexChartVo indexChartVo = new IndexChartVo();
        if (StringUtils.isBlank(request.getInYear())) {
            request.setInYear(DateUtil.getYear());
        }
      /*  List<IndexChartVo> list = personRegisterDoMapperExt.selectPersonTypeYearCount(request);
        getChartTypeVo(indexChartVo, list, "PER_INFO_POLITICAL");*/
        
        List<IndexChartVo> list = personRegisterDoMapperExt.selectPersonTypeYearCountTwo(request);
        getChartTypeVoTwo(indexChartVo, list);
        indexChartVo.setInTime(request.getInYear());
        return indexChartVo;
    }
    
    
    /**
     * 手动拼接返回数据
     *
     * @param request
     * @return
     * @throws BizException
     */
    private IndexChartVo getChartTypeVoTwo(IndexChartVo indexChartVo, List<IndexChartVo> list) throws BizException {
     
        List<DictionaryDo> dictionaryDos = new ArrayList<DictionaryDo>();
    	DictionaryDo doo = new DictionaryDo();
    	doo.setCode("401");doo.setName("人大代表");
    	dictionaryDos.add(doo);
    	DictionaryDo doo1 = new DictionaryDo();
    	doo1.setCode("404"); doo1.setName("公务员");
    	dictionaryDos.add(doo1);
    	DictionaryDo doo2 = new DictionaryDo();
    	doo2.setCode("402"); doo2.setName("政协委员");
    	dictionaryDos.add(doo2);
    	DictionaryDo doo3 = new DictionaryDo();
    	doo3.setCode("412"); doo3.setName("司法人员");
    	dictionaryDos.add(doo3);
    	DictionaryDo doo4 = new DictionaryDo();
    	doo4.setCode("418"); doo4.setName("外国人");
    	dictionaryDos.add(doo4);
    	DictionaryDo doo5 = new DictionaryDo();
    	doo5.setCode("650"); doo5.setName("其他");
    	dictionaryDos.add(doo5);
        
        String types = "";
        String datas = "";
        String names = "";
        String temp = "0";
        if (CollectionUtils.isNotEmpty(dictionaryDos)) {
            for (DictionaryDo dictionaryDo : dictionaryDos) {
                if (CollectionUtils.isNotEmpty(list)) {
                    for (IndexChartVo chartVo : list) {
                        temp = "1";
                        if (null != chartVo.getInType()) {
                            if (chartVo.getInType().equals(dictionaryDo.getCode())) {
                                if (StringUtils.isBlank(types)) {
                                    names = dictionaryDo.getName();
                                    types = chartVo.getInType();
                                    datas = chartVo.getCnt() + "";
                                } else {
                                    names += "," + dictionaryDo.getName();
                                    types += "," + chartVo.getInType();
                                    datas += "," + chartVo.getCnt();
                                }
                                temp = "0";
                                break;
                            }
                        }
                    }
                    if ("1".equals(temp)) {
                        // 拼接名称和数量
                        if (StringUtils.isBlank(names)) {
                            names = dictionaryDo.getName();
                            types = dictionaryDo.getCode();
                            datas = 0 + "";
                        } else {
                            names += "," + dictionaryDo.getName();
                            types += "," + dictionaryDo.getCode();
                            datas += "," + 0;
                        }
                    }
                } else {
                    // 拼接名称和数量
                    if (StringUtils.isBlank(names)) {
                        names = dictionaryDo.getName();
                        types = dictionaryDo.getCode();
                        datas = 0 + "";
                    } else {
                        names += "," + dictionaryDo.getName();
                        types += "," + dictionaryDo.getCode();
                        datas += "," + 0;
                    }
                }

            }
        }
        indexChartVo.setDatas(datas);
        indexChartVo.setNames(names);
        indexChartVo.setTypes(types);
        return indexChartVo;
    }
}
