// Lắng nghe sự kiện click trên biểu tượng giỏ hàng
document.addEventListener("DOMContentLoaded", function() {
	const addToCartButtons = document.querySelectorAll(".add-to-cart-button");
	addToCartButtons.forEach(function(button) {
		button.addEventListener("click", function(event) {
			event.preventDefault();
			const productId = button.getAttribute("data-product-id");
			const quantity = button.getAttribute("data-quantity");
			const quantityInput = document.getElementById("quantityInput");
			if (quantityInput != null) {
				addToCart(productId, quantityInput.value);
			}
			else {
				addToCart(productId, quantity);
			}
		});
	});
});

// Hàm thêm sản phẩm vào giỏ hàng bằng AJAX
function addToCart(productId, quantity) {
	const xhr = new XMLHttpRequest();
	xhr.open("POST", "/ecommerce/cart/add", true);
	xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
	xhr.onreadystatechange = function() {
		if (xhr.readyState === XMLHttpRequest.DONE && xhr.status === 200) {
			alert("Product added to cart successfully!");
		}
	};
	const params = "productId=" + productId + "&quantity=" + quantity;;
	xhr.send(params);
}


function updateTotal(input, price) {
	const cartItemId = input.getAttribute('data-cart-item-id');
	const quantity = input.value;
	const total = (price * quantity).toFixed(2);
	const totalElement = input.closest("tr").querySelector(".shoping__cart__total");
	totalElement.textContent = total + " $";
	updateCartTotal();
	var xhr = new XMLHttpRequest();
	xhr.open("POST", "/ecommerce/cart/updateCartItemQuantity", true);
	xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
	xhr.send("cartItemId=" + cartItemId + "&quantity=" + quantity);
}

function updateCartTotal() {
	const itemRows = document.querySelectorAll(".product_item");
	let subtotal = 0;

	itemRows.forEach(function(row) {
		const price = parseFloat(row.querySelector(".shoping__cart__price").textContent);
		const quantity = parseInt(row.querySelector(".pro-qty input").value);
		const total = price * quantity;
		subtotal += total;
	});
	const subtotalElement = document.getElementById("subtotal");
	const totalElement = document.getElementById("total");

	subtotalElement.textContent = "$" + subtotal.toFixed(2);
	totalElement.textContent = "$" + subtotal.toFixed(2);
}

document.addEventListener("DOMContentLoaded", function() {
	updateCartTotal();
});
