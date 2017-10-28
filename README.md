# pruebas-auto-taller7

### Ejecutar monkey java

Ejecutar el jar con la siguiente linea de comando, reemplazando los parametros por los que correspondan de acuerdo al ambiente donde se ejecute
Nota: para al parametro adb_root se debe incluir el utlimo slash (/) como aparece en el ejemplo

java -jar monkey_adb_juan_gaviria.jar adb_root=/home/juan/Android/Sdk/platform-tools/ telnet_token=ZhOfLHqRnR2sPche emulator_port=5554 n=10 tap=0.96 text=0.8 swipe=0.92 keyevent=0.1 rotate=0.3 network_speed=0.2 accelerometer=0.1 apk=/home/juan/Escritorio/silectric.apk paquete=net.alaindonesia.silectric           
