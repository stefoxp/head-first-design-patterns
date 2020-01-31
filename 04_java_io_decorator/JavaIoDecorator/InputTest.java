package JavaIoDecorator;

import java.io.*;

/**
 *
 * @author stefano
 */
public class InputTest {
    public static void main(String[] args) throws IOException {
        int c;
        try {
            // set up the FileInputStream and decorate it,
            // first with a BufferedInputStream
            // and then our brand new LowerCaseInputStream filter
            InputStream in = 
                    new LowerCaseInputStream(
                            new BufferedInputStream(
                                    new FileInputStream("test.txt")));
            
            while((c = in.read()) >= 0) {
                // just use the stream to read characters until the end of file
                System.out.print((char)c);
            }
            
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
