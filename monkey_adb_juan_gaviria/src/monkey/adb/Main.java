package monkey.adb;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.UUID;

/**
 * @author juan
 */
public class Main {

    private static String adb_root;
    private static String telnet_token;
    private static String emulator_port;
    private static final String[] VELOCIDADES = {"gsm", "hscsd", "gprs", "edge", "umts", "hsdpa", "lte", "evdo", "full"};

    public static void ejecutarTelnet(String orden) throws IOException {
        Runtime rt = Runtime.getRuntime();
        Process telnet = rt.exec("telnet localhost " + emulator_port);
        BufferedWriter out = new BufferedWriter(new OutputStreamWriter(telnet.getOutputStream()));
        out.write("auth " + telnet_token + "\n");
        out.write(orden + "\n");
        out.write("quit\n");
        out.flush();
        System.out.println("Orden telnet: " + orden);
    }

    public static void ejecutarAdb(String orden) throws IOException {
        Runtime rt = Runtime.getRuntime();
        rt.exec(adb_root + "adb shell input " + orden);
        System.out.println("Orden adb: " + orden);
    }

    public static void instalarApk(String apk, String paquete) throws IOException, InterruptedException {
        Runtime rt = Runtime.getRuntime();
        rt.exec(adb_root + "adb install -r " + apk);
        System.out.println("Instaldo: " + apk);
        Thread.sleep(3000);
    }

    public static void abrirApk(String paquete) throws IOException {
        Runtime rt = Runtime.getRuntime();
        InputStream is = rt.exec(adb_root + "adb shell dumpsys window windows | grep -E 'mCurrentFocus|mFocusedApp'").getInputStream();
        BufferedReader in = new BufferedReader(new InputStreamReader(is));
        String salida = "";
        String line;
        while ((line = in.readLine()) != null) {
            salida += line;
        }
        if (!salida.contains(paquete)) {
            rt.exec(adb_root + "adb shell monkey -p " + paquete + " 1");
            System.out.println("Abriendo: " + paquete);
        }
    }

    public static boolean getPermisoEjecutar(String probabilidad) {
        Random random = new Random();
        return probabilidad != null && random.nextInt(101) <= (Double.valueOf(probabilidad) * 100);
    }

    public static void main(String[] args) {
        try {
            Map<String, String> argumentos = new HashMap<>();
            for (String arg : args) {
                argumentos.put(arg.substring(0, arg.indexOf('=')), arg.substring(arg.indexOf('=') + 1));
            }
            adb_root = argumentos.get("adb_root");
            telnet_token = argumentos.get("telnet_token");
            emulator_port = argumentos.get("emulator_port");
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
            abrirApk(paquete);
            Random random = new Random(12345);
            for (int i = 0; i < n; i++) {
                int x1 = random.nextInt(1080);
                int y1 = random.nextInt(1920);
                int x2 = random.nextInt(1080);
                int y2 = random.nextInt(1920);
                int key = random.nextInt(283);
                int vel = random.nextInt(8 + 1);
                int x = random.nextInt(99);
                int y = random.nextInt(99);
                int z = random.nextInt(99);

                //Comandos ADB
                if (getPermisoEjecutar(tap)) {
                    abrirApk(paquete);
                    ejecutarAdb("tap " + x1 + " " + y1);
                }
                if (getPermisoEjecutar(text)) {
                    abrirApk(paquete);
                    ejecutarAdb("text " + UUID.randomUUID());
                }
                if (getPermisoEjecutar(swipe)) {
                    abrirApk(paquete);
                    ejecutarAdb("swipe " + x1 + " " + y1 + " " + x2 + " " + y2);
                }
                if (getPermisoEjecutar(keyevent)) {
                    abrirApk(paquete);
                    ejecutarAdb("keyevent " + key);
                }

                // Comandos telnet
                if (getPermisoEjecutar(rotate)) {
                    abrirApk(paquete);
                    ejecutarTelnet("rotate");
                }
                if (getPermisoEjecutar(network_speed)) {
                    abrirApk(paquete);
                    ejecutarTelnet("network speed " + VELOCIDADES[vel]);
                }
                if (getPermisoEjecutar(accelerometer)) {
                    abrirApk(paquete);
                    ejecutarTelnet("sensor set acceleration " + x + ":" + y + ":" + z);
                }
                Thread.sleep(1000);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
