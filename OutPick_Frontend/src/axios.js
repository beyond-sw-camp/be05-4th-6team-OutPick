import axios from "axios";

let test_axios = axios.create({
    baseURL : "http://localhost:8972/",
    headers: {
        'Content-Type': 'application/json; charset=utf-8'
    }
});

export function getProductByCategoryId(categoryId) {
    return test_axios.get(`product/category/${categoryId}`);
}

export function deleteProduct(productId) {
    return test_axios.delete(`product/delete/${productId}`);
}

export function findByName(name) {
    return test_axios.get(`product/find/${name}`);
}

export function getAllProductList() {
    return test_axios.get("product/list");
}

export function addProduct(data) {
    return test_axios.post("product/write", data);
}

export function modifyProduct(productId, data) {
    return test_axios.post(`product/modify/${productId}`, data);
}

export function getProductById(productId) {
    return test_axios.get(`product/${productId}`);
}
