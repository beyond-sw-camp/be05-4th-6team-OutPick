import {createRouter, createWebHistory} from "vue-router";

import ProductAdd from "@/pages/Product/ProductAdd.vue";
import ProductList from "@/pages/Product/ProductList.vue"
import ProductModify from "@/pages/Product/ProductModify.vue"

const router = createRouter({
    history : createWebHistory(),
    routes : [
        {
            path : "/",
            name : "List",
            component : ProductList
        },
        {
            path : "/product/add",
            name : "ProductAdd",
            component : ProductAdd   
        },
        {
            path : "/product/modify/:productId",
            name : "ProductModify",
            component : ProductModify
        }
    ]
});

export default router;