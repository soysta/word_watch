# ⏰ TimeSpell — Word Clock

Zamanı kelimelerle gösteren çok platformlu saat uygulaması.

**Platformlar:** Windows • Linux • Android (Duvar Kağıdı + Ekran Koruyucu) • Wear OS (Saat Yüzü)

## ✨ Özellikler

- 🕐 Saati kelimelerle gösterir (5 dakika hassasiyetle)
- 🇹🇷 Türkçe ve 🇬🇧 İngilizce dil desteği
- 🎨 Masaüstü: 30 tema • Android: 30 tema • Wear OS: 12 tema
- 🖥️ Windows/Linux masaüstü arkaplanı olarak çalışır
- 📱 Android canlı duvar kağıdı + kilit ekranı desteği
- 🌙 Android ekran koruyucu (Dream Service)
- ⌚ Wear OS saat yüzü (temaya göre font ve arka plan değişir)

## 📸 Örnek

```
S A A T   B İ R İ
Ç E Y R E K
G E Ç İ Y O R
→ "Saat biri çeyrek geçiyor" (01:15)
```

## 🚀 Kurulum

### Windows / Linux

[Releases](https://github.com/soysta/word_watch/releases) sayfasından indirin:

| Dosya | Platform |
|-------|----------|
| `TimeSpell Setup X.X.X.exe` | Windows Kurulum |
| `TimeSpell X.X.X.exe` | Windows Taşınabilir |
| `TimeSpell-X.X.X.AppImage` | Linux |
| `timespell_X.X.X_amd64.deb` | Ubuntu/Debian |

### Android

Google Play Store'dan veya Releases sayfasından APK/AAB indirin.

### Kaynak Koddan Çalıştır

```bash
# Masaüstü (Electron)
git clone https://github.com/soysta/word_watch.git
cd word_watch
npm install
npm start

# Android (Gradle)
cd android
./gradlew :app:assembleRelease
./gradlew :wear:assembleRelease
```

### Build

```bash
# Windows installer + portable
npm run build:win

# Linux AppImage + deb
npm run build:linux

# Android release AAB
cd android
./gradlew :app:bundleRelease
./gradlew :wear:bundleRelease
```

## 🎮 Kullanım

### Masaüstü

Uygulama başlatıldığında otomatik olarak masaüstü arkaplanına yerleşir.

**Sistem tepsisi menüsü** (sağ tık):
- 🇹🇷 **Dil Değiştir** — Türkçe / İngilizce
- 🎨 **Temalar** — 30 tema arasından seçim
- **Çıkış** — Uygulamayı kapat

### Android

- **Ayarlar** uygulamasından tema, dil ve kilit ekranı tercihi seçin
- **Duvar kağıdı** veya **ekran koruyucu** olarak ayarlayın

### Wear OS

- Saat yüzü olarak **TimeSpell** seçin
- Saat yüzüne uzun basarak **tema** değiştirin
- Her tema kendine özgü renk, arka plan ve font içerir

## 🎨 Masaüstü & Android Temaları (30)

| | | | |
|---|---|---|---|
| 📝 Klasik | 🌙 Gece | 🌊 Okyanus | 🔥 Neon |
| 🎮 Retro | 🌸 Pastel | 🏛️ Antik | 🚀 Uzay |
| 🎨 Renkli | 💚 Matrix | 🌅 Güneş | 💜 Mor Gece |
| 🩸 Kan Kırmızı | ✨ Altın Lüx | ❄️ Buz Mavisi | 🌿 Çimen |
| 🤖 Siberpunk | 🍬 Pembe Şeker | 🍊 Turuncu | ⚫ Gri Ton |
| 🌴 Tropikal | 🕰️ Vintage | ⚡ Elektrik | 🪵 Ahşap |
| 👾 Piksel | 🌈 Gökküşağı | 🌑 Kara Mod | 🏖️ Deniz |
| 🔴 Kızıl Gezegen | 🖋️ Klasik Koyu | | |

## ⌚ Wear OS Temaları (12)

| | | | |
|---|---|---|---|
| 🌙 Gece | 🌊 Okyanus | 🔥 Neon | 🎮 Retro |
| 💚 Matrix | ✨ Altın | 🩸 Kırmızı | 💜 Mor Gece |
| 🤖 Siberpunk | 🍊 Turuncu | ⚫ Gri Ton | ⚡ Elektrik |

## 🛠️ Teknolojiler

| Platform | Teknoloji |
|----------|-----------|
| Masaüstü | Electron, koffi (Windows API), HTML/CSS/JS |
| Android | Kotlin, Canvas API, WallpaperService, DreamService |
| Wear OS | Kotlin, WatchFace API, CanvasRenderer |

## 📁 Proje Yapısı

```
├── main.js              # Electron ana süreç
├── index.html           # Web arayüzü
├── script.js            # Saat mantığı + temalar (web)
├── styles.css           # 30 tema CSS tanımları
├── package.json         # Electron yapılandırma
└── android/
    ├── app/             # Android canlı duvar kağıdı
    │   └── src/main/java/com/timespell/wallpaper/
    │       ├── WordClockWallpaperService.kt
    │       ├── WordClockThemes.kt    # 30 tema tanımı
    │       ├── WordClockLogic.kt     # TR/EN saat mantığı
    │       ├── WordClockData.kt      # Grid ve kelime pozisyonları
    │       ├── WordClockDreamService.kt  # Ekran koruyucu
    │       └── SettingsActivity.kt   # Ayarlar ekranı
    └── wear/            # Wear OS saat yüzü
        └── src/main/java/com/timespell/wear/
            ├── WordClockWatchFaceService.kt  # 12 tema + renderer
            └── WatchFaceConfigActivity.kt    # Tema seçim ekranı
```

## 📄 Lisans

MIT
