package com.dennistocker.demo;

import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import sun.misc.ProxyGenerator;

import java.io.FileOutputStream;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Arrays;

@Slf4j
@SpringBootApplication
@EnableScheduling
@EnableAsync
@ServletComponentScan // 支持 WebServlet， WebFilter和 WebListener
@MapperScan("com.dennistocker.demo.dao")
public class DemoAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoAppApplication.class, args);
		// config in spring.factories
//		SpringApplication springApplication = new SpringApplication(DemoAppApplication.class);
//		springApplication.addInitializers(new MyContextInitializer());
//		springApplication.run(args);
	}

	/**
	 * CommanderLinerRunner 也可以通过实现接口来实现，可以使用Order注解指定执行顺序
	 * @param ctx
	 * @return
	 */
//	@Bean
	@Order(Ordered.HIGHEST_PRECEDENCE)
	public CommandLineRunner commandLineRunner(ApplicationContext ctx) {
		return args -> {
			log.info("Beans:");

			String[] beanNames = ctx.getBeanDefinitionNames();
			Arrays.sort(beanNames);
			for (String beanName : beanNames) {
				log.info(beanName);
			}
		};
	}

	@Bean
	public ApplicationRunner applicationRunner(ApplicationContext ctx) {
		return new ApplicationRunner() {
			@Override
			public void run(ApplicationArguments args) throws Exception {
				log.info(" -- ApplicationRunner: {}", args);

				log.info("=== 测试JDK 动态代理 ===");
				ProxyServiceImpl proxy = new ProxyServiceImpl(11);
				ClassLoader classLoader = proxy.getClass().getClassLoader();
				Class<?>[] interfaces = proxy.getClass().getInterfaces();
				InvocationHandler handler = new LogHandler(proxy);
				ProxyService service = (ProxyService) Proxy.newProxyInstance(classLoader, interfaces, handler);
				service.testProxy();
				proxyUtils.generateClassFile(ProxyServiceImpl.class, "ProxyServiceImplProxy");

				log.info("=== 测试CGLIB 动态代理 ===");
				Enhancer enhancer = new Enhancer();
				enhancer.setSuperclass(ProxyServiceImpl.class);
				enhancer.setCallback(new LogInterceptor());
				ProxyServiceImpl proxyService = (ProxyServiceImpl) enhancer.create(new Class[]{Integer.class},
						new Object[]{13});
				proxyService.testProxy();
			}
		};
	}


}


/**
 *  =========================== JDK 动态代理 ===================================
 *  基于Java反射机制实现，必须实现了接口的业务类才能使用这种办法生成代理对象。
 */

/**
 * InvocationHandler
 */
@Slf4j
class LogHandler implements InvocationHandler {

	private Object target;

	public LogHandler(Object target) {
		this.target = target;
	}

	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		log.info("proxy: {}, target: {}, this: {} =: {}", proxy, target, this, proxy == target);
		before();
		Object result = method.invoke(target, args);
		after();
		return result;
	}

	private void before() {
		log.info("before");
	}

	private void after() {
		log.info("after");
	}
}

/**
 *  接口
 */
interface ProxyService {
	void testProxy();
}

/**
 * 被代理的类
 */
@Slf4j
class ProxyServiceImpl implements ProxyService {

	public Integer num;

	ProxyServiceImpl(Integer n) {
		num = n;
	}

	public void testProxy() {
		log.info("JdkDynamicProxy: {}", num);
	}
}

/**
 * 保存生成的代理类
 */
@Slf4j
class proxyUtils {
	public static void generateClassFile(Class<?> clazz, String proxyName) {
		byte[] classFile = ProxyGenerator.generateProxyClass(proxyName, clazz.getInterfaces());
		String path = clazz.getResource(".").getPath();
		log.info("path: {}", path);
		try (FileOutputStream out = new FileOutputStream(path + proxyName + ".class")) {
			out.write(classFile);
			out.flush();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}


/**
 *  =========================== CGLIB 动态代理 ===================================
 *  基于ASM机制实现，通过生成业务类的子类作为代理类。
 */
@Slf4j
class LogInterceptor implements MethodInterceptor {

	/**
	 *
	 * @param o 要进行增强的对象
	 * @param method	拦截的方法
	 * @param objects	参数列表
	 * @param methodProxy 对方法的代理, invokeSuper方法表示调用 被代理对象 的方法
	 * @return
	 * @throws Throwable
	 */
	@Override
	public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
		before();
		Object result = methodProxy.invokeSuper(o, objects);
		after();
		return result;
	}

	void before() {
		log.info("CGLIB before");
	}

	void after() {
		log.info("CGLIB after");
	}
}
