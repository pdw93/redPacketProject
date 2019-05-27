package controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import service.UserRedPacketService;

import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName UserRedPacketController
 * @Description TODO
 * @Author shnstt
 * @Date 2019/4/20 17:04
 * @Version 1.0
 **/
@Controller
@RequestMapping("/userRedPacket")
public class UserRedPacketController {
    @Autowired
    private UserRedPacketService userRedPacketService;

    @RequestMapping(value = "/grabRedPacket",method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> grabRedPacket(Long redPacketId, Long userId){
        int result = userRedPacketService.grabRedPacketForVersion(redPacketId, userId);
        Map<String,Object> retMap = new HashMap<>(2);
        boolean flag = result > 0;
        retMap.put("success",flag);
        retMap.put("message", flag ? "抢红包成功" : "抢红包失败");
        return retMap;
    }

    @RequestMapping(value = "/redPacketPage", method = RequestMethod.GET)
    public ModelAndView redPacketPage(){
        ModelAndView mv = new ModelAndView();
        mv.setViewName("grabRedPacketPage");
        return mv;
    }
}
