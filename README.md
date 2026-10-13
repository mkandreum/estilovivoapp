# EstiloVivo — App Android

Contenedor nativo (Capacitor 8) de la web **https://estilovivo.xyoncloud.win**.

La APK **no empaqueta el frontend**: abre la web de producción en un WebView y expone
los plugins nativos al JavaScript de la web mediante el bridge de Capacitor. Cualquier
cambio desplegado en la web llega a la app sin publicar una nueva versión.

El código de la web y del backend vive en el repo de la web
([mkandreum/estilovivo](https://github.com/mkandreum/estilovivo)).

## Plugins nativos incluidos

| Plugin | Uso en la web |
|---|---|
| `@capacitor/app` | Botón atrás, deep links |
| `@capacitor/camera` | Fotos de prendas y probador |
| `@capacitor/filesystem` | Guardar/compartir imágenes |
| `@capacitor/splash-screen` | Splash de arranque |
| `@capacitor-firebase/*` | Push, Analytics, Crashlytics |
| `@capacitor-community/in-app-review` | Valoración en Play Store |
| `capacitor-plugin-cdv-purchase` | Suscripción Premium (Google Play Billing) |
| `EncryptedStorage` (propio) | Tokens de sesión cifrados (AES-256-GCM) |

> Si añades un plugin nativo nuevo en la web, añádelo también aquí (`npm i <plugin>`
> + `npm run sync`) y publica una nueva versión de la app.

## Requisitos

- Node 20+
- JDK 21 y Android SDK 36 (Android Studio)

## Desarrollo

```bash
npm install
npm run sync      # genera android/capacitor.config.json y los proyectos de plugins
npm run open      # abre Android Studio
```

Apuntar a otro servidor (p. ej. local o staging):

```bash
ESTILOVIVO_WEB_URL=https://staging.ejemplo.com npm run sync
```

## Release (Play Store)

1. Coloca `google-services.json` en `android/app/` (no se versiona).
2. Exporta las variables de firma y versión:
   ```bash
   export KEYSTORE_PATH=/ruta/release.keystore KEYSTORE_PASSWORD=*** KEY_ALIAS=estilovivo KEY_PASSWORD=***
   export VERSION_CODE=2 VERSION_NAME=1.0.1
   ```
3. `npm run build:release` → `android/app/build/outputs/bundle/release/app-release.aab`

## Requisitos del lado web

- Deep links verificados (`https://estilovivo.xyoncloud.win/...`): el servidor web debe
  servir `/.well-known/assetlinks.json` con el SHA-256 del certificado de firma de Play.
  El backend lo sirve desde la variable `ANDROID_SHA256_FINGERPRINTS`.
- El backend debe permitir CORS para `https://localhost` / `capacitor://localhost`
  (ya incluido).

## Seguridad

- Sólo HTTPS (`cleartextTrafficPermitted=false`); el tráfico a CA de usuario sólo en debug.
- `allowNavigation` restringido al dominio propio: enlaces externos se abren en el navegador.
- Sin backups de datos de la app (`allowBackup=false`, `data_extraction_rules`).
- Depuración remota del WebView desactivada en release.
