<template>
  <div class="container">

    <!-- 검색창 -->
    <div class="search_div">
      <form v-on:submit.prevent="search" class="row mt-4" >
        <div class="col-3" >
          <h5>상품이름</h5>
        </div>
        <div class="col-7" >
          <input type="text" v-model="searchText" placeholder="검색하세요" class="form-control" >
        </div>
        <div class="col-2" >
          <button class="btn btn-outline-light text-black" >검색</button>
        </div>
      </form>
    </div>

    <hr>

    <!-- 상품조회 -->
    <div class="row row-right mt-4">
      <div class="col-3" >
        <h5 class="seeList" @click="initial" >상품조회 : {{ copy_pd_list.length }} 건 </h5>
      </div>
      <div class="col-8 flex">
        <button class="btn btn-outline-light text-black" v-on:click="toAddPage" >등록</button>
      </div>
    </div>

    <div class="row mt-1 listDiv">
      <table class="table table-hover border-gray">
        <thead>
        <tr>
          <th>상품ID</th>
          <th>상품이름</th>
          <th>가격</th>
          <th>입고일</th>
          <th>공급업체</th>
        </tr>
        </thead>
        <tbody v-for="(pd, index) in copy_pd_list" :key="pd.productId" >
        <tr v-on:click="select(pd.productId)">
          <td>{{ index+1 }}</td>
          <td>{{ pd.name }}</td>
          <td>{{ pd.price }}</td>
          <td>{{ formatDate(pd.incommingDate) }}</td>
          <td>{{ pd.supplier }}</td>
          <td><button class="btn btn-outline-warning" v-on:click.stop="toModifyPage(pd.productId)"
          >수정</button> </td>
          <td><button class="btn btn-outline-danger" v-on:click.stop="pd_delete(pd.productId)" >삭제</button> </td>
        </tr>
        </tbody>

      </table>
    </div>

    <div v-if="search_result" class="alert alert-info mt-3">
      검색 결과가 존재하지 않습니다.
    </div>

    <div class="row row-right mt-5">
      <h5>상품 설명</h5>
    </div>

    <div class="row listDiv" >
      <table class="table border-gray">
        <thead>
          <th>카테고리 ID</th>
          <th>설명</th>
          <th>저장 창고</th>
          <th>재고</th>
        </thead>
        <tbody>
        <tr>
          <td>{{ pd_view.categoryId }}</td>
          <td>{{ pd_view.content }}</td>
          <td>{{ pd_view.storage }}</td>
          <td>{{ pd_view.stock }}</td>
        </tr>
        </tbody>
      </table>
    </div>

  </div>
</template>

<script>
import {useRouter} from 'vue-router';
import { ref} from "vue";
import { getAllProductList, deleteProduct
          , getProductById } from '@/axios';

export default {

  setup(){

    function formatDate(dateStr) { // 날짜 형식 변환해주는 함수임.
      if (!dateStr) return '날짜 정보 없음'; // 날짜 데이터가 없거나 null일 경우
      const date = new Date(dateStr);
      if (isNaN(date.getTime())) return '유효하지 않은 날짜'; // 날짜 객체가 유효하지 않은 경우
      return date.toISOString().slice(0, 10);
    }

    const searchText = ref('');
    const pd_view = ref({});
    const search_result = ref(false);

    const product_list = ref([]);

    // search에 따라 변경되는 값
    let copy_pd_list = ref([{}]);

    const search = () => {

      if(searchText.value){
        copy_pd_list.value = product_list.value.filter( (search_pd) => {
          return search_pd.name.includes(searchText.value);
        })
      }else {
        copy_pd_list.value = product_list.value;
      }

      if(copy_pd_list.value.length === 0){
        search_result.value = true;
      }

      searchText.value = "";
      pd_view.value = {};
    }

    // 상품조회 버튼 클릭시 전체 리스트 받아오기
    const initial = () => {
      copy_pd_list.value = product_list.value;
      search_result.value = false;
    }

    const getList = async () => {

      // axios - get 통신 : 전체리스트
      const response = await getAllProductList();
      product_list.value = response.data;

      // search를 위한 복제
      copy_pd_list.value = [...product_list.value];
    }

    getList();

    const pd_delete = async (productId) => {
      let answer = window.confirm("삭제하시겠습니까?");

      if (answer) {
        // axios - delete 통신 : 상품정보 삭제
        const response = await deleteProduct(productId);
        console.log(response);

        getList();
      }
    }

    const select = async (productId) => {

      // axios - get 통신 : 상세정보
      const response = await getProductById(productId);

      pd_view.value = response.data;
    }

    // 등록 페이지로 이동
    const router = useRouter();
    const toAddPage = () => {
      router.push({
        name : "ProductAdd"
      })
    }

    const toModifyPage = (productId) => {
      router.push({
        name : "ProductModify",
        params : {
          productId : productId
        }
      })
    }

    return{
      search,
      pd_delete,
      select,
      product_list,
      copy_pd_list,
      searchText,
      search_result,
      pd_view,
      toAddPage,
      toModifyPage,
      initial,
      formatDate
    }
  }

}
</script>

<style scoped>
@import url('https://fonts.googleapis.com/css2?family=Gowun+Dodum&display=swap');

.gowun-dodum-regular {
  font-family: "Gowun Dodum", sans-serif;
  font-weight: 400;
  font-style: normal;
}

div{
  font-family: "Gowun Dodum", sans-serif;
}

.row{
  display: flex;
  align-items: center;
}

.flex{
  display: flex;
  flex-direction: row-reverse;
  flex-wrap : wrap;
  gap : 5px;
}

form > .col-3{
  text-align: right;
}

form > .col-2{
  display: flex;
  align-items: flex-start;
}

.row-right{
  text-align: left;
  padding-left: 5%;
}

td {
  vertical-align: middle; /* 세로 방향 가운데 정렬 */
}

button:hover, .listDiv{
  box-shadow: 0 6px 7px rgba(79, 79, 79, 0.2); 
}

.seeList:hover{
  text-shadow: 0 3px 7px rgba(17, 17, 17, 0.403); 
}

.alert{
  font-weight: bold;
  box-shadow: 0 3px 7px rgba(139, 139, 139, 0.403); 
}

.listDiv{
  max-height : 30vh;
  overflow-y: auto;
}

</style>