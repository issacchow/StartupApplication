package springboot.job;

import com.dangdang.ddframe.job.api.ShardingContext;
import com.dangdang.ddframe.job.api.simple.SimpleJob;
import springboot.util.DataTypeUtil;

import java.util.Date;

import static springboot.util.LogUtil.log;

/**
 * Created by IssacChow on 18/5/31.
 */
public class MyJob001 implements SimpleJob {


    @Override
    public void execute(ShardingContext shardingContext) {

        Date now = new Date();
        log("[task-id:%s] [JobName:%s] [sharding item:%s] , total sharding count:%s  start:%s",
                shardingContext.getTaskId(),
                shardingContext.getJobName(),
                shardingContext.getShardingItem(),
                shardingContext.getShardingTotalCount(),
                DataTypeUtil.getDateString(now, ""));
        log("sleep");
        try {
            Thread.sleep(15000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        now = new Date();
        log("[task-id:%s] [JobName:%s] [sharding item:%s] , total sharding count:%s  end:%s",
                shardingContext.getShardingItem(),
                shardingContext.getTaskId(),
                shardingContext.getJobName(),
                shardingContext.getShardingTotalCount(),
                DataTypeUtil.getDateString(now, ""));
    }
}
