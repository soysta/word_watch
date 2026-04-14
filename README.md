# ⏰ Kelime Saati — Word Clock Wallpaper

Zamanı kelimelerle gösteren, masaüstü duvar kağıdı olarak çalışan bir saat uygulaması. Türkçe ve İngilizce dil desteği ile 30 farklı tema içerir.

## ✨ Özellikler

- 🕐 Saati kelimelerle gösterir (5 dakika hassasiyetle)
- 🇹🇷 Türkçe ve 🇬🇧 İngilizce dil desteği
- 🎨 30 farklı tema (Klasik, Neon, Matrix, Retro, Siberpunk vb.)
- 🖥️ Windows masaüstü arkaplanı olarak çalışır (simgelerin arkasında)
- 🔽 Sistem tepsisinden (tray) tema ve dil kontrolü

## 📸 Ekran Görüntüsü

```
S A A T   B İ R İ
Ç E Y R E K
G E Ç İ Y O R
→ "Saat biri çeyrek geçiyor" (01:15)
```

## 🚀 Kurulum

### Hazır İndir (Windows)

[Releases](https://github.com/soysta/word_watch/releases) sayfasından `.exe` dosyasını indirin.

| Dosya | Açıklama |
|-------|----------|
| `Kelime Saati Setup X.X.X.exe` | Kurulum sihirbazı |
| `Kelime Saati X.X.X.exe` | Taşınabilir (kurulum gerektirmez) |

### Kaynak Koddan Çalıştır

```bash
git clone https://github.com/soysta/word_watch.git
cd word_watch
npm install
npm start
```

### Build Al

```bash
# Windows installer + portable
npm run build:win

# Linux AppImage + deb (Linux ortamında çalıştırılmalı)
npm run build:linux
```

Oluşan dosyalar `dist/` klasöründe bulunur.

## 🎮 Kullanım

Uygulama başlatıldığında otomatik olarak masaüstü arkaplanına yerleşir.

**Sistem tepsisi menüsü** (sağ tık):
- 🇹🇷 **Dil Değiştir** — Türkçe / İngilizce
- 🎨 **Temalar** — 30 tema arasından seçim
- **Çıkış** — Uygulamayı kapat

## 🎨 Temalar

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

## 🛠️ Teknolojiler

- **Electron** — Masaüstü uygulaması
- **koffi** — Windows API (wallpaper katmanına yerleştirme)
- **HTML/CSS/JS** — Arayüz

## 📄 Lisans

MIT
