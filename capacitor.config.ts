import type { CapacitorConfig } from '@capacitor/cli';

/**
 * EstiloVivo Android — contenedor nativo (WebView) de la web de producción.
 *
 * La APK NO empaqueta el frontend: carga WEB_URL en remoto y expone los
 * plugins nativos (cámara, push, compras, almacenamiento cifrado...) al
 * JavaScript de la web a través del bridge de Capacitor.
 * `www/` sólo contiene la pantalla de error/sin conexión.
 */
const WEB_URL = process.env.ESTILOVIVO_WEB_URL || 'https://estilovivo.xyoncloud.win';
const WEB_HOST = new URL(WEB_URL).host;

const config: CapacitorConfig = {
  appId: 'com.estilovivo.app',
  appName: 'EstiloVivo',
  webDir: 'www',
  server: {
    url: WEB_URL,
    cleartext: false,
    androidScheme: 'https',
    // Sólo se permite navegar dentro de la app al dominio propio; el resto se abre fuera.
    allowNavigation: [WEB_HOST],
    errorPath: 'offline.html',
  },
  android: {
    allowMixedContent: false,
    captureInput: true,
    webContentsDebuggingEnabled: false,
    backgroundColor: '#0F172A',
  },
  plugins: {
    PushNotifications: {
      presentationOptions: ['badge', 'sound', 'alert'],
    },
    SplashScreen: {
      launchAutoHide: true,
      launchShowDuration: 1500,
      launchFadeOutDuration: 300,
      backgroundColor: '#0F172A',
      androidScaleType: 'CENTER_CROP',
      showSpinner: false,
    },
  },
};

export default config;
