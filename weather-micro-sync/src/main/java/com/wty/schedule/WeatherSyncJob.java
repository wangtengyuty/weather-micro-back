package com.wty.schedule;

import com.wty.Service.ISyncService;
import lombok.extern.slf4j.Slf4j;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.QuartzJobBean;

/**
 * @Author wangtengyu
 * @Create 2018-04-24-14:15
 */

@Slf4j
public class WeatherSyncJob extends QuartzJobBean {

    @Autowired
    private ISyncService ISyncService;

    /**
     * 定时任务 根据时间取获取一遍所有城市的数据放进缓存
     * @param jobExecutionContext
     * @throws JobExecutionException
     */
    @Override
    protected void executeInternal(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        log.info("调用天气数据同步服务");
        try {
            ISyncService.syncDataByCityKey();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
