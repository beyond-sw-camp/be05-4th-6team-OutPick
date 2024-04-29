<template> 
  <nav class="navbar navbar-expand-lg bg-body-tertiary">
    <div class="container-fluid">
      <router-link class="navbar-brand" :to="{ name : 'List' }">OutPick</router-link>
  
      <!-- 누르면 항목들이 나오는 버튼 -->
      <!-- <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
      </button> -->
      <!-- 눌렀을 때 나오는 항목들 -->
      <!-- <div class="collapse navbar-collapse" id="navbarNav"> -->
        <ul class="navbar-nav me-auto">
          <li class="nav-item">
            <router-link class="nav-link active" aria-current="page" :to="{name : 'ProductAdd'}">Product Add!!!!!</router-link>
          </li>
        </ul>
      <!-- </div> -->
    </div>
  </nav>

  <div class="container">
    <router-view/>
  </div>

</template>

<script>

export default {
  name: 'App',
  setup(){

    return{
      
    }
  }
}
</script>

<style>
#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
}
</style>
