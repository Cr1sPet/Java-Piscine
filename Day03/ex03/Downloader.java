import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class Downloader {

     static long download(String url, String fileName) throws IOException {
        try (InputStream in = URI.create(url).toURL().openStream()) {
            return Files.copy(in, Paths.get(fileName));
        }
    }

    public Downloader() {

    }

    public static void parallelFilesDownload(List<String> filesUrl, int threadsCount) throws IOException, ExecutionException, InterruptedException {

        List<Future> futureList = new ArrayList<>();

        ExecutorService service = Executors.newFixedThreadPool(threadsCount);

        for (int i = 0; i < filesUrl.size(); i++) {

            int finalI = i;
            Future future = service.submit(() -> {
                    System.out.printf("Thread-%s start download file number %d\n",
                            Thread.currentThread().getName().split("-")[3], finalI);
                try {
                    download(filesUrl.get(finalI), (finalI + 1) + "");
                } catch (IOException e) {
                    e.printStackTrace();
                }
                System.out.printf("Thread-%s finish download file number %d\n",
                            Thread.currentThread().getName().split("-")[3], finalI);

            });
            futureList.add(future);

        }
        for (Future future : futureList) {
            future.get();
        }

        service.shutdown();


    }



}
