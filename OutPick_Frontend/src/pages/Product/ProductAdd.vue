<template>
  <div class="product-form">
    <form @submit.prevent="submitForm" class="form-container">
      <div class="form-group">
        <label for="productName">상품 이름</label>
        <input id="productName" v-model="product.name" type="text">
      </div>
      <div class="form-group">
        <label for="description">설명</label>
        <textarea id="description" v-model="product.content"></textarea>
      </div>
      <div class="form-group">
        <label for="category">카테고리</label>
        <input id="category" v-model="product.categoryId" type="text">
      </div>
      <div class="form-group">
        <label for="price">가격</label>
        <input id="price" v-model="product.price" type="number">
      </div>
      <div class="form-group">
        <label for="stock">재고 수</label>
        <input id="stock" v-model="product.stock" type="number">
      </div>
      <div class="form-group">
        <label for="incommingDate">입고 날짜</label>
        <input id="incommingDate" v-model="product.incommingDate" type="date">
      </div>
      <div class="form-group">
        <label for="supplier">공급업체</label>
        <input id="supplier" v-model="product.supplier" type="text">
      </div>
      <div class="form-group">
        <label for="storage">저장 창고</label>
        <input id="storage" v-model="product.storage" type="text">
      </div>
      <div class="form-group action-buttons">
        <button type="submit" class="button">저장</button>
        <button type="button" class="button" @click="cancel">취소</button>
      </div>
    </form>
  </div>
</template>

<script setup>
import { reactive } from 'vue';
import { addProduct } from '@/axios';
import { useRouter } from 'vue-router';

const product = reactive({
  name: '',
  price: 0,
  content: '',
  categoryId: '',
  stock: 0,
  incommingDate: '',
  supplier: '',
  storage: ''
});
const router = useRouter();

const submitForm = async () => {
  try {
    const response = await addProduct(product);
    console.log('Product added', response.data);

    // 팝업 창을 띄워 사용자에게 선택지 제공
    if (confirm("저장이 완료되었습니다. 계속 추가하시겠습니까?")) {
      // 계속 추가를 선택한 경우, 폼 초기화
      resetForm();
    } else {
      // 취소를 선택한 경우, 메인 페이지로 이동
      router.push({ name: 'List' });
    }
  } catch (error) {
    console.error('Error adding product', error);
    alert("저장에 실패하였습니다");
  }
};

function resetForm() {
  Object.keys(product).forEach(key => {
    if (typeof product[key] === 'number') {
      product[key] = 0;
    } else {
      product[key] = '';
    }
  });
}

const cancel = () => {
  router.push({ name: 'List' });
};
</script>


<style>
.product-form {
  max-width: 800px;
  margin: 40px auto;
  padding: 20px;
  border: 1px solid #ccc;
  border-radius: 8px;
  background-color: #f9f9f9;
}

.form-container {
  display: flex;
  flex-direction: column;
  gap: 15px;
}

.form-group {
  display: flex;
  flex-direction: column;
}

.form-group label {
  font-size: 16px;
  color: #333;
  margin-bottom: 5px;
}

.form-group input,
.form-group textarea {
  padding: 8px;
  border: 1px solid #ccc;
  border-radius: 4px;
  font-size: 14px;
}

.action-buttons {
  display: flex;
  justify-content: flex-end; /* Align buttons to the right */
  gap: 10px;
}

.button {
  flex: none; /* Override previous flex setting */
  padding: 6px 12px; /* Reduced padding for smaller buttons */
  border: none;
  border-radius: 4px;
  cursor: pointer;
  font-size: 14px; /* Smaller font size */
  color: white;
  min-width: 80px; /* Ensure minimum width for each button */
}

.button:nth-child(1) { /* First button, i.e., save */
  background-color: blue; /* Green */
}

.button:nth-child(2) { /* Second button, i.e., cancel */
  background-color: #f44336; /* Red */
}
</style>

