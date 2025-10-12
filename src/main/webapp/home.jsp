<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="zh">
<head>
  <meta charset="UTF-8" />
  <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
  <title>Women Fashion Style</title>
  <link rel="stylesheet" href="css/home.css"/>
</head>
<body>
  <!-- Topbar -->
  <header class="topbar">
    <div class="brand">Women Fashion<br/>Style</div>
    <nav class="mainnav">
      <a href="#home">首頁</a>
      <a href="#tops">上衣</a>
      <a href="#bottoms">下衣</a>
      <a href="#acc">其他裝飾</a>
      <a href="#bags">包包</a>
    </nav>
    <div class="actions">
      <button aria-label="search">🔍</button>
      <button aria-label="favorites">♡</button>
      <button aria-label="cart">🛒</button>
    </div>
  </header>

  <!-- Hero -->
  <section id="home" class="hero">
    <img src="https://images.unsplash.com/photo-1544441893-675973e31985?q=80&w=1600&auto=format&fit=crop" alt="Hero">
    <div class="overlay">
      <div class="copy">
        <span class="badge">新品登場</span>
        <h1 style="margin:10px 0 6px 0;">夏日</h1>
        <p style="opacity:.92">把可愛角色融入日常設計，透氣耐穿、輕鬆穿搭。</p>
        <div class="cat-strip">
          <a class="chip" href="#tops">逛 上衣</a>
          <a class="chip" href="#bottoms">逛 下衣</a>
        </div>
      </div>
    </div>
  </section>

  <!-- 上衣（純 CSS tabs，無 JS） -->
  <section id="tops" class="wrap">
    <h2>上衣</h2>

    <div class="tabs">
      <!-- radios（隱藏），label 當按鈕 -->
      <input type="radio" name="cat" id="tab-t" checked>
      <label for="tab-t">T恤</label>

      <input type="radio" name="cat" id="tab-shirt">
      <label for="tab-shirt">襯衫</label>

      <input type="radio" name="cat" id="tab-knit">
      <label for="tab-knit">針織/毛衣</label>

      <input type="radio" name="cat" id="tab-outer">
      <label for="tab-outer">外套</label>

      <input type="radio" name="cat" id="tab-sport">
      <label for="tab-sport">運動上衣</label>

      <!-- Panels -->
      <div class="panels">
        <!-- T恤 -->
        <div class="panel p-t grid">
          <article class="card">
            <img src="images/acc/roundnetT-shirt.jpg" alt="純色圓領T">
            <div class="meta">
              <div class="name">純色圓領T</div>
              <div class="price">NT$ 299</div>
              <a class="btn" href="#tops">加入購物車</a>
            </div>
          </article>
          <article class="card">
            <img src="images/acc/pocketT-shirt.jpg" alt="口袋短T">
            <div class="meta">
              <div class="name">口袋短T</div>
              <div class="price">NT$ 329</div>
              <a class="btn" href="#tops">加入購物車</a>
            </div>
          </article>
          <article class="card">
            <img src="images/acc/styleT-shirt.jpg" alt="寬鬆印花T">
            <div class="meta">
              <div class="name">寬鬆印花T</div>
              <div class="price">NT$ 399</div>
              <a class="btn" href="#tops">加入購物車</a>
            </div>
          </article>
        </div>

        <!-- 襯衫 -->
        <div class="panel p-shirt grid">
          <article class="card">
            <img src="https://images.unsplash.com/photo-1520975693415-8b456906c813?q=80&w=1200" alt="亞麻襯衫">
            <div class="meta">
              <div class="name">亞麻襯衫</div>
              <div class="price">NT$ 799</div>
              <a class="btn" href="#tops">加入購物車</a>
            </div>
          </article>
          <article class="card">
            <img src="https://images.unsplash.com/photo-1503342394121-6a6f2a0e6d91?q=80&w=1200" alt="條紋襯衫">
            <div class="meta">
              <div class="name">條紋襯衫</div>
              <div class="price">NT$ 699</div>
              <a class="btn" href="#tops">加入購物車</a>
            </div>
          </article>
        </div>

        <!-- 針織/毛衣 -->
        <div class="panel p-knit grid">
          <article class="card">
            <img src="https://images.unsplash.com/photo-1520975922284-c8e26ac2c74f?q=80&w=1200" alt="細針織上衣">
            <div class="meta">
              <div class="name">細針織上衣</div>
              <div class="price">NT$ 899</div>
              <a class="btn" href="#tops">加入購物車</a>
            </div>
          </article>
          <article class="card">
            <img src="https://images.unsplash.com/photo-1483985988355-763728e1935b?q=80&w=1200" alt="羊毛開襟衫">
            <div class="meta">
              <div class="name">羊毛開襟衫</div>
              <div class="price">NT$ 1299</div>
              <a class="btn" href="#tops">加入購物車</a>
            </div>
          </article>
        </div>

        <!-- 外套 -->
        <div class="panel p-outer grid">
          <article class="card">
            <img src="https://images.unsplash.com/photo-1520975693415-8b456906c813?q=80&w=1200" alt="薄風衣">
            <div class="meta">
              <div class="name">薄風衣</div>
              <div class="price">NT$ 1499</div>
              <a class="btn" href="#tops">加入購物車</a>
            </div>
          </article>
          <article class="card">
            <img src="https://images.unsplash.com/photo-1483985988355-763728e1935b?q=80&w=1200" alt="牛仔外套">
            <div class="meta">
              <div class="name">牛仔外套</div>
              <div class="price">NT$ 1299</div>
              <a class="btn" href="#tops">加入購物車</a>
            </div>
          </article>
        </div>

        <!-- 運動上衣 -->
        <div class="panel p-sport grid">
          <article class="card">
            <img src="https://images.unsplash.com/photo-1520975788664-6b9f2a1a01b6?q=80&w=1200" alt="快乾運動T">
            <div class="meta">
              <div class="name">快乾運動T</div>
              <div class="price">NT$ 499</div>
              <a class="btn" href="#tops">加入購物車</a>
            </div>
          </article>
          <article class="card">
            <img src="https://images.unsplash.com/photo-1520975693415-8b456906c813?q=80&w=1200" alt="連帽運動外套">
            <div class="meta">
              <div class="name">連帽運動外套</div>
              <div class="price">NT$ 1099</div>
              <a class="btn" href="#tops">加入購物車</a>
            </div>
          </article>
        </div>
      </div>
    </div>
  </section>

  <!-- 其他區塊（占位） -->
  <section id="bottoms" class="wrap">
    <h2>下衣</h2>
    <div class="grid">
      <article class="card">
        <img src="https://images.unsplash.com/photo-1519741497674-611481863552?q=80&w=1200" alt="高腰直筒牛仔褲">
        <div class="meta">
          <div class="name">高腰直筒牛仔褲</div>
          <div class="price">NT$ 899</div>
          <div>
            <a class="btn" href="pages/bottoms/jeans-highrise.html" target="_blank" rel="noopener">查看</a>
            <a class="btn" href="#bottoms">加入購物車</a>
          </div>
        </div>
      </article>
      <article class="card">
        <img src="https://images.unsplash.com/photo-1490481651871-ab68de25d43d?q=80&w=1200" alt="A字短裙">
        <div class="meta">
          <div class="name">A字短裙</div>
          <div class="price">NT$ 699</div>
          <div>
            <a class="btn" href="pages/bottoms/skirt-a.html" target="_blank" rel="noopener">查看</a>
            <a class="btn" href="#bottoms">加入購物車</a>
          </div>
        </div>
      </article>
      <article class="card">
        <img src="https://images.unsplash.com/photo-1542060748-10c28b62716d?q=80&w=1200" alt="寬鬆運動長褲">
        <div class="meta">
          <div class="name">寬鬆運動長褲</div>
          <div class="price">NT$ 799</div>
          <div>
            <a class="btn" href="pages/bottoms/jogger-wide.html" target="_blank" rel="noopener">查看</a>
            <a class="btn" href="#bottoms">加入購物車</a>
          </div>
        </div>
      </article>
      <article class="card">
        <img src="https://images.unsplash.com/photo-1541099649105-f69ad21f3246?q=80&w=1200" alt="針織長裙">
        <div class="meta">
          <div class="name">針織長裙</div>
          <div class="price">NT$ 899</div>
          <div>
            <a class="btn" href="pages/bottoms/skirt-knit.html" target="_blank" rel="noopener">查看</a>
            <a class="btn" href="#bottoms">加入購物車</a>
          </div>
        </div>
      </article>
    </div>
  </section>
  

  <section id="acc" class="wrap">
    <h2>其他裝飾</h2>
    <div class="grid">
      <article class="card">
        <img src="https://images.unsplash.com/photo-1520974735194-9d0d7faa9b2d?q=80&w=1200" alt="棒球帽">
        <div class="meta">
          <div class="name">棉質棒球帽</div>
          <div class="price">NT$ 390</div>
          <div>
            <a class="btn" href="pages/acc/cap-classic.html" target="_blank" rel="noopener">查看</a>
            <a class="btn" href="#acc">加入購物車</a>
          </div>
        </div>
      </article>
      <article class="card">
        <img src="https://images.unsplash.com/photo-1523292562811-8fa7962a78c8?q=80&w=1200" alt="珍珠項鍊">
        <div class="meta">
          <div class="name">珍珠短項鍊</div>
          <div class="price">NT$ 590</div>
          <div>
            <a class="btn" href="pages/acc/necklace-pearl.html" target="_blank" rel="noopener">查看</a>
            <a class="btn" href="#acc">加入購物車</a>
          </div>
        </div>
      </article>
      <article class="card">
        <img src="https://images.unsplash.com/photo-1520975961572-6a32b1a275b7?q=80&w=1200" alt="細皮帶">
        <div class="meta">
          <div class="name">細皮帶（咖）</div>
          <div class="price">NT$ 320</div>
          <div>
            <a class="btn" href="pages/acc/belt-thin.html" target="_blank" rel="noopener">查看</a>
            <a class="btn" href="#acc">加入購物車</a>
          </div>
        </div>
      </article>
      <article class="card">
        <img src="https://images.unsplash.com/photo-1508047631740-39510c1d2fd4?q=80&w=1200" alt="針織毛帽">
        <div class="meta">
          <div class="name">針織毛帽</div>
          <div class="price">NT$ 350</div>
          <div>
            <a class="btn" href="pages/acc/beanie-knit.html" target="_blank" rel="noopener">查看</a>
            <a class="btn" href="#acc">加入購物車</a>
          </div>
        </div>
      </article>
    </div>
  </section>
  

  <section id="bags" class="wrap">
    <h2>包包</h2>
    <div class="grid">
      <article class="card">
        <img src="https://images.unsplash.com/photo-1520614073990-dd6026db0d4d?q=80&w=1200" alt="托特包">
        <div class="meta">
          <div class="name">厚帆布托特包</div>
          <div class="price">NT$ 690</div>
          <div>
            <a class="btn" href="pages/bags/tote-canvas.html" target="_blank" rel="noopener">查看</a>
            <a class="btn" href="#bags">加入購物車</a>
          </div>
        </div>
      </article>
      <article class="card">
        <img src="https://images.unsplash.com/photo-1520975916090-3105956dac38?q=80&w=1200" alt="斜背包">
        <div class="meta">
          <div class="name">輕量斜背包</div>
          <div class="price">NT$ 890</div>
          <div>
            <a class="btn" href="pages/bags/crossbody-lite.html" target="_blank" rel="noopener">查看</a>
            <a class="btn" href="#bags">加入購物車</a>
          </div>
        </div>
      </article>
      <article class="card">
        <img src="https://images.unsplash.com/photo-1520975592249-92c2c74a0662?q=80&w=1200" alt="小方包">
        <div class="meta">
          <div class="name">小方包（黑）</div>
          <div class="price">NT$ 990</div>
          <div>
            <a class="btn" href="pages/bags/mini-box.html" target="_blank" rel="noopener">查看</a>
            <a class="btn" href="#bags">加入購物車</a>
          </div>
        </div>
      </article>
      <article class="card">
        <img src="https://images.unsplash.com/photo-1520976062615-0121f56e84cd?q=80&w=1200" alt="肩背水桶包">
        <div class="meta">
          <div class="name">肩背水桶包</div>
          <div class="price">NT$ 1,290</div>
          <div>
            <a class="btn" href="pages/bags/bucket-shoulder.html" target="_blank" rel="noopener">查看</a>
            <a class="btn" href="#bags">加入購物車</a>
          </div>
        </div>
      </article>
    </div>
  </section>
  

  <script src="js/home.js" defer></script> <!-- 可留空；預留擴充 -->
</body>
</html>
    