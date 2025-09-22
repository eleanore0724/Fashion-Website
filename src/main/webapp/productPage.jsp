<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>商品詳情 - 時尚女裝</title>
<link rel="stylesheet" href="css/productPage.css">

</head>
<body>
	<%@include file="components/header.jsp"%>
	<%@include file="components/productCatalog.jsp"%>

	<main class="main-content">
		<div class="container">
			<div class="product-detail">
				<!-- 商品圖片區域 -->
				<div class="product-images">
					<div class="main-image">
						<img id="mainImage" src="images/clothes/A1001/A1001.png" alt="女裝上衣主圖">
					</div>
					<div class="thumbnail-images">
						<div class="thumbnail active"
							onclick="changeMainImage(this, 'images/clothes/A1001/B1001.jpg?text=女裝主圖')">
							<img src="images/clothes/A1001/A1001.png" alt="縮圖1">
						</div>
						<div class="thumbnail"
							onclick="changeMainImage(this, 'images/clothes/A1001/C1001.png?text=女裝圖2')">
							<img
								src="images/clothes/A1001/C1001.png"
								alt="縮圖2">
						</div>
						<div class="thumbnail"
							onclick="changeMainImage(this, 'images/clothes/A1001/C1001.png?text=女裝圖3')">
							<img
								src="images/clothes/A1001/C1001.png"
								alt="縮圖2">
						</div>
					</div>
				</div>

				<!-- 商品信息區域 -->
				<div class="product-info">
					<h1 class="product-title">小背心</h1>

					<div class="product-price">
						<span class="current-price">NT$ 980</span>
					</div>

					<div class="product-description">
						<p>這款時尚優雅的連衣裙採用高品質面料製作，剪裁精緻，設計簡約大方。適合各種正式場合，讓您展現優雅氣質和迷人魅力。</p>
					</div>

					<!-- 商品規格選擇 -->
					<div class="product-options">
						<div class="option-group">
							<label>尺寸選擇：</label>
							<div class="size-options">
								<button class="size-option" data-size="XS">XS</button>
								<button class="size-option" data-size="S">S</button>
								<button class="size-option active" data-size="M">M</button>
								<button class="size-option" data-size="L">L</button>
								<button class="size-option" data-size="XL">XL</button>
							</div>
						</div>

						<div class="option-group">
							<label>數量：</label>
							<div class="quantity-selector">
								<button class="qty-btn" onclick="changeQuantity(-1)">-</button>
								<input type="number" id="quantity" value="1" min="1" max="99">
								<button class="qty-btn" onclick="changeQuantity(1)">+</button>
							</div>
						</div>
					</div>

					<!-- 購買按鈕 -->
					<div class="purchase-actions">
						<button class="btn btn-primary" onclick="addToCart()">
							<i class="fas fa-shopping-cart"></i> 加入購物車
						</button>
						<button class="btn btn-secondary" onclick="buyNow()">
							<i class="fas fa-bolt"></i> 立即購買
						</button>
						<button class="btn btn-outline" onclick="addToWishlist()">
							<i class="fas fa-heart"></i> 加入願望清單
						</button>
					</div>

					<!-- 商品特色 -->
					<div class="product-features">
						<h3>商品特色</h3>
						<ul>
							<li><i class="fas fa-check"></i> 高品質面料，舒適透氣</li>
							<li><i class="fas fa-check"></i> 精緻剪裁，修身顯瘦</li>
							<li><i class="fas fa-check"></i> 多種顏色選擇</li>
							<li><i class="fas fa-check"></i> 適合各種場合</li>
							<li><i class="fas fa-check"></i> 易於搭配</li>
						</ul>
					</div>
				</div>
			</div>

			<!-- 商品詳細信息標籤頁 -->
			<div class="product-tabs">
				<div class="tab-buttons">
					<button class="tab-btn active" onclick="showTab('description')">商品描述</button>
					<button class="tab-btn" onclick="showTab('specifications')">規格參數</button>
					<button class="tab-btn" onclick="showTab('shipping')">配送說明</button>
				</div>

				<div class="tab-content">
					<!-- 商品描述 -->
					<div id="description" class="tab-pane active">
						<h3>商品詳細介紹</h3>
						<p>這款時尚優雅的連衣裙是我們精心設計的精品女裝，採用高品質面料製作，剪裁精緻，讓您展現優雅氣質和迷人魅力。</p>

						<h4>面料品質</h4>
						<p>選用優質聚酯纖維和棉質混紡面料，質地柔軟舒適，透氣性好，穿著舒適。面料具有優良的垂墜感，讓裙擺自然飄逸。</p>

						<h4>設計特色</h4>
						<p>採用A字型剪裁設計，修身顯瘦，突出女性優美曲線。領口設計優雅大方，袖長適中，適合各種身材的女性穿著。</p>

						<h4>搭配建議</h4>
						<p>可搭配高跟鞋、平底鞋或運動鞋，適合辦公室、約會、派對等各種場合。外搭西裝外套或針織開衫，更顯時尚品味。</p>
					</div>

					<!-- 規格參數 -->
					<div id="specifications" class="tab-pane">
						<h3>商品規格</h3>
						<table class="specs-table">
							<tr>
								<td class="spec-label">面料成分</td>
								<td class="spec-value">聚酯纖維65% + 棉35%</td>
							</tr>
							<tr>
								<td class="spec-label">面料重量</td>
								<td class="spec-value">180g/m²</td>
							</tr>
							<tr>
								<td class="spec-label">裙長</td>
								<td class="spec-value">膝蓋上方5cm</td>
							</tr>
							<tr>
								<td class="spec-label">袖長</td>
								<td class="spec-value">短袖</td>
							</tr>
							<tr>
								<td class="spec-label">領型</td>
								<td class="spec-value">圓領</td>
							</tr>
							<tr>
								<td class="spec-label">腰型</td>
								<td class="spec-value">高腰設計</td>
							</tr>
							<tr>
								<td class="spec-label">下擺</td>
								<td class="spec-value">A字型</td>
							</tr>
							<tr>
								<td class="spec-label">洗滌方式</td>
								<td class="spec-value">機洗30°C</td>
							</tr>
							<tr>
								<td class="spec-label">產地</td>
								<td class="spec-value">中國</td>
							</tr>
						</table>
					</div>



					<!-- 配送說明 -->
					<div id="shipping" class="tab-pane">
						<h3>配送與退換貨政策</h3>

						<h4>配送方式</h4>
						<div class="shipping-methods">
							<div class="shipping-method">
								<i class="fas fa-truck"></i>
								<div class="method-info">
									<h5>宅配到府</h5>
									<p>1-2個工作天送達，運費NT$ 100</p>
								</div>
							</div>
							<div class="shipping-method">
								<i class="fas fa-store"></i>
								<div class="method-info">
									<h5>超商取貨</h5>
									<p>2-3個工作天送達，運費NT$ 60</p>
								</div>
							</div>
							<div class="shipping-method">
								<i class="fas fa-clock"></i>
								<div class="method-info">
									<h5>快速配送</h5>
									<p>當日下單，隔日送達，運費NT$ 150</p>
								</div>
							</div>
						</div>

						<h4>退換貨政策</h4>
						<ul>
							<li>收到商品後7天內可申請退換貨</li>
							<li>商品必須保持全新狀態，包裝完整</li>
							<li>退貨運費由買方負擔</li>
							<li>換貨運費由賣方負擔</li>
						</ul>
					</div>
				</div>
			</div>
			
	<%@include file="components/suggestedProducts.jsp"%>
	<%@include file="components/footer.jsp"%>
	
	<script src="productPageScript.js"></script>
</body>
</html>


