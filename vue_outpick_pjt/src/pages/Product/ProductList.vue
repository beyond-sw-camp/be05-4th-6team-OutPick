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
        <h5 class="seeList" @click="initial" >상품조회</h5>
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
        <tbody v-for="(pd, index) in copy_pd_list" :key="pd.product_id" >
        <tr v-on:click="select(pd.product_id)">
          <td>{{ index+1 }}</td>
          <td>{{ pd.name }}</td>
          <td>{{ pd.price }}</td>
          <td>{{ pd.incomming_date }}</td>
          <td>{{ pd.supplier }}</td>
          <td><button class="btn btn-outline-warning" v-on:click.stop="toModifyPage(pd.product_id)"
          >수정</button> </td>
          <td><button class="btn btn-outline-danger" v-on:click.stop="pd_delete(pd.product_id)" >삭제</button> </td>
        </tr>
        </tbody>

      </table>
    </div>

    <div v-if="search_result" class="alert alert-info">
      검색 결과가 존재하지 않습니다.
    </div>

    <div class="row row-right mt-5">
      <h5>상품 설명</h5>
    </div>

    <div class="row" >
      <table class="table border-gray">
        <thead>
        <th>설명</th>
        <th>저장 창고</th>
        <th>재고</th>
        </thead>
        <tbody>
        <tr>
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

export default {

  setup(){

    const searchText = ref('');
    const pd_view = ref({});
    const search_result = ref(false);

    const product_list = ref([
      {product_id : 1,
        name : "바나나",
        price : "3000",
        incomming_date : "2000-03-01",
        stock : 3,
        supplier : "필리핀"}
      ,{product_id : 2,
        name : "사과",
        price : "5000",
        incomming_date : "2000-06-03",
        stock : 2,
        supplier : "일본"},
      {product_id : 2,
        name : "사과",
        price : "5000",
        incomming_date : "2000-06-03",
        stock : 2,
        supplier : "일본"},
      {product_id : 2,
        name : "사과",
        price : "5000",
        incomming_date : "2000-06-03",
        stock : 2,
        supplier : "일본"},
      {product_id : 2,
        name : "사과",
        price : "5000",
        incomming_date : "2000-06-03",
        stock : 2,
        supplier : "일본"},
      {product_id : 2,
        name : "사과",
        price : "5000",
        incomming_date : "2000-06-03",
        stock : 2,
        supplier : "일본"}]);

    // search에 따라 변경되는 값
    let copy_pd_list = ref([{}]);

    const search = () => {
      window.alert("click!");

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

    const getList = () => {

      // axios - get 통신 : 전체리스트

      copy_pd_list.value = [...product_list.value];

      // console.log(copy_pd_list.value[0]);
    }

    getList();

    const modify = (product_id) => {
      window.alert("modify : " + product_id)

      // axios - post 통신 : 상품정보 수정
    }

    const pd_delete = (product_id) => {
      window.alert("delete : " + product_id)

      // axios - delete 통신 : 상품정보 삭제
    }

    const select = (product_id) => {
      window.alert("select : " + product_id)

      // axios - get 통신 : 상세정보

      pd_view.value = {content : "happy",
        storage : "h-101", stock : 3}
    }

    // 등록 페이지로 이동
    const router = useRouter();
    const toAddPage = () => {
      router.push({
        name : "ProductAdd"
      })
    }

    const toModifyPage = (product_id) => {
      router.push({
        name : "ProductModify",
        params : {
          product_id : product_id
        }
      })
    }

    // 전체 리스트 받아오기
    const initial = () => {
      copy_pd_list.value = product_list.value;
      search_result.value = false;
    }

    return{
      search,
      modify,
      pd_delete,
      select,
      product_list,
      copy_pd_list,
      searchText,
      search_result,
      pd_view,
      toAddPage,
      toModifyPage,
      initial
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

button:hover, table{
  box-shadow: 0 6px 7px rgba(79, 79, 79, 0.2); /* 강조된 그림자 효과 */
}

.seeList:hover{
  text-shadow: 0 3px 7px rgba(17, 17, 17, 0.403); /* 강조된 그림자 효과 */
}

.listDiv{
  max-height : 30vh;
  overflow-y: auto;
}

</style>