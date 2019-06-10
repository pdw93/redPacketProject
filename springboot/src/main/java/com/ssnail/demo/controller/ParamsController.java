package com.ssnail.demo.controller;

import com.ssnail.demo.controller.pojo.RoleParams;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

import java.util.List;

/**
 * @ClassName ParamsController
 * @Description 各种形式参数接收
 * @Author shnstt
 * @Date 2019/7/2 10:05
 * @Version 1.0
 **/
@Controller
@RequestMapping("/params")
@SessionAttributes(names = {"id", "roleName"}, types = {RoleParams.class})    //数据模型中对应的属性名称或者属性类型保存到HTTP的session对象中
public class ParamsController {

    /**
     * 从cookie和HTTP请求头中获取信息
     * @param userAgent
     * @param jsessionId     * @return
     * @return
     */
    @RequestMapping("/headerAndCookie")
    public String headerAndCookieParams(@RequestHeader(value = "User-Agent",required = false, defaultValue = "attribute") String userAgent, @CookieValue(value = "JSESSIONID",required = false,defaultValue = "MyJsessionId") String jsessionId){
        System.out.println("User-Agent->"+userAgent);
        System.out.println("JSESSIONID->"+jsessionId);
        return "index";
    }


    /**
     * 获取session的属性参数
     * @param id
     * @return
     */
    @RequestMapping("/sessionAttribute")
    public ModelAndView sessionAttr(@SessionAttribute(name = "id", required = false) Long id) {
        ModelAndView mv = new ModelAndView();
        mv.addObject("id", id);
        mv.setView(new MappingJackson2JsonView());
        return mv;
    }

    /**
     * 从HTTP的request对象中取属性参数
     *
     * @param id
     * @return
     */
    @RequestMapping("/requestAttribute")
    public ModelAndView requestAttr(@RequestAttribute(name = "id", required = false) Long id) {
        ModelAndView mv = new ModelAndView();
        mv.addObject("id", id);
        mv.setView(new MappingJackson2JsonView());
        return mv;
    }

    /**
     * URL重定向过程HTTP参数是以字符串形式传递，并不能有效传递
     * springMVC提供了一个方法--flash属性，需要提供一个数据模型RedirectAttribute，使用addFlashAttribute后
     * springMVC会将数据保存到session中，重定向后就会将其清除
     *
     * @param ra
     * @param roleParams
     * @return
     */
    @RequestMapping("redirectIndex3")
    public String addRole3(RedirectAttributes ra, RoleParams roleParams) {
        System.out.println("roleName->" + roleParams.getRoleName());
        ra.addFlashAttribute("roleParams", roleParams);
        return "redirect:./index.do";
    }

    /**
     * 通过返回视图和模型来实现重定向
     *
     * @param mv
     * @param roleName
     * @param note
     * @return
     */
    @RequestMapping("/redirectIndex2")
    public ModelAndView addRole2(ModelAndView mv, String roleName, String note) {
        mv.addObject(roleName);
        mv.addObject(note);
        mv.setViewName("redirect:./index.do");
        return mv;
    }

    /**
     * 通过返回字符串重定向
     *
     * @param model
     * @param roleName
     * @param note
     * @return
     */
    @RequestMapping("/redirectIndex")
    public String addRole(Model model, String roleName, String note) {
        System.out.println("roleName->" + roleName);
        model.addAttribute(roleName);
        model.addAttribute(note);
        return "redirect:./index.do";
    }

    /**
     * 接收序列化参数（roleName=xxx&&note=xxx）
     *
     * @param roleName
     * @param note
     * @return
     */
    @RequestMapping("/serializeParams")
    public ModelAndView serializeParams(String roleName, String note) {
        System.out.println("roleName->" + roleName);
        System.out.println("note->" + note);
        ModelAndView mv = new ModelAndView("index");
        return mv;
    }

    /**
     * 使用@RequestBody将json参数转化为列表数据
     *
     * @param roleParams
     * @return
     */
    @RequestMapping("/listParams")
    public ModelAndView listParams(@RequestBody List<RoleParams> roleParams) {
        System.out.println(roleParams.size());
        ModelAndView mv = new ModelAndView("index");
        return mv;
    }

    /**
     * 使用@RequestBody将json参数转化为pojo
     *
     * @param roleParams
     * @return
     */
    @RequestMapping("/jsonParams")
    public ModelAndView jsonPatams(@RequestBody RoleParams roleParams) {
        System.out.println("roleName->" + roleParams.getRoleName());
        System.out.println("note->" + roleParams.getNote());
        ModelAndView mv = new ModelAndView("index");
        return mv;
    }

    /**
     * 使用URL传递参数
     *
     * @param id
     * @return
     * @PathVariable允许参数为空
     */
    @RequestMapping("/getRole/{id}")
    public ModelAndView pathVariable(@PathVariable("id") Long id) {
        System.out.println("id->" + id);
        ModelAndView mv = new ModelAndView("index");
        return mv;
    }

    /**
     * 通过@requestParam接收参数，默认参数不能为空
     *
     * @param roleName
     * @param note
     * @return
     */
    @RequestMapping("/requestParam")
    public ModelAndView requestParam(@RequestParam(value = "role_name", required = false) String roleName, String note) {
        System.out.println("roleName->" + roleName);
        System.out.println("note->" + note);
        ModelAndView mv = new ModelAndView("index");
        return mv;
    }

    /**
     * 通过pojo接收参数（pojo属性要和HTTP请求参数保持一致）
     *
     * @param roleParams
     * @return
     */
    @RequestMapping("/commonParamsPojo")
    public ModelAndView commonParamsPojo(RoleParams roleParams) {
        System.out.println("roleName->" + roleParams.getRoleName());
        System.out.println("note->" + roleParams.getNote());
        ModelAndView mv = new ModelAndView("index");
        return mv;
    }

    /**
     * 无注解获取HTTP参数(参数名称和HTTP请求参数名一致)
     *
     * @param roleName
     * @param note
     * @return
     */
    @RequestMapping("/commonParams")
    public ModelAndView commonParams(String roleName, String note) {
        System.out.println("roleName->" + roleName);
        System.out.println("note->" + note);
        ModelAndView mv = new ModelAndView();
        mv.setViewName("index");
        return mv;
    }
}
