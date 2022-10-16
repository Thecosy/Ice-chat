package com.ninespace.sqlite.configuration;

import cn.hutool.extra.spring.SpringUtil;
import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ninespace.sqlite.entity.Group;
import com.ninespace.sqlite.entity.OfflineMsg;
import com.ninespace.sqlite.entity.User;
import com.ninespace.sqlite.mapper.GroupMapper;
import com.ninespace.sqlite.mapper.OfflineMsgMapper;
import com.ninespace.sqlite.mapper.UserMapper;
import com.ninespace.sqlite.service.OfflineMsgService;
import com.ninespace.sqlite.util.RedisUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.swing.plaf.basic.BasicDesktopIconUI;
import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;


/**
 * @author websocket服务
 */
@ServerEndpoint(value = "/imserver/{username}")
@Component
public class WebSocketServer {

    /**
    由于是自己的手动实例化的，因此实例化的heatMapService对象肯定是不在Spring容器中的，
     根据Spring的“依赖注入”，自然也无法将HeatMapMapper注入到Spring容器中。
     知道问题所在，那就把HeatMapService通过Spring的方式注入进容器即可，问题二解决。

     问题二解决了，自然问题一又回来了，为什么WebSocketServlet 中无法注入HeatMapService呢 ？
     原来WebSocket是多例的，而Spring管理的对象默认是单例的，即Spring默认只实例化一次HeatMapService，
     而WebSocketServlet 每新添加一个连接就会新增一个socket对象，自然无法将每一个socket对象中
     的HeatMapService进行实例化注入。解决的办法就是将HeatMapService由成员变量，变成类变量即可，
     让所有的socket对象共享同一个HeatMapService。
     */

    private static OfflineMsgService offlineMsgService;

    @Autowired
    public void setOfflineMsgService(OfflineMsgService offlineMsgService) {
        WebSocketServer.offlineMsgService = offlineMsgService;
    }

    private static OfflineMsgMapper offlineMsgMapper;

    @Autowired
    public void setOfflineMsgMapper(OfflineMsgMapper offlineMsgMapper) {
        WebSocketServer.offlineMsgMapper = offlineMsgMapper;
    }

    private static UserMapper userMapper;

    @Autowired
    public void setUserMapper(UserMapper userMapper) {
        WebSocketServer.userMapper = userMapper;
    }

    private static GroupMapper groupMapper;

    @Autowired
    public void setGroupMapper(GroupMapper groupMapper) {
        WebSocketServer.groupMapper = groupMapper;
    }


    @Autowired
    private RedisUtil redisUtil = SpringUtil.getBean(RedisUtil.class);

    private static final Logger log = LoggerFactory.getLogger(WebSocketServer.class);

    /**
     * 记录当前在线连接数
     */
    public static final Map<String, Session> sessionMap = new ConcurrentHashMap<>();

    /**
     * 连接建立成功调用的方法
     */
    @OnOpen
    public void onOpen(Session session, @PathParam("username") String username) {
        sessionMap.put(username, session);

        log.info("有新用户加入，username={}, 当前在线人数为：{}", username, sessionMap.size());
//
//        QueryWrapper<OfflineMsg> queryWrapper = new QueryWrapper<OfflineMsg>().eq("toId", username);
//        List<OfflineMsg> offlineMsgs = offlineMsgMapper.selectList(queryWrapper);
//        if (offlineMsgs.size()!=0){
//            log.info("用户"+username+"上线,推送离线消息");
//            for (OfflineMsg msg : offlineMsgs) {
//                Session toSession = sessionMap.get(msg.getToId()); // 根据 to用户名来获取 session，再通过session发送消息文本
//                // 服务器端 再把消息组装一下，组装后的消息包含发送人和发送的文本内容
//                // {"from": "zhang", "text": "hello"}
//                JSONObject jsonObject = new JSONObject();
//                jsonObject.set("from", msg.getFromId());  // from 是 zhang
//                jsonObject.set("text", msg.getMsg());  // text 同上面的text
//                jsonObject.set("profile", msg.getProfile());  // text 同上面的text
//
//                this.sendMessage(jsonObject.toString(), toSession);
//                //发送之后在本地不保留（销毁）
//                int i = offlineMsgMapper.deleteById(msg.getId());
//                log.info("发送给用户username={}，消息：{}", msg.getToId(), jsonObject.toString());
//            }
//        }

        JSONObject result = new JSONObject();
        JSONArray array = new JSONArray();
        result.set("users", array);
        for (Object key : sessionMap.keySet()) {
            JSONObject jsonObject = new JSONObject();
            jsonObject.set("username", key);
            // {"username", "zhang", "username": "admin"}
            array.add(jsonObject);
        }
//        {"users": [{"username": "zhang"},{ "username": "admin"}]}
        sendAllMessage(JSONUtil.toJsonStr(result));  // 后台发送消息给所有的客户端
    }

    /**
     * 连接关闭调用的方法
     */
    @OnClose
    public void onClose(Session session, @PathParam("username") String username) {
        sessionMap.remove(username);
        log.info("有一连接关闭，移除username={}的用户session, 当前在线人数为：{}", username, sessionMap.size());
    }

    /**
     * 收到客户端消息后调用的方法
     * 后台收到客户端发送过来的消息
     * onMessage 是一个消息的中转站
     * 接受 浏览器端 socket.send 发送过来的 json数据
     * @param message 客户端发送过来的消息
     */
    @OnMessage
    public void onMessage(String message, Session session, @PathParam("username") String username) {
        log.info("服务端收到用户username={}的消息:{}", username, message);
        JSONObject obj = JSONUtil.parseObj(message);

        //redis存储消息key为id
//        SimpleDateFormat df = new SimpleDateFormat("yyyyMMddhhmmss");
//        String date=df.format(new Date());
//        System.out.println("Redis:"+date);
//        redisUtil.set(date,message);
//        System.out.println(date);
//        System.out.println(redisUtil.get(date));

        String from = obj.getStr("from"); // to表示发送给哪个用户，比如 admin
        String toUsername = obj.getStr("to"); // to表示发送给哪个用户，比如 admin
        String text = obj.getStr("text"); // 发送的消息文本  hello
        String type = obj.getStr("type"); //消息类型 1. 单聊 2.群聊
        if(type.equals("1")) {
            // {"to": "admin", "text": "聊天文本"}
            Session toSession = sessionMap.get(toUsername); // 根据 to用户名来获取 session，再通过session发送消息文本
            if (toSession != null) {
                // 服务器端 再把消息组装一下，组装后的消息包含发送人和发送的文本内容
                // {"from": "zhang", "text": "hello"}
                JSONObject jsonObject = new JSONObject();

                //根据id查找用户头像追加
//            User user = userMapper.selectById(toUsername);
//            String profile = user.getProfile();

                jsonObject.set("from", username);  // from 是 zhang
                jsonObject.set("text", text);  // text 同上面的text
//                jsonObject.set("id", username);  // id 消息的目标用户，主要作用：判断是群聊还是消息

//            jsonObject.set("profile", profile);
                if (toSession == session) {
                    //是自己，忽略
                    return;
                }
                this.sendMessage(jsonObject.toString(), toSession);
                log.info("发送给用户username={}，消息：{}", toUsername, jsonObject.toString());
            } else {

                //Session为空代表用户离线，储存消息到本地
                OfflineMsg offlineMsg = new OfflineMsg();

                SimpleDateFormat df = new SimpleDateFormat("yyyyMMddhhmmss");
                String date = df.format(new Date());
                System.out.println("sqlite:" + date);

                offlineMsg.setId(date);
                offlineMsg.setFromId(username);
                offlineMsg.setToId(toUsername);
                offlineMsg.setMsg(text);
                offlineMsg.setType(1);

                Date d = new Date();
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                //利用创建好的格式对象格式化当前的时间。把当前时间d格式化成我们想要的格式
                String str = sdf.format(d); // 输出的时间（str）就是按照我们规定的时间格式：yyyy/MM/dd hh:mm:ss输出

                offlineMsg.setTime(str);
                log.info("将消息存入数据库" + offlineMsg);
                boolean save = offlineMsgService.save(offlineMsg);
                if (save) {
                    log.info("用户" + toUsername + "离线,将消息存入数据库");
                }

                //log.info("发送失败，未找到用户username={}的session", toUsername);
            }
        } else if(type.equals("2")) {
            // {"to": "admin", "text": "聊天文本"}
            //toUsername 是群号码 先找出这个群内的用户 然后发送

            Group group = groupMapper.selectById(toUsername);
            String members = group.getMembers();
            //将json字符串转为对象
            com.alibaba.fastjson.JSONArray jsArr = com.alibaba.fastjson.JSONObject.parseArray(members);
            if(jsArr != null){
                for (Object o : jsArr) {
                    System.out.println(o);
                    Session toSession = sessionMap.get(o.toString());
                    if (toSession != null) {
                        // 服务器端 再把消息组装一下，组装后的消息包含发送人和发送的文本内容
                        // {"from": "zhang", "text": "hello"}
                        JSONObject jsonObject = new JSONObject();

                        //根据id查找用户头像追加
                        User user = userMapper.selectById(username);
                        String profile = user.getProfile();

                        jsonObject.set("from", username);  // from 是 zhang
                        jsonObject.set("text", text);  // text 同上面的text
                        jsonObject.set("id", toUsername);
                        jsonObject.set("profile", profile);
                        if (toSession != session) {
                            //排除自己
                            this.sendMessage(jsonObject.toString(), toSession);
                            log.info("发送给用户username={}，消息：{}", toUsername, jsonObject.toString());
                        }
                        } else {
                        if (!o.toString().equals(from)) {
                            //排除自己

                        //Session为空代表用户离线，储存消息到本地
                        OfflineMsg offlineMsg = new OfflineMsg();

                        SimpleDateFormat df = new SimpleDateFormat("yyyyMMddhhmmss");
                        String date = df.format(new Date());
                        System.out.println("sqlite:" + date);

                        offlineMsg.setId(date);
                        offlineMsg.setFromId(username);
                        offlineMsg.setToId(o.toString());
                        offlineMsg.setMsg(text);
                        offlineMsg.setType(2);
                        offlineMsg.setGroupId(from);

                        //根据id查找用户头像追加
                        User user = userMapper.selectById(username);
                        String profile = user.getProfile();
                        offlineMsg.setProfile(profile);

                        Date e = new Date();
                        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                        String str = sdf.format(e);
                        offlineMsg.setTime(str);

                        log.info("将消息存入数据库" + offlineMsg);
                        boolean save = offlineMsgService.save(offlineMsg);
                        if (save) {
                            log.info("用户" + toUsername + "离线,将消息存入数据库");
                        }
                        }
                        //log.info("发送失败，未找到用户username={}的session", toUsername);
                    }
                }
            }
            //

        }
    }

    @OnError
    public void onError(Session session, Throwable error) {
        log.error("发生错误");
        error.printStackTrace();
    }

    /**
     * 服务端发送消息给客户端
     */
    private void sendMessage(String message, Session toSession) {
        try {
            log.info("服务端给客户端[{}]发送消息{}", toSession.getId(), message);
            toSession.getBasicRemote().sendText(message);
        } catch (Exception e) {
            log.error("服务端发送消息给客户端失败", e);
        }
    }

    /**
     * 服务端发送消息给所有客户端
     */
    private void sendAllMessage(String message) {
        try {
            for (Session session : sessionMap.values()) {
                log.info("服务端给客户端[{}]发送消息{}", session.getId(), message);
                session.getBasicRemote().sendText(message);
            }
        } catch (Exception e) {
            log.error("服务端发送消息给客户端失败", e);
        }
    }
}

