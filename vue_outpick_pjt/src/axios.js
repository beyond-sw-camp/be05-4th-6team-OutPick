import axios from "axios";

export default axios.create({
    baseURL : "http://localhost:8080/",
    
    getProductById(productId) {
        return axios.get(`product/${productId}`, {
            headers: {
                'Content-Type': 'application/json'
            }
        }
    )},

    getAllProductList() {
        return axios.get("/product/list", {
            headers: {
                'Content-Type': 'application/json'
            }
        })
    },
    
    //getProductSort()

    getProductByCategoryId(categoryId) {
        return axios.get(`product/category/${categoryId}`,{
            headers: {
                'Content-Type': 'application/json'
            }
        })
    },

    addProduct(data) {
        return axios.post("product/write", data, {
            headers: {
                'Content-Type': 'application/json'
            }
        })
    },

    modifyProduct(productId, data) {
        return axios.post(`product/modify/${productId}`, data, {
            headers: {
                'Content-Type': 'application/json'
            }
        })
    },

    deleteProduct(productId) {
        return axios.delete(`product/delete/${productId}`, {
            headers: {
                'Content-Type': 'application/json'
            }
        })
    },

    findByName(name) {
        return axios.get(`product/find/${name}`, {
            headers: {
                'Content-Type': 'application/json'
            }
        })
    }


})
