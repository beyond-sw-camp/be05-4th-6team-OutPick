<template>
    <div class="container">

      <!-- 검색창 -->
      <form v-on:submit.prevent="search" class="row mt-4" >
        <div class="col-3" >
          <h5>상품이름</h5>
        </div>
        <div class="col-7" >
          <input type="text" v-model="searchText" placeholder="검색하세요" class="form-control" >
        </div>
        <div class="col-2" >
          <button class="btn bg-light" >검색</button>
        </div>
      </form>

      <!-- 상품조회 -->
      <div class="row row-right mt-4">
        <div class="col-7" >
          <h5>상품조회</h5>
        </div>
        <div class="col-4 flex">
          <button class="btn bg-light" >등록</button>
        </div>
      </div>

      <div class="row mt-1">
        <table class="table table-hover">

          <thead>
            <tr>
              <th>상품ID</th>
              <th>상품이름</th>
              <th>가격</th>
              <th>입고일</th>
              <th>공급업체</th>
            </tr>
          </thead>
          <tbody v-for="pd in copy_pd_list" :key="pd.product_id" >
            <tr v-on:click="select(pd.product_id)">
              <td>{{ pd.product_id }}</td>
              <td>{{ pd.name }}</td>
              <td>{{ pd.price }}</td>  
              <td>{{ pd.incomming_date }}</td>
              <td>{{ pd.supplier }}</td>
              <td> <button class="btn bg-white" v-on:click="modify(pd.product_id)" >수정</button> </td>
              <td><button class="btn bg-white" v-on:click="pd_delete(pd.product_id)" >삭제</button> </td>
            </tr>
          </tbody>

        </table>
      </div>

      <div class="row row-right mt-5"> 
        <h5>상품 설명</h5>
      </div>

      <div class="row" >
        <table class="table">
          <thead>
            <th>설명</th>
            <th>저장 창고</th>
          </thead>
          <tbody>
            <tr>
              <td>{{ pd_view.content }}</td>
              <td>{{ pd_view.storage }}</td>
            </tr>
          </tbody>
        </table>
      </div>

    </div>
</template>
  
<script>
import { ref} from "vue";

export default {

  setup(){

    const searchText = ref('');
    const pd_view = ref({});

    const product_list = ref([
      {product_id : 1,
        name : "바나나",
        price : "3000",
        incomming_date : "2000-03-01",
        supplier : "필리핀"}
    ,{product_id : 2,
        name : "사과",
        price : "5000",
        incomming_date : "2000-06-03",
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

      searchText.value = "";
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
      storage : "h-101"}
    }

    return{
      search,
      modify,
      pd_delete,
      select,
      product_list,
      copy_pd_list,
      searchText,
      pd_view
    }
  }

}
</script>

<style>
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

.row-right{
  text-align: left;
  padding-left: 5%;
}

table tbody tr td {
  vertical-align: middle; /* 세로 방향 가운데 정렬 */
}

</style>