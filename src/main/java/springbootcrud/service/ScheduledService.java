package springbootcrud.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class ScheduledService {
    private Logger logger = LoggerFactory.getLogger(springbootcrud.service.ScheduledService.class);
    /**
     * second, minute, hour, day of month(日), month, day of week(周几)
     * 0 * * * * MON-FRI
     * [0 0/5 14,18 * * ?] 每天的14点整，和18点整，每隔5分钟执行一次
     * [0 15 10 ? * 1-6] 每个月的周一至周六10：15分执行一次
     * [0 0 2 ？ * 6L] 每个月的最后一个周六凌晨2点执行一次
     * [0 0 2 LW * ?] 每个月的最后一个工作日凌晨2点执行一次
     * [0 0 2-4 ? * 1#1] 每个月的第一个周一凌晨2点到4点期间，每个整点都要执行一次
     */
    @Scheduled(cron = "0 * * * * MON-SAT")
    public void hello() {
        logger.info("===============每分钟输出一次");
    }
}
