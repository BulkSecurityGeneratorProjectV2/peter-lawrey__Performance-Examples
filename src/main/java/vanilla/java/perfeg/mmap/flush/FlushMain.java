package vanilla.java.perfeg.mmap.flush;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.Files;

/**
 * @author peter.lawrey
 */
public class FlushMain {
    public static void main(String... args) throws IOException {
        File file = Files.createTempFile("deleteme", "dat").toFile();
        file.deleteOnExit();
        int tests = 10000;
        long start = System.nanoTime();
        FileChannel fc = new RandomAccessFile(file, "rwd").getChannel();
        for (int i = 0; i < tests; i++)
            fc.write(ByteBuffer.wrap(new byte[8]));
        fc.close();
        long time = System.nanoTime() - start;
        System.out.printf("Average latency %.1f us%n", time / tests / 1e3);
    }
}
