<template>
  <div class="product-form">
    <form @submit.prevent="submitForm" class="form-container">
      <div class="form-group">
        <label for="productName">상품 이름</label>
        <input id="productName" v-model="product.name" type="text">
      </div>
      <div class="form-group">
        <label for="content">설명</label>
        <textarea id="content" v-model="product.content"></textarea>
      </div>
      <div class="form-group">
        <label for="category">카테고리</label>
        <input id="category" v-model="product.category" type="text">
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
        <label for="incomming_date">입고일</label>
        <input id="incomming_date" v-model="product.incomming_date" type="date">
      </div>
      <div class="form-group">
        <label for="supplier">공급업체</label>
        <input id="supplier" v-model="product.supplier" type="text">
      </div>
      <div class="form-group">
        <label for="storage">저장창고</label>
        <input id="storage" v-model="product.storage" type="text">
      </div>
      <div class="form-group action-buttons">
        <button type="submit" class="button" @click="updateInfo">저장</button>
        <button type="button" class="button" @click="gotoMain">취소</button>  <!-- 취소 버튼 누르면 메인 페이지로 이동하게 수정 예정-->
      </div>
    </form>
  </div>
</template>

<script>
import { ref } from 'vue';
//import axios from 'axios';
import {useRouter} from 'vue-router';

export default{
  name: 'ProductModify',
  setup(){
    const router = useRouter();
    const product = ref({
      name: '',
      content: '',
      category: '',
      price: '',
      stock: '',
      incomming_date: '',
      supplier: '',
      storage: ''
    })

    const gotoMain = () => {
      router.push('/');
    }

    const updateInfo = async () => {

    }

    return {
      product,
      gotoMain,
      updateInfo

    }
  }

}


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