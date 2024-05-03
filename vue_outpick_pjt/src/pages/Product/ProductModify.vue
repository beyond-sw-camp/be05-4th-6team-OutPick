<template>
  <div class="product-form">
    <form @submit.prevent="updateInfo" class="form-container">
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
        <label for="incommingDate">입고일</label>
        <input id="incommingDate" v-model="product.incommingDate" type="date">
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
        <button type="submit" class="button">수정</button>
        <button type="button" class="button" @click="gotoMain">취소</button>  
      </div>
    </form>
  </div>
</template>

<script>
import { ref } from 'vue';
import { modifyProduct, getProductById } from '@/axios';
import { useRoute, useRouter } from 'vue-router';

export default {
  name: 'ProductModify',
  setup() {
    const route = useRoute();
    const router = useRouter();
    const productId = route.params.productId;
    const product = ref({});

    const gotoMain = () => {
      router.push({ name: "List" });
    };

    const getProduct = async () => {
      try {
        const response = await getProductById(productId);
        // 날짜 데이터를 변환하여 저장
        product.value = {
          ...response.data,
          incommingDate: formatDate(response.data.incommingDate)
        };
      } catch (error) {
        console.error("정보 불러오기 실패: ", error);
      }
    };

    function formatDate(dateStr) {
      if (!dateStr) return '';
      const date = new Date(dateStr);
      const formattedDate = date.toISOString().substring(0, 10);
      return formattedDate; // ISO string을 "yyyy-MM-dd" 형식으로 변환
    }

    getProduct();

    const updateInfo = async () => {
    console.log("전송되는 데이터:", JSON.stringify(product.value, null, 2));
    try {
        const response = await modifyProduct(productId, product.value);
        console.log("서버 응답:", response);
        if (response.status === 200) {
            alert("수정되었습니다.");
            router.push('/');
        } else {
            throw new Error(`Server responded with status: ${response.status}`);
        }
    } catch (error) {
        console.error("수정에 실패하였습니다: ", error);
        alert("수정 실패!");
    }
};


    return {
      product,
      gotoMain,
      updateInfo
    };
  }
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