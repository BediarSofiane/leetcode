import java.util.Arrays;

public class DataDogBufferTest {

    public static void main(String[] args) {
        FileOut file = new FileOut();
        Buffer buffer = new Buffer(5, file);
        buffer.write("Hello World".getBytes());
        buffer.flush();

    }
}

class FileOut {

    public FileOut() {
    }

    public void write(byte[] data) {
        // Write to disc
        System.out.println(new String(data));
    }
}

class Buffer {
    byte[] cache;
    FileOut fileOut;
    int cacheSize;

    public Buffer(int size, FileOut fileOut) {
        cache = new byte[size];
        this.fileOut = fileOut;
    }

    public void write(byte[] data) {
        int dataSize = data.length;
        int bufferSize = cache.length;
        int i = 0;
        while (i < dataSize) {
            cache[cacheSize] = data[i];
            i++;
            cacheSize++;
            if (cacheSize == bufferSize) {
                flush();
                cacheSize = 0;
            }
            
        }
    }

    public void flush() {
        if (cacheSize > 0) {
            fileOut.write(Arrays.copyOfRange(cache, 0, cacheSize));
            cacheSize = 0;
        }
    }
}
