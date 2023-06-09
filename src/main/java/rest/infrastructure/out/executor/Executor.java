package rest.infrastructure.out.executor;

import jakarta.annotation.Resource;
import jakarta.enterprise.concurrent.ManagedExecutorService;
import rest.application.api.out.Executable;

public class Executor implements Executable {

    @Resource
    private ManagedExecutorService mes;


    @Override
    public void execute(Runnable thread) {
        mes.execute(thread);
    }
}
