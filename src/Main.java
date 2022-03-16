import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

public class Main {
    public static void main(String[] args) {
        SchedulerFactory factory = new StdSchedulerFactory();

        // 자바 스케쥴러를 이용해 특정 시간마다 동작
        try {
            Scheduler scheduler = factory.getScheduler();

            JobDetail job = JobBuilder.newJob(eyeAccountManager.class)
                    .withIdentity("jobName", Scheduler.DEFAULT_GROUP)
                    .build();

            Trigger trigger = TriggerBuilder.newTrigger()
                    .withIdentity("triggerName", Scheduler.DEFAULT_GROUP)
                    .withSchedule(CronScheduleBuilder.cronSchedule("0 30 7 * * ?"))
                    .build();

            scheduler.scheduleJob(job, trigger);
            scheduler.start();

        } catch (Exception e) {
            e.printStackTrace();
        }
//
//        eyeAccountManager manager = new eyeAccountManager();
//
//        manager.setConfig();
    }
}
