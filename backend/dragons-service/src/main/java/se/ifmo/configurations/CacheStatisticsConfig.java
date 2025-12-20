package se.ifmo.configurations;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.hibernate.stat.Statistics;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.orm.jpa.EntityManagerFactoryUtils;

import jakarta.persistence.EntityManagerFactory;

@Aspect
@Configuration
@EnableAspectJAutoProxy
public class CacheStatisticsConfig {

    private static final Logger logger = LoggerFactory.getLogger(CacheStatisticsConfig.class);
    
    private final EntityManagerFactory entityManagerFactory;
    private final CacheLoggingProperties cacheLoggingProperties;
    
    public CacheStatisticsConfig(EntityManagerFactory entityManagerFactory, 
                                CacheLoggingProperties cacheLoggingProperties) {
        this.entityManagerFactory = entityManagerFactory;
        this.cacheLoggingProperties = cacheLoggingProperties;
    }
    
    @Around("@annotation(org.springframework.web.bind.annotation.GetMapping) || " +
            "@annotation(org.springframework.web.bind.annotation.PostMapping) || " +
            "@annotation(org.springframework.web.bind.annotation.PutMapping) || " +
            "@annotation(org.springframework.web.bind.annotation.DeleteMapping)")
    public Object logCacheStatistics(ProceedingJoinPoint joinPoint) throws Throwable {
        if (!cacheLoggingProperties.isEnabled()) {
            return joinPoint.proceed();
        }
        
        Statistics stats = EntityManagerFactoryUtils.getEntityManagerFactory(entityManagerFactory)
                .unwrap(org.hibernate.SessionFactory.class)
                .getStatistics();

        if (stats.isStatisticsEnabled()) {
            logger.info("Cache statistics before request - Hits: {}, Misses: {}, Put count: {}", 
                       stats.getSecondLevelCacheHitCount(),
                       stats.getSecondLevelCacheMissCount(),
                       stats.getSecondLevelCachePutCount());
        }
        
        Object result = joinPoint.proceed();
        
        if (stats.isStatisticsEnabled()) {
            logger.info("Cache statistics after request - Hits: {}, Misses: {}, Put count: {}", 
                       stats.getSecondLevelCacheHitCount(),
                       stats.getSecondLevelCacheMissCount(),
                       stats.getSecondLevelCachePutCount());
        }
        
        return result;
    }
}
