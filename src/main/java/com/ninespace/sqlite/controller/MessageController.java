package com.ninespace.sqlite.controller;

import cn.hutool.json.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ninespace.sqlite.entity.Group;
import com.ninespace.sqlite.entity.OfflineMsg;
import com.ninespace.sqlite.mapper.OfflineMsgMapper;
import com.ninespace.sqlite.mapper.UserMapper;
import com.ninespace.sqlite.vo.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.swagger2.annotations.EnableSwagger2WebMvc;

import javax.websocket.Session;
import java.util.ArrayList;
import java.util.List;

@RestController
@Api(tags = "message")
@RequestMapping("/message")
public class MessageController {

    @Autowired
    private OfflineMsgMapper offlineMsgMapper;

    @ApiOperation(value = "获取未读消息")
    @GetMapping("/getUnMessage/{username}/{type}/{faceName}")
    public Result getUnMessage(
            @PathVariable String username,
            @PathVariable String faceName,
            @PathVariable Integer type

    ) {
        if (type == 1) {
            QueryWrapper<OfflineMsg> queryWrapper = new QueryWrapper<OfflineMsg>().eq("type",type).eq("fromId",faceName).eq("toId", username);
            List<OfflineMsg> offlineMsgs = offlineMsgMapper.selectList(queryWrapper);

            List<JSONObject> result = new ArrayList<>();

            if (offlineMsgs.size() != 0) {
                for (OfflineMsg msg : offlineMsgs) {
                    // 服务器端 再把消息组装一下，组装后的消息包含发送人和发送的文本内容
                    // {"from": "zhang", "text": "hello"}
                    JSONObject jsonObject = new JSONObject();
                    jsonObject.set("from", msg.getFromId());  // from 是 zhang
                    jsonObject.set("text", msg.getMsg());  // text 同上面的text
                    jsonObject.set("profile", msg.getProfile());  // text 同上面的text
                    //发送之后在本地不保留（销毁）
                    int i = offlineMsgMapper.deleteById(msg.getId());
                    result.add(jsonObject);
                }
                return Result.ok().data("data",result);
            }
        }
        else if(type == 2){
            QueryWrapper<OfflineMsg> queryWrapper = new QueryWrapper<OfflineMsg>().eq("type",type).eq("groupId",faceName).eq("toId", username);
            List<OfflineMsg> offlineMsgs = offlineMsgMapper.selectList(queryWrapper);

            List<JSONObject> result = new ArrayList<>();

            if (offlineMsgs.size() != 0) {
                for (OfflineMsg msg : offlineMsgs) {
                    // 服务器端 再把消息组装一下，组装后的消息包含发送人和发送的文本内容
                    // {"from": "zhang", "text": "hello"}
                    JSONObject jsonObject = new JSONObject();
                    jsonObject.set("from", msg.getFromId());  // from 是 zhang
                    jsonObject.set("text", msg.getMsg());  // text 同上面的text
                    jsonObject.set("profile", msg.getProfile());  // text 同上面的text
                    //发送之后在本地不保留（销毁）
                    int i = offlineMsgMapper.deleteById(msg.getId());
                    result.add(jsonObject);
                }
                return Result.ok().data("data",result);
            }
        }
        return Result.other();
    }
}