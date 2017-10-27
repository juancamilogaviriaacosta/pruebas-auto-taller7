package monkey.adb;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.UUID;

/**
 * @author juan
 */
public class Main {

    public static final String ADB_ROOT = "/home/juan/Android/Sdk/platform-tools/";
    public static final String ADB_INPUT = ADB_ROOT + "adb shell input ";
    public static final String TELNET_TOKEN = "ZhOfLHqRnR2sPche";
    public static final String EMULATOR_PORT = "5554";
    public static final String[] VELOCIDADES = {"gsm", "hscsd", "gprs", "edge", "umts", "hsdpa", "lte", "evdo", "full"};

    public static void ejecutarTelnet(String orden) throws IOException {
        Runtime rt = Runtime.getRuntime();
        Process telnet = rt.exec("telnet localhost " + EMULATOR_PORT);
        BufferedWriter out = new BufferedWriter(new OutputStreamWriter(telnet.getOutputStream()));
        out.write("auth " + TELNET_TOKEN + "\n");
        out.write(orden + "\n");
        out.write("quit\n");
        out.flush();
    }

    public static void ejecutarAdb(String orden) throws IOException {
        Runtime rt = Runtime.getRuntime();
        rt.exec(ADB_INPUT + orden);
    }

    public static void instalarApk(String apk, String paquete) throws IOException {
        Runtime rt = Runtime.getRuntime();
        rt.exec(ADB_ROOT + "adb shell install " + apk);
        rt.exec(ADB_ROOT + "adb shell am start -n " + paquete);
    }

    public static boolean getEjecutar(String probabilidad) {
        Random random = new Random();
        return probabilidad != null && Double.valueOf(probabilidad) <= random.nextInt(100);
    }

    public static void main(String[] args) {
        try {
            Map<String, String> argumentos = new HashMap<>();
            for (String arg : args) {
                argumentos.put(arg.substring(0, arg.indexOf('=')), arg.substring(arg.indexOf('=') + 1));
            }
            System.out.println(argumentos);
            Integer n = Integer.valueOf(argumentos.get("n"));
            String tap = argumentos.get("tap");
            String text = argumentos.get("text");
            String swipe = argumentos.get("swipe");
            String keyevent = argumentos.get("keyevent");
            String rotate = argumentos.get("rotate");
            String network_speed = argumentos.get("network_speed");
            String accelerometer = argumentos.get("accelerometer");
            String apk = argumentos.get("apk");
            String paquete = argumentos.get("paquete");

            instalarApk(apk, paquete);
            Random random = new Random(12345);
            for (int i = 0; i < n; i++) {
                int x1 = random.nextInt(1080);
                int y1 = random.nextInt(1920);
                int x2 = random.nextInt(1080);
                int y2 = random.nextInt(1920);
                int key = random.nextInt(283);
                int vel = random.nextInt(8);
                int x = random.nextInt(99);
                int y = random.nextInt(99);
                int z = random.nextInt(99);

                //Comandos ADB
                if (getEjecutar(tap)) {
                    ejecutarAdb("tap " + x1 + " " + y1);
                }
                if (getEjecutar(text)) {
                    ejecutarAdb("text " + UUID.randomUUID());
                }
                if (getEjecutar(swipe)) {
                    ejecutarAdb("swipe " + x1 + " " + y1 + " " + x2 + " " + y2);
                }
                if (getEjecutar(keyevent)) {
                    ejecutarAdb("keyevent " + key);
                }

                // Comandos telnet
                if (getEjecutar(rotate)) {
                    ejecutarTelnet("rotate");
                }
                if (getEjecutar(network_speed)) {
                    ejecutarTelnet("network speed " + VELOCIDADES[vel]);
                }
                if (getEjecutar(accelerometer)) {
                    ejecutarTelnet("sensor set acceleration " + x + ":" + y + ":" + z);
                }

                Thread.sleep(1000);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
