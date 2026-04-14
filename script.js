// ==================== KELIME SAATI VERİLERİ ====================
const GRID = [
  "ITLISXATHPMA",
  "ACFIFTEENDCO",
  "TWENTYFIVEXW",
  "THIRTYFTENOS",
  "MINUTESETOUR",
  "PASTORUFOURT",
  "SEVENXTWELVE",
  "NINEFIVECTWO",
  "EIGHTFELEVEN",
  "SIXTHREEONEG",
  "TENSEZOCLOCK"
];

const WORDS = {
  IT:      [[0,0],[0,1]],
  IS:      [[0,3],[0,4]],
  A:       [[1,0]],
  FIFTEEN: [[1,2],[1,3],[1,4],[1,5],[1,6],[1,7],[1,8]],
  TWENTY:  [[2,0],[2,1],[2,2],[2,3],[2,4],[2,5]],
  FIVE_M:  [[2,6],[2,7],[2,8],[2,9]],
  THIRTY:  [[3,0],[3,1],[3,2],[3,3],[3,4],[3,5]],
  TEN_M:   [[3,7],[3,8],[3,9]],
  MINUTES: [[4,0],[4,1],[4,2],[4,3],[4,4],[4,5],[4,6]],
  PAST:    [[5,0],[5,1],[5,2],[5,3]],
  TO:      [[4,8],[4,9]],
  FOUR:    [[5,7],[5,8],[5,9],[5,10]],
  SEVEN:   [[6,0],[6,1],[6,2],[6,3],[6,4]],
  TWELVE:  [[6,6],[6,7],[6,8],[6,9],[6,10],[6,11]],
  NINE:    [[7,0],[7,1],[7,2],[7,3]],
  FIVE_H:  [[7,4],[7,5],[7,6],[7,7]],
  TWO:     [[7,9],[7,10],[7,11]],
  EIGHT:   [[8,0],[8,1],[8,2],[8,3],[8,4]],
  ELEVEN:  [[8,6],[8,7],[8,8],[8,9],[8,10],[8,11]],
  SIX:     [[9,0],[9,1],[9,2]],
  THREE:   [[9,3],[9,4],[9,5],[9,6],[9,7]],
  ONE:     [[9,8],[9,9],[9,10]],
  TEN_H:   [[10,0],[10,1],[10,2]],
  OCLOCK:  [[10,6],[10,7],[10,8],[10,9],[10,10],[10,11]]
};

const HOUR_KEYS = ['TWELVE','ONE','TWO','THREE','FOUR','FIVE_H','SIX','SEVEN','EIGHT','NINE','TEN_H','ELEVEN','TWELVE'];

// ==================== TÜRKÇE KELIME SAATI VERİLERİ ====================
const GRID_TR = [
  "SAATXASOYSTASO", // 0
  "BİRİXİKİYİXÜÇÜ", // 1 (Saatler -i)
  "DÖRDÜXBEŞİXXXX", // 2
  "ALTIYIXXYEDİYİ", // 3
  "SEKİZİXDOKUZUX", // 4
  "ONUXONBİRİDÖRT", // 5
  "ONİKİYİXBİREXX", // 6 (Saatler -e)
  "İKİYEXÜÇEDÖRDE", // 7
  "BEŞEXALTIYAONA", // 8
  "YEDİYEXXSEKİZE", // 9
  "DOKUZAXXONBİRE", // 10
  "ONİKİYEXXYİRMİ", // 11 (Dakikalar)
  "ONXBEŞXXÇEYREK", // 12
  "BUÇUKXXXVARXXX", // 13 (Fiiller)
  "XXXXXXXGEÇİYOR", // 14
];

const WORDS_TR = {
  // Başlık
  SAAT:      [[0,0],[0,1],[0,2],[0,3]],
  // Temel saat formları (tam saat ve buçuk için)
  BİR:       [[1,0],[1,1],[1,2]],
  İKİ:       [[1,5],[1,6],[1,7]],
  ÜÇ:        [[1,11],[1,12]],
  DÖRT:      [[5,11],[5,12],[5,13],[5,14]],
  BEŞ_H:     [[2,6],[2,7],[2,8]],
  ALTI:      [[3,0],[3,1],[3,2],[3,3]],
  YEDİ:      [[3,8],[3,9],[3,10],[3,11]],
  SEKİZ:     [[4,0],[4,1],[4,2],[4,3],[4,4]],
  DOKUZ:     [[4,7],[4,8],[4,9],[4,10],[4,11]],
  ON_H:      [[5,0],[5,1]],
  ONBİR:     [[5,4],[5,5],[5,6],[5,7],[5,8]],
  ONİKİ:     [[6,0],[6,1],[6,2],[6,3],[6,4]],
  // Belirtme hali (-i) (geçiyor ile kullanılır)
  BİRİ:      [[1,0],[1,1],[1,2],[1,3]],
  İKİYİ:     [[1,5],[1,6],[1,7],[1,8],[1,9]],
  ÜÇÜ:       [[1,11],[1,12],[1,13]],
  DÖRDÜ:     [[2,0],[2,1],[2,2],[2,3],[2,4]],
  BEŞİ:      [[2,6],[2,7],[2,8],[2,9]],
  ALTIYI:    [[3,0],[3,1],[3,2],[3,3],[3,4],[3,5]],
  YEDİYİ:    [[3,8],[3,9],[3,10],[3,11],[3,12],[3,13]],
  SEKİZİ:    [[4,0],[4,1],[4,2],[4,3],[4,4],[4,5]],
  DOKUZU:    [[4,7],[4,8],[4,9],[4,10],[4,11],[4,12]],
  ONU:       [[5,0],[5,1],[5,2]],
  ONBİRİ:    [[5,4],[5,5],[5,6],[5,7],[5,8],[5,9]],
  ONİKİYİ:   [[6,0],[6,1],[6,2],[6,3],[6,4],[6,5],[6,6]],
  // Yönelme hali (-e) (var ile kullanılır)
  BİRE:      [[6,8],[6,9],[6,10],[6,11]],
  İKİYE:     [[7,0],[7,1],[7,2],[7,3],[7,4]],
  ÜÇE:       [[7,6],[7,7],[7,8]],
  DÖRDE:     [[7,9],[7,10],[7,11],[7,12],[7,13]],
  BEŞE:      [[8,0],[8,1],[8,2],[8,3]],
  ALTIYA:    [[8,5],[8,6],[8,7],[8,8],[8,9],[8,10]],
  ONA:       [[8,11],[8,12],[8,13]],
  YEDİYE:    [[9,0],[9,1],[9,2],[9,3],[9,4],[9,5]],
  SEKİZE:    [[9,8],[9,9],[9,10],[9,11],[9,12],[9,13]],
  DOKUZA:    [[10,0],[10,1],[10,2],[10,3],[10,4],[10,5]],
  ONBİRE:    [[10,8],[10,9],[10,10],[10,11],[10,12],[10,13]],
  ONİKİYE:   [[11,0],[11,1],[11,2],[11,3],[11,4],[11,5],[11,6]],
  // Dakika kelimeleri
  BEŞ_M:     [[12,3],[12,4],[12,5]],
  ON_M:      [[12,0],[12,1]],
  YİRMİ:     [[11,9],[11,10],[11,11],[11,12],[11,13]],
  ÇEYREK:    [[12,8],[12,9],[12,10],[12,11],[12,12],[12,13]],
  // Fiiller
  BUÇUK:     [[13,0],[13,1],[13,2],[13,3],[13,4]],
  VAR:       [[13,8],[13,9],[13,10]],
  GEÇİYOR:   [[14,7],[14,8],[14,9],[14,10],[14,11],[14,12],[14,13]],
};

const HOUR_BASE_KEYS_TR = ['ONİKİ','BİR','İKİ','ÜÇ','DÖRD','BEŞ_H','ALTI','YEDİ','SEKİZ','DOKUZ','ON_H','ONBİR','ONİKİ'];
const HOUR_ACC_KEYS_TR  = ['ONİKİYİ','BİRİ','İKİYİ','ÜÇÜ','DÖRDÜ','BEŞİ','ALTIYI','YEDİYİ','SEKİZİ','DOKUZU','ONU','ONBİRİ','ONİKİYİ'];
const HOUR_DAT_KEYS_TR  = ['ONİKİYE','BİRE','İKİYE','ÜÇE','DÖRDE','BEŞE','ALTIYA','YEDİYE','SEKİZE','DOKUZA','ONA','ONBİRE','ONİKİYE'];

// ==================== TEMA LİSTESİ ====================
const THEMES = [
  { name: '📝 Klasik', icon: '📝' },
  { name: '🌙 Gece', icon: '🌙' },
  { name: '🌊 Okyanus', icon: '🌊' },
  { name: '🔥 Neon', icon: '🔥' },
  { name: '🎮 Retro', icon: '🎮' },
  { name: '🌸 Pastel', icon: '🌸' },
  { name: '🏛️ Antik', icon: '🏛️' },
  { name: '🚀 Uzay', icon: '🚀' },
  { name: '🎨 Renkli', icon: '🎨' },
  { name: '💚 Matrix', icon: '💚' },
  { name: '🌅 Güneş', icon: '🌅' },
  { name: '💜 Mor Gece', icon: '💜' },
  { name: '🩸 Kan Kırmızı', icon: '🩸' },
  { name: '✨ Altın Lüx', icon: '✨' },
  { name: '❄️ Buz Mavisi', icon: '❄️' },
  { name: '🌿 Çimen', icon: '🌿' },
  { name: '🤖 Siberpunk', icon: '🤖' },
  { name: '🍬 Pembe Şeker', icon: '🍬' },
  { name: '🍊 Turuncu', icon: '🍊' },
  { name: '⚫ Gri Ton', icon: '⚫' },
  { name: '🌴 Tropikal', icon: '🌴' },
  { name: '🕰️ Vintage', icon: '🕰️' },
  { name: '⚡ Elektrik', icon: '⚡' },
  { name: '🪵 Ahşap', icon: '🪵' },
  { name: '👾 Piksel', icon: '👾' },
  { name: '🌈 Gökküşağı', icon: '🌈' },
  { name: '🌑 Kara Mod', icon: '🌑' },
  { name: '🏖️ Deniz', icon: '🏖️' },
  { name: '🔴 Kızıl Gezegen', icon: '🔴' },
  { name: '🖋️ Klasik Koyu', icon: '🖋️' }
];

let currentTheme = 0;
let isPanelOpen = false;
let currentLang = 'tr'; // 'en' veya 'tr'

// ==================== TEMA YÖNETİMİ ====================
function initTheme() {
  const savedTheme = localStorage.getItem('wordClockTheme');
  if (savedTheme !== null && savedTheme < THEMES.length) {
    currentTheme = parseInt(savedTheme);
  }
  applyTheme(currentTheme);
  setupThemePanel();
  setupThemeButtons();
  setupToggle();
}

function setupToggle() {
  const toggle = document.getElementById('themeToggle');
  toggle.addEventListener('click', () => {
    isPanelOpen = !isPanelOpen;
    document.getElementById('themePanel').classList.toggle('open', isPanelOpen);
  });
}

function setupThemePanel() {
  const themeList = document.getElementById('themeList');
  themeList.innerHTML = '';
  
  THEMES.forEach((theme, index) => {
    const btn = document.createElement('button');
    btn.className = 'theme-item';
    btn.textContent = theme.name;
    btn.dataset.theme = index;
    if (index === currentTheme) {
      btn.classList.add('active');
    }
    themeList.appendChild(btn);
  });
}

function setupThemeButtons() {
  document.querySelectorAll('.theme-item').forEach(btn => {
    btn.addEventListener('click', () => {
      const themeIndex = parseInt(btn.dataset.theme);
      applyTheme(themeIndex);
    });
  });
}

function applyTheme(themeIndex) {
  document.body.className = `theme-${themeIndex}`;
  currentTheme = themeIndex;
  localStorage.setItem('wordClockTheme', themeIndex.toString());
  
  document.querySelectorAll('.theme-item').forEach(btn => {
    btn.classList.remove('active');
    if (parseInt(btn.dataset.theme) === themeIndex) {
      btn.classList.add('active');
    }
  });
}

// Klavye ile tema değiştirme (1-9 tuşları)
document.addEventListener('keydown', (e) => {
  if (e.key >= '1' && e.key <= '9') {
    applyTheme(parseInt(e.key) - 1);
  }
  if (e.key === 'Escape' && isPanelOpen) {
    isPanelOpen = false;
    document.getElementById('themePanel').classList.remove('open');
  }
});

// ==================== SAAT İŞLEVLERİ ====================
function initClock() {
  const clock = document.getElementById('clock');
  const grid = currentLang === 'tr' ? GRID_TR : GRID;
  clock.innerHTML = '';
  clock.style.gridTemplateColumns = `repeat(${grid[0].length}, 1fr)`;
  grid.forEach((row, r) => {
    [...row].forEach((char, c) => {
      const d = document.createElement('div');
      d.className = 'letter';
      d.id = `L-${r}-${c}`;
      d.textContent = char;
      clock.appendChild(d);
    });
  });
}

function updateClock() {
  const now = new Date();
  const h = now.getHours();
  const m = now.getMinutes();
  const roundedM = Math.round(m / 5) * 5;
  
  let dHour = h % 12;
  let dMin = roundedM;
  
  if (roundedM === 60) {
    dMin = 0;
    dHour = (dHour + 1) % 12;
  }

  const nHour = (dHour + 1) % 12;
  const active = new Set();
  const words = currentLang === 'tr' ? WORDS_TR : WORDS;

  if (currentLang === 'tr') {
    active.add('SAAT');
    if (dMin === 0) {
      // Tam saat: "saat bir"
      active.add(HOUR_BASE_KEYS_TR[dHour]);
    } else if (dMin === 30) {
      // Buçuk: "saat bir buçuk"
      active.add(HOUR_BASE_KEYS_TR[dHour]);
      active.add('BUÇUK');
    } else if (dMin <= 25) {
      // Geçiyor: "saat biri beş geçiyor" (belirtme hali + dakika + geçiyor)
      active.add(HOUR_ACC_KEYS_TR[dHour]);
      if (dMin === 5) active.add('BEŞ_M');
      else if (dMin === 10) active.add('ON_M');
      else if (dMin === 15) active.add('ÇEYREK');
      else if (dMin === 20) active.add('YİRMİ');
      else if (dMin === 25) { active.add('YİRMİ'); active.add('BEŞ_M'); }
      active.add('GEÇİYOR');
    } else {
      // Var: "saat bire beş var" (yönelme hali + dakika + var)
      active.add(HOUR_DAT_KEYS_TR[nHour]);
      const remaining = 60 - dMin;
      if (remaining === 5) active.add('BEŞ_M');
      else if (remaining === 10) active.add('ON_M');
      else if (remaining === 15) active.add('ÇEYREK');
      else if (remaining === 20) active.add('YİRMİ');
      else if (remaining === 25) { active.add('YİRMİ'); active.add('BEŞ_M'); }
      active.add('VAR');
    }
  } else {
    active.add('IT'); active.add('IS');
    if (dMin === 0) {
      active.add(HOUR_KEYS[dHour]);
      active.add('OCLOCK');
    } else {
      if (dMin === 5) { active.add('FIVE_M'); active.add('MINUTES'); }
      else if (dMin === 10) { active.add('TEN_M'); active.add('MINUTES'); }
      else if (dMin === 15) { active.add('A'); active.add('FIFTEEN'); active.add('MINUTES'); }
      else if (dMin === 20) { active.add('TWENTY'); active.add('MINUTES'); }
      else if (dMin === 25) { active.add('TWENTY'); active.add('FIVE_M'); active.add('MINUTES'); }
      else if (dMin === 30) { active.add('THIRTY'); active.add('MINUTES'); }
      else if (dMin === 35) { active.add('TWENTY'); active.add('FIVE_M'); active.add('MINUTES'); }
      else if (dMin === 40) { active.add('TWENTY'); active.add('MINUTES'); }
      else if (dMin === 45) { active.add('A'); active.add('FIFTEEN'); active.add('MINUTES'); }
      else if (dMin === 50) { active.add('TEN_M'); active.add('MINUTES'); }
      else if (dMin === 55) { active.add('FIVE_M'); active.add('MINUTES'); }

      if (dMin <= 30) {
        active.add('PAST');
        active.add(HOUR_KEYS[dHour]);
      } else {
        active.add('TO');
        active.add(HOUR_KEYS[nHour]);
      }
    }
  }

  document.querySelectorAll('.letter').forEach(el => {
    el.classList.remove('on');
  });
  
  active.forEach(key => {
    (words[key] || []).forEach(([r, c]) => {
      const el = document.getElementById(`L-${r}-${c}`);
      if (el) {
        el.classList.add('on');
      }
    });
  });

  document.getElementById('digital-time').textContent = now.toLocaleTimeString(currentLang === 'tr' ? 'tr-TR' : 'en-US');
}

// ==================== DİL DEĞİŞTİRME ====================
function initLang() {
  const savedLang = localStorage.getItem('wordClockLang');
  if (savedLang === 'en' || savedLang === 'tr') {
    currentLang = savedLang;
  }
  updateLangButton();
}

function toggleLanguage() {
  currentLang = currentLang === 'tr' ? 'en' : 'tr';
  localStorage.setItem('wordClockLang', currentLang);
  updateLangButton();
  initClock();
  updateClock();
}

function updateLangButton() {
  const btn = document.getElementById('langToggle');
  if (btn) {
    btn.textContent = currentLang === 'tr' ? '🇹🇷 TR' : '🇬🇧 EN';
  }
}

// ==================== BAŞLATMA ====================
document.addEventListener('DOMContentLoaded', () => {
  initLang();
  initTheme();
  initClock();
  updateClock();
  setInterval(updateClock, 1000);
  document.getElementById('langToggle').addEventListener('click', toggleLanguage);
});
