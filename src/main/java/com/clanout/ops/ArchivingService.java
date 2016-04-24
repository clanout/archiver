package com.clanout.ops;

import com.clanout.application.core.ApplicationContext;
import com.clanout.application.core.Module;
import com.clanout.application.module.plan.context.PlanContext;
import com.clanout.application.module.plan.domain.use_case.ArchivePlans;
import com.google.common.util.concurrent.ThreadFactoryBuilder;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;

public class ArchivingService
{
    private ArchivePlans archivePlans;

    public ArchivingService() throws Exception
    {
        ExecutorService backgroundPool = getBackgroundThreadPool();
        ApplicationContext applicationContext = new ApplicationContext(backgroundPool);
        PlanContext planContext = (PlanContext) applicationContext.getContext(Module.PLAN);
        archivePlans = planContext.archivePlans();
    }

    public void run()
    {
        archivePlans.execute();
    }

    private ExecutorService getBackgroundThreadPool()
    {
        String THREAD_NAME_FORMAT = "background-thread-%d";
        int BACKGROUND_THREAD_POOL_SIZE = 5;

        ThreadFactory backgroundThreadFactory = new ThreadFactoryBuilder()
                .setNameFormat(THREAD_NAME_FORMAT)
                .build();
        return Executors.newFixedThreadPool(BACKGROUND_THREAD_POOL_SIZE, backgroundThreadFactory);
    }
}
