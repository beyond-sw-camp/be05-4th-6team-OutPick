import axios from "axios";

let test_axios = axios.create({
    baseURL : "http://localhost:8080/",

    
    //getProductSort()

    getProductByCategoryId(categoryId) {
        return axios.get(`product/category/${categoryId}`,{
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

export function getAllProductList() {
    return test_axios.get("product/list", {
        headers: {
            'Content-Type': 'application/json'
        }
    })
}

export function addProduct(data) {
    return test_axios.post("product/write", data, {
        headers: {
            'Content-Type': 'application/json'
        }
    })
}

export function modifyProduct(productId, data) {
    return test_axios.post(`product/modify/${productId}`, data, {
        headers: {
            'Content-Type': 'application/json'
        }
    })
}

export function getProductById(productId) {
    return test_axios.get(`product/${productId}`, {
        headers: {
            'Content-Type': 'application/json'
        }
    }
)}
