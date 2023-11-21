package au.com.vietnamesecolour.config.data;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

public abstract class BaseService {

    /**
     * Common logger for use in subclasses.
     */
    protected final Logger LOGGER = LoggerFactory.getLogger(this.getClass());

//    protected ModelMapper modelMapper = Mappers.getMapper(ModelMapper.class);

    @Autowired
    protected ApplicationContext applicationContext;

    @Autowired
    protected ApplicationEventPublisher applicationEventPublisher;

    @Autowired
    protected ThreadPoolTaskExecutor taskExecutor;

//    protected Authentication getCurrentAuthentication() {
//        return SecurityContextHolder.getContext().getAuthentication();
//    }
//
//    protected String getCurrentUsername() {
//        var authentication = getCurrentAuthentication();
//        if (authentication != null) {
//            return authentication.getName();
//        }
//        throw new BaseException(CommonErrorCode.UNAUTHORIZED);
//    }
}