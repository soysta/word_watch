const { app, BrowserWindow, Tray, Menu, screen, nativeImage, ipcMain } = require('electron');
const path = require('path');

let mainWindow;
let tray;

// ==================== TEMA LİSTESİ (tray menüsü için) ====================
const THEMES = [
  '📝 Klasik', '🌙 Gece', '🌊 Okyanus', '🔥 Neon', '🎮 Retro',
  '🌸 Pastel', '🏛️ Antik', '🚀 Uzay', '🎨 Renkli', '💚 Matrix',
  '🌅 Güneş', '💜 Mor Gece', '🩸 Kan Kırmızı', '✨ Altın Lüx', '❄️ Buz Mavisi',
  '🌿 Çimen', '🤖 Siberpunk', '🍬 Pembe Şeker', '🍊 Turuncu', '⚫ Gri Ton',
  '🌴 Tropikal', '🕰️ Vintage', '⚡ Elektrik', '🪵 Ahşap', '👾 Piksel',
  '🌈 Gökküşağı', '🌑 Kara Mod', '🏖️ Deniz', '🔴 Kızıl Gezegen', '🖋️ Klasik Koyu'
];

// ==================== WINDOWS WALLPAPER API ====================
function attachToDesktop(browserWindow) {
  if (process.platform !== 'win32') return;

  try {
    const koffi = require('koffi');
    const user32 = koffi.load('user32.dll');

    const FindWindowW = user32.func('intptr __stdcall FindWindowW(str16 cls, str16 wnd)');
    const SendMessageTimeoutW = user32.func('intptr __stdcall SendMessageTimeoutW(intptr hwnd, uint msg, uintptr wp, intptr lp, uint flags, uint timeout, _Out_ intptr *result)');
    const FindWindowExW = user32.func('intptr __stdcall FindWindowExW(intptr parent, intptr after, str16 cls, str16 wnd)');
    const SetParent = user32.func('intptr __stdcall SetParent(intptr child, intptr newParent)');
    const SetWindowPos = user32.func('int __stdcall SetWindowPos(intptr hwnd, intptr after, int x, int y, int cx, int cy, uint flags)');

    const progman = FindWindowW('Progman', null);
    if (!progman) return;

    // Progman'a mesaj gönder — WorkerW oluştur
    const resultArr = [0];
    SendMessageTimeoutW(progman, 0x052C, 0xD, 1, 0x0000, 1000, resultArr);

    // SHELLDLL_DefView'ı bul (Progman'da veya WorkerW'de)
    let shellViewParent = 0;
    let shellView = FindWindowExW(progman, 0, 'SHELLDLL_DefView', null);
    if (shellView) {
      shellViewParent = progman;
    } else {
      let ww = FindWindowExW(0, 0, 'WorkerW', null);
      while (ww) {
        shellView = FindWindowExW(ww, 0, 'SHELLDLL_DefView', null);
        if (shellView) {
          shellViewParent = ww;
          break;
        }
        ww = FindWindowExW(0, ww, 'WorkerW', null);
      }
    }

    const bounds = screen.getPrimaryDisplay().bounds;
    const hwndBuf = browserWindow.getNativeWindowHandle();
    const electronHwnd = hwndBuf.length >= 8 ? Number(hwndBuf.readBigUInt64LE(0)) : hwndBuf.readUInt32LE(0);

    if (shellView && shellViewParent) {
      // Pencereyi simgelerin bulunduğu pencerenin çocuğu yap
      SetParent(electronHwnd, shellViewParent);
      // SHELLDLL_DefView'ın arkasına yerleştir (shellView precedes electronHwnd in z-order)
      // SWP_NOACTIVATE=0x0010 | SWP_SHOWWINDOW=0x0040
      SetWindowPos(electronHwnd, shellView, 0, 0, bounds.width, bounds.height, 0x0010 | 0x0040);
    } else {
      // Fallback
      SetParent(electronHwnd, progman);
      SetWindowPos(electronHwnd, 1, 0, 0, bounds.width, bounds.height, 0x0040);
    }
  } catch (e) {
    console.error('[Wallpaper] Hata:', e);
  }
}

// ==================== PENCERE OLUŞTURMA ====================
function createWindow() {
  const fullBounds = screen.getPrimaryDisplay().bounds;

  mainWindow = new BrowserWindow({
    x: fullBounds.x,
    y: fullBounds.y,
    width: fullBounds.width,
    height: fullBounds.height,
    frame: false,
    skipTaskbar: true,
    resizable: false,
    movable: false,
    autoHideMenuBar: true,
    webPreferences: {
      nodeIntegration: false,
      contextIsolation: true
    }
  });

  mainWindow.loadFile('index.html');

  // Pencere hazır olunca wallpaper'a ekle
  mainWindow.webContents.on('did-finish-load', () => {
    attachToDesktop(mainWindow);
  });

  mainWindow.on('closed', () => {
    mainWindow = null;
  });
}

// ==================== TRAY MENÜSÜ ====================
function createTray() {
  const icon = nativeImage.createFromPath(path.join(__dirname, 'icon-tray.png'));

  tray = new Tray(icon);
  tray.setToolTip('Kelime Saati');
  buildTrayMenu();
}

function buildTrayMenu() {
  const themeItems = THEMES.map((name, i) => ({
    label: name,
    type: 'radio',
    checked: false,
    click: () => {
      if (mainWindow) {
        mainWindow.webContents.executeJavaScript(`applyTheme(${i})`);
      }
    }
  }));

  const contextMenu = Menu.buildFromTemplate([
    { label: '⏰ Kelime Saati', enabled: false },
    { type: 'separator' },
    {
      label: '🇹🇷 Dil Değiştir (TR/EN)',
      click: () => {
        if (mainWindow) {
          mainWindow.webContents.executeJavaScript('toggleLanguage()');
        }
      }
    },
    { type: 'separator' },
    {
      label: '🎨 Temalar',
      submenu: themeItems
    },
    { type: 'separator' },
    {
      label: 'Çıkış',
      click: () => {
        app.isQuitting = true;
        app.quit();
      }
    }
  ]);

  tray.setContextMenu(contextMenu);
}

// ==================== BAŞLATMA ====================
app.whenReady().then(() => {
  createWindow();
  createTray();

  app.on('activate', () => {
    if (BrowserWindow.getAllWindows().length === 0) {
      createWindow();
    }
  });
});

app.on('window-all-closed', () => {
  // Tray'de çalışmaya devam et
});
