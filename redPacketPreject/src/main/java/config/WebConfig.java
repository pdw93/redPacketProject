package config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.scheduling.annotation.AsyncConfigurerSupport;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.HandlerAdapter;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * @ClassName WebConfig
 * @Description TODO
 * @Author shnstt
 * @Date 2019/4/20 15:56
 * @Version 1.0
 **/
@Configuration
@ComponentScan(basePackages = {"controller"}, includeFilters = {@ComponentScan.Filter(type = FilterType.ANNOTATION, value = Controller.class)})
@EnableWebMvc
@EnableAsync
public class WebConfig extends AsyncConfigurerSupport {
    /**
     * 初始化视图解析器
     * @return ViewResolver
     */
    @Bean(name = "internalResourceViewResolver")
    public ViewResolver initViewResolver(){
        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
        viewResolver.setPrefix("/WEB-INF/jsp/");
        viewResolver.setSuffix(".jsp");
        return viewResolver;
    }

    /**
     * 初始化适配器
     * @return HandlerAdapter
     */
    @Bean(name = "requestMappingHandlerAdapter")
    public HandlerAdapter initRequestMappingHandlerAdapter(){
        RequestMappingHandlerAdapter rmha = new RequestMappingHandlerAdapter();
        // http json 转换器
        MappingJackson2HttpMessageConverter jsonConverter = new MappingJackson2HttpMessageConverter();
        MediaType mediaType = MediaType.APPLICATION_JSON_UTF8;
        List<MediaType> mediaTypes = new ArrayList<>();
        mediaTypes.add(mediaType);
        jsonConverter.setSupportedMediaTypes(mediaTypes);
        // 往适配器中加入json转换器
        rmha.getMessageConverters().add(jsonConverter);
        return rmha;
    }

    @Override
    public Executor getAsyncExecutor() {
        ThreadPoolTaskExecutor taskExecutor = new ThreadPoolTaskExecutor();
        taskExecutor.setCorePoolSize(5);
        taskExecutor.setMaxPoolSize(10);
        taskExecutor.setQueueCapacity(200);
        taskExecutor.initialize();
        return taskExecutor;
    }
}
