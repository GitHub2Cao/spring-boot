package com.softnovo.service;

import com.softnovo.domain.User;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Service;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import java.util.Map;

@Service
public class UserService implements ApplicationListener<ContextRefreshedEvent> {
    private static ApplicationContext applicationContext;

    public void createUser(User user){
        System.out.println("save user.");
    }

    public User getUserById(String userId) {
        User user = new User();
        user.setUserId(userId);
        user.setUserName("test");
        return user;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        this.applicationContext = event.getApplicationContext();
        RequestMappingHandlerMapping bean = applicationContext.getBean(RequestMappingHandlerMapping.class);
        Map<RequestMappingInfo, HandlerMethod> handlerMethods = bean.getHandlerMethods();
        handlerMethods.forEach((k, v) -> {
            System.out.println(k + " === " + v);
        });
    }

//	@Override
//	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
//		this.applicationContext = applicationContext;
//
//		RequestMappingHandlerMapping bean = applicationContext.getBean(RequestMappingHandlerMapping.class);
//		Map<RequestMappingInfo, HandlerMethod> handlerMethods = bean.getHandlerMethods();
//		handlerMethods.forEach((k, v) -> {
//			System.out.println(k + " === " + v);
//		});
//
////			AnnotationConfigServletWebServerApplicationContext context = new AnnotationConfigServletWebServerApplicationContext(WebConfig.class);
////			// 作用 解析 @RequestMapping 以及派生注解，生成路径与控制器方法的映射关系, 在初始化时就生成
////			RequestMappingHandlerMapping handlerMapping = context.getBean(RequestMappingHandlerMapping.class);
////			Map<RequestMappingInfo, HandlerMethod> handlerMethods = handlerMapping.getHandlerMethods(); // 获取映射结果
////    handlerMethods.forEach((k, v) -> { System.out.println(k + "=" + v);});
//	}
}
