package config;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import javax.servlet.MultipartConfigElement;
import javax.servlet.ServletRegistration;

/**
 * @ClassName WebAppInitializer
 * @Description TODO
 * @Author shnstt
 * @Date 2019/4/20 15:51
 * @Version 1.0
 **/
public class WebAppInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

    @Override
    protected Class<?>[] getRootConfigClasses() {
        // 配置spring ioc 资源
        return new Class<?>[]{RootConfig.class};
    }

    @Override
    protected Class<?>[] getServletConfigClasses() {
        // dispatcherServlet 环境配置
        return new Class<?>[]{WebConfig.class};
    }

    @Override
    protected String[] getServletMappings() {
        // DispatcherServlet拦截请求配置
        return new String[]{"*.do"};
    }

    @Override
    protected void customizeRegistration(ServletRegistration.Dynamic registration) {
        // 配置文件上传路径
        String filePath = "c:/temp/mvn/uploads";
        // 5M
        Long singleMax = (long) (5 * Math.pow(2, 20));
        // 10M
        Long totalMax = (long) (10 * Math.pow(2, 20));
        // 设置上传文件配置
        registration.setMultipartConfig(new MultipartConfigElement(filePath, singleMax, totalMax, 0));
    }
}
