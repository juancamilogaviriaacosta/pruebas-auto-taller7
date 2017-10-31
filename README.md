# pruebas-auto-taller7

### 3.1 Ejecutar monkey java

Ejecutar el jar reemplazando los parametros por los que correspondan de acuerdo al ambiente donde se ejecute de la siguiente manera

* adb_root=/home/juan/Android/Sdk/platform-tools/ (ubicacion del adb)
* telnet_token=ZhOfLHqRnR2sPche (token telnet)
* emulator_port=5554 (puerto del emulador)
* n=10 (numero de eventos)
* tap=0.96 (probabilidad de ejecución del tap)
* text=0.8 (probabilidad de ejecución del text)
* swipe=0.92 (probabilidad de ejecución del swipe)
* keyevent=0.1 (probabilidad de ejecución del keyevent)
* rotate=0.3 (probabilidad de ejecución del rotate)
* network_speed=0.2 (probabilidad de ejecución del network_speed)
* accelerometer=0.1 (probabilidad de ejecución del accelerometer)
* apk=/home/juan/Escritorio/silectric.apk (ubicación del APK)
* paquete=net.alaindonesia.silectric (nombre del paquete)

Nota1: para al parametro adb_root se debe incluir el utlimo slash (/) como aparece en el ejemplo
Nota2: puede descargar el ejecutable en la sección de release [monkey_adb_juan_gaviria.jar](https://github.com/juancamilogaviriaacosta/pruebas-auto-taller7/releases/download/1/monkey_adb_juan_gaviria.jar)

Ejemplo

java -jar monkey_adb_juan_gaviria.jar adb_root=/home/juan/Android/Sdk/platform-tools/ telnet_token=ZhOfLHqRnR2sPche emulator_port=5554 n=10 tap=0.96 text=0.8 swipe=0.92 keyevent=0.1 rotate=0.3 network_speed=0.2 accelerometer=0.1 apk=/home/juan/Escritorio/silectric.apk paquete=net.alaindonesia.silectric

### 4.1. Test para el caso de uso de agregar un electrodomestico a para el calculo de la tarifa electrica

El código fuente del test se encuentra en
./espresso_4_5/Silectric-master/app/src/androidTest/java/net/alaindonesia/silectric/AgregarAcuarioTest.java

### 5.1 Test grabando los pasos para el caso de uso de configurar una tarifa para el cargo básico

El código fuente del test se encuentra en
./espresso_4_5/Silectric-master/app/src/androidTest/java/net/alaindonesia/silectric/AgregarCargoTest.java


El siguiente [video](https://www.youtube.com/watch?v=SY9701gmBFg) muestra todo el proceso

<a href="http://www.youtube.com/watch?feature=player_embedded&v=SY9701gmBFg
" target="_blank"><img src="http://img.youtube.com/vi/SY9701gmBFg/0.jpg" 
alt="IMAGE ALT TEXT HERE" width="560" height="315" border="10" /></a>


