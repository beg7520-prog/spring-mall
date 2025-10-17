INSERT INTO roles (name) VALUES ('ROLE_CUSTOMER');
INSERT INTO roles (name) VALUES ('ROLE_ADMIN');

INSERT INTO users (username, password) VALUES ('admin1', '$2a$10$wlPPkurmkLbNN97B2hZpKuWU0EGelPhCcbF0AHbIasnKpxpoCz.u2');
INSERT INTO users (username, password) VALUES ('user1', '$2a$10$LZ7FsCozWmrUHYANYbR.7u/IaH7/pnY3KAnouCuW2kPeu2/Pzjg5m');
INSERT INTO users (username, password) VALUES ('user2', '$2a$10$feeYi.3UrHpsmW/9Nsli/e8F6CEA2uF7MQM4lY00Q7nxOWtsImqsq');

INSERT INTO user_roles (user_id, role_id) VALUES (1, 2);
INSERT INTO user_roles (user_id, role_id) VALUES (2, 1);
INSERT INTO user_roles (user_id, role_id) VALUES (3, 1);

INSERT INTO products (name, category, price, image_url, description) VALUES
('手工陶瓷咖啡杯 Handmade Ceramic Coffee Mug', 'Home & Living', 680, 'https://source.unsplash.com/400x400/?ceramic,mug', '手工拉胚，每個杯子都有獨特紋理 | Handmade on the wheel, each mug has unique textures.'),
('日式竹編野餐籃 Japanese Bamboo Picnic Basket', 'Home & Living', 1280, 'https://source.unsplash.com/400x400/?picnic,basket', '純手工竹編，適合週末野餐 | Handwoven bamboo, perfect for weekend picnics.'),
('冷泡咖啡禮盒 Cold Brew Coffee Gift Set', 'Food & Drink', 850, 'https://source.unsplash.com/400x400/?coffee,coldbrew', '三款單品豆冷泡，清爽無苦澀 | Three single-origin beans, refreshing and smooth.'),
('原木書架 Wooden Bookshelf', 'Furniture', 3200, 'https://source.unsplash.com/400x400/?bookshelf,wood', '簡約北歐風，適合小空間 | Minimalist Nordic style, great for small spaces.'),
('莫蘭迪色調筆記本 Morandi Tone Notebook', 'Stationery', 180, 'https://source.unsplash.com/400x400/?notebook,stationery', '柔和色系，適合日常隨筆 | Soft tones, perfect for daily journaling.'),
('香氛大豆蠟燭 Soy Wax Scented Candle', 'Home & Living', 450, 'https://source.unsplash.com/400x400/?candle,aroma', '天然大豆蠟，淡雅木質香 | Natural soy wax with subtle woody fragrance.'),
('復古便攜唱片機 Vintage Portable Record Player', 'Electronics', 2800, 'https://source.unsplash.com/400x400/?vinyl,player', '文青必備，黑膠與藍芽雙模式 | Vinyl + Bluetooth, a must for retro lovers.'),
('牛皮手帳本 Leather Journal Notebook', 'Stationery', 960, 'https://source.unsplash.com/400x400/?journal,leather', '義大利進口牛皮，隨身記事 | Italian leather, perfect for notes on the go.'),
('和風布餐墊 Japanese Linen Placemat', 'Home & Living', 260, 'https://source.unsplash.com/400x400/?placemat,linen', '亞麻材質，帶有日式簡約感 | Linen material with Japanese minimalism.'),
('玻璃花瓶 Glass Vase', 'Home & Living', 420, 'https://source.unsplash.com/400x400/?vase,flowers', '簡單透明設計，適合插單枝花 | Transparent glass, ideal for single stems.'),
('冷壓初榨橄欖油 Extra Virgin Olive Oil', 'Food & Drink', 560, 'https://source.unsplash.com/400x400/?olive,oil', '西班牙小農直送 | From Spanish small farms, cold pressed.'),
('文青帆布托特包 Canvas Tote Bag', 'Fashion', 780, 'https://source.unsplash.com/400x400/?totebag,canvas', '簡約耐用，可搭配各種穿搭 | Minimalist and durable, suits any style.'),
('浮游花擺飾 Herbarium Floral Decor', 'Home & Living', 640, 'https://source.unsplash.com/400x400/?flowers,decor', '玻璃瓶中凝結的永恆花朵 | Preserved flowers in glass bottles.'),
('手沖咖啡濾杯 Coffee Dripper', 'Food & Drink', 380, 'https://source.unsplash.com/400x400/?coffee,dripper', '陶瓷製，手沖咖啡的最佳拍檔 | Ceramic, perfect for pour-over coffee.'),
('森林系床單組 Forest Bedding Set', 'Home & Living', 2100, 'https://source.unsplash.com/400x400/?bedding,forest', '柔軟棉麻，淺綠色系 | Soft cotton-linen in forest tones.'),
('經典黑膠唱片集 Classic Vinyl Record Set', 'Music', 1500, 'https://source.unsplash.com/400x400/?vinyl,record', '爵士與民謠選集 | Jazz & folk selection.'),
('小眾調香香水 Indie Perfume', 'Beauty', 1250, 'https://source.unsplash.com/400x400/?perfume,bottle', '木質與柑橘調的中性香氛 | Woody citrus unisex fragrance.'),
('法式藤編椅 French Rattan Chair', 'Furniture', 3400, 'https://source.unsplash.com/400x400/?rattan,chair', '輕盈透氣，適合客廳或陽台 | Lightweight rattan, airy and chic.'),
('極簡金屬書籤 Minimalist Metal Bookmark', 'Stationery', 120, 'https://source.unsplash.com/400x400/?bookmark,metal', '簡單俐落，適合閱讀愛好者 | Sleek design for book lovers.'),
('單品手沖咖啡豆 Single-Origin Coffee Beans', 'Food & Drink', 780, 'https://source.unsplash.com/400x400/?coffee,beans', '衣索比亞日曬豆，果香濃郁 | Ethiopian natural process, fruity aroma.'),
('原木鬧鐘 Wooden Alarm Clock', 'Home & Living', 980, 'https://source.unsplash.com/400x400/?clock,wood', '溫潤木紋，無聲設計 | Warm wood grain, silent ticking.'),
('文青攝影集 Indie Photography Book', 'Books', 950, 'https://source.unsplash.com/400x400/?photobook,art', '收錄生活細節的溫柔視角 | Gentle captures of daily life.'),
('手作陶盤 Handmade Ceramic Plate', 'Home & Living', 560, 'https://source.unsplash.com/400x400/?plate,ceramic', '不規則邊緣，樸拙美感 | Irregular edges, rustic beauty.'),
('亞麻餐巾 Linen Napkin', 'Home & Living', 280, 'https://source.unsplash.com/400x400/?napkin,linen', '自然棉麻質地，簡單又優雅 | Natural linen, simple and elegant.'),
('旅人隨身筆 Traveler’s Brass Pen', 'Stationery', 350, 'https://source.unsplash.com/400x400/?pen,brass', '黃銅材質，隨時間呈現光澤 | Brass patina develops over time.'),
('小農果醬組合 Artisan Jam Set', 'Food & Drink', 420, 'https://source.unsplash.com/400x400/?jam,fruit', '草莓、藍莓與檸檬口味 | Strawberry, blueberry, and lemon flavors.'),
('編織收納籃 Woven Storage Basket', 'Home & Living', 750, 'https://source.unsplash.com/400x400/?basket,storage', '棉麻編織，實用又美觀 | Cotton-linen weave, practical & stylish.'),
('北歐極簡檯燈 Nordic Minimalist Lamp', 'Home & Living', 1980, 'https://source.unsplash.com/400x400/?lamp,minimalist', '柔和燈光，適合夜讀 | Soft lighting, perfect for night reading.'),
('旅行日記書 Travel Journal Book', 'Books', 600, 'https://source.unsplash.com/400x400/?journal,travel', '記錄旅程與心情的好夥伴 | Capture journeys and thoughts.'),
('手工布書套 Handmade Fabric Book Cover', 'Stationery', 450, 'https://source.unsplash.com/400x400/?bookcover,fabric', '保護書籍，也展現個人風格 | Protects books with artisanal style.');

INSERT INTO cart_items (user_id, product_id, quantity)
VALUES
(2, 1, 2),
(2, 3, 1),
(2, 2, 4);

INSERT INTO orders (user_id, total, status)
VALUES (2, 2380, 'COMPLETED');

INSERT INTO order_items (order_id, product_id, quantity, price)
VALUES (1, 1, 1, 680),
       (1, 3, 2, 1700);
