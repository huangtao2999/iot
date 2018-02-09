package com.dsw.iot.controller.html;

import com.dsw.iot.model.DictionaryDo;
import com.dsw.iot.service.DictionaryService;
import com.dsw.iot.vo.DictionaryTreeVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/DictionaryHtml")
public class DictionaryHtml {

    @Autowired
    DictionaryService dictionaryService;

    @RequestMapping("/index")
    public String index(Model model) {
        //查询字典树
        // List<DictionaryTreeVo> dictionaryTreeVos =
        // dictionaryService.selectAllDictionary();
        // 采用动态加载，进页面加载根目录
        List<DictionaryTreeVo> dictionaryTreeVos = dictionaryService.selectDictionaryByPid((long) 0);
        model.addAttribute("dictionaryTree", dictionaryTreeVos);
        return "dictionary/index";
    }

    /**
     * 编辑当前节点
     *
     * @param model
     * @param id
     * @return
     */
    @RequestMapping("/editNode")
    public String editNode(Model model, Long id) {
        DictionaryDo dictionaryDo = new DictionaryDo();
        if (null != id) {
            dictionaryDo = dictionaryService.selectByPrimaryKey(id);
        }
        model.addAttribute("item", dictionaryDo);
        return "dictionary/addEdit";
    }

    /**
     * 新增同级/子级节点
     *
     * @param model
     * @param id
     * @return
     */
    @RequestMapping("/toAddTreeNode")
    public String toAddTreeChild(Model model, Long pid, String type) {
        DictionaryDo dictionaryDo = new DictionaryDo();
        dictionaryDo.setPid(pid);
        dictionaryDo.setType(type);
        // 放空对象，避免页面报错
        model.addAttribute("item", dictionaryDo);
        return "dictionary/addEdit";
    }


}
