package by.future.common.utils;


import java.util.concurrent.*;
import java.util.function.Function;

/**
 * @Author：by@Deng
 * @Date：2020/3/26 17:37
 */
public class CompletableFutureUtils {

    /**
     * completable超时设置
     *
     * @Author：by@Deng
     * @Date：2020/3/26 18:49
     */
    public static <T> CompletableFuture<T> timeoutAfter(CompletableFuture<T> future,long timeout, TimeUnit unit) {

        final CompletableFuture<T> result = new CompletableFuture<>();

        Delayer.delay(() -> result.completeExceptionally(new TimeoutException()), timeout, unit);

        return future.applyToEither(result, Function.identity()).exceptionally(throwable -> {
            System.out.println("调用超时");
            return null;
        });
    }


    static final class Delayer {

        static final ScheduledThreadPoolExecutor delayer;

        static ScheduledFuture<?> delay(Runnable command, long delay, TimeUnit unit) {
            return delayer.schedule(command, delay, unit);
        }

        static final class DaemonThreadFactory implements ThreadFactory {
            public Thread newThread(Runnable r) {
                Thread t = new Thread(r);
                t.setDaemon(true);
                t.setName("CompletableFutureDelayScheduler");
                return t;
            }
        }

        static {
            (delayer = new ScheduledThreadPoolExecutor(1, new DaemonThreadFactory())).setRemoveOnCancelPolicy(true);
        }
    }



}
