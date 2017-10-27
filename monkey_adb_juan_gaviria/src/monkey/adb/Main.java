package monkey.adb;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.Random;

/**
 *
 * @author juan
 */
public class Main {


    public static final String ADB_ROOT = "/home/juan/Android/Sdk/platform-tools/";
    public static final String ADB_INPUT = ADB_ROOT + "adb shell input ";
    public static final String TELNET_TOKEN = "ZhOfLHqRnR2sPche";
    public static final String EMULATOR_PORT = "5554";

    private static BufferedWriter connectToTelnet() throws IOException {
        Runtime rt = Runtime.getRuntime();
        Process telnet = rt.exec("telnet localhost "+EMULATOR_PORT);
        return new BufferedWriter(new OutputStreamWriter(telnet.getOutputStream()));
    }

    public static void rotate() throws IOException {
        BufferedWriter out = connectToTelnet();
        out.write("auth "+TELNET_TOKEN+"\n");
        out.write("rotate\n");
        out.write("quit\n");
        out.flush();
    }

    public static void main(String[] args) {
        Runtime rt = Runtime.getRuntime();
        try {

            Random random = new Random(12345);

            int i = 0;
            while(i < 10) {
                int x = random.nextInt(1080);
                int y = random.nextInt(1920);
                int rotate = random.nextInt(2);

                if (rotate == 1) {
                    Main.rotate();
                }

                rt.exec(ADB_INPUT + "tap " + x + " " + y);
                i++;
                Thread.sleep(1000);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}