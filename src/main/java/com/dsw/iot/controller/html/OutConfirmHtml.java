package com.dsw.iot.controller.html;

import com.dsw.iot.dto.GoodsRegisterRequest;
import com.dsw.iot.dto.PersonRegisterRequest;
import com.dsw.iot.model.OutConfirmDo;
import com.dsw.iot.model.UserDo;
import com.dsw.iot.service.GoodsRegisterService;
import com.dsw.iot.service.OutConfirmService;
import com.dsw.iot.service.PersonRegisterService;
import com.dsw.iot.util.CookieUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;


@Controller
@RequestMapping("/OutConfirmHtml")
public class OutConfirmHtml {

    @Autowired
    OutConfirmService outConfirmService;
    @Autowired
    PersonRegisterService personRegisterService;
    @Autowired
    GoodsRegisterService goodsRegisterService;

    /**
     * 人员出所审核首页
     *
     * @param model
     * @return
     */
    @RequestMapping("/index")
    public String index(Model model) {
        return "outConfirm/index";
    }

    /**
     * 新增界面
     *
     * @param model
     * @return
     */
    @RequestMapping("/add")
    public String add(Model model, Long registerId, HttpServletRequest request, HttpServletResponse response) {
        PersonRegisterRequest personRegisterRequest = new PersonRegisterRequest();
        List<GoodsRegisterRequest> goodsRegisterList = new ArrayList<GoodsRegisterRequest>();
        if (null != registerId) {
            goodsRegisterList = goodsRegisterService.getGoodsInfoByRid(registerId);
            personRegisterRequest = personRegisterService.getPersonRegisterInfo(registerId);
        }
        model.addAttribute("goodsRegisterList", goodsRegisterList);
        model.addAttribute("personRegisterDo", personRegisterRequest);

        UserDo userDo = CookieUtil.getUserFromCookie(request, response);
        userDo.setPassword(null);
        model.addAttribute("userDo", userDo);
        return "outConfirm/add";
    }

    /**
     * 编辑页面
     *
     * @param model
     * @return
     */
    @RequestMapping("/edit")
    public String edit(Model model, Long id, HttpServletRequest request, HttpServletResponse response) {
        OutConfirmDo outConfirmDo = new OutConfirmDo();
        PersonRegisterRequest personRegisterRequest = new PersonRegisterRequest();
        List<GoodsRegisterRequest> goodsRegisterList = new ArrayList<GoodsRegisterRequest>();
        if (null != id) {
            outConfirmDo = outConfirmService.getOutConfirm(id);
            goodsRegisterList = goodsRegisterService.getGoodsRegisterByOutId(id);
        }
        if (null != outConfirmDo.getRegisterId()) {
            personRegisterRequest = personRegisterService.getPersonRegisterInfo(outConfirmDo.getRegisterId());
        }

        model.addAttribute("outConfirmDo", outConfirmDo);
        model.addAttribute("personRegisterDo", personRegisterRequest);
        model.addAttribute("goodsRegisterList", goodsRegisterList);

        UserDo userDo = CookieUtil.getUserFromCookie(request, response);
        userDo.setPassword(null);
        model.addAttribute("userDo", userDo);
        return "outConfirm/edit";
    }

    /**
     * 查看详情
     *
     * @param model
     * @param id
     * @return
     */
    @RequestMapping("/detail")
    public String detail(Model model, Long id) {
        OutConfirmDo outConfirmDo = new OutConfirmDo();
        PersonRegisterRequest personRegisterRequest = new PersonRegisterRequest();
        List<GoodsRegisterRequest> goodsRegisterList = new ArrayList<GoodsRegisterRequest>();
        if (null != id) {
            outConfirmDo = outConfirmService.getOutConfirm(id);
            goodsRegisterList = goodsRegisterService.getGoodsRegisterByOutId(id);
        }
        if (null != outConfirmDo.getRegisterId()) {
            personRegisterRequest = personRegisterService.getPersonRegisterInfo(outConfirmDo.getRegisterId());
        }
        model.addAttribute("outConfirmDo", outConfirmDo);
        model.addAttribute("personRegisterDo", personRegisterRequest);
        model.addAttribute("goodsRegisterList", goodsRegisterList);
        return "outConfirm/detail";
    }
}
